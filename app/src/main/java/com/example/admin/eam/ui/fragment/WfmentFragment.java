package com.example.admin.eam.ui.fragment;

import android.app.ProgressDialog;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.bean.Results;
import com.example.admin.eam.model.Wfassignment;
import com.example.admin.eam.ui.activity.Wfm_Details_Activity;
import com.example.admin.eam.ui.adapter.BaseQuickAdapter;
import com.example.admin.eam.ui.adapter.WfmListAdapter;
import com.example.admin.eam.ui.widget.SwipeRefreshLayout;
import com.example.admin.eam.utils.AccountUtils;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;

import java.util.ArrayList;
import java.util.List;


/**
 * 流程审批的fragment
 */
public class WfmentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {


    private static final String TAG = "Wfment_fragment";


    LinearLayoutManager layoutManager;


    /**
     * RecyclerView*
     */
    public RecyclerView recyclerView;
    /**
     * 暂无数据*
     */
    private LinearLayout nodatalayout;
    /**
     * 界面刷新*
     */
    private SwipeRefreshLayout refresh_layout = null;
    /**
     * 适配器*
     */
    private WfmListAdapter wfmListAdapter;
    /**
     * 编辑框*
     */
    private EditText search;
    /**
     * 查询条件*
     */
    private String searchText = "";
    private int page = 1;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private ProgressDialog mProgressDialog;

    ArrayList<Wfassignment> items = new ArrayList<Wfassignment>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container,
                false);

        findByIdView(view);
        initView();
        return view;
    }


    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
        search = (EditText) view.findViewById(R.id.search_edit);
    }


    /**
     * 设置事件监听*
     */
    private void initView() {
        setSearchEdit();

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);



        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh_layout.setRefreshing(true);
        initAdapter(new ArrayList<Wfassignment>());
        items = new ArrayList<>();
        getData(searchText);
    }

    @Override
    public void onLoad() {
        page++;

        getData(searchText);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getData(searchText);
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
                                    getActivity().getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    wfmListAdapter.removeAll(items);
                    items = new ArrayList<Wfassignment>();
                    nodatalayout.setVisibility(View.GONE);
                    refresh_layout.setRefreshing(true);
                    page = 1;
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
    private void getData(String search) {
        if (!AccountUtils.getIsOffLine(getActivity())) {
            HttpManager.getDataPagingInfo(getActivity(), HttpManager.getwfassignmentUrl(AccountUtils.getpersonId(getActivity()), search, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int totalPages, int currentPage) {
                    ArrayList<Wfassignment> item = JsonUtils.parsingWfassignment(getActivity(), results.getResultlist());
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                    if (item == null || item.isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    } else {
                        if (totalPages == page) {
                            if (item != null || item.size() != 0) {
                                if (page == 1) {
                                    items = new ArrayList<Wfassignment>();
                                    initAdapter(new ArrayList<Wfassignment>());
                                }
                                for (int i = 0; i < item.size(); i++) {
                                    items.add(item.get(i));
                                }
                                addData(item);
                            }
                            nodatalayout.setVisibility(View.GONE);

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
        }else {
            refresh_layout.setRefreshing(false);
            nodatalayout.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Wfassignment> list) {
        wfmListAdapter = new WfmListAdapter(getActivity(), R.layout.list_item, list);
        recyclerView.setAdapter(wfmListAdapter);
        wfmListAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), Wfm_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wfassignment", items.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addData(final List<Wfassignment> list) {
        wfmListAdapter.addData(list);
        wfmListAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), Wfm_Details_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wfassignment", list.get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }


//    private void MaterialDialogOneBtn(final Wfassignment wfassignment) {//审批工作流
//        final MaterialDialog dialog = new MaterialDialog(getActivity());
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.isTitleShow(false)//
//                .btnNum(2)
//                .content("是否填写输入意见")//
//                .btnText("是", "否，直接提交")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)
//                .show();
//
//        dialog.setOnBtnClickL(
//                new OnBtnClickL() {//是
//                    @Override
//                    public void onBtnClick() {
//                        EditDialog(wfassignment, true);
//                        dialog.dismiss();
//                    }
//                },
//                new OnBtnClickL() {//否
//                    @Override
//                    public void onBtnClick() {
//                        wfgoon(wfassignment, wfassignment.getOwnerid(), "1", "");
//                        dialog.dismiss();
//                    }
//                }
//        );
//    }
//
//    private void EditDialog(final Wfassignment wfassignment, final boolean isok) {//输入审核意见
//        final NormalEditTextDialog dialog = new NormalEditTextDialog(getActivity());
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.isTitleShow(false)//
//                .btnNum(2)
//                .content(isok ? "通过" : "不通过")//
//                .btnText("提交", "取消")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)
//                .show();
//
//        dialog.setOnBtnClickL(
//                new OnBtnEditClickL() {
//                    @Override
//                    public void onBtnClick(String text) {
//                        wfgoon(wfassignment, wfassignment.getOwnerid(), "1", text);
//
//                        dialog.dismiss();
//                    }
//                },
//                new OnBtnEditClickL() {
//                    @Override
//                    public void onBtnClick(String text) {
//
//                        dialog.dismiss();
//                    }
//                }
//        );
//    }
//
//
//    /**
//     * 审批工作流
//     *
//     * @param id
//     * @param zx
//     */
//    private void wfgoon(final Wfassignment wfassignment, final String id, final String zx, final String desc) {
//        mProgressDialog = ProgressDialog.show(getActivity(), null,
//                getString(R.string.inputing), true, true);
//        mProgressDialog.setCanceledOnTouchOutside(false);
//        mProgressDialog.setCancelable(false);
//        new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... strings) {
//                String result = null;
//                if (wfassignment.app.equals("UDUPRAPP") && wfassignment.ownertable.equals("UDREPORT") && wfassignment.processname.equals("UDQXTB1")) { //跳转至缺陷提报单界面
//                    Log.i(TAG, "缺陷单");
//                    result = AndroidClientService.wfGoOn1(getActivity(), "UDQXTB", "UDREPORT", wfassignment.ownerid, "UDREPORTID", zx, desc);
//                } else {
//                    result = AndroidClientService.wfGoOn1(getActivity(), wfassignment.getProcessname(), wfassignment.getOwnertable(), id, wfassignment.getOwnertable() + "ID", zx, desc);
//                }
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                if (s == null || s.equals("")) {
//                    Toast.makeText(getActivity(), "审批失败", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "审批成功", Toast.LENGTH_SHORT).show();
//                }
//                mProgressDialog.dismiss();
//            }
//        }.execute();
//    }
//

}
