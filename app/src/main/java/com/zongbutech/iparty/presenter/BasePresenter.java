package com.zongbutech.iparty.presenter;

import android.content.Context;

import com.loveiparty.http.API.IpartApi;
import com.zongbutech.iparty.MyApplication;

/**
 * Created by lixian on 2016/3/31.
 */
public class BasePresenter {

    Context ct;
    public IpartApi mIpartApi;
    public BasePresenter(Context ct) {
        this.ct = ct;
        mIpartApi = ((MyApplication) ct.getApplicationContext()).getmIpartApi();
    }
}
