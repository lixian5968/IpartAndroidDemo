package com.zongbutech.iparty.view.activity.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loveiparty.http.db.User;
import com.zongbutech.iparty.MyApplication;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.activity.HomeActivity;
import com.zongbutech.iparty.view.activity.Login.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingSetiingActivity extends BaseActivity {


    @Bind(R.id.logout)
    Button logout;

    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_setiing);
        ButterKnife.bind(this);
        mUser = (User) getIntent().getSerializableExtra("User");


    }

    @OnClick(R.id.logout)
    void logout(View v) {
        ((MyApplication) ct.getApplicationContext()).Logout();
        if (HomeActivity.instance != null) {
            HomeActivity.instance.finish();
        }
        startActivity(new Intent(ct, MainActivity.class));
        finish();



    }

}
