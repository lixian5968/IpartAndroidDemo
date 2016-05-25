package com.zongbutech.iparty.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.presenter.LoginPresenter;
import com.zongbutech.iparty.view.IView.ILoginView;
import com.zongbutech.iparty.view.activity.HomeActivity;
import com.zongbutech.iparty.view.activity.Login.LoginBaseActivity;
import com.zongbutech.iparty.view.activity.Login.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lixian on 2016/3/9.
 */
public class LoginFragment extends BaseFragment implements ILoginView {


    public static String TAG = LoginFragment.class.getSimpleName();
    private static LoginFragment fragment;

    @Bind(R.id.Phone)
    EditText Phone;
    @Bind(R.id.Password)
    EditText Password;
    @Bind(R.id.login)
    Button login;

    LoginPresenter mLoginPresenter;
    @Override
    public View getResourcesView(LayoutInflater inflater,ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mLoginPresenter = new LoginPresenter(ct,this);
        return view;
    }

    @Override
    public void afterOncreate(Bundle savedInstanceState) {
        Log.e(TAG, "afterOncreate");
    }

    public static LoginFragment newInstance() {
        fragment = new LoginFragment();
        return fragment;
    }

    @OnClick(R.id.login)
    void login(View view) {


        String phoneNumber = Phone.getText().toString().trim();
        String PasswordNumber = Password.getText().toString().trim();

        if(phoneNumber.length()==11 && PasswordNumber.length()>0){
            mLoginPresenter.login(phoneNumber,PasswordNumber);
        }else {
            mToast("请输入帐号密码");
        }


    }


    @OnClick(R.id.ChangePassword)
    void ChangePassword(View view) {
        LoginBaseActivity activity = (LoginBaseActivity) getActivity();
        activity.regist();

    }


    public void login(String phoneNumber, String PasswordNumber) {
        Phone.setText(phoneNumber);
        Password.setText(PasswordNumber);
        mLoginPresenter.login(phoneNumber,PasswordNumber);
    }

    @Override
    public void loginSuccess(String s) {
        if(MainActivity.instance!=null){
            MainActivity.instance.finish();
        }
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
}
