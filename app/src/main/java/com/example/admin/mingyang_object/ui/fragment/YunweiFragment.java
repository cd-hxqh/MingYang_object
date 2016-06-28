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
 * 运维管理
 */
public class YunweiFragment extends BaseFragment {

    /**
     * 项目台账*
     */
    private TextView udrroText;
    /**
     * 项目日报*
     */
    private TextView udprorunlogText;
    /**
     * 问题联络单*
     */
    private TextView udfeedbackText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        udrroText = (TextView) view.findViewById(R.id.udrro_text_id);
        udprorunlogText = (TextView) view.findViewById(R.id.udprorunlog_text_id);
        udfeedbackText = (TextView) view.findViewById(R.id.udfeedback_text_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        udrroText.setOnClickListener(udrroTextOnClickListener);
        udprorunlogText.setOnClickListener(udprorunlogTextOnClickListener);
        udfeedbackText.setOnClickListener(udfeedbackTextOnClickListener);
    }


    private View.OnClickListener udrroTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udpro_ListActivity.class);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener udprorunlogTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udprorunlog_listactivity.class);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener udfeedbackTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udfeedback_listactivity.class);
            startActivityForResult(intent, 0);
        }
    };

}
