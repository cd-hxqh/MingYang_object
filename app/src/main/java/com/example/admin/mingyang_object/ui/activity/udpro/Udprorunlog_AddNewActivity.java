package com.example.admin.mingyang_object.ui.activity.udpro;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.UdprorunlogLine1;
import com.example.admin.mingyang_object.model.UdprorunlogLine2;
import com.example.admin.mingyang_object.model.UdprorunlogLine3;
import com.example.admin.mingyang_object.model.UdprorunlogLine4;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * 项目日报新增
 */
public class Udprorunlog_AddNewActivity extends BaseActivity {
    private static String TAG = "Udprorunlog_DetailActivity";

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
    private LinearLayout prorunlognumlayout;
    private TextView prorunlognumText; //日志编号

    private TextView descriptionText; //描述

    private TextView pronumText; //项目编号

    private TextView branchText; //所属中心

    private TextView udprorescText; //现场负责人

    private TextView contractsText; //现场联系人

    private TextView yearText; //年

    private TextView monthText;//月

    private TextView prostageText; //项目阶段

    private TextView statusText; //状态

    private String pronumDesc = "";//项目描述


    private Udprorunlog udprorunlog = new Udprorunlog();

    private PopupWindow popupWindow;

    /**
     * 土建*
     */
    private LinearLayout tujianLinearLayout;
    /**
     * 吊装*
     */
    private LinearLayout diaozhuangLinearLayout;
    /**
     * 工作日志*
     */
    private LinearLayout gzrzLinearLayout;
    /**
     * 工装管理*
     */
    private LinearLayout gzglLinearLayout;

    private Button cancel;//取消
    private Button save;//保存

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    private ArrayList<UdprorunlogLine1> UdprorunlogLine1List = new ArrayList<>();
    private ArrayList<UdprorunlogLine2> UdprorunlogLine2List = new ArrayList<>();
    private ArrayList<UdprorunlogLine3> UdprorunlogLine3List = new ArrayList<>();
    private ArrayList<UdprorunlogLine4> UdprorunlogLine4List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udprorunlog_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
//        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        prorunlognumlayout = (LinearLayout) findViewById(R.id.prorunlognum_layout);
        prorunlognumText = (TextView) findViewById(R.id.prorunlognum_text_id);
        descriptionText = (TextView) findViewById(R.id.desction_text_id);
        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        branchText = (TextView) findViewById(R.id.branch_text_id);
        udprorescText = (TextView) findViewById(R.id.udproresc_text_id);
        contractsText = (TextView) findViewById(R.id.contdesc_text_id);
        yearText = (TextView) findViewById(R.id.year_text_id);
        monthText = (TextView) findViewById(R.id.month_text_id);
        prostageText = (TextView) findViewById(R.id.prostage_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udprorunlog_addnew_title));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        prorunlognumlayout.setVisibility(View.GONE);
        statusText.setText("新建");
        pronumText.setOnClickListener(new LayoutOnClickListener(1, Constants.UDPROCODE));
        contractsText.setOnClickListener(new LayoutOnClickListener(2, Constants.PERSONCODE));
        yearText.setOnClickListener(new NormalListDialogOnClickListener(yearText));
        monthText.setOnClickListener(new NormalListDialogOnClickListener(monthText));

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
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udprorunlog_AddNewActivity.this).inflate(
                R.layout.logpopup_window, null);


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

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        popupWindow.showAsDropDown(view);
        tujianLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline1_text_id);
        diaozhuangLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline2_text_id);
        gzrzLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline_text_id);
        gzglLinearLayout = (LinearLayout) contentView.findViewById(R.id.udprorunlogline4_text_id);

        tujianLinearLayout.setOnClickListener(tujianLinearOnClickListener);
        diaozhuangLinearLayout.setOnClickListener(diaozhuangLinearOnClickListener);
        gzrzLinearLayout.setOnClickListener(gzrzLinearOnClickListener);
        gzglLinearLayout.setOnClickListener(gzglLinearOnClickListener);

    }


    private View.OnClickListener tujianLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!pronumText.getText().equals("")) {
                Intent intent = new Intent(Udprorunlog_AddNewActivity.this, Udprorunlog_Line1Activity.class);
                intent.putExtra("udprorunlog", getUdprorunlog());
                intent.putExtra("UdprorunlogLine1List", UdprorunlogLine1List);
                startActivityForResult(intent, 1000);
                popupWindow.dismiss();
            } else {
                Toast.makeText(Udprorunlog_AddNewActivity.this, "请选择项目编号", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private View.OnClickListener diaozhuangLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udprorunlog_AddNewActivity.this, Udprorunlog_Line2Activity.class);
            intent.putExtra("udprorunlog", getUdprorunlog());
            intent.putExtra("UdprorunlogLine2List", UdprorunlogLine2List);
            startActivityForResult(intent, 2000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener gzrzLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udprorunlog_AddNewActivity.this, Udprorunlog_Line3Activity.class);
            intent.putExtra("udprorunlog", getUdprorunlog());
            intent.putExtra("UdprorunlogLine3List", UdprorunlogLine3List);
            startActivityForResult(intent, 3000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener gzglLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udprorunlog_AddNewActivity.this, Udprorunlog_Line4Activity.class);
            intent.putExtra("udprorunlog", getUdprorunlog());
            intent.putExtra("UdprorunlogLine4List", UdprorunlogLine4List);
            startActivityForResult(intent, 4000);
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
            Intent intent = new Intent(Udprorunlog_AddNewActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
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
        if (textView == yearText) {
            types = getResources().getStringArray(R.array.year_array);
        } else if (textView == monthText) {
            types = getResources().getStringArray(R.array.month_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udprorunlog_AddNewActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(mMenuItems.get(position).mOperName);
                if (!yearText.getText().toString().equals("") && !monthText.getText().equals("")) {
                    descriptionText.setText(yearText.getText().toString() + "年" + monthText.getText() + "月" + pronumDesc);
                }
                dialog.dismiss();
            }
        });
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Udprorunlog_AddNewActivity.this);
        dialog.content("确定新增项目日报吗?")//
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

    private Udprorunlog getUdprorunlog() {
        Udprorunlog udprorunlog = this.udprorunlog;
        udprorunlog.PRORUNLOGNUM = prorunlognumText.getText().toString();
        udprorunlog.DESCRIPTION = descriptionText.getText().toString();
        udprorunlog.PRONUM = pronumText.getText().toString();
//        udprorunlog.BRANCH = branchText.getText().toString();
//        udprorunlog.UDPRORESC = udprorescText.getText().toString();
//        udprorunlog.CONTRACTS = contractsText.getText().toString();
        udprorunlog.YEAR = yearText.getText().toString();
        udprorunlog.MONTH = monthText.getText().toString();
        udprorunlog.PROSTAGE = prostageText.getText().toString();
        udprorunlog.STATUS = statusText.getText().toString();
        udprorunlog.isnew = true;
        return udprorunlog;
    }

    private boolean isOK(){
        if (pronumText.getText().toString().equals("")){
            Toast.makeText(Udprorunlog_AddNewActivity.this,"项目编号不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (contractsText.getText().toString().equals("")){
            Toast.makeText(Udprorunlog_AddNewActivity.this,"现场联系人不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (yearText.getText().toString().equals("")){
            Toast.makeText(Udprorunlog_AddNewActivity.this,"年份不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (monthText.getText().toString().equals("")){
            Toast.makeText(Udprorunlog_AddNewActivity.this,"月份不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private ArrayList<UdprorunlogLine1> getUdprorunlogLine1() {
        ArrayList<UdprorunlogLine1> udprorunlogLine1s = new ArrayList<>();
        for (int i = 0; i < UdprorunlogLine1List.size(); i++) {
            if (UdprorunlogLine1List.get(i).TYPE != null) {
                udprorunlogLine1s.add(UdprorunlogLine1List.get(i));
            }
        }
        return udprorunlogLine1s;
    }

    private ArrayList<UdprorunlogLine2> getUdprorunlogLine2() {
        ArrayList<UdprorunlogLine2> udprorunlogLine2s = new ArrayList<>();
        for (int i = 0; i < UdprorunlogLine2List.size(); i++) {
            if (UdprorunlogLine2List.get(i).TYPE != null) {
                udprorunlogLine2s.add(UdprorunlogLine2List.get(i));
            }
        }
        return udprorunlogLine2s;
    }

    private ArrayList<UdprorunlogLine3> getUdprorunlogLine3() {
        ArrayList<UdprorunlogLine3> udprorunlogLine3s = new ArrayList<>();
        for (int i = 0; i < UdprorunlogLine3List.size(); i++) {
            if (UdprorunlogLine3List.get(i).TYPE != null) {
                udprorunlogLine3s.add(UdprorunlogLine3List.get(i));
            }
        }
        return udprorunlogLine3s;
    }

    private ArrayList<UdprorunlogLine4> getUdprorunlogLine4() {
        ArrayList<UdprorunlogLine4> udprorunlogLine4s = new ArrayList<>();
        for (int i = 0; i < UdprorunlogLine4List.size(); i++) {
            if (UdprorunlogLine4List.get(i).TYPE != null) {
                udprorunlogLine4s.add(UdprorunlogLine4List.get(i));
            }
        }
        return udprorunlogLine4s;
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
        updataInfo = JsonUtils.UdprorunlogToJson(getUdprorunlog(), getUdprorunlogLine1(),getUdprorunlogLine2(),getUdprorunlogLine3(),getUdprorunlogLine4());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(Udprorunlog_AddNewActivity.this,
                        finalUpdataInfo, "UDPRORUNLOG", "PRORUNLOGNUM", AccountUtils.getpersonId(Udprorunlog_AddNewActivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(Udprorunlog_AddNewActivity.this, "新增项目日报失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udprorunlog_AddNewActivity.this, "项目日报" + workResult.wonum + "新增成功", Toast.LENGTH_SHORT).show();
                    udprorunlog.isnew = false;
                    finish();
                } else {
                    Toast.makeText(Udprorunlog_AddNewActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
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
                    pronumText.setText(option.getName());
                    pronumDesc = option.getName() + option.getDesc();
                    branchText.setText(option.getValue6());
                    udprorescText.setText(option.getValue4());
                    contractsText.setText(option.getValue4());
                    udprorunlog.CONTRACTS = option.getValue2();
                    udprorunlog.UDPRORESC = option.getValue4();
                    udprorunlog.RESPONSID = option.getValue2();
                    prostageText.setText(option.getValue3());
                    if (!monthText.getText().equals("") && !yearText.getText().equals("")) {
                        descriptionText.setText(yearText.getText().toString() + "年" + monthText.getText().toString()
                                + "月" + pronumDesc);
                    } else {
                        descriptionText.setText(pronumDesc);
                    }
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    contractsText.setText(option.getDesc());
                    udprorunlog.CONTRACTS = option.getName();
                    break;
                case 1000:
                    UdprorunlogLine1List = (ArrayList<UdprorunlogLine1>) data.getSerializableExtra("UdprorunlogLine1List");
                    break;
                case 2000:
                    UdprorunlogLine2List = (ArrayList<UdprorunlogLine2>) data.getSerializableExtra("UdprorunlogLine2List");
                    break;
                case 3000:
                    UdprorunlogLine3List = (ArrayList<UdprorunlogLine3>) data.getSerializableExtra("UdprorunlogLine3List");
                    break;
                case 4000:
                    UdprorunlogLine4List = (ArrayList<UdprorunlogLine4>) data.getSerializableExtra("UdprorunlogLine4List");
                    break;
            }
        }
    }
}
