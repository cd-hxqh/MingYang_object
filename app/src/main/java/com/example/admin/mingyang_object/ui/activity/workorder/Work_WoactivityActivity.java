package com.example.admin.mingyang_object.ui.activity.workorder;

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
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.WoactivityAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2016/5/10.
 */
public class Work_WoactivityActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private WoactivityAdapter woactivityAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    public WorkOrder workOrder;
    public ArrayList<Woactivity> woactivityList = new ArrayList<>();
    public ArrayList<Woactivity> deleteList = new ArrayList<>();

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
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
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
        titleTextView.setText(getResources().getString(R.string.title_activity_workwoactivity));
        menuImageView.setImageResource(R.mipmap.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(Work_WoactivityActivity.this);
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

        if (workOrder.WORKTYPE.equals(Constants.AA)||workOrder.WORKTYPE.equals(Constants.FR)){
            menuImageView.setVisibility(View.VISIBLE);
        }else {
            menuImageView.setVisibility(View.GONE);
        }

        if (!workOrder.isnew){
            if (woactivityList == null || woactivityList.size() == 0){
                refresh_layout.setRefreshing(true);
                getdata();
            }else{
                if (woactivityList != null && woactivityList.size() != 0) {
                    initList(woactivityList);
//                woactivityAdapter.addData(woactivityList);
                }else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        } else {//新建工单
            if (woactivityList == null || woactivityList.size() == 0){

            }else {
                if (woactivityList != null && woactivityList.size() != 0) {
                    initList(woactivityList);
//                woactivityAdapter.addData(woactivityList);
                }else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void initList(ArrayList<Woactivity> list ){
        ArrayList<Woactivity> woactivities = new ArrayList<>();
        for (int i = 0;i< list.size();i++){
            if (list.get(i).optiontype!=null&&list.get(i).optiontype.equals("delete")){
                deleteList.add(list.get(i));
            }else {
                woactivities.add(list.get(i));
            }
        }
        initAdapter(woactivities);
    }

    private void getdata() {
        if (workOrder.WONUM != null && !workOrder.WONUM.equals("")) {
            HttpManager.getDataPagingInfo(Work_WoactivityActivity.this, HttpManager.getwoactivityUrl(workOrder.WONUM, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<Woactivity> woactivities = null;
                    if (currentPage == page) {
                        woactivities = JsonUtils.parsingWoactivity(Work_WoactivityActivity.this, results.getResultlist(), workOrder.WONUM);
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if ((woactivities == null || woactivities.isEmpty())&&page==1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {

                        if (woactivities != null || woactivities.size() != 0) {
                            for (int i = 0; i < woactivities.size(); i++) {
                                woactivityList.add(woactivities.get(i));
                            }
                        }
                        nodatalayout.setVisibility(View.GONE);

                        initAdapter(woactivityList);
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
    private void initAdapter(final List<Woactivity> list) {
        woactivityAdapter = new WoactivityAdapter(Work_WoactivityActivity.this, R.layout.list_item, list,workOrder.WORKTYPE);
        recyclerView.setAdapter(woactivityAdapter);
        woactivityAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("woactivity", list.get(position));
                bundle.putSerializable("workOrder", workOrder);
                bundle.putSerializable("position", position);
                if (workOrder.WORKTYPE.equals(Constants.AA)){
                    intent.setClass(Work_WoactivityActivity.this, WoactivityDetailsActivity_AA.class);
                }else if (workOrder.WORKTYPE.equals(Constants.SP)){
                    intent.setClass(Work_WoactivityActivity.this, WoactivityDetailsActivity_SP.class);
                }else if (workOrder.WORKTYPE.equals(Constants.TP)){
                    intent.setClass(Work_WoactivityActivity.this, WoactivityDetailsActivity_TP.class);
                }else if (workOrder.WORKTYPE.equals(Constants.WS)){
                    intent.setClass(Work_WoactivityActivity.this, WoactivityDetailsActivity_WS.class);
                }
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
                final NormalDialog dialog = new NormalDialog(Work_WoactivityActivity.this);
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
                                Work_WoactivityActivity.this.finish();
//                                dialog.dismiss();
                            }
                        });
            } else {
                Work_WoactivityActivity.this.finish();
            }
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent();
            if (workOrder.WORKTYPE.equals(Constants.AA)){
                intent.setClass(Work_WoactivityActivity.this, WoactivityAddNewActivity_AA.class);
            }else if (workOrder.WORKTYPE.equals(Constants.SP)){
                intent.setClass(Work_WoactivityActivity.this, WoactivityAddNewActivity_SP.class);
            }else if (workOrder.WORKTYPE.equals(Constants.TP)){
                intent.setClass(Work_WoactivityActivity.this, WoactivityAddNewActivity_TP.class);
            }else if (workOrder.WORKTYPE.equals(Constants.WS)){
                intent.setClass(Work_WoactivityActivity.this, WoactivityAddNewActivity_WS.class);
            }
            intent.putExtra("taskid", (woactivityAdapter.getItemCount() + 1) * 10);
            startActivityForResult(intent, 1);
        }
    };

    private void setNodataLayout() {
        if (woactivityAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("woactivityList",getList());
            Work_WoactivityActivity.this.setResult(1000, intent);
            Work_WoactivityActivity.this.finish();
        }
    };

    private ArrayList<Woactivity> getList(){
        ArrayList<Woactivity> list = new ArrayList<>();
        if(woactivityAdapter.getData().size()!=0) {
            list.addAll(woactivityAdapter.getData());
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
                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
                    woactivityAdapter.add(woactivity);
                    initAdapter(woactivityAdapter.getData());
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
                    int position = data.getIntExtra("position", 0);
                    woactivityAdapter.set(position, woactivity);
                    initAdapter(woactivityAdapter.getData());
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    woactivityAdapter.remove(position);
                    initAdapter(woactivityAdapter.getData());
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
                    int position = data.getIntExtra("position", 0);
                    deleteList.add(woactivity);
                    woactivityAdapter.remove(position);
                    initAdapter(woactivityAdapter.getData());
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!workOrder.isnew&& (woactivityList == null || woactivityList.size() == 0)) {
            page = 1;
            getdata();
        }else {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void onLoad() {
        if (!workOrder.isnew) {
            page++;
            getdata();
        }
    }
}
