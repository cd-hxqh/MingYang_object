package com.example.admin.eam.ui.activity.udpro;

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
import com.example.admin.eam.model.Udfeedback;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.adapter.BaseQuickAdapter;
import com.example.admin.eam.ui.adapter.UdfeedbackAdapter;
import com.example.admin.eam.ui.widget.SwipeRefreshLayout;
import com.example.admin.eam.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/10/27.
 * 问题联络单界面
 */
public class Udfeedback_listactivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "Udfeedback_listactivity";
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
    private UdfeedbackAdapter udfeedbackAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private EditText search;
    private String searchText = "";
    private int page = 1;

    ArrayList<Udfeedback> items = new ArrayList<Udfeedback>();


    private String totalresult; //总共条数
    private String showcount; //显示条数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklist);

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
        titlename.setText(R.string.udfeedback_text);
        backImageView.setOnClickListener(backImageViewOnClickListener);
        addimg.setVisibility(View.VISIBLE);
        addimg.setOnClickListener(addOnClickListener);
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

    private View.OnClickListener addOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udfeedback_listactivity.this,Udfeedback_AddNewActivity.class);
            startActivity(intent);
        }
    };


    private void getData(String search) {
        Log.i(TAG, "page=" + page);
        HttpManager.getDataPagingInfo(this, HttpManager.getUdfeedbacksurl(search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {

                ArrayList<Udfeedback> item = JsonUtils.parsingUdfeedback(Udfeedback_listactivity.this, results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (item == null || item.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {
                    Log.i(TAG, "totalresult=" + results.getTotalresult() + "Showcount=" + results.getShowcount() + "");
                    totalresult = results.getTotalresult();
                    showcount = results.getShowcount() + "";

                    if (item != null || item.size() != 0) {
                        for (int i = 0; i < item.size(); i++) {
                            items.add(item.get(i));
                        }
                        if (page == 1) {
                            initAdapter(items);
                        }else {
                            addList(item);
                        }

                    }
                    nodatalayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
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
                                    Udfeedback_listactivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    udfeedbackAdapter.removeAll(items);
                    items = new ArrayList<Udfeedback>();
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
    private void initAdapter(final List<Udfeedback> list) {
        udfeedbackAdapter = new UdfeedbackAdapter(Udfeedback_listactivity.this, R.layout.list_item, items);
        recyclerView.setAdapter(udfeedbackAdapter);
        udfeedbackAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udfeedback_listactivity.this, Udfeedback_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udfeedback", list.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addList(final List<Udfeedback> list) {
        udfeedbackAdapter.addData(list);
        udfeedbackAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udfeedback_listactivity.this, Udfeedback_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udfeedback", items.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        items = new ArrayList<>();
        initAdapter(new ArrayList<Udfeedback>());
        getData(search.getText().toString());
    }

    @Override
    public void onLoad() {
        page++;
        if (totalresult.equals(showcount)) {
            MessageUtils.showMiddleToast(Udfeedback_listactivity.this, "已加载全部数据");
            refresh_layout.setLoading(false);
        } else {
            getData(searchText);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 100:
                page = 1;
                udfeedbackAdapter.removeAll(items);
                items = new ArrayList<>();
                refresh_layout.setRefreshing(true);
                getData(search.getText().toString());
                break;
        }
    }
}