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
import com.example.admin.mingyang_object.model.Udstock;


/**
 * 盘点详情
 */
public class Udstock_DetailActivity extends BaseActivity {
    private static String TAG = "Udstock_DetailActivity";

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
    private TextView stocknumText; //盘点单号

    private TextView descriptionText; //描述

    private TextView zpdnumText; //盘点单号凭证

    private TextView locationText; //仓库

    private TextView locdescText; //仓库名称

    private TextView createdbyText; //创建人

    private TextView createdateText; //创建时间


    private Udstock udstock;

    private PopupWindow popupWindow;

    /**
     * 上传附件*
     */
    private LinearLayout uploadfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udstock_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udstock = (Udstock) getIntent().getSerializableExtra("udstock");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        stocknumText = (TextView) findViewById(R.id.stocknum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        zpdnumText = (TextView) findViewById(R.id.zpdnum_text_id);
        locationText = (TextView) findViewById(R.id.location_text_id);
        locdescText = (TextView) findViewById(R.id.locdesc_text_id);
        createdbyText = (TextView) findViewById(R.id.createdby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);

        if (udstock != null) {
            stocknumText.setText(udstock.getSTOCKNUM());
            descriptionText.setText(udstock.getDESCRIPTION());
            zpdnumText.setText(udstock.getZPDNUM());
            locationText.setText(udstock.getLOCATION());
            locdescText.setText(udstock.getLOCDESC());
            createdbyText.setText(udstock.getCREATEDBY());
            createdateText.setText(udstock.getCREATEDATE());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udstock_detail_text));
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

        View contentView = LayoutInflater.from(Udstock_DetailActivity.this).inflate(
                R.layout.upload_window, null);


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
        uploadfile = (LinearLayout) contentView.findViewById(R.id.upload_file_id);


    }


}
