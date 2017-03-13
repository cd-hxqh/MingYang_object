package com.example.admin.eam.ui.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.bean.Results;
import com.example.admin.eam.model.Wfassignment;
import com.example.admin.eam.ui.activity.MainActivity;

import com.example.admin.eam.utils.AccountUtils;
import com.jauker.widget.BadgeView;

import java.util.ArrayList;

/**
 * Created by chris on 2017/3/2.
 */

public class TabbarFragment extends android.support.v4.app.Fragment {


    TextView textView;

    AppCompatImageView function;
    AppCompatImageView process;
    AppCompatImageView myself;

    RelativeLayout  functionRelativeLayout;
    RelativeLayout  processRelativeLayout;
    RelativeLayout  myselfRelativeLayout;

    ReceiveBroadCast receiveBroadCast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabbar,container,false);

        function =(AppCompatImageView)v.findViewById(R.id.appCompatImageButton0);
        process =(AppCompatImageView)v.findViewById(R.id.appCompatImageButton1);
        myself =(AppCompatImageView)v.findViewById(R.id.appCompatImageButton2);

        textView = (TextView) v.findViewById(R.id.appCompatTextView);
        textView.setText("流程审批");
        functionRelativeLayout = (RelativeLayout)v.findViewById(R.id.appCompatRelativeLayout0);
        processRelativeLayout = (RelativeLayout)v.findViewById(R.id.appCompatRelativeLayout1);
        myselfRelativeLayout = (RelativeLayout)v.findViewById(R.id.appCompatRelativeLayout2);

        initView();

//        BadgeView badgeView = new com.jauker.widget.BadgeView(getActivity().getApplicationContext());
//        badgeView.setTargetView(button);
//        badgeView.setBadgeCount(3);

        //注册广播接收
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("number_of_undo");    //只有持有相同的action的接受者才能接收此广播
        getActivity().registerReceiver(receiveBroadCast, filter);
        Log.e("广播","注册广播");

        Log.e("广播","查询前");
        getData();
        Log.e("广播","查询后");
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void initView()
    {
        final MainActivity ma = (MainActivity)getActivity();

        functionRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function.setImageResource(R.mipmap.ic_function_down);
                process.setImageResource(R.mipmap.ic_float);
                myself.setImageResource(R.mipmap.ic_me);
                ma.showFragment(1);
            }
        });
        processRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function.setImageResource(R.mipmap.ic_function);
                process.setImageResource(R.mipmap.ic_float_down);
                myself.setImageResource(R.mipmap.ic_me);
                ma.showFragment(2);
            }
        });
        myselfRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function.setImageResource(R.mipmap.ic_function);
                process.setImageResource(R.mipmap.ic_float);
                myself.setImageResource(R.mipmap.ic_me_down);
                ma.showFragment(3);
            }
        });
        functionRelativeLayout.performClick();
    }
    private void getData() {
            String persionId =AccountUtils.getpersonId(getActivity().getApplicationContext());
            Log.e("广播","查询地址："+HttpManager.getwfassignmentUrl(persionId,"",1,999));
            HttpManager.getDataPagingInfo(getActivity(), HttpManager.getwfassignmentUrl(persionId,"",1,999), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {

                }

                @Override
                public void onSuccess(Results results, int totalPages, int currentPage) {
                    Log.e("广播","查询成功");
                    ArrayList<Wfassignment> item = JsonUtils.parsingWfassignment(getActivity(), results.getResultlist());

                    if (item == null || item.isEmpty()) {

                        Intent intent = new Intent();  //Itent就是我们要发送的内容
                        intent.putExtra("data", "0");
                        intent.setAction("number_of_undo");   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
                        getActivity().sendBroadcast(intent);   //发送广播
                        Log.e("广播","发送广播");

                    } else {
                        Log.e("广播","未处理"+item.size());
                        Intent intent = new Intent();  //Itent就是我们要发送的内容
                        intent.putExtra("data", ""+item.size());
                        intent.setAction("number_of_undo");   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
                        getActivity().sendBroadcast(intent);   //发送广播
                        Log.e("广播","发送广播");
                    }
                }
                @Override
                public void onFailure(String error) {
                    Log.e("广播","查询失败:"+error);
                }
            });

    }
    private  class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {


            String message = intent.getStringExtra("data");
            Log.e("广播","接收到广播:"+message);

            if(message.equals("0"))
            {
                textView.setText("流程审批");
            }else {
                textView.setText("流程审批(" + message + ")");
            }
        }
    }
}
