package com.zongbutech.iparty.view.activity.Party;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Message;
import com.loveiparty.http.db.Party;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PartyMessageActivity extends BaseActivity {
    Party mParty;


    @Bind(R.id.mMessage)
    TextView mMessage;

    @Bind(R.id.mEditText)
    EditText mEditText;

    @Bind(R.id.mButton)
    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_message);
        ButterKnife.bind(this);
        mParty = (Party) getIntent().getSerializableExtra("mParty");
        getMessage();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditText.getText().toString().trim();
                JsonObject obj = new JsonObject();
                obj.addProperty("content", message);
                if (message != null && message.length() > 0) {
                    mIpartApi.postMessage(mParty.getParty_id(), obj)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<JsonObject>() {
                                @Override
                                public void call(JsonObject jsonObject) {
                                    int code = jsonObject.get("code").getAsInt();
                                    if (code == 0) {
                                        mToast("发送成功");
                                        finish();
                                        if (jsonObject.get("data") != null) {
                                            Log.e("lx", jsonObject.toString());
                                        }
                                    } else {
                                        Log.e("", "");
                                    }
                                }
                            });

                } else {
                    mToast("请输入信息");
                }


//                List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
//                list.add(new OkHttpUtils.Param("content", message));
//                String url = "http://www.loveiparty.com/dev/open/api/v1/messages/" + mParty.getParty_id();
//                OkHttpUtils.post(ct, url, new OkHttpUtils.ResultCallback<String>() {
//                    @Override
//                    public void onSuccess(String response) {
//                        Log.e("lx",response);
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//                        Log.e("lx",e.getMessage());
//                    }
//                }, list);

            }
        });
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
                            mMessage.setText(result.toString());
                            Log.e("", "");
                        } else {
                            Log.e("", "");
                        }
                    }
                });
    }


}
