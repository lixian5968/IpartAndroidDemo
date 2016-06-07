package com.example;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static final int VERSION = 1;
    public static final String GREEN_DAO_CODE_PATH = "../IpartDemo/IpartHttp/src/main/java";

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(VERSION, "com.loveiparty.http.db");

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
        Message.addStringProperty("avatar");
        Message.addStringProperty("content");
        Message.addDateProperty("created_at");
        Message.addStringProperty("nickname");
        Message.addIntProperty("sex");
        Message.addIntProperty("user_id");
        Message.addStringProperty("party_id");


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
        Order.setSuperclass("com.loveiparty.http.Bean.BaseHttpBean");
        Order.addIdProperty();
        Order.addStringProperty("order_id");
        Order.addDateProperty("order_time");
        Order.addStringProperty("party_id");
        Order.addDateProperty("party_time");
        Order.addIntProperty("status");
        Order.addStringProperty("user_avatar");
        Order.addIntProperty("user_id");
        Order.addStringProperty("user_nickname");
        Order.addIntProperty("user_sex");

        Order.addStringProperty("objectId");
        Order.addIntProperty("refund_state");
        Order.addDateProperty("created_time");
        Order.addStringProperty("phone");
        Order.addIntProperty("sex");
        Order.addIntProperty("isshare");
        Order.addStringProperty("share_user_id");
        Order.addStringProperty("share_id");
        Order.addStringProperty("paytime");
        Order.addStringProperty("party_title");
        Order.addIntProperty("issendmsg");
        Order.addIntProperty("send_notifications");
        Order.addIntProperty("is_del");
        Order.addIntProperty("discount");
        Order.addStringProperty("out_trade_no");
        Order.addStringProperty("bonus_id");
        Order.addIntProperty("price");
        Order.addDateProperty("update_time");


        Entity Party = schema.addEntity("Party");
        Party.setSuperclass("com.loveiparty.http.Bean.BasePartyBean");
        Party.addIdProperty();
        Party.addStringProperty("objectId");
        Party.addStringProperty("address_text");
        Party.addStringProperty("address_url");
        Party.addStringProperty("address_thumbnail");
        Party.addDateProperty("end_time");
        Party.addIntProperty("favorite_num");
        Party.addStringProperty("head_photo");
        Party.addIntProperty("in_progress");
        Party.addDateProperty("last_update_time");
        Party.addIntProperty("least_num");
        Party.addIntProperty("limited");
        Party.addIntProperty("maximum_num");
        Party.addIntProperty("old_price_man");
        Party.addIntProperty("old_price_woman");
        Party.addIntProperty("party_id");
        Party.addStringProperty("pic_base_url");
        Party.addIntProperty("price_man");
        Party.addIntProperty("price_woman");
        Party.addStringProperty("publisher_avatar");
        Party.addIntProperty("publisher_id");
        Party.addStringProperty("publisher_name");
        Party.addIntProperty("publisher_star");
        Party.addStringProperty("recommend");
        Party.addIntProperty("sold_num");
        Party.addDateProperty("start_time");
        Party.addStringProperty("time_text");
        Party.addIntProperty("time_type");
        Party.addStringProperty("title");
        Party.addStringProperty("types");
        Party.addIntProperty("week_activity");
        //没用到了
        Party.addStringProperty("only");
        Party.addIntProperty("leastnum");
        Party.addIntProperty("sold");
        Party.addStringProperty("phone");
        Party.addStringProperty("description");
        Party.addStringProperty("content");
        Party.addStringProperty("note");
        Party.addDateProperty("created_time");
        Party.addStringProperty("photoids");
        Party.addStringProperty("wechatgroup");
        Party.addStringProperty("customphoto");
        Party.addIntProperty("is_delete");
        Party.addIntProperty("type_ids");
        Party.addStringProperty("deadline_time");
        Party.addStringProperty("user_id");
        Party.addBooleanProperty("is_audited");
        Party.addBooleanProperty("is_available");
        Party.addDateProperty("update_time");






        Entity Talent = schema.addEntity("Talent");
        Talent.addIdProperty();
        Talent.setSuperclass("com.loveiparty.http.Bean.BaseTalentBean");
        Talent.addStringProperty("introduction");
        Talent.addStringProperty("talent_avatar");
        Talent.addStringProperty("talent_details");
        Talent.addStringProperty("talent_name");
        Talent.addStringProperty("talent_phone");
        Talent.addDoubleProperty("talent_stars");
        Talent.addIntProperty("talent_types");
        Talent.addIntProperty("user_id");

        Talent.addStringProperty("objectId");
        Talent.addStringProperty("real_name");
        Talent.addStringProperty("avatar");
        Talent.addIntProperty("identify");
        Talent.addStringProperty("city");
        Talent.addStringProperty("province");
        Talent.addStringProperty("wechat_account");
        Talent.addStringProperty("phone");
        Talent.addStringProperty("photo_ids");
        Talent.addStringProperty("details");
        Talent.addIntProperty("type_ids");
        Talent.addDateProperty("created_time");
        Talent.addIntProperty("is_audited");
        Talent.addDateProperty("update_time");

        Entity User = schema.addEntity("User");
        User.setSuperclass("com.loveiparty.http.Bean.BaseTalentBean");
        User.addStringProperty("introduction");
        User.addStringProperty("talent_avatar");
        User.addStringProperty("talent_details");
        User.addStringProperty("talent_name");
        User.addStringProperty("talent_phone");
        User.addStringProperty("talent_bg_img");
        User.addDoubleProperty("talent_stars");
        User.addIntProperty("talent_types");
        User.addIntProperty("user_id");
        User.addIdProperty();
        User.addStringProperty("avatar");
        User.addIntProperty("has_change_sex");
        User.addDateProperty("last_update_time");
        User.addStringProperty("nickname");
        User.addStringProperty("open_id");
        User.addDateProperty("register_time");
        User.addIntProperty("sex");
        User.addStringProperty("telephone");
        User.addIntProperty("user_type");
        User.addStringProperty("objectId");
        User.addStringProperty("province");
        User.addStringProperty("city");
        User.addStringProperty("country");
        User.addStringProperty("access_token");
        User.addIntProperty("platform_id");
        User.addStringProperty("thirdparty_account");



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
