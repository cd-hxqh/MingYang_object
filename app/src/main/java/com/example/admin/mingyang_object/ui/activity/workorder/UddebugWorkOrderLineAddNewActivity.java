package com.example.admin.mingyang_object.ui.activity.workorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.UddebugWorkOrderLine;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.DateSelect;


/**
 * Created by think on 2016/6/21.
 * 调试工单子表新增页面
 */
public class UddebugWorkOrderLineAddNewActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private UddebugWorkOrderLine uddebugWorkOrderLine = new UddebugWorkOrderLine();
    private DebugWorkOrder workOrder = new DebugWorkOrder();
    private int position;

    private TextView winddrivengeneratornum;//风机编码(机位号)
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
    private String pronum;

    private Button cancel;//取消
    private Button save;//保存

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uddebugworkorderline_details);

        geiIntentData();
        findViewById();
        initView();
        Log.e("调试工单","新增调试工单子表");
        Intent intent=getIntent();
        pronum=intent.getStringExtra("pronum");
        Log.e("调试工单","新增调试工单子表"+pronum);
    }

    private void geiIntentData() {
//        uddebugWorkOrderLine = (UddebugWorkOrderLine) getIntent().getSerializableExtra("uddebugWorkOrderLine");
//        workOrder = (DebugWorkOrder) getIntent().getSerializableExtra("workOrder");
//        position = getIntent().getIntExtra("position", 0);
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
        cancel=(Button)findViewById(R.id.work_cancel);
        save=(Button)findViewById(R.id.work_save);
    }

    @Override
    protected void initView() {

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText("新增" + getResources().getString(R.string.title_activity_uddebugworkorderlinedetails));

        //风机编码
        winddrivengeneratornum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("调试工单","选择风机编码");
                Intent intent = new Intent(UddebugWorkOrderLineAddNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.UDLOCNUMCODE);
                intent.putExtra("udprojectnum", pronum);
                startActivityForResult(intent, 6);

            }
        });
        //调试日期
        dynamicdebugdate.setOnClickListener(new DateChecked(dynamicdebugdate));
        //并网运行日期
        synchronizationdebugdate.setOnClickListener(new DateChecked(synchronizationdebugdate));
        //静态调试日期
        time1.setOnClickListener(new DateChecked(time1));
        //动态调试日期
        time2.setOnClickListener(new DateChecked(time2));
        //调试责任人
        responsibleperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","选择调试责任人");
                Intent intent = new Intent(UddebugWorkOrderLineAddNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 7);
            }
        });
        //调试组长
        debugleader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","选择调试组长");
                Intent intent = new Intent(UddebugWorkOrderLineAddNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 8);
            }
        });
        //调试工程师1
        crew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","选择调试工程师1");
                Intent intent = new Intent(UddebugWorkOrderLineAddNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 9);
            }
        });

        //调试工程师2
        crew2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","选择调试工程师2");
                Intent intent = new Intent(UddebugWorkOrderLineAddNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 10);
            }
        });
        //调试工程师3
        crew3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","选择调试工程师3");
                Intent intent = new Intent(UddebugWorkOrderLineAddNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 11);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","取消");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","保存");
            }
        });
//        confirm.setOnClickListener(confirmOnClickListener);
//        delete.setOnClickListener(deleteOnClickListener);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.e("调试工单","选择返回结果");
        Option option;
        if (requestCode==6)
        {
            option = (Option) data.getSerializableExtra("option");
            winddrivengeneratornum.setText(option.getName());

        }
        //调试责任人
        if (requestCode==7)
        {
            option = (Option) data.getSerializableExtra("option");
            responsibleperson.setText(option.getName());

        }
        //调试组长
        if (requestCode==8)
        {
            option = (Option) data.getSerializableExtra("option");
            debugleader.setText(option.getName());

        }
        //调试工程师1
        if (requestCode==9)
        {
            option = (Option) data.getSerializableExtra("option");
            crew.setText(option.getName());

        }
        //调试工程师2
        if (requestCode==10)
        {
            option = (Option) data.getSerializableExtra("option");
            crew2.setText(option.getName());

        }
        //调试工程师3
        if (requestCode==11)
        {
            option = (Option) data.getSerializableExtra("option");
            crew3.setText(option.getName());

        }
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
    class DateChecked implements View.OnClickListener {
        TextView textView;

        public DateChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateSelect(UddebugWorkOrderLineAddNewActivity.this, textView).showDialog();
        }
    }
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
