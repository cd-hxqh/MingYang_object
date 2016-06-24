package com.example.admin.mingyang_object.ui.activity.workorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.example.admin.mingyang_object.model.UddebugWorkOrderLine;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;


/**
 * Created by think on 2016/6/21.
 * 调试工单子表详情页面
 */
public class UddebugWorkOrderLineDetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private UddebugWorkOrderLine uddebugWorkOrderLine = new UddebugWorkOrderLine();
    private DebugWorkOrder workOrder;
    private int position;

    private TextView winddrivengeneratornum;//风机编码
    private EditText fjlocation;//机台号
    private TextView dynamicdebugdate;//调试日期
    private TextView synchronizationdebugdate;//并网运行日期
    private TextView time1;//静态调试日期
    private TextView time2;//动态调试日期
    private EditText vesion;//程序版本号
    private TextView responsibleperson;//调试责任人
    private TextView debugleader;//调试组长
    private TextView crew;//调试工程师1
    private TextView crew2;//调试工程师2
    private TextView crew3;//调试工程师3
    private EditText question;//问题记录
    private EditText dispose;//处理过程
    private EditText remark;//备注
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uddebugworkorderline_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        uddebugWorkOrderLine = (UddebugWorkOrderLine) getIntent().getSerializableExtra("uddebugWorkOrderLine");
        workOrder = (DebugWorkOrder) getIntent().getSerializableExtra("workOrder");
        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        winddrivengeneratornum = (TextView) findViewById(R.id.debug_winddrivengeneratornum);
        fjlocation = (EditText) findViewById(R.id.debug_fjlocation);
        dynamicdebugdate = (TextView) findViewById(R.id.debug_dynamicdebugdate);
        synchronizationdebugdate = (TextView) findViewById(R.id.debug_synchronizationdebugdate);
        time1 = (TextView) findViewById(R.id.debug_time1);
        time2 = (TextView) findViewById(R.id.debug_time2);
        vesion = (EditText) findViewById(R.id.debug_vesion);
        responsibleperson = (TextView) findViewById(R.id.debug_responsibleperson);
        debugleader = (TextView) findViewById(R.id.debug_debugleader);
        crew = (TextView) findViewById(R.id.debug_crew);
        crew2 = (TextView) findViewById(R.id.debug_crew2);
        crew3 = (TextView) findViewById(R.id.debug_crew3);
        question = (EditText) findViewById(R.id.debug_question);
        dispose = (EditText) findViewById(R.id.debug_dispose);
        remark = (EditText) findViewById(R.id.debug_remark);
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
        titleTextView.setText(getResources().getString(R.string.title_activity_uddebugworkorderlinedetails));

        winddrivengeneratornum.setText(uddebugWorkOrderLine.WINDDRIVENGENERATORNUM);
        fjlocation.setText(uddebugWorkOrderLine.FJLOCATION);
        dynamicdebugdate.setText(uddebugWorkOrderLine.DYNAMICDEBUGDATE);
        synchronizationdebugdate.setText(uddebugWorkOrderLine.SYNCHRONIZATIONDEBUGDATE);
        time1.setText(uddebugWorkOrderLine.TIME1);
        time2.setText(uddebugWorkOrderLine.TIME2);
        vesion.setText(uddebugWorkOrderLine.VESION);
        responsibleperson.setText(uddebugWorkOrderLine.RESPONSIBLEPERSON);
        debugleader.setText(uddebugWorkOrderLine.DEBUGLEADER);
        crew.setText(uddebugWorkOrderLine.CREW);
        crew2.setText(uddebugWorkOrderLine.CREW2);
        crew3.setText(uddebugWorkOrderLine.CREW3);
        question.setText(uddebugWorkOrderLine.QUESTION);
        dispose.setText(uddebugWorkOrderLine.DISPOSE);
        remark.setText(uddebugWorkOrderLine.REMARK);
//        confirm.setOnClickListener(confirmOnClickListener);
//        delete.setOnClickListener(deleteOnClickListener);
    }

    private UddebugWorkOrderLine getWoactivity() {
        UddebugWorkOrderLine uddebugWorkOrderLine = this.uddebugWorkOrderLine;

        return uddebugWorkOrderLine;
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
