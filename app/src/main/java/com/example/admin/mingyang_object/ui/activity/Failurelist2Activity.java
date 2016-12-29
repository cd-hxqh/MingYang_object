package com.example.admin.mingyang_object.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.model.Failurelist;
import com.example.admin.mingyang_object.ui.adapter.Failurelist2Adapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;

import java.util.ArrayList;


/**
 * Created by think on 2016/7/16.
 * 补救措施界面
 */
public class Failurelist2Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    private static String TAG = "Failurelist2Activity";

    private TextView titlename;
    private RelativeLayout backlayout;

    private String failurelist;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private Failurelist2Adapter failurelist2Adapter;
    private SwipeRefreshLayout refresh_layout = null;
    private RelativeLayout searLayout;
    private int page = 1;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklist);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        findViewById();

        getIntentData();

        initView();

    }

    private void getIntentData(){
        failurelist = getIntent().getStringExtra("failurelist");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        searLayout = (RelativeLayout) findViewById(R.id.search_layout);
    }

    @Override
    protected void initView() {
        titlename.setText("补救措施");
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searLayout.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        failurelist2Adapter = new Failurelist2Adapter(this);
        recyclerView.setAdapter(failurelist2Adapter);
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        getData(failurelist);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getData(String failurelist){
        HttpManager.getDataPagingInfo(this,HttpManager.getFailurelist2Url("", page, 20,failurelist), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                if (results.getResultlist() != null) {
                    ArrayList<Failurelist> items = JsonUtils.parsingFailurelist(results.getResultlist());
                    if (items==null||items.size()==0){
                        refresh_layout.setRefreshing(false);
                        nodatalayout.setVisibility(View.VISIBLE);
                    }else{//问题原因
                        failurelist2Adapter.adddate(items);
                        refresh_layout.setRefreshing(false);
                        nodatalayout.setVisibility(View.GONE);
                    }
                } else {
                    refresh_layout.setRefreshing(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        failurelist2Adapter = new Failurelist2Adapter(Failurelist2Activity.this);
        getData(failurelist);
    }

    @Override
    public void onLoad(){
        page++;
        getData(failurelist);
    }
}
