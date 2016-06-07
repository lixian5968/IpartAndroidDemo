package com.zongbutech.iparty.view.fragment.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.ImageLoaderUtils;
import com.loveiparty.http.db.User;
import com.zhy.lxroundimage.LxRoundImageView;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.presenter.HomeSettingPresenter;
import com.zongbutech.iparty.utils.db.SharePrefUtil;
import com.zongbutech.iparty.view.IView.IHomeSettingView;
import com.zongbutech.iparty.view.activity.Setting.CouponsSetiingActivity;
import com.zongbutech.iparty.view.activity.Setting.EticketsSetiingActivity;
import com.zongbutech.iparty.view.activity.Setting.FavoritesSetiingActivity;
import com.zongbutech.iparty.view.activity.Setting.OrdersSetiingActivity;
import com.zongbutech.iparty.view.activity.Setting.SettingSetiingActivity;
import com.zongbutech.iparty.view.activity.Setting.UserItemSetiingActivity;
import com.zongbutech.iparty.view.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lixian on 2016/3/9.
 */
public class HomeSettingFragment extends BaseFragment implements IHomeSettingView {
    public static String TAG = HomeSettingFragment.class.getSimpleName();
    private static HomeSettingFragment fragment;
    @Bind(R.id.UserIcon)
    LxRoundImageView UserIcon;
    @Bind(R.id.UserName)
    TextView UserName;

    @Bind(R.id.UserMoreMessage)
    LinearLayout UserMoreMessage;


    HomeSettingPresenter presenter;
    boolean login;
    User mUser;

    @Override
    public View getResourcesView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        presenter = new HomeSettingPresenter(ct, this);
        login = SharePrefUtil.getBoolean(ct, "LoginSuccess", false);
        if (login) {
            mUser = presenter.getUser();
            if (mUser != null) {
                UserName.setText(mUser.getNickname());
                String url = "";
                if (mUser.getAvatar().startsWith("http")) {
                    url = mUser.getAvatar();
                } else {
                    url = Constants.BaseImageUrl + mUser.getAvatar();
                }
                ImageLoaderUtils.display(ct, UserIcon, url);
            }
        }


        return view;
    }


    @OnClick(R.id.UserMoreMessage)
    void UserMoreMessage(View view) {
        Intent it = new Intent(ct, UserItemSetiingActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("User", mUser);
        it.putExtras(bd);
        startActivity(it);
    }


    @OnClick(R.id.mEtickets)
    void mEtickets(View view) {
        Intent it = new Intent(ct, EticketsSetiingActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("User", mUser);
        it.putExtras(bd);
        startActivity(it);
    }

    @OnClick(R.id.mFavorites)
    void mFavorites(View view) {
        Intent it = new Intent(ct, FavoritesSetiingActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("User", mUser);
        it.putExtras(bd);
        startActivity(it);
    }

    @OnClick(R.id.mOrders)
    void mOrders(View view) {
        Intent it = new Intent(ct, OrdersSetiingActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("User", mUser);
        it.putExtras(bd);
        startActivity(it);
    }

    @OnClick(R.id.mCoupons)
    void mCoupons(View view) {
        Intent it = new Intent(ct, CouponsSetiingActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("User", mUser);
        it.putExtras(bd);
        startActivity(it);
    }



    @OnClick(R.id.mSetting)
    void mSetting(View view) {
        Intent it = new Intent(ct, SettingSetiingActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("User", mUser);
        it.putExtras(bd);
        startActivity(it);
    }

    @Override
    public void afterOncreate(Bundle savedInstanceState) {
        Log.e(TAG, "afterOncreate");
    }

    public static HomeSettingFragment newInstance() {
        fragment = new HomeSettingFragment();
        return fragment;
    }
}
