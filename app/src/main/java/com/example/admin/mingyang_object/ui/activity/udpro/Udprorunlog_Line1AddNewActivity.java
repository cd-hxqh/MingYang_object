package com.example.admin.mingyang_object.ui.activity.udpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.UdprorunlogLine1;
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
 * Created by think on 2016/7/5.
 * 土建阶段日报新增页面
 */
public class Udprorunlog_Line1AddNewActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private UdprorunlogLine1 udprorunlogLine1 = new UdprorunlogLine1();
    private Udprorunlog udprorunlog;
    private int position;

    private TextView createdate;//创建日期
    private TextView personid;//项目负责人
    private TextView funnum;//风机号
    private TextView prophase;//当前项目阶段
    private EditText land;//征地
    private EditText insideroad;//场内道路
    private EditText outsideroad;//场外道路
    private CheckBox villagerinvolved;//村民阻工
    private EditText remark;//备注
    private TextView keypoint;//现场重难点描述
    private TextView basestart;//基础开挖
    private TextView baseplacing;//基础浇筑
    private TextView baseaog;//基础环到货
    private TextView tameraog;//塔筒到货
    private TextView vehiclerecords;//现场押车记录
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//删除

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udprorunlog_line1_details);

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

        createdate = (TextView) findViewById(R.id.udprorunlogline1_createdate);
        personid = (TextView) findViewById(R.id.udprorunlogline1_personid);
        funnum = (TextView) findViewById(R.id.udprorunlogline1_funnum);
        prophase = (TextView) findViewById(R.id.udprorunlogline1_prophase);
        land = (EditText) findViewById(R.id.udprorunlogline1_land);
        insideroad = (EditText) findViewById(R.id.udprorunlogline1_insideroad);
        outsideroad = (EditText) findViewById(R.id.udprorunlogline1_outsideroad);
        villagerinvolved = (CheckBox) findViewById(R.id.udprorunlogline1_villagerinvolved);
        remark = (EditText) findViewById(R.id.udprorunlogline1_remark);
        keypoint = (TextView) findViewById(R.id.udprorunlogline1_keypoint);
        basestart = (TextView) findViewById(R.id.udprorunlogline1_basestart);
        baseplacing = (TextView) findViewById(R.id.udprorunlogline1_baseplacing);
        baseaog = (TextView) findViewById(R.id.udprorunlogline1_baseaog);
        tameraog = (TextView) findViewById(R.id.udprorunlogline1_tameraog);
        vehiclerecords = (TextView) findViewById(R.id.udprorunlogline1_vehiclerecords);
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
        titleTextView.setText("新增" + getResources().getString(R.string.udprorunlog_line1_details_title));

        createdate.setText(GetDateAndTime.GetDate());
        funnum.setOnClickListener(new LayoutOnClickListener(1, Constants.UDLOCNUMCODE));
        personid.setOnClickListener(new LayoutOnClickListener(2, Constants.PERSONCODE));
        basestart.setOnClickListener(new DateChecked(basestart));
        baseplacing.setOnClickListener(new DateChecked(baseplacing));
        baseaog.setOnClickListener(new DateChecked(baseaog));
        tameraog.setOnClickListener(new DateChecked(tameraog));
        prophase.setOnClickListener(new NormalListDialogOnClickListener(prophase));

        confirm.setOnClickListener(confirmOnClickListener);
        cancel.setOnClickListener(cancelOnClickListener);
    }

    private UdprorunlogLine1 getUdprorunlogLine1() {
        UdprorunlogLine1 udprorunlogLine1 = this.udprorunlogLine1;
        udprorunlogLine1.CREATEDATE = createdate.getText().toString();
        udprorunlogLine1.PERSONID = personid.getText().toString();
        udprorunlogLine1.FUNNUM = funnum.getText().toString();
        udprorunlogLine1.PROPHASE = prophase.getText().toString();
        udprorunlogLine1.LAND = land.getText().toString();
        udprorunlogLine1.INSIDEROAD = insideroad.getText().toString();
        udprorunlogLine1.OUTSIDEROAD = outsideroad.getText().toString();
        udprorunlogLine1.VILLAGERINVOLVED = villagerinvolved.isChecked() ? 1 : 0;
        udprorunlogLine1.REMARK = remark.getText().toString();
        udprorunlogLine1.KEYPOINT = keypoint.getText().toString();
        udprorunlogLine1.BASESTART = basestart.getText().toString();
        udprorunlogLine1.BASEPLACING = baseplacing.getText().toString();
        udprorunlogLine1.BASEAOG = baseaog.getText().toString();
        udprorunlogLine1.TAMERAOG = tameraog.getText().toString();
        udprorunlogLine1.VEHICLERECORDS = vehiclerecords.getText().toString();
        udprorunlogLine1.TYPE = "add";
        return udprorunlogLine1;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("udprorunlogLine1", getUdprorunlogLine1());
            Udprorunlog_Line1AddNewActivity.this.setResult(1, intent);
            Toast.makeText(Udprorunlog_Line1AddNewActivity.this, "日报本地新增成功", Toast.LENGTH_SHORT).show();
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
            new DateSelect(Udprorunlog_Line1AddNewActivity.this, textView).showDialog();
        }
    }

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(Udprorunlog_Line1AddNewActivity.this, textView).showDialog();
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
        if (textView == prophase) {
            types = getResources().getStringArray(R.array.prophase_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udprorunlog_Line1AddNewActivity.this, mMenuItems);
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
            Intent intent = new Intent(Udprorunlog_Line1AddNewActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            if (requestCode == 1){
                intent.putExtra("udprojectnum",udprorunlog.getPRONUM());
            }
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data!=null) {
            switch (requestCode) {
                case 1:
                    if (data != null) {
                        option = (Option) data.getSerializableExtra("option");
                        funnum.setText(option.getName());
                    }
                    break;
                case 2:
                    if (data != null) {
                        option = (Option) data.getSerializableExtra("option");
                        personid.setText(option.getName());
                        udprorunlogLine1.PERSONDESC = option.getDesc();
                    }
                    break;
            }
        }
    }
}
