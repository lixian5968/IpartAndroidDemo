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
    @POST("api/v1/session")
    Observable<JsonObject> IpartLogin(@Body JsonObject mJsonObject);

    //获取用户信息
    @GET("api/v1/users/{userID}")
    Observable<JsonObject> IpartGetUserByID(@Path("userID") String username);

    //用户注销
    @DELETE("api/v1/session")
    Observable<JsonObject> IpartLogout();

    //获取用户优惠卷
    @GET("api/v1/coupons")
    Observable<JsonObject> IpartGetHongBao(@Query("page") int page, @Query("roll") int roll, @Query("only_valid") int only_valid);


    //修改密码 获取用户短信验证码
    @GET("api/v1/users/{telephone}/reset_password")
    Observable<JsonObject> getPhoneCodes(@Path("telephone") String telephone);

    //修改密码 获取用户短信验证码
    @PUT("api/v1/users/{telephone}/reset_password")
    Observable<JsonObject> PutPhoneCodes(@Path("telephone") String telephone, @Body JsonObject mJsonObject);


    //获取part的详细信息
    @GET("api/v1/partylist/{partId}")
    Observable<JsonObject> getPart(@Path("partId") String partId);


    //获取part的列表
    @GET("api/v1/partylist/{page}/{roll}")
    Observable<JsonObject> getParts(@Path("page") int page, @Path("roll") int roll, @Query("types") int types, @Query("joined_users") int joined_users);

    //获取part的列表by Talente
    @GET("api/v1/users/{TalentId}/partylist/{page}/{roll}")
    Observable<JsonObject> getPartsByTalentId(@Path("TalentId") int TalentId, @Path("page") int page, @Path("roll") int roll);


    //获取Talent的列表
    @GET("api/v1/users/talents/{page}/{roll}")
    Observable<JsonObject> getTalents(@Path("page") int page, @Path("roll") int roll, @Query("identify") int identify);


    //上传图片
    @Multipart
    @POST("api/v1/upload/images")
    Observable<JsonObject> uploadImage(@Part("fileName") String description, @Part("file\"; filename=\"avatar.jpg\"") RequestBody imgs);


    //上传图片
    @PUT("api/v1/users/{UserId}")
    Observable<JsonObject> PutUserInfors(@Path("UserId") int UserId, @Body JsonObject mJsonObject);

    //的到电子票
    @GET("api/v1/orders/etickets")
    Observable<JsonObject> getUserEtickets(@Query("page") int page, @Query("roll") int roll);

    //的到感兴趣的
    @GET("api/v1/favorites")
    Observable<JsonObject> getUserFavorites(@Query("page") int page, @Query("roll") int roll);

    //获取订单
    @GET("api/v1/orders/users/{UserId}")
    Observable<JsonObject> getUserOrders(@Path("UserId") int UserId, @Query("types") String types, @Query("page") int page, @Query("roll") int roll);

    //获取Message 通过 party的id
    @GET("api/v1/messages/{PartyId}/{page}/{roll}")
    Observable<JsonObject> getMessageById(@Path("PartyId") int PartyId, @Query("page") int page, @Query("roll") int roll);

    //判断用户是否喜欢这个party
    @GET("api/v1/favorites/query_me/{PartyId}")
    Observable<JsonObject> CheckLoveParty(@Path("PartyId") int PartyId);

    //的到喜欢这个party的用户列表
    @GET("api/v1/favorites/{PartyId}")
    Observable<JsonObject> getUserByLoveParty(@Path("PartyId") int PartyId);

    //发送message
    @POST("api/v1/messages/{PartyId}")
    Observable<JsonObject> postMessage(@Path("PartyId") int PartyId, @Body JsonObject mJsonObject);

    //的到达人信息
    @GET("api/v1/users/{UserId}")
    Observable<JsonObject> getUserByTalent(@Path("UserId") int UserId);

    //的到join party的信息
    @GET("api/v1/orders/party/{PartyId}")
    Observable<JsonObject> getJoinParty(@Path("PartyId") int PartyId, @Query("start_timestamp") Long start_timestamp, @Query("end_timestamp") Long end_timestamp);

    //参加party
    @POST("api/v1/orders")
    Observable<JsonObject> JoinParty(@Body JsonObject mJsonObject);

    //删除参加party
    @DELETE("api/v1/orders/{OrderId}")
    Observable<JsonObject> DeleteParty(@Path("OrderId") String OrderId);

    //得到达人 举办的的party
    @GET("api/v1/orders/talent")
    Observable<JsonObject> talentParty(@Query("types") int types, @Query("page") int page, @Query("roll") int roll);

    //得到达人 参加的party的具体信息
    @GET("api/v1/partylist/{PartyIds}")
    Observable<JsonObject> getPartysByIds(@Path("PartyIds") String PartyId, @Query("out_dated") int out_dated);

    //得到达人 参加的party的订单信息
    @GET("api/v1/orders/party/{PartyIds}?start_timestamp=-1&end_timestamp=4102329600")
    Observable<JsonObject> getPartysOrdersByIds(@Path("PartyIds") String PartyId);

    //得到达人 参加的party的用户信息
    @GET("api/v1/users/{UserIds}")
    Observable<JsonObject> getUsersByIds(@Path("UserIds") String UserIds);
}
