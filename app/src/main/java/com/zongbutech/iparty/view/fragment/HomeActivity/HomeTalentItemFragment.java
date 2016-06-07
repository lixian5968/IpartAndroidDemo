package com.zongbutech.iparty.view.fragment.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loveiparty.http.Utils.Constants;
import com.loveiparty.http.Utils.ImageLoaderUtils;
import com.loveiparty.http.db.User;
import com.zhy.lxroundimage.LxRoundImageView;
import com.zongbutech.iparty.R;
import com.zongbutech.iparty.model.DbModel.UserDbModel;
import com.zongbutech.iparty.utils.db.SharePrefUtil;
import com.zongbutech.iparty.view.activity.Talent.TalentSignInActivity;
import com.zongbutech.iparty.view.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lixian on 2016/3/9.
 */
public class HomeTalentItemFragment extends BaseFragment {


    @Bind(R.id.UserIcon)
    LxRoundImageView UserIcon;

    @Bind(R.id.mTextView)
    TextView mTextView;

    @Bind(R.id.talent_item_bg)
    ImageView talent_item_bg;


    @Override
    public View getResourcesView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_talent_item, container, false);
        ButterKnife.bind(this, view);


        UserDbModel dbModel = new UserDbModel(ct);
        int userId = SharePrefUtil.getInt(ct, "userId", -1);
        User mUser = dbModel.getUserById(userId);

        String url = Constants.BaseImageUrl + mUser.getTalent_avatar();
        ImageLoaderUtils.display(ct, UserIcon, url);
        mTextView.setText(mUser.getTalent_name());
        String BgUrl = Constants.BaseImageUrl + mUser.getTalent_bg_img();
        ImageLoaderUtils.display(ct, talent_item_bg, BgUrl);


        return view;
    }

    @OnClick(R.id.signin_manager)
    void signin_manager(View v) {
        startActivity(new Intent(ct, TalentSignInActivity.class));
    }


    static HomeTalentItemFragment fragment;

    public static HomeTalentItemFragment newInstance() {
        fragment = new HomeTalentItemFragment();
        return fragment;
    }

    @Override
    public void afterOncreate(Bundle savedInstanceState) {
    }


}
