package com.zongbutech.iparty.view.IView;

/**
 * Created by lixian on 2016/3/31.
 */
public interface BaseView {
    void showProgress();
    void hideProgress();
    void showActivityProgress();
    void hideActivityProgress();
    void showLoadFailMsg(String msg, Exception e);
    void mToast(String s);
}
