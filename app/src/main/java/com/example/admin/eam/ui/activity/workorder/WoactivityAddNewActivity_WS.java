package com.example.admin.eam.ui.activity.workorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.Woactivity;
import com.example.admin.eam.model.WorkOrder;
import com.example.admin.eam.ui.activity.BaseActivity;


/**
 * Created by think on 2016/6/21.
 * 定检工单任务新增页面
 */
public class WoactivityAddNewActivity_WS extends BaseActivity {
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
    private EditText allpower;//人员数量
    private EditText alloptime;//耗时(小时)
    private EditText invcontent;//定检规格
    private EditText udzgstu;//检修情况
    private EditText udzgmeasure;//不合格修正措施
    private CheckBox perinspr;//定检结果
    private TextView udrlstopdate;//完成时间
    private EditText udremark;//备注
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_ws);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
//        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
//        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        taskid = (TextView) findViewById(R.id.work_woactivity_taskid);
        description = (EditText) findViewById(R.id.woactivity_description);
        wojo1 = (TextView) findViewById(R.id.woactivity_wojo1);
        wojo2 = (TextView) findViewById(R.id.woactivity_wojo2);
        wojo3 = (TextView) findViewById(R.id.woactivity_wojo3);
        wojo4 = (TextView) findViewById(R.id.woactivity_wojo4);
        allpower = (EditText) findViewById(R.id.woactivity_allpower);
        alloptime = (EditText) findViewById(R.id.woactivity_alloptime);
        invcontent = (EditText) findViewById(R.id.woactivity_invcontent);
        udzgstu = (EditText) findViewById(R.id.woactivity_udzgstu);
        udzgmeasure = (EditText) findViewById(R.id.woactivity_udzgmeasure);
        perinspr = (CheckBox) findViewById(R.id.woactivity_perinspr);
        udrlstopdate = (TextView) findViewById(R.id.woactivity_udrlstopdate);
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
        description.setText(woactivity.DESCRIPTION);
        wojo1.setText(woactivity.WOJO1);
        wojo2.setText(woactivity.WOJO2);
        wojo3.setText(woactivity.WOJO3);
        wojo4.setText(woactivity.WOJO4);
        allpower.setText(woactivity.ALLPOWER);
        alloptime.setText(woactivity.ALLOPTIME);
        invcontent.setText(woactivity.INVCONTENT);
        udzgstu.setText(woactivity.UDZGSTU);
        udzgmeasure.setText(woactivity.UDZGMEASURE);
        perinspr.setChecked(woactivity.PERINSPR.equals("Y"));
        udrlstopdate.setText(woactivity.UDRLSTOPDATE);
        udremark.setText(woactivity.UDREMARK);
//        confirm.setOnClickListener(confirmOnClickListener);
//        delete.setOnClickListener(deleteOnClickListener);
        udremark.setEnabled(false);
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
