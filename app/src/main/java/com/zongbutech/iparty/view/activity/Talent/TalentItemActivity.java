package com.zongbutech.iparty.view.activity.Talent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.ImageLoaderUtils;
import com.loveiparty.http.db.Party;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.activity.Party.UserPushParty;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TalentItemActivity extends BaseActivity {
    Party mParty;

    @Bind(R.id.mTextView)
    TextView mTextView;

    @Bind(R.id.talent_bg)
    ImageView talent_bg;

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

                            try {
                                JsonObject mDate = jsonObject.get("data").getAsJsonArray().get(0).getAsJsonObject();
                                String url = Constants.BaseImageUrl + mDate.get("talent_photos").getAsJsonArray()
                                        .get(mDate.get("talent_photos").getAsJsonArray().size() - 1)
                                        .getAsJsonObject().get("url").getAsString();
                                ImageLoaderUtils.display(ct, talent_bg, url);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("", "");
                        }
                    }
                });

    }


    @OnClick(R.id.user_push_party)
    void user_push_party(View v) {

        Intent it = new Intent(ct, UserPushParty.class);
        it.putExtra("TalentId", mParty.getPublisher_id());
        startActivity(it);

    }

}
