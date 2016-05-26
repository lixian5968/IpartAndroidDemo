package com.zongbutech.iparty.presenter;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Party;
import com.zongbutech.iparty.model.DbModel.PartDbModel;
import com.zongbutech.iparty.view.IView.IHomePartView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lixian on 2016/3/10.
 */
public class HomePartPresenter extends BasePresenter {

    private static final String TAG = HomePartPresenter.class.getSimpleName();
    private IHomePartView mIHomePartView;
    private Context ct;
    PartDbModel mPartDbModel;

    public HomePartPresenter(Context ct, IHomePartView mIHomePartView) {
        super(ct);
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

        mIpartApi.getParts(pageIndex+1, Constants.PAZE_Roll, type, 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<JsonObject>() {
            @Override
            public void call(JsonObject jsonObject) {
                int code = jsonObject.get("code").getAsInt();
                if (code == 0) {
                    JsonArray result = jsonObject.get("data").getAsJsonArray();
                    List<Party> mPartys = new ArrayList<Party>();
                    for (int i = 0; i < result.size(); i++) {
                        Party mParty = JsonUtils.deserialize(result.get(i).toString(), Party.class);
                        mPartys.add(mParty);
                    }
                    mIHomePartView.hideProgress();
                    mIHomePartView.addNews(mPartys);
                } else {
                    mIHomePartView.hideProgress();
                    mIHomePartView.showLoadFailMsg(jsonObject.toString(), new Exception());
                }
            }
        });




    }




}
