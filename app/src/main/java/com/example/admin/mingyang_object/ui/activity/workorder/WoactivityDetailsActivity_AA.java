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
 * 终验收工单任务详情页面
 */
public class WoactivityDetailsActivity_AA extends BaseActivity {
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
    private EditText udzyscorn;//工作任务
    private TextView udinspoby;//负责人
    private TextView udstarttime;//开始时间
    private TextView udendtime;//结束时间
    private EditText udzysbasic;//执行标准
    private CheckBox perinspr;//终验收结果
    private EditText udprobdesc;//问题描述
    private TextView udzglimit;//整改期限
    private TextView udrprrsb;//整改责任人
    private EditText udzgmeasure;//整改方案
    private EditText udzgresult;//整改完成情况
    private EditText udremark;//备注
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除

    private boolean isChange;//选项选择后是否改变


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_aa);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        taskid = (TextView) findViewById(R.id.work_woactivity_taskid);
        udzyscorn = (EditText) findViewById(R.id.work_woactivity_wojo1);
        udinspoby = (TextView) findViewById(R.id.woactivity_udinspoby);
        udstarttime = (TextView) findViewById(R.id.woactivity_udstarttime);
        udendtime = (TextView) findViewById(R.id.woactivity_udendtime);
        udzysbasic = (EditText) findViewById(R.id.woactivity_udzysbasic);
        perinspr = (CheckBox) findViewById(R.id.woactivity_perinspr);
        udprobdesc = (EditText) findViewById(R.id.woactivity_udprobdesc);
        udzglimit = (TextView) findViewById(R.id.woactivity_udzglimit);
        udrprrsb = (TextView) findViewById(R.id.woactivity_lead);
        udzgmeasure = (EditText) findViewById(R.id.woactivity_udzgmeasure);
        udzgresult = (EditText) findViewById(R.id.woactivity_udzgresult);
        udremark = (EditText) findViewById(R.id.woactivity_udremark);
//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.work_save);
        delete = (Button) findViewById(R.id.work_cancel);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_woactivitydetails));
        delete.setText(R.string.work_delete);

        taskid.setText(woactivity.TASKID);
        udzyscorn.setText(woactivity.UDZYSCORN);
        udinspoby.setText(woactivity.UDINSPOBYNAME);
        udstarttime.setText(woactivity.UDSTARTTIME);
        udendtime.setText(woactivity.UDENDTIME);
        udzysbasic.setText(woactivity.UDZYSBASIC);
        perinspr.setChecked(woactivity.PERINSPR.equals("Y"));
        udprobdesc.setText(woactivity.UDPROBDESC);
        udzglimit.setText(woactivity.UDZGLIMIT);
        udrprrsb.setText(woactivity.UDRPRRSBNAME);
        udzgmeasure.setText(woactivity.UDZGMEASURE);
        udzgresult.setText(woactivity.UDZGRESULT);
        udremark.setText(woactivity.UDREMARK);

        udstarttime.setOnClickListener(new DateChecked(udstarttime));
        udendtime.setOnClickListener(new DateChecked(udendtime));
        udzglimit.setOnClickListener(new DateTimeChecked(udzglimit));
        udrprrsb.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
        udinspoby.setOnClickListener(new LayoutOnClickListener(2,Constants.PERSONCODE));
        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.TASKID = taskid.getText().toString();
        woactivity.UDZYSCORN = udzyscorn.getText().toString();
        woactivity.UDSTARTTIME = udstarttime.getText().toString();
        woactivity.UDENDTIME = udendtime.getText().toString();
        woactivity.UDZYSBASIC = udzysbasic.getText().toString();
        woactivity.PERINSPR = perinspr.isChecked() ? "Y" : "N";
        woactivity.UDPROBDESC = udprobdesc.getText().toString();
        woactivity.UDZGLIMIT = udzglimit.getText().toString();
//        woactivity.LEAD = lead.getText().toString();
        woactivity.UDZGMEASURE = udzgmeasure.getText().toString();
        woactivity.UDZGRESULT = udzgresult.getText().toString();
        woactivity.UDREMARK = udremark.getText().toString();
        return woactivity;
    }

    private boolean isOK(){
        if (udstarttime.getText().toString().equals("")){
            Toast.makeText(WoactivityDetailsActivity_AA.this,"开始时间不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (udendtime.getText().toString().equals("")){
            Toast.makeText(WoactivityDetailsActivity_AA.this,"结束时间不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (udzyscorn.getText().toString().equals("")){
            Toast.makeText(WoactivityDetailsActivity_AA.this,"工作任务不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (udzglimit.getText().toString().equals("")){
            Toast.makeText(WoactivityDetailsActivity_AA.this,"整改期限不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (udrprrsb.getText().toString().equals("")){
            Toast.makeText(WoactivityDetailsActivity_AA.this,"整改责任人不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOK()) {
                Intent intent = getIntent();
                if ((woactivity.UDZYSCORN.equals(udzyscorn.getText().toString())
//                        &&woactivity.UDINSPOBYNAME.equals(udinspoby.getText().toString())
                        && woactivity.UDSTARTTIME.equals(udstarttime.getText().toString())
                        && woactivity.UDENDTIME.equals(udendtime.getText().toString())
                        && woactivity.UDZYSBASIC.equals(udzysbasic.getText().toString())
                        && (woactivity.PERINSPR.equals(perinspr.isChecked() ? "Y" : "N"))
                        && woactivity.UDPROBDESC.equals(udprobdesc.getText().toString())
                        && woactivity.UDZGLIMIT.equals(udzglimit.getText().toString())
//                        && woactivity.UDRPRRSBNAME.equals(udrprrsb.getText().toString())
                        && woactivity.UDZGMEASURE.equals(udzgmeasure.getText().toString())
                        && woactivity.UDZGRESULT.equals(udzgresult.getText().toString())
                        && woactivity.UDREMARK.equals(udremark.getText().toString()))&&!isChange) {//如果内容没有修改
                    intent.putExtra("woactivity", woactivity);
                } else {
                    Woactivity woactivity = getWoactivity();
                    if (woactivity.TYPE == null || !woactivity.TYPE.equals("add")) {
                        woactivity.TYPE = "update";
                    }
                    intent.putExtra("woactivity", woactivity);
                    Toast.makeText(WoactivityDetailsActivity_AA.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("position", position);
                WoactivityDetailsActivity_AA.this.setResult(2, intent);
                finish();
            }
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("position", position);
            if (!woactivity.isUpload){
                WoactivityDetailsActivity_AA.this.setResult(3, intent);
            }else {
                Woactivity woactivity = getWoactivity();
                woactivity.TYPE = "delete";
                intent.putExtra("woactivity", woactivity);
                WoactivityDetailsActivity_AA.this.setResult(4, intent);
            }
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
            new DateSelect(WoactivityDetailsActivity_AA.this, textView).showDialog();
        }
    }

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(WoactivityDetailsActivity_AA.this, textView).showDialog();
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
            Intent intent = new Intent(WoactivityDetailsActivity_AA.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data!=null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    udrprrsb.setText(option.getDesc());
                    if (woactivity.UDRPRRSB!=null&&!woactivity.UDRPRRSB.equals(option.getName())) {
                        woactivity.UDRPRRSBNAME = option.getDesc();
                        woactivity.UDRPRRSB = option.getName();
                        isChange = true;
                    }
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby.setText(option.getDesc());
                    if (woactivity.UDINSPOBY!=null&&!woactivity.UDINSPOBY.equals(option.getName())) {
                        woactivity.UDINSPOBYNAME = option.getDesc();
                        woactivity.UDINSPOBY = option.getName();
                        isChange = true;
                    }
                    break;
            }
        }
    }
}
