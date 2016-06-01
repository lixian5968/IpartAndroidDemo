package com.zongbutech.iparty;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.loveiparty.http.API.IpartApi;
import com.loveiparty.http.Utils.OkHttpClientServer;
import com.loveiparty.http.db.DaoMaster;
import com.loveiparty.http.db.DaoSession;
import com.loveiparty.http.db.UserDao;
import com.loveiparty.http.service.RetrofitService;
import com.zongbutech.iparty.utils.db.SharePrefUtil;

import okhttp3.OkHttpClient;

/**
 * Created by sll on 2016/3/8.
 */
public class MyApplication extends Application {


    OkHttpClient mOkHttpClient;
    IpartApi mIpartApi;

    @Override
    public void onCreate() {
        super.onCreate();
        mOkHttpClient = OkHttpClientServer.getOkHttpClient(getApplicationContext());
        mIpartApi = RetrofitService.getInstance(mOkHttpClient).createService(IpartApi.class);
        setupDatabase();
    }


    public OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }


    public IpartApi getmIpartApi() {
        return mIpartApi;
    }

    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, "ruitougongshe", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


    public void Logout() {
        int id = SharePrefUtil.getInt(getApplicationContext(), "userId", -1);
        try {
            daoSession.getUserDao().delete(daoSession.getUserDao().queryBuilder().where(UserDao.Properties.User_id.eq(id)).list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharePrefUtil.saveBoolean(getApplicationContext(), "LoginSuccess", false);
        SharePrefUtil.saveInt(getApplicationContext(), "userId", -1);
    }
}
