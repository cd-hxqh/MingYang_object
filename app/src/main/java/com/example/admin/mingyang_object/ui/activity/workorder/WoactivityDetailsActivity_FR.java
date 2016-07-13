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
    private TextView udacstarttime;//计划开始时间
    private TextView udacstoptime;//计划完成时间
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除


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
        udacstarttime = (TextView) findViewById(R.id.woactivity_udacstarttime);
        udacstoptime = (TextView) findViewById(R.id.woactivity_udacstoptime);

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
        owner.setText(woactivity.OWNER);
        udacstarttime.setText(woactivity.UDACSTARTTIME);
        udacstoptime.setText(woactivity.UDACSTOPTIME);
        owner.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
        udacstarttime.setOnClickListener(new DateTimeChecked(udacstarttime));
        udacstoptime.setOnClickListener(new DateTimeChecked(udacstoptime));
        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.DESCRIPTION = description.getText().toString();
        woactivity.OWNER = owner.getText().toString();
        woactivity.UDACSTARTTIME = udacstarttime.getText().toString();
        woactivity.UDACSTOPTIME = udacstoptime.getText().toString();
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
                if (((woactivity.DESCRIPTION == null && description.getText().toString().equals(""))
                        || woactivity.DESCRIPTION.equals(description.getText().toString()))
                        && ((woactivity.OWNER == null && owner.getText().toString().equals(""))
                        || woactivity.OWNER.equals(owner.getText().toString()))
                        && ((woactivity.UDACSTARTTIME == null && udacstarttime.getText().toString().equals(""))
                        || woactivity.UDACSTARTTIME.equals(udacstarttime.getText().toString()))
                        && ((woactivity.UDACSTOPTIME == null && udacstoptime.getText().toString().equals(""))
                        || woactivity.UDACSTOPTIME.equals(udacstoptime.getText().toString()))) {//如果内容没有修改
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
                    owner.setText(option.getName());
                    break;
            }
        }
    }
}
