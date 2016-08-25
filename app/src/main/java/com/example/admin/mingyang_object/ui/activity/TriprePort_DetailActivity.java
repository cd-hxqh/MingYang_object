package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.UdTriprePort;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripre_port_detail);
        getIntentData();
        findViewById();
        initView();
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
        description=(TextView) findViewById(R.id.trip_decription);
        //地点
        toplace=(TextView)findViewById(R.id.trip_location);
        //出差申请号
        tripcode=(TextView)findViewById(R.id.trip_num);
        //出差日期
        tripdate=(TextView)findViewById(R.id.trip_date);
        //出差事由
        tripcontent=(TextView)findViewById(R.id.trip_reason);
        //工作内容
        workcontent=(TextView)findViewById(R.id.trip_word_content);
        //录入人
        creatby=(TextView)findViewById(R.id.trip_createby);
        //录入时间
        createDate=(TextView)findViewById(R.id.trip_createDate);
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
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){}
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
}
