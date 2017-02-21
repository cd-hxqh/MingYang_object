package com.example.admin.eam.ui.activity.stockQuery;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.bean.Results;
import com.example.admin.eam.model.StockQuery;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.adapter.StockQueryAdapter;
import com.example.admin.eam.ui.widget.SwipeRefreshLayout;
import com.example.admin.eam.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;

import org.json.JSONObject;

import java.util.ArrayList;
//库存查询
public class StockQuery_listActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{


    private TextView titlename;//标题

    private Button choose;//
    private RelativeLayout backlayout;//
    LinearLayoutManager layoutManager;//
    public RecyclerView recyclerView;//
    private LinearLayout nodatalayout;//
    private StockQueryAdapter stockQueryAdapter;//
    private SwipeRefreshLayout refresh_layout = null;//
    private EditText search;//关键字输入框
    private String searchText = "";//关键字
    private int page = 1;//当前页
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private String status = "全部";
    private ArrayList<String> LOCATIONs;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stock_query_list);

        findViewById();

        initView();
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.e("库存查询","onResume");
        queryPermission();
        recyclerView.smoothScrollToPosition(0);
        onRefresh();
    }

    @Override
    protected void findViewById() {

        titlename = (TextView) findViewById(R.id.title_name);
        choose = (Button) findViewById(R.id.title_height);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }
    @Override
    protected void initView() {

        choose.setVisibility(View.VISIBLE);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StockQuery_listActivity.this, ConditionActivity.class);
                intent.putStringArrayListExtra("LOCATIONs",LOCATIONs);
                startActivityForResult(intent, 0);
            }
        });
        titlename.setText("库存查询");
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

        stockQueryAdapter = new StockQueryAdapter(this);
        recyclerView.setAdapter(stockQueryAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
    }

    private void getData(String search){

        Log.e("库存查询","请求地址"+ HttpManager.getStockQueryUrl(search, page, 20));

        HttpManager.getDataPagingInfo(this, HttpManager.getStockQueryUrl(search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.e("库存查询","请求成功");
            }
            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                Log.e("库存查询","请求成功");
                nodatalayout.setVisibility(View.INVISIBLE);

                if (results.getResultlist()!=null) {

                    ArrayList<StockQuery> items = JsonUtils.parsingStockQuery(StockQuery_listActivity.this, results.getResultlist());
                    ArrayList<StockQuery> filterItems = new ArrayList<StockQuery>();
                    if (LOCATIONs==null||LOCATIONs.isEmpty())
                    {
                      filterItems=items;
                    }
                    else
                    {
                        for (StockQuery one:items)
                        {
                            if (LOCATIONs.contains(one.getLOCATION()))
                            {
                                filterItems.add(one);
                            }
                        }
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);

                    if (items == null || items.isEmpty()) {

                        nodatalayout.setVisibility(View.VISIBLE);
                        stockQueryAdapter = new StockQueryAdapter(StockQuery_listActivity.this);
                        recyclerView.setAdapter(stockQueryAdapter);

                    } else {

                        if (page == 1) {

                            stockQueryAdapter = new StockQueryAdapter(StockQuery_listActivity.this);
                            recyclerView.setAdapter(stockQueryAdapter);
                        }
                        if (totalPages == page) {
                            stockQueryAdapter.adddate(filterItems);
                        }
                    }
                }else {
                    refresh_layout.setRefreshing(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(String error) {
                Log.e("库存查询","请求失败");
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }
    private void getConditionData(SharedPreferences sp)
    {
        StringBuilder condition = new StringBuilder("{");

        String tmp1 = sp.getString("ITEMNUM","");
        if (tmp1.length()>0)
        {
            condition.append("'ITEMNUM':'"+tmp1+"',");
        }

        String tmp2 = sp.getString("ITEMDESC","");

        if (tmp2.length()>0)
        {
            condition.append("'ITEMDESC':'"+tmp2+"',");
        }

        String tmp3 = sp.getString("LOCATIONDESC","");

        if (tmp3.length()>0)
        {
            condition.append("'LOCATIONDESC':'"+tmp3+"',");
        }

        String tmp4 = sp.getString("LOCATION","");

        if (tmp4.length()>0)
        {
            condition.append("'LOCATION':'"+tmp4+"',");
        }

        String tmp5 = sp.getString("BINNUM","");

        if (tmp5.length()==0)
        {
            condition.append("'BINNUM':'无限制'");
        }
        else if (tmp5.equals("所有"))
        {

        }
        else
        {
            condition.append("'BINNUM':'"+tmp5+"'");
        }
        condition.append("}");
        Log.e("库存查询","condition" +condition);

        Log.e("库存查询","请求地址"+ HttpManager.getConditonStockQueryUrl(condition.toString(), page, 20));

        HttpManager.getDataPagingInfo(this, HttpManager.getConditonStockQueryUrl(condition.toString(), page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.e("库存查询","请求成功");
            }
            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {

                Log.e("库存查询","请求成功");

                nodatalayout.setVisibility(View.INVISIBLE);

                if (results.getResultlist()!=null) {

                    ArrayList<StockQuery> items = JsonUtils.parsingStockQuery(StockQuery_listActivity.this, results.getResultlist());
                    ArrayList<StockQuery> filterItems = new ArrayList<StockQuery>();
                    if (LOCATIONs==null||LOCATIONs.isEmpty())
                    {
                        filterItems=items;
                    }
                    else
                    {
                        for (StockQuery one:items)
                        {
                            if (LOCATIONs.contains(one.getLOCATION()))
                            {
                                filterItems.add(one);
                            }
                        }
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);

                    if (items == null || items.isEmpty()) {

                        nodatalayout.setVisibility(View.VISIBLE);

                        stockQueryAdapter = new StockQueryAdapter(StockQuery_listActivity.this);
                        recyclerView.setAdapter(stockQueryAdapter);

                    } else {

                        if (page == 1) {

                            stockQueryAdapter = new StockQueryAdapter(StockQuery_listActivity.this);
                            recyclerView.setAdapter(stockQueryAdapter);

                        }
                        if (totalPages == page) {

                            stockQueryAdapter.adddate(filterItems);
                        }
                        stockQueryAdapter.notifyDataSetChanged();
                    }
                }else {
                    refresh_layout.setRefreshing(false);
                    nodatalayout.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(String error) {
                Log.e("库存查询","请求失败");
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }
    public void queryPermission(){

        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {


                String reviseresult = AndroidClientService.permissionWebService(StockQuery_listActivity.this,"chenzb");

                return reviseresult;
            }

            @Override
            protected void onPostExecute(String workResult) {
                super.onPostExecute(workResult);
                if (workResult==null)
                {
                    Log.e("库存查询","没有结果");
                }
                else
                {
                    Log.e("库存查询","结果"+workResult);

                    try
                    {
                        JSONObject jo = new JSONObject(workResult);

                        String location =jo.getString("sqlMsg");

                        if (location.equals("ALL"))
                        {
                            Log.e("库存查询","所有权限");
                        }
                        else
                        {
                            Log.e("库存查询","局部权限");
                            String[] locations = location.split(",");
                            LOCATIONs = new ArrayList<String>(locations.length);
                            for (String one:locations)
                            {
                                LOCATIONs.add(one);
                                Log.e("库存查询","局部权限"+one);
                            }
                        }

                    }
                    catch(Exception e)
                    {
                        Log.e("库存查询","有错误");
                    }
                }
                Log.e("库存查询","查询是否需要高级搜索");

                SharedPreferences sp =getSharedPreferences("conditon", MODE_PRIVATE);

                if (sp.getAll().size()==0)
                {
                    Log.e("库存查询","普通");
                    getData(searchText);
                }
                else
                {
                    Log.e("库存查询","条件搜索"+sp.getAll().toString());
                    getConditionData(sp);
                }

            }

        }.execute();

    }
    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        searchText=search.getText().toString();
        queryPermission();
    }

    @Override
    public void onLoad(){
        page++;
        searchText=search.getText().toString();
        queryPermission();
    }

}