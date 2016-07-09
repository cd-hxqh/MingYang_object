package com.example.admin.mingyang_object.ui.activity.udpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.UdprorunlogLine3;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.utils.GetDateAndTime;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * Created by think on 2016/7/6.
 * 工作日报新增页面
 */
public class Udprorunlog_Line3AddNewActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private UdprorunlogLine3 udprorunlogLine3 = new UdprorunlogLine3();
    private Udprorunlog udprorunlog;
    private int position;

    public TextView runlogdate;//日期
    public EditText description;//描述
    public TextView weather;//天气
    public EditText tem;//温度(℃)
    public EditText windspeed;//平均风速(m/s)

    public TextView worktype;//工作性质
    public EditText workpg;//工作班成员
    public EditText workcron;//工作任务
    public TextView compsta;//完成情况
    public EditText situation;//异常情况说明
    public EditText remark;//备注

    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//删除

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udprorunlog_line3_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
//        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
//        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        runlogdate = (TextView) findViewById(R.id.udprorunlog_line3_runlogdate);
        description = (EditText) findViewById(R.id.udprorunlog_line3_description);
        weather = (TextView) findViewById(R.id.udprorunlog_line3_weather);
        tem = (EditText) findViewById(R.id.udprorunlog_line3_tem);
        windspeed = (EditText) findViewById(R.id.udprorunlog_line3_windspeed);

        worktype = (TextView) findViewById(R.id.udprorunlog_line3_worktype);
        workpg = (EditText) findViewById(R.id.udprorunlog_line3_workpg);
        workcron = (EditText) findViewById(R.id.udprorunlog_line3_workcron);
        compsta = (TextView) findViewById(R.id.udprorunlog_line3_compsta);
        situation = (EditText) findViewById(R.id.udprorunlog_line3_situation);
        remark = (EditText) findViewById(R.id.udprorunlog_line3_remark);

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
        titleTextView.setText("新增" + getResources().getString(R.string.udprorunlog_line3_details_title));

        runlogdate.setOnClickListener(new DateChecked(runlogdate));
        weather.setOnClickListener(new NormalListDialogOnClickListener(weather));
        worktype.setOnClickListener(new NormalListDialogOnClickListener(worktype));
        compsta.setOnClickListener(new NormalListDialogOnClickListener(compsta));
        confirm.setOnClickListener(confirmOnClickListener);
        cancel.setOnClickListener(cancelOnClickListener);
    }

    private UdprorunlogLine3 getUdprorunlogLine3() {
        UdprorunlogLine3 udprorunlogLine3 = this.udprorunlogLine3;
        udprorunlogLine3.RUNLOGDATE = runlogdate.getText().toString();
        udprorunlogLine3.DESCRIPTION = description.getText().toString();
        udprorunlogLine3.WEATHER = weather.getText().toString();
        udprorunlogLine3.TEM = tem.getText().toString().equals("")?(0.00):Double.parseDouble(tem.getText().toString());
        udprorunlogLine3.WINDSPEED = windspeed.getText().toString().equals("")?(0.00):Double.parseDouble(windspeed.getText().toString());
        udprorunlogLine3.WORKTYPE = worktype.getText().toString();
        udprorunlogLine3.WORKPG = workpg.getText().toString();
        udprorunlogLine3.WORKCRON = workcron.getText().toString();
        udprorunlogLine3.COMPSTA = compsta.getText().toString();
        udprorunlogLine3.SITUATION = situation.getText().toString();
        udprorunlogLine3.REMARK = remark.getText().toString();
        udprorunlogLine3.TYPE = "add";
        return udprorunlogLine3;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("udprorunlogLine3", getUdprorunlogLine3());
            Udprorunlog_Line3AddNewActivity.this.setResult(1, intent);
            Toast.makeText(Udprorunlog_Line3AddNewActivity.this, "工作日报本地新增成功", Toast.LENGTH_SHORT).show();
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
            new DateSelect(Udprorunlog_Line3AddNewActivity.this, textView).showDialog();
        }
    }

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(Udprorunlog_Line3AddNewActivity.this, textView).showDialog();
        }
    }

    private class NormalListDialogOnClickListener implements View.OnClickListener {
        TextView textView;

        public NormalListDialogOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            NormalListDialog(textView);
        }
    }

    private void NormalListDialog(final TextView textView) {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();
        if (textView == weather) {
            types = getResources().getStringArray(R.array.weather_array);
        }else if (textView == worktype){
            types = getResources().getStringArray(R.array.worktype_array);
        }else if (textView == compsta){
            types = getResources().getStringArray(R.array.compsta_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udprorunlog_Line3AddNewActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(mMenuItems.get(position).mOperName);
                dialog.dismiss();
            }
        });
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
            Intent intent = new Intent(Udprorunlog_Line3AddNewActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            intent.putExtra("udprojectnum",udprorunlog.getPRONUM());
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (requestCode) {
//            case 1:
//                if (data!=null) {
//                    option = (Option) data.getSerializableExtra("option");
//                    personid.setText(option.getName());
//                    person.setText(option.getDesc());
//                }
//                break;
        }
    }
}
