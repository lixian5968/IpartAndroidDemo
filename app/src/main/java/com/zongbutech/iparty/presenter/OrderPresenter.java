package com.zongbutech.iparty.presenter;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.JsonUtils;
import com.loveiparty.http.db.Order;
import com.zongbutech.iparty.view.IView.IOrdersView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lixian on 2016/3/10.
 */
public class OrderPresenter extends BasePresenter {

    private static final String TAG = OrderPresenter.class.getSimpleName();
    private IOrdersView mIOrdersView;
    private Context ct;

    public OrderPresenter(Context ct, IOrdersView mIOrdersView) {
        super(ct);
        this.mIOrdersView = mIOrdersView;
        this.ct = ct;
    }


    //加载的的part类行
    public void loadBeans(int user_id, final String type, final int pageIndex) {
        if (pageIndex == 0) {
            mIOrdersView.showProgress();
        }

        mIpartApi.getUserOrders(user_id, type,pageIndex,Constants.PAZE_Roll).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<JsonObject>() {
            @Override
            public void call(JsonObject jsonObject) {
                try {
                    int code = jsonObject.get("code").getAsInt();
                    if (code == 0 && jsonObject.get("data")!=null) {
                        JsonArray result = jsonObject.get("data").getAsJsonArray();
                        List<Order> mBeans = new ArrayList<Order>();
                        for (int i = 0; i < result.size(); i++) {
                            Order mBean = JsonUtils.deserialize(result.get(i).toString(), Order.class);
                            mBeans.add(mBean);
                        }
                        mIOrdersView.hideProgress();
                        mIOrdersView.addBeans(mBeans);
                    } else {
                        mIOrdersView.hideProgress();
                        mIOrdersView.showLoadFailMsg(jsonObject.toString(), new Exception());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mIOrdersView.hideProgress();
                    mIOrdersView.showLoadFailMsg(jsonObject.toString(), new Exception());
                }
            }
        });




    }




}
