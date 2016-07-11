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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.example.admin.mingyang_object.utils.WorkTypeUtils;

import java.util.ArrayList;


/**
 * Created by think on 2015/10/27.
 * 工单详情界面
 */
public class Work_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    private static String TAG = "Work_ListActivity";

    private TextView titlename;
    private ImageView addimg;
    private TextView choose;
    private PopupWindow popupWindow;
    private RelativeLayout backlayout;

    private LinearLayout status1Linearlayout;
    private LinearLayout status2Linearlayout;
    private LinearLayout status3Linearlayout;
    private LinearLayout status4Linearlayout;
    private LinearLayout statusallLinearlayout;
    private String status = "全部";

    private String worktype;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private WorkListAdapter workListAdapter;
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
        choose = (TextView) findViewById(R.id.title_choose);
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
        choose.setVisibility(View.VISIBLE);
        choose.setOnClickListener(chooseOnClickListener);
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Work_ListActivity.this, Work_AddNewActivity.class);
                intent.putExtra("worktype", worktype);
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
        workListAdapter = new WorkListAdapter(this,worktype);
        recyclerView.setAdapter(workListAdapter);
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        getData(searchText,status);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getData(String search,String status){
        HttpManager.getDataPagingInfo(this, HttpManager.getworkorderUrl(worktype,status, search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                if (results.getResultlist()!=null) {
                    ArrayList<WorkOrder> items = JsonUtils.parsingWorkOrder(Work_ListActivity.this, results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        nodatalayout.setVisibility(View.GONE);
                        if (page == 1) {
                            workListAdapter = new WorkListAdapter(Work_ListActivity.this, worktype);
                            recyclerView.setAdapter(workListAdapter);
                        }
                        if (totalPages == page) {
                            workListAdapter.adddate(items);
                        }
                    }
                }else {
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
                                    Work_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    workListAdapter = new WorkListAdapter(Work_ListActivity.this,worktype);
                    recyclerView.setAdapter(workListAdapter);
                    getData(searchText,status);
                    return true;
                }
                return false;
            }
        });
    }

    private View.OnClickListener chooseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(choose);
        }
    };

    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Work_ListActivity.this).inflate(
                R.layout.status_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        status1Linearlayout = (LinearLayout) contentView.findViewById(R.id.status_1_id);
        status2Linearlayout = (LinearLayout) contentView.findViewById(R.id.status_2_id);
        status3Linearlayout = (LinearLayout) contentView.findViewById(R.id.status_3_id);
        status4Linearlayout = (LinearLayout) contentView.findViewById(R.id.status_4_id);
        statusallLinearlayout = (LinearLayout) contentView.findViewById(R.id.status_all_id);
        status1Linearlayout.setOnClickListener(status1OnClickListener);
        status2Linearlayout.setOnClickListener(status2OnClickListener);
        status3Linearlayout.setOnClickListener(status3OnClickListener);
        status4Linearlayout.setOnClickListener(status4OnClickListener);
        statusallLinearlayout.setOnClickListener(statusallOnClickListener);
    }

    private View.OnClickListener status1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            workListAdapter = new WorkListAdapter(Work_ListActivity.this,worktype);
            recyclerView.setAdapter(workListAdapter);
            status = getString(R.string.status_1);
            choose.setText(status);
            getData(search.getText().toString(), status);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener status2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            workListAdapter = new WorkListAdapter(Work_ListActivity.this,worktype);
            recyclerView.setAdapter(workListAdapter);
            status = getString(R.string.status_2);
            choose.setText(status);
            getData(search.getText().toString(),status);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener status3OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            workListAdapter = new WorkListAdapter(Work_ListActivity.this,worktype);
            recyclerView.setAdapter(workListAdapter);
            status = getString(R.string.status_3);
            choose.setText(status);
            getData(search.getText().toString(),status);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener status4OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            workListAdapter = new WorkListAdapter(Work_ListActivity.this,worktype);
            recyclerView.setAdapter(workListAdapter);
            status = getString(R.string.status_4);
            choose.setText(status);
            getData(search.getText().toString(),status);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener statusallOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            workListAdapter = new WorkListAdapter(Work_ListActivity.this,worktype);
            recyclerView.setAdapter(workListAdapter);
            status = "全部";
            choose.setText(status);
            getData(search.getText().toString(),status);
            popupWindow.dismiss();
        }
    };

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(search.getText().toString(),status);
    }

    @Override
    public void onLoad(){
        page++;
        getData(searchText,status);
    }
}
