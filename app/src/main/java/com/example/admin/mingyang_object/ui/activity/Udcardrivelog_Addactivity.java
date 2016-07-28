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
import com.example.admin.mingyang_object.model.Udcardrivelog;
import com.example.admin.mingyang_object.model.Udcarfuelcharge;
import com.example.admin.mingyang_object.model.Udcarmainlog;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.GetDateAndTime;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.utils.TimeSelect;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * 行驶记录新增
 */
public class Udcardrivelog_Addactivity extends BaseActivity {
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

    private TextView driverText;//司机

    private TextView prodescText;//所属项目

    private TextView branchdescText;//所属中心

    /**
     * 录入信息
     */
    private TextView driveridText;//创建人

    private TextView createdateText;//创建日期

    /**
     * 出车信息*
     */

    private TextView startdateText; //出车日期

    private TextView starttimeText; //出车时间

    private EditText departureText; //出发地

    private EditText destinationText; //目的地

    private EditText goreasonText; //出车事由

    private TextView wonumText;//业务单号

    private TextView wtypedescText;//任务类型

    private TextView startnumberText;//起始里程

    private EditText endnumberText; //结束里程

    private EditText standardfuelconsumptionText; //标准油耗


    private EditText feeText; //路桥费

    private CheckBox comisornoText; //是否提交


    /**
     * 是否提交*
     */
    private boolean istijiao = false;


    /**
     * 保存按钮*
     */
    private Button saveButton;
    /**
     * 取消保存*
     */
    private Button canleButton;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udcardrivelog_add);
        findViewById();
        initView();

    }


    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);
        driverText = (TextView) findViewById(R.id.driverid1_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);

        driveridText = (TextView) findViewById(R.id.driverid_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);

        startdateText = (TextView) findViewById(R.id.startdate_text_id);
        starttimeText = (TextView) findViewById(R.id.starttime_text_id);
        departureText = (EditText) findViewById(R.id.departure_text_id);
        destinationText = (EditText) findViewById(R.id.destination_text_id);
        goreasonText = (EditText) findViewById(R.id.goreason_text_id);
        wonumText = (TextView) findViewById(R.id.wonum_text_id);
        wtypedescText = (TextView) findViewById(R.id.wtypedesc_text_id);
        startnumberText = (TextView) findViewById(R.id.startnumber_text_id);
        endnumberText = (EditText) findViewById(R.id.endnumber_text_id);
        standardfuelconsumptionText = (EditText) findViewById(R.id.standardfuelconsumption_text_id);

        feeText = (EditText) findViewById(R.id.fee_text_id);
        comisornoText = (CheckBox) findViewById(R.id.comisorno_text_id);
        saveButton = (Button) findViewById(R.id.work_save);
        canleButton = (Button) findViewById(R.id.work_cancel);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.xcjlxj_text));

        startdateText.setText(GetDateAndTime.GetDate());
        driveridText.setText(AccountUtils.getdisplayName(Udcardrivelog_Addactivity.this));
        createdateText.setText(GetDateAndTime.GetDate());
        licensenumText.setOnClickListener(licensenumTextOnClickListener);
        startdateText.setOnClickListener(startdateTextOnClickListener);
        starttimeText.setOnClickListener(starttimeTextOnClickListener);

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
            MessageUtils.showMiddleToast(Udcardrivelog_Addactivity.this, "取消新增维修单");
            finish();
        }
    };

    private View.OnClickListener licensenumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Udcardrivelog_Addactivity.this, OptionActivity.class);
            intent.putExtra("optiontype", Constants.UDVEHICLE);
            startActivityForResult(intent, 0);
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
        if (textView == wonumText) {
            types = getResources().getStringArray(R.array.udcardrivelog_wonum_array);
        }
//        else if (textView == culevel) {
//            types = getResources().getStringArray(R.array.culevel_array);
//        }
        for (String type : types) {
            mMenuItems.add(new DialogMenuItem(type, 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udcardrivelog_Addactivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

//                textView.setText(mMenuItems.get(position).mOperName);
                Intent intent = new Intent(Udcardrivelog_Addactivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.WONUMCODE);
                intent.putExtra("wonumtype", mMenuItems.get(position).mOperName);
                startActivityForResult(intent, Constants.WONUMCODE);
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (resultCode) {
                case Constants.UDVEHICLE:
                    option = (Option) data.getSerializableExtra("option");
                    licensenumText.setText(option.getName());
                    carnameText.setText(option.getDesc());
                    driverText.setText(option.getValue4());
                    prodescText.setText(option.getValue2());
                    branchdescText.setText(option.getValue3());
                    startnumberText.setText(option.getValue5());
                    break;
                case Constants.WONUMCODE:
                    option = (Option) data.getSerializableExtra("option");
                    break;
                default:
                    break;
            }
        }
    }


    private View.OnClickListener startdateTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DateSelect(Udcardrivelog_Addactivity.this, startdateText).showDialog();

        }
    };
    private View.OnClickListener starttimeTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new TimeSelect(Udcardrivelog_Addactivity.this, starttimeText).showDialog();

        }
    };


    /**
     * 封装需要上传的数据*
     */
    private Udcardrivelog capsulation() {
        String licensenum = licensenumText.getText().toString(); //车牌号
        String driverid = AccountUtils.getpersonId(Udcardrivelog_Addactivity.this); //录入人
        String createdate = GetDateAndTime.GetDate(); //录入时间

        String startdate = startdateText.getText().toString(); //出车日期
        String starttime = starttimeText.getText().toString(); //出车时间
        String departure = departureText.getText().toString(); //出发地
        String destination = destinationText.getText().toString(); //目的地
        String goreason = goreasonText.getText().toString(); //出车事由
        String endnumber = endnumberText.getText().toString(); //结束里程
        String standardfuelconsumption = standardfuelconsumptionText.getText().toString(); //标准油耗
        String fee = feeText.getText().toString(); //路桥费
        Udcardrivelog udcardrivelog = new Udcardrivelog();
        udcardrivelog.setLICENSENUM(licensenum);
        udcardrivelog.setDRIVERID(driverid);
        udcardrivelog.setCREATEDATE(createdate);

        udcardrivelog.setSTARTDATE(startdate);
        udcardrivelog.setSTARTTIME(starttime);
        udcardrivelog.setDEPARTURE(departure);
        udcardrivelog.setDESTINATION(destination);

        udcardrivelog.setGOREASON(goreason);
        udcardrivelog.setENDNUMBER(endnumber);
        udcardrivelog.setSTANDARDFUELCONSUMPTION(standardfuelconsumption);
        udcardrivelog.setFEE(fee);
        if (istijiao) {
            udcardrivelog.setCOMISORNO("已提交");
        }


        return udcardrivelog;
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.udcardrivelogToJson(capsulation());

        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.InsertWO(Udcardrivelog_Addactivity.this,
                        finalUpdataInfo, "UDCARDRIVELOG", "CARDRIVELOGNUM", AccountUtils.getpersonId(Udcardrivelog_Addactivity.this), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    MessageUtils.showMiddleToast(Udcardrivelog_Addactivity.this, "新增失败");
                } else if (workResult.errorMsg.equals("成功")) {
                    MessageUtils.showMiddleToast(Udcardrivelog_Addactivity.this, "行驶记录" + workResult.wonum + "新增成功");
                    finish();
                } else {
                    MessageUtils.showMiddleToast(Udcardrivelog_Addactivity.this, workResult.errorMsg);
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
        if (startdate.equals("")) {//出车日期
            startdateText.setError("必填!");
            startdateText.requestFocus();
            return false;
        }

        String starttime = starttimeText.getText().toString(); //出车时间
        if (starttime.equals("")) {//出车时间
            starttimeText.setError("必填!");
            starttimeText.requestFocus();
            return false;
        }
        String departure = departureText.getText().toString(); //出发地
        if (departure.equals("")) {//维修总额
            departureText.setError("必填!");
            return false;
        }

        String goreason = goreasonText.getText().toString(); //出车事由
        if (goreason.equals("")) { //出车事由
            goreasonText.setError("必填!");
            goreasonText.requestFocus();
            return false;
        }

        String endnumber = endnumberText.getText().toString(); //结束里程
        if (endnumber.equals("")) { //出车事由
            endnumberText.setError("必填!");
            return false;
        }

        return true;
    }


}
