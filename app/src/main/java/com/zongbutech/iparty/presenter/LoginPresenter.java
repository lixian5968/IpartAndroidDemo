package com.zongbutech.iparty.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.loveiparty.http.HttpBean.HttpUserInfo;
import com.loveiparty.http.Utils.JsonUtils;
import com.zongbutech.iparty.model.DbModel.UserDbModel;
import com.zongbutech.iparty.utils.db.SharePrefUtil;
import com.zongbutech.iparty.view.IView.ILoginView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lixian on 2016/5/25.
 */
public class LoginPresenter extends BasePresenter {
    private ILoginView mILoginView;
    Context ct;
    UserDbModel mLoginDbModel;

    public LoginPresenter(Context ct, ILoginView mILoginView) {
        super(ct);
        this.mILoginView = mILoginView;
        this.ct = ct;
        mLoginDbModel = new UserDbModel(ct);
    }


    public int user_id;
    int HongBaoPage = 1;
    int HongBaoRoll = 10;
    int only_valid = 1;

    public void login(String phoneNumber, String passwordNumber) {
        JsonObject object = new JsonObject();
        object.addProperty("telephone", phoneNumber);
        object.addProperty("password", passwordNumber);

        mIpartApi.IpartLogin(object).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<JsonObject, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            user_id = jsonObject.get("user_id").getAsInt();
                            return mIpartApi.IpartGetUserByID(user_id + "").subscribeOn(Schedulers.io());
                        } else {
                            return Observable.error(new RuntimeException(jsonObject.toString()));
                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<JsonObject, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(JsonObject mJsonObject) {
                        int code = mJsonObject.get("code").getAsInt();
                        if (code == 0) {
                            String result = mJsonObject.get("data").getAsJsonArray().get(0).toString();
                            HttpUserInfo mHttpUserInfo = JsonUtils.deserialize(result, HttpUserInfo.class);
                            mLoginDbModel.saveUser(mHttpUserInfo);
                            SharePrefUtil.saveLong(ct, "userId", (long) mHttpUserInfo.user_id);
                            return mIpartApi.IpartGetHongBao(HongBaoPage, HongBaoRoll, only_valid).subscribeOn(Schedulers.io());
                        } else {
                            return Observable.error(new RuntimeException(mJsonObject.toString()));
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
                                    mILoginView.loginSuccess("登陆成功");
                                    SharePrefUtil.saveBoolean(ct,"LoginSuccess",true);
                                } else {
                                    mILoginView.mToast(mJsonObject.toString());
                                }
                                Log.e("lx", mJsonObject.toString());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mILoginView.showLoadFailMsg(throwable.getMessage(), (Exception) throwable);
                            }
                        });

    }
}
