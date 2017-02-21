package com.example.admin.eam.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.Udinspo;
import com.example.admin.eam.model.Udinsproject;
import com.example.admin.eam.utils.MessageUtils;


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

    private EditText inspunitText; //巡检部位

    private CheckBox okCheckBox; //巡检结果

    private LinearLayout inspcontentLayout;
    private EditText inspcontentText;//巡检不合格原因

    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//取消


    private Udinsproject udinsproject;
    private Udinspo udinspo;
    private int position;


    private PopupWindow popupWindow;

    /**
     * 附件上传*
     */
    private LinearLayout uploadLinearLayout;

    /**
     * 隐藏*
     */
    private LinearLayout linearLayout1;
    /**
     * 隐藏*
     */
    private LinearLayout linearLayout2;


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
        udinspo = (Udinspo) getIntent().getSerializableExtra("udinspo");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        jptaskText = (TextView) findViewById(R.id.jptask_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        jo1Text = (TextView) findViewById(R.id.jo1_text_id);
        jo2Text = (TextView) findViewById(R.id.jo2_text_id);
        jo3Text = (TextView) findViewById(R.id.jo3_text_id);
        serialnumText = (TextView) findViewById(R.id.serialnum_text_id);
        inspunitText = (EditText) findViewById(R.id.inspunit_text_id);
        okCheckBox = (CheckBox) findViewById(R.id.ok_text_id);
        inspcontentLayout = (LinearLayout) findViewById(R.id.inspcontent_layout);
        inspcontentText = (EditText) findViewById(R.id.inspcontent_text_id);
        confirm = (Button) findViewById(R.id.work_save);
        cancel = (Button) findViewById(R.id.work_cancel);

        if (udinsproject != null) {
            jptaskText.setText(udinsproject.getJPTASK());
            descriptionText.setText(udinsproject.getDESCRIPTION());
            jo1Text.setText(udinsproject.getJO1());
            jo2Text.setText(udinsproject.getJO2());
            jo3Text.setText(udinsproject.getJO3());
            serialnumText.setText(udinsproject.getSERIALNUM());
            inspunitText.setText(udinsproject.getINSPUNIT());
            inspcontentText.setText(udinsproject.getINSPCONTENT());
            if (udinsproject.getOK().equals("Y")) {
                okCheckBox.setChecked(true);
                inspcontentLayout.setVisibility(View.GONE);
            } else {
                okCheckBox.setChecked(false);
                inspcontentLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udinsproject_details_text));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

//        if (udinspo.getSTATUS().equals("待执行")){
//            inspunitText.setFocusable(true);
//            inspunitText.setFocusableInTouchMode(true);
//            okCheckBox.setFocusable(true);
//            okCheckBox.setFocusableInTouchMode(true);
//            inspcontentText.setFocusable(true);
//            inspcontentText.setFocusableInTouchMode(true);
//        }else {
//            inspunitText.setFocusable(false);
//            inspunitText.setFocusableInTouchMode(false);
//            okCheckBox.setFocusable(false);
//            okCheckBox.setFocusableInTouchMode(false);
//            okCheckBox.setClickable(false);
//            inspcontentText.setFocusable(false);
//            inspcontentText.setFocusableInTouchMode(false);
//        }
        okCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                inspcontentLayout.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                inspcontentText.setText(isChecked ? "" : inspcontentText.getText().toString());
            }
        });
        cancel.setOnClickListener(backImageViewOnClickListener);
        confirm.setOnClickListener(confirmOnClickListener);
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private Udinsproject getUdinsproject() {
        Udinsproject udinsproject = this.udinsproject;
        udinsproject.setINSPUNIT(inspunitText.getText().toString());
        udinsproject.setOK(okCheckBox.isChecked() ? "Y" : "N");
        udinsproject.setINSPCONTENT(inspcontentText.getText().toString());
        return udinsproject;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if (udinsproject.INSPUNIT.equals(inspunitText.getText().toString())
                    && udinsproject.OK.equals(okCheckBox.isChecked() ? "Y" : "N")
                    && udinsproject.INSPCONTENT.equals(inspcontentText.getText().toString())) {//如果内容没有修改
                intent.putExtra("udinsproject", udinsproject);
            } else {
                Udinsproject udinsproject = getUdinsproject();
                if (udinsproject.TYPE == null || !udinsproject.TYPE.equals("add")) {
                    udinsproject.TYPE = "update";
                }
                intent.putExtra("udinsproject", udinsproject);
                MessageUtils.showMiddleToast(Udinsproject_DetailActivity.this, "巡检项目本地修改成功");
            }
            intent.putExtra("position", position);
            Udinsproject_DetailActivity.this.setResult(2, intent);
            finish();
        }
    };


    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udinsproject_DetailActivity.this).inflate(
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
        uploadLinearLayout = (LinearLayout) contentView.findViewById(R.id.upload_file_id);
        linearLayout1 = (LinearLayout)  contentView.findViewById(R.id.work_flower_id);
        linearLayout2 = (LinearLayout) contentView.findViewById(R.id.udstockline_id);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        uploadLinearLayout.setOnClickListener(uploadLinearLayoutOnClickListener);

    }

    private View.OnClickListener uploadLinearLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent intent = new Intent(Udinsproject_DetailActivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "UDINSPROJECTID");
            intent.putExtra("ownerid", udinsproject.getUDINSPROJECTID());
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };


}
