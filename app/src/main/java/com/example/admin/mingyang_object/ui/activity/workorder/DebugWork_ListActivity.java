package com.example.admin.mingyang_object.ui.activity.workorder;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.DebugWorkListAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.example.admin.mingyang_object.utils.WorkTypeUtils;

import java.util.ArrayList;


/**
 * Created by think on 2015/10/27.
 * 调试工单列表界面
 */
public class DebugWork_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    private static String TAG = "Work_ListActivity";

    private TextView titlename;
    private ImageView addimg;
    private RelativeLayout backlayout;
    private String worktype;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private DebugWorkListAdapter workListAdapter;
    private SwipeRefreshLayout refresh_layout = null;
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
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(WorkTypeUtils.getTitle(worktype));
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DebugWork_ListActivity.this,DebugWork_AddNewActivity.class);
                intent.putExtra("worktype",worktype);
                startActivity(intent);
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
        workListAdapter = new DebugWorkListAdapter(this);
        recyclerView.setAdapter(workListAdapter);
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        Log.e("调试工单","调试工单21");
        getData(searchText);
        Log.e("调试工单","调试工单22");
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getData(String search){
        HttpManager.getDataPagingInfo(this, HttpManager.getworkorderUrl(worktype,"全部", search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
               // Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                Log.e("调试工单","调试工单11");
                if (results.getResultlist()!=null) {
                    Log.e("调试工单","调试工单12");
                    ArrayList<DebugWorkOrder> items = JsonUtils.parsingDebugWorkOrder(DebugWork_ListActivity.this, results.getResultlist());
                    Log.e("调试工单","调试工单13");
                    refresh_layout.setRefreshing(false);
                    Log.e("调试工单","调试工单14");
                    refresh_layout.setLoading(false);
                    Log.e("调试工单","调试工单15");
                    if (items == null || items.isEmpty()) {
                        Log.e("调试工单","调试工单16");
                        nodatalayout.setVisibility(View.VISIBLE);
                        Log.e("调试工单","调试工单17");
                    } else {
                        Log.e("调试工单","调试工单18");
                        if (page == 1) {
                            Log.e("调试工单","调试工单19");
                            workListAdapter = new DebugWorkListAdapter(DebugWork_ListActivity.this);
                            Log.e("调试工单","调试工单20");
                            recyclerView.setAdapter(workListAdapter);
                            Log.e("调试工单","调试工单21");
                        }
                        if (totalPages == page) {
                            Log.e("调试工单","调试工单11");
                            workListAdapter.adddate(items);
                        }
                    }

                }else {
                    Log.e("调试工单","调试工单11");
                    refresh_layout.setRefreshing(false);
                    nodatalayout.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
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
                                    DebugWork_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    workListAdapter = new DebugWorkListAdapter(DebugWork_ListActivity.this);
                    recyclerView.setAdapter(workListAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(search.getText().toString());
    }

    @Override
    public void onLoad(){
        page++;
        getData(searchText);
    }
}
