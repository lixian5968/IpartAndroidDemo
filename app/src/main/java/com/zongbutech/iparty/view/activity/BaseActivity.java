package com.zongbutech.iparty.view.activity;


import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loveiparty.http.API.IpartApi;
import com.lxview.MyDialog;
import com.zongbutech.iparty.MyApplication;
import com.zongbutech.iparty.R;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public Context ct;
    public String Tag = "BaseActivity";
    public IpartApi mIpartApi;
    public int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
            ActivityManager.RunningTaskInfo cinfo = runningTasks.get(0);
            ComponentName component = cinfo.topActivity;
            Tag = component.getShortClassName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ct = this;
        mIpartApi = ((MyApplication) ct.getApplicationContext()).getmIpartApi();


        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
    }

    public void finish(View v) {
        finish();
    }

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
            Toast.makeText(ct, s, Toast.LENGTH_SHORT).show();
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

    public void mToast(Exception e) {
        try {
            mToast(e.getMessage());
        } catch (Exception mye) {
            mye.printStackTrace();
        }
    }

}
