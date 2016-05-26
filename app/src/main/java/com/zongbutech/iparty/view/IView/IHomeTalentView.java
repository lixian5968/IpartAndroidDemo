package com.zongbutech.iparty.view.IView;


import com.loveiparty.http.db.Talent;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public interface IHomeTalentView extends BaseView{
    void addNews(List<Talent> newsList);
}
