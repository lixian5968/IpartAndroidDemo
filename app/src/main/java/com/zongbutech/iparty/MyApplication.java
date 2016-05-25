package com.zongbutech.iparty;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.loveiparty.http.API.IpartApi;
import com.loveiparty.http.UserCookie.okhttp.CookieInterceptor;
import com.loveiparty.http.UserCookie.okhttp.HttpLoggingInterceptor;
import com.loveiparty.http.UserCookie.storage.UserStorage;
import com.loveiparty.http.service.RetrofitService;
import com.zongbutech.iparty.db.DaoMaster;
import com.zongbutech.iparty.db.DaoSession;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by sll on 2016/3/8.
 */
public class MyApplication extends Application {


    OkHttpClient mOkHttpClient;
    ClearableCookieJar cookieJar;
    IpartApi mIpartApi;

    @Override
    public void onCreate() {
        super.onCreate();

        UserStorage mUserStorage = new UserStorage(getApplicationContext());
        CookieInterceptor mCookieInterceptor = new CookieInterceptor(mUserStorage);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(20 * 1000, TimeUnit.MILLISECONDS).readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        builder.addInterceptor(mCookieInterceptor);
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        mOkHttpClient = builder.cookieJar(cookieJar).build();
        mIpartApi = RetrofitService.getInstance(mOkHttpClient).createService(IpartApi.class);
        setupDatabase();
    }

    public OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }

    public ClearableCookieJar getCookieJar() {
        return cookieJar;
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

}
