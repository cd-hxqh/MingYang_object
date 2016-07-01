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
 * 故障工单任务新增页面
 */
public class WoactivityAddNewActivity_FR extends BaseActivity {
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
    private TextView udacstarttime;//计划开始时间
    private TextView udacstoptime;//计划完成时间
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//取消


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_fr);

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
        description = (EditText) findViewById(R.id.woactivity_description);
        owner = (TextView) findViewById(R.id.woactivity_owner);
        udacstarttime = (TextView) findViewById(R.id.woactivity_udacstarttime);
        udacstoptime = (TextView) findViewById(R.id.woactivity_udacstoptime);

//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
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

        owner.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
        udacstarttime.setOnClickListener(new DateTimeChecked(udacstarttime));
        udacstoptime.setOnClickListener(new DateTimeChecked(udacstoptime));
        confirm.setOnClickListener(confirmOnClickListener);
        cancel.setOnClickListener(cancelOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.TASKID = taskid.getText().toString();
        woactivity.DESCRIPTION = description.getText().toString();
        woactivity.OWNER = owner.getText().toString();
        woactivity.UDACSTARTTIME = udacstarttime.getText().toString();
        woactivity.UDACSTOPTIME = udacstoptime.getText().toString();
        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("woactivity", getWoactivity());
            WoactivityAddNewActivity_FR.this.setResult(1, intent);
            Toast.makeText(WoactivityAddNewActivity_FR.this, "任务本地新增成功", Toast.LENGTH_SHORT).show();
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
            new DateSelect(WoactivityAddNewActivity_FR.this, textView).showDialog();
        }
    }

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(WoactivityAddNewActivity_FR.this, textView).showDialog();
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
            Intent intent = new Intent(WoactivityAddNewActivity_FR.this, OptionActivity.class);
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
                owner.setText(option.getName());
                break;
        }
    }
}
