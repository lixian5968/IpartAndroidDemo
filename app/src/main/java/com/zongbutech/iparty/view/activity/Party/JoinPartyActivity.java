package com.zongbutech.iparty.view.activity.Party;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Order;
import com.loveiparty.http.db.Party;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.utils.db.SharePrefUtil;
import com.zongbutech.iparty.view.activity.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class JoinPartyActivity extends BaseActivity {
    @Bind(R.id.join_party)
    Button join_party;

    @Bind(R.id.delete_party)
    Button delete_party;

    @Bind(R.id.mTextView)
    TextView mTextView;


    Party mParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_party);
        ButterKnife.bind(this);
        mParty = (Party) getIntent().getSerializableExtra("mParty");
        getJoinParty();


    }

    long startTime;
    long endTime;
    Date endDate;
    Handler handler;
    String Orderid;

    private void getJoinParty() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date StartDate = new Date();
            String StartString = df.format(StartDate);
            Date newStartDate = df.parse(StartString);
            startTime = newStartDate.getTime() / 1000;
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(newStartDate); //你自己的数据进行类型转换
            calendar.add(calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
            endTime = (calendar.getTime().getTime() - 1) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mIpartApi.getJoinParty(mParty.getParty_id(), startTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            if(jsonObject.get("data")==null){
                                mTextView.setVisibility(View.INVISIBLE);
                                join_party.setVisibility(View.VISIBLE);
                                delete_party.setVisibility(View.INVISIBLE);
                            }else{
                                JsonArray result = jsonObject.get("data").getAsJsonArray();
                                List<Order> mBeans = new ArrayList<Order>();
                                for (int i = 0; i < result.size(); i++) {
                                    Order mBean = JsonUtils.deserialize(result.get(i).toString(), Order.class);
                                    mBeans.add(mBean);
                                }
                                int count = Check(mBeans);
                                if (count == -1) {
                                    mTextView.setVisibility(View.INVISIBLE);
                                    join_party.setVisibility(View.VISIBLE);
                                    delete_party.setVisibility(View.INVISIBLE);
                                } else {
                                    join_party.setVisibility(View.INVISIBLE);
                                    mTextView.setVisibility(View.VISIBLE);
                                    delete_party.setVisibility(View.VISIBLE);
                                    endDate = mBeans.get(count).getParty_time();
                                    Orderid = mBeans.get(count).getOrder_id();
                                    handler = new Handler();
                                    sendTime();
                                }
                            }
                            Log.e("", "");
                        } else {
                            Log.e("", "");
                        }
                    }
                });
    }

    private int Check(List<Order> mBeans) {
        int user_id = SharePrefUtil.getInt(ct, "userId", -1);
        for (int i = 0; i < mBeans.size(); i++) {
            if (user_id == mBeans.get(i).getUser_id()) {
                return i;
            }
        }
        return -1;
    }

    private void sendTime() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Date newDate = new Date();
                long l = endDate.getTime() - newDate.getTime();
                long day = l / (24 * 60 * 60 * 1000);
                long hour = (l / (60 * 60 * 1000) - day * 24);
                long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
                long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
                mTextView.setText("day:" + day + ",hour:" + hour + ",min:" + min + ",s:" + s);
                if (day == 0 && hour == 0 && min == 0 && s == 0) {
                    mTextView.setText("开始");
                } else {
                    sendTime();
                }
            }
        }, 1000);
    }


    @OnClick(R.id.join_party)
    void joinPart(View v) {


        JsonObject object = new JsonObject();
        object.addProperty("party_id", mParty.getParty_id());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date StartDate = new Date();
        String StartString = df.format(StartDate);

        object.addProperty("party_time", StartString);
        mIpartApi.JoinParty(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        int code = jsonObject.get("code").getAsInt();
                        if (code == 0) {
                            Log.e("", "");
                            getJoinParty();
                        } else {
                            Log.e("", "");
                        }
                    }
                });
    }
    @OnClick(R.id.delete_party)
    void delete_party(View v) {
        if(Orderid!=null && Orderid.length()>0){
            mIpartApi.DeleteParty(Orderid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<JsonObject>() {
                        @Override
                        public void call(JsonObject jsonObject) {
                            int code = jsonObject.get("code").getAsInt();
                            if (code == 0) {
                                Log.e("", "");
                                getJoinParty();
                            } else {
                                Log.e("", "");
                            }
                        }
                    });
        }
    }

}
