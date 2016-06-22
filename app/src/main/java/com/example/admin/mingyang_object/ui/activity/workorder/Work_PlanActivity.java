package com.example.admin.mingyang_object.ui.activity.workorder;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
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
//    private WoactivityFragment woactivityFragment;
//    private WplaborFragment wplaborFragment;
//    private WpmaterialFragment wpmaterialFragment;
//    private WpserviceFragment wpserviceFragment;
//    private WptoolFragment wptoolFragment;

    private LinearLayout confirmBtn;
    private Button revise;//
    private Button wfservice;

    public WorkOrder workOrder;
//    public ArrayList<Woactivity> woactivityList = new ArrayList<>();
//    public ArrayList<Wplabor> wplaborList = new ArrayList<>();
//    public ArrayList<Wpmaterial> wpmaterialList = new ArrayList<>();

//    public void setWoactivityList(ArrayList<Woactivity> list){
//        this.woactivityList = list;
//    }
//    public void setWplaborList(ArrayList<Wplabor> list){
//        this.wplaborList = list;
//    }
//    public void setWpmaterialList(ArrayList<Wpmaterial> list){
//        this.wpmaterialList = list;
//    }

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_plan);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
//        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
//        wplaborList = (ArrayList<Wplabor>) getIntent().getSerializableExtra("wplaborList");
//        wpmaterialList = (ArrayList<Wpmaterial>) getIntent().getSerializableExtra("wpmaterialList");
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

//        confirmBtn = (LinearLayout) findViewById(R.id.buttom_layout);
//        revise = (Button) findViewById(R.id.work_revise);
//        wfservice = (Button) findViewById(R.id.wfservice);
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
//        woactivity.setBackground(getResources().getDrawable(R.drawable.ab_solid_example));
        woactivity.setOnClickListener(new Buttonlistener());
        labtrans.setOnClickListener(new Buttonlistener());
        matusetrans.setOnClickListener(new Buttonlistener());
        fragmentlist = new ArrayList<>();
//        woactivityFragment = new WoactivityFragment(workOrder,woactivityList);
//        wplaborFragment = new WplaborFragment(workOrder,wplaborList);
//        wpmaterialFragment = new WpmaterialFragment(workOrder,wpmaterialList);
//        wpserviceFragment = new WpserviceFragment(workOrder);
//        wptoolFragment = new WptoolFragment(workOrder);
//        fragmentlist.add(woactivityFragment);
//        fragmentlist.add(wplaborFragment);
//        fragmentlist.add(wpmaterialFragment);
//        fragmentlist.add(wpserviceFragment);
//        fragmentlist.add(wptoolFragment);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
        woactivity.performClick();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

//        revise.setText(getResources().getString(R.string.ok));
//        revise.setOnClickListener(OkOnClickListener);
//        wfservice.setVisibility(View.GONE);

//        setNodataLayout();
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
//            intent.putExtra("woactivityList", woactivityFragment.woactivityAdapter.getList());
//            intent.putExtra("wplaborList", wplaborFragment.wplaborAdapter.getList());
//            intent.putExtra("wpmaterialList", wpmaterialFragment.wpmaterialAdapter.getList());
            Work_PlanActivity.this.setResult(1000, intent);
            finish();
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent;
//            if (currentIndex == 0) {
//                intent = new Intent(Work_PlanActivity.this, WoactivityAddNewActivity.class);
//                intent.putExtra("taskid", (woactivityFragment.woactivityAdapter.woactivityList.size() + 1) * 10);
//                startActivityForResult(intent, 0);
//            } else if (currentIndex == 1) {
//                intent = new Intent(Work_PlanActivity.this, WplaborAddNewActivity.class);
////                intent.putExtra("woactivityList", woactivityFragment.woactivityAdapter.woactivityList);
//                startActivityForResult(intent, 1);
//            } else if (currentIndex == 2) {
//                intent = new Intent(Work_PlanActivity.this, WpmaterialAddNewActivity.class);
////                intent.putExtra("woactivityList", woactivityFragment.woactivityAdapter.woactivityList);
//                startActivityForResult(intent, 2);
//            }
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
//        if(currentIndex==0){
//            if (woactivityFragment.woactivityAdapter.getItemCount()==0) {
//                woactivityFragment.nodatalayout.setVisibility(View.VISIBLE);
//            }else {
//                woactivityFragment.nodatalayout.setVisibility(View.GONE);
//            }
//        }else if (currentIndex==1){
//            if (wplaborFragment.wplaborAdapter.getItemCount()==0) {
//                wplaborFragment.nodatalayout.setVisibility(View.VISIBLE);
//            }else {
//                wplaborFragment.nodatalayout.setVisibility(View.GONE);
//            }
//        }else if (currentIndex==2){
//            if (wpmaterialFragment.wpmaterialAdapter.getItemCount()==0) {
//                wpmaterialFragment.nodatalayout.setVisibility(View.VISIBLE);
//            }else {
//                wpmaterialFragment.nodatalayout.setVisibility(View.GONE);
//            }
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
//            case -1:
//                if (data != null) {
//                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
////                    woactivityList.add(woactivity);
//                    woactivityFragment.woactivityAdapter.adddate(woactivity);
//                    woactivityFragment.nodatalayout.setVisibility(View.GONE);
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 1:
//                if (data != null) {
//                    Wplabor wplabor = (Wplabor) data.getSerializableExtra("wplabor");
////                    wplaborList.add(wplabor);
//                    wplaborFragment.wplaborAdapter.adddate(wplabor);
//                    wplaborFragment.nodatalayout.setVisibility(View.GONE);
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 2:
//                if (data != null) {
//                    Wpmaterial wpmaterial = (Wpmaterial) data.getSerializableExtra("wpmaterial");
////                    wpmaterialList.add(wpmaterial);
//                    wpmaterialFragment.wpmaterialAdapter.adddate(wpmaterial);
//                    wpmaterialFragment.nodatalayout.setVisibility(View.GONE);
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 4:
//                if (data != null){
//                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
//                    int position = data.getIntExtra("position",0);
////                    woactivityList.set(position,woactivity);
//                    woactivityFragment.woactivityAdapter.woactivityList.set(position, woactivity);
//                    woactivityFragment.woactivityAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 5:
//                if(data != null){
//                    Wplabor wplabor = (Wplabor) data.getSerializableExtra("wplabor");
//                    int position = data.getIntExtra("position",0);
////                    wplaborList.set(position,wplabor);
//                    wplaborFragment.wplaborAdapter.wplaborList.set(position,wplabor);
//                    wplaborFragment.wplaborAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 6:
//                if(data != null){
//                    Wpmaterial wpmaterial = (Wpmaterial) data.getSerializableExtra("wpmaterial");
//                    int position = data.getIntExtra("position",0);
////                    wpmaterialList.set(position,wpmaterial);
//                    wpmaterialFragment.wpmaterialAdapter.wpmaterialList.set(position, wpmaterial);
//                    wpmaterialFragment.wpmaterialAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 7://本地任务删除
//                if (data != null){
//                    int position = data.getIntExtra("position",0);
//                    woactivityFragment.woactivityAdapter.woactivityList.remove(position);
//                    woactivityFragment.woactivityAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 8://服务器任务删除操作
//                if (data != null){
//                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
//                    int position = data.getIntExtra("position",0);
//                    woactivityFragment.woactivityAdapter.deleteList.add(woactivity);
//                    woactivityFragment.woactivityAdapter.woactivityList.remove(position);
//                    woactivityFragment.woactivityAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 9://本地员工删除
//                if (data != null) {
//                    int position = data.getIntExtra("position", 0);
//                    wplaborFragment.wplaborAdapter.wplaborList.remove(position);
//                    wplaborFragment.wplaborAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 10://服务器员工删除
//                if(data != null){
//                    Wplabor wplabor = (Wplabor) data.getSerializableExtra("wplabor");
//                    int position = data.getIntExtra("position",0);
//                    wplaborFragment.wplaborAdapter.deleteList.add(wplabor);
//                    wplaborFragment.wplaborAdapter.wplaborList.remove(position);
//                    wplaborFragment.wplaborAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 11://本地物料删除
//                if(data != null){
//                    int position = data.getIntExtra("position", 0);
//                    wpmaterialFragment.wpmaterialAdapter.wpmaterialList.remove(position);
//                    wpmaterialFragment.wpmaterialAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 12://服务器物料删除
//                if(data != null){
//                    Wpmaterial wpmaterial = (Wpmaterial) data.getSerializableExtra("wpmaterial");
//                    int position = data.getIntExtra("position",0);
//                    wpmaterialFragment.wpmaterialAdapter.deleteList.add(wpmaterial);
//                    wpmaterialFragment.wpmaterialAdapter.wpmaterialList.remove(position);
//                    wpmaterialFragment.wpmaterialAdapter.notifyDataSetChanged();
//                }
//                confirmBtn.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            default:
//                break;
        }
    }
}
