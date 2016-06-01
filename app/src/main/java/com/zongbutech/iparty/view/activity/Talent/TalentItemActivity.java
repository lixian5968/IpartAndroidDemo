package com.zongbutech.iparty.view.activity.Talent;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.loveiparty.http.db.Party;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TalentItemActivity extends BaseActivity {
    Party mParty;

    @Bind(R.id.mTextView)
    TextView mTextView;

    @Bind(R.id.talent_bg)
    RelativeLayout talent_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_item);
        ButterKnife.bind(this);
        mParty = (Party) getIntent().getSerializableExtra("mParty");



        mIpartApi.getUserByTalent(mParty.getPublisher_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            mTextView.setText(jsonObject.toString());
                        } else {
                            Log.e("", "");
                        }
                    }
                });

    }


}
