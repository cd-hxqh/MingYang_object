package com.example.admin.mingyang_object.ui.activity;

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
import com.example.admin.mingyang_object.ui.activity.udpro.Udprorunlog_Line1Activity;
import com.example.admin.mingyang_object.ui.activity.udpro.Udprorunlog_Line2Activity;
import com.example.admin.mingyang_object.ui.activity.udpro.Udprorunlog_Line3Activity;
import com.example.admin.mingyang_object.ui.activity.udpro.Udprorunlog_Line4Activity;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * 运行记录详情
 */
public class Udrunlogr_DetailActivity extends BaseActivity {
    private static String TAG = "Udrunlogr_DetailActivity";

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


    private Button cancel;
    private Button save;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    private Udprorunlog udprorunlog;

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
    /**工装管理**/
    private LinearLayout gzglLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udrunlogr_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);

        if (udprorunlog != null) {
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udprorunlog_detail_title));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


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

        View contentView = LayoutInflater.from(Udrunlogr_DetailActivity.this).inflate(
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




    }


    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;
        int optiontype;

        private LayoutOnClickListener(int requestCode, int optiontype) {
            this.requestCode = requestCode;
            this.optiontype = optiontype;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udrunlogr_DetailActivity.this, OptionActivity.class);
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
//        if (textView == yearText) {
//            types = getResources().getStringArray(R.array.year_array);
//        } else if (textView == monthText) {
//            types = getResources().getStringArray(R.array.month_array);
//        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Udrunlogr_DetailActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(mMenuItems.get(position).mOperName);
//                if (!yearText.getText().toString().equals("") && !monthText.getText().equals("")) {
//                    descriptionText.setText(yearText.getText().toString() + "年" + monthText.getText() + "月" + pronumDesc);
//                }
                dialog.dismiss();
            }
        });
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Udrunlogr_DetailActivity.this);
        dialog.content("确定修改日报吗?")//
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
        return udprorunlog;
    }

    private boolean isOK(){
//        if (pronumText.getText().toString().equals("")){
//            Toast.makeText(Udrunlogr_DetailActivity.this,"项目编号不能为空",Toast.LENGTH_SHORT).show();
//            return false;
//        }else if (contractsText.getText().toString().equals("")){
//            Toast.makeText(Udrunlogr_DetailActivity.this,"现场联系人不能为空",Toast.LENGTH_SHORT).show();
//            return false;
//        }else if (yearText.getText().toString().equals("")){
//            Toast.makeText(Udrunlogr_DetailActivity.this,"年份不能为空",Toast.LENGTH_SHORT).show();
//            return false;
//        }else if (monthText.getText().toString().equals("")){
//            Toast.makeText(Udrunlogr_DetailActivity.this,"月份不能为空",Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    private ArrayList<UdprorunlogLine1> getUdprorunlogLine1() {
        ArrayList<UdprorunlogLine1> udprorunlogLine1s = new ArrayList<>();
//        for (int i = 0; i < UdprorunlogLine1List.size(); i++) {
//            if (UdprorunlogLine1List.get(i).TYPE != null) {
//                udprorunlogLine1s.add(UdprorunlogLine1List.get(i));
//            }
//        }
        return udprorunlogLine1s;
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
//        updataInfo = JsonUtils.UdprorunlogToJson(getUdprorunlog(), getUdprorunlogLine1(),getUdprorunlogLine2(),getUdprorunlogLine3(),getUdprorunlogLine4());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(Udrunlogr_DetailActivity.this,finalUpdataInfo,
                        "UDPRORUNLOG", "PRORUNLOGNUM", udprorunlog.PRORUNLOGNUM, Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(Udrunlogr_DetailActivity.this, "修改日报失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udrunlogr_DetailActivity.this, "修改日报成功", Toast.LENGTH_SHORT).show();
                    setResult(100);
                    finish();
                } else {
                    Toast.makeText(Udrunlogr_DetailActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
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
//                    pronumText.setText(option.getName());
//                    prdescText.setText(option.getDesc());
//                    pronumDesc = option.getName() + option.getDesc();
//                    branchText.setText(option.getValue1());
//                    udprorescText.setText(option.getValue4());
//                    contractsText.setText(option.getValue4());
//                    udprorunlog.CONTRACTS = option.getValue2();
//                    udprorunlog.UDPRORESC = option.getValue4();
//                    udprorunlog.RESPONSID = option.getValue2();
//                    prostageText.setText(option.getValue3());
//                    if (!monthText.getText().equals("")&&!yearText.getText().equals("")) {
//                        descriptionText.setText(yearText.getText().toString() + "年" + monthText.getText().toString()
//                                + "月" + pronumDesc);
//                    }else {
//                        descriptionText.setText(pronumDesc);
//                    }
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    break;
                case 1000:
//                    UdprorunlogLine1List = (ArrayList<UdprorunlogLine1>) data.getSerializableExtra("UdprorunlogLine1List");
                    break;
            }
        }
    }
}
