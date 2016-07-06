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
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.utils.DateTimeSelect;


/**
 * Created by think on 2016/6/21.
 * 技改工单任务详情页面
 */
public class WoactivityDetailsActivity_TP extends BaseActivity {
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
    private EditText invcontent;//
    private EditText udprobdesc;//
    private TextView udsaftydesc;//
    private TextView udzglimit;//整改期限
    private EditText udzgmeasure;//
    private EditText udzgstu;//
    private EditText udzgresult;//
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_tp);

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
        invcontent = (EditText) findViewById(R.id.woactivity_invcontent);
        udprobdesc = (EditText) findViewById(R.id.woactivity_udprobdesc);
        udsaftydesc = (TextView) findViewById(R.id.woactivity_udsaftydesc);
        udzgmeasure = (EditText) findViewById(R.id.woactivity_udzgmeasure);
        udzglimit = (TextView) findViewById(R.id.woactivity_udzglimit);
        udzgstu = (EditText) findViewById(R.id.woactivity_udzgstu);
        udzgresult = (EditText) findViewById(R.id.woactivity_udzgresult);

//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.confirm);
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
        titleTextView.setText(getResources().getString(R.string.title_activity_woactivitydetails));

        taskid.setText(woactivity.TASKID);
        description.setText(woactivity.DESCRIPTION);
        wojo1.setText(woactivity.WOJO1);
        wojo2.setText(woactivity.WOJO2);
        wojo3.setText(woactivity.WOJO3);
        wojo4.setText(woactivity.WOJO4);
        invcontent.setText(woactivity.INVCONTENT);
        udprobdesc.setText(woactivity.UDPROBDESC);
        udsaftydesc.setText(woactivity.UDSAFTYDESC);
        udzgmeasure.setText(woactivity.UDZGMEASURE);
        udzglimit.setText(woactivity.UDZGLIMIT);
        udzgstu.setText(woactivity.UDZGSTU);
        udzgresult.setText(woactivity.UDZGRESULT);

        udzglimit.setOnClickListener(new DateTimeChecked(udzglimit));
        confirm.setOnClickListener(confirmOnClickListener);
//        delete.setOnClickListener(deleteOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.INVCONTENT = invcontent.getText().toString();
        woactivity.UDPROBDESC = udprobdesc.getText().toString();
        woactivity.UDSAFTYDESC = udsaftydesc.getText().toString();
        woactivity.UDZGMEASURE = udzgmeasure.getText().toString();
        woactivity.UDZGLIMIT = udzglimit.getText().toString();
        woactivity.UDZGSTU = udzgstu.getText().toString();
        woactivity.UDZGRESULT = udzgresult.getText().toString();
        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if((woactivity.INVCONTENT==null||woactivity.INVCONTENT.equals(invcontent.getText().toString()))
                    &&(woactivity.UDPROBDESC==null||woactivity.UDPROBDESC.equals(udprobdesc.getText().toString()))
                    &&(woactivity.UDSAFTYDESC==null||woactivity.UDSAFTYDESC.equals(udsaftydesc.getText().toString()))
                    &&(woactivity.UDZGMEASURE==null||woactivity.UDZGMEASURE.equals(udzgmeasure.getText().toString()))
                    &&(woactivity.UDZGLIMIT==null||woactivity.UDZGLIMIT.equals(udzglimit.getText().toString()))
                    &&(woactivity.UDZGSTU==null||woactivity.UDZGSTU.equals(udzgstu.getText().toString()))
                    &&(woactivity.UDZGRESULT==null||woactivity.UDZGRESULT.equals(udzgresult.getText().toString()))) {//如果内容没有修改
                intent.putExtra("woactivity",woactivity);
            }else {
                Woactivity woactivity = getWoactivity();
                if(woactivity.TYPE==null||!woactivity.TYPE.equals("add")) {
                    woactivity.TYPE = "update";
                }
                intent.putExtra("woactivity", woactivity);
                Toast.makeText(WoactivityDetailsActivity_TP.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
            }
            intent.putExtra("position", position);
            WoactivityDetailsActivity_TP.this.setResult(2, intent);
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
            new DateTimeSelect(WoactivityDetailsActivity_TP.this, textView).showDialog();
        }
    }
}
