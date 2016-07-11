package com.example.admin.mingyang_object.ui.activity.udpro;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udfeedback;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.GetDateAndTime;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * 新增问题联络单
 */
public class Udfeedback_AddNewActivity extends BaseActivity {
    private static String TAG = "Udfeedback_DetailActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**
     * 菜单
     */
    private ImageView menuImageView;


    /**
     * 界面信息*
     */
    /**
     * 问题联络单基本信息*
     */
    private LinearLayout udfeedback1layout;

    private LinearLayout numlayout;

    private TextView feedbacknumText; //编号

    private TextView descriptionText; //描述

    private TextView problemtypeText; //问题类型

    private TextView emergencyText; //紧急程度

    private TextView workordernumText; //相关故障工单

    private EditText problemsituationText; //现场问题及进展情况描述
    /**
     * 项目信息*
     */
    private LinearLayout udfeedback2layout;

    private TextView pronumText; //项目编号

    private TextView prodescText; //项目描述

    private TextView proresText; //项目负责人

    private TextView phone1Text; //项目人电话

    private TextView branchText; //所属中心

    private TextView prostageText; //项目阶段
    /**
     * 问题提出*
     */
    private LinearLayout udfeedback3layout;

    private TextView createnameText; //需求提出人

    private TextView phone2Text; //提出人电话

    private TextView dept1Text; //提出人部门

    private TextView createdateText; //提出时间

    private TextView statusText; //状态

    /**
     * 支持部门*
     */
    private LinearLayout udfeedback4layout;

    private TextView dept2Text; //支持部门

    private TextView leaderText; //支持部门领导

    private TextView responsetimeText; //需求完成时间


    /**
     * 问题处理*
     */
    private LinearLayout udfeedback5layout;

    private TextView solvedbyText; //问题处理人

    private TextView phone3Text; //联系电话

    private TextView dept3Text; //解决人所属部门

    /**
     * 问题解决*
     */
    private LinearLayout udfeedback6layout;

    private TextView foundtimeText; //抵达时间

    private TextView comptimeText; //完成时间

    private TextView progressText; //解决问题及反馈

    /**
     * 问题确认*
     */
    private LinearLayout udfeedback7layout;

    private CheckBox isstopText; //是否解决

    private TextView remarkText; //说明

    private Button cancel;//取消
    private Button save;//保存

    private Udfeedback udfeedback = new Udfeedback();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udfeedback_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
//        udfeedback = (Udfeedback) getIntent().getSerializableExtra("udfeedback");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        numlayout = (LinearLayout) findViewById(R.id.num_layout);
        udfeedback1layout = (LinearLayout) findViewById(R.id.activity_udfeedback1_layout);
        udfeedback2layout = (LinearLayout) findViewById(R.id.activity_udfeedback2_layout);
        udfeedback3layout = (LinearLayout) findViewById(R.id.activity_udfeedback3_layout);
        udfeedback4layout = (LinearLayout) findViewById(R.id.activity_udfeedback4_layout);
        udfeedback5layout = (LinearLayout) findViewById(R.id.activity_udfeedback5_layout);
        udfeedback6layout = (LinearLayout) findViewById(R.id.activity_udfeedback6_layout);
        udfeedback7layout = (LinearLayout) findViewById(R.id.activity_udfeedback7_layout);

        feedbacknumText = (TextView) findViewById(R.id.feedbacknum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        problemtypeText = (TextView) findViewById(R.id.problemtype_text_id);
        emergencyText = (TextView) findViewById(R.id.emergency_text_id);
        workordernumText = (TextView) findViewById(R.id.workordernum_text_id);
        problemsituationText = (EditText) findViewById(R.id.problemsituation_text_id);

        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        proresText = (TextView) findViewById(R.id.prores_text_id);
        phone1Text = (TextView) findViewById(R.id.phone1_text_id);
        branchText = (TextView) findViewById(R.id.branch_text_id);
        prostageText = (TextView) findViewById(R.id.prostage_text_id);

        createnameText = (TextView) findViewById(R.id.createname_text_id);
        phone2Text = (TextView) findViewById(R.id.phone2_text_id);
        dept1Text = (TextView) findViewById(R.id.dept1_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);

        dept2Text = (TextView) findViewById(R.id.dept2_text_id);
        leaderText = (TextView) findViewById(R.id.leader_text_id);
        responsetimeText = (TextView) findViewById(R.id.responsetime_text_id);


        solvedbyText = (TextView) findViewById(R.id.solvedby_text_id);
        phone3Text = (TextView) findViewById(R.id.phone3_text_id);
        dept3Text = (TextView) findViewById(R.id.dept3_text_id);

        foundtimeText = (TextView) findViewById(R.id.foundtime_text_id);
        comptimeText = (TextView) findViewById(R.id.comptime_text_id);
        progressText = (TextView) findViewById(R.id.progress_text_id);

        isstopText = (CheckBox) findViewById(R.id.isstop_text_id);
        remarkText = (TextView) findViewById(R.id.remark_text_id);

        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText("新增" + getString(R.string.wtlldxj_text));

        numlayout.setVisibility(View.GONE);
        udfeedback4layout.setVisibility(View.GONE);
        udfeedback5layout.setVisibility(View.GONE);
        udfeedback6layout.setVisibility(View.GONE);
        udfeedback7layout.setVisibility(View.GONE);

        createnameText.setText(AccountUtils.getpersonId(Udfeedback_AddNewActivity.this));
        createdateText.setText(GetDateAndTime.GetDateTime());
        statusText.setText("新建");
        problemtypeText.setOnClickListener(new NormalListDialogOnClickListener(problemtypeText));
        emergencyText.setOnClickListener(new NormalListDialogOnClickListener(emergencyText));
        workordernumText.setOnClickListener(new LayoutOnClickListener(1, Constants.WORKORDERCODE));
        pronumText.setOnClickListener(new LayoutOnClickListener(2,Constants.UDPROCODE));
        createnameText.setOnClickListener(new LayoutOnClickListener(3,Constants.PERSONCODE));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDataInfo();
            }
        });
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

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
        if (textView == problemtypeText) {
            types = getResources().getStringArray(R.array.problemtype_array);
        } else if (textView == emergencyText) {
            types = getResources().getStringArray(R.array.culevel_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udfeedback_AddNewActivity.this, mMenuItems);
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
            Intent intent = new Intent(Udfeedback_AddNewActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Udfeedback_AddNewActivity.this);
        dialog.content("确定新增问题联络单吗?")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        if (isOK()) {
                            showProgressDialog("数据提交中...");
                            startAsyncTask();
                            dialog.dismiss();
                        }
                    }
                });
    }

    private Udfeedback getUdfeedback(){
        Udfeedback udfeedback = this.udfeedback;
        udfeedback.setPROBLEMTYPE(problemtypeText.getText().toString());
        udfeedback.setEMERGENCY(emergencyText.getText().toString());
        udfeedback.setWORKORDERNUM(workordernumText.getText().toString());
        udfeedback.setPROBLEMSITUATION(problemsituationText.getText().toString());
        udfeedback.setPRONUM(pronumText.getText().toString());
        return udfeedback;
    }

    private boolean isOK(){
        if (problemtypeText.getText().toString().equals("")){
            Toast.makeText(Udfeedback_AddNewActivity.this,"问题类型不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (emergencyText.getText().toString().equals("")){
            Toast.makeText(Udfeedback_AddNewActivity.this,"紧急程度不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (pronumText.getText().toString().equals("")){
            Toast.makeText(Udfeedback_AddNewActivity.this,"项目编号不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 提交数据*
     */
    private void startAsyncTask() {
//        if (NetWorkHelper.isNetwork(Work_DetailsActivity.this)) {
//            MessageUtils.showMiddleToast(Work_DetailsActivity.this, "暂无网络,现离线保存数据!");
//            saveWorkOrder();
//        } else {
        String updataInfo = null;
//            if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
        updataInfo = JsonUtils.UdfeedbackToJson(getUdfeedback());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(Udfeedback_AddNewActivity.this,
                        finalUpdataInfo, "UDFEEDBACK", "FEEDBACKNUM", AccountUtils.getpersonId(Udfeedback_AddNewActivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(Udfeedback_AddNewActivity.this, "新增问题联络单失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udfeedback_AddNewActivity.this, "问题联络单" + workResult.wonum + "新增成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Udfeedback_AddNewActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }
        }.execute();
////        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    workordernumText.setText(option.getName());
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    pronumText.setText(option.getName());
                    prodescText.setText(option.getDesc());
                    proresText.setText(option.getValue4());
                    branchText.setText(option.getValue1());
                    prostageText.setText(option.getValue3());
                    phone1Text.setText(option.getValue5());
                    break;
                case 3:
                    option = (Option) data.getSerializableExtra("option");
                    createnameText.setText(option.getDesc());
                    udfeedback.setCREATEBY(option.getName());
                    phone2Text.setText(option.getValue1());
                    dept1Text.setText(option.getValue2());
            }
        }
    }
}
