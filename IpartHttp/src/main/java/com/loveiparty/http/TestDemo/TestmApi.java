
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

package com.loveiparty.http.TestDemo;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类描述:GitHubApi
 * 创建人:malin.myemail@gmail.com
 * 创建时间:16-1-24
 * 备注:https://github.com/basil2style
 */
public interface TestmApi {

    @GET("/api/v1/users/{userID}")
    Call<TestHttpUserInfo> getUser(@Path("userID") String userID, @Query("access_token") String access_token);

    @GET("/api/v1/users/{userID}")
    Observable<TestHttpUserInfo> getUserObservable(@Path("userID") String username, @Query("access_token") String access_token);

    @FormUrlEncoded
    @POST("/api/v1/users/login")
    Call<TestHttpUserInfo> Login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/users/login")
    Observable<TestHttpUserInfo> LoginObservable(@Field("username") String username, @Field("password") String password);


    @POST("/api/v1/users/login")
    Call<TestHttpUserInfo> Login(@Body TestLoginUser user);


    @PUT("/api/v1/Feeds/{FeedID}")
    Call<TestHttpFeed> PutFeed(@Path("FeedID") String FeedID, @Query("access_token") String access_token, @Body TestHttpFeed mHttpFeed);

    @PUT("/api/v1/Feeds/{FeedID}")
    @FormUrlEncoded
    Call<TestHttpFeed> PutFeed(@Path("FeedID") String FeedID, @Query("access_token") String access_token, @Field("content") String content);


    @PUT("/api/v1/Feeds/{FeedID}")
    Observable<TestHttpFeed> PutFeedObservable(@Path("FeedID") String FeedID, @Query("access_token") String access_token, @Body TestHttpFeed mHttpFeed);

    @DELETE("/api/v1/Feeds/{FeedID}")
    Observable<TestDeleteResult> DeleteFeedObservable(@Path("FeedID") String FeedID, @Query("access_token") String access_token);


}
