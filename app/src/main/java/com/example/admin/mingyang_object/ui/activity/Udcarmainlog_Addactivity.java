package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udcarmainlog;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.Wpmaterial;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.utils.GetDateAndTime;
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
 * 维修记录新增
 */
public class Udcarmainlog_Addactivity extends BaseActivity {
    private static String TAG = "Udcarmainlog_Addactivity";

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

    /**
     * 基本信息*
     */

    private TextView licensenumText; //车牌号

    private TextView carnameText; //车辆名称


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
    private boolean istijiao;


    /**
     * 保存按钮*
     */
    private Button saveButton;
    /**
     * 取消保存*
     */
    private Button canleButton;


    private Udcarmainlog udcarmainlog;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udcarmainlog_add);
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }


    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);


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
        saveButton = (Button) findViewById(R.id.work_save);
        canleButton = (Button) findViewById(R.id.work_cancel);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.wxjlxj_text));

        licensenumText.setOnClickListener(licensenumTextOnClickListener);
        startdateText.setOnClickListener(new DateTimeOnClickListener(startdateText));
        enddateText.setOnClickListener(new DateTimeOnClickListener(enddateText));

        servicetypeText.setOnClickListener(servicetypeTextOnClickListener);
        comisornoText.setOnCheckedChangeListener(comisornoTextOnCheckedChangeListener);


        saveButton.setOnClickListener(saveButtonOnClickListener);
        canleButton.setOnClickListener(canleButtonOnClickListener);
    }


    private CompoundButton.OnCheckedChangeListener comisornoTextOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            istijiao = isChecked;
        }
    };


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    private View.OnClickListener canleButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MessageUtils.showMiddleToast(Udcarmainlog_Addactivity.this, "取消新增维修单");
            finish();
        }
    };

    private View.OnClickListener licensenumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Udcarmainlog_Addactivity.this, OptionActivity.class);
            intent.putExtra("optiontype", Constants.UDVEHICLE);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener servicetypeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };
    private View.OnClickListener saveButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isBiTian()) {
                showProgressDialog("数据提交中...");
                startAsyncTask();
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (resultCode) {
                case Constants.UDVEHICLE:
                    option = (Option) data.getSerializableExtra("option");
                    licensenumText.setText(option.getName());
                    carnameText.setText(option.getDesc());
                    number2Text.setText(option.getValue8());
                    break;

                default:
                    break;
            }
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
            new DateSelect(Udcarmainlog_Addactivity.this, textView).showDialog();
        }
    }


    private void NormalListDialog() {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();
        types = getResources().getStringArray(R.array.servicetype_array);
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udcarmainlog_Addactivity.this, mMenuItems);
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


    /**
     * 封装需要上传的数据*
     */
    private Udcarmainlog capsulation() {
        String licensenum = licensenumText.getText().toString(); //车牌号
        String driverid = AccountUtils.getpersonId(Udcarmainlog_Addactivity.this);
        String createdate = GetDateAndTime.GetDate();

        String startdate = startdateText.getText().toString(); //开始时间
        String enddate = enddateText.getText().toString(); //结束时间
        String price = priceText.getText().toString(); //单价
        String mainnumber = mainnumberText.getText().toString(); //维修数量
        String totalprice = totalpriceText.getText().toString(); //维修总额
        String invoicenum = invoicenumText.getText().toString(); //维修发票号
        String servicetype = servicetypeText.getText().toString(); //维修类型
        String mainplace = mainplaceText.getText().toString(); //维修地点
        String maincontent = maincontentText.getText().toString(); //维修.保养.更换
        String number2 = number2Text.getText().toString(); //上次维修里程
        String number1 = number1Text.getText().toString(); //本次次维修里程


        String remark = remarkText.getText().toString(); //备注
        Udcarmainlog udcarmainlog = new Udcarmainlog();
        udcarmainlog.setLICENSENUM(licensenum);
        udcarmainlog.setDRIVERID(driverid);
        udcarmainlog.setCREATEDATE(createdate);


        udcarmainlog.setSTARTDATE(startdate);
        udcarmainlog.setENDDATE(enddate);
        udcarmainlog.setPRICE(price);
        udcarmainlog.setMAINNUMBER(mainnumber);
        udcarmainlog.setTOTALPRICE(totalprice);
        udcarmainlog.setINVOICENUM(invoicenum);
        if (servicetype.equals("事故维修")) {
            udcarmainlog.setSERVICETYPE("accident");
        } else {
            udcarmainlog.setSERVICETYPE("normal");
        }

        udcarmainlog.setMAINPLACE(mainplace);
        udcarmainlog.setMAINCONTENT(maincontent);
        udcarmainlog.setNUMBER2(number2);
        udcarmainlog.setNUMBER1(number1);
        if (istijiao) {
            udcarmainlog.setCOMISORNO("已提交");
        }

        udcarmainlog.setREMARK(remark);

        return udcarmainlog;
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.udcarmainlogToJson(capsulation());

        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(Udcarmainlog_Addactivity.this,
                        finalUpdataInfo, "UDCARMAINLOG", "MAINLOGNUM", AccountUtils.getpersonId(Udcarmainlog_Addactivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null || workResult.errorMsg == null) {
                    MessageUtils.showMiddleToast(Udcarmainlog_Addactivity.this, "新增失败");
                } else if (workResult.errorMsg.equals("成功")) {
                    MessageUtils.showMiddleToast(Udcarmainlog_Addactivity.this, "维修单" + workResult.wonum + "新增成功");
                    finish();
                } else {
                    MessageUtils.showMiddleToast(Udcarmainlog_Addactivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }
        }.execute();

    }


    /**
     * 验证必填项*
     */
    private boolean isBiTian() {
        String licensenum = licensenumText.getText().toString();
        if (licensenum.equals("")) { //车牌号
            licensenumText.setError("必填!");
            licensenumText.requestFocus();
            return false;
        }
        String startdate = startdateText.getText().toString();
        if (startdate.equals("")) {//维修开始日期
            startdateText.setError("必填!");
            startdateText.requestFocus();
            return false;
        }

        String enddate = enddateText.getText().toString(); //结束时间
        if (enddate.equals("")) {//维修结束时间
            enddateText.setError("必填!");
            enddateText.requestFocus();
            return false;
        }
        String totalprice = totalpriceText.getText().toString(); //维修总额
        if (totalprice.equals("")) {//维修总额
            totalpriceText.setError("必填!");
            return false;
        }

        String servicetype = servicetypeText.getText().toString(); //维修类型
        if (servicetype.equals("")) { //车牌号
            servicetypeText.setError("必填!");
            servicetypeText.requestFocus();
            return false;
        }

        return true;
    }


}
