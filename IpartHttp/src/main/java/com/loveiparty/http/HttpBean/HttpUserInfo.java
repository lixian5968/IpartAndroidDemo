package com.loveiparty.http.HttpBean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lixian on 2016/5/25.
 */
public class HttpUserInfo extends  BaseHttpBean implements Serializable {

    public String avatar;
    public int has_change_sex;
    public Date last_update_time;
    public String nickname;
    public String open_id;
    public Date register_time;
    public int sex;
    public String telephone;
    public int user_id;
    public int user_type;
}
