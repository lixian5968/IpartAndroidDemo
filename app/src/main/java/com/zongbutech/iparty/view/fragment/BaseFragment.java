package com.zongbutech.iparty.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loveiparty.http.API.IpartApi;
import com.lxview.MyDialog;
import com.zongbutech.iparty.MyApplication;
import com.zongbutech.iparty.R;

/**
 * Created by lixian on 2016/2/15.
 */
public abstract class BaseFragment extends Fragment {

    public   LinearLayoutManager mLayoutManager;

    protected View view;
    public Context ct;
    public IpartApi mIpartApi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ct = getActivity();
        mIpartApi = ((MyApplication) ct.getApplicationContext()).getmIpartApi();
        //防止 多个数据 调用同一个界面
        if (view == null) {
            view = getResourcesView(inflater, container);
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        afterOncreate(savedInstanceState);
    }

    public abstract View getResourcesView(LayoutInflater inflater, ViewGroup container);

    public abstract void afterOncreate(Bundle savedInstanceState);





    public void showLoadFailMsg(String msg, Exception e) {
        if (e == null && msg == null) {
            mToast("发生未知错误");
        } else if (msg != null && e != null) {
            mToast(msg + ",e：" + e.getMessage());
            Log.e("showLoadFailMsg", msg + ",e：" + e.getMessage());
        } else if (msg == null && e != null) {
            mToast(",e：" + e.getMessage());
            Log.e("showLoadFailMsg", e.getMessage());
        } else if (msg != null && e == null) {
            mToast(msg);
            Log.e("showLoadFailMsg", msg);
        }
        CancelWaitDialog();
    }

    public void mToast(String s) {
        try {
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 等待对话框
    public MyDialog MyWaitDialog;

    public void showMyWaitDialog(final Context ct) {
        if (ct == null) {
            return;
        }
        View view = View.inflate(ct, R.layout.wait_dialog, null);
        if (MyWaitDialog == null) {
            MyWaitDialog = new MyDialog(ct, 0, 0, view, R.style.dialog);
        }
        MyWaitDialog.setCancelable(false);
        MyWaitDialog.show();
    }

    //关闭等待对话框
    public void CancelWaitDialog() {
        if (MyWaitDialog != null && MyWaitDialog.isShowing()) {
            MyWaitDialog.dismiss();
        }
    }


    public void showActivityProgress() {
        showMyWaitDialog(ct);
    }

    public void hideActivityProgress() {
        CancelWaitDialog();
    }


    public void showProgress() {

    }

    public void hideProgress() {

    }


}
