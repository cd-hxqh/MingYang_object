package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Udstockline;


/**
 * 盘点明细行
 */
public class Udstockline_DetailActivity extends BaseActivity {
    private static String TAG = "Udstockline_DetailActivity";

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
    private TextView zpdrowText; //行项目

    private TextView matnrText; //物料编码

    private TextView maktxText; //物料描述

    private TextView msehlText; //单位

    private EditText actualqtyText; //实盘数量

    private EditText diffqtyText; //差异数量

    private EditText diffreasonText; //差异原因


    private Udstockline udstockline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udstockline_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udstockline = (Udstockline) getIntent().getSerializableExtra("udstockline");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        zpdrowText = (TextView) findViewById(R.id.zpdrow_text_id);
        matnrText = (TextView) findViewById(R.id.matnr_text_id);
        maktxText = (TextView) findViewById(R.id.maktx_text_id);
        msehlText = (TextView) findViewById(R.id.msehl_text_id);
        actualqtyText = (EditText) findViewById(R.id.actualqty_text_id);
        diffqtyText = (EditText) findViewById(R.id.diffqty_text_id);
        diffreasonText = (EditText) findViewById(R.id.diffreason_text_id);

        if (udstockline != null) {
            zpdrowText.setText(udstockline.getZPDROW());
            matnrText.setText(udstockline.getMATNR());
            maktxText.setText(udstockline.getMAKTX());
            msehlText.setText(udstockline.getMSEHL());
            actualqtyText.setText(udstockline.getACTUALQTY());
            diffqtyText.setText(udstockline.getDIFFQTY());
            diffreasonText.setText(udstockline.getDIFFREASON());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udstockline_text));

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
