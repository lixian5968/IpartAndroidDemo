package com.zongbutech.iparty.model.Imodel;

import com.zongbutech.iparty.model.ImplModel.HomePartModel;

/**
 * Created by lixian on 2016/3/10.
 */
public interface IHomePartModel {


    void loadParts(String url, int type, HomePartModel.OnLoadNewsListListener Listener);

}
