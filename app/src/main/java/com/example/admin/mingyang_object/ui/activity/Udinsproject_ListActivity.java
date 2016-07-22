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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.dao.UdinsprojectDao;
import com.example.admin.mingyang_object.model.Udinspo;
import com.example.admin.mingyang_object.model.Udinsproject;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdinspoAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdinsprojectAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/10/27.
 * 巡检项目
 */
public class Udinsproject_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "Udinsproject_ListActivity";
    /**
     * 编号*
     */
    private TextView titlename;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;


    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UdinsprojectAdapter udinsprojectAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private EditText search;
    private String searchText = "";
    private int page = 1;

    public ArrayList<Udinsproject> udinsprojectList = new ArrayList<>();

    private Udinspo udinspo;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private LinearLayout confirmlayout;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udinspo = (Udinspo) getIntent().getExtras().getSerializable("udinspo");
        udinsprojectList = (ArrayList<Udinsproject>) getIntent().getSerializableExtra("udinsprojectList");
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);

        confirmlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirmBtn = (Button) findViewById(R.id.confirm);
    }

    @Override
    protected void initView() {
//        setSearchEdit();
        titlename.setText(R.string.udinsproject_text);
        backImageView.setOnClickListener(backImageViewOnClickListener);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        if (udinspo.id==0) {
            if (udinsprojectList == null || udinsprojectList.size() == 0) {
                getData();
            } else {
                initAdapter(udinsprojectList);
                refresh_layout.setRefreshing(false);
            }
        }else {//本地历史记录
            if (udinsprojectList != null && udinsprojectList.size() != 0) {
                initAdapter(udinsprojectList);
                refresh_layout.setRefreshing(false);
//                woactivityAdapter.addData(woactivityList);
            } else {
                initAdapter(new ArrayList<Udinsproject>());
                nodatalayout.setVisibility(View.VISIBLE);
                refresh_layout.setRefreshing(false);
            }
        }
        setNodataLayout();

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

    }

    /**
     * 返回事件*
     */
    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    private void getData() {
        HttpManager.getDataPagingInfo(this, HttpManager.getudinsprojecturl(udinspo.getINSPONUM(), page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {

                ArrayList<Udinsproject> item = JsonUtils.parsingUdinsproject(Udinsproject_ListActivity.this, results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {

                    if (item != null || item.size() != 0) {
                        if (page == 1) {
                            udinsprojectList = new ArrayList<Udinsproject>();
                            initAdapter(new ArrayList<Udinsproject>());
                        }
                        for (int i = 0; i < item.size(); i++) {
                            udinsprojectList.add(item.get(i));
                            item.get(i).setINSPONUM(udinspo.getINSPONUM());
                        }
                    }
                    new UdinsprojectDao(Udinsproject_ListActivity.this).create(item);
                    nodatalayout.setVisibility(View.GONE);

                    initAdapter(udinsprojectList);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }

//    private void setSearchEdit() {
//        SpannableString msp = new SpannableString("XX搜索");
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_search);
//        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        search.setHint(msp);
//        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    // 先隐藏键盘
//                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
//                            .hideSoftInputFromWindow(
//                                    Udinsproject_ListActivity.this.getCurrentFocus()
//                                            .getWindowToken(),
//                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                    searchText = search.getText().toString().trim();
//                    udinsprojectAdapter.removeAll(udinsprojectList);
//                    udinsprojectList = new ArrayList<Udinsproject>();
//                    getData(searchText);
//                    return true;
//                }
//                return false;
//            }
//        });
//    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Udinsproject> list) {
        udinsprojectAdapter = new UdinsprojectAdapter(Udinsproject_ListActivity.this, R.layout.list_item_2, list);
        recyclerView.setAdapter(udinsprojectAdapter);
        udinsprojectAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udinsproject_ListActivity.this, Udinsproject_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udinsproject", list.get(position));
                bundle.putSerializable("udinspo", udinspo);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addList(final List<Udinsproject> list) {
        udinsprojectAdapter.addData(list);
        udinsprojectAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udinsproject_ListActivity.this, Udinsproject_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udinsproject", udinsprojectList.get(position));
                bundle.putSerializable("udinspo", udinspo);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("udinsprojectList", getList());
            Udinsproject_ListActivity.this.setResult(1000, intent);
            Udinsproject_ListActivity.this.finish();
        }
    };

    private void setNodataLayout() {
        if (udinsprojectAdapter == null){
            nodatalayout.setVisibility(View.GONE);
            return;
        }
        if (udinsprojectAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private ArrayList<Udinsproject> getList() {
        ArrayList<Udinsproject> list = new ArrayList<>();
        if (udinsprojectAdapter.getData().size() != 0) {
            list.addAll(udinsprojectAdapter.getData());
        }
        return list;
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        if (udinsprojectList == null || udinsprojectAdapter.getItemCount() == 0) {
            udinsprojectAdapter.removeAll(udinsprojectList);
            getData();
        } else {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void onLoad() {
        if (udinsprojectList.size() <= 20) {
            page = 1;
        } else {
            page = udinsprojectList.size() / 20 + 1;
        }
        page++;
        getData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 2://修改
                if (data != null) {
                    Udinsproject udinsproject = (Udinsproject) data.getSerializableExtra("udinsproject");
                    int position = data.getIntExtra("position", 0);
                    udinsprojectAdapter.set(position, udinsproject);
                    initAdapter(udinsprojectAdapter.getData());
                    udinsprojectAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }
}
