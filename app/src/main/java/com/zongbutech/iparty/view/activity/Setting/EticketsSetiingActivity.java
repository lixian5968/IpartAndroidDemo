package com.zongbutech.iparty.view.activity.Setting;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.db.User;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class EticketsSetiingActivity extends BaseActivity {


    @Bind(R.id.mTextView)
    TextView mTextView;

    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etickets_setiing);
        ButterKnife.bind(this);
        mUser = (User) getIntent().getSerializableExtra("User");


        showMyWaitDialog(ct);
        mIpartApi.getUserEtickets(1, Constants.PAZE_Roll)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<JsonObject>() {
            @Override
            public void call(JsonObject jsonObject) {
                mTextView.setText(jsonObject.toString());
                CancelWaitDialog();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mTextView.setText(throwable.getMessage());
                CancelWaitDialog();
            }
        });
    }


}
