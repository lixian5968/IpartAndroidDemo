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
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Talent;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.activity.Party.PartyItemActivity;
import com.zongbutech.iparty.view.activity.Party.UserPushParty;
import com.zongbutech.iparty.view.fragment.HomeActivity.HomeTalentListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TalentItemActivity extends BaseActivity {

    @Bind(R.id.mTextView)
    TextView mTextView;

    @Bind(R.id.talent_bg)
    ImageView talent_bg;

    int TalentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_item);
        ButterKnife.bind(this);

        String from = getIntent().getStringExtra("from");
        if (HomeTalentListFragment.class.getSimpleName().equals(from)) {
            Talent mTalent = (Talent) getIntent().getSerializableExtra("mTalent");
            TalentId = mTalent.getUser_id();
            setImage(mTalent);
        } else if (PartyItemActivity.class.getSimpleName().equals(from)) {
            TalentId = getIntent().getIntExtra("TalentId", -1);
            LoadTalent();
        }


    }

    private void LoadTalent() {
        mIpartApi.getUserByTalent(TalentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0 && jsonObject.get("data") != null && jsonObject.get("data").getAsJsonArray() != null && jsonObject.get("data").getAsJsonArray().size() > 0) {
                            Talent mTalent = JsonUtils.deserialize(jsonObject.get("data").getAsJsonArray().get(0).toString(), Talent.class);
                            setImage(mTalent);
                        } else {
                            Log.e("", "");
                        }
                    }
                });

    }

    private void setImage(Talent mTalent) {

        mTextView.setText(mTalent.getTalent_details());
        String url = Constants.BaseImageUrl + mTalent.talent_photos.get(mTalent.talent_photos.size() - 1).url;
        ImageLoaderUtils.display(ct, talent_bg, url);
    }


    @OnClick(R.id.user_push_party)
    void user_push_party(View v) {

        Intent it = new Intent(ct, UserPushParty.class);
        it.putExtra("TalentId", TalentId);
        startActivity(it);

    }

}
