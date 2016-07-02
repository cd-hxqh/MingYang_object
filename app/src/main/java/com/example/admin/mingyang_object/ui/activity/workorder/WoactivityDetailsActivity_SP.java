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
 * 排查工单任务详情页面
 */
public class WoactivityDetailsActivity_SP extends BaseActivity {
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
    private TextView description;//描述
    private TextView wojo1;//系统/项目
    private TextView wojo2;//子系统/子项目
    private TextView wojo3;//检查/检修方法
    private TextView wojo4;//kks编码
    private CheckBox perinspr;//排查结果
    private EditText udinsunit;//排查部位
    private TextView udrprrsb;//整改责任人
    private EditText udprobdesc;//问题描述
    private EditText udzgmeasure;//整改措施及建议
    private TextView udzglimit;//整改期限
    private EditText udzgstu;//整改情况回复
    private EditText udzgresult;//整改结果验证
    private LinearLayout buttonlayout;
    private Button confirm;//确定


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_sp);

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
        description = (TextView) findViewById(R.id.woactivity_description);
        wojo1 = (TextView) findViewById(R.id.woactivity_wojo1);
        wojo2 = (TextView) findViewById(R.id.woactivity_wojo2);
        wojo3 = (TextView) findViewById(R.id.woactivity_wojo3);
        wojo4 = (TextView) findViewById(R.id.woactivity_wojo4);
        perinspr = (CheckBox) findViewById(R.id.woactivity_perinspr);
        udinsunit = (EditText) findViewById(R.id.woactivity_udinsunit);
        udrprrsb = (TextView) findViewById(R.id.woactivity_udrprrsb);
        udprobdesc = (EditText) findViewById(R.id.woactivity_udprobdesc);
        udzgmeasure = (EditText) findViewById(R.id.woactivity_udzgmeasure);
        udzglimit = (TextView) findViewById(R.id.woactivity_udzglimit);
        udzgstu = (EditText) findViewById(R.id.woactivity_udzgstu);
        udzgresult = (EditText) findViewById(R.id.woactivity_udzgresult);

//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.confirm);
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

        taskid.setText(woactivity.TASKID);
        description.setText(woactivity.DESCRIPTION);
        wojo1.setText(woactivity.WOJO1);
        wojo2.setText(woactivity.WOJO2);
        wojo3.setText(woactivity.WOJO3);
        wojo4.setText(woactivity.WOJO4);
        perinspr.setChecked(woactivity.PERINSPR != 0);
        udinsunit.setText(woactivity.UDINSUNIT);
        udrprrsb.setText(woactivity.UDRPRRSB);
        udprobdesc.setText(woactivity.UDPROBDESC);
        udzgmeasure.setText(woactivity.UDZGMEASURE);
        udzglimit.setText(woactivity.UDZGLIMIT);
        udzgstu.setText(woactivity.UDZGSTU);
        udzgresult.setText(woactivity.UDZGRESULT);

        udzglimit.setOnClickListener(new DateTimeChecked(udzglimit));
        udrprrsb.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));

        confirm.setOnClickListener(confirmOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.PERINSPR = perinspr.isChecked() ? 1 : 0;
        woactivity.UDINSUNIT = udinsunit.getText().toString();
        woactivity.UDRPRRSB = udrprrsb.getText().toString();
        woactivity.UDPROBDESC = udprobdesc.getText().toString();
        woactivity.UDZGMEASURE = udzgmeasure.getText().toString();
        woactivity.UDZGSTU = udzgstu.getText().toString();
        woactivity.UDZGRESULT = udzgresult.getText().toString();
        woactivity.UDZGLIMIT = udzglimit.getText().toString();
        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if((woactivity.PERINSPR == (perinspr.isChecked() ? 1 : 0))
                    &&woactivity.UDINSUNIT.equals(udinsunit.getText().toString())
                    &&woactivity.UDRPRRSB.equals(udrprrsb.getText().toString())
                    &&woactivity.UDPROBDESC.equals(udprobdesc.getText().toString())
                    &&woactivity.UDZGMEASURE.equals(udzgmeasure.getText().toString())
                    &&woactivity.UDZGSTU.equals(udzgstu.getText().toString())
                    &&woactivity.UDZGRESULT.equals(udzgresult.getText().toString())
                    &&woactivity.UDZGLIMIT.equals(udzglimit.getText().toString())) {//如果内容没有修改
                intent.putExtra("woactivity",woactivity);
            }else {
                Woactivity woactivity = getWoactivity();
                if(woactivity.TYPE==null||!woactivity.TYPE.equals("add")) {
                    woactivity.TYPE = "update";
                }
                intent.putExtra("woactivity", woactivity);
                Toast.makeText(WoactivityDetailsActivity_SP.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
            }
            intent.putExtra("position", position);
            WoactivityDetailsActivity_SP.this.setResult(2, intent);
            finish();
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = getIntent();
//            intent.putExtra("position", position);
//            if (woactivity.workorderid == null||woactivity.workorderid.equals("")){
//                WoactivityDetailsActivity.this.setResult(3, intent);
//            }else {
//                Woactivity woactivity = getWoactivity();
//                woactivity.optiontype = "delete";
//                intent.putExtra("woactivity", woactivity);
//                WoactivityDetailsActivity.this.setResult(4, intent);
//            }
//            finish();
        }
    };

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(WoactivityDetailsActivity_SP.this, textView).showDialog();
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
            Intent intent = new Intent(WoactivityDetailsActivity_SP.this, OptionActivity.class);
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
                udrprrsb.setText(option.getName());
                break;
        }
    }
}
