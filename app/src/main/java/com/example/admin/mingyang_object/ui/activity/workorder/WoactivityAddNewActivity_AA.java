package com.example.admin.mingyang_object.ui.activity.workorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.DateTimeSelect;


/**
 * Created by think on 2016/6/21.
 * 终验收工单任务新增页面
 */
public class WoactivityAddNewActivity_AA extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Woactivity woactivity = new Woactivity();
    private WorkOrder workOrder;
    private int position;

    private TextView taskid;//任务
    private EditText wojo1;//工作任务
    private TextView udstarttime;//开始时间
    private TextView udendtime;//结束时间
    private EditText udzysbasic;//执行标准
    private CheckBox perinspr;//终验收结果
    private EditText udprobdesc;//问题描述
    private TextView udzglimit;//整改期限
    private TextView lead;//整改责任人
    private EditText udzgmeasure;//整改方案
    private EditText udzgresult;//整改完成情况
    private EditText udremark;//备注
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//删除


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_aa);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
//        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
//        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
//        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        taskid = (TextView) findViewById(R.id.work_woactivity_taskid);
        wojo1 = (EditText) findViewById(R.id.work_woactivity_wojo1);
        udstarttime = (TextView) findViewById(R.id.woactivity_udstarttime);
        udendtime = (TextView) findViewById(R.id.woactivity_udendtime);
        udzysbasic = (EditText) findViewById(R.id.woactivity_udzysbasic);
        perinspr = (CheckBox) findViewById(R.id.woactivity_perinspr);
        udprobdesc = (EditText) findViewById(R.id.woactivity_udprobdesc);
        udzglimit = (TextView) findViewById(R.id.woactivity_udzglimit);
        lead = (TextView) findViewById(R.id.woactivity_lead);
        udzgmeasure = (EditText) findViewById(R.id.woactivity_udzgmeasure);
        udzgresult = (EditText) findViewById(R.id.woactivity_udzgresult);
        udremark = (EditText) findViewById(R.id.woactivity_udremark);
        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.work_save);
        cancel = (Button) findViewById(R.id.work_cancel);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText("新增" + getResources().getString(R.string.title_activity_woactivitydetails));

        taskid.setText(getIntent().getIntExtra("taskid", 0) + "");

        udstarttime.setOnClickListener(new DateChecked(udstarttime));
        udendtime.setOnClickListener(new DateChecked(udendtime));
        udzglimit.setOnClickListener(new DateTimeChecked(udzglimit));
        lead.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));

        confirm.setOnClickListener(confirmOnClickListener);
        cancel.setOnClickListener(cancelOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.TASKID = taskid.getText().toString();
        woactivity.WOJO1 = wojo1.getText().toString();
        woactivity.UDSTARTTIME = udstarttime.getText().toString();
        woactivity.UDENDTIME = udendtime.getText().toString();
        woactivity.UDZYSBASIC = udzysbasic.getText().toString();
        woactivity.PERINSPR = perinspr.isChecked() ? "Y" : "N";
        woactivity.UDPROBDESC = udprobdesc.getText().toString();
        woactivity.UDZGLIMIT = udzglimit.getText().toString();
        woactivity.LEAD = lead.getText().toString();
        woactivity.UDZGMEASURE = udzgmeasure.getText().toString();
        woactivity.UDZGRESULT = udzgresult.getText().toString();
        woactivity.UDREMARK = udremark.getText().toString();
        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("woactivity", getWoactivity());
            WoactivityAddNewActivity_AA.this.setResult(1, intent);
            Toast.makeText(WoactivityAddNewActivity_AA.this, "任务本地新增成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    class DateChecked implements View.OnClickListener {
        TextView textView;

        public DateChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateSelect(WoactivityAddNewActivity_AA.this, textView).showDialog();
        }
    }

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(WoactivityAddNewActivity_AA.this, textView).showDialog();
        }
    }

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;
        int optiontype;

        private LayoutOnClickListener(int requestCode, int optiontype) {
            this.requestCode = requestCode;
            this.optiontype = optiontype;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WoactivityAddNewActivity_AA.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (requestCode) {
            case 1:
                option = (Option) data.getSerializableExtra("option");
                lead.setText(option.getName());
                break;
        }
    }
}
