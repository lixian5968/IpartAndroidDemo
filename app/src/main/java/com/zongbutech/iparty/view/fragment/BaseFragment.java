package com.zongbutech.iparty.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lixian on 2016/2/15.
 */
public abstract class BaseFragment extends Fragment {

    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //防止 多个数据 调用同一个界面
        if (view == null) {
            view = getResourcesView(inflater,container);
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        afterOncreate(savedInstanceState);
    }

    public abstract View getResourcesView(LayoutInflater inflater,ViewGroup container );

    public abstract void afterOncreate(Bundle savedInstanceState);
}
