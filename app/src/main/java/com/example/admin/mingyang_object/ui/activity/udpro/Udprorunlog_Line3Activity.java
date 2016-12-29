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
import com.example.admin.mingyang_object.model.UdprorunlogLine3;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdprorunlogLine3Adapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 工作日报列表页面
 * Created by think on 2016/7/6.
 */
public class Udprorunlog_Line3Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UdprorunlogLine3Adapter udprorunlogLine3Adapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    public Udprorunlog udprorunlog;
    public ArrayList<UdprorunlogLine3>UdprorunlogLine3List = new ArrayList<>();
    public ArrayList<UdprorunlogLine3> deleteList = new ArrayList<>();

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
        Log.e("项目日报","工作日报");
    }

    private void getData() {
        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
        UdprorunlogLine3List = (ArrayList<UdprorunlogLine3>) getIntent().getSerializableExtra("UdprorunlogLine3List");
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
        titleTextView.setText(getResources().getString(R.string.udprorunlog_line3_title));
        menuImageView.setImageResource(R.mipmap.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(Udprorunlog_Line3Activity.this);
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
            if (UdprorunlogLine3List == null || UdprorunlogLine3List.size() == 0){
                refresh_layout.setRefreshing(true);
                getdata();
            }else{
                if (UdprorunlogLine3List != null && UdprorunlogLine3List.size() != 0) {
                    initList(UdprorunlogLine3List);
//                woactivityAdapter.addData(woactivityList);
                }else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        } else {//新建项目日报
            if (UdprorunlogLine3List == null || UdprorunlogLine3List.size() == 0){
                initAdapter(new ArrayList<UdprorunlogLine3>());
                nodatalayout.setVisibility(View.VISIBLE);
            }else {
                if (UdprorunlogLine3List != null && UdprorunlogLine3List.size() != 0) {
                    initList(UdprorunlogLine3List);
//                woactivityAdapter.addData(woactivityList);
                }else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void initList(ArrayList<UdprorunlogLine3> list ){
        ArrayList<UdprorunlogLine3> woactivities = new ArrayList<>();
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
            HttpManager.getDataPagingInfo(Udprorunlog_Line3Activity.this, HttpManager.getUdprorunlogLine3Url(udprorunlog.PRORUNLOGNUM, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<UdprorunlogLine3> udprorunlogLine3s = null;
                    if (currentPage == page) {
                        udprorunlogLine3s = JsonUtils.parsingUdprorunlogLine3(Udprorunlog_Line3Activity.this, results.getResultlist(), udprorunlog.PRORUNLOGNUM);
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if ((udprorunlogLine3s == null || udprorunlogLine3s.isEmpty())&&page==1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        initAdapter(new ArrayList<UdprorunlogLine3>());
                    } else {

                        if (udprorunlogLine3s != null || udprorunlogLine3s.size() != 0) {
                            if (UdprorunlogLine3List == null){
                                UdprorunlogLine3List = new ArrayList<UdprorunlogLine3>();
                            }
                            for (int i = 0; i < udprorunlogLine3s.size(); i++) {
                                UdprorunlogLine3List.add(udprorunlogLine3s.get(i));
                            }
                        }
                        nodatalayout.setVisibility(View.GONE);

                        initAdapter(UdprorunlogLine3List);
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
    private void initAdapter(final List<UdprorunlogLine3> list) {
        udprorunlogLine3Adapter = new UdprorunlogLine3Adapter(Udprorunlog_Line3Activity.this, R.layout.list_item, list);
        recyclerView.setAdapter(udprorunlogLine3Adapter);
        udprorunlogLine3Adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("udprorunlogline3", list.get(position));
                bundle.putSerializable("udprorunlog", udprorunlog);
                bundle.putSerializable("position", position);
                intent.setClass(Udprorunlog_Line3Activity.this, Udprorunlog_Line3DetailsActivity.class);
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
                final NormalDialog dialog = new NormalDialog(Udprorunlog_Line3Activity.this);
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
                                Udprorunlog_Line3Activity.this.finish();
//                                dialog.dismiss();
                            }
                        });
            } else {
                Udprorunlog_Line3Activity.this.finish();
            }
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent();
            intent.setClass(Udprorunlog_Line3Activity.this, Udprorunlog_Line3AddNewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("udprorunlog", udprorunlog);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);
        }
    };

    private void setNodataLayout() {
        if (udprorunlogLine3Adapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("UdprorunlogLine3List",getList());
            Udprorunlog_Line3Activity.this.setResult(3000, intent);
            Udprorunlog_Line3Activity.this.finish();
        }
    };

    private ArrayList<UdprorunlogLine3> getList(){
        ArrayList<UdprorunlogLine3> list = new ArrayList<>();
        if(udprorunlogLine3Adapter.getData().size()!=0) {
            list.addAll(udprorunlogLine3Adapter.getData());
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
                    UdprorunlogLine3 udprorunlogLine3 = (UdprorunlogLine3) data.getSerializableExtra("udprorunlogLine3");
                    udprorunlogLine3Adapter.add(udprorunlogLine3);
                    initAdapter(udprorunlogLine3Adapter.getData());
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    UdprorunlogLine3 udprorunlogLine3 = (UdprorunlogLine3) data.getSerializableExtra("udprorunlogLine3");
                    int position = data.getIntExtra("position", 0);
                    udprorunlogLine3Adapter.set(position, udprorunlogLine3);
                    initAdapter(udprorunlogLine3Adapter.getData());
                    udprorunlogLine3Adapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    udprorunlogLine3Adapter.remove(position);
                    initAdapter(udprorunlogLine3Adapter.getData());
                    udprorunlogLine3Adapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    UdprorunlogLine3 udprorunlogLine3 = (UdprorunlogLine3) data.getSerializableExtra("udprorunlogLine3");
                    int position = data.getIntExtra("position", 0);
                    deleteList.add(udprorunlogLine3);
                    udprorunlogLine3Adapter.remove(position);
                    initAdapter(udprorunlogLine3Adapter.getData());
                    udprorunlogLine3Adapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!udprorunlog.isnew&& (UdprorunlogLine3List == null || UdprorunlogLine3List.size() == 0)) {
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
