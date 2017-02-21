package com.example.admin.eam.ui.activity;

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

import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.bean.Results;
import com.example.admin.eam.model.Udrunliner;
import com.example.admin.eam.model.Udrunlogr;
import com.example.admin.eam.ui.adapter.BaseQuickAdapter;
import com.example.admin.eam.ui.adapter.UdrunlinerAdapter;
import com.example.admin.eam.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 运行日志活动子表列表页面
 * Created by think on 2016/7/6.
 */
public class UdrunlinerListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UdrunlinerAdapter udrunlinerAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    public Udrunlogr udrunlogr;
    public ArrayList<Udrunliner> udrunlinerArrayList = new ArrayList<>();
    public ArrayList<Udrunliner> deleteList = new ArrayList<>();

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
        udrunlogr = (Udrunlogr) getIntent().getSerializableExtra("Udrunlogr");
        udrunlinerArrayList = (ArrayList<Udrunliner>) getIntent().getSerializableExtra("UdrunlinerList");
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
        titleTextView.setText(getResources().getString(R.string.udrunliner_list));
        menuImageView.setImageResource(R.mipmap.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(UdrunlinerListActivity.this);
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

        if (!udrunlogr.isnew) {
            if (udrunlinerArrayList == null || udrunlinerArrayList.size() == 0) {
                refresh_layout.setRefreshing(true);
                getdata();
            } else {
                if (udrunlinerArrayList != null && udrunlinerArrayList.size() != 0) {
                    initList(udrunlinerArrayList);
//                woactivityAdapter.addData(woactivityList);
                } else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        } else {//新建项目日报
            if (udrunlinerArrayList == null || udrunlinerArrayList.size() == 0) {
                initAdapter(new ArrayList<Udrunliner>());
                nodatalayout.setVisibility(View.VISIBLE);
            } else {
                if (udrunlinerArrayList != null && udrunlinerArrayList.size() != 0) {
                    initList(udrunlinerArrayList);
//                woactivityAdapter.addData(woactivityList);
                } else {
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void initList(ArrayList<Udrunliner> list) {
        ArrayList<Udrunliner> woactivities = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).TYPE != null && list.get(i).TYPE.equals("delete")) {
                deleteList.add(list.get(i));
            } else {
                woactivities.add(list.get(i));
            }
        }
        initAdapter(woactivities);
    }

    private void getdata() {
        if (udrunlogr.LOGNUM != null && !udrunlogr.LOGNUM.equals("")) {
            HttpManager.getDataPagingInfo(UdrunlinerListActivity.this, HttpManager.getUdrunlinerUrl(udrunlogr.LOGNUM, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<Udrunliner> udrunliners = null;
                    if (currentPage == page) {
                        udrunliners = JsonUtils.parsingUdrunliner(UdrunlinerListActivity.this, results.getResultlist(), udrunlogr.LOGNUM);
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if ((udrunliners == null || udrunliners.isEmpty()) && page == 1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        initAdapter(new ArrayList<Udrunliner>());
                    } else {

                        if (udrunliners != null && udrunliners.size() != 0) {
                            for (int i = 0; i < udrunliners.size(); i++) {
                                udrunlinerArrayList.add(udrunliners.get(i));
                            }
                            if (page == 1) {
                                initAdapter(udrunlinerArrayList);
                            } else {
                                addAdapter(udrunliners);
                            }
                        }
                        nodatalayout.setVisibility(View.GONE);

//                        initAdapter(udrunlinerArrayList);
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
    private void initAdapter(final List<Udrunliner> list) {
        udrunlinerAdapter = new UdrunlinerAdapter(UdrunlinerListActivity.this, R.layout.list_item_1, list);
        recyclerView.setAdapter(udrunlinerAdapter);
        udrunlinerAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("udrunliner", list.get(position));
                bundle.putSerializable("udrunlogr", udrunlogr);
                bundle.putSerializable("position", position);
                intent.setClass(UdrunlinerListActivity.this, UdrunlinerDetailsActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

    /**
     * 添加数据数据*
     */
    private void addAdapter(final List<Udrunliner> list) {
        udrunlinerAdapter.addData(list);
        udrunlinerAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("udrunliner", udrunlinerArrayList.get(position));
                bundle.putSerializable("udrunlogr", udrunlogr);
                bundle.putSerializable("position", position);
                intent.setClass(UdrunlinerListActivity.this, UdrunlinerDetailsActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (confirmlayout.getVisibility() == View.VISIBLE) {
                final NormalDialog dialog = new NormalDialog(UdrunlinerListActivity.this);
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
                                UdrunlinerListActivity.this.finish();
//                                dialog.dismiss();
                            }
                        });
            } else {
                UdrunlinerListActivity.this.finish();
            }
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent();
            intent.setClass(UdrunlinerListActivity.this, UdrunlinerAddNewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("udrunlogr", udrunlogr);
            bundle.putSerializable("udrunlinerArrayList",udrunlinerArrayList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);
        }
    };

    private void setNodataLayout() {
        if (udrunlinerAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("UdrunlinerList", getList());
            UdrunlinerListActivity.this.setResult(1000, intent);
            UdrunlinerListActivity.this.finish();
        }
    };

    private ArrayList<Udrunliner> getList() {
        ArrayList<Udrunliner> list = new ArrayList<>();
        if (udrunlinerAdapter.getData().size() != 0) {
            list.addAll(udrunlinerAdapter.getData());
        }
        if (deleteList.size() != 0) {
            list.addAll(deleteList);
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1://新增
                if (data != null) {
                    Udrunliner udrunliner = (Udrunliner) data.getSerializableExtra("udrunliner");
                    udrunlinerAdapter.add(udrunliner);
                    initAdapter(udrunlinerAdapter.getData());
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    Udrunliner udrunliner = (Udrunliner) data.getSerializableExtra("udrunliner");
                    int position = data.getIntExtra("position", 0);
                    udrunlinerAdapter.set(position, udrunliner);
                    initAdapter(udrunlinerAdapter.getData());
                    udrunlinerAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    udrunlinerAdapter.remove(position);
                    initAdapter(udrunlinerAdapter.getData());
                    udrunlinerAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    Udrunliner udrunliner = (Udrunliner) data.getSerializableExtra("udrunliner");
                    int position = data.getIntExtra("position", 0);
                    deleteList.add(udrunliner);
                    udrunlinerAdapter.remove(position);
                    initAdapter(udrunlinerAdapter.getData());
                    udrunlinerAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!udrunlogr.isnew && (udrunlinerArrayList == null || udrunlinerArrayList.size() == 0)) {
            page = 1;
            getdata();
        } else {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void onLoad() {
        if (!udrunlogr.isnew) {
            page++;
            getdata();
        } else {
            refresh_layout.setLoading(false);
        }
    }
}
