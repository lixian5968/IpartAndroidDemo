package com.zongbutech.iparty.model.DbModel;

import android.content.Context;

import com.loveiparty.http.db.DaoSession;
import com.zongbutech.iparty.MyApplication;

/**
 * Created by lixian on 2016/5/25.
 */
public class BaseDbModel {
    public  Context ct;
    public DaoSession daoSession;
    public BaseDbModel(Context ct) {
        this.ct = ct;
        daoSession = ((MyApplication) ct.getApplicationContext()).getDaoSession();
    }
}
