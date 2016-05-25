package com.zongbutech.iparty.model.ImplModel;

import com.google.gson.Gson;
import com.zongbutech.iparty.beans.HomePartyBean;
import com.zongbutech.iparty.beans.HomePartysBean;
import com.zongbutech.iparty.model.Imodel.IHomePartModel;
import com.zongbutech.iparty.utils.http.OkHttpUtils;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public class HomePartModel implements IHomePartModel {


    @Override
    public void loadParts(String url, final int type, final OnLoadNewsListListener Listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                HomePartysBean bean = gson.fromJson(response,HomePartysBean.class);
                if(bean.code<0){
                    Listener.onFailure("load news list failure.",new RuntimeException("bean.getCode()<0"));
                }else{
                    Listener.onSuccess(bean.data);
                }
            }

            @Override
            public void onFailure(Exception e) {
                Listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }



    public interface OnLoadNewsListListener {
        void onSuccess(List<HomePartyBean> list);
        void onFailure(String msg, Exception e);
    }
}
