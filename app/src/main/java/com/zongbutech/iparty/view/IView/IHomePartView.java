package com.zongbutech.iparty.view.IView;


import com.loveiparty.http.db.Party;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public interface IHomePartView extends BaseView{
    void addNews( List<Party> newsList);
}
