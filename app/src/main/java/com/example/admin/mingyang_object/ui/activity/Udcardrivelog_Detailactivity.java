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
import com.example.admin.mingyang_object.model.Udcardrivelog;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.UdPerson_ListActivity;
import com.example.admin.mingyang_object.ui.activity.Udfandetails_ListActivity;
import com.example.admin.mingyang_object.ui.activity.Ududvehicle_ListActivity;


/**
 * 行驶记录详情
 */
public class Udcardrivelog_Detailactivity extends BaseActivity {
    private static String TAG = "Udcardrivelog_Detailactivity";

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
     * 车辆基础信息*
     */
    private TextView cardrivelognumText; //记录编号

    private TextView descriptionText; //描述

    private TextView licensenumText; //车牌号

    private TextView carnameText; //车辆名称

    private TextView driverid1Text; //司机

    private TextView prodescText; //所属项目

    private TextView branchdescText; //所属中心

    /**
     * 录入信息*
     */

    private TextView driveridText; //创建人

    private TextView createdateText; //创建时间

    private TextView comisornoText; //是否提交

    /**
     * 出车信息*
     */

    private TextView startdateText; //出车日期
    private TextView starttimeText; //出车时间
    private TextView departureText; //出发地
    private TextView destinationText; //目的地
    private TextView goreasonText; //出车事由

    /**
     * 车辆行驶详细信息*
     */
    private TextView wonumText; //业务单号
    private TextView wtypedescText; //任务类型
    private TextView startnumberText; //起始里程
    private TextView endnumberText; //结束里程
    private TextView lastfuelconsumptionText; //上次油耗
    private TextView standardfuelconsumptionText; //标准油耗
    private TextView feeText; //路桥费

    private Udcardrivelog udcardrivelog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udcardrivelog_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udcardrivelog = (Udcardrivelog) getIntent().getSerializableExtra("udcardrivelog");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        cardrivelognumText = (TextView) findViewById(R.id.cardrivelognum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);
        driverid1Text = (TextView) findViewById(R.id.driverid1_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);

        driveridText = (TextView) findViewById(R.id.driverid_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        comisornoText = (TextView) findViewById(R.id.comisorno_text_id);

        startdateText = (TextView) findViewById(R.id.startdate_text_id);
        starttimeText = (TextView) findViewById(R.id.starttime_text_id);
        departureText = (TextView) findViewById(R.id.departure_text_id);
        destinationText = (TextView) findViewById(R.id.destination_text_id);
        goreasonText = (TextView) findViewById(R.id.goreason_text_id);

        wonumText = (TextView) findViewById(R.id.wonum_text_id);
        wtypedescText = (TextView) findViewById(R.id.wtypedesc_text_id);
        startnumberText = (TextView) findViewById(R.id.startnumber_text_id);
        endnumberText = (TextView) findViewById(R.id.endnumber_text_id);
        lastfuelconsumptionText = (TextView) findViewById(R.id.lastfuelconsumption_text_id);
        standardfuelconsumptionText = (TextView) findViewById(R.id.standardfuelconsumption_text_id);
        feeText = (TextView) findViewById(R.id.fee_text_id);

        if (udcardrivelog != null) {
            cardrivelognumText.setText(udcardrivelog.getCARDRIVELOGNUM());
            descriptionText.setText(udcardrivelog.getDESCRIPTION());
            licensenumText.setText(udcardrivelog.getLICENSENUM());
            carnameText.setText(udcardrivelog.getCARNAME());
            driverid1Text.setText(udcardrivelog.getDRIVERID1());
            prodescText.setText(udcardrivelog.getPRODESC());
            branchdescText.setText(udcardrivelog.getBRANCHDESC());
            driveridText.setText(udcardrivelog.getDRIVERID());
            createdateText.setText(udcardrivelog.getCREATEDATE());
            comisornoText.setText(udcardrivelog.getCOMISORNO());
            startdateText.setText(udcardrivelog.getSTARTDATE());
            starttimeText.setText(udcardrivelog.getSTARTTIME());
            departureText.setText(udcardrivelog.getDEPARTURE());
            destinationText.setText(udcardrivelog.getDESTINATION());
            goreasonText.setText(udcardrivelog.getGOREASON());
            wonumText.setText(udcardrivelog.getWONUM());
            wtypedescText.setText(udcardrivelog.getWORKTYPE());
            startnumberText.setText(udcardrivelog.getSTARTNUMBER());
            endnumberText.setText(udcardrivelog.getENDNUMBER());
            lastfuelconsumptionText.setText(udcardrivelog.getLASTFUELCONSUMPTION());
            standardfuelconsumptionText.setText(udcardrivelog.getSTANDARDFUELCONSUMPTION());
            feeText.setText(udcardrivelog.getFEE());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udcarddrivelog_details_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
