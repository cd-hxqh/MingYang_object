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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.UdTriprePort;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

public class TriprePort_DetailActivity extends BaseActivity {

    private LinearLayout pictureLinearlayout;

    private TextView titlename;//
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private PopupWindow popupWindow;

    private UdTriprePort triprePortr;

    private TextView name;//工号／姓名
    private TextView department;//部门
    private TextView description;//描述
    private TextView project;//出差项目
    private TextView toplace;//出差地点
    private TextView tripcode;//出差申请号
    private TextView tripdate;//出差日期
    private TextView tripcontent;//出差事由
    private TextView workcontent;//工作内容
    private TextView creatby;//录入人
    private TextView createDate;//录入时间

    private Button cancel;
    private Button save;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripre_port_detail);
        getIntentData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

    }
    private void getIntentData(){
        triprePortr=(UdTriprePort)getIntent().getSerializableExtra("triprePortr");
        Log.e("出差","取得的数据"+triprePortr.getDESCRIPTION());
    }
    @Override
    protected void findViewById() {

        backlayout = (RelativeLayout) findViewById(R.id.title_back);

        titlename = (TextView) findViewById(R.id.title_name);

        menuImageView = (ImageView) findViewById(R.id.title_add);

        //姓名
        name=(TextView)findViewById(R.id.trip_name);
        //部门
        department=(TextView)findViewById(R.id.trip_department);
        //项目
        project=(TextView)findViewById(R.id.trip_project);
        //描述
        description=(EditText) findViewById(R.id.trip_decription);
        //地点
        toplace=(EditText)findViewById(R.id.trip_location);
        //出差申请号
        tripcode=(TextView)findViewById(R.id.trip_num);
        //出差日期
        tripdate=(TextView)findViewById(R.id.trip_date);
        //出差事由
        tripcontent=(EditText)findViewById(R.id.trip_reason);
        //工作内容
        workcontent=(EditText)findViewById(R.id.trip_word_content);
        //录入人
        creatby=(TextView)findViewById(R.id.trip_createby);
        //录入时间
        createDate=(TextView)findViewById(R.id.trip_createDate);

        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);

    }
    @Override
    protected void initView() {


        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlename.setText("出差总结报告详情");
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        //姓名
        name.setText(triprePortr.getACOUNT());
        //部门
        department.setText(triprePortr.getDEPTNAME());
        //项目
        project.setText(triprePortr.getPROJECT()+" "+triprePortr.getPROJECTNAME());
        //描述
        description.setText(triprePortr.getDESCRIPTION());
        //地点
        toplace.setText(triprePortr.getTOPLACE());
        //出差申请号
        tripcode.setText(triprePortr.getTRIPCODE());
        //出差日期
        tripdate.setText(triprePortr.getTRIPDATE());
        //出差事由
        tripcontent.setText(triprePortr.getTRIPCONTENT());
        //工作内容
        workcontent.setText(triprePortr.getWORKCONTENT());
        //录入人
        creatby.setText(triprePortr.getCREATEBY());
        //录入时间
        createDate.setText(triprePortr.getCREATEDATE());

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择出差人");
                Intent intent = new Intent(TriprePort_DetailActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 1);
            }
        });
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择出差部门");
                Intent intent = new Intent(TriprePort_DetailActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.UDDEPTCODE);
                startActivityForResult(intent, 2);

            }
        });
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择出差项目");
                Intent intent = new Intent(TriprePort_DetailActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.UDPROCODE);
                startActivityForResult(intent, 3);
            }
        });
        tripdate.setOnClickListener(new DateChecked(tripdate));
        creatby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择录入人");
                Intent intent = new Intent(TriprePort_DetailActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 4);
            }
        });
        createDate.setOnClickListener(new DateChecked(tripdate));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.e("调试工单","按下取消按钮");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDataInfo();
                Log.e("调试工单","按下确定按钮");
            }
        });

    }
    class DateChecked implements View.OnClickListener {
        TextView textView;

        public DateChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateSelect(TriprePort_DetailActivity.this, textView).showDialog();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Option option;
        if (requestCode==1)
        {
            option = (Option) data.getSerializableExtra("option");
            name.setText(option.getName());
        }
        if (requestCode==2)
        {
            option = (Option) data.getSerializableExtra("option");
            department.setText(option.getName());
        }
        if (requestCode==3)
        {
            option = (Option) data.getSerializableExtra("option");
            project.setText(option.getName());
        }
        if (requestCode==4)
        {
            option = (Option) data.getSerializableExtra("option");
            creatby.setText(option.getName());
        }
    }
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(TriprePort_DetailActivity.this).inflate(
                R.layout.trip_popup, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);
        //图片上传
        pictureLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_commit_id);
        pictureLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","附件上传");
                popupWindow.dismiss();
                Intent intent = new Intent(TriprePort_DetailActivity.this, WxDemoActivity.class);
//                intent.putExtra("ownertable", "WORKORDER");
//                intent.putExtra("ownerid", workOrder.getDEBUGWORKORDERID()+"");
                startActivityForResult(intent, 0);
            }
        });
    }
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(TriprePort_DetailActivity.this);
        dialog.content("确定保存修改?")//
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
    private void startAsyncTask(){

        String updataInfo = null;

        updataInfo = JsonUtils.tripPortToJson(getTriprePortr());

        final String finalUpdataInfo = updataInfo;
        Log.e("出差","将要提交的出差总结报告"+finalUpdataInfo);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(TriprePort_DetailActivity.this,
                        finalUpdataInfo, "UDTRIPREPORT", "SERIALNUMBER", AccountUtils.getpersonId(TriprePort_DetailActivity.this), Constants.WORK_URL);
                //Context context, String json, String mboObjectName, String mboKey, String personId, String url
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(TriprePort_DetailActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    //Toast.makeText(TriprePort_addNewActivity.this, "工单" + workResult.wonum + "新增成功", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(TriprePort_DetailActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }

        }.execute();
    }
    protected UdTriprePort getTriprePortr()
    {
        UdTriprePort  triprePort=new UdTriprePort();

        triprePort.setACOUNT(name.getText().toString());
        triprePort.setDESCRIPTION(description.getText().toString());//描述
        triprePort.setACOUNT(name.getText().toString());//出差人编号
        triprePort.setNAME1(name.getText().toString());//出差人姓名
        triprePort.setDEPTNUM(department.getText().toString());//部门编号
        triprePort.setCREATEBY(creatby.getText().toString());//录入人;
        triprePort.setCREATEDATE(createDate.getText().toString()+" 00:00:00");//录入日期
        triprePort.setTRIPDATE(tripdate.getText().toString()+" 00:00:00");//出差日期
        triprePort.setPROJECT(project.getText().toString());//出差项目
        triprePort.setTOPLACE(toplace.getText().toString());//出差地点
        triprePort.setTRIPCONTENT(tripcontent.getText().toString());//出差事由
        triprePort.setWORKCONTENT(workcontent.getText().toString());//工作内容

        return triprePort;
    }
}
