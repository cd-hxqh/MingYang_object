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
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Udreport;


/**
 * 故障提报单详情
 */
public class Udreport_DetailActivity extends BaseActivity {
    private static String TAG = "Udreport_DetailActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;


    /**
     * 界面信息*
     */
    private TextView reportnumText; //编号

    private TextView descriptionText; //描述

    private TextView branchText; //中心

    private TextView pronumText; //项目中心

    private TextView prdescText; //项目名称

    private TextView location_codeText; //机位号

    private TextView assetlocText; //设备位置

    private TextView culevelText; //故障等级

    private TextView faulttypeText; //故障类型

    private TextView happen_timeText; //报障时间

    private CheckBox is_endCheckBox; //故障是否结束

    private TextView end_timeText; //结束时间

    private TextView statustypeText; //状态

    private CheckBox cuisplanTCheckBox; //是否由集控生成

    private TextView createbyText; //提报人

    private TextView reporttimeText; //提报时间

    private TextView fault_codedescText; //故障类


    private TextView fault_code1Text; //故障代码

    private TextView cudescribeText; //故障描述

    private TextView resultText; //处理结果

    private TextView remarkText; //备注


    private Udreport udreport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udreport_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udreport = (Udreport) getIntent().getSerializableExtra("udreport");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        reportnumText = (TextView) findViewById(R.id.reportnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        branchText = (TextView) findViewById(R.id.branchdesc_text_id);
        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prdescText = (TextView) findViewById(R.id.prdesc_text_id);
        location_codeText = (TextView) findViewById(R.id.location_code_text_id);
        assetlocText = (TextView) findViewById(R.id.assetloc_text_id);
        culevelText = (TextView) findViewById(R.id.culevel_text_id);
        faulttypeText = (TextView) findViewById(R.id.faulttype_text_id);
        happen_timeText = (TextView) findViewById(R.id.capacity_text_id);
        is_endCheckBox = (CheckBox) findViewById(R.id.is_end_text_id);
        end_timeText = (TextView) findViewById(R.id.end_time_text_id);
        statustypeText = (TextView) findViewById(R.id.statustype_text_id);
        cuisplanTCheckBox = (CheckBox) findViewById(R.id.cuisplan_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        reporttimeText = (TextView) findViewById(R.id.reporttime_text_id);
        fault_codedescText = (TextView) findViewById(R.id.fault_codedesc_text_id);
        fault_code1Text = (TextView) findViewById(R.id.fault_code1_text_id);
        cudescribeText = (TextView) findViewById(R.id.cudescribe_text_id);
        resultText = (TextView) findViewById(R.id.result_text_id);
        remarkText = (TextView) findViewById(R.id.remark_text_id);

        if (udreport != null) {
            reportnumText.setText(udreport.getREPORTNUM());
            descriptionText.setText(udreport.getDESCRIPTION());
            branchText.setText(udreport.getBRANCHDESC());
            pronumText.setText(udreport.getPRONUM());
            prdescText.setText(udreport.getPRODESC());
            location_codeText.setText(udreport.getLOCATION_CODE());
            assetlocText.setText(udreport.getASSETLOC());
            culevelText.setText(udreport.getCULEVEL());
            faulttypeText.setText(udreport.getFAULTTYPE());
            happen_timeText.setText(udreport.getHAPPEN_TIME());
            if (udreport.getIS_END().equals("Y")) {
                is_endCheckBox.setChecked(true);
            } else {
                is_endCheckBox.setChecked(false);
            }
            end_timeText.setText(udreport.getEND_TIME());
            statustypeText.setText(udreport.getSTATUSTYPE());
            if (udreport.getCUISPLAN().equals("Y")) {
                cuisplanTCheckBox.setChecked(true);
            } else {
                cuisplanTCheckBox.setChecked(false);
            }
            createbyText.setText(udreport.getCREATEBY());
            reporttimeText.setText(udreport.getREPORTTIME());
            fault_codedescText.setText(udreport.getFAULT_CODEDESC());
            fault_code1Text.setText(udreport.getFAULT_CODE1());
            cudescribeText.setText(udreport.getCUDESCRIBE());
            resultText.setText(udreport.getRESULT());
            remarkText.setText(udreport.getREMARK());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udreport_detail_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
