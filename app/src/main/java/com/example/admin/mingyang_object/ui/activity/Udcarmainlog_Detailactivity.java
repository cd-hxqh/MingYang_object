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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Udcarmainlog;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * 维修记录详情
 */
public class Udcarmainlog_Detailactivity extends BaseActivity {
    private static String TAG = "Udcarmainlog_Detailactivity";

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
     * scrollview*
     */
    private ScrollView scrollview;


    /**
     * 界面信息*
     */

    /**
     * 基本信息*
     */
    private TextView mainlognumText; //编号

    private TextView descriptionText; //描述

    private TextView licensenumText; //车牌号

    private TextView carnameText; //车辆名称

    private TextView driverid1Text; //司机

    private TextView driveridNameText; //司机名称

    private TextView respnameText; //责任人

    private TextView respname_Text;// 责任人名称

    private TextView prodescText; //所属项目

    private TextView branchdescText; //所属中心

    private TextView createbyText; //录入人

    private TextView createdateText; //录入日期

    /**
     * 维修信息*
     */

    private TextView startdateText; //维修开始日期

    private TextView enddateText; //维修结束日期

    private EditText priceText; //维修单价

    private EditText mainnumberText; //维修数量

    private EditText totalpriceText; //维修总额

    private EditText invoicenumText; //维修发票号


    /**
     * 维修信息1
     */

    private TextView servicetypeText; //维修类型
    private EditText mainplaceText; //维修地点
    private EditText maincontentText; //维修.保养.更换项目
    private EditText number2Text; //上次维修里程表读数
    private EditText number1Text; //本次维修里程表读数
    private CheckBox comisornoText; //是否提交
    private EditText remarkText; //备注

    /**
     * 是否提交*
     */
    private boolean iscomis;

    private Udcarmainlog udcarmainlog;

    private PopupWindow popupWindow;


    /**
     * 附件上传*
     */
    private LinearLayout uploadLinearLayout;
    /**
     * 编辑*
     */
    private LinearLayout editLinearLayouut;
    /**
     * 是否编辑*
     */
    private boolean isEdit = false;

    /**
     * 操作布局界面*
     */
    private LinearLayout operationLinearLayout;
    /**
     * 保存*
     */
    private Button saveButton;
    /**
     * 取消*
     */
    private Button cancelButton;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udcarmainlog_details);
        geiIntentData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();


    }

    private void geiIntentData() {
        udcarmainlog = (Udcarmainlog) getIntent().getSerializableExtra("udcarmainlog");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        scrollview = (ScrollView) findViewById(R.id.scrollview_id);


        mainlognumText = (TextView) findViewById(R.id.mainlognum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);
        driverid1Text = (TextView) findViewById(R.id.driverid1_text_id);
        driveridNameText = (TextView) findViewById(R.id.driverid_name_text_id);
        respnameText = (TextView) findViewById(R.id.respons_text_id);
        respname_Text = (TextView) findViewById(R.id.respname_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);


        startdateText = (TextView) findViewById(R.id.startdate_text_id);
        enddateText = (TextView) findViewById(R.id.enddate_text_id);
        priceText = (EditText) findViewById(R.id.price_text_id);
        mainnumberText = (EditText) findViewById(R.id.mainnumber_text_id);
        totalpriceText = (EditText) findViewById(R.id.totalprice_text_id);
        invoicenumText = (EditText) findViewById(R.id.invoicenum_text_id);

        servicetypeText = (TextView) findViewById(R.id.servicetype_text_id);
        mainplaceText = (EditText) findViewById(R.id.mainplace_text_id);
        maincontentText = (EditText) findViewById(R.id.maincontent_text_id);
        number2Text = (EditText) findViewById(R.id.number2_text_id);
        number1Text = (EditText) findViewById(R.id.number1_text_id);
        comisornoText = (CheckBox) findViewById(R.id.comisorno_text_id);
        remarkText = (EditText) findViewById(R.id.remark_text_id);

        operationLinearLayout = (LinearLayout) findViewById(R.id.button_layout);
        saveButton = (Button) findViewById(R.id.work_save);
        cancelButton = (Button) findViewById(R.id.work_cancel);


        if (udcarmainlog != null) {
            mainlognumText.setText(udcarmainlog.getMAINLOGNUM());
            descriptionText.setText(udcarmainlog.getDESCRIPTION());
            licensenumText.setText(udcarmainlog.getLICENSENUM());
            carnameText.setText(udcarmainlog.getVEHICLENAME());
            driverid1Text.setText(udcarmainlog.getDRIVERID1());
            driveridNameText.setText(udcarmainlog.getDRIVERNAME());
            respnameText.setText(udcarmainlog.getRESPONSID());
            respname_Text.setText(udcarmainlog.getRESPNAME());
            prodescText.setText(udcarmainlog.getPRODESC());
            branchdescText.setText(udcarmainlog.getBRANCHDESC());
            createbyText.setText(udcarmainlog.getCREATEBY());
            createdateText.setText(udcarmainlog.getCREATEDATE());

            startdateText.setText(udcarmainlog.getSTARTDATE());
            enddateText.setText(udcarmainlog.getENDDATE());
            priceText.setText(udcarmainlog.getPRICE());
            mainnumberText.setText(udcarmainlog.getMAINNUMBER());
            totalpriceText.setText(udcarmainlog.getTOTALPRICE());
            invoicenumText.setText(udcarmainlog.getINVOICENUM());


            if (udcarmainlog.getSERVICETYPE() != null && udcarmainlog.getSERVICETYPE().equals("normal")) {
                servicetypeText.setText("正常维修");
            } else if (udcarmainlog.getSERVICETYPE() != null && !udcarmainlog.getSERVICETYPE().equals("normal")){
                servicetypeText.setText("事故维修");
            }
            mainplaceText.setText(udcarmainlog.getMAINPLACE());
            maincontentText.setText(udcarmainlog.getMAINCONTENT());
            number2Text.setText(udcarmainlog.getNUMBER2());
            number1Text.setText(udcarmainlog.getNUMBER1());
            if (udcarmainlog.getCOMISORNO() == null || udcarmainlog.getCOMISORNO().equals("")) {

                comisornoText.setChecked(false);
            } else {
                comisornoText.setChecked(true);
            }
            remarkText.setText(udcarmainlog.getREMARK());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.wxjlxq_text));

        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        operationLinearLayout.setVisibility(View.GONE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        startdateText.setOnClickListener(new DateTimeOnClickListener(startdateText));
        enddateText.setOnClickListener(new DateTimeOnClickListener(enddateText));

        comisornoText.setOnCheckedChangeListener(comisornoTextOnCheckedChangeListener);
        servicetypeText.setOnClickListener(servicetypeTextOnClickListener);

        isEdit(isEdit);

        saveButton.setOnClickListener(saveButtonOnClickListener);
    }

    private View.OnClickListener servicetypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };


    private void NormalListDialog() {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();
        types = getResources().getStringArray(R.array.servicetype_array);
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udcarmainlog_Detailactivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                servicetypeText.setText(mMenuItems.get(position).mOperName);

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
            new DateSelect(Udcarmainlog_Detailactivity.this, textView).showDialog();
        }
    }


    private CompoundButton.OnCheckedChangeListener comisornoTextOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            iscomis = isChecked;
        }
    };


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    private View.OnClickListener saveButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showProgressDialog("数据提交中...");
            startAsyncTask();
        }
    };


    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udcarmainlog_Detailactivity.this).inflate(
                R.layout.popup_item_window, null);


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
        uploadLinearLayout = (LinearLayout) contentView.findViewById(R.id.add_linearlayout_id);
        editLinearLayouut = (LinearLayout) contentView.findViewById(R.id.delete_linearlayout_id);

        TextView udloadText = (TextView) contentView.findViewById(R.id.textView_id);
        ImageView udloadImage = (ImageView) contentView.findViewById(R.id.imageView_id);
        TextView editText = (TextView) contentView.findViewById(R.id.textView_1_id);
        ImageView editImage = (ImageView) contentView.findViewById(R.id.imageView_1_id);
        udloadText.setText(getResources().getString(R.string.work_commit));
        editText.setText(getString(R.string.eidt_text));
        udloadImage.setImageResource(R.mipmap.ic_upload);
        editImage.setImageResource(R.mipmap.ic_edit);


        editLinearLayouut.setOnClickListener(editLinearLayouutOnClickListener);
        uploadLinearLayout.setOnClickListener(uploadLinearLayoutOnClickListener);


    }

    private View.OnClickListener uploadLinearLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent intent = new Intent(Udcarmainlog_Detailactivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "UDCARMAINLOG");
            intent.putExtra("ownerid", udcarmainlog.getUDCARMAINLOGID());
            startActivityForResult(intent, 0);
        }

    };


    private View.OnClickListener editLinearLayouutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            isEdit(!isEdit);

            if (udcarmainlog.getCOMISORNO() != null && udcarmainlog.getCOMISORNO().equals("已提交")) {
                MessageUtils.showMiddleToast(Udcarmainlog_Detailactivity.this, "该记录状态已提交,不可编辑");
            } else {
                if (isEdit) {
                    operationLinearLayout.setVisibility(View.GONE);
                    setSpace(0);
                    isEdit = false;
                } else {
                    operationLinearLayout.setVisibility(View.VISIBLE);
                    setSpace(200);
                    isEdit = true;
                }
            }
        }

    };


    /**
     * 设置scrollview的距离*
     */
    private void setSpace(int space) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollview.getLayoutParams();
        layoutParams.bottomMargin = space;//将默认的距离底部20dp，改为0，这样底部区域全被scrollview填满。
        scrollview.setLayoutParams(layoutParams);
    }


    /**
     * 编辑状态*
     */
    private void isEdit(boolean isshow) {
        //维修开始日期
        startdateText.setEnabled(isshow);
        //维修结束日期
        enddateText.setEnabled(isshow);
        //维修单价
        priceText.setEnabled(isshow);
        //维修数量
        mainnumberText.setEnabled(isshow);
        //维修总额
        totalpriceText.setEnabled(isshow);
        //维修发票号
        invoicenumText.setEnabled(isshow);
        //维修类型
        servicetypeText.setEnabled(isshow);
        //维修地点
        mainplaceText.setEnabled(isshow);
        //维修、保养、更换项目
        maincontentText.setEnabled(isshow);
        //上次维修里程表读数
        number2Text.setEnabled(isshow);
        //本次维修里程表读数
        number1Text.setEnabled(isshow);
        //是否提交
        comisornoText.setEnabled(isshow);
        //备注
        remarkText.setEnabled(isshow);
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.udcarmainlogToJson(capsulation(udcarmainlog));

        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(Udcarmainlog_Detailactivity.this,
                        finalUpdataInfo, "UDCARMAINLOG", "MAINLOGNUM", udcarmainlog.getMAINLOGNUM(), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    MessageUtils.showMiddleToast(Udcarmainlog_Detailactivity.this, "更新失败");
                } else if (workResult.errorMsg.equals("成功")) {
                    MessageUtils.showMiddleToast(Udcarmainlog_Detailactivity.this, "维修记录" + workResult.wonum + "更新成功");
                    setResult(100);
                    finish();
                } else {
                    MessageUtils.showMiddleToast(Udcarmainlog_Detailactivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }
        }.execute();

    }


    /**
     * 封装需要上传的数据*
     */
    private Udcarmainlog capsulation(Udcarmainlog udcarmainlog) {

        String startdate = startdateText.getText().toString(); //维修开始日期
        String enddate = enddateText.getText().toString(); //维修结束日期
        String price = priceText.getText().toString(); //维修单价
        String mainnumber = mainnumberText.getText().toString(); //维修数量
        String totalprice = totalpriceText.getText().toString(); //维修总额
        String invoicenum = invoicenumText.getText().toString(); //维修发票号
        String servicetype = servicetypeText.getText().toString(); //维修类型
        String mainplace = mainplaceText.getText().toString(); //维修地点
        String maincontent = maincontentText.getText().toString(); //维修、保养、更换项目
        String number2 = number2Text.getText().toString(); //上次维修里程表读数
        String number1 = number1Text.getText().toString(); //本次次维修里程表读数
        String remark = remarkText.getText().toString(); //备注
        if (!startdate.equals("") && !startdate.equals(udcarmainlog.getSTARTDATE())) {
            udcarmainlog.setSTARTDATE(startdate);
        }
        if (!enddate.equals("") && !enddate.equals(udcarmainlog.getENDDATE())) {
            udcarmainlog.setENDDATE(enddate);
        }
        if (!price.equals("") && !price.equals(udcarmainlog.getPRICE())) {
            udcarmainlog.setPRICE(price);
        }
        if (!mainnumber.equals("") && !mainnumber.equals(udcarmainlog.getMAINNUMBER())) {
            udcarmainlog.setMAINNUMBER(mainnumber);
        }
        if (!totalprice.equals("") && !totalprice.equals(udcarmainlog.getTOTALPRICE())) {
            udcarmainlog.setTOTALPRICE(totalprice);
        }
        if (!invoicenum.equals("") && !invoicenum.equals(udcarmainlog.getINVOICENUM())) {
            udcarmainlog.setINVOICENUM(invoicenum);
        }
        if (!servicetype.equals("") && !servicetype.equals(udcarmainlog.getSERVICETYPE())) {
            udcarmainlog.setSERVICETYPE(servicetype);
        }
        if (!mainplace.equals("") && !mainplace.equals(udcarmainlog.getMAINPLACE())) {
            udcarmainlog.setMAINPLACE(mainplace);
        }
        if (!maincontent.equals("") && !mainplace.equals(udcarmainlog.getMAINCONTENT())) {
            udcarmainlog.setMAINCONTENT(maincontent);
        }
        if (!number2.equals("") && !mainplace.equals(udcarmainlog.getNUMBER2())) {
            udcarmainlog.setNUMBER2(number2);
        }
        if (!number1.equals("") && !number1.equals(udcarmainlog.getNUMBER1())) {
            udcarmainlog.setNUMBER1(number1);
        }
        if (!remark.equals("") && !remark.equals(udcarmainlog.getREMARK())) {
            udcarmainlog.setREMARK(remark);
        }
        if (iscomis) {
            udcarmainlog.setCOMISORNO("已提交");
        }


        return udcarmainlog;
    }


}
