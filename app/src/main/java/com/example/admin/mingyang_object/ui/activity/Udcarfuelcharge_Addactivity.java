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

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udcarfuelcharge;
import com.example.admin.mingyang_object.model.Udcarmainlog;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.DateSelect;
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
 * 加油记录新增
 */
public class Udcarfuelcharge_Addactivity extends BaseActivity {
    private static String TAG = "Udcarfuelcharge_Addactivity";

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

    private TextView chargedateText; //加油日期

    private EditText number2Text; //上次加油里程表读数

    private EditText number1Text; //本次加油里程表读数

    private TextView number4Text; //油品号

    private EditText priceText; //单价

    private EditText fuelcostText; //加油费

    private EditText invoicenumText; //发票号


    private EditText placeText; //加油费

    private CheckBox comisornoText; //是否提交

    private EditText remarkText; //备注


    /**
     * 是否提交*
     */
    private boolean istijiao=false;


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
        setContentView(R.layout.activity_udcarfuelcharge_add);
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


        chargedateText = (TextView) findViewById(R.id.chargedate_text_id);
        number2Text = (EditText) findViewById(R.id.number2_text_id);
        number1Text = (EditText) findViewById(R.id.number1_text_id);
        number4Text = (TextView) findViewById(R.id.number4_text_id);
        priceText = (EditText) findViewById(R.id.price_text_id);
        fuelcostText = (EditText) findViewById(R.id.fuelcost_text_id);
        invoicenumText = (EditText) findViewById(R.id.invoicenum_text_id);

        placeText = (EditText) findViewById(R.id.place_text_id);
        comisornoText = (CheckBox) findViewById(R.id.comisorno_text_id);
        remarkText = (EditText) findViewById(R.id.remark_text_id);
        saveButton = (Button) findViewById(R.id.work_save);
        canleButton = (Button) findViewById(R.id.work_cancel);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.jyjlxj_text));

        licensenumText.setOnClickListener(licensenumTextOnClickListener);
        chargedateText.setOnClickListener(new DateTimeOnClickListener(chargedateText));

        number4Text.setOnClickListener(number4TextOnClickListener);
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
            MessageUtils.showMiddleToast(Udcarfuelcharge_Addactivity.this, "取消新增维修单");
            finish();
        }
    };

    private View.OnClickListener licensenumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Udcarfuelcharge_Addactivity.this, OptionActivity.class);
            intent.putExtra("optiontype", Constants.UDVEHICLE);
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener number4TextOnClickListener = new View.OnClickListener() {
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
            new DateSelect(Udcarfuelcharge_Addactivity.this, textView).showDialog();
        }
    }


    private void NormalListDialog() {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();
        types = getResources().getStringArray(R.array.number4_array);
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udcarfuelcharge_Addactivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                number4Text.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });

    }


    /**
     * 封装需要上传的数据*
     */
    private Udcarfuelcharge capsulation() {
        String licensenum = licensenumText.getText().toString(); //车牌号
        String driverid = AccountUtils.getpersonId(Udcarfuelcharge_Addactivity.this); //录入人
        String createdate = GetDateAndTime.GetDate(); //录入时间

        String chargedate = chargedateText.getText().toString(); //加油日期
        String number2 = number2Text.getText().toString(); //上次加油里程表读数
        String number1 = number1Text.getText().toString(); //本次加油里程表读数
        String number4 = number4Text.getText().toString(); //油品号
        String price = priceText.getText().toString(); //单价
        String fuelcost = fuelcostText.getText().toString(); //加油费
        String invoicenum = invoicenumText.getText().toString(); //发票号
        String place = placeText.getText().toString(); //加油地点
        String remark = remarkText.getText().toString(); //备注
        Udcarfuelcharge udcarfuelcharge = new Udcarfuelcharge();
        udcarfuelcharge.setLICENSENUM(licensenum);
        udcarfuelcharge.setDRIVERID(driverid);
        udcarfuelcharge.setCREATEDATE(createdate);

        udcarfuelcharge.setCHARGEDATE(chargedate);
        udcarfuelcharge.setNUMBER2(number2);
        udcarfuelcharge.setNUMBER1(number1);
        udcarfuelcharge.setNUMBER4(number4);

        udcarfuelcharge.setPRICE(price);
        udcarfuelcharge.setFUELCOST(fuelcost);
        udcarfuelcharge.setINVOICENUM(invoicenum);
        udcarfuelcharge.setPLACE(place);
        if (istijiao) {
            udcarfuelcharge.setCOMISORNO("已提交");
        }

        udcarfuelcharge.setREMARK(remark);

        return udcarfuelcharge;
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.udcarfuelchargeToJson(capsulation());

        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(
                        finalUpdataInfo, "UDCARFUELCHARGE", "CARFUELCHARGENUM", AccountUtils.getpersonId(Udcarfuelcharge_Addactivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    MessageUtils.showMiddleToast(Udcarfuelcharge_Addactivity.this, "新增失败");
                } else if (workResult.errorMsg.equals("成功")) {
                    MessageUtils.showMiddleToast(Udcarfuelcharge_Addactivity.this, "加油记录" + workResult.wonum + "新增成功");
//                    finish();
                } else {
                    MessageUtils.showMiddleToast(Udcarfuelcharge_Addactivity.this, workResult.errorMsg);
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
        String chargedate = chargedateText.getText().toString();
        if (chargedate.equals("")) {//加油日期
            chargedateText.setError("必填!");
            chargedateText.requestFocus();
            return false;
        }

        String price = priceText.getText().toString(); //单价
        if (price.equals("")) {//单价
            priceText.setError("必填!");
            priceText.requestFocus();
            return false;
        }
        String fuelcost = fuelcostText.getText().toString(); //加油费
        if (fuelcost.equals("")) {//维修总额
            fuelcostText.setError("必填!");
            return false;
        }

        String place = placeText.getText().toString(); //加油地点
        if (place.equals("")) { //加油地点
            placeText.setError("必填!");
            placeText.requestFocus();
            return false;
        }

        return true;
    }


}
