package com.example.admin.eam.ui.activity;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.eam.R;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.dao.UdinspoDao;
import com.example.admin.eam.dao.UdinsprojectDao;
import com.example.admin.eam.model.Udinspo;
import com.example.admin.eam.model.Udinsproject;
import com.example.admin.eam.model.WebResult;
import com.example.admin.eam.utils.AccountUtils;
import com.example.admin.eam.utils.DateSelect;
import com.example.admin.eam.utils.DateTimeSelect;
import com.example.admin.eam.utils.MessageUtils;
import com.example.admin.eam.utils.NetWorkHelper;
import com.example.admin.eam.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * 巡检单详情
 */
public class Udinspo_DetailActivity extends BaseActivity {
    private static String TAG = "Udinspo_DetailActivity";

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
    private TextView reportnumText; //编号

    private TextView descriptionText; //描述

    private TextView branchText;//中心

    private TextView branchdescText; //中心描述

    private TextView pronumText; //项目编号

    private TextView prdescText; //项目名称

    private TextView modeltypeText; //风机型号

    private TextView jpnumText; //巡检标准

    private TextView udlocnumText; //机位号

    private TextView udwptype;//处理人员类型
    private EditText udwp;//处理人员

//    private TextView fjdescText; //设备位置

    private TextView statusText; //状态

    private TextView inspplannumText; //巡检计划单号

    private TextView resbyText; //巡检负责人

    private TextView inspobyText; //巡检人员

    private TextView inspoby1Text; //巡检人员1

    private TextView inspoby2Text; //巡检人员2

    private TextView inspoby4Text; //巡检人员4

    private TextView inspoby5Text; //巡检人员5

    private TextView inspoby6Text; //巡检人员6


    private CheckBox isstopCheckBox; //是否停机

    private TextView inspodateText; //巡检日期

    private TextView stoptimeText; //停机时间

    private TextView oktimeText; //恢复时间

    private TextView alltimeText; //累计停机时间

    private TextView createbyText; //创建人

    private TextView createdateText; //创建时间

    private TextView changebyText; //修改人

    private TextView changedateText; //修改时间

    private TextView weatherText; //天气

    private TextView starttimeText; //计划开始日期

    private TextView comptimeText; //计划完成时间

    private TextView lastrundateText; //上次巡检时间

    private TextView nextrundateText; //下次巡检时间

    private TextView wonumText;//故障工单

    private Udinspo udinspo;

    private PopupWindow popupWindow;

    private LinearLayout buttonlayout;
    private Button cancel;
    private Button save;

    /**
     * 是否停机*
     */
    private boolean istingji = false;

    /**
     * 巡检项目*
     */
    private LinearLayout udinsprojectLinear;
    /**
     * 发送工作流*
     */
    private LinearLayout flowerLinearLayout;
    /**
     * 图片上传*
     */
    private LinearLayout uploadLinearLayout;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private ProgressDialog mProgressDialog;

    private ArrayList<Udinsproject> udinsprojectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspo_details);
        geiIntentData();
        findViewById();
        initView();
        checkWFPRequired(Udinspo_DetailActivity.this,"UDINSPOAPP","UDINSPO",udinspo.getSTATUS());
        Log.e("必填字段","巡检单必填字段"+RequiredFields);
    }

    private void geiIntentData() {
        udinspo = (Udinspo) getIntent().getSerializableExtra("udinspo");
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
        modeltypeText = (TextView) findViewById(R.id.modeltype_text_id);
        jpnumText = (TextView) findViewById(R.id.jpnum_text_id);
        udlocnumText = (TextView) findViewById(R.id.udlocnum_text_id);
        udwptype = (TextView) findViewById(R.id.work_udwptype);
        udwp = (EditText) findViewById(R.id.work_udwp);
//        fjdescText = (TextView) findViewById(R.id.fjdesc_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        inspplannumText = (TextView) findViewById(R.id.inspplannum_text_id);
        resbyText = (TextView) findViewById(R.id.resby_text_id);
        inspobyText = (TextView) findViewById(R.id.inspoby_text_id);
        inspoby1Text = (TextView) findViewById(R.id.inspoby_1_text_id);
        inspoby2Text = (TextView) findViewById(R.id.inspoby_2_text_id);
        inspoby4Text = (TextView) findViewById(R.id.inspoby_4_text_id);
        inspoby5Text = (TextView) findViewById(R.id.inspoby_5_text_id);
        inspoby6Text = (TextView) findViewById(R.id.inspoby_6_text_id);
        isstopCheckBox = (CheckBox) findViewById(R.id.isstop_text_id);
        inspodateText = (TextView) findViewById(R.id.inspodate_text_id);
        stoptimeText = (TextView) findViewById(R.id.stoptime_text_id);
        oktimeText = (TextView) findViewById(R.id.oktime_text_id);
        alltimeText = (TextView) findViewById(R.id.alltime_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        changebyText = (TextView) findViewById(R.id.changeby_text_id);
        changedateText = (TextView) findViewById(R.id.changedate_text_id);
        weatherText = (TextView) findViewById(R.id.weather_text_id);
        starttimeText = (TextView) findViewById(R.id.starttime_text_id);
        comptimeText = (TextView) findViewById(R.id.comptime_1_text_id);
        lastrundateText = (TextView) findViewById(R.id.lastrundate_text_id);
        nextrundateText = (TextView) findViewById(R.id.nextrundate_text_id);
        wonumText = (TextView) findViewById(R.id.wonum_text);

        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);

        if (udinspo != null) {
            reportnumText.setText(udinspo.getINSPONUM());
            descriptionText.setText(udinspo.getDESCRIPTION());
            branchText.setText(udinspo.getBRANCH());
            branchdescText.setText(udinspo.getBRANCHDESC());
            pronumText.setText(udinspo.getPRONUM());
            prdescText.setText(udinspo.getPRODESC());
            modeltypeText.setText(udinspo.getMODELTYPE());
            jpnumText.setText(udinspo.getJPDESC());
            udlocnumText.setText(udinspo.getUDLOCNUM());
            udwptype.setText(udinspo.getUDWPTYPE());
            udwp.setText(udinspo.getUDWP());
//            fjdescText.setText(udinspo.getFJDESC());
            statusText.setText(udinspo.getSTATUS());
            inspplannumText.setText(udinspo.getINSPPLANNUM());
            resbyText.setText(udinspo.getNAME());
            inspobyText.setText(udinspo.getNAME1());
            inspoby1Text.setText(udinspo.getNAME2());
            inspoby2Text.setText(udinspo.getNAME3());
            inspoby4Text.setText(udinspo.getNAME4());
            inspoby5Text.setText(udinspo.getNAME5());
            inspoby6Text.setText(udinspo.getNAME6());
            if (udinspo.getISSTOP().equals("Y")) {
                isstopCheckBox.setChecked(true);
            } else {
                isstopCheckBox.setChecked(false);
            }
            inspodateText.setText(udinspo.getINSPODATE());
            stoptimeText.setText(udinspo.getSTOPTIME());
            oktimeText.setText(udinspo.getOKTIME());
            alltimeText.setText(udinspo.getALLTIME());
            createbyText.setText(udinspo.getCREATEBY());
            createdateText.setText(udinspo.getCREATEDATE());
            changebyText.setText(udinspo.getCHANGEBY());
            changedateText.setText(udinspo.getCHANGEDATE());
            weatherText.setText(udinspo.getWEATHER());
            starttimeText.setText(udinspo.getSTARTTIME());
            comptimeText.setText(udinspo.getCOMPTIME());
            lastrundateText.setText(udinspo.getLASTRUNDATE());
            nextrundateText.setText(udinspo.getNEXTRUNDATE());
            wonumText.setText(udinspo.getWONUM());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udinspo_detail_text));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        if (udinspo.getSTATUS().equals("待执行")) {
            buttonlayout.setVisibility(View.VISIBLE);
            udwptype.setOnClickListener(new NormalListDialogOnClickListener(udwptype));
//            weatherText.setOnClickListener(new NormalListDialogOnClickListener(weatherText));
            inspodateText.setOnClickListener(new DateChecked(inspodateText));
            stoptimeText.setOnClickListener(new DateAndTimeChecked(stoptimeText));
            oktimeText.setOnClickListener(new DateAndTimeChecked(oktimeText));
            isstopCheckBox.setOnCheckedChangeListener(isstopCheckBoxOnCheckedChangeListener);
            setIsEnabled(true);
        } else {
            weatherText.setEnabled(false);
            buttonlayout.setVisibility(View.GONE);
        }

        if (udinspo.id != 0) {
            getLocationData(udinspo.id);
        }

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


    /**
     * 判断数据是否能修改*
     */
    private void setIsEnabled(boolean isEnabled) {
        weatherText.setEnabled(isEnabled);
        inspodateText.setEnabled(isEnabled);
        stoptimeText.setEnabled(isEnabled);
        oktimeText.setEnabled(isEnabled);
        isstopCheckBox.setEnabled(isEnabled);
    }


    private CompoundButton.OnCheckedChangeListener isstopCheckBoxOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            istingji = isChecked;
        }
    };


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

        View contentView = LayoutInflater.from(Udinspo_DetailActivity.this).inflate(
                R.layout.udinspo_popup_window, null);


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
        udinsprojectLinear = (LinearLayout) contentView.findViewById(R.id.udinproject_id);
        flowerLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_flower_id);
        uploadLinearLayout = (LinearLayout) contentView.findViewById(R.id.upload_data_id);

        udinsprojectLinear.setOnClickListener(udinsprojectLinearOnClickListener);
        flowerLinearLayout.setOnClickListener(flowerOnClickListener);
        uploadLinearLayout.setOnClickListener(uploadLinearLayoutOnClickListener);

    }


    private View.OnClickListener udinsprojectLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udinspo_DetailActivity.this, Udinsproject_ListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("udinspo", udinspo);
            bundle.putSerializable("udinsprojectList", udinsprojectList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1000);
            popupWindow.dismiss();

        }
    };

    private View.OnClickListener flowerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (statusText.getText().toString().equals("待执行")) {
                if (checkRequiredFieldcompeletion()) {
                    MaterialDialogOneBtn();
                }
            }else {
                Toast.makeText(Udinspo_DetailActivity.this,"仅当状态为待执行时才能发送工作流",Toast.LENGTH_SHORT).show();
            }
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener uploadLinearLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent intent = new Intent(Udinspo_DetailActivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "UDINSPO");
            intent.putExtra("ownerid", udinspo.getUDINSPOID());
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

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
        if (textView == udwptype) {
            types = getResources().getStringArray(R.array.udwptype_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udinspo_DetailActivity.this, mMenuItems);
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

    class DateAndTimeChecked implements View.OnClickListener {
        TextView textView;

        public DateAndTimeChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateTimeSelect(Udinspo_DetailActivity.this, textView).showDialog();
        }
    }

    class DateChecked implements View.OnClickListener {
        TextView textView;

        public DateChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateSelect(Udinspo_DetailActivity.this, textView).showDialog();
        }
    }

    private Udinspo getUdinspo() {
        Udinspo udinspo = this.udinspo;
        udinspo.setWEATHER(weatherText.getText().toString());
        if (istingji) {
            udinspo.setISSTOP("Y");
        } else {
            udinspo.setISSTOP("N");
        }
        udinspo.setWEATHER(weatherText.getText().toString());
        udinspo.setINSPODATE(inspodateText.getText().toString());
        udinspo.setSTOPTIME(stoptimeText.getText().toString());
        udinspo.setOKTIME(oktimeText.getText().toString());
        udinspo.setUDWPTYPE(udwptype.getText().toString());
        udinspo.setUDWP(udwp.getText().toString());
        return udinspo;
    }

    private ArrayList<Udinsproject> getUdinsprojectList() {
        ArrayList<Udinsproject> udinsprojects = new ArrayList<>();
        for (int i = 0; i < udinsprojectList.size(); i++) {
            if (udinsprojectList.get(i).TYPE != null && udinsprojectList.get(i).TYPE.equals("update")) {
                udinsprojects.add(udinsprojectList.get(i));
            }
        }
        return udinsprojects;
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Udinspo_DetailActivity.this);
        dialog.content("确定修改巡检单吗?")//
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
        if (NetWorkHelper.isNetwork(Udinspo_DetailActivity.this)) {
            MessageUtils.showMiddleToast(Udinspo_DetailActivity.this, "暂无网络,现离线保存数据!");
            saveWorkOrder();
            closeProgressDialog();
        } else {
            String updataInfo = null;
//            if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
            updataInfo = JsonUtils.UdinspoToJson(getUdinspo(), getUdinsprojectList());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
            final String finalUpdataInfo = updataInfo;
            new AsyncTask<String, String, WebResult>() {
                @Override
                protected WebResult doInBackground(String... strings) {
                    WebResult reviseresult = AndroidClientService.UpdateWO(Udinspo_DetailActivity.this, finalUpdataInfo,
                            "UDINSPO", "INSPONUM", udinspo.getINSPONUM(), Constants.WORK_URL);
                    return reviseresult;
                }

                @Override
                protected void onPostExecute(WebResult workResult) {
                    super.onPostExecute(workResult);
                    if (workResult.errorMsg == null) {
                        Toast.makeText(Udinspo_DetailActivity.this, "修改巡检单失败", Toast.LENGTH_SHORT).show();
                    } else if (workResult.errorMsg.equals("成功")) {
                        Toast.makeText(Udinspo_DetailActivity.this, "修改巡检单成功", Toast.LENGTH_SHORT).show();
                        setResult(100);
                        finish();
                    } else {
                        Toast.makeText(Udinspo_DetailActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                    }
                    closeProgressDialog();
                }
            }.execute();
        }
    }

    private void saveWorkOrder() {
        Udinspo udinspo = getUdinspo();
        udinspo.belong = AccountUtils.getpersonId(Udinspo_DetailActivity.this);
//        workOrder.ishistory = true;
        new UdinspoDao(Udinspo_DetailActivity.this).update(udinspo);
        int id = udinspo.id;
        if (id != 0) {
            if (udinsprojectList.size() != 0) {
                for (Udinsproject udinsproject : udinsprojectList) {
                    udinsproject.belongid = id;
                }
                if (new UdinsprojectDao(Udinspo_DetailActivity.this).queryByInsponum(udinspo.getINSPONUM()).size() > 0) {//删除默认保存的记录，防止重复
                    new UdinsprojectDao(Udinspo_DetailActivity.this).deleteList(new UdinsprojectDao(Udinspo_DetailActivity.this).queryByInsponum(udinspo.getINSPONUM()));
                }
                new UdinsprojectDao(Udinspo_DetailActivity.this).create(udinsprojectList);
            }
        }
    }

    //如果为历史数据，则获取本地子表信息
    private void getLocationData(int id) {
        udinsprojectList = (ArrayList<Udinsproject>) new UdinsprojectDao(Udinspo_DetailActivity.this).queryById(id);
        if (udinsprojectList == null || udinsprojectList.size() == 0) {//如果没有修过记录，则查找默认保存记录
            udinsprojectList = (ArrayList<Udinsproject>) new UdinsprojectDao(Udinspo_DetailActivity.this).queryByInsponum(udinspo.getINSPONUM());
        }
    }

    private void MaterialDialogOneBtn() {//审批工作流
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Udinspo_DetailActivity.this);
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
     * 审批工作流
     *
     * @param zx
     */
    private void wfgoon(final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Udinspo_DetailActivity.this, null,
                getString(R.string.approve), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult result = AndroidClientService.approve(Udinspo_DetailActivity.this,
                        "UDINSPO", "UDINSPO", udinspo.getUDINSPOID(), "UDINSPOID", zx, desc,AccountUtils.getpersonId(Udinspo_DetailActivity.this));

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(WebResult s) {
                super.onPostExecute(s);
                if (s == null || s.wonum == null || s.errorMsg == null) {
                    Toast.makeText(Udinspo_DetailActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else if (s.wonum.equals(udinspo.getUDINSPOID()) && s.errorMsg != null) {
                    statusText.setText(s.errorMsg);
                    udinspo.setSTATUS(s.errorMsg);
                    Toast.makeText(Udinspo_DetailActivity.this, "审批成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Udinspo_DetailActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }


    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Udinspo_DetailActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {

            switch (requestCode) {

                case 1000:

                    if (data.hasExtra("udinsprojectList") && data.getSerializableExtra("udinsprojectList") != null) {

                        udinsprojectList = (ArrayList<Udinsproject>) data.getSerializableExtra("udinsprojectList");

                    }
                    break;
            }
        }
    }
    private boolean checkRequiredFieldcompeletion()
    {
        StringBuilder nullFields= new StringBuilder("");

        if (RequiredFields==null||RequiredFields.size()==0)
        {
            return true;
        }
        else
        {
            for(String str:RequiredFields)
            {
                try {

                    Method getter = null;
                    getter = udinspo.getClass().getMethod("get"+str);
                    String value=(String)getter.invoke(udinspo);

                    if (value==null||value.length()<=0)
                    {
                        nullFields.append(str+",");

                        Log.e("必填字段","字段名："+str+"字段值为空");
                    }
                     else
                    {
                        Log.e("必填字段","字段名："+str+"字段值："+value);
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }

            }
            if (nullFields.length()==0)
            {
                return true;
            }
            else
            {
                Toast.makeText(this,"以下内容未填写<"+nullFields.toString()+">,请填写并保存后进行其它操作",Toast.LENGTH_LONG);
            }

        }
        return true;
    }
}
