package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udcardrivelog;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.utils.TimeSelect;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;

import java.util.ArrayList;


/**
 * 行驶记录详情
 */
public class Udcardrivelog_Detailactivity extends BaseActivity {
    private static String TAG = "Udcardrivelog_Detailactivity";

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
     * 车辆基础信息*
     */
    private TextView cardrivelognumText; //记录编号

    private TextView descriptionText; //描述

    private TextView licensenumText; //车牌号

    private TextView carnameText; //车辆名称

    private TextView driverid1Text; //司机

    private TextView driveridNameText; //司机名称

    private TextView prodescText; //所属项目

    private TextView branchdescText; //所属中心

    /**
     * 录入信息*
     */

    private TextView driveridText; //创建人

    private TextView createbyText; //创建人名称

    private TextView createdateText; //创建时间

    private CheckBox comisornoText; //是否提交

    /**
     * 出车信息*
     */

    private TextView startdateText; //出车日期
    private TextView starttimeText; //出车时间
    private EditText departureText; //出发地
    private EditText destinationText; //目的地
    private EditText goreasonText; //出车事由

    /**
     * 车辆行驶详细信息*
     */
    private TextView wonumText; //业务单号
    private TextView wtypedescText; //任务类型
    private TextView startnumberText; //起始里程
    private EditText endnumberText; //结束里程
    private TextView lastfuelconsumptionText; //上次油耗
    private EditText standardfuelconsumptionText; //标准油耗
    private EditText feeText; //路桥费

    /**
     * 是否提交*
     */
    private boolean iscomis;
    private Udcardrivelog udcardrivelog;

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
        setContentView(R.layout.activity_udcardrivelog_details);
        geiIntentData();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        findViewById();
        initView();
    }

    private void geiIntentData() {
        udcardrivelog = (Udcardrivelog) getIntent().getSerializableExtra("udcardrivelog");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        scrollview = (ScrollView) findViewById(R.id.scrollview_id);


        cardrivelognumText = (TextView) findViewById(R.id.cardrivelognum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);
        driverid1Text = (TextView) findViewById(R.id.driverid1_text_id);
        driveridNameText = (TextView) findViewById(R.id.driverid_name_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);

        driveridText = (TextView) findViewById(R.id.driverid_text_id);
        createbyText = (TextView) findViewById(R.id.createby_name_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        comisornoText = (CheckBox) findViewById(R.id.comisorno_text_id);

        startdateText = (TextView) findViewById(R.id.startdate_text_id);
        starttimeText = (TextView) findViewById(R.id.starttime_text_id);
        departureText = (EditText) findViewById(R.id.departure_text_id);
        destinationText = (EditText) findViewById(R.id.destination_text_id);
        goreasonText = (EditText) findViewById(R.id.goreason_text_id);

        wonumText = (TextView) findViewById(R.id.wonum_text_id);
        wtypedescText = (TextView) findViewById(R.id.wtypedesc_text_id);
        startnumberText = (TextView) findViewById(R.id.startnumber_text_id);
        endnumberText = (EditText) findViewById(R.id.endnumber_text_id);
        lastfuelconsumptionText = (TextView) findViewById(R.id.lastfuelconsumption_text_id);
        standardfuelconsumptionText = (EditText) findViewById(R.id.standardfuelconsumption_text_id);
        feeText = (EditText) findViewById(R.id.fee_text_id);

        operationLinearLayout = (LinearLayout) findViewById(R.id.button_layout);
        saveButton = (Button) findViewById(R.id.work_save);
        cancelButton = (Button) findViewById(R.id.work_cancel);


        if (udcardrivelog != null) {
            cardrivelognumText.setText(udcardrivelog.getCARDRIVELOGNUM());
            descriptionText.setText(udcardrivelog.getDESCRIPTION());
            licensenumText.setText(udcardrivelog.getLICENSENUM());
            carnameText.setText(udcardrivelog.getCARNAME());
            driverid1Text.setText(udcardrivelog.getDRIVERID1());
            driveridNameText.setText(udcardrivelog.getDRIVERNAME());
            prodescText.setText(udcardrivelog.getPRODESC());
            branchdescText.setText(udcardrivelog.getBRANCHDESC());
            driveridText.setText(udcardrivelog.getDRIVERID());
            createbyText.setText(udcardrivelog.getCREATEBY());

            createdateText.setText(udcardrivelog.getCREATEDATE());
            if (udcardrivelog.getCOMISORNO() == null || udcardrivelog.getCOMISORNO().equals("")) {
                comisornoText.setChecked(false);

            } else {
                comisornoText.setChecked(true);
            }

            startdateText.setText(udcardrivelog.getSTARTDATE());
            starttimeText.setText(udcardrivelog.getSTARTTIME());
            departureText.setText(udcardrivelog.getDEPARTURE());
            destinationText.setText(udcardrivelog.getDESTINATION());
            goreasonText.setText(udcardrivelog.getGOREASON());
            wonumText.setText(udcardrivelog.getWONUM());
            wtypedescText.setText(udcardrivelog.getWORKTYPE());
            startnumberText.setText(udcardrivelog.getSTARTNUMBER());
            endnumberText.setText(udcardrivelog.getENDNUMBER());
            lastfuelconsumptionText.setText(udcardrivelog.getLASTFUELCONSUMPTION());
            standardfuelconsumptionText.setText(udcardrivelog.getSTANDARDFUELCONSUMPTION());
            feeText.setText(udcardrivelog.getFEE());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udcarddrivelog_details_text));

        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        operationLinearLayout.setVisibility(View.GONE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        isEdit(isEdit);
        startdateText.setOnClickListener(startdateTextOnClickListener);
        starttimeText.setOnClickListener(starttimeTextOnClickListener);
        comisornoText.setOnCheckedChangeListener(comisornoTextOnCheckedChangeListener);
        wonumText.setOnClickListener(new LayoutOnClickListener(1,Constants.WONUMCODE2));

        saveButton.setOnClickListener(saveButtonOnClickListener);
    }

    private CompoundButton.OnCheckedChangeListener comisornoTextOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            iscomis = isChecked;
        }
    };

    private View.OnClickListener saveButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showProgressDialog("数据提交中...");
            startAsyncTask();
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


    private View.OnClickListener startdateTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DateSelect(Udcardrivelog_Detailactivity.this, startdateText).showDialog();

        }
    };
    private View.OnClickListener starttimeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new TimeSelect(Udcardrivelog_Detailactivity.this, starttimeText).showDialog();

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
            Intent intent = new Intent(Udcardrivelog_Detailactivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }


    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udcardrivelog_Detailactivity.this).inflate(
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


        uploadLinearLayout.setOnClickListener(uploadLinearLayoutOnClickListener);
        editLinearLayouut.setOnClickListener(editLinearLayouutOnClickListener);

    }


    private View.OnClickListener uploadLinearLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent intent = new Intent(Udcardrivelog_Detailactivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "UDCARDRIVELOG");
            intent.putExtra("ownerid", udcardrivelog.getUDCARDRIVELOGID());
            startActivityForResult(intent, 0);
        }

    };
    private View.OnClickListener editLinearLayouutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            if (udcardrivelog.getCOMISORNO() != null && udcardrivelog.getCOMISORNO().equals("已提交")) {
                MessageUtils.showMiddleToast(Udcardrivelog_Detailactivity.this, "该记录状态已提交,不可编辑");
            } else {


                isEdit(!isEdit);
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
        comisornoText.setEnabled(isshow);
        //出车日期
        startdateText.setEnabled(isshow);
        //出车时间
        starttimeText.setEnabled(isshow);
        //出发地
        departureText.setEnabled(isshow);
        //目的地
        destinationText.setEnabled(isshow);

        //出车事由
        goreasonText.setEnabled(isshow);
        //结束里程
        endnumberText.setEnabled(isshow);
        //标准油耗
        standardfuelconsumptionText.setEnabled(isshow);
        //路桥费
        feeText.setEnabled(isshow);
        //业务单号
        wonumText.setEnabled(isshow);
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.udcardrivelogToJson(capsulation(udcardrivelog));

        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(Udcardrivelog_Detailactivity.this,
                        finalUpdataInfo, "UDCARDRIVELOG", "CARDRIVELOGNUM", udcardrivelog.getCARDRIVELOGNUM(), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    MessageUtils.showMiddleToast(Udcardrivelog_Detailactivity.this, "更新失败");
                } else if (workResult.errorMsg.equals("成功")) {
                    MessageUtils.showMiddleToast(Udcardrivelog_Detailactivity.this, "行驶记录" + workResult.wonum + "更新成功");
                    setResult(100);
                    finish();
                } else {
                    MessageUtils.showMiddleToast(Udcardrivelog_Detailactivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }
        }.execute();

    }


    /**
     * 封装需要上传的数据*
     */
    private Udcardrivelog capsulation(Udcardrivelog udcardrivelog) {

        String startdate = startdateText.getText().toString(); //出车日期
        String starttime = starttimeText.getText().toString(); //出车时间
        String departure = departureText.getText().toString(); //出发地
        String destination = destinationText.getText().toString(); //目的地
        String goreason = goreasonText.getText().toString(); //出车事由
        String endnumber = endnumberText.getText().toString(); //结束里程
        String wonum = wonumText.getText().toString(); //业务单号
        String standardfuelconsumption = standardfuelconsumptionText.getText().toString(); //标准油耗
        String fee = feeText.getText().toString(); //路桥费
        if (!startdate.equals("") && !startdate.equals(udcardrivelog.getSTARTDATE())) {
            udcardrivelog.setSTARTDATE(startdate);
        }
        if (!starttime.equals("") && !starttime.equals(udcardrivelog.getSTARTTIME())) {
            udcardrivelog.setSTARTTIME(starttime);
        }
        if (!departure.equals("") && !departure.equals(udcardrivelog.getDEPARTURE())) {
            udcardrivelog.setDEPARTURE(departure);
        }
        if (!destination.equals("") && !destination.equals(udcardrivelog.getDESTINATION())) {
            udcardrivelog.setDESTINATION(destination);
        }
        if (!goreason.equals("") && !goreason.equals(udcardrivelog.getGOREASON())) {
            udcardrivelog.setGOREASON(goreason);
        }
        if (!endnumber.equals("") && !endnumber.equals(udcardrivelog.getENDNUMBER())) {
            udcardrivelog.setENDNUMBER(endnumber);
        }if (!wonum.equals("") && !wonum.equals(udcardrivelog.getWONUM())) {
            udcardrivelog.setWONUM(wonum);
        }
        if (!standardfuelconsumption.equals("") && !standardfuelconsumption.equals(udcardrivelog.getSTANDARDFUELCONSUMPTION())) {
            udcardrivelog.setSTANDARDFUELCONSUMPTION(standardfuelconsumption);
        }
        if (!fee.equals("") && !fee.equals(udcardrivelog.getFEE())) {
            udcardrivelog.setFEE(fee);
        }
        if (iscomis) {
            udcardrivelog.setCOMISORNO("已提交");
        }


        return udcardrivelog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    wonumText.setText(option.getName());
                    break;
            }
        }
    }

}
