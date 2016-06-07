package com.zongbutech.iparty.view.activity.Party;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loveiparty.http.Bean.PhotoBean;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.ImageLoaderUtils;
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Message;
import com.loveiparty.http.db.Party;
import com.loveiparty.http.db.User;
import com.lxpagercycle.LxCycleView;
import com.zhy.lxroundimage.LxRoundImageView;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.activity.Talent.TalentItemActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PartyItemActivity extends BaseActivity {


    @Bind(R.id.mLxCycleView)
    LxCycleView mLxCycleView;
    @Bind(R.id.party_name)
    TextView party_name;
    Party mParty;

    @Bind(R.id.user_message)
    TextView user_message;
    @Bind(R.id.user_love)
    TextView user_love;
    @Bind(R.id.users)
    TextView users;


    @Bind(R.id.UserName)
    TextView UserName;
    @Bind(R.id.UserIcon)
    LxRoundImageView UserIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_item);
        ButterKnife.bind(this);
        mParty = (Party) getIntent().getSerializableExtra("mParty");

        party_name.setText(mParty.getTitle());
        List<String> userPhotoUrls = new ArrayList<>();
        for (PhotoBean bean : mParty.photos) {
            String url = Constants.BaseImageUrl + bean.url;
            userPhotoUrls.add(url);
        }
        mLxCycleView.setListImage(userPhotoUrls);


        String UserIconUrl = Constants.BaseImageUrl + mParty.getPublisher_avatar();
        ImageLoaderUtils.display(ct, UserIcon, UserIconUrl);
        UserName.setText(mParty.getPublisher_name());

        getMessage();
        CheckLoveParty();
        getUserByLoveParty();


    }


    int page = 1;
    private void getMessage() {
        mIpartApi.getMessageById(mParty.getParty_id(), page, Constants.PAZE_Roll)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0 && jsonObject.get("data") != null) {
                            JsonArray result = jsonObject.get("data").getAsJsonArray();
                            List<Message> mBeans = new ArrayList<Message>();
                            for (int i = 0; i < result.size(); i++) {
                                Message mBean = JsonUtils.deserialize(result.get(i).toString(), Message.class);
                                mBeans.add(mBean);
                            }
                            user_message.setText(result.toString());
                            Log.e("", "");
                        } else {
                            Log.e("", "");
                        }
                    }
                });
    }


    private void CheckLoveParty() {
        mIpartApi.CheckLoveParty(mParty.getParty_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            JsonObject result = jsonObject.get("data").getAsJsonObject();
                            boolean check = result.get("is_favorite").getAsBoolean();
                            user_love.setText(result.toString());
                            Log.e("", "");
                        } else {
                            Log.e("", "");
                        }
                    }
                });


    }

    private void getUserByLoveParty() {
        mIpartApi.getUserByLoveParty(mParty.getParty_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            JsonArray result = jsonObject.get("data").getAsJsonArray();
                            List<User> mBeans = new ArrayList<User>();
                            for (int i = 0; i < result.size(); i++) {
                                User mBean = JsonUtils.deserialize(result.get(i).toString(), User.class);
                                mBeans.add(mBean);
                            }
                            users.setText(result.toString());
                        } else {
                            Log.e("", "");
                        }
                    }
                });
    }


    @OnClick(R.id.user_message)
    void user_message(View v) {

        Intent it = new Intent(ct, PartyMessageActivity.class);
        it.putExtra("mParty", mParty);
        startActivity(it);


    }


    @OnClick(R.id.talent_user)
    void talent_user(View v) {
        Intent it = new Intent(ct, TalentItemActivity.class);
        it.putExtra("from", PartyItemActivity.class.getSimpleName());
        it.putExtra("TalentId", mParty.getPublisher_id());
        startActivity(it);
    }

    @OnClick(R.id.join_party)
    void join_party(View v) {
        Intent it = new Intent(ct, JoinPartyActivity.class);
        it.putExtra("mParty", mParty);
        startActivity(it);
    }

}
