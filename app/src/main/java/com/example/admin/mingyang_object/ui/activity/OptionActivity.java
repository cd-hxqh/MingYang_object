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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.JobPlan;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Person;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.ui.adapter.OptionAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2016/5/16.
 * 通用选项查询界面
 */
public class OptionActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "OptionActivity";
    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImage;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    /**
     * 暂无数据*
     */
    private LinearLayout nodatalayout;
    /**
     * 同步数据*
     */
    private Button freshbtn;
    private OptionAdapter optionAdapter;
    private EditText search;
    private String searchText = "";
    public int optiontype;
    public String parent;

    private String CraftSearch;

    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
        optiontype = getIntent().getIntExtra("optiontype",0);
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        freshbtn = (Button) findViewById(R.id.fresh_btn);
        search = (EditText) findViewById(R.id.search_edit);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_option);

        backImage.setOnClickListener(new View.OnClickListener() {
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
        optionAdapter = new OptionAdapter(this);
        recyclerView.setAdapter(optionAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(false);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
        setSearchEdit();
        getData(searchText);

        freshbtn.setOnClickListener(freshbtnbtnOnClickListener);
    }

    /**
     * 同步数据*
     */
    private View.OnClickListener freshbtnbtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent = new Intent();
//            intent.setClass(OptionActivity.this, DownloadActivity.class);
//            startActivityForResult(intent, 1000);
        }
    };


    @Override
    protected void onActivityResult(int requestCode1, int resultCode, Intent data) {
        super.onActivityResult(requestCode1, resultCode, data);
        switch (requestCode1) {
            case 1000:
                Log.i(TAG, "requestCode=" + requestCode1);
                getData(searchText);
                break;
        }
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
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    OptionActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    optionAdapter = new OptionAdapter(OptionActivity.this);
                    recyclerView.setAdapter(optionAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    private String getUrl(String searchText) {
        if (optiontype == Constants.PERSONCODE) {
            return HttpManager.getPersonUrl(searchText, page, 20);
        }else if (optiontype == Constants.WS_JOBPLANCODE){
            return HttpManager.getJobplanUrl(searchText, page, 20, "定检标准");
        }else if (optiontype == Constants.UDPROCODE){
            return HttpManager.getUdprourl2(searchText, page, 20);
        }else if (optiontype == Constants.UDLOCNUMCODE){
            return HttpManager.getUdfandetailsurl(searchText, getIntent().getStringExtra("udprojectnum"),page, 20);
        }
        return "";
    }

    private void getData(String searchText) {
        HttpManager.getDataPagingInfo(this, getUrl(searchText), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                if (results.getResultlist() != null) {
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (page == 1) {
                        optionAdapter = new OptionAdapter(OptionActivity.this);
                        recyclerView.setAdapter(optionAdapter);
                    }
                    if (optiontype == Constants.PERSONCODE) {//
                        ArrayList<Person> items = JsonUtils.parsingPerson(results.getResultlist());
                        if (totalPages == page) {
                            optionAdapter.addPersonDate(items);
                        }
                    }else if (optiontype == Constants.WS_JOBPLANCODE){//
                        ArrayList<JobPlan> items = JsonUtils.parsingJobPlan(results.getResultlist());
                        if (totalPages == page) {
                            optionAdapter.addJobPlanDate(items);
                        }
                    }else if (optiontype == Constants.UDPROCODE){//
                        ArrayList<Udpro> items = JsonUtils.parsingUdpro(OptionActivity.this, results.getResultlist());
                        if (totalPages == page) {
                            optionAdapter.addUdproDate(items);
                        }
                    }else if (optiontype == Constants.UDLOCNUMCODE){//
                        ArrayList<Udfandetails> items = JsonUtils.parsingUdfandetails(OptionActivity.this, results.getResultlist());
                        if (totalPages == page) {
                            optionAdapter.addUdfandetailsDate(items);
                        }
                    }
                    if (optionAdapter.getItemCount() == 0) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    }
                } else {
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

    public void responseData(Option option) {
        Intent intent = getIntent();
        intent.putExtra("option", option);
        OptionActivity.this.setResult(optiontype, intent);
        finish();
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(searchText);
    }

    //上拉加载
    @Override
    public void onLoad() {
        page++;
        getData(searchText);
    }
}
