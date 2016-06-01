package com.zongbutech.iparty.view.activity.Setting;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.loveiparty.http.db.User;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.fragment.OrdersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrdersSetiingActivity extends BaseActivity {


    @Bind(R.id.mTabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.mViewPager)
    ViewPager mViewPager;
    User mUser;
    List<String> OrdersTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_setiing);
        ButterKnife.bind(this);
        mUser = (User) getIntent().getSerializableExtra("User");
        OrdersTitle = new ArrayList<>();
        OrdersTitle.add("全部");
        OrdersTitle.add("待支付");
        OrdersTitle.add("已报名");
        OrdersTitle.add("已结束");
        OrdersTitle.add("退款");

        OrdersFragmentAdaper adapter = new OrdersFragmentAdaper(getSupportFragmentManager(), OrdersTitle, mUser);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }


    public class OrdersFragmentAdaper extends FragmentPagerAdapter {

        List<String> adapterTitle;
        User mUser;

        public OrdersFragmentAdaper(FragmentManager supportFragmentManager, List<String> ordersTitle, User mUser) {
            super(supportFragmentManager);
            this.adapterTitle = ordersTitle;
            this.mUser = mUser;
        }


        @Override
        public Fragment getItem(int position) {
            OrdersFragment mOrdersFragment = new OrdersFragment();
            Bundle bd = new Bundle();
            bd.putSerializable("mUser", mUser);
            if(position==0){
                bd.putString("mtype","1,2,3,11,12,13,21,22,31,32,33,34,35");
            }
            mOrdersFragment.setArguments(bd);
            return mOrdersFragment;
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
