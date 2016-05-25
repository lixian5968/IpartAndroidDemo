package com.zongbutech.iparty.view.IView;

import com.zongbutech.iparty.db.Party;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public interface IHomePartView extends BaseView{
    void addNews(List<Party> OldList, List<Party> newsList, boolean update);
}
