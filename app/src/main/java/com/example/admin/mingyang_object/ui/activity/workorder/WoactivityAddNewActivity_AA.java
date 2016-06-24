package com.example.admin.mingyang_object.ui.activity.workorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;


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
    private Button delete;//删除


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
//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
//        confirm = (Button) findViewById(R.id.confirm);
//        delete = (Button) findViewById(R.id.work_delete);
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

        taskid.setText(woactivity.TASKID);
        wojo1.setText(woactivity.WOJO1);
        udstarttime.setText(woactivity.UDSTARTTIME);
        udendtime.setText(woactivity.UDENDTIME);
        udzysbasic.setText(woactivity.UDZYSBASIC);
        perinspr.setChecked(woactivity.PERINSPR!=null&&woactivity.PERINSPR.equals("Y"));
        udprobdesc.setText(woactivity.UDPROBDESC);
        udzglimit.setText(woactivity.UDZGLIMIT);
        lead.setText(woactivity.LEAD);
        udzgmeasure.setText(woactivity.UDZGMEASURE);
        udzgresult.setText(woactivity.UDZGRESULT);
        udremark.setText(woactivity.UDREMARK);

//        confirm.setOnClickListener(confirmOnClickListener);
//        delete.setOnClickListener(deleteOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;

        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent = getIntent();
//            if(woactivity.taskid.equals(taskid.getText().toString())
//                    &&woactivity.wojo1.equals(wojo1.getText().toString())
//                    &&woactivity.description.equals(description.getText().toString())
//                    &&woactivity.wojo2.equals(wojo2.getText().toString())
//                    &&woactivity.udisdo.equals(udisdo.getText().toString())
//                    &&woactivity.udisyq.equals(udisyq.getText().toString())
//                    &&woactivity.udyqyy.equals(udyqyy.getText().toString())
//                    &&woactivity.udremark.equals(udremark.getText().toString())) {//如果内容没有修改
//                intent.putExtra("woactivity",woactivity);
//            }else {
//                Woactivity woactivity = getWoactivity();
//                if(woactivity.optiontype==null||!woactivity.optiontype.equals("add")) {
//                    woactivity.optiontype = "update";
//                }
//                intent.putExtra("woactivity", woactivity);
//                Toast.makeText(WoactivityDetailsActivity.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
//            }
//            intent.putExtra("position", position);
//            WoactivityDetailsActivity.this.setResult(2, intent);
//            finish();
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
}
