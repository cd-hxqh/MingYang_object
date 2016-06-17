package com.example.admin.mingyang_object.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.ui.activity.Udpro_ListActivity;


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
     * 阶段验收单*
     */
    private TextView udacceptanceText;


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
        udacceptanceText = (TextView) view.findViewById(R.id.udacceptance_text_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        udrroText.setOnClickListener(udrroTextOnClickListener);
    }


    private View.OnClickListener udrroTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Udpro_ListActivity.class);
            startActivityForResult(intent, 0);
        }
    };

}