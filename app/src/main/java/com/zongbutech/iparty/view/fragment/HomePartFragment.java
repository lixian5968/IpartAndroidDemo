package com.zongbutech.iparty.view.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loveiparty.http.Utils.Urls;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.db.Party;
import com.zongbutech.iparty.presenter.HomePartPresenter;
import com.zongbutech.iparty.view.IView.IHomePartView;
import com.zongbutech.iparty.view.adapter.HomePartyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lixian on 2016/3/9.
 */
public class HomePartFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IHomePartView {


    public static String TAG = HomePartFragment.class.getSimpleName();
    private static HomePartFragment fragment;

    @Bind(R.id.homepart_swiperefresh)
    SwipeRefreshLayout mSwipeRefreshWidget;
    @Bind(R.id.homepart_recycle)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private HomePartyAdapter mAdapter;
    private List<Party> mData;
    private HomePartPresenter mHomePartPresenter;
    //选择的项目
    private int type;
    //加载的页面
    private int pageIndex = 0;

    @Override
    public View getResourcesView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_home_part, container, false);
        ButterKnife.bind(this, view);
        mHomePartPresenter = new HomePartPresenter(ct, this);
        type = getArguments().getInt("select");

        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        //setHasFixedSize()方法用来使RecyclerView保持固定的大小，该信息被用于自身的优化。
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HomePartyAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();


        return view;
    }

    @Override
    public void afterOncreate(Bundle savedInstanceState) {
        Log.e(TAG, "afterOncreate");
    }

    public static HomePartFragment newInstance() {
        fragment = new HomePartFragment();
        return fragment;
    }


    private HomePartyAdapter.OnItemClickListener mOnItemClickListener = new HomePartyAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
//            HomePartyBean PartyBean = mAdapter.getItem(position);
//            ((BaseActivity) getActivity()).mToast(PartyBean.title);
//            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
//            intent.putExtra("news", news);
//
//            View transitionView = view.findViewById(R.id.ivNews);
//            ActivityOptionsCompat options =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                            transitionView, getString(R.string.transition_news_img));
//
//            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    };

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                pageIndex += Urls.PAZE_SIZE;
                mHomePartPresenter.loadParts(type, pageIndex);
            }
        }
    };


    //刷新
    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (mData != null) {
            mData.clear();
        }
        mHomePartPresenter.loadParts(type, pageIndex);
    }

    //显示刷新标志
    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    // 添加 View
    @Override
    public void addNews(List<Party> OldList, List<Party> newsList, boolean update) {

//        mAdapter.isShowFooter(true);
        if (mData == null) {
            mData = new ArrayList<Party>();
        }
        //如果没有更多数据了,则隐藏footer布局
        if (newsList != null && newsList.size() > 0) {
            if (update && OldList!=null && OldList.size()>0) {
                for (Party updateBean : OldList) {
                    mData.remove(updateBean);
                }
            }
            mData.addAll(newsList);
            if (newsList.size() < Urls.PAZE_SIZE * 10) {
//                mAdapter.isShowFooter(false);
            }
            if (pageIndex == 0) {
                mAdapter.setmDate(mData);
            } else {
                mAdapter.notifyDataSetChanged();
            }
        } else {
//            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }



//        if (newsList == null || newsList.size() == 0) {
//            mAdapter.isShowFooter(false);
//            mAdapter.notifyDataSetChanged();
//            return;
//        }
//        mData.addAll(newsList);
//        if (pageIndex == 0) {
//            mAdapter.setmDate(mData);
//        } else {
//            mAdapter.notifyDataSetChanged();
//        }


    }

    //隐藏
    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    //显示错误信息
    @Override
    public void showLoadFailMsg(String msg, Exception e) {
        if (pageIndex == 0) {
//            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view = mRecyclerView.getRootView();
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }

}
