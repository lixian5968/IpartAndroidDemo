package com.zongbutech.iparty.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.beans.HomePartyBean;
import com.zongbutech.iparty.beans.JoinedUserBean;
import com.zongbutech.iparty.utils.http.ImageLoaderUtils;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class HomePartyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<HomePartyBean> mData;
    private boolean mShowFooter = true;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public HomePartyAdapter(Context context) {
        this.mContext = context;
    }

    public void setmDate(List<HomePartyBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {

            HomePartyBean Party = mData.get(position);
            if (Party == null) {
                return;
            }
            ((ItemViewHolder) holder).homepart_item_money.setText(Party.price_man / 100 + "");
            ((ItemViewHolder) holder).homepart_item_name.setText(Party.title);
            ((ItemViewHolder) holder).homepart_item_loveUserCount.setText(Party.favorite_num);
            ((ItemViewHolder) holder).homepart_item_time.setText(Party.start_time + "," + Party.end_time);

            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).homepart_item_all, Party.pic_base_url + Party.head_photo);
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).homepart_item_user, Party.pic_base_url + Party.publisher_avatar);

            if (Party.joined_users == null || Party.joined_users.size() == 0) {
                ((ItemViewHolder) holder).home_fragment_joined_users_all.setVisibility(View.GONE);
            } else {
                ((ItemViewHolder) holder).home_fragment_joined_users_all.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).home_fragment_joined_users.removeAllViews();
                for (JoinedUserBean juser : Party.joined_users) {
                    View JUser = LayoutInflater.from(mContext).inflate(R.layout.fragment_item_joinuser, null);
                    ImageView  jion_user_icon = (ImageView) JUser.findViewById(R.id.jion_user_icon);
                    TextView  jion_user_name = (TextView) JUser.findViewById(R.id.jion_user_name);
                    ImageView  jion_user_sex = (ImageView) JUser.findViewById(R.id.jion_user_sex);

                    ImageLoaderUtils.display(mContext, jion_user_icon, juser.avatar);
                    jion_user_name.setText(juser.nickname);
                    if(juser.sex == 1){

                    }else{

                    }
                    ((ItemViewHolder) holder).home_fragment_joined_users.addView(JUser);

                }


            }


        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (mData == null) {
            return begin;
        }
        return mData.size() + begin;
    }

    public HomePartyBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView homepart_item_all;
        public TextView homepart_item_money;
        public ImageView homepart_item_user;
        public TextView homepart_item_name;
        public LinearLayout homepart_item_loveUser;
        public TextView homepart_item_loveUserCount;
        public TextView homepart_item_time;
        public LinearLayout home_fragment_joined_users;
        public RelativeLayout home_fragment_joined_users_all;

        public ItemViewHolder(View v) {
            super(v);
            homepart_item_all = (ImageView) v.findViewById(R.id.homepart_item_all);
            homepart_item_money = (TextView) v.findViewById(R.id.homepart_item_money);
            homepart_item_user = (ImageView) v.findViewById(R.id.homepart_item_user);
            homepart_item_name = (TextView) v.findViewById(R.id.homepart_item_name);
            homepart_item_time = (TextView) v.findViewById(R.id.homepart_item_time);
            home_fragment_joined_users = (LinearLayout) v.findViewById(R.id.home_fragment_joined_users);
            homepart_item_loveUser = (LinearLayout) v.findViewById(R.id.homepart_item_loveUser);
            homepart_item_loveUserCount = (TextView) v.findViewById(R.id.homepart_item_loveUserCount);
            home_fragment_joined_users_all = (RelativeLayout) v.findViewById(R.id.home_fragment_joined_users_all);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }

}
