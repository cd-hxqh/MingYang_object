package com.example.admin.mingyang_object.ui.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.model.Wfassignment;
import com.example.admin.mingyang_object.utils.WorkTypeUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;

/**
 * 流程审批详情
 */
public class Wfm_Details_Activity extends BaseActivity {

    private static final String TAG = "Wfm_Details_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    /**
     * 界面信息显示*
     */
    private TextView ownertableText; //应用程序名
    private TextView descriptionText; //描述
    private TextView assigncodeText; //任务分配人
    private TextView duedateText; //到期日期
    private TextView processnameText; //过程名称
    private TextView roleidText; //任务角色
    private TextView startdateText; //当前日期

    private Button approve;

    private ProgressDialog mProgressDialog;
    private String result;
    private Wfassignment wfm; //流程审批


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfm_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        wfm = (Wfassignment) getIntent().getSerializableExtra("wfassignment");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        ownertableText = (TextView) findViewById(R.id.wfm_ownertable_text);
        descriptionText = (TextView) findViewById(R.id.wfm_description_text);
        assigncodeText = (TextView) findViewById(R.id.wfm_assigncode_text);
        duedateText = (TextView) findViewById(R.id.wfm_duedate_text);
        processnameText = (TextView) findViewById(R.id.wfm_processname_text);
        roleidText = (TextView) findViewById(R.id.wfm_roleid_text);
        startdateText = (TextView) findViewById(R.id.wfm_startdate_text);
        approve = (Button) findViewById(R.id.wfm_approve);
    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.wfm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);


        if (wfm != null) {
            ownertableText.setText(wfm.getOWNERTABLE() == null ? "" : wfm.getOWNERTABLE());
            descriptionText.setText(wfm.getDESCRIPTION() == null ? "" : wfm.getDESCRIPTION());
            assigncodeText.setText(wfm.getASSIGNCODE() == null ? "" : wfm.getASSIGNCODE());
            duedateText.setText(wfm.getDUEDATE() == null ? "" : wfm.getDUEDATE());
            processnameText.setText(wfm.getPROCESSNAME() == null ? "" : wfm.getPROCESSNAME());
            roleidText.setText(wfm.getROLEID() == null ? "" : wfm.getROLEID());
            startdateText.setText(wfm.getSTARTDATE() == null ? "" : wfm.getSTARTDATE());
        }
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        approve.setOnClickListener(approveOnClickListener);
    }

    /**
     * 返回按钮*
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener approveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MaterialDialogOneBtn1();
        }
    };


    private void MaterialDialogOneBtn1() {//审批工作流
        final MaterialDialog dialog = new MaterialDialog(Wfm_Details_Activity.this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否填写输入意见")//
                .btnText("是", "否，直接提交")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        EditDialog(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        wfgoon("1", "");
                        dialog.dismiss();
                    }
                }
        );
    }


    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Wfm_Details_Activity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content(isok ? "通过" : "不通过")//
                .btnText("提交", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
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

                        dialog.dismiss();
                    }
                }
        );
    }

    /**
     * 审批工作流
     *
     * @param zx
     */
    private void wfgoon(final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Wfm_Details_Activity.this, null,
                getString(R.string.approve), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult result = AndroidClientService.approve(Wfm_Details_Activity.this,
                        wfm.getPROCESSNAME(), wfm.getOWNERTABLE(), wfm.getOWNERID(), wfm.getOWNERTABLE() + "ID", zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(WebResult s) {
                super.onPostExecute(s);
                if (s == null || s.wonum == null || s.errorMsg == null) {
                    Toast.makeText(Wfm_Details_Activity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else if (s.wonum.equals(wfm.getOWNERID()) && s.errorMsg != null) {
                    Toast.makeText(Wfm_Details_Activity.this, s.errorMsg, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Wfm_Details_Activity.this, "审批失败", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

}
