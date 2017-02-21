package com.example.admin.eam.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.ui.activity.Udinspo_ListActivity;
import com.example.admin.eam.ui.activity.Udreport_ListActivity;
import com.example.admin.eam.ui.activity.Udrunlogr_ListActivity;


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

    /**
     * 运行记录
     */
    private TextView udrunlogrText;


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
        udrunlogrText = (TextView) view.findViewById(R.id.udrunlogr_text_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
//        udrunlogrText.setVisibility(View.INVISIBLE);
        udreportText.setOnClickListener(udreportTextOnClickListener);
        udinspoText.setOnClickListener(udinspoTextOnClickListener);
        udrunlogrText.setOnClickListener(udrunlogrTextOnClickListener);
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

    private View.OnClickListener udrunlogrTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udrunlogr_ListActivity.class);
            startActivityForResult(intent, 0);
        }
    };

}
