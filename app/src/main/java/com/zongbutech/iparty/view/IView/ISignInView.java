package com.zongbutech.iparty.view.IView;


import com.loveiparty.http.db.Order;
import com.loveiparty.http.db.Party;
import com.loveiparty.http.db.User;
import com.zongbutech.iparty.beans.TalentOrdersBean;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public interface ISignInView extends BaseView{
    void addBeans(List<TalentOrdersBean> mTalentOrdersBeans, List<Party> mPartys, List<Order> mOrders, List<User> mUsers);
}
