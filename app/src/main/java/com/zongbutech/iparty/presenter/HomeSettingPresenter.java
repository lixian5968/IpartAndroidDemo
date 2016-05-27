package com.zongbutech.iparty.presenter;

import android.content.Context;

import com.loveiparty.http.db.User;
import com.zongbutech.iparty.model.DbModel.UserDbModel;
import com.zongbutech.iparty.utils.db.SharePrefUtil;
import com.zongbutech.iparty.view.IView.IHomeSettingView;

/**
 * Created by lixian on 2016/3/10.
 */
public class HomeSettingPresenter extends BasePresenter {

    private static final String TAG = HomeSettingPresenter.class.getSimpleName();
    private IHomeSettingView mIHomeSettingView;
    private Context ct;
    UserDbModel mUserDbModel;

    public HomeSettingPresenter(Context ct, IHomeSettingView mIHomeSettingView) {
        super(ct);
        this.mIHomeSettingView = mIHomeSettingView;
        mUserDbModel = new UserDbModel(ct);
        this.ct = ct;
    }


    public User getUser() {
        int userCode = SharePrefUtil.getInt(ct, "userId", -1);
        return mUserDbModel.getUserById(userCode);
    }
}
