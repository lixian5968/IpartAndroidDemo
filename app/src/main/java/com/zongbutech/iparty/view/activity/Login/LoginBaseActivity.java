package com.zongbutech.iparty.view.activity.Login;

import android.content.Intent;

import com.zongbutech.iparty.view.activity.BaseActivity;

/**
 * Created by lixian on 2016/5/25.
 */
public abstract class LoginBaseActivity extends BaseActivity {

    public static final int LoginToRegist = 1000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == LoginToRegist) {
            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");
            login(username, password);
        }
    }

    public abstract void login(String username, String password);

    public void regist() {
        Intent it = new Intent(LoginBaseActivity.this, ResertPasswordActivity.class);
        startActivityForResult(it, LoginToRegist);
    }
}
