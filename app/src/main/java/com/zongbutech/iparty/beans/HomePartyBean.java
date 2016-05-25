package com.zongbutech.iparty.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */

public class HomePartyBean implements Serializable {
    public String address_text;
    public String address_thumbnail;
    public String address_url;
    public String deadline_time;
    public Date end_time;
    public int favorite_num;
    public String head_photo;
    public String in_progress;
    public List<JoinedUserBean> joined_users;
    public Date last_update_time;
    public int least_num;
    public int limited;
    public int maximum_num;
    public int old_price_man;
    public int old_price_woman;
    public int party_id;
    public List<PhotoBean> photos;
    public String pic_base_url;
    public int price_man;
    public int price_woman;
    public String publisher_avatar;
    public int publisher_id;
    public String publisher_name;
    public int publisher_star;
    public String recommend;
    public int sold_num;
    public Date start_time;
    public String time_text;
    public int time_type;
    public String title;
    public String types;
    public int week_activity;

}