package com.example.admin.mingyang_object.ui.activity.udpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.UdPerson_ListActivity;
import com.example.admin.mingyang_object.ui.activity.Ududvehicle_ListActivity;


/**
 * 项目台账
 */
public class Udpro_DetailActivity extends BaseActivity {
    private static String TAG = "Udpro_DetailActivity";

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
    private TextView pronumText; //项目编号

    private TextView prDescText; //项目名称

    private TextView branchText; //所属中心

    private TextView branchdesc;//中心描述

    private TextView responsText; //责任人

    private TextView responsname;//责任人描述

    private TextView responsphoneText;//责任人电话

    private TextView deptText;//责任人部门

    private TextView ownerText; //业主单位

    private TextView signdateText; //签订时间

    private TextView contractstatusText; //合同状态

    private CheckBox testproText; //试点项目？

    private TextView prostageText; //项目当前阶段

    private TextView capacityText; //总厂容量

    private TextView periodText; //质保期

    private TextView windspeed3;//额定风速(m/s)

    private TextView windspeed1;//切入风速(m/s)

    private TextView windspeed2;//切出风速(m/s)

    private TextView temperature1;//环境温度区间(℃)

    private TextView temperature2;//运行温度区间(℃)

    private TextView bond;//安全保证金

    private TextView transport;//运输方式

    private TextView tpop;//付款比例

    private TextView req2;//预验收要求

    private TextView req1;//试运行要求

    private TextView utilization;//可利用率要求

    private TextView specialcon;//特殊配置

    private TextView cycle;//调试周期

    private TextView remarks;//备注

    private Udpro udpro;

    private PopupWindow popupWindow;

    /**
     * 风机型号*
     */
    private LinearLayout udfandetailsLinear;
    /**
     * 项目人员*
     */
    private LinearLayout personLinear;
    /**
     * 项目车辆*
     */
    private LinearLayout udvehicleLinear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udpro_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udpro = (Udpro) getIntent().getSerializableExtra("udpro");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);


        pronumText = (TextView) findViewById(R.id.pronum_text_id);
        prDescText = (TextView) findViewById(R.id.prdesc_text_id);
        branchText = (TextView) findViewById(R.id.branch_text_id);
        branchdesc = (TextView) findViewById(R.id.branchdesc_text);
        responsText = (TextView) findViewById(R.id.respons_text_id);
        responsname = (TextView) findViewById(R.id.responsname_text);
        responsphoneText = (TextView) findViewById(R.id.responsphone_text_id);
        deptText = (TextView) findViewById(R.id.dept_text_id);
        ownerText = (TextView) findViewById(R.id.owner_text_id);
        signdateText = (TextView) findViewById(R.id.signdate_text＿);
        contractstatusText = (TextView) findViewById(R.id.contractstatus_text_id);
        testproText = (CheckBox) findViewById(R.id.testpro_text_id);
        prostageText = (TextView) findViewById(R.id.prostage_text_id);
        capacityText = (TextView) findViewById(R.id.capacity_text_id);
        periodText = (TextView) findViewById(R.id.period_text_id);
        windspeed3 = (TextView) findViewById(R.id.windspeed3_text);
        windspeed1 = (TextView) findViewById(R.id.windspeed1_text);
        windspeed2 = (TextView) findViewById(R.id.windspeed2_text);
        temperature1 = (TextView) findViewById(R.id.temperature1_text);
        temperature2 = (TextView) findViewById(R.id.temperature2_text);
        bond = (TextView) findViewById(R.id.bond_text);
        transport = (TextView) findViewById(R.id.transport_text);
        tpop = (TextView) findViewById(R.id.tpop_text);
        req2 = (TextView) findViewById(R.id.req2_text);
        req1 = (TextView) findViewById(R.id.req1_text);
        utilization = (TextView) findViewById(R.id.utilization_text);
        specialcon = (TextView) findViewById(R.id.specialcon_text);
        cycle = (TextView) findViewById(R.id.cycle_text);
        remarks = (TextView) findViewById(R.id.remarks_text);

        if (udpro != null) {
            pronumText.setText(udpro.getPRONUM());
            prDescText.setText(udpro.getDESCRIPTION());
            branchText.setText(udpro.getBRANCH());
            branchdesc.setText(udpro.getBRANCHDESC());
            responsText.setText(udpro.getRESPONS());
            responsname.setText(udpro.getRESPONSNAME());
            responsphoneText.setText(udpro.getRESPONSPHONE());
            deptText.setText(udpro.getDEPT());
            ownerText.setText(udpro.getOWNER());
            signdateText.setText(udpro.getSIGNDATE());
            contractstatusText.setText(udpro.getCONTRACTSTATUS());
            testproText.setChecked(udpro.getTESTPRO() != null && udpro.getTESTPRO().equals("Y"));
            prostageText.setText(udpro.getPROSTAGE());
            capacityText.setText(udpro.getCAPACITY());
            periodText.setText(udpro.getPERIOD());
            windspeed3.setText(udpro.getWINDSPEED3());
            windspeed1.setText(udpro.getWINDSPEED1());
            windspeed2.setText(udpro.getWINDSPEED2());
            temperature1.setText(udpro.getTEMPERATURE1());
            temperature2.setText(udpro.getTEMPERATURE2());
            bond.setText(udpro.getBOND());
            transport.setText(udpro.getTRANSPORT());
            tpop.setText(udpro.getTPOP());
            req2.setText(udpro.getREQ2());
            req1.setText(udpro.getREQ1());
            utilization.setText(udpro.getUTILIZATION());
            specialcon.setText(udpro.getSPECIALCON());
            cycle.setText(udpro.getCYCLE());
            remarks.setText(udpro.getREMARKS());
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.udpro_detail_text));
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        testproText.setClickable(false);
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

        View contentView = LayoutInflater.from(Udpro_DetailActivity.this).inflate(
                R.layout.popup_window, null);


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
        udfandetailsLinear = (LinearLayout) contentView.findViewById(R.id.udfandetails_id);
        personLinear = (LinearLayout) contentView.findViewById(R.id.person_id);
        udvehicleLinear = (LinearLayout) contentView.findViewById(R.id.udvehicle_id);

        udfandetailsLinear.setOnClickListener(udfandetailsLinearOnClickListener);
        personLinear.setOnClickListener(personLinearOnClickListener);
        udvehicleLinear.setOnClickListener(udvehicleLinearOnClickListener);

    }


    private View.OnClickListener udfandetailsLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udpro_DetailActivity.this, Udfandetails_ListActivity.class);
            intent.putExtra("pronum", udpro.getPRONUM());
            intent.putExtra("siteid", udpro.getSITEID());
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };
    private View.OnClickListener personLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udpro_DetailActivity.this, UdPerson_ListActivity.class);
            intent.putExtra("pronum", udpro.getPRONUM());
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };
    private View.OnClickListener udvehicleLinearOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udpro_DetailActivity.this, Ududvehicle_ListActivity.class);
            intent.putExtra("pronum", udpro.getPRONUM());
            startActivityForResult(intent, 0);
            popupWindow.dismiss();

        }
    };
}
