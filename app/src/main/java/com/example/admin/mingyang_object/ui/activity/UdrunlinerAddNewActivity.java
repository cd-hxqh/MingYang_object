package com.example.admin.mingyang_object.ui.activity;

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
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udrunliner;
import com.example.admin.mingyang_object.model.Udrunlogr;
import com.example.admin.mingyang_object.utils.Date2Select;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by think on 2016/8/17.
 * 工作日志活动新增页面
 */
public class UdrunlinerAddNewActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Udrunliner udrunliner = new Udrunliner();
    private Udrunlogr udrunlogr;
    public ArrayList<Udrunliner> udrunlinerArrayList = new ArrayList<>();
    private int position;

    private TextView logdate;//日期
    private EditText newdesc;//描述
    private TextView weather;//天气
    private EditText tem;//温度℃
    private EditText windspeed;//平均风速(m/s)
    private TextView personattnum;//人员考勤编号
    private TextView worknum;//工作序号
    private EditText workpg;//工作班成员
    private TextView worktype;//工作性质
    private EditText workcron;//工作任务
    private TextView compsta;//完成情况
    private EditText remark;//备注

    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//删除

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udrunliner_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
//        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
        udrunlogr = (Udrunlogr) getIntent().getSerializableExtra("udrunlogr");
        udrunlinerArrayList = (ArrayList<Udrunliner>) getIntent().getSerializableExtra("udrunlinerArrayList");
//        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        logdate = (TextView) findViewById(R.id.udrunliner_logdate);
        newdesc = (EditText) findViewById(R.id.udrunliner_newdesc);
        weather = (TextView) findViewById(R.id.udrunliner_weather);
        tem = (EditText) findViewById(R.id.udrunliner_tem);
        windspeed = (EditText) findViewById(R.id.udrunliner_windspeed);
        personattnum = (TextView) findViewById(R.id.udrunliner_personattnum);
        worknum = (TextView) findViewById(R.id.udrunliner_worknum);
        workpg = (EditText) findViewById(R.id.udrunliner_workpg);
        worktype = (TextView) findViewById(R.id.udrunliner_worktype);
        workcron = (EditText) findViewById(R.id.udrunliner_workcron);
        compsta = (TextView) findViewById(R.id.udrunliner_compsta);
        remark = (EditText) findViewById(R.id.udrunliner_remark);

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
        titleTextView.setText("新增" + getResources().getString(R.string.udrunliner_details));

        logdate.setOnClickListener(new DateChecked(logdate));
        weather.setOnClickListener(new NormalListDialogOnClickListener(weather));
        worktype.setOnClickListener(new NormalListDialogOnClickListener(worktype));
        compsta.setOnClickListener(new NormalListDialogOnClickListener(compsta));

        confirm.setOnClickListener(confirmOnClickListener);
        cancel.setOnClickListener(cancelOnClickListener);
    }

    private Udrunliner getUdrunliner() {
        Udrunliner udrunliner = this.udrunliner;
        udrunliner.LOGDATE = logdate.getText().toString();
        udrunliner.NEWDESC = newdesc.getText().toString();
        udrunliner.WEATHER = weather.getText().toString();
        udrunliner.TEM = tem.getText().toString();
        udrunliner.WINDSPEED = windspeed.getText().toString();
        udrunliner.PERSONATTNUM = personattnum.getText().toString();
        udrunliner.WORKNUM = worknum.getText().toString();
        udrunliner.WORKPG = workpg.getText().toString();
        udrunliner.WORKTYPE = worktype.getText().toString();
        udrunliner.WORKCRON = workcron.getText().toString();
        udrunliner.COMPSTA = compsta.getText().toString();
        udrunliner.REMARK = remark.getText().toString();
        udrunliner.LOGNUM = udrunlogr.LOGNUM;
        udrunliner.TYPE = "add";
        return udrunliner;
    }

    private boolean isOK() {
        if (logdate.getText().toString().equals("")) {
            Toast.makeText(UdrunlinerAddNewActivity.this, "请选择日期", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (weather.getText().toString().equals("")) {
            Toast.makeText(UdrunlinerAddNewActivity.this, "请选择天气", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tem.getText().toString().equals("")) {
            Toast.makeText(UdrunlinerAddNewActivity.this, "请选择温度", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (windspeed.getText().toString().equals("")) {
            Toast.makeText(UdrunlinerAddNewActivity.this, "请选择平均风速", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!logdate.getText().toString().equals("")) {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Calendar c = Calendar.getInstance();
                c.setTime(dateformat.parse(logdate.getText().toString()));
                if (!(c.get(Calendar.YEAR) + "").equals(udrunlogr.YEAR)
                        || (c.get(Calendar.MONTH) + 1) != Integer.parseInt(udrunlogr.MONTH)) {
                    Toast.makeText(UdrunlinerAddNewActivity.this, "年月选择错误", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOK()) {
                Intent intent = getIntent();
                intent.putExtra("udrunliner", getUdrunliner());
                UdrunlinerAddNewActivity.this.setResult(1, intent);
                Toast.makeText(UdrunlinerAddNewActivity.this, "工作日志活动本地新增成功", Toast.LENGTH_SHORT).show();
                finish();
            }

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
            new Date2Select(UdrunlinerAddNewActivity.this, textView).showDialog();
//            setWorknum();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !logdate.getText().toString().equals("")) {
            setWorknum();
        }
    }

    //设置序号
    private void setWorknum() {
        String data = logdate.getText().toString();
        List<String> worknumList = new ArrayList<>();
        for (int i = 0; i < udrunlinerArrayList.size(); i++) {
            if (data.equals(udrunlinerArrayList.get(i).LOGDATE)) {//如果此日期存在
                worknumList.add(udrunlinerArrayList.get(i).WORKNUM);
            }
        }
        if (worknumList.size() == 0) {
            worknum.setText("1");
        } else {
            // 字符串排序
            Collections.sort(worknumList);
            System.out.println(worknumList.toString());
            Collections.sort(worknumList, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return new Double((String) o1).compareTo(new Double((String) o2));
                }
            });
            worknum.setText((Integer.parseInt(worknumList.get(worknumList.size() - 1)) + 1) + "");
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
        } else if (textView == worktype) {
            types = getResources().getStringArray(R.array.worktype2_array);
        } else if (textView == compsta) {
            types = getResources().getStringArray(R.array.compsta_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(UdrunlinerAddNewActivity.this, mMenuItems);
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
            Intent intent = new Intent(UdrunlinerAddNewActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            if (requestCode == 2) {
                if (udrunlogr.PRONUM == null || udrunlogr.PRONUM.equals("")) {
                    Toast.makeText(UdrunlinerAddNewActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    intent.putExtra("udprojectnum", udrunlogr.PRONUM);
                }
            }
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (requestCode) {
                case 1:
                    if (data != null) {
                        option = (Option) data.getSerializableExtra("option");
                    }
                    break;
                case 2:
                    if (data != null) {
                        option = (Option) data.getSerializableExtra("option");
                    }
            }
        }
    }
}
