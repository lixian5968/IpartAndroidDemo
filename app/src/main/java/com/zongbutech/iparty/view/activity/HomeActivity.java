package com.zongbutech.iparty.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loveiparty.http.db.User;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.model.DbModel.UserDbModel;
import com.zongbutech.iparty.utils.db.SharePrefUtil;
import com.zongbutech.iparty.view.fragment.HomeActivity.HomeFragment;
import com.zongbutech.iparty.view.fragment.HomeActivity.HomeSettingFragment;
import com.zongbutech.iparty.view.fragment.HomeActivity.HomeTalentItemFragment;
import com.zongbutech.iparty.view.fragment.HomeActivity.HomeTalentListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.main_home_bt_all)
    LinearLayout main_home_bt_all;
    @Bind(R.id.main_home_bt_image)
    CheckBox main_home_bt_image;
    @Bind(R.id.main_home_bt_text)
    TextView main_home_bt_text;

    @Bind(R.id.main_daren_bt_all)
    LinearLayout main_daren_bt_all;
    @Bind(R.id.main_daren_bt_image)
    CheckBox main_daren_bt_image;
    @Bind(R.id.main_daren_bt_text)
    TextView main_daren_bt_text;

    @Bind(R.id.main_me_bt_all)
    LinearLayout main_me_bt_all;
    @Bind(R.id.main_me_bt_image)
    CheckBox main_me_bt_image;
    @Bind(R.id.main_me_bt_text)
    TextView main_me_bt_text;


    CheckBox[] mCheckBoxs;
    TextView[] mTextViews;

    HomeFragment mHomeFragment = null;
    HomeTalentListFragment mTalentListFragment = null;
    HomeTalentItemFragment mTalentItemFragment = null;
    HomeSettingFragment mMeFragment = null;

    //当前界面的Fragment
    Fragment mContent = null;

    public static HomeActivity instance = null;
    int user_type =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        instance = this;


        UserDbModel dbModel = new UserDbModel(ct);
        int userId = SharePrefUtil.getInt(ct, "userId", -1);
        User mUser = dbModel.getUserById(userId);
        if(mUser!=null){
            user_type = mUser.getUser_type();
        }


        if (savedInstanceState == null) {
            mHomeFragment = HomeFragment.newInstance();
            mTalentListFragment = HomeTalentListFragment.newInstance();
            mTalentItemFragment = HomeTalentItemFragment.newInstance();
            mMeFragment = HomeSettingFragment.newInstance();
            mContent = mHomeFragment;
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, mHomeFragment).commit();
        }

        main_home_bt_all.setOnClickListener(this);
        main_daren_bt_all.setOnClickListener(this);
        main_me_bt_all.setOnClickListener(this);
        mCheckBoxs = new CheckBox[]{main_home_bt_image, main_daren_bt_image, main_me_bt_image};
        mTextViews = new TextView[]{main_home_bt_text, main_daren_bt_text, main_me_bt_text};


        main_home_bt_all.performClick();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_home_bt_all:
                SetColorAndImage(0);
                switchContent(mHomeFragment);
                break;

            case R.id.main_daren_bt_all:
                SetColorAndImage(1);

                if (user_type == 0) {
                    switchContent(mTalentListFragment);
                } else if (user_type == 1) {
                    switchContent(mTalentItemFragment);
                }


                break;


            case R.id.main_me_bt_all:
                SetColorAndImage(2);
                switchContent(mMeFragment);

                break;


        }
    }


    /**
     * 修改显示的内容 不会重新加载
     **/
    public void switchContent(Fragment to) {

        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.home_fragment, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }


    }


    //设置选择的图片文字 的颜色
    private void SetColorAndImage(int select) {

        for (int i = 0; i < mCheckBoxs.length; i++) {
            if (i == select) {
                mCheckBoxs[i].setChecked(true);
                mTextViews[i].setTextColor(ct.getResources().getColor(R.color.red));
            } else {
                mCheckBoxs[i].setChecked(false);
                mTextViews[i].setTextColor(ct.getResources().getColor(R.color.white));
            }
        }


    }
}
