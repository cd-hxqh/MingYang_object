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
import com.example.admin.mingyang_object.dao.WpmaterialDao;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Wpmaterial;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.WoactivityAdapter;
import com.example.admin.mingyang_object.ui.adapter.WpmaterialAdapter;
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
public class Work_WpmaterialActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private WpmaterialAdapter wpmaterialAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    public WorkOrder workOrder;
    public ArrayList<Wpmaterial> wpmaterialList = new ArrayList<>();
    public ArrayList<Wpmaterial> deleteList = new ArrayList<>();

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
        wpmaterialList = (ArrayList<Wpmaterial>) getIntent().getSerializableExtra("wpmaterialList");
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
        titleTextView.setText(getResources().getString(R.string.title_activity_wpmaterial));
        menuImageView.setImageResource(R.mipmap.add);
        if (!workOrder.UDSTATUS.equals("已完成")||!workOrder.UDSTATUS.equals("已关闭")) {
            menuImageView.setVisibility(View.VISIBLE);
        }
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(Work_WpmaterialActivity.this);
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

        if (workOrder.id==0) {
            if (!workOrder.isnew) {
                if (wpmaterialList == null || wpmaterialList.size() == 0) {
                    refresh_layout.setRefreshing(true);
                    getdata();
                } else {
                    if (wpmaterialList != null && wpmaterialList.size() != 0) {
                        initList(wpmaterialList);
//                woactivityAdapter.addData(woactivityList);
                    } else {
                        nodatalayout.setVisibility(View.VISIBLE);
                    }
                }
            } else {//新建工单
                if (wpmaterialList == null || wpmaterialList.size() == 0) {
                    initAdapter(new ArrayList<Wpmaterial>());
                } else {
                    if (wpmaterialList != null && wpmaterialList.size() != 0) {
                        initList(wpmaterialList);
//                woactivityAdapter.addData(woactivityList);
                    } else {
                        nodatalayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }else {//本地历史工单
            if (wpmaterialList != null && wpmaterialList.size() != 0) {
                initList(wpmaterialList);
//                woactivityAdapter.addData(woactivityList);
            } else {
                initAdapter(new ArrayList<Wpmaterial>());
                nodatalayout.setVisibility(View.VISIBLE);
            }
        }
        setNodataLayout();
    }

    private void initList(ArrayList<Wpmaterial> list ){
        ArrayList<Wpmaterial> woactivities = new ArrayList<>();
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
        if (workOrder.WONUM != null && !workOrder.WONUM.equals("")) {
            HttpManager.getDataPagingInfo(Work_WpmaterialActivity.this, HttpManager.getWpmaterialUrl(workOrder.WONUM, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<Wpmaterial> wpmaterials = null;
                    if (currentPage == page) {
                        wpmaterials = JsonUtils.parsingWpmaterial(Work_WpmaterialActivity.this, results.getResultlist(), workOrder.WONUM);
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if ((wpmaterials == null || wpmaterials.isEmpty())&&page==1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        initAdapter(new ArrayList<Wpmaterial>());
                    } else {

                        if (wpmaterials != null || wpmaterials.size() != 0) {
                            if (wpmaterialList == null){
                                wpmaterialList = new ArrayList<Wpmaterial>();
                            }
                            for (int i = 0; i < wpmaterials.size(); i++) {
                                wpmaterialList.add(wpmaterials.get(i));
                            }
                        }
                        new WpmaterialDao(Work_WpmaterialActivity.this).create(wpmaterials);
                        nodatalayout.setVisibility(View.GONE);

                        initAdapter(wpmaterialList);
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
    private void initAdapter(final List<Wpmaterial> list) {
        wpmaterialAdapter = new WpmaterialAdapter(Work_WpmaterialActivity.this, R.layout.list_item, list);
        recyclerView.setAdapter(wpmaterialAdapter);
        wpmaterialAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("wpmaterial", list.get(position));
                bundle.putSerializable("workOrder", workOrder);
                bundle.putSerializable("position", position);
                intent.setClass(Work_WpmaterialActivity.this, WpmaterialDetailsActivity.class);
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
                final NormalDialog dialog = new NormalDialog(Work_WpmaterialActivity.this);
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
                                Work_WpmaterialActivity.this.finish();
//                                dialog.dismiss();
                            }
                        });
            } else {
                Work_WpmaterialActivity.this.finish();
            }
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent();
            intent.setClass(Work_WpmaterialActivity.this, WpmaterialAddNewActivity.class);
            startActivityForResult(intent, 1);
        }
    };

    private void setNodataLayout() {
        if (wpmaterialAdapter!=null&&wpmaterialAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("wpmaterialList",getList());
            Work_WpmaterialActivity.this.setResult(2000, intent);
            Work_WpmaterialActivity.this.finish();
        }
    };

    private ArrayList<Wpmaterial> getList(){
        ArrayList<Wpmaterial> list = new ArrayList<>();
        if(wpmaterialAdapter.getData().size()!=0) {
            list.addAll(wpmaterialAdapter.getData());
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
                    Wpmaterial wpmaterial = (Wpmaterial) data.getSerializableExtra("wpmaterial");
                    wpmaterialAdapter.add(wpmaterial);
                    initAdapter(wpmaterialAdapter.getData());
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    Wpmaterial wpmaterial = (Wpmaterial) data.getSerializableExtra("wpmaterial");
                    int position = data.getIntExtra("position", 0);
                    wpmaterialAdapter.set(position, wpmaterial);
                    initAdapter(wpmaterialAdapter.getData());
                    wpmaterialAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    wpmaterialAdapter.remove(position);
                    initAdapter(wpmaterialAdapter.getData());
                    wpmaterialAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    Wpmaterial wpmaterial = (Wpmaterial) data.getSerializableExtra("wpmaterial");
                    int position = data.getIntExtra("position", 0);
                    deleteList.add(wpmaterial);
                    wpmaterialAdapter.remove(position);
                    initAdapter(wpmaterialAdapter.getData());
                    wpmaterialAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!workOrder.isnew&& (wpmaterialList == null || wpmaterialList.size() == 0)) {
            page = 1;
            getdata();
        }else {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void onLoad() {
        if (!workOrder.isnew) {
            if (wpmaterialList.size() <= 20) {
                page = 1;
            } else {
                page = wpmaterialList.size() / 20 + 1;
            }
            page++;
            getdata();
        }else {
            refresh_layout.setLoading(false);
        }
    }
}
