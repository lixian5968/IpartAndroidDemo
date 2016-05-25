package com.zongbutech.iparty.view.activity.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ResertPasswordActivity extends BaseActivity {

    private EditText PhoneNumber, ResertNumber, newPassword;
    private Button sendNumber, ResertButton;
    private TextView mTextView;

    String PhoneNumberString;
    String newPasswordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resert_password);

        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        ResertNumber = (EditText) findViewById(R.id.ResertNumber);
        newPassword = (EditText) findViewById(R.id.newPassword);

        sendNumber = (Button) findViewById(R.id.sendNumber);
        ResertButton = (Button) findViewById(R.id.ResertButton);

        mTextView = (TextView) findViewById(R.id.mTextView);


        sendNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PhoneNumberString = PhoneNumber.getText().toString();
                if (PhoneNumberString.length() == 11) {
                    mIpartApi.getPhoneCodes(PhoneNumberString).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<JsonObject>() {
                        @Override
                        public void call(JsonObject jsonObject) {
                            mTextView.setText(jsonObject.toString());
                        }
                    });
                }


            }
        });

        ResertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneNumberString = PhoneNumber.getText().toString();
                String ResertNumberString = ResertNumber.getText().toString();
                newPasswordString = newPassword.getText().toString();


                if (PhoneNumberString.length() == 11 && ResertNumberString.length() > 0 && newPasswordString.length() > 0) {
                    JsonObject object = new JsonObject();
                    object.addProperty("sms_code", ResertNumberString);
                    object.addProperty("password", newPasswordString);
                    mIpartApi.PutPhoneCodes(PhoneNumberString, object).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<JsonObject>() {
                        @Override
                        public void call(JsonObject jsonObject) {
                            mTextView.setText(jsonObject.toString());
                            if (jsonObject.get("code").getAsInt() == 0) {
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("username", PhoneNumberString);
                                returnIntent.putExtra("password", newPasswordString);
                                ResertPasswordActivity.this.setResult(RESULT_OK, returnIntent);
                                ResertPasswordActivity.this.finish();
                            }

                        }
                    });
                }


            }
        });

    }


}
