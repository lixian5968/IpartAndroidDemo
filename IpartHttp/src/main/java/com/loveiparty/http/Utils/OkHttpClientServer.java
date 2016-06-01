package com.loveiparty.http.Utils;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.loveiparty.http.UserCookie.okhttp.HttpLoggingInterceptor;
import com.loveiparty.http.UserCookie.storage.UserStorage;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by lixian on 2016/6/1.
 */
public class OkHttpClientServer {
    public static OkHttpClient mOkHttpClient =null;
    public static OkHttpClient getOkHttpClient(Context ct) {
        if (mOkHttpClient == null) {
            UserStorage mUserStorage = new UserStorage(ct);
//            CookieInterceptor mCookieInterceptor = new CookieInterceptor(mUserStorage);
            OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(20 * 1000, TimeUnit.MILLISECONDS).readTimeout(20 * 1000, TimeUnit.MILLISECONDS);
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
//            builder.addInterceptor(mCookieInterceptor);
            ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(ct));
            mOkHttpClient = builder.cookieJar(cookieJar).build();
        }
        return mOkHttpClient;
    }
}
