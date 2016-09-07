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
import com.example.admin.mingyang_object.model.Udstock;
import com.example.admin.mingyang_object.model.Udstockline;
import com.example.admin.mingyang_object.ui.adapter.BaseQuickAdapter;
import com.example.admin.mingyang_object.ui.adapter.UdstocklineAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 库存盘点行列表页面
 * Created by think on 2016/9/5.
 */
public class UdstocklineListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private UdstocklineAdapter udstocklineAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private EditText search;
    private String searchText = "";
    private int page = 1;
    public Udstock udstock;
    public ArrayList<Udstockline> udstockArrayList = new ArrayList<>();
//    public ArrayList<Udrunliner> deleteList = new ArrayList<>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private LinearLayout confirmlayout;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udstocklinelist);

        getData();
        findViewById();
        initView();
    }

    private void getData() {
        udstock = (Udstock) getIntent().getSerializableExtra("Udstock");
        udstockArrayList = (ArrayList<Udstockline>) getIntent().getSerializableExtra("UdstocklineList");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
        confirmlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirmBtn = (Button) findViewById(R.id.confirm);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        backImageView.setOnClickListener(backOnClickListener);
        titleTextView.setText(getResources().getString(R.string.udstocklinelist_text));
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(UdstocklineListActivity.this);
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
        if (udstockArrayList == null || udstockArrayList.size() == 0) {
            refresh_layout.setRefreshing(true);
            getdata(searchText,page);
        } else {
            if (udstockArrayList != null && udstockArrayList.size() != 0) {
                initAdapter(udstockArrayList);
//                woactivityAdapter.addData(woactivityList);
            } else {
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
                                    UdstocklineListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    udstocklineAdapter.removeAll(udstockArrayList);
                    udstockArrayList = new ArrayList<Udstockline>();
                    getdata(searchText, 1);
                    return true;
                }
                return false;
            }
        });
    }

    private void getdata(String searchText, final int page) {
            HttpManager.getDataPagingInfo(UdstocklineListActivity.this, HttpManager.getudstocklineurl(searchText,udstock.getLOCATION(), udstock.getSTOCKNUM(), page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<Udstockline> udrunliners = null;
                    if (currentPage == page) {
                        udrunliners = JsonUtils.parsingUdstockline(UdstocklineListActivity.this, results.getResultlist());
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if ((udrunliners == null || udrunliners.isEmpty()) && page == 1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        initAdapter(new ArrayList<Udstockline>());
                    } else {

                        if (udrunliners != null && udrunliners.size() != 0) {
                            for (int i = 0; i < udrunliners.size(); i++) {
                                udstockArrayList.add(udrunliners.get(i));
                            }
                            if (page == 1) {
                                initAdapter(udstockArrayList);
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
    }

    /**
     * 获取数据*
     */
    private void initAdapter(final List<Udstockline> list) {
        udstocklineAdapter = new UdstocklineAdapter(UdstocklineListActivity.this, R.layout.list_item, list);
        recyclerView.setAdapter(udstocklineAdapter);
        udstocklineAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("udstockline", list.get(position));
                bundle.putSerializable("udstock", udstock);
                bundle.putSerializable("position", position);
                intent.setClass(UdstocklineListActivity.this, Udstockline_DetailActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

    /**
     * 添加数据数据*
     */
    private void addAdapter(final List<Udstockline> list) {
        udstocklineAdapter.addData(list);
        udstocklineAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("udstockline", udstockArrayList.get(position));
                bundle.putSerializable("udstock", udstock);
                bundle.putSerializable("position", position);
                intent.setClass(UdstocklineListActivity.this, Udstockline_DetailActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (confirmlayout.getVisibility() == View.VISIBLE) {
                final NormalDialog dialog = new NormalDialog(UdstocklineListActivity.this);
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
                                UdstocklineListActivity.this.finish();
//                                dialog.dismiss();
                            }
                        });
            } else {
                UdstocklineListActivity.this.finish();
            }
        }
    };

    private void setNodataLayout() {
        if (udstocklineAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("UdstocklineList", udstockArrayList);
            UdstocklineListActivity.this.setResult(1000, intent);
            UdstocklineListActivity.this.finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
//            case 1://新增
//                if (data != null) {
//                    Udrunliner udrunliner = (Udrunliner) data.getSerializableExtra("udrunliner");
//                    udstocklineAdapter.add(udrunliner);
//                    initAdapter(udstocklineAdapter.getData());
//                    nodatalayout.setVisibility(View.GONE);
//                }
//                confirmlayout.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
            case 2://修改
                if (data != null) {
                    Udstockline udstockline = (Udstockline) data.getSerializableExtra("udrunliner");
                    int position = data.getIntExtra("position", 0);
                    udstocklineAdapter.set(position, udstockline);
                    initAdapter(udstocklineAdapter.getData());
                    udstocklineAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
//            case 3://本地任务删除
//                if (data != null) {
//                    int position = data.getIntExtra("position", 0);
//                    udstocklineAdapter.remove(position);
//                    initAdapter(udstocklineAdapter.getData());
//                    udstocklineAdapter.notifyDataSetChanged();
//                }
//                confirmlayout.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
//            case 4://服务器任务删除操作
//                if (data != null) {
//                    Udrunliner udrunliner = (Udrunliner) data.getSerializableExtra("udrunliner");
//                    int position = data.getIntExtra("position", 0);
//                    deleteList.add(udrunliner);
//                    udstocklineAdapter.remove(position);
//                    initAdapter(udstocklineAdapter.getData());
//                    udstocklineAdapter.notifyDataSetChanged();
//                }
//                confirmlayout.setVisibility(View.VISIBLE);
//                setNodataLayout();
//                break;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        udstocklineAdapter.removeAll(udstockArrayList);
        udstockArrayList = new ArrayList<Udstockline>();
        getdata(searchText, page);
    }

    @Override
    public void onLoad() {
        page++;
        getdata(searchText,page);
    }
}
