package com.zongbutech.iparty.view.IView;


import com.loveiparty.http.db.Order;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public interface IOrdersView extends BaseView{
    void addBeans(List<Order> newsList);
}
