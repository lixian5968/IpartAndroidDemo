package com.zongbutech.iparty.model.DbModel;

import android.content.Context;

import com.loveiparty.http.HttpBean.HttpUserInfo;
import com.zongbutech.iparty.db.User;
import com.zongbutech.iparty.db.UserDao;

/**
 * Created by lixian on 2016/5/25.
 */
public class UserDbModel extends BaseDbModel {
    UserDao mUserDao;

    public UserDbModel(Context ct) {
        super(ct);
        mUserDao = daoSession.getUserDao();
    }

    public void saveUser(HttpUserInfo data) {


        User user = new User();
        user.setHeadimgurl(data.avatar);
        user.setHas_change_sex(data.has_change_sex);
        user.setUpdate_time(data.last_update_time);
        user.setNickname(data.nickname);
        user.setOpenid(data.open_id);
        user.setCreated_time(data.register_time);
        user.setSex(data.sex);
        user.setPhone(data.telephone);
        user.setType(data.user_type);
        user.setId((long) data.user_id);
        mUserDao.insertOrReplace(user);
    }


}
