package com.example.admin.mingyang_object.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.CommonModel;


/**
 * Created by Administrator on 2015/10/12.
 */
public class RefreshUtils extends SwipeRefreshLayout implements AbsListView.OnScrollListener {
    /**
     * 滑动到最下面时的上拉操作
     */

    private int mTouchSlop;
    /**
     * listview实例
     */
    private ListView mListView;

    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;

    /**
     * ListView的加载中footer
     */
    public View mListViewFooter;

    /**
     * ListView的加载中footer
     */
    public View mListViewFooter1;

    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 按下时的x坐标
     */
    private int mXDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    static boolean isListener = true;

    /**
     * @param context
     */
    public RefreshUtils(Context context) {
        this(context, null);
    }

    public RefreshUtils(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);

        mListViewFooter1 = LayoutInflater.from(context).inflate(R.layout.listview_footer1, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 初始化ListView对象
        if (mListView == null) {
            new CommonModel();
            getListView();
        }
    }

    /**
     * 获取ListView对象
     */
    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            for(int i=0; i<childs; i++){
                View childView = getChildAt(i);
                if (childView instanceof ListView) {
                    mListView = (ListView) childView;
                    // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                    mListView.setOnScrollListener(this);
                    Log.d(VIEW_LOG_TAG, "### 找到listview");
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                mXDown =  (int) event.getRawX();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                // 抬起
                int deltaY = (int) event.getRawY() - mYDown;
                int deltaX = (int) event.getRawX() - mXDown;
                if (canLoad() && deltaY<-60 && Math.abs(deltaY)>Math.abs(deltaX)) {  // 竖直方向滑动距离大于水平方向滑动距离
                    loadData();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {

        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;
    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (mOnLoadListener != null) {
            isListener = CommonModel.isListener();
            if(isListener) {
                // 设置状态
                setLoading(true);
                //
                mOnLoadListener.onLoad();
            }else {
                // 设置状态
                setLoading1(true);
                //
                mOnLoadListener.onLoad();
            }
        }
    }

    /**
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter);
        } else {
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * @param loading
     */
    public void setLoading1(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter1);
        } else {
            mListView.removeFooterView(mListViewFooter1);
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 滚动时到了最底部也可以加载更多
        if (canLoad()) {
            loadData();
        }
    }

    /**
     * 加载更多的监听器
     *
     * @author mrsimple
     */
    public static interface OnLoadListener {
        public void onLoad();
    }

    public void setListViewAdapter(ListAdapter adapter){
        if(mListView!=null){
            mListView.addFooterView(mListViewFooter);
            mListView.setAdapter(adapter);  // 在setAdapter之前后调用addFooterView和removeFooterView防止出现类型转换异常
            mListView.removeFooterView(mListViewFooter);
        }
    }

}
