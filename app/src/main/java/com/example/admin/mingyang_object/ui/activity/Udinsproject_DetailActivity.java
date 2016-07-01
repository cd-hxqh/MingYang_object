package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udinsproject;
import com.example.admin.mingyang_object.model.Udpro;


/**
 * 巡检项目
 */
public class Udinsproject_DetailActivity extends BaseActivity {
    private static String TAG = "Udinsproject_DetailActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**
     * 菜单
     */
    private ImageView menuImageView;


    /**
     * 界面信息*
     */
    private TextView jptaskText; //任务

    private TextView descriptionText; //描述

    private TextView jo1Text; //系统/项目

    private TextView jo2Text; //子系统/子项目

    private TextView jo3Text; //标准/检修方法

    private TextView serialnumText; //序号

    private TextView inspunitText; //巡检部位

    private CheckBox okCheckBox; //巡检结果


    private Udinsproject udinsproject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinsproject_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udinsproject = (Udinsproject) getIntent().getSerializableExtra("udinsproject");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        jptaskText = (TextView) findViewById(R.id.jptask_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        jo1Text = (TextView) findViewById(R.id.jo1_text_id);
        jo2Text = (TextView) findViewById(R.id.jo2_text_id);
        jo3Text = (TextView) findViewById(R.id.jo3_text_id);
        serialnumText = (TextView) findViewById(R.id.serialnum_text_id);
        inspunitText = (TextView) findViewById(R.id.inspunit_text_id);
        okCheckBox = (CheckBox) findViewById(R.id.ok_text_id);

        if (udinsproject != null) {
            jptaskText.setText(udinsproject.getJPTASK());
            descriptionText.setText(udinsproject.getDESCRIPTION());
            jo1Text.setText(udinsproject.getJO1());
            jo2Text.setText(udinsproject.getJO2());
            jo3Text.setText(udinsproject.getJO3());
            serialnumText.setText(udinsproject.getSERIALNUM());
            inspunitText.setText(udinsproject.getINSPCONTENT());
            if (udinsproject.getOK().equals("Y")) {
                okCheckBox.setChecked(true);
            } else {
                okCheckBox.setChecked(false);
            }
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udinsproject_details_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
