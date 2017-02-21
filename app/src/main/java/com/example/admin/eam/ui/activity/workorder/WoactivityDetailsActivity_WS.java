package com.example.admin.eam.ui.activity.workorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.eam.R;
import com.example.admin.eam.model.Woactivity;
import com.example.admin.eam.model.WorkOrder;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.activity.PhotoActivity;
import com.example.admin.eam.utils.DateSelect;


/**
 * Created by think on 2016/6/21.
 * 定检工单任务详情页面
 */
public class WoactivityDetailsActivity_WS extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Woactivity woactivity = new Woactivity();
    private WorkOrder workOrder;
    private int position;

    private TextView taskid;//任务
    private TextView description;//描述
    private TextView wojo1;//系统/项目
    private TextView wojo2;//子系统/子项目
    private TextView wojo3;//检查/检修方法
    private TextView wojo4;//kks编码
    private EditText allpower;//人员数量
    private EditText alloptime;//耗时(小时)
    private EditText invcontent;//定检规格
    private EditText udzgstu;//检修情况
    private EditText udzgmeasure;//不合格修正措施
    private CheckBox perinspr;//定检结果
    private TextView udrlstopdate;//完成时间
    private EditText udremark;//备注
    private LinearLayout buttonlayout;
    private Button confirm;//确定

    /**
     * 菜单
     */
    private ImageView menuImageView;
    private PopupWindow popupWindow;
    /**
     * 附件上传*
     */
    private LinearLayout uploadLinearLayout;
    /**
     * 隐藏*
     */
    private LinearLayout hiddenLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details_ws);
        geiIntentData();
        findViewById();
        initView();
        Log.e("工单","工单任务详情");
    }

    private void geiIntentData() {
        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        taskid = (TextView) findViewById(R.id.work_woactivity_taskid);
        description = (TextView) findViewById(R.id.woactivity_description);
        wojo1 = (TextView) findViewById(R.id.woactivity_wojo1);
        wojo2 = (TextView) findViewById(R.id.woactivity_wojo2);
        wojo3 = (TextView) findViewById(R.id.woactivity_wojo3);
        wojo4 = (TextView) findViewById(R.id.woactivity_wojo4);
        allpower = (EditText) findViewById(R.id.woactivity_allpower);
        alloptime = (EditText) findViewById(R.id.woactivity_alloptime);
        invcontent = (EditText) findViewById(R.id.woactivity_invcontent);
        udzgstu = (EditText) findViewById(R.id.woactivity_udzgstu);
        udzgmeasure = (EditText) findViewById(R.id.woactivity_udzgmeasure);
        perinspr = (CheckBox) findViewById(R.id.woactivity_perinspr);
        udrlstopdate = (TextView) findViewById(R.id.woactivity_udrlstopdate);
        udremark = (EditText) findViewById(R.id.woactivity_udremark);
        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.confirm);
//        delete = (Button) findViewById(R.id.work_delete);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_woactivitydetails));

        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(menuImageView);
            }
        });
        taskid.setText(woactivity.TASKID);
        description.setText(woactivity.DESCRIPTION);
        wojo1.setText(woactivity.WOJO1);
        wojo2.setText(woactivity.WOJO2);
        wojo3.setText(woactivity.WOJO3);
        wojo4.setText(woactivity.WOJO4);
        allpower.setText(woactivity.ALLPOWER);
        alloptime.setText(woactivity.ALLOPTIME);
        invcontent.setText(woactivity.INVCONTENT);
        udzgstu.setText(woactivity.UDZGSTU);
        udzgmeasure.setText(woactivity.UDZGMEASURE);

        if(woactivity.PERINSPR!=null)
        {
            if (woactivity.PERINSPR.equals("Y"))
            {
                perinspr.setChecked(true);
            }else {perinspr.setChecked(false);}

        }else {perinspr.setChecked(false);}

        udrlstopdate.setText(woactivity.UDRLSTOPDATE);
        udremark.setText(woactivity.UDREMARK);

        udrlstopdate.setOnClickListener(new DateChecked(udrlstopdate));
        confirm.setOnClickListener(confirmOnClickListener);
//        delete.setOnClickListener(deleteOnClickListener);
        if (workOrder.UDSTATUS.equals("新建")||workOrder.UDSTATUS.equals("待审批")){
            udremark.setEnabled(false);
            allpower.setEnabled(false);
            alloptime.setEnabled(false);
            perinspr.setClickable(false);
            invcontent.setEnabled(false);
            udzgstu.setEnabled(false);
            udzgmeasure.setEnabled(false);
        }else if (workOrder.UDSTATUS.equals("进行中")||workOrder.UDSTATUS.equals("已反馈")
                ||workOrder.UDSTATUS.equals("物料已申请")||workOrder.UDSTATUS.equals("驳回")){
            perinspr.setClickable(false);
        }
    }

    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(WoactivityDetailsActivity_WS.this).inflate(
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
        hiddenLinearLayout = (LinearLayout) contentView.findViewById(R.id.delete_linearlayout_id);
        hiddenLinearLayout.setVisibility(View.GONE);

        TextView udloadText = (TextView) contentView.findViewById(R.id.textView_id);
        ImageView udloadImage = (ImageView) contentView.findViewById(R.id.imageView_id);

        udloadText.setText(getResources().getString(R.string.work_commit));

        udloadImage.setImageResource(R.mipmap.ic_upload);

        uploadLinearLayout.setOnClickListener(uploadLinearLayoutOnClickListener);


    }
    private View.OnClickListener uploadLinearLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            Intent intent = new Intent(WoactivityDetailsActivity_WS.this, PhotoActivity.class);
            intent.putExtra("ownertable", "WOACTIVITY");
            intent.putExtra("ownerid", woactivity.getTASKID());
            startActivityForResult(intent, 0);
        }

    };
    class DateChecked implements View.OnClickListener {
        TextView textView;

        public DateChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateSelect(WoactivityDetailsActivity_WS.this, textView).showDialog();
        }
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.ALLPOWER = allpower.getText().toString();
        woactivity.ALLOPTIME = alloptime.getText().toString();
        woactivity.INVCONTENT = invcontent.getText().toString();
        woactivity.UDZGSTU = udzgstu.getText().toString();
        woactivity.UDZGMEASURE = udzgmeasure.getText().toString();
        woactivity.PERINSPR = perinspr.isChecked() ? "Y" : "N";
        woactivity.UDRLSTOPDATE = udrlstopdate.getText().toString();
        woactivity.UDREMARK = udremark.getText().toString();
        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if (((woactivity.ALLPOWER==null||woactivity.ALLPOWER.equals(allpower.getText().toString()))
                    && (woactivity.ALLOPTIME==null||woactivity.ALLOPTIME.equals(alloptime.getText().toString()))
                    && (woactivity.INVCONTENT==null||woactivity.INVCONTENT.equals(invcontent.getText().toString()))
                    && (woactivity.UDZGSTU==null||woactivity.UDZGSTU.equals(udzgstu.getText().toString()))
                    && (woactivity.UDZGMEASURE==null||woactivity.UDZGMEASURE.equals(udzgmeasure.getText().toString()))
                    && (woactivity.PERINSPR.equals(perinspr.isChecked() ? "Y" : "N"))
                    && (woactivity.UDRLSTOPDATE==null||woactivity.UDRLSTOPDATE.equals(udrlstopdate.getText().toString()))
                    &&(woactivity.UDREMARK==null||woactivity.UDREMARK.equals(udremark.getText().toString())))
                    ||workOrder.UDSTATUS.equals("已关闭")||workOrder.UDSTATUS.equals("已取消")) {//如果内容没有修改
                intent.putExtra("woactivity", woactivity);
            } else {
                Woactivity woactivity = getWoactivity();
                if (woactivity.TYPE == null || !woactivity.TYPE.equals("add")) {
                    woactivity.TYPE = "update";
                }
                intent.putExtra("woactivity", woactivity);
                Toast.makeText(WoactivityDetailsActivity_WS.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
            }
            intent.putExtra("position", position);
            WoactivityDetailsActivity_WS.this.setResult(2, intent);
            finish();
        }
    };


    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = getIntent();
//            intent.putExtra("position", position);
//            if (woactivity.workorderid == null||woactivity.workorderid.equals("")){
//                WoactivityDetailsActivity.this.setResult(3, intent);
//            }else {
//                Woactivity woactivity = getWoactivity();
//                woactivity.optiontype = "delete";
//                intent.putExtra("woactivity", woactivity);
//                WoactivityDetailsActivity.this.setResult(4, intent);
//            }
//            finish();
        }
    };
}
