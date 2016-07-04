package com.example.admin.mingyang_object.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udcarfuelcharge;
import com.example.admin.mingyang_object.model.Udcarmainlog;


/**
 * 维修记录详情
 */
public class Udcarmainlog_Detailactivity extends BaseActivity {
    private static String TAG = "Udcarmainlog_Detailactivity";

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

    /**
     * 基本信息*
     */
    private TextView mainlognumText; //编号

    private TextView descriptionText; //描述

    private TextView licensenumText; //车牌号

    private TextView carnameText; //车辆名称

    private TextView driverid1Text; //司机

    private TextView respnameText; //责任人

    private TextView prodescText; //所属项目

    private TextView branchdescText; //所属中心

    private TextView createbyText; //录入人

    private TextView createdateText; //录入日期

    /**
     * 维修信息*
     */

    private TextView startdateText; //维修开始日期

    private TextView enddateText; //维修结束日期

    private TextView priceText; //维修单价

    private TextView mainnumberText; //维修数量

    private TextView totalpriceText; //维修总额

    private TextView invoicenumText; //维修发票号


    /**
     * 维修信息1
     */

    private TextView servicetypeText; //维修类型
    private TextView mainplaceText; //维修地点
    private TextView maincontentText; //维修.保养.更换项目
    private TextView number2Text; //上次维修里程表读数
    private TextView number1Text; //本次维修里程表读数
    private TextView comisornoText; //是否提交
    private TextView remarkText; //备注


    private Udcarmainlog udcarmainlog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udcarmainlog_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udcarmainlog = (Udcarmainlog) getIntent().getSerializableExtra("udcarmainlog");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        mainlognumText = (TextView) findViewById(R.id.mainlognum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);
        driverid1Text = (TextView) findViewById(R.id.driverid1_text_id);
        respnameText = (TextView) findViewById(R.id.respons_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);


        startdateText = (TextView) findViewById(R.id.startdate_text_id);
        enddateText = (TextView) findViewById(R.id.enddate_text_id);
        priceText = (TextView) findViewById(R.id.price_text_id);
        mainnumberText = (TextView) findViewById(R.id.mainnumber_text_id);
        totalpriceText = (TextView) findViewById(R.id.totalprice_text_id);
        invoicenumText = (TextView) findViewById(R.id.invoicenum_text_id);

        servicetypeText = (TextView) findViewById(R.id.servicetype_text_id);
        mainplaceText = (TextView) findViewById(R.id.mainplace_text_id);
        maincontentText = (TextView) findViewById(R.id.maincontent_text_id);
        number2Text = (TextView) findViewById(R.id.number2_text_id);
        number1Text = (TextView) findViewById(R.id.number1_text_id);
        comisornoText = (TextView) findViewById(R.id.comisorno_text_id);
        remarkText = (TextView) findViewById(R.id.remark_text_id);

        if (udcarmainlog != null) {
            mainlognumText.setText(udcarmainlog.getMAINLOGNUM());
            descriptionText.setText(udcarmainlog.getDESCRIPTION());
            licensenumText.setText(udcarmainlog.getLICENSENUM());
            carnameText.setText(udcarmainlog.getVEHICLENAME());
            driverid1Text.setText(udcarmainlog.getDRIVERID1());
            respnameText.setText(udcarmainlog.getRESPNAME());
            prodescText.setText(udcarmainlog.getPRODESC());
            branchdescText.setText(udcarmainlog.getBRANCHDESC());
            createbyText.setText(udcarmainlog.getCREATEBY());
            createdateText.setText(udcarmainlog.getCREATEDATE());

            startdateText.setText(udcarmainlog.getSTARTDATE());
            enddateText.setText(udcarmainlog.getENDDATE());
            priceText.setText(udcarmainlog.getPRICE());
            mainnumberText.setText(udcarmainlog.getMAINNUMBER());
            totalpriceText.setText(udcarmainlog.getTOTALPRICE());
            invoicenumText.setText(udcarmainlog.getINVOICENUM());

            servicetypeText.setText(udcarmainlog.getSERVICETYPE());
            mainplaceText.setText(udcarmainlog.getMAINPLACE());
            maincontentText.setText(udcarmainlog.getMAINCONTENT());
            number2Text.setText(udcarmainlog.getNUMBER2());
            number1Text.setText(udcarmainlog.getNUMBER1());
            comisornoText.setText(udcarmainlog.getCOMISORNO());
            remarkText.setText(udcarmainlog.getREMARK());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.wxjlxq_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
