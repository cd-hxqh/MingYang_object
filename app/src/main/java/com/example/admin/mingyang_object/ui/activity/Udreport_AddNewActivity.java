package com.example.admin.mingyang_object.ui.activity;

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
import com.example.admin.mingyang_object.model.Udreport;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.utils.GetDateAndTime;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;

/**
 * Created by think on 2016/7/9.
 * 故障提报单新增页面
 */
public class Udreport_AddNewActivity extends BaseActivity {

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;


    /**
     * 界面信息*
     */
    private LinearLayout numLayout;

    private TextView reportnumText; //编号

    private TextView descriptionText; //描述

    private TextView branchText; //中心

    private TextView branchdescText;//中心描述

    private TextView pronumText; //项目中心

    private TextView prdescText; //项目名称

    private TextView location_codeText; //机位号

    private TextView assetlocText; //设备位置

    private TextView assetlocdescText;//设备位置描述

    private TextView culevelText; //故障等级

    private TextView faulttypeText; //故障类型

    private TextView happen_timeText; //报障时间

    private LinearLayout is_endLayout;
    private CheckBox is_endCheckBox; //故障是否结束

    private TextView end_timeText; //结束时间

    private TextView statustypeText; //状态

    private LinearLayout cuisplanLayout;
    private CheckBox cuisplanTCheckBox; //是否由集控生成

    private TextView createbyText; //提报人

    private TextView reporttimeText; //提报时间

    private TextView fault_codedescText; //故障类


    private TextView fault_code1Text; //故障代码

    private TextView fault_code1descText;//故障代码描述

    private EditText cudescribeText; //故障描述

    private EditText resultText; //处理结果

    private EditText remarkText; //备注

    private LinearLayout num2Layout;//

    private Button cancel;//取消
    private Button save;//保存

    private String failurelist = "";
    private Udreport udreport = new Udreport();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udreport_details);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        numLayout = (LinearLayout) findViewById(R.id.num_layout);
        reportnumText = (TextView) findViewById(R.id.reportnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        branchText = (TextView) findViewById(R.id.branch_text);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);
        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prdescText = (TextView) findViewById(R.id.prdesc_text_id);
        location_codeText = (TextView) findViewById(R.id.location_code_text_id);
        assetlocText = (TextView) findViewById(R.id.assetloc_text_id);
        assetlocdescText = (TextView) findViewById(R.id.assetlocdesc_text);
        culevelText = (TextView) findViewById(R.id.culevel_text_id);
        faulttypeText = (TextView) findViewById(R.id.faulttype_text_id);
        happen_timeText = (TextView) findViewById(R.id.capacity_text_id);
        is_endLayout = (LinearLayout) findViewById(R.id.is_end_layout);
        is_endCheckBox = (CheckBox) findViewById(R.id.is_end_text_id);
        end_timeText = (TextView) findViewById(R.id.end_time_text_id);
        statustypeText = (TextView) findViewById(R.id.statustype_text_id);
        cuisplanLayout = (LinearLayout) findViewById(R.id.cuisplan_layout);
        cuisplanTCheckBox = (CheckBox) findViewById(R.id.cuisplan_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        reporttimeText = (TextView) findViewById(R.id.reporttime_text_id);
        fault_codedescText = (TextView) findViewById(R.id.fault_codedesc_text_id);
        fault_code1Text = (TextView) findViewById(R.id.fault_code1_text_id);
        fault_code1descText = (TextView) findViewById(R.id.fault_code1desc_text);
        cudescribeText = (EditText) findViewById(R.id.cudescribe_text_id);
        resultText = (EditText) findViewById(R.id.result_text_id);
        remarkText = (EditText) findViewById(R.id.remark_text_id);
        num2Layout = (LinearLayout) findViewById(R.id.num2_layout);

        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText("新增故障提报单");

        numLayout.setVisibility(View.GONE);
        is_endLayout.setVisibility(View.GONE);
        cuisplanLayout.setVisibility(View.GONE);
        num2Layout.setVisibility(View.GONE);
        createbyText.setText(AccountUtils.getpersonId(Udreport_AddNewActivity.this));
        reporttimeText.setText(GetDateAndTime.GetDateTime());
        pronumText.setOnClickListener(new LayoutOnClickListener(1, Constants.UDPROCODE));
        location_codeText.setOnClickListener(new LayoutOnClickListener(2, Constants.UDLOCNUMCODE));
        assetlocText.setOnClickListener(new LayoutOnClickListener(3,Constants.LOCATIONCODE));
//        culevelText.setOnClickListener(new NormalListDialogOnClickListener(culevelText));
//        faulttypeText.setOnClickListener(new NormalListDialogOnClickListener(faulttypeText));
        happen_timeText.setOnClickListener(new DateTimeOnClickListener(happen_timeText));
//        end_timeText.setOnClickListener(new DateTimeOnClickListener(end_timeText));
        statustypeText.setText("新建");
        fault_codedescText.setOnClickListener(new LayoutOnClickListener(4, Constants.FAILURECODE));
        fault_code1Text.setOnClickListener(new LayoutOnClickListener(5, Constants.PROBLEMCODE));
        statustypeText.setOnClickListener(new NormalListDialogOnClickListener(statustypeText));

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

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;
        int optiontype;

        private LayoutOnClickListener(int requestCode, int optiontype) {
            this.requestCode = requestCode;
            this.optiontype = optiontype;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udreport_AddNewActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            if (requestCode == 2) {
                if (pronumText.getText().toString().equals("")) {
                    Toast.makeText(Udreport_AddNewActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    intent.putExtra("udprojectnum", pronumText.getText().toString());
                }
            }
            if (requestCode == 3) {
                if (pronumText.getText().toString().equals("")) {
                    Toast.makeText(Udreport_AddNewActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (location_codeText.getText().toString().equals("")) {
                    Toast.makeText(Udreport_AddNewActivity.this, "请先选择机位号", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("udprojectnum", pronumText.getText().toString());
                intent.putExtra("udlocnum", location_codeText.getText().toString());
            }
            if (requestCode == 5) {
                if (udreport.getFAULT_CODE()!=null&&udreport.getFAULT_CODE().equals("") && failurelist.equals("")) {
                    Toast.makeText(Udreport_AddNewActivity.this, "请先选择故障类", Toast.LENGTH_SHORT).show();
                    return;
                } else if (udreport.getFAULT_CODE()!=null&&!udreport.getFAULT_CODE().equals("") && failurelist.equals("")) {
                    Toast.makeText(Udreport_AddNewActivity.this, "请重新选择故障类", Toast.LENGTH_SHORT).show();
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
        if (textView == culevelText) {
            types = getResources().getStringArray(R.array.culevel_array);
        }else if (textView == faulttypeText){
            types = getResources().getStringArray(R.array.faulttype_array);
        }else if (textView == statustypeText){
            types = getResources().getStringArray(R.array.status_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udreport_AddNewActivity.this, mMenuItems);
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
            new DateTimeSelect(Udreport_AddNewActivity.this, textView).showDialog();
        }
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Udreport_AddNewActivity.this);
        dialog.content("确定新增故障提报单吗?")//
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
                        if (isOK()) {
                            startAsyncTask();
                        }else {
                            closeProgressDialog();
                        }
                        dialog.dismiss();
                    }
                });
    }

    private boolean isOK(){
        if (pronumText.getText().toString().equals("")){
            Toast.makeText(Udreport_AddNewActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (location_codeText.getText().toString().equals("")) {
            Toast.makeText(Udreport_AddNewActivity.this, "请输入机位号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (assetlocText.getText().toString().equals("")){
            Toast.makeText(Udreport_AddNewActivity.this, "请输入设备位置", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (happen_timeText.getText().toString().equals("")){
            Toast.makeText(Udreport_AddNewActivity.this, "请输入故障发生时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fault_codedescText.getText().toString().equals("")){
            Toast.makeText(Udreport_AddNewActivity.this, "请输入故障类", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fault_code1Text.getText().toString().equals("")){
            Toast.makeText(Udreport_AddNewActivity.this, "请输入故障代码", Toast.LENGTH_SHORT).show();
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
        updataInfo = JsonUtils.UdreportToJson(getUdreport());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(Udreport_AddNewActivity.this,
                        finalUpdataInfo, "UDREPORT", "REPORTNUM", AccountUtils.getpersonId(Udreport_AddNewActivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null || workResult.errorMsg == null) {
                    Toast.makeText(Udreport_AddNewActivity.this, "新增故障提报单失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udreport_AddNewActivity.this, "故障提报单" + workResult.wonum + "新增成功", Toast.LENGTH_SHORT).show();
                    setResult(100);
                    finish();
                } else {
                    Toast.makeText(Udreport_AddNewActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }

        }.execute();
////        }
    }

    private Udreport getUdreport(){
        Udreport udreport = this.udreport;
        udreport.setBRANCH(branchText.getText().toString());
        udreport.setPRONUM(pronumText.getText().toString());
        udreport.setLOCATION_CODE(location_codeText.getText().toString());
        udreport.setLOCATION(assetlocText.getText().toString());
//        udreport.setUDGZDJ(culevelText.getText().toString());
//        udreport.setUDGZTYPE(faulttypeText.getText().toString());
        udreport.setHAPPEN_TIME(happen_timeText.getText().toString());
        udreport.setEND_TIME(end_timeText.getText().toString());
        udreport.setSTATUSTYPE(statustypeText.getText().toString());
        udreport.setCREATEBY(createbyText.getText().toString());
        udreport.setREPORTTIME(reporttimeText.getText().toString());
        udreport.setCUDESCRIBE(cudescribeText.getText().toString());
        udreport.setRESULT(resultText.getText().toString());
        udreport.setREMARK(remarkText.getText().toString());
        return udreport;
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
//                    udreport.setLOCATION(option.getName());
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
