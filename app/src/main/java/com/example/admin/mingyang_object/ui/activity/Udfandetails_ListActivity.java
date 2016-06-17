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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdfandetailsAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdproAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/10/27.
 * 风机型号子表
 */
public class Udfandetails_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "Udfandetails_ListActivity";
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
    private UdfandetailsAdapter udfandetailsAdapter;
    private SwipeRefreshLayout refresh_layout = null;

    private EditText search;

    private String searchText = "";
    private int page = 1;

    ArrayList<Udfandetails> items = new ArrayList<Udfandetails>();

    /**
     * 台账编号*
     */
    private String pronum;
    /**
     * 站点*
     */
    private String siteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklist);
        initData();
        findViewById();
        initView();
    }

    private void initData() {
        pronum = getIntent().getExtras().getString("pronum");
        siteid = getIntent().getExtras().getString("siteid");

        Log.i(TAG, "pronum=" + pronum + "siteid=" + siteid);
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
        search.setVisibility(View.GONE);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.udfandetails_text);
        backImageView.setOnClickListener(backImageViewOnClickListener);
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

        getData(searchText);

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


    private void getData(String search) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdfandetailsurl(search, pronum, siteid, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                Log.i(TAG, "results=" + results.getResultlist());

                ArrayList<Udfandetails> item = JsonUtils.parsingUdfandetails(Udfandetails_ListActivity.this, results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {

                    if (item != null || item.size() != 0) {
                        for (int i = 0; i < item.size(); i++) {
                            items.add(item.get(i));
                        }
                    }
                    nodatalayout.setVisibility(View.GONE);

                    initAdapter(items);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
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
                                    Udfandetails_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    udfandetailsAdapter.removeAll(items);
                    items = new ArrayList<Udfandetails>();
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Udfandetails> list) {
        udfandetailsAdapter = new UdfandetailsAdapter(Udfandetails_ListActivity.this, R.layout.list_item, list);
        recyclerView.setAdapter(udfandetailsAdapter);
        udfandetailsAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(Udfandetails_ListActivity.this, Udpro_DetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("udpro", list.get(position));
//                intent.putExtras(bundle);
//                startActivityForResult(intent, 0);
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
    public void onLoad() {
        page++;
        getData(searchText);
    }
}
