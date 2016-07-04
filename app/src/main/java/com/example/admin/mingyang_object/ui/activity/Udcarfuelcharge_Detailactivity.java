package com.example.admin.mingyang_object.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udcardrivelog;
import com.example.admin.mingyang_object.model.Udcarfuelcharge;


/**
 * 加油记录详情
 */
public class Udcarfuelcharge_Detailactivity extends BaseActivity {
    private static String TAG = "Udcarfuelcharge_Detailactivity";

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
     * 车辆信息*
     */
    private TextView carfuelchargenumText; //编号

    private TextView descriptionText; //描述

    private TextView licensenumText; //车牌号

    private TextView carnameText; //车辆名称

    private TextView driverid1Text; //司机

    private TextView respnameText; //责任人

    private TextView prodescText; //所属项目

    private TextView branchdescText; //所属中心

    /**
     * 录入信息*
     */

    private TextView driveridText; //录入人编号

    private TextView createbyText; //录入人

    private TextView createdateText; //录入日期

    private TextView comisornoText; //是否提交

    /**
     * 加油记录详细信息
     */

    private TextView chargedateText; //加油日期
    private TextView number2Text; //上次加油里程表读数
    private TextView number1Text; //本次加油里程表读数
    private TextView number3Text; //里程差
    private TextView number4Text; //油品号
    private TextView number5Text; //本次加油量
    private TextView priceText; //单价
    private TextView fuelcostText; //加油费
    private TextView lastfuelconsumptionText; //油耗
    private TextView invoicenumText; //发票号
    private TextView placeText; //加油地点
    private TextView remarkText; //备注


    private Udcarfuelcharge udcarfuelcharge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udcarfuelcharge_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udcarfuelcharge = (Udcarfuelcharge) getIntent().getSerializableExtra("udcarfuelcharge");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        carfuelchargenumText = (TextView) findViewById(R.id.carfuelchargenum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);
        driverid1Text = (TextView) findViewById(R.id.driverid1_text_id);
        respnameText = (TextView) findViewById(R.id.respons_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);

        driveridText = (TextView) findViewById(R.id.driverid_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        comisornoText = (TextView) findViewById(R.id.comisorno_text_id);

        chargedateText = (TextView) findViewById(R.id.chargedate_text_id);
        number2Text = (TextView) findViewById(R.id.number2_text_id);
        number1Text = (TextView) findViewById(R.id.number1_text_id);
        number3Text = (TextView) findViewById(R.id.number3_text_id);
        number4Text = (TextView) findViewById(R.id.number4_text_id);
        number5Text = (TextView) findViewById(R.id.number5_text_id);
        priceText = (TextView) findViewById(R.id.price_text_id);
        fuelcostText = (TextView) findViewById(R.id.fuelcost_text_id);
        lastfuelconsumptionText = (TextView) findViewById(R.id.lastfuelconsumption_text_id);
        invoicenumText = (TextView) findViewById(R.id.invoicenum_text_id);
        placeText = (TextView) findViewById(R.id.place_text_id);
        remarkText = (TextView) findViewById(R.id.remark_text_id);

        if (udcarfuelcharge != null) {
            carfuelchargenumText.setText(udcarfuelcharge.getCARFUELCHARGENUM());
            descriptionText.setText(udcarfuelcharge.getDESCRIPTION());
            licensenumText.setText(udcarfuelcharge.getLICENSENUM());
            carnameText.setText(udcarfuelcharge.getVEHICLENAME());
            driverid1Text.setText(udcarfuelcharge.getDRIVERID1());
            respnameText.setText(udcarfuelcharge.getRESPNAME());
            prodescText.setText(udcarfuelcharge.getPRODESC());
            branchdescText.setText(udcarfuelcharge.getBRACHDESC());

            driveridText.setText(udcarfuelcharge.getDRIVERID());
            createbyText.setText(udcarfuelcharge.getCREATEBY());
            createdateText.setText(udcarfuelcharge.getCREATEDATE());
            comisornoText.setText(udcarfuelcharge.getCOMISORNO());

            chargedateText.setText(udcarfuelcharge.getCHARGEDATE());
            number2Text.setText(udcarfuelcharge.getNUMBER2());
            number1Text.setText(udcarfuelcharge.getNUMBER1());
            number3Text.setText(udcarfuelcharge.getNUMBER3());
            number4Text.setText(udcarfuelcharge.getNUMBER4());
            number5Text.setText(udcarfuelcharge.getNUMBER5());
            priceText.setText(udcarfuelcharge.getPRICE());
            fuelcostText.setText(udcarfuelcharge.getFUELCOST());
            lastfuelconsumptionText.setText(udcarfuelcharge.getLASTFUELCONSUMPTION());
            invoicenumText.setText(udcarfuelcharge.getINVOICENUM());
            placeText.setText(udcarfuelcharge.getPLACE());
            remarkText.setText(udcarfuelcharge.getREMARK());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udcarfuelcharge_detail_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
