package com.example.admin.eam.ui.activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.bean.Results;
import com.example.admin.eam.model.UdTriprePort;
import com.example.admin.eam.ui.adapter.tripReportListAdapter;
import com.example.admin.eam.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;

public class TriprePort_listActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener {

    private TextView titlename;//标题
    private ImageView addimg;//
    private Button choose;//
    private RelativeLayout backlayout;//
    LinearLayoutManager layoutManager;//
    public RecyclerView recyclerView;//
    private LinearLayout nodatalayout;//
    private tripReportListAdapter tripReportAdapter;//
    private SwipeRefreshLayout refresh_layout = null;//
    private EditText search;//关键字输入框
    private String searchText = "";//关键字
    private int page = 1;//当前叶
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private String status = "全部";
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tripre_port);

        findViewById();

        getIntentData();

        initView();

        Log.e("出差","启动");
        dismiss8();
        dismiss5();
    }
    private void getIntentData(){

    }
    public void  dismiss8()
    {
        Log.e("出差","AA "+2*3*4*5*6*6*6*6*5*4*3*2);
    }

    public void  dismiss5()
    {

    }
        @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.title_add);
        choose = (Button) findViewById(R.id.title_choose);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }
    @Override
    protected void initView() {
        choose.setVisibility(View.VISIBLE);
        choose.setOnClickListener(new NormalListDialogOnClickListener(choose));
        titlename.setText("出差总结报告");
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TriprePort_listActivity.this,TriprePort_addNewActivity.class);
                startActivity(intent);
            }
        });

        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        tripReportAdapter = new tripReportListAdapter(this);
        recyclerView.setAdapter(tripReportAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        refresh_layout.setRefreshing(true);
        getData(searchText,status);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getData(String search,String status){

        Log.e("出差","请求地址"+HttpManager.getTripReportUrl(search, page, 20));

        HttpManager.getDataPagingInfo(this, HttpManager.getTripReportUrl(search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.e("出差","请求成功");
                // Log.i(TAG, "data=" + results);
            }
            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                Log.e("出差","请求成功");
                if (results.getResultlist()!=null) {
                    ArrayList<UdTriprePort> items = JsonUtils.parsingTripReport(TriprePort_listActivity.this, results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            tripReportAdapter = new tripReportListAdapter(TriprePort_listActivity.this);
                            recyclerView.setAdapter(tripReportAdapter);
                        }
                        if (totalPages == page) {
                            tripReportAdapter.adddate(items);
                        }
                    }
                }else {
                    refresh_layout.setRefreshing(false);
                    nodatalayout.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onFailure(String error) {
                Log.e("出差","请求失败");
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });

    }

    private class NormalListDialogOnClickListener implements View.OnClickListener {
        TextView textView;

        public NormalListDialogOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            NormalListDialog(textView);
        }
    }
    private void NormalListDialog(final TextView textView) {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();

        types = getResources().getStringArray(R.array.dc_status_array);

        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(TriprePort_listActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(mMenuItems.get(position).mOperName);
                tripReportAdapter = new tripReportListAdapter(TriprePort_listActivity.this);
                recyclerView.setAdapter(tripReportAdapter);
                status = mMenuItems.get(position).mOperName;
                choose.setText(status);
                getData(search.getText().toString(), status);
                dialog.dismiss();
            }
        });
    }
    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(search.getText().toString(),status);
    }

    @Override
    public void onLoad(){
        page++;
        getData(searchText,status);
    }

}
