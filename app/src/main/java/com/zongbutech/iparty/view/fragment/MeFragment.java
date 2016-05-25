package com.zongbutech.iparty.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zongbutech.iparty.R;

/**
 * Created by lixian on 2016/3/9.
 */
public class MeFragment extends BaseFragment{
    public static String TAG = MeFragment.class.getSimpleName();
    private static MeFragment fragment;



    @Override
    public View getResourcesView(LayoutInflater inflater,ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }



    @Override
    public void afterOncreate(Bundle savedInstanceState) {
        Log.e(TAG,"afterOncreate");
    }
    public static MeFragment newInstance() {
        fragment = new MeFragment();
        return fragment;
    }
}
