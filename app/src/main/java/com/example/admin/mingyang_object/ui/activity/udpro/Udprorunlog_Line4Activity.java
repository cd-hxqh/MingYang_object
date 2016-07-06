package com.example.admin.mingyang_object.ui.activity.udpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.UdprorunlogLine4;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdprorunlogLine4Adapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 工装管理列表页面
 * Created by think on 2016/7/4.
 */
public class Udprorunlog_Line4Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UdprorunlogLine4Adapter udprorunlogLine4Adapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    public Udprorunlog udprorunlog;
    public ArrayList<UdprorunlogLine4>UdprorunlogLine4List = new ArrayList<>();
    public ArrayList<UdprorunlogLine4> deleteList = new ArrayList<>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private LinearLayout confirmlayout;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity);

        getData();
        findViewById();
        initView();
    }

    private void getData() {
        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
        UdprorunlogLine4List = (ArrayList<UdprorunlogLine4>) getIntent().getSerializableExtra("UdprorunlogLine4List");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        confirmlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirmBtn = (Button) findViewById(R.id.confirm);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backOnClickListener);
        titleTextView.setText(getResources().getString(R.string.udprorunlog_line4_title));
        menuImageView.setImageResource(R.mipmap.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(Udprorunlog_Line4Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        woactivityAdapter = new WoactivityAdapter(Work_WoactivityActivity.this);
//        recyclerView.setAdapter(woactivityAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

//        if ((workOrder.status != null && workOrder.status.equals(Constants.STATUS25))||workOrder.isnew) {
//            menuImageView.setVisibility(View.VISIBLE);
//        }else {
//            menuImageView.setVisibility(View.GONE);
//        }

        if (!udprorunlog.isnew){
            if (UdprorunlogLine4List == null || UdprorunlogLine4List.size() == 0){
                refresh_layout.setRefreshing(true);
                getdata();
            }else{
                if (UdprorunlogLine4List != null && UdprorunlogLine4List.size() != 0) {
                    initList(UdprorunlogLine4List);
//                woactivityAdapter.addData(woactivityList);
                }else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        } else {//新建项目日报
            if (UdprorunlogLine4List == null || UdprorunlogLine4List.size() == 0){
                initAdapter(new ArrayList<UdprorunlogLine4>());
                nodatalayout.setVisibility(View.VISIBLE);
            }else {
                if (UdprorunlogLine4List != null && UdprorunlogLine4List.size() != 0) {
                    initList(UdprorunlogLine4List);
//                woactivityAdapter.addData(woactivityList);
                }else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void initList(ArrayList<UdprorunlogLine4> list ){
        ArrayList<UdprorunlogLine4> woactivities = new ArrayList<>();
        for (int i = 0;i< list.size();i++){
            if (list.get(i).TYPE!=null&&list.get(i).TYPE.equals("delete")){
                deleteList.add(list.get(i));
            }else {
                woactivities.add(list.get(i));
            }
        }
        initAdapter(woactivities);
    }

    private void getdata() {
        if (udprorunlog.PRORUNLOGNUM != null && !udprorunlog.PRORUNLOGNUM.equals("")) {
            HttpManager.getDataPagingInfo(Udprorunlog_Line4Activity.this, HttpManager.getUdprorunlogLine4Url(udprorunlog.PRORUNLOGNUM, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<UdprorunlogLine4> udprorunlogLine4s = null;
                    if (currentPage == page) {
                        udprorunlogLine4s = JsonUtils.parsingUdprorunlogLine4(Udprorunlog_Line4Activity.this, results.getResultlist(), udprorunlog.PRORUNLOGNUM);
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if ((udprorunlogLine4s == null || udprorunlogLine4s.isEmpty())&&page==1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        initAdapter(new ArrayList<UdprorunlogLine4>());
                    } else {
                        if (udprorunlogLine4s != null || udprorunlogLine4s.size() != 0) {
                            if (UdprorunlogLine4List == null){
                                UdprorunlogLine4List = new ArrayList<UdprorunlogLine4>();
                            }
                            for (int i = 0; i < udprorunlogLine4s.size(); i++) {
                                UdprorunlogLine4List.add(udprorunlogLine4s.get(i));
                            }
                        }
                        nodatalayout.setVisibility(View.GONE);

                        initAdapter(UdprorunlogLine4List);
                    }
                }

                @Override
                public void onFailure(String error) {
                    if (page == 1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                }
            });
        } else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
    }

    /**
     * 获取数据*
     */
    private void initAdapter(final List<UdprorunlogLine4> list) {
        udprorunlogLine4Adapter = new UdprorunlogLine4Adapter(Udprorunlog_Line4Activity.this, R.layout.list_item, list);
        recyclerView.setAdapter(udprorunlogLine4Adapter);
        udprorunlogLine4Adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("udprorunlogline4", list.get(position));
                bundle.putSerializable("udprorunlog", udprorunlog);
                bundle.putSerializable("position", position);
                intent.setClass(Udprorunlog_Line4Activity.this, Udprorunlog_Line4DetailsActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

//    private void addListData(ArrayList<Woactivity> list) {
//        if (nodatalayout.getVisibility() == View.VISIBLE) {
//            nodatalayout.setVisibility(View.GONE);
//        }
//        if (page == 1 && woactivityAdapter.getItemCount() != 0) {
//            woactivityAdapter = new WoactivityAdapter(Woactivity_Activity.this);
//            recyclerView.setAdapter(woactivityAdapter);
//        }
//        if ((list == null || list.size() == 0) && page == 1) {
//            nodatalayout.setVisibility(View.VISIBLE);
//        } else {
//            woactivityAdapter.addData(list);
//        }
//    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (confirmlayout.getVisibility() == View.VISIBLE) {
                final NormalDialog dialog = new NormalDialog(Udprorunlog_Line4Activity.this);
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
                                Udprorunlog_Line4Activity.this.finish();
//                                dialog.dismiss();
                            }
                        });
            } else {
                Udprorunlog_Line4Activity.this.finish();
            }
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent();
            intent.setClass(Udprorunlog_Line4Activity.this, Udprorunlog_Line4AddNewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("udprorunlog", udprorunlog);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);
        }
    };

    private void setNodataLayout() {
        if (udprorunlogLine4Adapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("UdprorunlogLine4List",getList());
            Udprorunlog_Line4Activity.this.setResult(4000, intent);
            Udprorunlog_Line4Activity.this.finish();
        }
    };

    private ArrayList<UdprorunlogLine4> getList(){
        ArrayList<UdprorunlogLine4> list = new ArrayList<>();
        if(udprorunlogLine4Adapter.getData().size()!=0) {
            list.addAll(udprorunlogLine4Adapter.getData());
        }
        if(deleteList.size()!=0) {
            list.addAll(deleteList);
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1://新增
                if (data != null) {
                    UdprorunlogLine4 udprorunlogLine4 = (UdprorunlogLine4) data.getSerializableExtra("udprorunlogLine4");
                    udprorunlogLine4Adapter.add(udprorunlogLine4);
                    initAdapter(udprorunlogLine4Adapter.getData());
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    UdprorunlogLine4 udprorunlogLine1 = (UdprorunlogLine4) data.getSerializableExtra("udprorunlogLine4");
                    int position = data.getIntExtra("position", 0);
                    udprorunlogLine4Adapter.set(position, udprorunlogLine1);
                    initAdapter(udprorunlogLine4Adapter.getData());
                    udprorunlogLine4Adapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    udprorunlogLine4Adapter.remove(position);
                    initAdapter(udprorunlogLine4Adapter.getData());
                    udprorunlogLine4Adapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    UdprorunlogLine4 udprorunlogLine4 = (UdprorunlogLine4) data.getSerializableExtra("udprorunlogLine4");
                    int position = data.getIntExtra("position", 0);
                    deleteList.add(udprorunlogLine4);
                    udprorunlogLine4Adapter.remove(position);
                    initAdapter(udprorunlogLine4Adapter.getData());
                    udprorunlogLine4Adapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!udprorunlog.isnew&& (UdprorunlogLine4List == null || UdprorunlogLine4List.size() == 0)) {
            page = 1;
            getdata();
        }else {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void onLoad() {
        if (!udprorunlog.isnew) {
            page++;
            getdata();
        }else {
            refresh_layout.setLoading(false);
        }
    }
}
