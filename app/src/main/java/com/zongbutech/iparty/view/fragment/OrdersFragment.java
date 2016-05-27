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

import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.db.Order;
import com.loveiparty.http.db.User;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.presenter.OrderPresenter;
import com.zongbutech.iparty.view.IView.IOrdersView;
import com.zongbutech.iparty.view.adapter.OrdersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lixian on 2016/3/9.
 */
public class OrdersFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IOrdersView {


    public static String TAG = OrdersFragment.class.getSimpleName();
    private static OrdersFragment fragment;

    @Bind(R.id.homepart_swiperefresh)
    SwipeRefreshLayout mSwipeRefreshWidget;
    @Bind(R.id.homepart_recycle)
    RecyclerView mRecyclerView;

    private OrdersAdapter mAdapter;
    private List<Order> mData;
    private OrderPresenter mOrderPresenter;
    //加载的页面
    private int pageIndex = 0;
    User mUser;
    String type;
    @Override
    public View getResourcesView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_orders_part, container, false);
        ButterKnife.bind(this, view);
        mOrderPresenter = new OrderPresenter(ct, this);
        mUser = (User) getArguments().getSerializable("mUser");
        type = getArguments().getString("mtype");

        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        //setHasFixedSize()方法用来使RecyclerView保持固定的大小，该信息被用于自身的优化。
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new OrdersAdapter(getActivity().getApplicationContext());
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

    public static OrdersFragment newInstance() {
        fragment = new OrdersFragment();
        return fragment;
    }


    private OrdersAdapter.OnItemClickListener mOnItemClickListener = new OrdersAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
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
                pageIndex += Constants.PAZE_SIZE;
                LoadBeans();
            }
        }
    };

    private void LoadBeans() {
        mOrderPresenter.loadBeans(mUser.getUser_id(),type, pageIndex);
    }


    //刷新
    @Override
    public void onRefresh() {
        pageIndex = 0;
        LoadBeans();
    }

    //显示刷新标志
    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    // 添加 View
    @Override
    public void addBeans(List<Order> newsList) {

        mAdapter.isShowFooter(true);
        if (mData == null) {
            mData = new ArrayList<Order>();
        }
        if (pageIndex == 0 && mData!=null) {
            mData.clear();
        }
        //如果没有更多数据了,则隐藏footer布局
        if (newsList != null && newsList.size() > 0) {
            mData.addAll(newsList);
            if (newsList.size() < Constants.PAZE_SIZE * 10) {
                mAdapter.isShowFooter(false);
            }
            if (pageIndex == 0) {
                mAdapter.setmDate(mData);
            } else {
                mAdapter.notifyDataSetChanged();
            }
        } else {
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }


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
            mAdapter.notifyDataSetChanged();
        }
        View view = mRecyclerView.getRootView();
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }

}
