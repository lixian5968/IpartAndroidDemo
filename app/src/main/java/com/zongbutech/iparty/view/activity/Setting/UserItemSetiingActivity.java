package com.zongbutech.iparty.view.activity.Setting;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.ImageLoaderUtils;
import com.loveiparty.http.db.User;
import com.zhy.lxroundimage.LxRoundImageView;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.CameraActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserItemSetiingActivity extends CameraActivity {


    @Bind(R.id.myIconAll)
    RelativeLayout myIconAll;
    @Bind(R.id.myIcon)
    LxRoundImageView myIcon;
    @Bind(R.id.mySex)
    TextView mySex;


    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_item_setiing);
        ButterKnife.bind(this);
        mUser = (User) getIntent().getSerializableExtra("User");


        if (mUser != null) {
            String url = "";
            if (mUser.getAvatar().startsWith("http")) {
                url = mUser.getAvatar();
            } else {
                url = Constants.BaseImageUrl + mUser.getAvatar();
            }
            ImageLoaderUtils.display(ct, myIcon, url);
            mySex.setText(mUser.getSex() + "");
        }
    }

    String url ="";

    @Override
    public void setImage(String originalUri, Bitmap resizeBmp) {
        myIcon.setImageBitmap(resizeBmp);
        Log.e("UserItemSetiing", originalUri);
        File file = new File(originalUri);
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/jpg"), file);
        mIpartApi.uploadImage(file.getName(), requestBody1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            url = jsonObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();
                        }
                        Log.e("lx", jsonObject.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("lx", "");
                    }
                });
    }

    @OnClick(R.id.myIconAll)
    void myIconAll(View view) {
        showImageDialog();
    }

    @OnClick(R.id.push_userItem)
    void push_userItem(View view) {
        if (mUser != null && url!=null && url.length() > 0) {
            JsonObject object = new JsonObject();
            Log.e("lx", url);
            if (url!=null && url.length() > 0) {
//                object.addProperty("avatar", "/upload/images/2016-05-27/party_5747fb28b6b2b.jpg");
                object.addProperty("avatar", url);
            }
            mIpartApi.PutUserInfors(mUser.getUser_id(), object)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<JsonObject>() {
                        @Override
                        public void call(JsonObject jsonObject) {
                            int code = jsonObject.get("code").getAsInt();
                            if (code == 0) {

                            }
                            Log.e("lx", jsonObject.toString());
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e("lx", "");
                        }
                    });
        }


    }


}
