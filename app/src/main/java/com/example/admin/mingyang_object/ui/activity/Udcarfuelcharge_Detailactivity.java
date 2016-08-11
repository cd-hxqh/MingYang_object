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
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Udcardrivelog;
import com.example.admin.mingyang_object.model.Udcarfuelcharge;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.model.GreaseCard;
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
 * 加油记录详情
 */
public class Udcarfuelcharge_Detailactivity extends BaseActivity {
    private static String TAG = "Udcarfuelcharge_Detailactivity";

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
     * 车辆信息*
     */
    private TextView carfuelchargenumText; //编号

    private TextView descriptionText; //描述

    private TextView licensenumText; //车牌号

    private TextView carnameText; //车辆名称

    private TextView driverid1Text; //司机

    private TextView drivernameText; //司机名称

    private TextView respnameText; //责任人

    private TextView resp_nameText;//责任人名称

    private TextView prodescText; //所属项目

    private TextView branchdescText; //所属中心

    /**
     * 录入信息*
     */

    private TextView driveridText; //录入人编号

    private TextView createbyText; //录入人

    private TextView createdateText; //录入日期

    private CheckBox comisornoText; //是否提交

    /**
     * 加油记录详细信息
     */

    private TextView chargedateText; //加油日期
    private EditText number2Text; //上次加油里程表读数
    private EditText number1Text; //本次加油里程表读数
    private TextView number3Text; //里程差
    private TextView carNum; //加油卡编号
    private TextView number4Text; //油品号
    private TextView number5Text; //本次加油量
    private EditText priceText; //单价
    private EditText fuelcostText; //加油费
    private TextView lastfuelconsumptionText; //油耗
    private EditText invoicenumText; //发票号
    private EditText placeText; //加油地点
    private EditText remarkText; //备注

    /**
     * 是否提交*
     */
    private boolean iscomis;
    private Udcarfuelcharge udcarfuelcharge;

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
    private ArrayList<String> mCarNums = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udcarfuelcharge_details);
        geiIntentData();
        findViewById();
        initView();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        getGreaseCard();
    }

    private void geiIntentData() {
        udcarfuelcharge = (Udcarfuelcharge) getIntent().getSerializableExtra("udcarfuelcharge");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        scrollview = (ScrollView) findViewById(R.id.scrollview_id);
        carfuelchargenumText = (TextView) findViewById(R.id.carfuelchargenum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        licensenumText = (TextView) findViewById(R.id.licensenum_text_id);
        carnameText = (TextView) findViewById(R.id.carname_text_id);
        driverid1Text = (TextView) findViewById(R.id.driverid1_text_id);
        drivernameText = (TextView) findViewById(R.id.driverid_name_id);
        respnameText = (TextView) findViewById(R.id.respons_text_id);
        resp_nameText = (TextView) findViewById(R.id.respname_text_id);
        prodescText = (TextView) findViewById(R.id.prodesc_text_id);
        branchdescText = (TextView) findViewById(R.id.branchdesc_text_id);

        driveridText = (TextView) findViewById(R.id.driverid_text_id);
        createbyText = (TextView) findViewById(R.id.createby_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        comisornoText = (CheckBox) findViewById(R.id.comisorno_text_id);

        chargedateText = (TextView) findViewById(R.id.chargedate_text_id);
        number2Text = (EditText) findViewById(R.id.number2_text_id);
        number1Text = (EditText) findViewById(R.id.number1_text_id);
        number3Text = (TextView) findViewById(R.id.number3_text_id);
        number4Text = (TextView) findViewById(R.id.number4_text_id);
        carNum = (TextView) findViewById(R.id.carnum_id);
        number5Text = (TextView) findViewById(R.id.number5_text_id);
        priceText = (EditText) findViewById(R.id.price_text_id);
        fuelcostText = (EditText) findViewById(R.id.fuelcost_text_id);
        lastfuelconsumptionText = (TextView) findViewById(R.id.lastfuelconsumption_text_id);
        invoicenumText = (EditText) findViewById(R.id.invoicenum_text_id);
        placeText = (EditText) findViewById(R.id.place_text_id);
        remarkText = (EditText) findViewById(R.id.remark_text_id);


        operationLinearLayout = (LinearLayout) findViewById(R.id.button_layout);
        saveButton = (Button) findViewById(R.id.work_save);
        cancelButton = (Button) findViewById(R.id.work_cancel);

        if (udcarfuelcharge != null) {
            carfuelchargenumText.setText(udcarfuelcharge.getCARFUELCHARGENUM());
            descriptionText.setText(udcarfuelcharge.getDESCRIPTION());
            licensenumText.setText(udcarfuelcharge.getLICENSENUM());
            carnameText.setText(udcarfuelcharge.getVEHICLENAME());
            driverid1Text.setText(udcarfuelcharge.getDRIVERID1());
            drivernameText.setText(udcarfuelcharge.getDRIVERNAME());
            respnameText.setText(udcarfuelcharge.getRESPONSID());
            resp_nameText.setText(udcarfuelcharge.getRESPNAME());
            prodescText.setText(udcarfuelcharge.getPRODESC());
            branchdescText.setText(udcarfuelcharge.getBRACHDESC());

            driveridText.setText(udcarfuelcharge.getDRIVERID());
            createbyText.setText(udcarfuelcharge.getCREATEBY());
            createdateText.setText(udcarfuelcharge.getCREATEDATE());
            if (udcarfuelcharge.getCOMISORNO() == null || udcarfuelcharge.getCOMISORNO().equals("")) {
                comisornoText.setChecked(false);
            } else {
                comisornoText.setChecked(true);
            }


            chargedateText.setText(udcarfuelcharge.getCHARGEDATE());
            number2Text.setText(udcarfuelcharge.getNUMBER2());
            number1Text.setText(udcarfuelcharge.getNUMBER1());
            number3Text.setText(udcarfuelcharge.getNUMBER3());
            number4Text.setText(udcarfuelcharge.getNUMBER4());
            number5Text.setText(udcarfuelcharge.getNUMBER5());
            priceText.setText(udcarfuelcharge.getPRICE());
            fuelcostText.setText(udcarfuelcharge.getFUELCOST());
            lastfuelconsumptionText.setText(udcarfuelcharge.getLASTFUELCONSUMPTION());
            invoicenumText.setText(udcarfuelcharge.getINVOICENUM());
            placeText.setText(udcarfuelcharge.getPLACE());
            remarkText.setText(udcarfuelcharge.getREMARK());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udcarfuelcharge_detail_text));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        operationLinearLayout.setVisibility(View.GONE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        chargedateText.setOnClickListener(new DateTimeOnClickListener(chargedateText));
        number4Text.setOnClickListener(number4TextOnClickListener);
        isEdit(isEdit);
        comisornoText.setOnCheckedChangeListener(comisornoTextOnCheckedChangeListener);
        saveButton.setOnClickListener(saveButtonOnClickListener);
        carNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carNumNormalListDialog();
            }
        });
    }

    //查询可用的加油卡号
    private void getGreaseCard(){
       String url=HttpManager.getGreaseCard("",1,200);
        HttpManager.getData(this,url,new HttpRequestHandler<Results>(){
            @Override
            public void onSuccess(Results results) {

                ArrayList<GreaseCard> item = JsonUtils.parsingGreaseCard(Udcarfuelcharge_Detailactivity.this, results.getResultlist());

                if (item == null || item.isEmpty()) {

                } else {
                        for (int i=0;i<item.size();i++)
                        {
                            Log.e("加油卡台账","加油卡编号"+item.get(i).getCARNUM());

                            mCarNums.add(item.get(i).getCARNUM());
                        }
                }
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {



            }

            @Override
            public void onFailure(String error) {

            }
        });

    }
    private CompoundButton.OnCheckedChangeListener comisornoTextOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            iscomis = isChecked;
        }
    };

    //时间选择监听
    private class DateTimeOnClickListener implements View.OnClickListener {
        TextView textView;

        private DateTimeOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View view) {
            new DateSelect(Udcarfuelcharge_Detailactivity.this, textView).showDialog();
        }
    }

    private View.OnClickListener number4TextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
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


    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udcarfuelcharge_Detailactivity.this).inflate(
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
            Intent intent = new Intent(Udcarfuelcharge_Detailactivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "UDCARFUELCHARGE");
            intent.putExtra("ownerid", udcarfuelcharge.getUDCARFUELCHARGEID());
            startActivityForResult(intent, 0);
        }

    };


    private View.OnClickListener editLinearLayouutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();

            if (udcarfuelcharge.getCOMISORNO() != null && udcarfuelcharge.getCOMISORNO().equals("已提交")) {
                MessageUtils.showMiddleToast(Udcarfuelcharge_Detailactivity.this, "该记录状态已提交,不可编辑");
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
        //是否提交
        comisornoText.setEnabled(isshow);

        //加油日期
        chargedateText.setEnabled(isshow);
        //上次加油里程表读数
        number2Text.setEnabled(isshow);
        //本次加油里程表读数
        number1Text.setEnabled(isshow);
        //油品号
        number4Text.setEnabled(isshow);
        //单价
        priceText.setEnabled(isshow);
        //加油费
        fuelcostText.setEnabled(isshow);
        //发票号
        invoicenumText.setEnabled(isshow);
        //加油地点
        placeText.setEnabled(isshow);
        //备注
        remarkText.setEnabled(isshow);
    }


    private void NormalListDialog() {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();
        types = getResources().getStringArray(R.array.number4_array);
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udcarfuelcharge_Detailactivity.this, mMenuItems);
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
    private void carNumNormalListDialog() {

        mMenuItems = new ArrayList<>();

        for (int i = 0; i < mCarNums.size(); i++) {
            mMenuItems.add(new DialogMenuItem(mCarNums.get(i), 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udcarfuelcharge_Detailactivity.this, mMenuItems);
        dialog.title("请选择加油卡编号")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                carNum.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });

    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.udcarfuelchargeToJson(capsulation(udcarfuelcharge));

        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(Udcarfuelcharge_Detailactivity.this,
                        finalUpdataInfo, "UDCARFUELCHARGE", "CARFUELCHARGENUM", udcarfuelcharge.getCARFUELCHARGENUM(), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    MessageUtils.showMiddleToast(Udcarfuelcharge_Detailactivity.this, "更新失败");
                } else if (workResult.errorMsg.equals("成功")) {
                    MessageUtils.showMiddleToast(Udcarfuelcharge_Detailactivity.this, "行驶记录" + workResult.wonum + "更新成功");
                    setResult(100);
                    finish();
                } else {
                    MessageUtils.showMiddleToast(Udcarfuelcharge_Detailactivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }
        }.execute();

    }


    /**
     * 封装需要上传的数据*
     */
    private Udcarfuelcharge capsulation(Udcarfuelcharge udcarfuelcharge) {

        String chargedate = chargedateText.getText().toString(); //加油日期
        String number2 = number2Text.getText().toString(); //上次加油里程表读数
        String number1 = number1Text.getText().toString(); //本次加油里程表读数
        String number4 = number4Text.getText().toString(); //油品号
        String price = priceText.getText().toString(); //单价
        String fuelcost = fuelcostText.getText().toString(); //加油费
        String invoicenum = invoicenumText.getText().toString(); //发票号
        String place = placeText.getText().toString(); //加油地点
        String remark = remarkText.getText().toString(); //备注
        if (!chargedate.equals("") && !chargedate.equals(udcarfuelcharge.getCHARGEDATE())) {
            udcarfuelcharge.setCHARGEDATE(chargedate);
        }
        if (!number2.equals("") && !number2.equals(udcarfuelcharge.getNUMBER2())) {
            udcarfuelcharge.setNUMBER2(number2);
        }
        if (!number1.equals("") && !number1.equals(udcarfuelcharge.getNUMBER1())) {
            udcarfuelcharge.setNUMBER1(number1);
        }
        if (!number4.equals("") && !number4.equals(udcarfuelcharge.getNUMBER4())) {
            udcarfuelcharge.setNUMBER4(number4);
        }
        if (!price.equals("") && !price.equals(udcarfuelcharge.getPRICE())) {
            udcarfuelcharge.setPRICE(price);
        }
        if (!fuelcost.equals("") && !fuelcost.equals(udcarfuelcharge.getFUELCOST())) {
            udcarfuelcharge.setFUELCOST(fuelcost);
        }
        if (!invoicenum.equals("") && !invoicenum.equals(udcarfuelcharge.getINVOICENUM())) {
            udcarfuelcharge.setINVOICENUM(invoicenum);
        }
        if (!place.equals("") && !place.equals(udcarfuelcharge.getPLACE())) {
            udcarfuelcharge.setPLACE(place);
        }
        if (!remark.equals("") && !remark.equals(udcarfuelcharge.getREMARK())) {
            udcarfuelcharge.setREMARK(remark);
        }
        if (iscomis) {
            udcarfuelcharge.setCOMISORNO("已提交");
        }


        return udcarfuelcharge;
    }


}
