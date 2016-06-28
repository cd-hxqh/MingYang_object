package com.example.admin.mingyang_object.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.ui.activity.Udfeedback_listactivity;
import com.example.admin.mingyang_object.ui.activity.Udpro_ListActivity;
import com.example.admin.mingyang_object.ui.activity.Udprorunlog_listactivity;


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
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        udcardrivelogText.setOnClickListener(udcardrivelogTextOnClickListener);
        udcarfuelchargeText.setOnClickListener(udcarfuelchargeTextOnClickListener);
        udcarmainlogText.setOnClickListener(udcarmainlogTextOnClickListener);
    }


    private View.OnClickListener udcardrivelogTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udpro_ListActivity.class);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener udcarfuelchargeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udprorunlog_listactivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener udcarmainlogTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udfeedback_listactivity.class);
            startActivityForResult(intent, 0);
        }
    };

}
