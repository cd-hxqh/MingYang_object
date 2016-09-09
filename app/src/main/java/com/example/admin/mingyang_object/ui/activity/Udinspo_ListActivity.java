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
import android.widget.AdapterView;
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
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.dao.UdinspoDao;
import com.example.admin.mingyang_object.model.Udinspo;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdinspoAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdproAdapter;
import com.example.admin.mingyang_object.ui.adapter.WorkListAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/10/27.
 * 巡检单
 */
public class Udinspo_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "Udinspo_ListActivity";
    /**
     * 编号*
     */
    private TextView titlename;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    private Button choose;
    private String status = "全部";

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UdinspoAdapter udinspoAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private EditText search;
    private String searchText = "";
    private int page = 1;

    ArrayList<Udinspo> items = new ArrayList<Udinspo>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspolist);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        choose = (Button) findViewById(R.id.title_choose);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.udinspo_text);
        backImageView.setOnClickListener(backImageViewOnClickListener);
        choose.setVisibility(View.VISIBLE);

        choose.setOnClickListener(new NormalListDialogOnClickListener(choose));
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

        getData(searchText,status,1);

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


    private void getData(String search,String status, final int page) {
        if (!AccountUtils.getIsOffLine(Udinspo_ListActivity.this)&&!status.equals("本地记录")) {
            HttpManager.getDataPagingInfo(this, HttpManager.getudinspourl(search, status, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int totalPages, int currentPage) {

                    ArrayList<Udinspo> item = JsonUtils.parsingUdinspo(Udinspo_ListActivity.this, results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (item == null || item.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (totalPages == page) {
                            if (item != null || item.size() != 0) {
                                if (page == 1) {
                                    items = new ArrayList<Udinspo>();
                                    initAdapter(new ArrayList<Udinspo>());
                                }
                                for (int i = 0; i < item.size(); i++) {
                                    items.add(item.get(i));
                                }
                                addAdapter(item);
                            }
                            nodatalayout.setVisibility(View.GONE);
                            new UdinspoDao(Udinspo_ListActivity.this).update(item);
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
        }else if (AccountUtils.getIsOffLine(Udinspo_ListActivity.this)&&!status.equals("本地记录")){//本地保存记录
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
            if (search.equals("")) {
                items = (ArrayList<Udinspo>) new UdinspoDao(Udinspo_ListActivity.this).queryForAllByNum(status);
            }else {
                items = (ArrayList<Udinspo>) new UdinspoDao(Udinspo_ListActivity.this).queryForAllByNum(search,status);
            }
            initAdapter(items);
            if (udinspoAdapter.getItemCount()==0){
                nodatalayout.setVisibility(View.VISIBLE);
            }
        }else if (status.equals("本地记录")){//本地修改保存的记录
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
            items = (ArrayList<Udinspo>) new UdinspoDao(Udinspo_ListActivity.this).
                    queryForLoc(search, AccountUtils.getpersonId(Udinspo_ListActivity.this));
            initAdapter(new UdinspoDao(Udinspo_ListActivity.this).
                    queryForLoc(search, AccountUtils.getpersonId(Udinspo_ListActivity.this)));
            if (udinspoAdapter.getItemCount()==0){
                nodatalayout.setVisibility(View.VISIBLE);
            }
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
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Udinspo_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    udinspoAdapter.removeAll(items);
                    items = new ArrayList<Udinspo>();
                    getData(searchText,status,1);
                    return true;
                }
                return false;
            }
        });
    }

    private class NormalListDialogOnClickListener implements View.OnClickListener {
        TextView textView;

        public NormalListDialogOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            NormalListDialog(textView);
        }
    }

    private void NormalListDialog(final TextView textView) {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();
        types = getResources().getStringArray(R.array.udinspos_status_array);

        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udinspo_ListActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(mMenuItems.get(position).mOperName);
                items = new ArrayList<Udinspo>();
                initAdapter(new ArrayList<Udinspo>());
                status = mMenuItems.get(position).mOperName;
                choose.setText(status);
                getData(search.getText().toString(), status,1);
                dialog.dismiss();
            }
        });
    }

    /**
     * 获取数据*
     */
    private void initAdapter(final List<Udinspo> list) {
        udinspoAdapter = new UdinspoAdapter(Udinspo_ListActivity.this, R.layout.list_item, list);
        recyclerView.setAdapter(udinspoAdapter);
        udinspoAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udinspo_ListActivity.this, Udinspo_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udinspo", items.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 获取数据*
     */
    private void addAdapter(final List<Udinspo> list) {
        udinspoAdapter.addData(list);
        udinspoAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Udinspo_ListActivity.this, Udinspo_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("udinspo", items.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
//        page = 1;
        getData(search.getText().toString(), status, 1);
    }

    @Override
    public void onLoad() {
        page++;
        getData(searchText,status,page);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 100:
                refresh_layout.setRefreshing(true);
                getData(search.getText().toString(), status, 1);
                break;
        }
    }
}
