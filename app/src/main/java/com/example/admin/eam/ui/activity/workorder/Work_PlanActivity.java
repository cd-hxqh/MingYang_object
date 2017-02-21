package com.example.admin.eam.ui.activity.workorder;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.WorkOrder;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/12/3.
 * 工单任务页面
 */
public class Work_PlanActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private ImageView backimg;
    private Button woactivity;//任务
    private Button labtrans;//员工
    private Button matusetrans;//物料
    private ViewPager mViewPager;
    private int currentIndex = 0;
    private List<Fragment> fragmentlist = new ArrayList<>();


    private LinearLayout confirmBtn;
    private Button revise;//
    private Button wfservice;

    public WorkOrder workOrder;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_plan);
        Log.e("工单","工作计划");
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backimg = (ImageView) findViewById(R.id.title_back_id);
        woactivity = (Button) findViewById(R.id.work_woactivity);
        labtrans = (Button) findViewById(R.id.work_labtrans);
        matusetrans = (Button) findViewById(R.id.work_matusetrans);
        mViewPager = (ViewPager) findViewById(R.id.pager);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView() {
        mViewPager.setCurrentItem(currentIndex);
        mViewPager.setOffscreenPageLimit(2);
        titlename.setText(getResources().getString(R.string.work_plan));
        backimg.setOnClickListener(backOnClickListener);
        menuImageView.setImageResource(R.mipmap.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        if (workOrder.WONUM!=null&&!workOrder.WONUM.equals("")&&!workOrder.STATUS.equals("")){
            menuImageView.setVisibility(View.GONE);
        }

        woactivity.setOnClickListener(new Buttonlistener());
        labtrans.setOnClickListener(new Buttonlistener());
        matusetrans.setOnClickListener(new Buttonlistener());
        fragmentlist = new ArrayList<>();

        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
        woactivity.performClick();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();


    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (confirmBtn.getVisibility() == View.VISIBLE) {
                final NormalDialog dialog = new NormalDialog(Work_PlanActivity.this);
                dialog.content("确定放弃修改吗?")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
                        .show();

                dialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                Work_PlanActivity.this.finish();
//                            dialog.dismiss();
                            }
                        });
            }else {
                Work_PlanActivity.this.finish();
            }
        }
    };

    private View.OnClickListener OkOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();

            Work_PlanActivity.this.setResult(1000, intent);
            finish();
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    public class Buttonlistener implements View.OnClickListener {
        public Buttonlistener() {

        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {
            resetTextView();
            if (view.getId() == woactivity.getId()) {
//                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                woactivity.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 0;
            } else if (view.getId() == labtrans.getId()) {
//                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                labtrans.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 1;
            } else if (view.getId() == matusetrans.getId()) {
//                view.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
                matusetrans.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 2;
            }
            mViewPager.setCurrentItem(currentIndex);
        }
    }

    /**
     * ViewPager的适配器
     */
    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }

    /**
     * ViewPager的PageChangeListener(页面改变的监听器)
     */
    private class MyPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float offset, int offsetPixels) {

        }

        /**
         * 滑动ViewPager的时候
         */
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onPageSelected(int position) {
            resetTextView();
            switch (position) {
                case 0:
                    woactivity.performClick();
                    break;
                case 1:
                    labtrans.performClick();
                    break;
                case 2:
                    matusetrans.performClick();
                    break;
            }
            currentIndex = position;
        }
    }

    /**
     * 重置颜色
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void resetTextView() {
        woactivity.setTextColor(getResources().getColor(R.color.black));
        woactivity.setBackground(getResources().getDrawable(R.color.light_gray));
        labtrans.setTextColor(getResources().getColor(R.color.black));
        labtrans.setBackground(getResources().getDrawable(R.color.light_gray));
        matusetrans.setTextColor(getResources().getColor(R.color.black));
        matusetrans.setBackground(getResources().getDrawable(R.color.light_gray));
    }

    private void setNodataLayout(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
//
        }
    }
}
