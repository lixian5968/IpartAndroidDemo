package com.zongbutech.iparty.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zongbutech.iparty.R;
import com.zongbutech.iparty.utils.splash.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class SplashSelectActivity extends BaseActivity {

    private ViewPager mViewPager;
    private int[] mImgIds = new int[]{R.drawable.android_guide_step_1, R.drawable.android_guide_step_2, R.drawable.android_guide_step_3};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    private LinearLayout mPointLinearLayout;

    // 小圆点图片
    private ImageView imageView;
    // 小圆点图片 合计
    private ImageView[] imageViews;
    Button GoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_select);
        initData();
        mViewPager = (ViewPager) findViewById(R.id.splash_select_viewpager);
        mPointLinearLayout = (LinearLayout) findViewById(R.id.mPointLinearLayout);
        GoHome = (Button) findViewById(R.id.GoHome);

        mViewPager.setPageTransformer(true, new DepthPageTransformer());
//        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(mImageViews.get(position));
                return mImageViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {

                container.removeView(mImageViews.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public int getCount() {
                return mImgIds.length;
            }
        });

        imageViews = new ImageView[mImgIds.length];
        for (int i = 0; i < mImgIds.length; i++) {
            LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            // 设置每个小圆点距离左边的间距
            margin.setMargins(10, 0, 0, 0);
            imageView = new ImageView(ct);
            // 设置每个小圆点的宽高android.view.ViewGroup.LayoutParams;
            imageView.setLayoutParams(new ViewGroup.LayoutParams(15, 15));
            imageViews[i] = imageView;
            if (i == 0) {
                // 默认选中第一张图片
                imageViews[i].setBackgroundResource(R.drawable.point_push);
            } else {
                // 其他图片都设置未选中状态
                imageViews[i].setBackgroundResource(R.drawable.point_pop);
            }
            mPointLinearLayout.addView(imageViews[i], margin);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==imageViews.length-1){
                    GoHome.setVisibility(View.VISIBLE);
                }else{
                    GoHome.setVisibility(View.GONE);
                }

                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[position].setBackgroundResource(R.drawable.point_push);
                    if (position != i) {
                        imageViews[i].setBackgroundResource(R.drawable.point_pop);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        GoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashSelectActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    private void initData() {
        for (int imgId : mImgIds) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }

}
