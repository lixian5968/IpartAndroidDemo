package com.loveiparty.http.API;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lixian on 2016/5/23.
 */
public interface IpartApi {

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
    Observable<JsonObject> IpartGetHongBao(@Query("page") int page, @Query("roll") int roll, @Query("only_valid") int only_valid);


    //修改密码 获取用户短信验证码
    @GET("/open/api/v1/users/{telephone}/reset_password")
    Observable<JsonObject> getPhoneCodes(@Path("telephone") String telephone);

    //修改密码 获取用户短信验证码
    @PUT("/open/api/v1/users/{telephone}/reset_password")
    Observable<JsonObject> PutPhoneCodes(@Path("telephone") String telephone, @Body JsonObject mJsonObject);


    //获取part的详细信息
    @GET("/open/api/v1/partylist/{partId}")
    Observable<JsonObject> getPart(@Path("partId") String partId);


    //获取part的列表
    @GET("/open/api/v1/partylist/{page}/{roll}")
    Observable<JsonObject> getParts(@Path("page") int page, @Path("roll") int roll, @Query("types") int types, @Query("joined_users") int joined_users);


    //获取Talent的列表
    @GET("/open/api/v1/users/talents/{page}/{roll}")
    Observable<JsonObject> getTalents(@Path("page") int page, @Path("roll") int roll, @Query("identify") int identify);


    //上传图片
    @Multipart
    @POST("/open/api/v1/upload/images")
    Observable<JsonObject> uploadImage(@Part("fileName") String description, @Part("file\"; filename=\"avatar.jpg\"") RequestBody imgs);


    //上传图片
    @PUT("/open/api/v1/users/{UserId}")
    Observable<JsonObject> PutUserInfors(@Path("UserId") int UserId, @Body JsonObject mJsonObject);

    //的到电子票
    @GET("/open/api/v1/orders/etickets")
    Observable<JsonObject> getUserEtickets(@Query("page") int page, @Query("roll") int roll);

    //的到感兴趣的
    @GET("/open/api/v1/favorites")
    Observable<JsonObject> getUserFavorites(@Query("page") int page, @Query("roll") int roll);

    //获取订单
    @GET("/open/api/v1/orders/users/{UserId}")
    Observable<JsonObject> getUserOrders(@Path("UserId") int UserId, @Query("types") String types, @Query("page") int page, @Query("roll") int roll);



}
