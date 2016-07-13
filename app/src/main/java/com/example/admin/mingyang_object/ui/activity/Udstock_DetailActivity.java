package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Udstock;
import com.example.admin.mingyang_object.model.Udstockline;
import com.example.admin.mingyang_object.model.Wfassignment;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdstocklineAdapter;
import com.example.admin.mingyang_object.ui.adapter.WfmListAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.example.admin.mingyang_object.utils.AccountUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 盘点详情
 */
public class Udstock_DetailActivity extends BaseActivity {
    private static String TAG = "Udstock_DetailActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**
     * 菜单
     */
    private ImageView menuImageView;


    /**
     * 界面信息*
     */
    private TextView stocknumText; //盘点单号

    private TextView descriptionText; //描述

    private TextView zpdnumText; //盘点单号凭证

    private TextView locationText; //仓库

    private TextView locdescText; //仓库名称

    private TextView createdbyText; //创建人

    private TextView createdateText; //创建时间


    private Udstock udstock;

    private PopupWindow popupWindow;

    /**
     * 上传附件*
     */
    private LinearLayout uploadfile;


    LinearLayoutManager layoutManager;


    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;
    /**
     * 暂无数据*
     */
    private LinearLayout nodatalayout;
    /**
     * 界面刷新*
     */
    private SwipeRefreshLayout refresh_layout = null;

    UdstocklineAdapter udstocklineAdapter;

    private int page = 1;

    ArrayList<Udstockline> items = new ArrayList<Udstockline>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udstock_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udstock = (Udstock) getIntent().getSerializableExtra("udstock");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        stocknumText = (TextView) findViewById(R.id.stocknum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        zpdnumText = (TextView) findViewById(R.id.zpdnum_text_id);
        locationText = (TextView) findViewById(R.id.location_text_id);
        locdescText = (TextView) findViewById(R.id.locdesc_text_id);
        createdbyText = (TextView) findViewById(R.id.createdby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);

        if (udstock != null) {
            stocknumText.setText(udstock.getSTOCKNUM());
            descriptionText.setText(udstock.getDESCRIPTION());
            zpdnumText.setText(udstock.getZPDNUM());
            locationText.setText(udstock.getLOCATION());
            locdescText.setText(udstock.getLOCDESC());
            createdbyText.setText(udstock.getCREATEDBY());
            createdateText.setText(udstock.getCREATEDATE());
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udstock_detail_text));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        layoutManager = new LinearLayoutManager(Udstock_DetailActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(onRefreshListener);
        refresh_layout.setOnLoadListener(onLoadListener);
        getData();

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udstock_DetailActivity.this).inflate(
                R.layout.upload_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        popupWindow.showAsDropDown(view);
        uploadfile = (LinearLayout) contentView.findViewById(R.id.upload_file_id);
        uploadfile.setOnClickListener(uploadfileOnClickListener);


    }


    private View.OnClickListener uploadfileOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent intent = new Intent(Udstock_DetailActivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "UDSTOCK");
            intent.putExtra("ownerid", udstock.getUDSTOCKID());
            startActivityForResult(intent, 0);
        }
    };


    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            page = 1;

            getData();
        }
    };

    private SwipeRefreshLayout.OnLoadListener onLoadListener = new SwipeRefreshLayout.OnLoadListener() {
        @Override
        public void onLoad() {
            page++;

            getData();
        }
    };


    /**
     * 获取数据*
     */
    private void getData() {
        HttpManager.getDataPagingInfo(Udstock_DetailActivity.this, HttpManager.getudstocklineurl(udstock.getLOCATION(), udstock.getSTOCKNUM(), page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {

                ArrayList<Udstockline> item = JsonUtils.parsingUdstockline(Udstock_DetailActivity.this, results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {

                    if (item != null || item.size() != 0) {
                        for (int i = 0; i < item.size(); i++) {
                            items.add(item.get(i));
                        }
                    }
                    nodatalayout.setVisibility(View.GONE);

                    initAdapter(item);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Udstockline> list) {
        udstocklineAdapter = new UdstocklineAdapter(Udstock_DetailActivity.this, R.layout.udstocklinelist_item, list);
        recyclerView.setAdapter(udstocklineAdapter);
        udstocklineAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udstock_DetailActivity.this, Udstockline_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udstockline", items.get(position));
                bundle.putSerializable("udstock", udstock);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }


}
