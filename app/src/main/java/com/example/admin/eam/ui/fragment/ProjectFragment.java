package com.example.admin.eam.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.ui.activity.TriprePort_listActivity;
import com.example.admin.eam.ui.activity.udpro.Udfeedback_listactivity;
import com.example.admin.eam.ui.activity.udpro.Udpro_ListActivity;
import com.example.admin.eam.ui.activity.udpro.Udprorunlog_listactivity;


/**
 * 项目管理
 */
public class ProjectFragment extends BaseFragment {

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
    /**
     * 出差总结报告*
     */
    private TextView udtripreportText;


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
        udtripreportText = (TextView) view.findViewById(R.id.tripreport_text_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        udrroText.setOnClickListener(udrroTextOnClickListener);
        udprorunlogText.setOnClickListener(udprorunlogTextOnClickListener);
        udfeedbackText.setOnClickListener(udfeedbackTextOnClickListener);
        udtripreportText.setOnClickListener(tripReportTextOnClickListener);
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
    private View.OnClickListener tripReportTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), TriprePort_listActivity.class);
            startActivityForResult(intent, 0);
        }
    };
}
