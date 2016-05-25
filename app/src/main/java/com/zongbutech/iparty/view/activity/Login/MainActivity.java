package com.zongbutech.iparty.view.activity.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.utils.db.SharePrefUtil;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.login_VideoView)
    VideoView login_VideoView;


    @Bind(R.id.NoLoginGoHome)
    Button NoLoginGoHome;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.register)
    Button register;

    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        instance = this;
        if (SharePrefUtil.getBoolean(ct, "LoginSuccess", false)) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }

        //VideoView控制视频播放的功能相对较少，具体而言，它只有start和pause方法。为了提供更多的控制，
        login_VideoView.setMediaController(new MediaController(this));
        Uri mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.iparty);
        login_VideoView.setVideoURI(mUri);
        NoLoginGoHome.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        login_VideoView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                login_VideoView.pause();
            }
        }, 1000);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.NoLoginGoHome:

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
                break;
            case R.id.login:

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.register:

                break;

        }
    }
}
