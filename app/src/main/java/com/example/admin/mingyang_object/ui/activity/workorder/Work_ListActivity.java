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
import android.widget.AdapterView;
import android.widget.Button;
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
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.dao.WorkOrderDao;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.adapter.WorkListAdapter;
import com.example.admin.mingyang_object.ui.widget.SwipeRefreshLayout;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.WorkTypeUtils;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * Created by think on 2015/10/27.
 * 工单列表界面
 */
public class Work_ListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    private static String TAG = "Work_ListActivity";

    private TextView titlename;
    private ImageView addimg;
    private Button choose;
    private RelativeLayout backlayout;

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

        getIntentData();

        initView();

    }

    private void getIntentData(){
        worktype = getIntent().getStringExtra("worktype");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        choose = (Button) findViewById(R.id.title_choose);
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
        choose.setOnClickListener(new NormalListDialogOnClickListener(choose));
        if (worktype.equals(Constants.FR)||worktype.equals(Constants.AA)||worktype.equals(Constants.WS)) {
            addimg.setVisibility(View.VISIBLE);
        }
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Work_ListActivity.this, Work_AddNewActivity.class);
                intent.putExtra("worktype", worktype);
                startActivityForResult(intent,0);
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

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//
//        page = 1;
//        refresh_layout.setRefreshing(true);
//        getData(search.getText().toString(),status);
//    }

    private void getData(String search,String status){
        if (!AccountUtils.getIsOffLine(Work_ListActivity.this)&&!status.equals("本地记录")) {
            HttpManager.getDataPagingInfo(this, HttpManager.getworkorderUrl(worktype, status, search, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int totalPages, int currentPage) {
                    if (results.getResultlist() != null) {
                        ArrayList<WorkOrder> items = JsonUtils.parsingWorkOrder(Work_ListActivity.this, results.getResultlist());
                        refresh_layout.setRefreshing(false);
                        refresh_layout.setLoading(false);
                        if (items == null || items.isEmpty()) {
                            nodatalayout.setVisibility(View.VISIBLE);
                        } else {
                            nodatalayout.setVisibility(View.GONE);
                            new WorkOrderDao(Work_ListActivity.this).update(items);
                            if (page == 1) {
                                workListAdapter = new WorkListAdapter(Work_ListActivity.this, worktype);
                                recyclerView.setAdapter(workListAdapter);
                            }
                            if (totalPages == page) {
                                workListAdapter.adddate(items);
                            }
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
        }else if (AccountUtils.getIsOffLine(Work_ListActivity.this)&&!status.equals("本地记录")){//本地保存记录
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
            workListAdapter = new WorkListAdapter(Work_ListActivity.this, worktype);
            recyclerView.setAdapter(workListAdapter);
            if (search.equals("")) {
                workListAdapter.adddate((ArrayList<WorkOrder>) new WorkOrderDao(Work_ListActivity.this).queryByType(worktype,status));
            }else {
                workListAdapter.adddate((ArrayList<WorkOrder>) new WorkOrderDao(Work_ListActivity.this).queryByType2(worktype, search, status));
            }
            if (workListAdapter.getItemCount()==0){
                nodatalayout.setVisibility(View.VISIBLE);
            }
        }else if (status.equals("本地记录")){//本地修改保存的记录

            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
            workListAdapter = new WorkListAdapter(Work_ListActivity.this, worktype);
            recyclerView.setAdapter(workListAdapter);
            workListAdapter.adddate((ArrayList<WorkOrder>) new WorkOrderDao(Work_ListActivity.this).
                    queryForLoc(worktype, search, AccountUtils.getpersonId(Work_ListActivity.this)));
            if (workListAdapter.getItemCount()==0){
                nodatalayout.setVisibility(View.VISIBLE);
            }

        }
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
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Work_ListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    workListAdapter = new WorkListAdapter(Work_ListActivity.this, worktype);
                    recyclerView.setAdapter(workListAdapter);
                    getData(searchText, status);
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
        if (worktype.equals(Constants.FR)) {//故障工单
            types = getResources().getStringArray(R.array.gz_status_array);
        } else if (worktype.equals(Constants.AA)) {//终验收工单
            types = getResources().getStringArray(R.array.zys_status_array);
        } else if (worktype.equals(Constants.SP)) {//排查工单
            types = getResources().getStringArray(R.array.pc_status_array);
        } else if (worktype.equals(Constants.TP)) {//技改工单
            types = getResources().getStringArray(R.array.jg_status_array);
        }else if (worktype.equals(Constants.WS)) {//定检工单
            types = getResources().getStringArray(R.array.dj_status_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Work_ListActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(mMenuItems.get(position).mOperName);
                workListAdapter = new WorkListAdapter(Work_ListActivity.this,worktype);
                recyclerView.setAdapter(workListAdapter);
                status = mMenuItems.get(position).mOperName;
                choose.setText(status);
                getData(search.getText().toString(), status);
                dialog.dismiss();
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(search.getText().toString(), status);
    }

    @Override
    public void onLoad(){
        page++;
        getData(searchText,status);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 100:
                page = 1;
                getData(search.getText().toString(), status);
                break;
        }
    }
}
