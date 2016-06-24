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
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.example.admin.mingyang_object.model.UddebugWorkOrderLine;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UddebugWorkOrderLineAdapter;
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
public class DebugWork_UddebugWorkOrderLineActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UddebugWorkOrderLineAdapter woactivityAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    public DebugWorkOrder workOrder;
    public ArrayList<UddebugWorkOrderLine> woactivityList = new ArrayList<>();
    public ArrayList<UddebugWorkOrderLine> deleteList = new ArrayList<>();

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
        workOrder = (DebugWorkOrder) getIntent().getSerializableExtra("debugworkOrder");
        woactivityList = (ArrayList<UddebugWorkOrderLine>) getIntent().getSerializableExtra("uddebugWorkOrderLines");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
//        confirmlayout = (LinearLayout) findViewById(R.id.confirm_layout);
//        confirmBtn = (Button) findViewById(R.id.ok);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backOnClickListener);
        titleTextView.setText(getResources().getString(R.string.title_activity_uddebugworkorderlinelist));
        menuImageView.setImageResource(R.mipmap.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
//        confirmlayout.setVisibility(View.GONE);
//        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(DebugWork_UddebugWorkOrderLineActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        woactivityAdapter = new WoactivityAdapter(Work_WoactivityActivity.this);
        recyclerView.setAdapter(woactivityAdapter);

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

        if (!workOrder.isnew && (woactivityList == null || woactivityList.size() == 0)) {
            refresh_layout.setRefreshing(true);
            getdata();
        } else {
            if (woactivityList != null && woactivityList.size() != 0) {
                initAdapter(woactivityList);
//                woactivityAdapter.addData(woactivityList);
            }else {
                nodatalayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getdata() {
        if (workOrder.DEBUGWORKORDERNUM != null && !workOrder.DEBUGWORKORDERNUM.equals("")) {
            HttpManager.getDataPagingInfo(DebugWork_UddebugWorkOrderLineActivity.this, HttpManager.getuddebugworkorderlineUrl(workOrder.DEBUGWORKORDERNUM, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<UddebugWorkOrderLine> woactivities = null;
                    if (currentPage == page) {
                        woactivities = JsonUtils.parsingUddebugWorkOrderLine(DebugWork_UddebugWorkOrderLineActivity.this, results.getResultlist(), workOrder.DEBUGWORKORDERNUM);
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
    private void initAdapter(final List<UddebugWorkOrderLine> list) {
        woactivityAdapter = new UddebugWorkOrderLineAdapter(DebugWork_UddebugWorkOrderLineActivity.this, R.layout.list_item, list);
        recyclerView.setAdapter(woactivityAdapter);
        woactivityAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("uddebugWorkOrderLine", list.get(position));
                bundle.putSerializable("workOrder", workOrder);
                bundle.putSerializable("position", position);
                    intent.setClass(DebugWork_UddebugWorkOrderLineActivity.this, UddebugWorkOrderLineDetailsActivity.class);

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
//            if (confirmlayout.getVisibility() == View.VISIBLE) {
//                final NormalDialog dialog = new NormalDialog(DebugWork_UddebugWorkOrderLineActivity.this);
//                dialog.content("确定放弃修改吗?")//
//                        .showAnim(mBasIn)//
//                        .dismissAnim(mBasOut)//
//                        .show();
//
//                dialog.setOnBtnClickL(
//                        new OnBtnClickL() {
//                            @Override
//                            public void onBtnClick() {
//                                dialog.dismiss();
//                            }
//                        },
//                        new OnBtnClickL() {
//                            @Override
//                            public void onBtnClick() {
//                                DebugWork_UddebugWorkOrderLineActivity.this.finish();
////                            dialog.dismiss();
//                            }
//                        });
//            } else {
                DebugWork_UddebugWorkOrderLineActivity.this.finish();
//            }
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(DebugWork_UddebugWorkOrderLineActivity.this, UddebugWorkOrderLineAddNewActivity.class);
//            intent.putExtra("taskid", (woactivityAdapter.woactivityList.size() + 1) * 10);
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
            DebugWork_UddebugWorkOrderLineActivity.this.setResult(1000, intent);
            DebugWork_UddebugWorkOrderLineActivity.this.finish();
        }
    };

    private ArrayList<UddebugWorkOrderLine> getList(){
        ArrayList<UddebugWorkOrderLine> list = new ArrayList<>();
        if(woactivityList.size()!=0) {
            list.addAll(woactivityList);
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
                    UddebugWorkOrderLine woactivity = (UddebugWorkOrderLine) data.getSerializableExtra("uddebugWorkOrderLine");
                    woactivityAdapter.add(woactivity);
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    UddebugWorkOrderLine woactivity = (UddebugWorkOrderLine) data.getSerializableExtra("uddebugWorkOrderLine");
                    int position = data.getIntExtra("position", 0);
                    woactivityAdapter.set(position, woactivity);
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    woactivityAdapter.remove(position);
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    UddebugWorkOrderLine woactivity = (UddebugWorkOrderLine) data.getSerializableExtra("uddebugWorkOrderLine");
                    int position = data.getIntExtra("position", 0);
                    deleteList.add(woactivity);
                    woactivityAdapter.remove(position);
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
