package com.example.admin.mingyang_object.ui.activity;

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
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Failurelist;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udqtyform;
import com.example.admin.mingyang_object.model.Udreport;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * 故障提报单详情
 */
public class Udreport_DetailActivity extends BaseActivity {
    private static String TAG = "Udreport_DetailActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;
    private ImageView menuImageView;
    private PopupWindow popupWindow;

    /**
     * 生成故障工单
     */
    private LinearLayout workorderLayout;
    /**
     * 生成质量问题反馈单
     */
    private LinearLayout udpbforLayout;
    /**
     * 生成附件
     */
    private LinearLayout commitLinearLayout;
    /**
     * 查看故障原因措施*
     */
    private LinearLayout failureLinearLayout;

    /**
     * 界面信息*
     */
    private TextView reportnumText; //编号

    private TextView descriptionText; //描述

    private TextView branchText; //中心

    private TextView branchdescText;//中心描述

    private TextView pronumText; //项目中心

    private TextView prdescText; //项目名称

    private TextView location_codeText; //机位号

    private TextView assetlocText; //设备位置

    private TextView assetlocdescText;//设备位置描述

    private TextView udgzdjText; //故障等级

    private TextView udgztypeText; //故障类型

    private TextView happen_timeText; //报障时间

    private CheckBox is_endCheckBox; //故障是否结束

    private TextView end_timeText; //结束时间

    private TextView statustypeText; //状态

    private CheckBox cuisplanTCheckBox; //是否由集控生成

    private TextView createbyText; //提报人

    private TextView reporttimeText; //提报时间

    private TextView fault_codedescText; //故障类


    private TextView fault_code1Text; //故障代码

    private TextView fault_code1descText;//故障代码描述

    private EditText cudescribeText; //故障描述

    private EditText resultText; //处理结果

    private EditText remarkText; //备注

    private TextView wonum;//工单号

    private TextView udpbformnum;//质量问题反馈单号

    private Button cancel;
    private Button save;

    private String failurelist = "";
    private Udreport udreport;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udreport_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udreport = (Udreport) getIntent().getSerializableExtra("udreport");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        reportnumText = (TextView) findViewById(R.id.reportnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        branchText = (TextView) findViewById(R.id.branch_text);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);
        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prdescText = (TextView) findViewById(R.id.prdesc_text_id);
        location_codeText = (TextView) findViewById(R.id.location_code_text_id);
        assetlocText = (TextView) findViewById(R.id.assetloc_text_id);
        assetlocdescText = (TextView) findViewById(R.id.assetlocdesc_text);
        udgzdjText = (TextView) findViewById(R.id.culevel_text_id);
        udgztypeText = (TextView) findViewById(R.id.faulttype_text_id);
        happen_timeText = (TextView) findViewById(R.id.capacity_text_id);
        is_endCheckBox = (CheckBox) findViewById(R.id.is_end_text_id);
        end_timeText = (TextView) findViewById(R.id.end_time_text_id);
        statustypeText = (TextView) findViewById(R.id.statustype_text_id);
        cuisplanTCheckBox = (CheckBox) findViewById(R.id.cuisplan_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        reporttimeText = (TextView) findViewById(R.id.reporttime_text_id);
        fault_codedescText = (TextView) findViewById(R.id.fault_codedesc_text_id);
        fault_code1Text = (TextView) findViewById(R.id.fault_code1_text_id);
        fault_code1descText = (TextView) findViewById(R.id.fault_code1desc_text);
        cudescribeText = (EditText) findViewById(R.id.cudescribe_text_id);
        resultText = (EditText) findViewById(R.id.result_text_id);
        remarkText = (EditText) findViewById(R.id.remark_text_id);
        wonum = (TextView) findViewById(R.id.udreport_wonum);
        udpbformnum = (TextView) findViewById(R.id.udreport_udpbformnum);

        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);

        if (udreport != null) {
            reportnumText.setText(udreport.getREPORTNUM());
            descriptionText.setText(udreport.getDESCRIPTION());
            branchText.setText(udreport.getBRANCH());
            branchdescText.setText(udreport.getBRANCHDESC());
            pronumText.setText(udreport.getPRONUM());
            prdescText.setText(udreport.getPRODESC());
            location_codeText.setText(udreport.getLOCATION_CODE());
            assetlocText.setText(udreport.getLOCATION());
            assetlocdescText.setText(udreport.getASSETLOC());
            udgzdjText.setText(udreport.getUDGZDJ());
            udgztypeText.setText(udreport.getUDGZTYPE());
            happen_timeText.setText(udreport.getHAPPEN_TIME());
            if (udreport.getIS_END().equals("Y")) {
                is_endCheckBox.setChecked(true);
            } else {
                is_endCheckBox.setChecked(false);
            }
            end_timeText.setText(udreport.getEND_TIME());
            statustypeText.setText(udreport.getSTATUSTYPE());
            if (udreport.getCUISPLAN().equals("Y")) {
                cuisplanTCheckBox.setChecked(true);
            } else {
                cuisplanTCheckBox.setChecked(false);
            }
            createbyText.setText(udreport.getCREATEBY());
            reporttimeText.setText(udreport.getREPORTTIME());
            fault_codedescText.setText(udreport.getFAULT_CODEDESC());
            fault_code1Text.setText(udreport.getFAULT_CODE1());
            fault_code1descText.setText(udreport.getFAULT_CODE1DESC());
            cudescribeText.setText(udreport.getCUDESCRIBE());
            resultText.setText(udreport.getRESULT());
            remarkText.setText(udreport.getREMARK());
            wonum.setText(udreport.getWONUM());
            udpbformnum.setText(udreport.getUDPBFORMNUM());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udreport_detail_text));
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        cuisplanTCheckBox.setClickable(false);
        if (udreport.getWONUM() == null || udreport.getWONUM().equals("")) {
            pronumText.setOnClickListener(new LayoutOnClickListener(1, Constants.UDPROCODE));
            location_codeText.setOnClickListener(new LayoutOnClickListener(2, Constants.UDLOCNUMCODE));
            assetlocText.setOnClickListener(new LayoutOnClickListener(3, Constants.LOCATIONCODE));
//            udgzdjText.setOnClickListener(new NormalListDialogOnClickListener(udgzdjText));
//            udgztypeText.setOnClickListener(new NormalListDialogOnClickListener(udgztypeText));
            happen_timeText.setOnClickListener(new DateTimeOnClickListener(happen_timeText));
            end_timeText.setOnClickListener(new DateTimeOnClickListener(end_timeText));
            fault_codedescText.setOnClickListener(new LayoutOnClickListener(4, Constants.FAILURECODE));
            fault_code1Text.setOnClickListener(new LayoutOnClickListener(5, Constants.PROBLEMCODE));
        } else {
            is_endCheckBox.setClickable(false);
            cudescribeText.setFocusableInTouchMode(false);
            cudescribeText.setFocusable(false);
            resultText.setFocusableInTouchMode(false);
            resultText.setFocusable(false);
            remarkText.setFocusableInTouchMode(false);
            remarkText.setFocusable(false);
        }
        statustypeText.setOnClickListener(new NormalListDialogOnClickListener(statustypeText));

        getFailureList();

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
        View contentView = LayoutInflater.from(Udreport_DetailActivity.this).inflate(
                R.layout.udreport_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        workorderLayout = (LinearLayout) contentView.findViewById(R.id.workorder_id);
        udpbforLayout = (LinearLayout) contentView.findViewById(R.id.udpbfor_id);
        commitLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_commit_id);
        failureLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_failure_id);
        workorderLayout.setOnClickListener(workorderOnClickListener);
        udpbforLayout.setOnClickListener(udpbforOnClickListener);
        commitLinearLayout.setOnClickListener(commitOnClickListener);
        failureLinearLayout.setOnClickListener(failureOnClickListener);
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener workorderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (udreport.getWONUM() != null && !udreport.getWONUM().equals("")) {
                popupWindow.dismiss();
                Toast.makeText(Udreport_DetailActivity.this, "已生成故障工单", Toast.LENGTH_SHORT).show();
            } else {
                popupWindow.dismiss();
                submitDataInfo2();
            }
        }
    };
    private View.OnClickListener commitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent intent = new Intent(Udreport_DetailActivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "UDREPORT");
            intent.putExtra("ownerid", udreport.getUDREPORTID());
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener udpbforOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (udreport.getSTATUSTYPE().equals("已提报") && statustypeText.getText().toString().equals("已提报")
                    && (udreport.getUDPBFORMNUM() == null || udreport.getUDPBFORMNUM().equals(""))) {
                popupWindow.dismiss();
                submitDataInfo3();
            } else if (udreport.getUDPBFORMNUM() != null && !udreport.getUDPBFORMNUM().equals("")) {
                Toast.makeText(Udreport_DetailActivity.this, "已生成质量问题反馈单!", Toast.LENGTH_SHORT).show();
            } else {
                popupWindow.dismiss();
                Toast.makeText(Udreport_DetailActivity.this, "仅当已提报状态可以生成质量问题反馈单!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener failureOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (fault_codedescText.getText().equals("")){
                Toast.makeText(Udreport_DetailActivity.this,"请选择故障类",Toast.LENGTH_SHORT).show();
            }else if (fault_code1Text.getText().equals("")){
                Toast.makeText(Udreport_DetailActivity.this,"请选择故障代码",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(Udreport_DetailActivity.this, Failurelist1Activity.class);
                intent.putExtra("failurecode", udreport.getFAULT_CODE1());
                startActivityForResult(intent, 0);
            }
            popupWindow.dismiss();
        }
    };

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;
        int optiontype;

        private LayoutOnClickListener(int requestCode, int optiontype) {
            this.requestCode = requestCode;
            this.optiontype = optiontype;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udreport_DetailActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            if (requestCode == 2) {
                if (pronumText.getText().toString().equals("")) {
                    Toast.makeText(Udreport_DetailActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    intent.putExtra("udprojectnum", pronumText.getText().toString());
                }
            }
            if (requestCode == 3) {
                if (pronumText.getText().toString().equals("")) {
                    Toast.makeText(Udreport_DetailActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (location_codeText.getText().toString().equals("")) {
                    Toast.makeText(Udreport_DetailActivity.this, "请先选择机位号", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("udprojectnum", pronumText.getText().toString());
                intent.putExtra("udlocnum", location_codeText.getText().toString());
            }
            if (requestCode == 5) {
                if (udreport.getFAULT_CODE() != null && udreport.getFAULT_CODE().equals("") && failurelist.equals("")) {
                    Toast.makeText(Udreport_DetailActivity.this, "请先选择故障类", Toast.LENGTH_SHORT).show();
                    return;
                } else if (udreport.getFAULT_CODE() != null && !udreport.getFAULT_CODE().equals("") && failurelist.equals("")) {
                    Toast.makeText(Udreport_DetailActivity.this, "请重新选择故障类", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("failurelist", failurelist);
            }
            startActivityForResult(intent, requestCode);
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
//        if (textView == udgzdjText) {
//            types = getResources().getStringArray(R.array.culevel_array);
//        } else if (textView == faulttypeText) {
//            types = getResources().getStringArray(R.array.faulttype_array);
//        } else
        if (textView == statustypeText) {
            types = getResources().getStringArray(R.array.status_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udreport_DetailActivity.this, mMenuItems);
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

    //时间选择监听
    private class DateTimeOnClickListener implements View.OnClickListener {
        TextView textView;

        private DateTimeOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View view) {
            new DateTimeSelect(Udreport_DetailActivity.this, textView).showDialog();
        }
    }

    private Udreport getUdreport() {
        Udreport udreport = this.udreport;
        udreport.setBRANCH(branchText.getText().toString());
        udreport.setPRONUM(pronumText.getText().toString());
        udreport.setLOCATION_CODE(location_codeText.getText().toString());
        udreport.setLOCATION(assetlocText.getText().toString());
//        udreport.setCULEVEL(udgzdjText.getText().toString());
//        udreport.setFAULTTYPE(faulttypeText.getText().toString());
        udreport.setHAPPEN_TIME(happen_timeText.getText().toString());
        udreport.setIS_END(is_endCheckBox.isChecked() ? "Y" : "N");
        udreport.setEND_TIME(end_timeText.getText().toString());
        udreport.setSTATUSTYPE(statustypeText.getText().toString());
        udreport.setCREATEBY(createbyText.getText().toString());
        udreport.setREPORTTIME(reporttimeText.getText().toString());
        udreport.setCUDESCRIBE(cudescribeText.getText().toString());
        udreport.setRESULT(resultText.getText().toString());
        udreport.setREMARK(remarkText.getText().toString());
        udreport.setWONUM(wonum.getText().toString());
        udreport.setUDPBFORMNUM(udpbformnum.getText().toString());
        return udreport;
    }

    /**
     * 提交工单数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Udreport_DetailActivity.this);
        dialog.content("确定修改故障提报单吗?")//
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
     * 提交故障数据*
     */
    private void startAsyncTask() {
//        if (NetWorkHelper.isNetwork(Work_DetailsActivity.this)) {
//            MessageUtils.showMiddleToast(Work_DetailsActivity.this, "暂无网络,现离线保存数据!");
//            saveWorkOrder();
//        } else {
        String updataInfo = null;
//            if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
        updataInfo = JsonUtils.UdreportToJson(getUdreport());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(Udreport_DetailActivity.this, finalUpdataInfo,
                        "UDREPORT", "REPORTNUM", udreport.getREPORTNUM(), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(Udreport_DetailActivity.this, "修改故障提报单失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udreport_DetailActivity.this, "修改故障提报单成功", Toast.LENGTH_SHORT).show();
                    udreport = getUdreport();
                } else {
                    Toast.makeText(Udreport_DetailActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }
        }.execute();
////        }
    }

    /**
     * 提交问题反馈单数据*
     */
    private void submitDataInfo2() {
        final NormalDialog dialog = new NormalDialog(Udreport_DetailActivity.this);
        dialog.content("确定生成故障工单吗?")//
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
                        startAsyncTask2();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 提交质量反馈单数据*
     */
    private void startAsyncTask2() {
        String updataInfo = null;
        updataInfo = JsonUtils.WorkToJson(getWorkOrder());
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(Udreport_DetailActivity.this,
                        finalUpdataInfo, "WORKORDER", "WONUM", AccountUtils.getpersonId(Udreport_DetailActivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    MessageUtils.showMiddleToast(Udreport_DetailActivity.this, "生成故障工单失败");
                } else if (workResult.errorMsg.equals("成功")) {
                    MessageUtils.showMiddleToast(Udreport_DetailActivity.this, "工单" + workResult.wonum + "新增成功");
                    udreport.setWONUM(workResult.wonum);
                    wonum.setText(workResult.wonum);
                    startAsyncTask();//保存
                } else {
                    MessageUtils.showMiddleToast(Udreport_DetailActivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }

        }.execute();
    }

    private WorkOrder getWorkOrder() {
        WorkOrder workOrder = new WorkOrder();
        workOrder.BRANCH = branchText.getText().toString();
        workOrder.UDPROJECTNUM = pronumText.getText().toString();
        workOrder.UDLOCNUM = location_codeText.getText().toString();
        workOrder.UDLOCATION = udreport.getLOCATION();
        workOrder.UDREPORTNUM = udreport.getREPORTNUM();
        workOrder.FAILURECODE = udreport.getFAULT_CODE();
        workOrder.PROBLEMCODE = udreport.getFAULT_CODE1();
//        workOrder.CULEVEL = udgzdjText.getText().toString();
        workOrder.UDSTATUS = "新建";
        workOrder.UDRPRRSB = AccountUtils.getpersonId(Udreport_DetailActivity.this);
        workOrder.UDZGLIMIT = udreport.getREPORTTIME();
        workOrder.CREATEBY = udreport.getCREATEBY();
        workOrder.CREATEDATE = udreport.getREPORTTIME();
        workOrder.WORKTYPE = "FR";
        return workOrder;
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo3() {
        final NormalDialog dialog = new NormalDialog(Udreport_DetailActivity.this);
        dialog.content("确定生成质量问题反馈单吗?")//
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
                        startAsyncTask3();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 提交数据*
     */
    private void startAsyncTask3() {
        String updataInfo = null;
        updataInfo = JsonUtils.UdqtyformToJson(getUdqtyform());
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(Udreport_DetailActivity.this,
                        finalUpdataInfo, "UDQTYFORM", "QTYFORMNUM", AccountUtils.getpersonId(Udreport_DetailActivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(Udreport_DetailActivity.this, "生成质量问题反馈单失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udreport_DetailActivity.this, "质量问题反馈单" + workResult.wonum + "新增成功", Toast.LENGTH_SHORT).show();
                    udreport.setUDPBFORMNUM(workResult.wonum);
                    udpbformnum.setText(workResult.wonum);
                    startAsyncTask();//保存质量问题反馈单号
                } else {
                    Toast.makeText(Udreport_DetailActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }

        }.execute();
    }

    private Udqtyform getUdqtyform() {
        Udqtyform udqtyform = new Udqtyform();
        udqtyform.PRONUM = udreport.getPRONUM();
        udqtyform.FAULTDATE = getDate(udreport.getHAPPEN_TIME());
        udqtyform.PERSONID = AccountUtils.getpersonId(Udreport_DetailActivity.this);
        return udqtyform;
    }

    private String getDate(String datetime) {
        String srcPattern = "yyyy-MM-dd hh:mm:ss";
        String outPattern = "yyyy-MM-dd";
        String out = "";
        try {
            out = new SimpleDateFormat(outPattern).format(new SimpleDateFormat(srcPattern).parse(datetime));
        } catch (ParseException e) {
            Toast.makeText(Udreport_DetailActivity.this, "无效时间格式", Toast.LENGTH_SHORT).show();
        }
        return out;
    }

    private void getFailureList(){//得到故障问题failurelist
        HttpManager.getDataPagingInfo(this, HttpManager.getFailurelist3Url(1, 20, udreport.getFAULT_CODE()), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                if (results.getResultlist() != null) {
                    ArrayList<Failurelist> items = JsonUtils.parsingFailurelist(results.getResultlist());
                    if (items != null && items.size() == 1) {//问题原因
                        failurelist = items.get(0).FAILURELIST + "";
                    }
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    pronumText.setText(option.getName());
                    prdescText.setText(option.getDesc());
                    branchText.setText(option.getValue1());
                    branchdescText.setText(option.getValue6());
                    location_codeText.setText("");
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    location_codeText.setText(option.getName());
                    break;
                case 3:
                    option = (Option) data.getSerializableExtra("option");
                    assetlocText.setText(option.getName());
                    assetlocdescText.setText(option.getDesc());
                    udreport.setLOCATION(option.getName());
                    break;
                case 4:
                    option = (Option) data.getSerializableExtra("option");
                    fault_codedescText.setText(option.getDesc());
                    udreport.setFAULT_CODE(option.getName());
                    failurelist = option.getValue1();
                    fault_code1Text.setText("");
                    fault_code1descText.setText("");
                    break;
                case 5:
                    option = (Option) data.getSerializableExtra("option");
                    fault_code1Text.setText(option.getName());
                    fault_code1descText.setText(option.getDesc());
                    udreport.setFAULT_CODE1(option.getName());
                    break;
            }
        }
    }
}
