package com.example.admin.mingyang_object.ui.activity.workorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.WorkListAdapter;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.RefreshUtils;

import java.util.ArrayList;


/**
 * Created by think on 2015/10/27.
 * 工单详情界面
 */
public class Work_ListActivity extends BaseActivity{
    private static String TAG = "Work_ListActivity";

    private TextView titlename;
    private ImageView addimg;
    private RelativeLayout backlayout;
    private String worktype;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private WorkListAdapter workListAdapter;
    // 下拉加载，  下拉刷新控件
    protected RefreshUtils refreshUtils;
    private EditText search;
    private String searchText = "";
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklist);

        findViewById();
        getIntentData();
        initView();
    }

    private void getIntentData(){
        worktype = getIntent().getStringExtra("worktype");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        addimg = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refreshUtils = (RefreshUtils) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.work_list_title);
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshUtils.setLoading(true);
//                Intent intent = new Intent(Work_ListActivity.this,Work_AddNewActivity.class);
//                intent.putExtra("worktype",worktype);
//                startActivity(intent);
//                new AsyncTask<String,String,String>(){
//                    @Override
//                    protected String doInBackground(String... strings) {
//                        new AndroidClientService().InsertWO("");
//                        return null;
//                    }
//                }.execute();
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
        workListAdapter = new WorkListAdapter(this);
        recyclerView.setAdapter(workListAdapter);

        getData(searchText);

        // 注册上拉监听事件
        refreshUtils.setOnLoadListener(new RefreshUtils.OnLoadListener() {
            public void onLoad() {
                page++;
                getData(searchText);
            }
        });

        // 注册下拉监听事件
        refreshUtils.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData(search.getText().toString());
            }
        });

    }

    private void getData(String search){
        HttpManager.getDataPagingInfo(this, HttpManager.getworkorderUrl(worktype, search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<WorkOrder> items = JsonUtils.parsingWorkOrder(Work_ListActivity.this, results.getResultlist(), worktype);
                refreshUtils.setRefreshing(false);
//                refreshUtils.setLoading(false);
                if (items == null || items.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {
                    if (page == 1) {
                        workListAdapter = new WorkListAdapter(Work_ListActivity.this);
                        recyclerView.setAdapter(workListAdapter);
                    }
                    if (totalPages == page) {
                        workListAdapter.adddate(items);
                    }
                }
            }

            @Override
            public void onFailure(String error) {
                refreshUtils.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setSearchEdit(){
        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        search.setHint(msp);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Work_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    workListAdapter = new WorkListAdapter(Work_ListActivity.this);
                    recyclerView.setAdapter(workListAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

//    //下拉刷新触发事件
//    @Override
//    public void onRefresh() {
//        page = 1;
//        getData(search.getText().toString());
//    }
//
//    @Override
//    public void onLoad(){
//        page++;
//        getData(searchText);
//    }
}
