
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 malin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.loveiparty.http.service;


import com.loveiparty.http.service.GsonCopy.GsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


public class RetrofitService {
//    private static final String API = "http://www.loveiparty.com/open/";

    private static final String API = "http://www.loveiparty.com/dev/open/";

    //http://www.loveiparty.com/dev/open/


    protected RetrofitService(OkHttpClient mOkHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                        //设置OKHttpClient为网络客户端
                .client(mOkHttpClient)
                .build();
    }

    private volatile static RetrofitService instance = null;

    private Retrofit retrofit;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitService getInstance(OkHttpClient mOkHttpClient) {
        if (instance == null) {
            synchronized (RetrofitService.class) {
                if (instance == null) {
                    instance = new RetrofitService(mOkHttpClient);
                }
            }
        }
        return instance;
    }

    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }

}
