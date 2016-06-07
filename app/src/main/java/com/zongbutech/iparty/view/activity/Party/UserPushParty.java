package com.zongbutech.iparty.view.activity.Party;

import android.os.Bundle;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.view.activity.BaseActivity;
import com.zongbutech.iparty.view.fragment.HomeActivity.HomePartFragment;

public class UserPushParty extends BaseActivity {
    int TalentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_push_party);
        TalentId = getIntent().getIntExtra("TalentId", -1);

        HomePartFragment mHomePartFragment = new HomePartFragment();
        Bundle bd = new Bundle();
        bd.putInt("TalentId", TalentId);
        bd.putString("from", UserPushParty.class.getSimpleName());
        mHomePartFragment.setArguments(bd);

        getSupportFragmentManager().beginTransaction().replace(R.id.push_replace,mHomePartFragment).commit();


    }


}
