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
import com.example.admin.mingyang_object.model.Udfeedback;
import com.example.admin.mingyang_object.model.Udpro;


/**
 * 问题联络单详情
 */
public class Udfeedback_DetailActivity extends BaseActivity {
    private static String TAG = "Udfeedback_DetailActivity";

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
    /**
     * 问题联络单基本信息*
     */
    private TextView feedbacknumText; //编号

    private TextView descriptionText; //描述

    private TextView problemtypeText; //问题类型

    private TextView emergencyText; //紧急程度

    private TextView workordernumText; //相关故障工单

    private TextView problemsituationText; //现场问题及进展情况描述
    /**
     * 项目信息*
     */

    private TextView pronumText; //项目编号

    private TextView prodescText; //项目描述

    private TextView proresText; //项目负责人

    private TextView phone1Text; //项目人电话

    private TextView branchText; //所属中心

    private TextView prostageText; //项目阶段
    /**
     * 问题提出*
     */
    private TextView createnameText; //需求提出人

    private TextView phone2Text; //提出人电话

    private TextView dept1Text; //提出人部门

    private TextView createdateText; //提出时间

    private TextView statusText; //状态

    /**
     * 支持部门*
     */
    private TextView dept2Text; //支持部门

    private TextView leaderText; //支持部门领导

    private TextView responsetimeText; //需求完成时间


    /**
     * 问题处理*
     */
    private TextView solvedbyText; //问题处理人

    private TextView phone3Text; //联系电话

    private TextView dept3Text; //解决人所属部门

    /**
     * 问题解决*
     */
    private TextView foundtimeText; //抵达时间

    private TextView comptimeText; //完成时间

    private TextView progressText; //解决问题及反馈

    /**
     * 问题确认*
     */
    private CheckBox isstopText; //是否解决

    private TextView remarkText; //说明

    private Udfeedback udfeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udfeedback_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udfeedback = (Udfeedback) getIntent().getSerializableExtra("udfeedback");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        feedbacknumText = (TextView) findViewById(R.id.feedbacknum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        problemtypeText = (TextView) findViewById(R.id.problemtype_text_id);
        emergencyText = (TextView) findViewById(R.id.emergency_text_id);
        workordernumText = (TextView) findViewById(R.id.workordernum_text_id);
        problemsituationText = (TextView) findViewById(R.id.problemsituation_text_id);

        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        proresText = (TextView) findViewById(R.id.prores_text_id);
        phone1Text = (TextView) findViewById(R.id.phone1_text_id);
        branchText = (TextView) findViewById(R.id.branch_text_id);
        prostageText = (TextView) findViewById(R.id.prostage_text_id);

        createnameText = (TextView) findViewById(R.id.createname_text_id);
        phone2Text = (TextView) findViewById(R.id.phone2_text_id);
        dept1Text = (TextView) findViewById(R.id.dept1_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);

        dept2Text = (TextView) findViewById(R.id.dept2_text_id);
        leaderText = (TextView) findViewById(R.id.leader_text_id);
        responsetimeText = (TextView) findViewById(R.id.responsetime_text_id);


        solvedbyText = (TextView) findViewById(R.id.solvedby_text_id);
        phone3Text = (TextView) findViewById(R.id.phone3_text_id);
        dept3Text = (TextView) findViewById(R.id.dept3_text_id);

        foundtimeText = (TextView) findViewById(R.id.foundtime_text_id);
        comptimeText = (TextView) findViewById(R.id.comptime_text_id);
        progressText = (TextView) findViewById(R.id.progress_text_id);

        isstopText = (CheckBox) findViewById(R.id.isstop_text_id);
        remarkText = (TextView) findViewById(R.id.remark_text_id);

        if (udfeedback != null) {
            feedbacknumText.setText(udfeedback.getFEEDBACKNUM());
            descriptionText.setText(udfeedback.getDESCRIPTION());
            problemtypeText.setText(udfeedback.getPROBLEMTYPE());
            emergencyText.setText(udfeedback.getEMERGENCY());
            workordernumText.setText(udfeedback.getWORKORDERNUM());
            problemsituationText.setText(udfeedback.getPROBLEMSITUATION());

            pronumText.setText(udfeedback.getPRONUM());
            prodescText.setText(udfeedback.getPRODESC());
            proresText.setText(udfeedback.getPRORES());
            phone1Text.setText(udfeedback.getPHONE1());
            branchText.setText(udfeedback.getBRANCH());
            prostageText.setText(udfeedback.getPROSTAGE());

            createnameText.setText(udfeedback.getCREATENAME());
            phone2Text.setText(udfeedback.getPHONE2());
            dept1Text.setText(udfeedback.getDEPT1());
            createdateText.setText(udfeedback.getCREATEDATE());
            statusText.setText(udfeedback.getSTATUS());

            dept2Text.setText(udfeedback.getDEPT2());
            leaderText.setText(udfeedback.getLEADER());
            responsetimeText.setText(udfeedback.getRESPONSETIME());

            solvedbyText.setText(udfeedback.getSOLVEDBY());
            phone3Text.setText(udfeedback.getPHONE3());
            dept3Text.setText(udfeedback.getDEPT3());

            foundtimeText.setText(udfeedback.getFOUNDTIME());
            comptimeText.setText(udfeedback.getCOMPTIME());
            progressText.setText(udfeedback.getPROGRESS());


            if(udfeedback.getISSTOP().equals("1")){
                isstopText.setChecked(true);
            }else {
                isstopText.setChecked(false);
            }

            remarkText.setText(udfeedback.getREMARK());

        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.wtlldxj_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };



}
