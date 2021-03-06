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
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.model.Option;
import com.example.admin.eam.model.Udprorunlog;
import com.example.admin.eam.model.UdprorunlogLine2;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.activity.OptionActivity;
import com.example.admin.eam.utils.DateSelect;
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
    private TextView phonenum;//电话号码
    private TextView prophase;//当前项目阶段
    private EditText workjob;//当日工作内容
    private EditText remark1;//备注

    public TextView dznum;//机位号

    public EditText clxproduction;//主机累计到货数q
    public EditText compchecking;//轮毂累计到货数clx
    public EditText comprunning;//叶片累计到货数e

    public EditText basecomp;//基础浇筑完成累计数r
    public EditText bpqproduction;//吊装完成累计数t
    public EditText debugging;//电气安装完成累计数y
    public EditText debugging2;//安装验收完成累计数u

    public TextView date1;//试运行开始日期160829
    public TextView date2;//试运行完成日期null
    public TextView date3;//预验收完成日期null

    public EditText debuggingcheck;//试运行台数q
    public EditText dzcomp;//试运行完成台数w
    public EditText dzstart;//预验收完成台数e

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
        phonenum = (TextView) findViewById(R.id.udprorunlog_line2_phononum);
        prophase = (TextView) findViewById(R.id.udprorunlog_line2_prophase);
        workjob = (EditText) findViewById(R.id.udprorunlog_line2_workjob);
//
        dznum = (TextView) findViewById(R.id.udprorunlogline2_dznum);//机位号
        remark1 = (EditText) findViewById(R.id.udprorunlogline2_remark1);//备注



        clxproduction = (EditText) findViewById(R.id.udprorunlogline2_clxproduction);//主机累计到货数q
        compchecking = (EditText) findViewById(R.id.udprorunlogline2_compchecking);//轮毂累计到货数clx
        comprunning = (EditText) findViewById(R.id.udprorunlogline2_comprunning);//叶片累计到货数e

        basecomp = (EditText) findViewById(R.id.udprorunlogline2_basecomp);//基础浇筑完成累计数r
        bpqproduction = (EditText) findViewById(R.id.udprorunlogline2_bpqproduction);//吊装完成累计数t
        debugging = (EditText) findViewById(R.id.udprorunlogline2_debugging);//电气安装完成累计数y
        debugging2 = (EditText) findViewById(R.id.udprorunlogline2_debugging2);//安装验收完成累计数u

        date1 = (TextView) findViewById(R.id.udprorunlogline2_date1);//试运行开始日期160829
        date2 = (TextView) findViewById(R.id.udprorunlogline2_date2);//试运行完成日期null
        date3 = (TextView) findViewById(R.id.udprorunlogline2_date3);//预验收完成日期null

        debuggingcheck = (EditText) findViewById(R.id.udprorunlogline2_debuggingcheck);//试运行台数q
        dzcomp = (EditText) findViewById(R.id.udprorunlogline2_dzcomp);//试运行完成台数w
        dzstart = (EditText) findViewById(R.id.udprorunlogline2_dzstart);//预验收完成台数e

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
        person.setText(udprorunlogLine2.NAME);
        phonenum.setText(udprorunlogLine2.PHONE);
        prophase.setText(udprorunlogLine2.PROPHASE);
        workjob.setText(udprorunlogLine2.WORKJOB);

        clxproduction.setText(udprorunlogLine2.CLXPRODUCTION);//主机累计到货数q
        compchecking.setText(udprorunlogLine2.COMPCHECKING);//轮毂累计到货数clx
        comprunning.setText(udprorunlogLine2.COMPRUNNING);//叶片累计到货数e

        basecomp.setText(udprorunlogLine2.BASECOMP);//基础浇筑完成累计数r
        bpqproduction.setText(udprorunlogLine2.BPQPRODUCTION);//吊装完成累计数t
        debugging.setText(udprorunlogLine2.DEBUGGING);//电气安装完成累计数y
        debugging2.setText(udprorunlogLine2.DEBUGGING2);//安装验收完成累计数u

        date1.setText(udprorunlogLine2.DATE1);//试运行开始日期160829
        date2.setText(udprorunlogLine2.DATE2);//试运行完成日期null
        date3.setText(udprorunlogLine2.DATE3);//预验收完成日期null

        debuggingcheck.setText(udprorunlogLine2.DEBUGGINGCHECK);//试运行台数q
        dzcomp.setText(udprorunlogLine2.DZCOMP);//试运行完成台数w
        dzstart.setText(udprorunlogLine2.DZSTART);//预验收完成台数e


        date1.setOnClickListener(new DateChecked(date1));
        date2.setOnClickListener(new DateChecked(date2));
        date3.setOnClickListener(new DateChecked(date3));
        createdate.setOnClickListener(new DateChecked(createdate));
        personid.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
//        prophase.setOnClickListener(new NormalListDialogOnClickListener(prophase));
        dznum.setOnClickListener(new LayoutOnClickListener(2, Constants.UDLOCNUMCODE));
        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private UdprorunlogLine2 getUdprorunlogLine2() {

        UdprorunlogLine2 udprorunlogLine2 = this.udprorunlogLine2;
        udprorunlogLine2.CREATEDATE = createdate.getText().toString();
        udprorunlogLine2.PERSONID = personid.getText().toString();
        udprorunlogLine2.NAME = person.getText().toString();
//        udprorunlogLine2.PROPHASE = prophase.getText().toString();
        udprorunlogLine2.WORKJOB = workjob.getText().toString();

        udprorunlogLine2.CLXPRODUCTION=clxproduction.getText().toString();//主机累计到货数q
        udprorunlogLine2.COMPCHECKING=compchecking.getText().toString();//轮毂累计到货数clx
        udprorunlogLine2.COMPRUNNING=comprunning.getText().toString();//叶片累计到货数e

        udprorunlogLine2.BASECOMP=basecomp.getText().toString();//基础浇筑完成累计数r
        udprorunlogLine2.BPQPRODUCTION=bpqproduction.getText().toString();//吊装完成累计数t
        udprorunlogLine2.DEBUGGING=debugging.getText().toString();//电气安装完成累计数y
        udprorunlogLine2.DEBUGGING2=debugging2.getText().toString();//安装验收完成累计数u

        udprorunlogLine2.DATE1=date1.getText().toString();//试运行开始日期160829
        udprorunlogLine2.DATE2=date2.getText().toString();//试运行完成日期null
        udprorunlogLine2.DATE3=date3.getText().toString();//预验收完成日期null

        udprorunlogLine2.DEBUGGINGCHECK=debuggingcheck.getText().toString();//试运行台数q
        udprorunlogLine2.DZCOMP=dzcomp.getText().toString();//试运行完成台数w
        udprorunlogLine2.DZSTART=dzstart.getText().toString();//预验收完成台数e


        return udprorunlogLine2;
    }

    private boolean isOK(){
        if (createdate.getText().toString().equals("")){
            Toast.makeText(Udprorunlog_Line2DetailsActivity.this,"请选择日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (personid.getText().toString().equals("")){
            Toast.makeText(Udprorunlog_Line2DetailsActivity.this,"请选择项目负责人",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOK()) {
                Intent intent = getIntent();
                if (udprorunlogLine2.CREATEDATE.equals(createdate.getText().toString())
                        && udprorunlogLine2.PERSONID.equals(personid.getText().toString())
//                        && udprorunlogLine2.PROPHASE.equals(prophase.getText().toString())
                        && udprorunlogLine2.WORKJOB.equals(workjob.getText().toString())
                        && udprorunlogLine2.REMARK1.equals(remark1.getText().toString())) {//如果内容没有修改
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
            if (requestCode == 2) {
                if (udprorunlog.PRONUM == null || udprorunlog.PRONUM.equals("")) {
                    Toast.makeText(Udprorunlog_Line2DetailsActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    intent.putExtra("udprojectnum", udprorunlog.PRONUM);
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
                    option = (Option) data.getSerializableExtra("option");
                    personid.setText(option.getName());
                    person.setText(option.getDesc());
                    phonenum.setText(option.getValue1());
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
}
