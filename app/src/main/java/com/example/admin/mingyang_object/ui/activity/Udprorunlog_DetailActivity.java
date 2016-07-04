package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.UdprorunlogLine1;

import java.util.ArrayList;


/**
 * 项目日报详情
 */
public class Udprorunlog_DetailActivity extends BaseActivity {
    private static String TAG = "Udprorunlog_DetailActivity";

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
    private TextView prorunlognumText; //日志编号

    private TextView descriptionText; //描述

    private TextView pronumText; //项目编号

    private TextView branchText; //所属中心

    private TextView udprorescText; //现场负责人

    private TextView contractsText; //现场联系人

    private TextView yearText; //年

    private TextView monthText;//月


    private TextView prostageText; //项目阶段

    private TextView statusText; //状态

    private ArrayList<UdprorunlogLine1> UdprorunlogLine1List = new ArrayList<>();

    private Udprorunlog udprorunlog;

    private PopupWindow popupWindow;

    /**
     * 土建*
     */
    private LinearLayout tujianLinearLayout;
    /**
     * 吊装*
     */
    private LinearLayout diaozhuangLinearLayout;
    /**
     * 工作日志*
     */
    private LinearLayout gzrzLinearLayout;
    /**工装管理**/
    private LinearLayout gzglLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udprorunlog_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        prorunlognumText = (TextView) findViewById(R.id.prorunlognum_text_id);
        descriptionText = (TextView) findViewById(R.id.desction_text_id);
        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        branchText = (TextView) findViewById(R.id.branch_text_id);
        udprorescText = (TextView) findViewById(R.id.udproresc_text_id);
        contractsText = (TextView) findViewById(R.id.contdesc_text_id);
        yearText = (TextView) findViewById(R.id.year_text_id);
        monthText = (TextView) findViewById(R.id.month_text_id);
        prostageText = (TextView) findViewById(R.id.prostage_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);

        if (udprorunlog != null) {
            prorunlognumText.setText(udprorunlog.getPRORUNLOGNUM());
            descriptionText.setText(udprorunlog.getDESCRIPTION());
            pronumText.setText(udprorunlog.getPRONUM());
            branchText.setText(udprorunlog.getBRANCH());
            udprorescText.setText(udprorunlog.getUDPRORESC());
            contractsText.setText(udprorunlog.getCONTDESC());
            yearText.setText(udprorunlog.getYEAR());
            monthText.setText(udprorunlog.getMONTH());
            prostageText.setText(udprorunlog.getPROSTAGE());
            statusText.setText(udprorunlog.getSTATUS());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udprorunlog_detail_title));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udprorunlog_DetailActivity.this).inflate(
                R.layout.logpopup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        popupWindow.showAsDropDown(view);
        tujianLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline1_text_id);
        diaozhuangLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline2_text_id);
        gzrzLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline_text_id);
        gzglLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline4_text_id);

        tujianLinearLayout.setOnClickListener(tujianLinearOnClickListener);
        diaozhuangLinearLayout.setOnClickListener(personLinearOnClickListener);
        gzrzLinearLayout.setOnClickListener(udvehicleLinearOnClickListener);
        gzglLinearLayout.setOnClickListener(udvehicleLinearOnClickListener);

    }


    private View.OnClickListener tujianLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udprorunlog_DetailActivity.this, Udprorunlog_Line1Activity.class);
            intent.putExtra("udprorunlog", udprorunlog);
            intent.putExtra("UdprorunlogLine1List", UdprorunlogLine1List);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };
    private View.OnClickListener personLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(Udprorunlog_DetailActivity.this, UdPerson_ListActivity.class);
//            intent.putExtra("pronum", udpro.getPRONUM());
//            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };
    private View.OnClickListener udvehicleLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(Udprorunlog_DetailActivity.this, Ududvehicle_ListActivity.class);
//            intent.putExtra("pronum", udpro.getPRONUM());
//            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };
}
