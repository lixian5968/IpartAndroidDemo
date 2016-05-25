package com.zongbutech.iparty.view.activity.Login;

import android.os.Bundle;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.fragment.LoginFragment;

public class LoginActivity extends LoginBaseActivity {
    LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginFragment = LoginFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_login_fragment, mLoginFragment).commit();


    }


    @Override
    public void login(String username, String password) {
        mLoginFragment.login(username,password);
    }
}
