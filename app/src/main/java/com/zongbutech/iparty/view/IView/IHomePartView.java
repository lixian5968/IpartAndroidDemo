package com.zongbutech.iparty.view.IView;

import com.zongbutech.iparty.beans.HomePartyBean;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public interface IHomePartView {

    void showProgress();

    void addNews(List<HomePartyBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
