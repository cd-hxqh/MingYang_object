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


/**
 * 项目台账
 */
public class Udpro_DetailActivity extends BaseActivity {
    private static String TAG = "Udpro_DetailActivity";

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
    private TextView pronumText; //项目编号

    private TextView prDescText; //项目名称

    private TextView branchText; //所属中心

    private TextView responsText; //责任人

    private TextView ownerText; //业主单位

    private TextView signdateText; //签订时间

    private TextView contractstatusText; //合同状态

    private TextView testproText; //试点项目？

    private TextView prostageText; //项目当前阶段

    private TextView capacityText; //总厂容量

    private TextView periodText; //质保期

    private Udpro udpro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udpro_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udpro = (Udpro) getIntent().getParcelableExtra("udpro");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prDescText = (TextView) findViewById(R.id.prdesc_text_id);
        branchText = (TextView) findViewById(R.id.branch_text_id);
        responsText = (TextView) findViewById(R.id.respons_text_id);
        ownerText = (TextView) findViewById(R.id.owner_text_id);
        signdateText = (TextView) findViewById(R.id.signdate_text＿);
        contractstatusText = (TextView) findViewById(R.id.contractstatus_text_id);
        testproText = (TextView) findViewById(R.id.testpro_text_id);
        prostageText = (TextView) findViewById(R.id.prostage_text_id);
        capacityText = (TextView) findViewById(R.id.capacity_text_id);
        periodText = (TextView) findViewById(R.id.period_text_id);

        if (udpro != null) {
            pronumText.setText(udpro.pronum);
            prDescText.setText(udpro.description);
            branchText.setText(udpro.branch);
            responsText.setText(udpro.respons);
            ownerText.setText(udpro.owner);
            signdateText.setText(udpro.signdate);
            contractstatusText.setText(udpro.contractstatus);
//            testproText.setText(udpro.t);
            prostageText.setText(udpro.prostage);
            capacityText.setText(udpro.capacity);
            periodText.setText(udpro.period);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udpro_detail_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
