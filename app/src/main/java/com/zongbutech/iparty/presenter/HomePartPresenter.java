package com.zongbutech.iparty.presenter;

import com.zongbutech.iparty.beans.HomePartyBean;
import com.zongbutech.iparty.model.Imodel.IHomePartModel;
import com.zongbutech.iparty.model.ImplModel.HomePartModel;
import com.zongbutech.iparty.utils.http.Urls;
import com.zongbutech.iparty.view.IView.IHomePartView;

import java.util.List;

/**
 * Created by lixian on 2016/3/10.
 */
public class HomePartPresenter implements HomePartModel.OnLoadNewsListListener {

    private static final String TAG = HomePartPresenter.class.getSimpleName();

    private IHomePartView mIHomePartView;
    private IHomePartModel mIHomePartModel;

    public HomePartPresenter(IHomePartView mIHomePartView) {
        this.mIHomePartView = mIHomePartView;
        this.mIHomePartModel = new HomePartModel();
    }


    //加载的的part类行
    public void loadParts(int type, int pageIndex) {
        String url = getUrl(type, pageIndex);
        if(pageIndex == 0) {
            mIHomePartView.showProgress();
        }
        mIHomePartModel.loadParts(url, type, this);
    }

    //pageIndex 第一个数据为0
    private String getUrl(int type, int pageIndex) {
        String url;
        if(type==0){
            url = Urls.UrlStart +"partylist/"+(pageIndex+1)+"/10?joined_users=1";
        }else{
            url = Urls.UrlStart +"partylist/"+(pageIndex+1)+"/10?types="+type+"&joined_users=1";
        }
        return url;
    }



    @Override
    public void onSuccess(List<HomePartyBean> list) {
        mIHomePartView.hideProgress();
        mIHomePartView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mIHomePartView.hideProgress();
        mIHomePartView.showLoadFailMsg();
    }

}
