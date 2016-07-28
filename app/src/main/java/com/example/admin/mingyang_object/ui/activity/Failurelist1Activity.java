package com.example.admin.mingyang_object.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.model.Failurelist;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.adapter.Failurelist1Adapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;

import java.util.ArrayList;


/**
 * Created by think on 2016/7/16.
 * 故障原因界面
 */
public class Failurelist1Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    private static String TAG = "Failurelist1Activity";

    private TextView titlename;
    private RelativeLayout backlayout;

    private String failurecode;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private Failurelist1Adapter failurelist1Adapter;
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
        failurecode = getIntent().getStringExtra("failurecode");
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
        titlename.setText("故障原因");
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
        failurelist1Adapter = new Failurelist1Adapter(this);
        recyclerView.setAdapter(failurelist1Adapter);
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        getData();

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getData(){//得到问题原因
            HttpManager.getDataPagingInfo(this, HttpManager.getFailurelist3Url(page, 20, getIntent().getStringExtra("failurecode")), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int totalPages, int currentPage) {
                    if (results.getResultlist() != null) {
                        ArrayList<Failurelist> items = JsonUtils.parsingFailurelist(results.getResultlist());
                        if (items==null){
                            refresh_layout.setRefreshing(false);
                            refresh_layout.setLoading(false);
                            nodatalayout.setVisibility(View.VISIBLE);
                        }else if(items.size()==1){//问题原因
                            getData2(items.get(0).FAILURELIST+"");
                        }
                    } else {
                        refresh_layout.setRefreshing(false);
                        refresh_layout.setLoading(false);
                        nodatalayout.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(String error) {
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            });
    }

    private void getData2(String failurelist){
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
                        failurelist1Adapter.adddate(items);
                        refresh_layout.setRefreshing(false);
                        refresh_layout.setLoading(false);
                        nodatalayout.setVisibility(View.GONE);
                    }
                } else {
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        failurelist1Adapter = new Failurelist1Adapter(Failurelist1Activity.this);
        getData();
    }

    @Override
    public void onLoad(){
        page++;
        getData();
    }
}
