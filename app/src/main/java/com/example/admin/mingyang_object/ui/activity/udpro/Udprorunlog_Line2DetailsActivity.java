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
import com.example.admin.mingyang_object.model.UdprorunlogLine2;
import com.example.admin.mingyang_object.model.UdprorunlogLine4;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.GetDateAndTime;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * Created by think on 2016/7/6.
 * 吊装调试详情页面
 */
public class Udprorunlog_Line2DetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private UdprorunlogLine2 udprorunlogLine2 = new UdprorunlogLine2();
    private Udprorunlog udprorunlog;
    private int position;

    private TextView createdate;//日期
    private TextView personid;//项目负责人
    private TextView person;//
    private TextView prophase;//当前项目阶段
    private EditText workjob;//当日工作内容
    private EditText remark;//备注
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udprorunlog_line2_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udprorunlogLine2 = (UdprorunlogLine2) getIntent().getSerializableExtra("udprorunlogline2");
        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        createdate = (TextView) findViewById(R.id.udprorunlog_line2_createdate);
        personid = (TextView) findViewById(R.id.udprorunlog_line2_personid);
        person = (TextView) findViewById(R.id.udprorunlog_line2_person);
        prophase = (TextView) findViewById(R.id.udprorunlog_line2_prophase);
        workjob = (EditText) findViewById(R.id.udprorunlog_line2_workjob);
        remark = (EditText) findViewById(R.id.udprorunlog_line2_remark);
//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.work_save);
        delete = (Button) findViewById(R.id.work_cancel);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.udprorunlog_line2_details_title));
        delete.setText(R.string.work_delete);

        createdate.setText(udprorunlogLine2.CREATEDATE);
        personid.setText(udprorunlogLine2.PERSONID);
        person.setText(udprorunlogLine2.CREATEBY);
        prophase.setText(udprorunlogLine2.PROPHASE);
        workjob.setText(udprorunlogLine2.WORKJOB);
        remark.setText(udprorunlogLine2.REMARK);
        createdate.setOnClickListener(new DateChecked(createdate));
        personid.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
        prophase.setOnClickListener(new NormalListDialogOnClickListener(prophase));
        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private UdprorunlogLine2 getUdprorunlogLine2() {
        UdprorunlogLine2 udprorunlogLine2 = this.udprorunlogLine2;
        udprorunlogLine2.CREATEDATE = createdate.getText().toString();
        udprorunlogLine2.PERSONID = personid.getText().toString();
        udprorunlogLine2.CREATEBY = person.getText().toString();
        udprorunlogLine2.PROPHASE = prophase.getText().toString();
        udprorunlogLine2.WORKJOB = workjob.getText().toString();
        udprorunlogLine2.REMARK = remark.getText().toString();
        return udprorunlogLine2;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if (udprorunlogLine2.CREATEDATE.equals(createdate.getText().toString())
                    && udprorunlogLine2.PERSONID.equals(personid.getText().toString())
                    && udprorunlogLine2.PROPHASE.equals(prophase.getText().toString())
                    && udprorunlogLine2.WORKJOB.equals(workjob.getText().toString())
                    && udprorunlogLine2.REMARK.equals(remark.getText().toString())) {//如果内容没有修改
                intent.putExtra("udprorunlogLine2", udprorunlogLine2);
            } else {
                UdprorunlogLine2 udprorunlogLine2 = getUdprorunlogLine2();
                if (udprorunlogLine2.TYPE == null || !udprorunlogLine2.TYPE.equals("add")) {
                    udprorunlogLine2.TYPE = "update";
                }
                intent.putExtra("udprorunlogLine2", udprorunlogLine2);
                Toast.makeText(Udprorunlog_Line2DetailsActivity.this, "吊装调试日报本地修改成功", Toast.LENGTH_SHORT).show();
            }
            intent.putExtra("position", position);
            Udprorunlog_Line2DetailsActivity.this.setResult(2, intent);
            finish();
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("position", position);
            if (!udprorunlogLine2.isUpload) {
                Udprorunlog_Line2DetailsActivity.this.setResult(3, intent);
            } else {
                UdprorunlogLine2 udprorunlogLine2 = getUdprorunlogLine2();
                udprorunlogLine2.TYPE = "delete";
                intent.putExtra("udprorunlogLine2", udprorunlogLine2);
                Udprorunlog_Line2DetailsActivity.this.setResult(4, intent);
            }
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
            new DateSelect(Udprorunlog_Line2DetailsActivity.this, textView).showDialog();
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
        final NormalListDialog dialog = new NormalListDialog(Udprorunlog_Line2DetailsActivity.this, mMenuItems);
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
            Intent intent = new Intent(Udprorunlog_Line2DetailsActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (requestCode) {
            case 1:
                if (data!=null) {
                    option = (Option) data.getSerializableExtra("option");
                    personid.setText(option.getName());
                    person.setText(option.getDesc());
                }
                break;
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
