package com.example.admin.eam.ui.activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.bean.Results;
import com.example.admin.eam.model.Udrunlogr;
import com.example.admin.eam.ui.adapter.BaseQuickAdapter;
import com.example.admin.eam.ui.adapter.UdrunlogrAdapter;
import com.example.admin.eam.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/10/27.
 * 运行记录
 */
public class Udrunlogr_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "Udrunlogr_ListActivity";
    /**
     * 编号*
     */
    private TextView titlename;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    private ImageView addimg;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UdrunlogrAdapter udrunlogrAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private EditText search;
    private String searchText = "";
    private int page = 1;

    ArrayList<Udrunlogr> items = new ArrayList<Udrunlogr>();

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
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        addimg = (ImageView) findViewById(R.id.title_add);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.udrunlogr_text);
        backImageView.setOnClickListener(backImageViewOnClickListener);
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Udrunlogr_ListActivity.this,Udrunlogr_AddNewActivity.class);
                startActivityForResult(intent,0);
            }
        });
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        getData(searchText,1);

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


    private void getData(String search, final int page) {
//        if (!AccountUtils.getIsOffLine(Udrunlogr_ListActivity.this)&&!status.equals("本地记录")) {
        HttpManager.getDataPagingInfo(this, HttpManager.getudrunlogrurl(search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {

                ArrayList<Udrunlogr> item = JsonUtils.parsingUdrunlogr(Udrunlogr_ListActivity.this, results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {
                    if (totalPages == page) {
                        if (item != null || item.size() != 0) {
                            if (page == 1) {
                                items = new ArrayList<Udrunlogr>();
                                initAdapter(new ArrayList<Udrunlogr>());
                            }
                            for (int i = 0; i < item.size(); i++) {
                                items.add(item.get(i));
                            }
                            addAdapter(item);
                        }
                        nodatalayout.setVisibility(View.GONE);
//                        initAdapter(items);
                    }
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
//        }else if (AccountUtils.getIsOffLine(Udrunlogr_ListActivity.this)&&!status.equals("本地记录")){//本地保存记录
//            refresh_layout.setRefreshing(false);
//            refresh_layout.setLoading(false);
//            if (search.equals("")) {
//                items = (ArrayList<Udinspo>) new UdinspoDao(Udrunlogr_ListActivity.this).queryForAllByNum(status);
//            }else {
//                items = (ArrayList<Udinspo>) new UdinspoDao(Udrunlogr_ListActivity.this).queryForAllByNum(search,status);
//            }
//            initAdapter(items);
//            if (udinspoAdapter.getItemCount()==0){
//                nodatalayout.setVisibility(View.VISIBLE);
//            }
//        }else if (status.equals("本地记录")){//本地修改保存的记录
//            refresh_layout.setRefreshing(false);
//            refresh_layout.setLoading(false);
//            items = (ArrayList<Udinspo>) new UdinspoDao(Udrunlogr_ListActivity.this).
//                    queryForLoc(search, AccountUtils.getpersonId(Udrunlogr_ListActivity.this));
//            initAdapter(new UdinspoDao(Udrunlogr_ListActivity.this).
//                    queryForLoc(search, AccountUtils.getpersonId(Udrunlogr_ListActivity.this)));
//            if (udinspoAdapter.getItemCount()==0){
//                nodatalayout.setVisibility(View.VISIBLE);
//            }
//        }
    }

    private void setSearchEdit() {
        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        search.setHint(msp);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Udrunlogr_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    udrunlogrAdapter.removeAll(items);
                    items = new ArrayList<Udrunlogr>();
                    getData(searchText, 1);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 获取数据*
     */
    private void initAdapter(final List<Udrunlogr> list) {
        udrunlogrAdapter = new UdrunlogrAdapter(Udrunlogr_ListActivity.this, R.layout.list_item, list);
        recyclerView.setAdapter(udrunlogrAdapter);
        udrunlogrAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udrunlogr_ListActivity.this, Udrunlogr_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udrunlogr", items.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 获取数据*
     */
    private void addAdapter(final List<Udrunlogr> list) {
        udrunlogrAdapter.addData(list);
        udrunlogrAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udrunlogr_ListActivity.this, Udrunlogr_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udrunlogr", items.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
//        page = 1;
        getData(search.getText().toString(), 1);
    }

    @Override
    public void onLoad() {
        page++;
        getData(searchText,page);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 100:
                refresh_layout.setRefreshing(true);
                getData(search.getText().toString(), 1);
                break;
        }
    }
}
