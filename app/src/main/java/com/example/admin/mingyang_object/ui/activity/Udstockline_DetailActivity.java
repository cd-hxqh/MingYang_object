package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Udstock;
import com.example.admin.mingyang_object.model.Udstockline;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;


/**
 * 盘点明细行
 */
public class Udstockline_DetailActivity extends BaseActivity {
    private static String TAG = "Udstockline_DetailActivity";

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
    private TextView zpdrowText; //行项目

    private TextView matnrText; //物料编码

    private TextView maktxText; //物料描述

    private TextView msehlText; //单位

//    private LinearLayout numexistLayout;
//    private TextView numexistText;//账存数量

    private EditText actualqtyText; //实盘数量

//    private LinearLayout diffqtyLayout;
//    private TextView diffqtyText; //差异数量

    private EditText diffreasonText; //差异原因

    private LinearLayout buttonlayout;
    private Button cancel;
    private Button save;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private Udstockline udstockline;
    private Udstock udstock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udstockline_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udstockline = (Udstockline) getIntent().getSerializableExtra("udstockline");
        udstock = (Udstock) getIntent().getSerializableExtra("udstock");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        zpdrowText = (TextView) findViewById(R.id.zpdrow_text_id);
        matnrText = (TextView) findViewById(R.id.matnr_text_id);
        maktxText = (TextView) findViewById(R.id.maktx_text_id);
        msehlText = (TextView) findViewById(R.id.msehl_text_id);
//        numexistLayout = (LinearLayout) findViewById(R.id.numexist_layout);
//        numexistText = (TextView) findViewById(R.id.numexist_text_id);
        actualqtyText = (EditText) findViewById(R.id.actualqty_text_id);
//        diffqtyLayout = (LinearLayout) findViewById(R.id.diffqty_layout);
//        diffqtyText = (TextView) findViewById(R.id.diffqty_text_id);
        diffreasonText = (EditText) findViewById(R.id.diffreason_text_id);

        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);

        if (udstockline != null) {
            zpdrowText.setText(udstockline.getZPDROW());
            matnrText.setText(udstockline.getMATNR());
            maktxText.setText(udstockline.getMAKTX());
            msehlText.setText(udstockline.getMSEHL());
//            numexistText.setText(udstockline.getNUMEXIST());
            actualqtyText.setText(udstockline.getACTUALQTY() + "");
//            diffqtyText.setText(udstockline.getDIFFQTY() + "");
            diffreasonText.setText(udstockline.getDIFFREASON());

//            if (udstock.getISCLOSE().equals("Y")){
//                numexistLayout.setVisibility(View.GONE);
//                diffqtyLayout.setVisibility(View.GONE);
//            }
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udstockline_text));

//        actualqtyText.addTextChangedListener(new EditChangedListener());
        save.setText("上传");
        if (udstock.getINVOWNER()==null||!udstock.getINVOWNER().equals(AccountUtils.getpersonId(Udstockline_DetailActivity.this))){
            actualqtyText.setEnabled(false);
            diffreasonText.setEnabled(false);
        }

        cancel.setOnClickListener(backImageViewOnClickListener);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDataInfo();
            }
        });
    }

//    class EditChangedListener implements TextWatcher {
//        private CharSequence temp;//监听前的文本
//        private int editStart;//光标开始位置
//        private int editEnd;//光标结束位置
//        private final int charMaxNum = 10;
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            temp = s;
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            if (!s.equals("0") && !s.equals("") && count != 0) {
//                diffqtyText.setText((int)(Integer.parseInt(s.toString()) - Double.parseDouble(numexistText.getText().toString())) + "");
//            } else {
//                diffqtyText.setText((int)(-Double.parseDouble(numexistText.getText().toString())) + "");
//            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
////            if (DEBUG)
////                Log.i(TAG, "输入文字后的状态");
////            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
////            editStart = mEditTextMsg.getSelectionStart();
////            editEnd = mEditTextMsg.getSelectionEnd();
////            if (temp.length() > charMaxNum) {
////                Toast.makeText(getApplicationContext(), "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();
////                s.delete(editStart - 1, editEnd);
////                int tempSelection = editStart;
////                mEditTextMsg.setText(s);
////                mEditTextMsg.setSelection(tempSelection);
////            }
//
//        }
//    }

    ;

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        if (udstockline.getACTUALQTY().equals(actualqtyText.getText().toString())
//                && udstockline.getDIFFQTY() == (diffqtyText.getText().toString().equals("") ? 0 : Integer.parseInt(diffqtyText.getText().toString()))
                && udstockline.getDIFFREASON().equals(diffreasonText.getText().toString())) {
//            finish();
        } else {
            final NormalDialog dialog = new NormalDialog(Udstockline_DetailActivity.this);
            dialog.content("确定上传盘点行吗?")//
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
        updataInfo = JsonUtils.UdstockToJson(udstock, getUdstockline());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(Udstockline_DetailActivity.this, finalUpdataInfo,
                        "UDSTOCK", "STOCKNUM", udstock.getSTOCKNUM(), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult==null||workResult.errorMsg == null) {
                    Toast.makeText(Udstockline_DetailActivity.this, "修改库存盘点行失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udstockline_DetailActivity.this, "修改库存盘点行成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Udstockline_DetailActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }
        }.execute();
////        }

    }

    private ArrayList<Udstockline> getUdstockline() {
        ArrayList<Udstockline> udstocklines = new ArrayList<>();
        Udstockline udstockline = this.udstockline;
        udstockline.setACTUALQTY(actualqtyText.getText().toString());
//        udstockline.setDIFFQTY(diffqtyText.getText().toString().equals("") ? 0 : Integer.parseInt(diffqtyText.getText().toString()));
        udstockline.setDIFFREASON(diffreasonText.getText().toString());
        udstockline.setTYPE("update");
        udstocklines.add(udstockline);
        return udstocklines;
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
