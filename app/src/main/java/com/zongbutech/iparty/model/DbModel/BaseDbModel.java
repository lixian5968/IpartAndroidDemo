package com.zongbutech.iparty.model.DbModel;

import android.content.Context;

import com.zongbutech.iparty.MyApplication;
import com.zongbutech.iparty.db.DaoSession;

/**
 * Created by lixian on 2016/5/25.
 */
public class BaseDbModel {
    public  Context ct;
    public  DaoSession daoSession;
    public BaseDbModel(Context ct) {
        this.ct = ct;
        daoSession = ((MyApplication) ct.getApplicationContext()).getDaoSession();
    }
}
