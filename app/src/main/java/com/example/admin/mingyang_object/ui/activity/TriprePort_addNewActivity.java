package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

public class TriprePort_addNewActivity extends BaseActivity {

    private UdTriprePort triprePortr;
    private TextView titlename;//
    private ImageView menuImageView;
    private RelativeLayout backlayout;

    private TextView name;//工号／姓名
    private TextView department;//部门
    private EditText description;//描述
    private TextView project;//出差项目
    private EditText toplace;//出差地点
    private TextView tripdate;//出差日期
    private EditText tripcontent;//出差事由
    private EditText workcontent;//工作内容
    private TextView creatby;//录入人
    private TextView createDate;//录入时间

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private Button cancel;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripre_port_add_new);
        findViewById();
        initView();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

    }
    @Override
    protected void findViewById() {

        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);

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

        titlename.setText("新建出差总结报告");

        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //姓名
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择出差人");
                Intent intent = new Intent(TriprePort_addNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 1);
            }
        });
        //部门
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择出差部门");
                Intent intent = new Intent(TriprePort_addNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.UDDEPTCODE);
                startActivityForResult(intent, 2);
            }
        });
        //项目
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择出差项目");
                Intent intent = new Intent(TriprePort_addNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.UDPROCODE);
                startActivityForResult(intent, 3);
            }
        });

        //出差日期
        tripdate.setOnClickListener(new DateChecked(tripdate));

        //录入人
        creatby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("出差","选择录入人");
                Intent intent = new Intent(TriprePort_addNewActivity.this, OptionActivity.class);
                intent.putExtra("optiontype", Constants.PERSONCODE);
                startActivityForResult(intent, 4);
            }
        });
        //录入时间
        createDate.setOnClickListener(new DateChecked(createDate));
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

    class DateChecked implements View.OnClickListener {
        TextView textView;

        public DateChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateSelect(TriprePort_addNewActivity.this, textView).showDialog();
        }
    }
    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(TriprePort_addNewActivity.this);
        dialog.content("确定保存出差报告?")//
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
                        if(!checkdata())
                        {
                            return;
                        }
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

                WebResult reviseresult = AndroidClientService.InsertWO(TriprePort_addNewActivity.this,
                        finalUpdataInfo, "UDTRIPREPORT", "SERIALNUMBER", AccountUtils.getpersonId(TriprePort_addNewActivity.this), Constants.WORK_URL);
                //Context context, String json, String mboObjectName, String mboKey, String personId, String url
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {

                super.onPostExecute(workResult);

                if (workResult.errorMsg == null) {

                    Toast.makeText(TriprePort_addNewActivity.this, "保存成功", Toast.LENGTH_SHORT).show();

                } else if (workResult.errorMsg.equals("成功")) {

                    finish();

                } else {

                    Toast.makeText(TriprePort_addNewActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();

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
    protected  boolean checkdata()
    {
        //姓名
        if("".equals(name.getText().toString())||name.getText()==null)
        {
            Toast.makeText(TriprePort_addNewActivity.this, "请选择出差人", Toast.LENGTH_SHORT).show();
            return false;
        }
        //部门
        if("".equals(department.getText().toString())||department.getText()==null)
        {
            Toast.makeText(TriprePort_addNewActivity.this, "请选部门", Toast.LENGTH_SHORT).show();
            return false;
        }
        //录入人
        if("".equals(department.getText().toString())||department.getText()==null)
        {
            Toast.makeText(TriprePort_addNewActivity.this, "请选择录入人", Toast.LENGTH_SHORT).show();
            return false;
        }
        //出差项目
        if("".equals(department.getText().toString())||department.getText()==null)
        {
            Toast.makeText(TriprePort_addNewActivity.this, "请选择出差项目", Toast.LENGTH_SHORT).show();
            return false;
        }
        //出差地点
        if("".equals(department.getText().toString())||department.getText()==null)
        {
            Toast.makeText(TriprePort_addNewActivity.this, "请选填写出差地点", Toast.LENGTH_SHORT).show();
            return false;
        }
        //出差日期
        if("".equals(department.getText().toString())||department.getText()==null)
        {
            Toast.makeText(TriprePort_addNewActivity.this, "请选择出差日期", Toast.LENGTH_SHORT).show();
            return false;
        }
        //录入时间
        if("".equals(department.getText().toString())||department.getText()==null)
        {
            Toast.makeText(TriprePort_addNewActivity.this, "请选录入时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        //

        return true;

    }
}


