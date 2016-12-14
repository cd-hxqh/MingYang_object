package com.example.admin.mingyang_object.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.ui.activity.Udcardrivelog_Listactivity;
import com.example.admin.mingyang_object.ui.activity.Udcarfuelcharge_Listactivity;
import com.example.admin.mingyang_object.ui.activity.Udcarmainlog_Listactivity;
import com.example.admin.mingyang_object.ui.activity.stockQuery.StockQuery_listActivity;


/**
 * 资源管理
 */
public class ZiyuanFragment extends BaseFragment {

    /**
     * 行驶记录*
     */
    private TextView udcardrivelogText;
    /**
     * 加油记录*
     */
    private TextView udcarfuelchargeText;
    /**
     * 维修记录*
     */
    private TextView udcarmainlogText;
    /**
     * 库存查询*
     */
    private TextView stockQueryText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ziyuan, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        udcardrivelogText = (TextView) view.findViewById(R.id.udcardrivelog_text_id);
        udcarfuelchargeText = (TextView) view.findViewById(R.id.udcarfuelcharge_text_id);
        udcarmainlogText = (TextView) view.findViewById(R.id.udcarmainlog_text_id);
        stockQueryText = (TextView) view.findViewById(R.id.query_text_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {

        udcardrivelogText.setOnClickListener(udcardrivelogTextOnClickListener);
        udcarfuelchargeText.setOnClickListener(udcarfuelchargeTextOnClickListener);
        udcarmainlogText.setOnClickListener(udcarmainlogTextOnClickListener);
        stockQueryText.setOnClickListener(stockQueryTextOnClickListener);

    }


    private View.OnClickListener udcardrivelogTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udcardrivelog_Listactivity.class);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener udcarfuelchargeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udcarfuelcharge_Listactivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener udcarmainlogTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udcarmainlog_Listactivity.class);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener stockQueryTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("库存查询","点击按钮");
            Intent intent = new Intent(getActivity(), StockQuery_listActivity.class);
            startActivityForResult(intent, 0);
        }
    };

}
