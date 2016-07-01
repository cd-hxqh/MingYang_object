package com.example.admin.mingyang_object.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.ui.activity.Udfeedback_listactivity;
import com.example.admin.mingyang_object.ui.activity.Udinspo_ListActivity;
import com.example.admin.mingyang_object.ui.activity.Udpro_ListActivity;
import com.example.admin.mingyang_object.ui.activity.Udprorunlog_listactivity;
import com.example.admin.mingyang_object.ui.activity.Udreport_ListActivity;


/**
 * 运维管理
 */
public class YunweiFragment extends BaseFragment {

    /**
     * 故障提报*
     */
    private TextView udreportText;
    /**
     * 巡检单*
     */
    private TextView udinspoText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yunwei, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        udreportText = (TextView) view.findViewById(R.id.udreport_text_id);
        udinspoText = (TextView) view.findViewById(R.id.udinspo_text_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        udreportText.setOnClickListener(udreportTextOnClickListener);
        udinspoText.setOnClickListener(udinspoTextOnClickListener);
    }


    private View.OnClickListener udreportTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udreport_ListActivity.class);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener udinspoTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udinspo_ListActivity.class);
            startActivityForResult(intent, 0);
        }
    };


}
