package com.example;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static final int VERSION = 1;
    public static final String GREEN_DAO_CODE_PATH = "../IpartDemo/app/src/main/java";

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(VERSION, "com.zongbutech.iparty.db");

        Entity UserAsset = schema.addEntity("UserAsset");
        UserAsset.addIdProperty();
        UserAsset.addStringProperty("objectId");
        UserAsset.addStringProperty("user_id");
        UserAsset.addStringProperty("total_fee");//总费用
        UserAsset.addStringProperty("coupon_fee");//代金卷
        UserAsset.addStringProperty("trade_type");//交易类型
        UserAsset.addStringProperty("fee_state");//1->提交/预付/不可提现；10->完成/可提现
        UserAsset.addDateProperty("created_time");
        UserAsset.addDateProperty("updated_time");


        Entity Comment = schema.addEntity("Comment");
        Comment.addIdProperty();
        Comment.addStringProperty("objectId");
        Comment.addStringProperty("user_id");
        Comment.addDateProperty("created_time");
        Comment.addDateProperty("updated_time");
        Comment.addStringProperty("username");
        Comment.addStringProperty("order_id");
        Comment.addStringProperty("party_id");
        Comment.addStringProperty("stars");
        Comment.addStringProperty("content");
        Comment.addStringProperty("photos");
        Comment.addStringProperty("is_anonymous");
        Comment.addStringProperty("status");


        Entity Coupon = schema.addEntity("Coupon");
        Coupon.addIdProperty();
        Coupon.addStringProperty("objectId");
        Coupon.addStringProperty("user_id");
        Coupon.addDateProperty("created_time");
        Coupon.addDateProperty("updated_time");
        Coupon.addDateProperty("start_time");
        Coupon.addDateProperty("end_time");
        Coupon.addStringProperty("photo");
        Coupon.addStringProperty("state");
        Coupon.addStringProperty("amount");
        Coupon.addStringProperty("limited_amount");
        Coupon.addStringProperty("description");

        Entity ETicket = schema.addEntity("ETicket");
        ETicket.addIdProperty();
        ETicket.addStringProperty("objectId");
        ETicket.addStringProperty("order_id");
        ETicket.addStringProperty("user_id");
        ETicket.addStringProperty("party_id");
        ETicket.addStringProperty("qrcode");
        ETicket.addStringProperty("status");
        ETicket.addStringProperty("wechatgroup");
        ETicket.addDateProperty("created_time");
        ETicket.addDateProperty("updated_time");

        Entity MyFavorite = schema.addEntity("MyFavorite");
        MyFavorite.addIdProperty();
        MyFavorite.addStringProperty("objectId");
        MyFavorite.addStringProperty("user_id");
        MyFavorite.addStringProperty("party_id");
        MyFavorite.addDateProperty("created_time");

        //反馈
        Entity Feedback = schema.addEntity("Feedback");
        Feedback.addIdProperty();
        Feedback.addStringProperty("objectId");
        Feedback.addStringProperty("content");
        Feedback.addStringProperty("contact");
        Feedback.addStringProperty("user_id");
        Feedback.addStringProperty("user_ip");
        Feedback.addDateProperty("created_time");

//我要留言
        Entity Message = schema.addEntity("Message");
        Message.addIdProperty();
        Message.addStringProperty("objectId");
        Message.addStringProperty("user_id");
        Message.addStringProperty("party_id");
        Message.addStringProperty("content");
        Message.addDateProperty("created_time");
        Message.addStringProperty("status");
        Message.addStringProperty("user_ip");
        Message.addDateProperty("update_time");


//        推送消息
        Entity NotificationPush = schema.addEntity("NotificationPush");
        NotificationPush.addIdProperty();
        NotificationPush.addStringProperty("objectId");
        NotificationPush.addStringProperty("user_id");
        NotificationPush.addStringProperty("content");
        NotificationPush.addStringProperty("send_state");
        NotificationPush.addBooleanProperty("is_read");
        NotificationPush.addStringProperty("extras");
        NotificationPush.addDateProperty("created_time");
        NotificationPush.addDateProperty("updated_time");

//订单
        Entity Order = schema.addEntity("Order");
        Order.addIdProperty();
        Order.addStringProperty("objectId");
        Order.addStringProperty("user_id");
        Order.addStringProperty("party_id");
        Order.addIntProperty("status");
        Order.addIntProperty("refund_state");
        Order.addDateProperty("created_time");
        Order.addDateProperty("party_time");
        Order.addStringProperty("phone");
        Order.addIntProperty("sex");
        Order.addIntProperty("isshare");
        Order.addStringProperty("share_user_id");
        Order.addStringProperty("share_id");
        Order.addDateProperty("paytime");
        Order.addIntProperty("issendmsg");
        Order.addIntProperty("send_notifications");
        Order.addIntProperty("is_del");
        Order.addIntProperty("discount");
        Order.addStringProperty("out_trade_no");
        Order.addStringProperty("bonus_id");
        Order.addIntProperty("price");
        Order.addDateProperty("update_time");


        Entity Party = schema.addEntity("Party");
        Party.addIdProperty();
        Party.addStringProperty("objectId");
        Party.addStringProperty("title");
        Party.addStringProperty("only");
        Party.addIntProperty("old_price_man");
        Party.addIntProperty("old_price_woman");
        Party.addIntProperty("price_man");
        Party.addIntProperty("price_woman");
        Party.addDateProperty("end_time");
        Party.addIntProperty("favorite_num");
        Party.addIntProperty("leastnum");
        Party.addIntProperty("sold");
        Party.addStringProperty("address");
        Party.addStringProperty("addressurl");
        Party.addStringProperty("address_thumbnail");
        Party.addStringProperty("phone");
        Party.addStringProperty("description");
        Party.addStringProperty("content");
        Party.addStringProperty("note");
        Party.addStringProperty("pic_base_url");
        Party.addDateProperty("created_time");
        Party.addStringProperty("photoids");
        Party.addStringProperty("headphoto");
        Party.addStringProperty("wechatgroup");
        Party.addStringProperty("customphoto");
        Party.addIntProperty("is_delete");
        Party.addIntProperty("type_ids");
        Party.addIntProperty("limited");
        Party.addIntProperty("maximum_num");
        Party.addDateProperty("start_time");
        Party.addStringProperty("deadline_time");
        Party.addIntProperty("time_type");
        Party.addStringProperty("time_text");
        Party.addStringProperty("user_id");
        Party.addBooleanProperty("is_audited");
        Party.addBooleanProperty("is_available");
        Party.addDateProperty("update_time");
        Party.addStringProperty("publisher_avatar");
        Party.addIntProperty("publisher_id");
        Party.addStringProperty("publisher_name");
        Party.addIntProperty("publisher_star");
        Party.addStringProperty("recommend");
        Party.addIntProperty("sold_num");
        Party.addStringProperty("types");
        Party.addIntProperty("week_activity");



        Entity Talent = schema.addEntity("Talent");
        Talent.addIdProperty();
        Talent.addStringProperty("objectId");
        Talent.addStringProperty("user_id");
        Talent.addStringProperty("real_name");
        Talent.addStringProperty("avatar");
        Talent.addIntProperty("identify");
        Talent.addStringProperty("city");
        Talent.addStringProperty("province");
        Talent.addStringProperty("wechat_account");
        Talent.addStringProperty("phone");
        Talent.addStringProperty("photo_ids");
        Talent.addStringProperty("introduction");
        Talent.addStringProperty("details");
        Talent.addIntProperty("type_ids");
        Talent.addDateProperty("created_time");
        Talent.addBooleanProperty("is_audited");
        Talent.addDateProperty("update_time");

        Entity User = schema.addEntity("User");
        User.addIdProperty();
        User.addStringProperty("objectId");
        User.addStringProperty("openid");
        User.addStringProperty("nickname");
        User.addIntProperty("sex");
        User.addStringProperty("province");
        User.addStringProperty("city");
        User.addStringProperty("country");
        User.addStringProperty("headimgurl");
        User.addStringProperty("phone");
        User.addDateProperty("created_time");
        User.addIntProperty("has_change_sex");
        User.addStringProperty("access_token");
        User.addIntProperty("platform_id");
        User.addStringProperty("thirdparty_account");
        User.addIntProperty("type");
        User.addDateProperty("update_time");


        Entity UrlString = schema.addEntity("UrlString");
        UrlString.addIdProperty();
        UrlString.addStringProperty("UrlType");
        UrlString.addStringProperty("Url");

        File f = new File(GREEN_DAO_CODE_PATH);
        if (!f.exists()) {
            f.mkdirs();
        }

        new DaoGenerator().generateAll(schema, f.getAbsolutePath());
    }
}