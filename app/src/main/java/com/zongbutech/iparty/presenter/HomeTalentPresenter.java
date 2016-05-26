package com.zongbutech.iparty.presenter;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Talent;
import com.zongbutech.iparty.view.IView.IHomeTalentView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lixian on 2016/3/10.
 */
public class HomeTalentPresenter extends BasePresenter {

    private static final String TAG = HomeTalentPresenter.class.getSimpleName();
    private IHomeTalentView mIHomeTalentView;
    private Context ct;

    public HomeTalentPresenter(Context ct, IHomeTalentView mIHomeTalentView) {
        super(ct);
        this.mIHomeTalentView = mIHomeTalentView;
        this.ct = ct;
    }


    //加载的的part类行
    public void loadBeans(final int type, final int pageIndex) {
        if (pageIndex == 0) {
            mIHomeTalentView.showProgress();
        }

        mIpartApi.getTalents(pageIndex+1, Constants.PAZE_Roll, type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<JsonObject>() {
            @Override
            public void call(JsonObject jsonObject) {
                int code = jsonObject.get("code").getAsInt();
                if (code == 0) {
                    JsonArray result = jsonObject.get("data").getAsJsonArray();
                    List<Talent> mBeans = new ArrayList<Talent>();
                    for (int i = 0; i < result.size(); i++) {
                        Talent mBean = JsonUtils.deserialize(result.get(i).toString(), Talent.class);
                        mBeans.add(mBean);
                    }
                    mIHomeTalentView.hideProgress();
                    mIHomeTalentView.addNews(mBeans);
                } else {
                    mIHomeTalentView.hideProgress();
                    mIHomeTalentView.showLoadFailMsg(jsonObject.toString(), new Exception());
                }
            }
        });




    }




}
