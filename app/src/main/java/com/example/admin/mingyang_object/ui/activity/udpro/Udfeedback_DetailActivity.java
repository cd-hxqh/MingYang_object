package com.example.admin.mingyang_object.ui.activity.udpro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.MaterialDialog2;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * 问题联络单详情
 */
public class Udfeedback_DetailActivity extends BaseActivity {
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
    private PopupWindow popupWindow;


    /**
     * 界面信息*
     */
    /**
     * 问题联络单基本信息*
     */
    private TextView feedbacknumText; //编号

    private TextView descriptionText; //描述

    private TextView problemtypeText; //问题类型

    private TextView emergencyText; //紧急程度

    private TextView workordernumText; //相关故障工单

    private EditText problemsituationText; //现场问题及进展情况描述
    /**
     * 项目信息*
     */

    private TextView pronumText; //项目编号

    private TextView prodescText; //项目描述

    private TextView proresText; //项目负责人

    private TextView phone1Text; //项目人电话

    private TextView branchText; //所属中心

    private TextView prostageText; //项目阶段
    /**
     * 问题提出*
     */
    private TextView createnameText; //需求提出人

    private TextView phone2Text; //提出人电话

    private TextView dept1Text; //提出人部门

    private TextView createdateText; //提出时间

    private TextView statusText; //状态

    /**
     * 支持部门*
     */
    private TextView dept2Text; //支持部门

    private TextView leaderText; //支持部门领导

    private TextView responsetimeText; //需求完成时间


    /**
     * 问题处理*
     */
    private TextView solvedbyText; //问题处理人

    private TextView phone3Text; //联系电话

    private TextView dept3Text; //解决人所属部门

    /**
     * 问题解决*
     */
    private TextView foundtimeText; //抵达时间

    private TextView comptimeText; //完成时间

    private EditText progressText; //解决问题及反馈

    /**
     * 问题确认*
     */
    private CheckBox isstopText; //是否解决

    private EditText remarkText; //说明

    private Button cancel;
    private Button save;

    /**
     * 发送工作流*
     */
    private LinearLayout flowerLinearLayout;

    private Udfeedback udfeedback;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udfeedback_details);
        geiIntentData();
        findViewById();
        initView();
        isCheckde();
    }

    private void geiIntentData() {
        udfeedback = (Udfeedback) getIntent().getSerializableExtra("udfeedback");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


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
        progressText = (EditText) findViewById(R.id.progress_text_id);

        isstopText = (CheckBox) findViewById(R.id.isstop_text_id);
        remarkText = (EditText) findViewById(R.id.remark_text_id);

        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);

        if (udfeedback != null) {
            feedbacknumText.setText(udfeedback.getFEEDBACKNUM());
            descriptionText.setText(udfeedback.getDESCRIPTION());
            problemtypeText.setText(udfeedback.getPROBLEMTYPE());
            emergencyText.setText(udfeedback.getEMERGENCY());
            workordernumText.setText(udfeedback.getWORKORDERNUM());
            problemsituationText.setText(udfeedback.getPROBLEMSITUATION());

            pronumText.setText(udfeedback.getPRONUM());
            prodescText.setText(udfeedback.getPRODESC());
            proresText.setText(udfeedback.getPRORES());
            phone1Text.setText(udfeedback.getPHONE1());
            branchText.setText(udfeedback.getBRANCH());
            prostageText.setText(udfeedback.getPROSTAGE());

            createnameText.setText(udfeedback.getCREATENAME());
            phone2Text.setText(udfeedback.getPHONE2());
            dept1Text.setText(udfeedback.getDEPT1());
            createdateText.setText(udfeedback.getCREATEDATE());
            statusText.setText(udfeedback.getSTATUS());

            dept2Text.setText(udfeedback.getDEPT2());
            leaderText.setText(udfeedback.getLEADER());
            responsetimeText.setText(udfeedback.getRESPONSETIME());

            solvedbyText.setText(udfeedback.getSOLVEDBY());
            phone3Text.setText(udfeedback.getPHONE3());
            dept3Text.setText(udfeedback.getDEPT3());

            foundtimeText.setText(udfeedback.getFOUNDTIME());
            comptimeText.setText(udfeedback.getCOMPTIME());
            progressText.setText(udfeedback.getPROGRESS());


            if(udfeedback.getISSTOP()==1){
                isstopText.setChecked(true);
            }else {
                isstopText.setChecked(false);
            }
            remarkText.setText(udfeedback.getREMARK());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.wtlldxj_text));
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        problemsituationText.setFocusable(false);
        problemsituationText.setFocusableInTouchMode(false);
        progressText.setFocusable(false);
        progressText.setFocusableInTouchMode(false);
        remarkText.setFocusable(false);
        remarkText.setFocusableInTouchMode(false);
        isstopText.setFocusable(false);
        isstopText.setFocusableInTouchMode(false);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOK()) {
                    submitDataInfo();
                }
            }
        });
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Udfeedback_DetailActivity.this).inflate(
                R.layout.udfeedback_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        flowerLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_flower_id);
        flowerLinearLayout.setOnClickListener(flowerOnClickListener);

    }
    private View.OnClickListener flowerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (udfeedback.getSTATUS().equals("新建")) {
                MaterialDialogOneBtn();
            } else {
                EditDialog();
            }
            popupWindow.dismiss();
        }
    };

    private void isCheckde(){
        switch (udfeedback.getSTATUS()){
            case "新建":
                problemtypeText.setOnClickListener(new NormalListDialogOnClickListener(problemtypeText));
                emergencyText.setOnClickListener(new NormalListDialogOnClickListener(emergencyText));
                workordernumText.setOnClickListener(new LayoutOnClickListener(1, Constants.WORKORDERCODE));
                pronumText.setOnClickListener(new LayoutOnClickListener(2, Constants.UDPROCODE));
                createnameText.setOnClickListener(new LayoutOnClickListener(3, Constants.PERSONCODE));
                problemsituationText.setFocusable(true);
                problemsituationText.setFocusableInTouchMode(true);
                break;
            case "待审批":
                dept2Text.setOnClickListener(new LayoutOnClickListener(4,Constants.UDDEPTCODE));
                leaderText.setOnClickListener(new LayoutOnClickListener(5,Constants.PERSONCODE));
                responsetimeText.setOnClickListener(new DateTimeOnClickListener(responsetimeText));
                break;
            case "已审批":
                solvedbyText.setOnClickListener(new LayoutOnClickListener(6,Constants.PERSONCODE));
                break;
            case "已派工":
                foundtimeText.setOnClickListener(new DateTimeOnClickListener(foundtimeText));
                comptimeText.setOnClickListener(new DateTimeOnClickListener(comptimeText));
                progressText.setFocusable(true);
                progressText.setFocusableInTouchMode(true);
                break;
            case "已响应":
                isstopText.setFocusable(true);
                isstopText.setFocusableInTouchMode(true);
                remarkText.setFocusable(true);
                remarkText.setFocusableInTouchMode(true);
                break;
            default:
                break;
        }
    }

    private boolean isOK(){
        switch (udfeedback.getSTATUS()){
            case "新建":
                if (problemtypeText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入问题类型", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (emergencyText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入紧急程度", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (pronumText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (createnameText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入需求提出人", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (problemsituationText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入现场问题及进展描述", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            case "待审批":
                if (dept2Text.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入支持部门", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (leaderText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入支持部门领导", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (responsetimeText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入需求完成时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            case "已派工":
                if (foundtimeText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入抵达时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (comptimeText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入完成时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (progressText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入解决问题及反馈", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            case "已响应":
                if (remarkText.getText().toString().equals("")){
                    Toast.makeText(Udfeedback_DetailActivity.this, "请输入说明", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
        }
        return false;
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
        final NormalListDialog dialog = new NormalListDialog(Udfeedback_DetailActivity.this, mMenuItems);
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
            Intent intent = new Intent(Udfeedback_DetailActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    //时间选择监听
    private class DateTimeOnClickListener implements View.OnClickListener {
        TextView textView;

        private DateTimeOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View view) {
            new DateTimeSelect(Udfeedback_DetailActivity.this, textView).showDialog();
        }
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

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Udfeedback_DetailActivity.this);
        dialog.content("确定修改问题联络单吗?")//
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
                        showProgressDialog("数据提交中...");
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });
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
                WebResult reviseresult = AndroidClientService.UpdateWO(Udfeedback_DetailActivity.this,finalUpdataInfo,
                        "UDFEEDBACK", "FEEDBACKNUM", udfeedback.getFEEDBACKNUM(), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(Udfeedback_DetailActivity.this, "修改问题联络单失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udfeedback_DetailActivity.this, "修改问题联络单成功", Toast.LENGTH_SHORT).show();
//                    setResult(100);
//                    finish();
                } else {
                    Toast.makeText(Udfeedback_DetailActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }
        }.execute();
////        }

    }

    private void MaterialDialogOneBtn() {//开始工作流
        final MaterialDialog2 dialog = new MaterialDialog2(Udfeedback_DetailActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否启动工作流")//
                .btnText("是", "否")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        startWF();
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
        );
    }

    private void EditDialog() {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Udfeedback_DetailActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(true)//
                .title("审批工作流")
                .btnNum(3)
                .content("通过")//
                .btnText("取消", "通过", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon("1", text);
                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon("0", text.equals("通过") ? "不通过" : text);
                        dialog.dismiss();
                    }
                }
        );
    }

    /**
     * 开始工作流
     */
    private void startWF() {
        mProgressDialog = ProgressDialog.show(Udfeedback_DetailActivity.this, null,
                getString(R.string.start), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult result = AndroidClientService.startwf(Udfeedback_DetailActivity.this, "UDFEEDBACK", "UDFEEDBACK", udfeedback.getFEEDBACKNUM(), "FEEDBACKNUM",AccountUtils.getpersonId(Udfeedback_DetailActivity.this));

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(WebResult s) {
                super.onPostExecute(s);
                if (s != null && s.errorMsg != null && s.errorMsg.equals("工作流启动成功")) {
                    Toast.makeText(Udfeedback_DetailActivity.this, s.errorMsg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Udfeedback_DetailActivity.this, "工作流启动失败", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    /**
     * 审批工作流
     *
     * @param zx
     */
    private void wfgoon(final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Udfeedback_DetailActivity.this, null,
                getString(R.string.approve), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult result = AndroidClientService.approve(Udfeedback_DetailActivity.this,
                        "UDFEEDBACK", "UDFEEDBACK", udfeedback.getUDFEEDBACKID()+"", "UDFEEDBACKID", zx, desc,
                        AccountUtils.getpersonId(Udfeedback_DetailActivity.this));

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(WebResult s) {
                super.onPostExecute(s);
                if (s == null || s.wonum == null || s.errorMsg == null) {
                    Toast.makeText(Udfeedback_DetailActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else if (s.wonum.equals(udfeedback.getUDFEEDBACKID()+"") && s.errorMsg != null) {
                    statusText.setText(s.errorMsg);
                    udfeedback.setSTATUS(s.errorMsg);
                    Toast.makeText(Udfeedback_DetailActivity.this, "审批成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Udfeedback_DetailActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
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
                    break;
                case 4:
                    option = (Option) data.getSerializableExtra("option");
                    dept2Text.setText(option.getDesc());
                    udfeedback.setSUPPORTDEPT(option.getName());
                    break;
                case 5:
                    option = (Option) data.getSerializableExtra("option");
                    leaderText.setText(option.getDesc());
                    udfeedback.setLEADER(option.getName());
                    break;
                case 6:
                    option = (Option) data.getSerializableExtra("option");
                    solvedbyText.setText(option.getDesc());
                    udfeedback.setSOLVEDBY(option.getName());
                    phone3Text.setText(option.getValue1());
                    dept3Text.setText(option.getValue2());
                    break;
            }
        }
    }

}
