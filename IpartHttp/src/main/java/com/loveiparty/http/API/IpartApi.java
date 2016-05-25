package com.loveiparty.http.API;

import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lixian on 2016/5/23.
 */
public interface IpartApi {

    //下面三种穿的参数样式
//    telephone=13282008515&password=900929
//    @FormUrlEncoded
//    @POST("/open/api/v1/session")
//    Observable<JsonObject> IpartLoginObservable(@Field("telephone") String username, @Field("password") String password);
//    @POST("/open/api/v1/session")
//    Observable<JsonObject> IpartLoginObservable(@Part("telephone") String username, @Part("password") String password);
//    @POST("/open/api/v1/session")
//    Observable<JsonObject> IpartLoginObservable(@Query("telephone") String username, @Query("password") String password);


    // 传递的样式{"telephone":"13282008515","password":"900929"}

    //用户登录
    @POST("/open/api/v1/session")
    Observable<JsonObject> IpartLogin(@Body JsonObject mJsonObject);

    //获取用户信息
    @GET("/open/api/v1/users/{userID}")
    Observable<JsonObject> IpartGetUserByID(@Path("userID") String username);

    //用户注销
    @DELETE("/open/api/v1/session")
    Observable<JsonObject> IpartLogout();

    //获取用户优惠卷
    @GET("/open/api/v1/coupons")
    Observable<JsonObject> IpartGetHongBao( @Query("page") int page, @Query("roll") int roll, @Query("only_valid") int only_valid);


    //修改密码 获取用户短信验证码
    @GET("/open/api/v1/users/{telephone}/reset_password")
    Observable<JsonObject> getPhoneCodes(@Path("telephone") String telephone);

    //修改密码 获取用户短信验证码
    @PUT("/open/api/v1/users/{telephone}/reset_password")
    Observable<JsonObject> PutPhoneCodes(@Path("telephone") String telephone,@Body JsonObject mJsonObject);



    //获取part的详细信息
    @GET("/open/api/v1/partylist/{partId}")
    Observable<JsonObject> getPart(@Path("partId") String partId);
//    @GET("/open/api/v1/partylist/{partId}")
//    Observable<JsonObject> getPart(@Path("partId") String partId);
//
//    http://www.loveiparty.com/open/api/v1/messages/647/1/10
}
