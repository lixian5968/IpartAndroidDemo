package com.zongbutech.iparty.view.fragment.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.db.Talent;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.presenter.HomeTalentPresenter;
import com.zongbutech.iparty.view.IView.IHomeTalentView;
import com.zongbutech.iparty.view.activity.Talent.TalentItemActivity;
import com.zongbutech.iparty.view.adapter.HomeTalentAdapter;
import com.zongbutech.iparty.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lixian on 2016/3/9.
 */
public class HomeTalentListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IHomeTalentView {

    private HomeTalentAdapter mAdapter;
    public static String TAG = HomeTalentListFragment.class.getSimpleName();
    private static HomeTalentListFragment fragment;

    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    //选择的类型
    private int type = -1;
    //加载页面
    private int pageIndex = 0;
    HomeTalentPresenter presenter;

    @Override
    public View getResourcesView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_talent_list, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //setHasFixedSize()方法用来使RecyclerView保持固定的大小，该信息被用于自身的优化。
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HomeTalentAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        presenter = new HomeTalentPresenter(ct, this);

        onRefresh();

        return view;
    }


    private HomeTalentAdapter.OnItemClickListener mOnItemClickListener = new HomeTalentAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            Talent mTalent = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), TalentItemActivity.class);
            intent.putExtra("from", HomeTalentListFragment.class.getSimpleName());
            intent.putExtra("mTalent", mTalent);
            startActivity(intent);

        }
    };

    @Override
    public void afterOncreate(Bundle savedInstanceState) {
        Log.e(TAG, "afterOncreate");
    }

    public static HomeTalentListFragment newInstance() {
        fragment = new HomeTalentListFragment();
        return fragment;
    }

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

                presenter.loadBeans(type, pageIndex);

            }
        }
    };

    @Override
    public void onRefresh() {
        pageIndex = 0;
        presenter.loadBeans(type, pageIndex);
    }

    //显示刷新标志
    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    // 添加 View
    private List<Talent> mData;

    @Override
    public void addNews(List<Talent> newsList) {
        mAdapter.isShowFooter(true);
        if (mData == null) {
            mData = new ArrayList<Talent>();
        }
        if (pageIndex == 0 && mData != null) {
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
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
