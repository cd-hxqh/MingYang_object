package com.example.admin.eam.ui.activity.udpro;

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

import com.example.admin.eam.R;
import com.example.admin.eam.model.Option;
import com.example.admin.eam.model.Udprorunlog;
import com.example.admin.eam.model.UdprorunlogLine4;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.activity.OptionActivity;
import com.example.admin.eam.utils.DateSelect;
import com.example.admin.eam.utils.DateTimeSelect;
import com.example.admin.eam.utils.GetDateAndTime;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * Created by think on 2016/7/5.
 * 工装管理新增页面
 */
public class Udprorunlog_Line4AddNewActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private UdprorunlogLine4 udprorunlogLine4 = new UdprorunlogLine4();
    private Udprorunlog udprorunlog;
    private int position;

    private TextView runlogdate;//日期
    private TextView type2;//类型
    private EditText number1;//已到货数
    private EditText number2;//已吊装数
    private EditText number3;//已返厂数
    private EditText number4;//风场丢失数
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//删除

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udprorunlog_line4_details);

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

        runlogdate = (TextView) findViewById(R.id.udprorunlogline4_runlogdate);
        type2 = (TextView) findViewById(R.id.udprorunlogline4_type2);
        number1 = (EditText) findViewById(R.id.udprorunlogline4_number1);
        number2 = (EditText) findViewById(R.id.udprorunlogline4_number2);
        number3 = (EditText) findViewById(R.id.udprorunlogline4_number3);
        number4 = (EditText) findViewById(R.id.udprorunlogline4_number4);
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
        titleTextView.setText("新增" + getResources().getString(R.string.udprorunlog_line4_details_title));

        runlogdate.setText(GetDateAndTime.GetDate());
        runlogdate.setOnClickListener(new DateChecked(runlogdate));
        type2.setOnClickListener(new NormalListDialogOnClickListener(type2));

        confirm.setOnClickListener(confirmOnClickListener);
        cancel.setOnClickListener(cancelOnClickListener);
    }

    private UdprorunlogLine4 getUdprorunlogLine4() {
        UdprorunlogLine4 udprorunlogLine4 = this.udprorunlogLine4;
        udprorunlogLine4.RUNLOGDATE = runlogdate.getText().toString();
        udprorunlogLine4.TYPE2 = type2.getText().toString();
        udprorunlogLine4.NUMBER1 = number1.getText().toString().equals("")? 0: Integer.parseInt(number1.getText().toString());
        udprorunlogLine4.NUMBER2 = number2.getText().toString().equals("")? 0: Integer.parseInt(number2.getText().toString());
        udprorunlogLine4.NUMBER3 = number3.getText().toString().equals("")? 0: Integer.parseInt(number3.getText().toString());
        udprorunlogLine4.NUMBER4 = number4.getText().toString().equals("")? 0: Integer.parseInt(number4.getText().toString());
        udprorunlogLine4.TYPE = "add";
        return udprorunlogLine4;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("udprorunlogLine4", getUdprorunlogLine4());
            Udprorunlog_Line4AddNewActivity.this.setResult(1, intent);
            Toast.makeText(Udprorunlog_Line4AddNewActivity.this, "工装管理本地新增成功", Toast.LENGTH_SHORT).show();
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
            new DateSelect(Udprorunlog_Line4AddNewActivity.this, textView).showDialog();
        }
    }

    class DateTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(Udprorunlog_Line4AddNewActivity.this, textView).showDialog();
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
        if (textView == type2) {
            types = getResources().getStringArray(R.array.type2_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udprorunlog_Line4AddNewActivity.this, mMenuItems);
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
            Intent intent = new Intent(Udprorunlog_Line4AddNewActivity.this, OptionActivity.class);
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
        switch (requestCode) {
//            case 1:
//                if (data!=null) {
//                    option = (Option) data.getSerializableExtra("option");
//                    funnum.setText(option.getName());
//                }
//                break;
//            case 2:
//                if (data!=null) {
//                    option = (Option) data.getSerializableExtra("option");
//                    personid.setText(option.getName());
//                    udprorunlogLine1.PERSONDESC = option.getDesc();
//                }
//                break;
        }
    }
}
