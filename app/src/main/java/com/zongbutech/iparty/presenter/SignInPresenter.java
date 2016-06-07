package com.zongbutech.iparty.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Order;
import com.loveiparty.http.db.Party;
import com.loveiparty.http.db.User;
import com.zongbutech.iparty.beans.TalentOrdersBean;
import com.zongbutech.iparty.view.IView.ISignInView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lixian on 2016/3/10.
 */
public class SignInPresenter extends BasePresenter {

    private static final String TAG = SignInPresenter.class.getSimpleName();
    private ISignInView mISignInView;
    private Context ct;

    public SignInPresenter(Context ct, ISignInView mISignInView) {
        super(ct);
        this.mISignInView = mISignInView;
        this.ct = ct;
    }


    //加载的的part类行
    List<TalentOrdersBean> mTalentOrdersBeans;
    List<Party> mPartys;
    List<Order> mOrders;
    List<User> mUsers;



    public void loadBeans(final int pageIndex) {
        if (pageIndex == 0) {
            mISignInView.showProgress();
        }
        mIpartApi.talentParty(3, pageIndex, Constants.PAZE_Roll)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<JsonObject, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            JsonArray mJsonArray = jsonObject.get("data").getAsJsonArray();
                            mTalentOrdersBeans = new ArrayList<TalentOrdersBean>();
                            HashSet<Integer> mPartyIdHashSets = new HashSet<Integer>();
                            for (int i = 0; i < mJsonArray.size(); i++) {
                                TalentOrdersBean mTalentOrdersBean = JsonUtils.deserialize(mJsonArray.get(i).toString(), TalentOrdersBean.class);
                                mTalentOrdersBeans.add(mTalentOrdersBean);
                                mPartyIdHashSets.add(mTalentOrdersBean.party_id);
                            }
                            List<Integer> mPartyIdListts = new ArrayList<Integer>();
                            mPartyIdListts.addAll(mPartyIdHashSets);
                            String PartyIds = "";
                            for (int i = 0; i < mPartyIdListts.size(); i++) {
                                PartyIds += mPartyIdListts.get(i);
                                if (i != mPartyIdListts.size() - 1) {
                                    PartyIds += ",";
                                }
                            }

                            return mIpartApi.getPartysByIds(PartyIds, 1).subscribeOn(Schedulers.io());
                        } else {
                            return Observable.error(new RuntimeException(jsonObject.toString()));
                        }

                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<JsonObject, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            JsonArray mJsonArray = jsonObject.get("data").getAsJsonArray();
                            mPartys = new ArrayList<Party>();
                            HashSet<Integer> mPartyIdHashSets = new HashSet<Integer>();
                            for (int i = 0; i < mJsonArray.size(); i++) {
                                Party mParty = JsonUtils.deserialize(mJsonArray.get(i).toString(), Party.class);
                                mPartys.add(mParty);
                                mPartyIdHashSets.add(mParty.getParty_id());
                            }
                            List<Integer> mPartyIdListts = new ArrayList<Integer>();
                            mPartyIdListts.addAll(mPartyIdHashSets);
                            String PartyIds = "";
                            for (int i = 0; i < mPartyIdListts.size(); i++) {
                                PartyIds += mPartyIdListts.get(i);
                                if (i != mPartyIdListts.size() - 1) {
                                    PartyIds += ",";
                                }
                            }
                            return mIpartApi.getPartysOrdersByIds(PartyIds).subscribeOn(Schedulers.io());
                        } else {
                            return Observable.error(new RuntimeException(jsonObject.toString()));
                        }

                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<JsonObject, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            JsonArray mJsonArray = jsonObject.get("data").getAsJsonArray();
                            mOrders = new ArrayList<Order>();
                            HashSet<Integer> mHashSet = new HashSet<Integer>();
                            for (int i = 0; i < mJsonArray.size(); i++) {
                                Order mOrder = JsonUtils.deserialize(mJsonArray.get(i).toString(), Order.class);
                                mOrders.add(mOrder);
                                mHashSet.add(mOrder.getUser_id());
                            }
                            List<Integer> mList = new ArrayList<Integer>();
                            mList.addAll(mHashSet);
                            String UserIds = "";
                            for (int i = 0; i < mList.size(); i++) {
                                UserIds += mList.get(i);
                                if (i != mList.size() - 1) {
                                    UserIds += ",";
                                }
                            }
                            return mIpartApi.getUsersByIds(UserIds).subscribeOn(Schedulers.io());
                        } else {
                            return Observable.error(new RuntimeException(jsonObject.toString()));
                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action1<JsonObject>() {
                            @Override
                            public void call(JsonObject mJsonObject) {
                                int code = mJsonObject.get("code").getAsInt();
                                if (code == 0) {
                                    JsonArray mJsonArray = mJsonObject.get("data").getAsJsonArray();
                                    mUsers = new ArrayList<User>();
                                    for (int i = 0; i < mJsonArray.size(); i++) {
                                        User mUser = JsonUtils.deserialize(mJsonArray.get(i).toString(), User.class);
                                        mUsers.add(mUser);
                                    }
                                    mISignInView.addBeans(mTalentOrdersBeans,mPartys,mOrders,mUsers);
                                } else {
                                    mISignInView.mToast(mJsonObject.toString());
                                }
                                Log.e("lx", mJsonObject.toString());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mISignInView.showLoadFailMsg(throwable.getMessage(), (Exception) throwable);
                            }
                        });


    }


}
