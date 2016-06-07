package com.zongbutech.iparty.view.activity.Talent;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.fragment.FragmentDemo;
import com.zongbutech.iparty.view.fragment.TalentSignInActivity.HadSignInFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TalentSignInActivity extends BaseActivity {


    @Bind(R.id.mTabLayout)
    TabLayout mTabLayout;

    @Bind(R.id.mViewPager)
    ViewPager mViewPager;


    List<String> SignTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_signin);
        ButterKnife.bind(this);

        SignTitle = new ArrayList<>();
        SignTitle.add("已报名");
        SignTitle.add("待参加");
        SignTitle.add("已参加");

        SignFragmentAdaper adapter = new SignFragmentAdaper(getSupportFragmentManager(), SignTitle);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);


    }

    public class SignFragmentAdaper extends FragmentPagerAdapter {

        List<String> adapterTitle;

        public SignFragmentAdaper(FragmentManager supportFragmentManager, List<String> ordersTitle) {
            super(supportFragmentManager);
            this.adapterTitle = ordersTitle;
        }


        @Override
        public Fragment getItem(int position) {
            if(position==0){
                HadSignInFragment mHadSignInFragment = new HadSignInFragment();
                return mHadSignInFragment;
            }else{
                FragmentDemo mFragmentDemo = new FragmentDemo();
                return mFragmentDemo;
            }
        }

        @Override
        public int getCount() {
            return adapterTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return adapterTitle.get(position);
        }
    }


}
