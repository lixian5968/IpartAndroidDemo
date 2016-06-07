package com.zongbutech.iparty.view.fragment.HomeActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.utils.partBean.ClientMaps;
import com.zongbutech.iparty.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lixian on 2016/3/9.
 */
public class HomeFragment extends BaseFragment {


    @Bind(R.id.home_tablayout)
    TabLayout home_tablayout;
    @Bind(R.id.home_viewpager)
    ViewPager home_viewpager;
    List<String> AdapterTitle;
    public static String TAG = HomeFragment.class.getSimpleName();
    private static HomeFragment fragment;


    @Override
    public View getResourcesView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        if (AdapterTitle == null) {
            AdapterTitle = new ArrayList<>();
//            for (Map.Entry<String, Integer> entry : ClientMaps.partTypeToName.entrySet()) {
//                AdapterTitle.add( entry.getKey());
//            }

            AdapterTitle.add("全部");
            AdapterTitle.add("夜生活");
            AdapterTitle.add("派对");
            AdapterTitle.add("特色体验");
            AdapterTitle.add("电影");
            AdapterTitle.add("运动");
            AdapterTitle.add("美食");
            AdapterTitle.add("音乐");
            AdapterTitle.add("旅游");
            AdapterTitle.add("高端生活");
            AdapterTitle.add("其它");

        }

        MainHomeFragmentAdaper adapter = new MainHomeFragmentAdaper(getChildFragmentManager(), AdapterTitle);
        home_viewpager.setAdapter(adapter);
        home_viewpager.setOffscreenPageLimit(3);
        home_tablayout.setupWithViewPager(home_viewpager);
        home_tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        return view;
    }


    @Override
    public void afterOncreate(Bundle savedInstanceState) {


    }

    public static HomeFragment newInstance() {
        Log.e(TAG, "newInstance");
//        Bundle args = new Bundle();
//        args.putSerializable("adapterTitle", (Serializable) adapterTitle);
        fragment = new HomeFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    public class MainHomeFragmentAdaper extends FragmentPagerAdapter {

        List<String> adapterTitle;

        public MainHomeFragmentAdaper(FragmentManager childFragmentManager, List<String> adapterTitle) {
            super(childFragmentManager);
            this.adapterTitle = adapterTitle;
        }

        @Override
        public Fragment getItem(int position) {
            HomePartFragment mHomePartFragment = new HomePartFragment();
            Bundle bd = new Bundle();
            bd.putInt("select", ClientMaps.partTypeToName.get(adapterTitle.get(position)));
            bd.putString("from",HomeFragment.class.getSimpleName());
            mHomePartFragment.setArguments(bd);
            return mHomePartFragment;
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
