package com.zongbutech.iparty.view.activity;


import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public Context ct;
    public String Tag = "BaseActivity";


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

        ct=this;
    }

    public void finish(View v) {
        finish();
    }
    public void mToast(String s) {
        try {
            Toast.makeText(BaseActivity.this, s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mToast(Exception e) {
        try {
            Toast.makeText(BaseActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception mye) {
            mye.printStackTrace();
        }
    }

}
