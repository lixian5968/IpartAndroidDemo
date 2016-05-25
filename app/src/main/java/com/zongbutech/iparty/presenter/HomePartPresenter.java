package com.zongbutech.iparty.presenter;

import android.content.Context;

import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.Utils.OkHttpUtils;
import com.loveiparty.http.Utils.Urls;
import com.zongbutech.iparty.beans.HomePartyBean;
import com.zongbutech.iparty.beans.HomePartysBean;
import com.zongbutech.iparty.beans.PresenterBean;
import com.zongbutech.iparty.db.Party;
import com.zongbutech.iparty.model.DbModel.PartDbModel;
import com.zongbutech.iparty.view.IView.IHomePartView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lixian on 2016/3/10.
 */
public class HomePartPresenter {

    private static final String TAG = HomePartPresenter.class.getSimpleName();
    private IHomePartView mIHomePartView;
    private Context ct;
    PartDbModel mPartDbModel;

    public HomePartPresenter(Context ct, IHomePartView mIHomePartView) {
        this.mIHomePartView = mIHomePartView;
        this.ct = ct;
        mPartDbModel = new PartDbModel(ct);
    }

    String url;

    //加载的的part类行
    public void loadParts(final int type, final int pageIndex) {
        if (pageIndex == 0) {
            mIHomePartView.showProgress();
        }


        Observable.create(new Observable.OnSubscribe<PresenterBean>() {
            @Override
            public void call(Subscriber<? super PresenterBean> subscriber) {
                url = getUrl(type, pageIndex);
                PresenterBean mPresenterBean = new PresenterBean();
                mPresenterBean.IntType = type;
                mPresenterBean.page = pageIndex;
                List<Party> OldmPartys = mPartDbModel.getParts(type, pageIndex);
                mPresenterBean.OldList = OldmPartys;
                subscriber.onNext(mPresenterBean);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<PresenterBean, PresenterBean>() {
                    @Override
                    public PresenterBean call(PresenterBean presenterBean) {
                        List<Party> OldmPartys = presenterBean.OldList;
                        if (presenterBean.OldList != null && presenterBean.OldList.size() > 0) {
                            mIHomePartView.hideProgress();
                            mIHomePartView.addNews(null, OldmPartys, false);
                        }
                        return presenterBean;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<PresenterBean, PresenterBean>() {
                    @Override
                    public PresenterBean call(PresenterBean presenterBean) {
                        String result = OkHttpUtils.get(ct, url);
                        HomePartysBean bean = JsonUtils.deserialize(result, HomePartysBean.class);
                        if (bean.code < 0) {
                            presenterBean.error = result;
                            return presenterBean;
                        } else {
                            if (CheckDbParty(bean.data, presenterBean.OldList)) {
                                for (HomePartyBean mHomePartyBean : bean.data) {
                                    if (mHomePartyBean != null) {
                                        mPartDbModel.saveDbParty(mHomePartyBean);
                                    }
                                }
                                List<Party> NewPartys = mPartDbModel.getParts(type, pageIndex);
                                presenterBean.NewList = NewPartys;
                                return presenterBean;
                            } else {
                                return null;
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PresenterBean>() {
                    @Override
                    public void call(PresenterBean presenterBean) {
                        if (presenterBean != null) {
                            mIHomePartView.hideProgress();
                            if (presenterBean.error == null || presenterBean.error.length() == 0) {
                                mIHomePartView.addNews(presenterBean.OldList, presenterBean.NewList, false);
                            } else {
                                mIHomePartView.showLoadFailMsg(presenterBean.error, new Exception(presenterBean.error));
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mIHomePartView.hideProgress();
                        mIHomePartView.showLoadFailMsg(throwable.getMessage(), (Exception) throwable);
                    }
                });


    }


    private boolean CheckDbParty(List<HomePartyBean> list, List<Party> dblist) {
        if (dblist != null
                && list != null
                && dblist.size() == list.size()
                && dblist.size() > 0
                && list.size() > 0
                && list.get(0).party_id == (dblist.get(0).getId())
                && list.get(0).last_update_time.compareTo(dblist.get(0).getUpdate_time()) <= 0
                ) {
            return false;
        } else {
            return true;
        }

    }

    //pageIndex 第一个数据为0
    private String getUrl(int type, int pageIndex) {
        String url;
        if (type == 0) {
            url = Urls.UrlStart + "partylist/" + (pageIndex + 1) + "/10?joined_users=1";
        } else {
            url = Urls.UrlStart + "partylist/" + (pageIndex + 1) + "/10?types=" + type + "&joined_users=1";
        }
        return url;
    }


}
