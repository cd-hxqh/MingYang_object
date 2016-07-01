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
import com.example.admin.mingyang_object.model.Udinspo;
import com.example.admin.mingyang_object.model.Udpro;


/**
 * 巡检单详情
 */
public class Udinspo_DetailActivity extends BaseActivity {
    private static String TAG = "Udinspo_DetailActivity";

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
    private TextView reportnumText; //编号

    private TextView descriptionText; //描述

    private TextView branchdescText; //中心

    private TextView pronumText; //项目编号

    private TextView prdescText; //项目名称

    private TextView modeltypeText; //风机型号

    private TextView jpnumText; //巡检标准

    private TextView udlocnumText; //机位号

    private TextView fjdescText; //设备位置

    private TextView statusText; //状态

    private TextView inspplannumText; //巡检计划单号

    private TextView resbyText; //巡检负责人

    private TextView inspobyText; //巡检人员

    private TextView inspoby1Text; //巡检人员1

    private TextView inspoby2Text; //巡检人员2


    private CheckBox isstopCheckBox; //是否停机

    private TextView inspodateText; //巡检日期

    private TextView stoptimeText; //停机时间

    private TextView oktimeText; //恢复时间

    private TextView alltimeText; //累计停机时间

    private TextView createbyText; //创建人

    private TextView createdateText; //创建时间

    private TextView changebyText; //修改人

    private TextView changedateText; //修改时间

    private TextView weatherText; //天气

    private TextView starttimeText; //计划开始日期

    private TextView comptimeText; //计划完成时间

    private TextView lastrundateText; //上次巡检时间

    private TextView nextrundateText; //下次巡检时间

    private Udinspo udinspo;

    private PopupWindow popupWindow;

    /**
     * 巡检项目*
     */
    private LinearLayout udinsprojectLinear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspo_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udinspo = (Udinspo) getIntent().getSerializableExtra("udinspo");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        reportnumText = (TextView) findViewById(R.id.reportnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);
        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prdescText = (TextView) findViewById(R.id.prdesc_text_id);
        modeltypeText = (TextView) findViewById(R.id.modeltype_text_id);
        jpnumText = (TextView) findViewById(R.id.jpnum_text_id);
        udlocnumText = (TextView) findViewById(R.id.udlocnum_text_id);
        fjdescText = (TextView) findViewById(R.id.fjdesc_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        inspplannumText = (TextView) findViewById(R.id.inspplannum_text_id);
        resbyText = (TextView) findViewById(R.id.resby_text_id);
        inspobyText = (TextView) findViewById(R.id.inspoby_text_id);
        inspoby1Text = (TextView) findViewById(R.id.inspoby_1_text_id);
        inspoby2Text = (TextView) findViewById(R.id.inspoby_2_text_id);
        isstopCheckBox = (CheckBox) findViewById(R.id.isstop_text_id);
        inspodateText = (TextView) findViewById(R.id.inspodate_text_id);
        stoptimeText = (TextView) findViewById(R.id.stoptime_text_id);
        oktimeText = (TextView) findViewById(R.id.oktime_text_id);
        alltimeText = (TextView) findViewById(R.id.alltime_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        changebyText = (TextView) findViewById(R.id.changeby_text_id);
        changedateText = (TextView) findViewById(R.id.changedate_text_id);
        weatherText = (TextView) findViewById(R.id.weather_text_id);
        starttimeText = (TextView) findViewById(R.id.starttime_text_id);
        comptimeText = (TextView) findViewById(R.id.comptime_1_text_id);
        lastrundateText = (TextView) findViewById(R.id.lastrundate_text_id);
        nextrundateText = (TextView) findViewById(R.id.nextrundate_text_id);

        if (udinspo != null) {
            reportnumText.setText(udinspo.getINSPONUM());
            descriptionText.setText(udinspo.getDESCRIPTION());
            branchdescText.setText(udinspo.getBRANCH());
            pronumText.setText(udinspo.getPRONUM());
            prdescText.setText(udinspo.getPRODESC());
            modeltypeText.setText(udinspo.getMODELTYPE());
            jpnumText.setText(udinspo.getJPDESC());
            udlocnumText.setText(udinspo.getUDLOCNUM());
            fjdescText.setText(udinspo.getFJDESC());
            statusText.setText(udinspo.getSTATUS());
            inspplannumText.setText(udinspo.getINSPPLANNUM());
            resbyText.setText(udinspo.getNAME());
            inspobyText.setText(udinspo.getNAME1());
            inspoby1Text.setText(udinspo.getNAME2());
            inspoby2Text.setText(udinspo.getNAME3());
            if (udinspo.getISSTOP().equals("Y")) {
                isstopCheckBox.setChecked(true);
            } else {
                isstopCheckBox.setChecked(false);
            }
            inspodateText.setText(udinspo.getINSPODATE());
            stoptimeText.setText(udinspo.getSTOPTIME());
            oktimeText.setText(udinspo.getOKTIME());
            alltimeText.setText(udinspo.getALLTIME());
            createbyText.setText(udinspo.getCREATEBY());
            createdateText.setText(udinspo.getCREATEDATE());
            changebyText.setText(udinspo.getCHANGEBY());
            changedateText.setText(udinspo.getCHANGEDATE());
            weatherText.setText(udinspo.getWEATHER());
            starttimeText.setText(udinspo.getSTARTTIME());
            comptimeText.setText(udinspo.getCOMPTIME());
            lastrundateText.setText(udinspo.getLASTRUNDATE());
            nextrundateText.setText(udinspo.getNEXTRUNDATE());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udinspo_detail_text));
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

        View contentView = LayoutInflater.from(Udinspo_DetailActivity.this).inflate(
                R.layout.udinspo_popup_window, null);


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
        udinsprojectLinear = (LinearLayout) contentView.findViewById(R.id.udinproject_id);

        udinsprojectLinear.setOnClickListener(udinsprojectLinearOnClickListener);

    }


    private View.OnClickListener udinsprojectLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udinspo_DetailActivity.this, Udinsproject_ListActivity.class);
            intent.putExtra("insponum", udinspo.getINSPONUM());
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };

}
