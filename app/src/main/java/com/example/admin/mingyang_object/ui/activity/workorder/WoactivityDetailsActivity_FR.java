package com.example.admin.mingyang_object.ui.activity.workorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.admin.mingyang_object.utils.DateTimeSelect;


/**
 * Created by think on 2016/6/29.
 * 故障工单任务详情页面
 */
public class WoactivityDetailsActivity_FR extends BaseActivity {
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
    private EditText description;//描述
    private TextView owner;//负责人
    private TextView udacstarttime2;//实际开始时间
    private TextView udacstoptime2;//实际完成时间
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除

    private boolean isChange;//选项选择后是否改变


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_fr);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        taskid = (TextView) findViewById(R.id.work_woactivity_taskid);
        description = (EditText) findViewById(R.id.woactivity_description);
        owner = (TextView) findViewById(R.id.woactivity_owner);
        udacstarttime2 = (TextView) findViewById(R.id.woactivity_udacstarttime2);
        udacstoptime2 = (TextView) findViewById(R.id.woactivity_udacstoptime2);

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
        description.setText(woactivity.DESCRIPTION);
        owner.setText(woactivity.OWNERNAME);
        udacstarttime2.setText(woactivity.UDACSTARTTIME);
        udacstoptime2.setText(woactivity.UDACSTOPTIME);
        if (workOrder.UDSTATUS.equals("新建")||workOrder.UDSTATUS.equals("待审批")) {
            owner.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
        }
        if (workOrder.UDSTATUS.equals("进行中")||workOrder.UDSTATUS.equals("已反馈")||
                workOrder.UDSTATUS.equals("物料已申请")||workOrder.UDSTATUS.equals("驳回")) {
            udacstarttime2.setOnClickListener(new DateTimeChecked(udacstarttime2));
            udacstoptime2.setOnClickListener(new DateTimeChecked(udacstoptime2));
        }
        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.DESCRIPTION = description.getText().toString();
//        woactivity.OWNER = owner.getText().toString();
        woactivity.UDACSTARTTIME2 = udacstarttime2.getText().toString();
        woactivity.UDACSTOPTIME2 = udacstoptime2.getText().toString();
        return woactivity;
    }

    private boolean isOK() {
//        if (owner.getText().toString().equals("")){
//            Toast.makeText(WoactivityDetailsActivity_FR.this,"负责人不能为空",Toast.LENGTH_SHORT).show();
//            return false;
//        }else if (udacstarttime.getText().toString().equals("")){
//            Toast.makeText(WoactivityDetailsActivity_FR.this,"计划开始时间不能为空",Toast.LENGTH_SHORT).show();
//            return false;
//        }else if (udacstoptime.getText().toString().equals("")){
//            Toast.makeText(WoactivityDetailsActivity_FR.this,"计划完成时间不能为空",Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOK()) {
                Intent intent = getIntent();
                if (((!(woactivity.DESCRIPTION == null && description.getText().toString().equals(""))
                        || woactivity.DESCRIPTION.equals(description.getText().toString()))
//                        && ((woactivity.OWNER == null && owner.getText().toString().equals(""))
//                        || woactivity.OWNER.equals(owner.getText().toString()))
                        && ((woactivity.UDACSTARTTIME2 == null && udacstarttime2.getText().toString().equals(""))
                        || woactivity.UDACSTARTTIME2.equals(udacstarttime2.getText().toString()))
                        && ((woactivity.UDACSTOPTIME2 == null && udacstoptime2.getText().toString().equals(""))
                        || woactivity.UDACSTOPTIME2.equals(udacstoptime2.getText().toString()))&&!isChange)
                        ||workOrder.UDSTATUS.equals("已关闭")||workOrder.UDSTATUS.equals("已取消")) {//如果内容没有修改
                    intent.putExtra("woactivity", woactivity);
                } else {
                    Woactivity woactivity = getWoactivity();
                    if (woactivity.TYPE == null || !woactivity.TYPE.equals("add")) {
                        woactivity.TYPE = "update";
                    }
                    intent.putExtra("woactivity", woactivity);
                    Toast.makeText(WoactivityDetailsActivity_FR.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("position", position);
                WoactivityDetailsActivity_FR.this.setResult(2, intent);
                finish();
            }
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("position", position);
            if (!woactivity.isUpload) {
                WoactivityDetailsActivity_FR.this.setResult(3, intent);
            } else {
                Woactivity woactivity = getWoactivity();
                woactivity.TYPE = "delete";
                intent.putExtra("woactivity", woactivity);
                WoactivityDetailsActivity_FR.this.setResult(4, intent);
            }
            finish();
        }
    };

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(WoactivityDetailsActivity_FR.this, textView).showDialog();
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
            Intent intent = new Intent(WoactivityDetailsActivity_FR.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    owner.setText(option.getDesc());
                    if (woactivity.OWNER!=null&&!woactivity.OWNER.equals(option.getName())) {
                        woactivity.OWNERNAME = option.getDesc();
                        woactivity.OWNER = option.getName();
                        isChange = true;
                    }
                    break;
            }
        }
    }
}
