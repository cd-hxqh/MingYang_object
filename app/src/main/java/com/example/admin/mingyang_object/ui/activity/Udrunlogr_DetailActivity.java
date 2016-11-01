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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udrunliner;
import com.example.admin.mingyang_object.model.Udrunlogr;
import com.example.admin.mingyang_object.model.WebResult;
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
//    private LinearLayout udrunlogrlayout;
    private TextView lognum;//运行日志编号
    private TextView description;//描述
    private TextView branch;//中心编号
    private TextView branchdesc;//中心描述
    private TextView pronum;//项目编号
    private TextView prodesc;//项目描述
    private TextView year;//年
    private TextView month;//月
    private TextView prohead;//负责人
    private TextView name1;//负责人描述
    private TextView creater;//录入人编号
    private TextView createname;//录入人描述
    private TextView createtime;//录入时间

    private Button cancel;
    private Button save;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    private Udrunlogr udrunlogr;
    private ArrayList<Udrunliner> udrunliners = new ArrayList<>();

    private PopupWindow popupWindow;

    /**
     * 工作日志活动子表*
     */
    private LinearLayout udrunlinerLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udrunlogr_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udrunlogr = (Udrunlogr) getIntent().getSerializableExtra("udrunlogr");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);

//        udrunlogrlayout = (LinearLayout) findViewById(R.id.udrunlogr_layout);
        lognum = (TextView) findViewById(R.id.udrunlogr_lognum);
        description = (TextView) findViewById(R.id.udrunlogr_description);
        branch = (TextView) findViewById(R.id.udrunlogr_branch);
        branchdesc = (TextView) findViewById(R.id.udrunlogr_branchdesc);
        pronum = (TextView) findViewById(R.id.udrunlogr_pronum);
        prodesc = (TextView) findViewById(R.id.udrunlogr_prodesc);
        year = (TextView) findViewById(R.id.udrunlogr_year);
        month = (TextView) findViewById(R.id.udrunlogr_month);
        prohead = (TextView) findViewById(R.id.udrunlogr_prohead);
        name1 = (TextView) findViewById(R.id.udrunlogr_name1);
        creater = (TextView) findViewById(R.id.udrunlogr_creater);
        createname = (TextView) findViewById(R.id.udrunlogr_createname);
        createtime = (TextView) findViewById(R.id.udrunlogr_createtime);

        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);

        if (udrunlogr != null) {
            lognum.setText(udrunlogr.LOGNUM);
            description.setText(udrunlogr.DESCRIPTION);
            branch.setText(udrunlogr.BRANCH);
            branchdesc.setText(udrunlogr.BRANCHDESC);
            pronum.setText(udrunlogr.PRONUM);
            prodesc.setText(udrunlogr.PRODESC);
            year.setText(udrunlogr.YEAR);
            month.setText(udrunlogr.MONTH);
            prohead.setText(udrunlogr.PROHEAD);
            name1.setText(udrunlogr.NAME1);
            creater.setText(udrunlogr.CREATER);
            createname.setText(udrunlogr.CREATENAME);
            createtime.setText(udrunlogr.CREATETIME);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udrunlogr_detail_title));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
//        udrunlogrlayout.setVisibility(View.VISIBLE);

        prohead.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));

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
                R.layout.udrunlogr_window, null);


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
        udrunlinerLinearLayout = (LinearLayout) contentView.findViewById(R.id.udrunliner_text_id);
        udrunlinerLinearLayout.setOnClickListener(udrunlinerOnClickListener);
    }

    private View.OnClickListener udrunlinerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udrunlogr_DetailActivity.this, UdrunlinerListActivity.class);
            intent.putExtra("Udrunlogr", getUdprorunlog());
            intent.putExtra("UdrunlinerList", udrunliners);
            startActivityForResult(intent, 1000);
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
        dialog.content("确定修改运行记录吗?")//
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

    private Udrunlogr getUdprorunlog() {
        Udrunlogr udrunlogr = this.udrunlogr;
        udrunlogr.PROHEAD = prohead.getText().toString();
        return udrunlogr;
    }

    private boolean isOK(){
        if (pronum.getText().toString().equals("")){
            Toast.makeText(Udrunlogr_DetailActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (year.getText().toString().equals("")){
            Toast.makeText(Udrunlogr_DetailActivity.this, "请输入年号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (month.getText().toString().equals("")){
            Toast.makeText(Udrunlogr_DetailActivity.this, "请输入月份", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (prohead.getText().toString().equals("")){
            Toast.makeText(Udrunlogr_DetailActivity.this, "请输入负责人", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private ArrayList<Udrunliner> getUdprorunlogLine1() {
        ArrayList<Udrunliner> udprorunlogLine1s = new ArrayList<>();
        for (int i = 0; i < udrunliners.size(); i++) {
            if (udrunliners.get(i).TYPE != null) {
                udprorunlogLine1s.add(udrunliners.get(i));
            }
        }
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
        updataInfo = JsonUtils.UdrunlogrToJson(getUdprorunlog(), getUdprorunlogLine1());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        Log.i(TAG, finalUpdataInfo);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(Udrunlogr_DetailActivity.this, finalUpdataInfo,
                        "UDRUNLOGR", "LOGNUM", udrunlogr.getLOGNUM(), Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult==null||workResult.errorMsg == null) {
                    Toast.makeText(Udrunlogr_DetailActivity.this, "修改运行记录失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Udrunlogr_DetailActivity.this, "修改运行记录成功", Toast.LENGTH_SHORT).show();
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
                    prohead.setText(option.getName());
                    name1.setText(option.getDesc());
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    break;
                case 1000:
                    udrunliners = (ArrayList<Udrunliner>) data.getSerializableExtra("UdrunlinerList");
                    break;
            }
        }
    }
}
