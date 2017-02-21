package com.example.admin.eam.ui.activity.workorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.eam.R;
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.model.DebugWorkOrder;
import com.example.admin.eam.model.Option;
import com.example.admin.eam.model.UddebugWorkOrderLine;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.activity.OptionActivity;
import com.example.admin.eam.utils.DateSelect;


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
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("调试工单","保存");
                if (checkData())
                {
                    Intent intent = getIntent();
                    UddebugWorkOrderLine tmp_uddebugWorkOrderLine=getWoactivity();
                    intent.putExtra("uddebugWorkOrderLine", tmp_uddebugWorkOrderLine);
                    Toast.makeText(UddebugWorkOrderLineAddNewActivity.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
                    UddebugWorkOrderLineAddNewActivity.this.setResult(1, intent);
                    finish();
                }
                else
                {

                }
//            intent.putExtra("position", position);
            }
        });

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
        uddebugWorkOrderLine.WINDDRIVENGENERATORNUM=winddrivengeneratornum.getText().toString();//风机编码
        uddebugWorkOrderLine.FJLOCATION=fjlocation.getText().toString();//机台号
        uddebugWorkOrderLine.DYNAMICDEBUGDATE=dynamicdebugdate.getText().toString();//调试日期
        uddebugWorkOrderLine.SYNCHRONIZATIONDEBUGDATE=synchronizationdebugdate.getText().toString();//并网运行日期
        uddebugWorkOrderLine.TIME1=time1.getText().toString();//静态调试日期
        uddebugWorkOrderLine.TIME2=time2.getText().toString();//动态调试日期
        uddebugWorkOrderLine.VESION=vesion.getText().toString();//程序版本号
        uddebugWorkOrderLine.RESPONSIBLEPERSON=responsibleperson.getText().toString();//调试责任人
        uddebugWorkOrderLine.DEBUGLEADER=debugleader.getText().toString();//调试组长
        uddebugWorkOrderLine.CREW=crew.getText().toString();//调试工程师1
        uddebugWorkOrderLine.CREW2=crew2.getText().toString();//调试工程师2
        uddebugWorkOrderLine.CREW3=crew3.getText().toString();//调试工程师3
        uddebugWorkOrderLine.QUESTION=question.getText().toString();//问题记录
        uddebugWorkOrderLine.DISPOSE=dispose.getText().toString();//处理过程
        uddebugWorkOrderLine.REMARK=remark.getText().toString();//备注

        return uddebugWorkOrderLine;
    }
    private boolean checkData()
    {
        //风机编码
        if ( "".equals(winddrivengeneratornum.getText().toString()) )
        {
            Toast.makeText(UddebugWorkOrderLineAddNewActivity.this, "请选择风机编码", Toast.LENGTH_SHORT).show();
            return false;
        }
        //机台号
        if ( "".equals(fjlocation.getText().toString()) )
        {
            Toast.makeText(UddebugWorkOrderLineAddNewActivity.this, "请填写机台号", Toast.LENGTH_SHORT).show();
            return false;
        }
        //调试日期
        if ( "".equals(dynamicdebugdate.getText().toString()) )
        {
            dynamicdebugdate.setText("");

        }
        //并网运行日期
        if ( "".equals(synchronizationdebugdate.getText().toString()) )
        {
            synchronizationdebugdate.setText("");

        }
        //静态调试日期
        if ( "".equals(time1.getText().toString()) )
        {
            time1.setText("");

        }
        //动态调试日期
        if ( "".equals(time2.getText().toString()) )
        {
            time2.setText("");
        }
        //程序版本号
        if ( "".equals(vesion.getText().toString()) )
        {
            Toast.makeText(UddebugWorkOrderLineAddNewActivity.this ,"请填写版本号",Toast.LENGTH_SHORT).show();
            return false;
        }
        //调试责任人
        if ( "".equals(responsibleperson.getText().toString()) )
        {
            responsibleperson.setText("");
        }
        //调试组长
        if ( "".equals(debugleader.getText().toString()) )
        {
            debugleader.setText("");
        }
        //调试工程师1
        if ( "".equals(crew.getText().toString()) )
        {
            crew.setText("");
        }
        //问题纪录
        if ( "".equals(question.getText().toString()) )
        {
            question.setText("");
        }
        //处理过程
        if ( "".equals(dispose.getText().toString()) )
        {
            dispose.setText("");
        }
        //备注
        if ( "".equals(remark.getText().toString()) )
        {
            remark.setText("");
        }

        return true;
    }
    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

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
