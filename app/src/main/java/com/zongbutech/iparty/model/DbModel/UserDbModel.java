package com.zongbutech.iparty.model.DbModel;

import android.content.Context;

import com.loveiparty.http.db.User;
import com.loveiparty.http.db.UserDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by lixian on 2016/5/25.
 */
public class UserDbModel extends BaseDbModel {
    UserDao mUserDao;

    public UserDbModel(Context ct) {
        super(ct);
        mUserDao = daoSession.getUserDao();
    }

    public void saveUser(User data) {
        mUserDao.insertOrReplace(data);
    }


    public User getUserById(int userCode) {
        try {
            QueryBuilder builder = mUserDao.queryBuilder();
            List<User> mList =   builder.where(UserDao.Properties.User_id.eq((long)userCode)).list();
            return mList.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
