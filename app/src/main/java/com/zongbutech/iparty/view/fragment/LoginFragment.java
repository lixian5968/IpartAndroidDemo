package com.zongbutech.iparty.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
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

    @Bind(R.id.textInputPhone)
    TextInputLayout textInputPhone;
    @Bind(R.id.textInputPassword)
    TextInputLayout textInputPassword;



    @Bind(R.id.login)
    Button login;

    LoginPresenter mLoginPresenter;
    @Override
    public View getResourcesView(LayoutInflater inflater,ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mLoginPresenter = new LoginPresenter(ct,this);
        Phone.addTextChangedListener(new MTextWatcher(textInputPhone));
        Password.addTextChangedListener(new MTextWatcher(textInputPassword));

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

        if(phoneNumber.length()!=11){
            textInputPhone.setError("请输入正确的手机号码");
            textInputPhone.setErrorEnabled(true);
            return;
        }

        if(PasswordNumber.length()<6){
            textInputPassword.setError("请输入正确的密码");
            textInputPassword.setErrorEnabled(true);
            return;
        }


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

    class MTextWatcher implements TextWatcher {
        TextInputLayout textInputLayout;
        public MTextWatcher(TextInputLayout textInputLayout) {
            this.textInputLayout = textInputLayout;
        }
        @Override
        public void afterTextChanged(Editable arg0) {
        }
        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            textInputLayout.setErrorEnabled(false);
        }
    }


}
