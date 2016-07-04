package com.example.admin.mingyang_object.ui.activity.workorder;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Wpmaterial;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.utils.WorkTypeUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * Created by think on 2015/10/29.
 */
public class Work_DetailsActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private PopupWindow popupWindow;

    /**
     * 工作计划*
     */
    private LinearLayout planLinearlayout;
    /**
     * 物料信息
     */
    private LinearLayout wpmaterialLinearLayout;
    /**
     * 故障汇报*
     */
    private LinearLayout reportLinearLayout;
    /**
     * 发送工作流*
     */
    private LinearLayout flowerLinearLayout;
    /**
     * 上传附件*
     */
    private LinearLayout commitLinearLayout;
    private WorkOrder workOrder;
    private LinearLayout work_numlayout;
    private TextView wonum;//工单号
    private EditText description;//工单描述
    private LinearLayout description_layout;
    private TextView branch;//中心
    private TextView udprojectnum;//项目
    private LinearLayout udlocnumlayout;
    private TextView udlocnum;//机位号
    private LinearLayout udlocationlayout;
    private TextView udlocation;//位置
    private LinearLayout leadlayout;
    private TextView leadText;
    private TextView lead;//运行组/维护组工程师
    private TextView udstatus;//状态
    private TextView createby;//创建人
    private TextView createdate;//创建时间
    private LinearLayout defultlayout;
    private TextView failurecode;//故障类
    private TextView problemcode;//问题原因
    private TextView culevel;//故障等级
    private TextView udrprrsb;//提报人
    private TextView udzglimit;//提报时间
    private LinearLayout udplannumlayout;
    private TextView udplannum;//终验收计划号
    private TextView schedstart;//计划开始时间
    private TextView schedfinish;//计划结束时间
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际结束时间
    private CheckBox isstoped;//是否停机
    private TextView pmchgevalstart;//故障开始时间
    private TextView pmchgevalend;//故障恢复时间
    private TextView udjgresult;//累计时间
    private EditText udprobdesc;//故障隐患描述
    private LinearLayout timelayout;
    private LinearLayout udjpnumlayout;
    private TextView udjpnumtext;
    private TextView udjpnum;//定检标准编号/排查标准/技改标准
    private LinearLayout udplstartdatelayout;
    private TextView udplstartdate;//计划开始时间
    private LinearLayout udplstopdatelayout;
    private TextView udplstopdate;//计划完成时间
    private TextView udrlstartdate;//实际开始时间
    private TextView udrlstopdate;//实际完成时间
    private LinearLayout inspolayout;
    private TextView udinspoby;//定检人员1
    private TextView udinspoby2;//定检人员2
    private TextView udinspoby3;//定检人员3
    private TextView djplannum;//定检计划编号
    private TextView djtype;//定检类型
    private TextView pccompnum1;//计划定检风机台数
    private LinearLayout lastlayout;
    private TextView wtcode;//风机型号
    private LinearLayout assettypelayout;
    private TextView assettype;//设备类别
    private LinearLayout perinsprlayout;
    private TextView perinsprtext;
    private CheckBox perinspr;//定检结果
    private LinearLayout udremarklayout;
    private EditText udremark;//备注
    private LinearLayout isbigparlayout;
    private CheckBox isbigpar;//大部件发放
    private LinearLayout udzgmeasurelayout;
    private EditText udzgmeasure;//故障处理方案
    private LinearLayout plannumlayout;
    private TextView plannum;//排查计划编号
    private LinearLayout pccompnumlayout;
    private TextView pccompnumtext;
    private EditText pccompnum;//排查完成台数/风机台数
    private LinearLayout pctypelayout;
    private TextView pctype;//排查类型
    private LinearLayout udfjfollayout;
    private EditText udfjfol;//风机跟踪
    private LinearLayout pcresonlayout;
    private TextView pcresontext;
    private EditText pcreson;//排查原因/技改原因
    private LinearLayout udjgresult1layout;
    private EditText udjgresult1;//排查结果
    private LinearLayout udjgwolayout;
    private TextView jgplannum;//技改计划编号
    private TextView udjgtype;//技改类型
    private EditText udfjappnum;//主控程序版本号
    private TextView udrprrsb1;//负责人
    private LinearLayout inspo2layout;//故障工单人员信息
    private TextView lead2;//维护/运行组长
    private TextView udinspoby_2;//维护/运行人员
    private TextView udinspoby2_2;//维护/运行人员
    private TextView udinspoby3_2;//维护/运行人员

    private String failurelist = "";
    private Button cancel;
    private Button save;

    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Wpmaterial> wpmaterialLit = new ArrayList<>();
//    private ArrayList<Failurereport> failurereportList = new ArrayList<>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_details);
        geiIntentData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
//        djtypeData();
//        pctypeData();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);

        work_numlayout = (LinearLayout) findViewById(R.id.work_wonum_layout);
        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (EditText) findViewById(R.id.work_describe);
        description_layout = (LinearLayout) findViewById(R.id.work_describe_layout);
        branch = (TextView) findViewById(R.id.work_branch);
        udprojectnum = (TextView) findViewById(R.id.work_udprojectnum);
        udlocnumlayout = (LinearLayout) findViewById(R.id.work_udlocnum_layout);
        udlocnum = (TextView) findViewById(R.id.work_udlocnum);
        udlocationlayout = (LinearLayout) findViewById(R.id.work_udlocation_layout);
        udlocation = (TextView) findViewById(R.id.work_udlocation);
        leadlayout = (LinearLayout) findViewById(R.id.work_lead_layout);
        leadText = (TextView) findViewById(R.id.work_lead_text);
        lead = (TextView) findViewById(R.id.work_lead);
        udstatus = (TextView) findViewById(R.id.work_status);
        createby = (TextView) findViewById(R.id.work_createby);
        createdate = (TextView) findViewById(R.id.work_createdate);
        defultlayout = (LinearLayout) findViewById(R.id.work_defultlayout);
        failurecode = (TextView) findViewById(R.id.work_failurecode);
        problemcode = (TextView) findViewById(R.id.work_problemcode);
        culevel = (TextView) findViewById(R.id.work_culevel);
        udrprrsb = (TextView) findViewById(R.id.work_udrprrsb);
        udzglimit = (TextView) findViewById(R.id.work_udzglimit);
        udplannumlayout = (LinearLayout) findViewById(R.id.work_udplannum_layout);
        udplannum = (TextView) findViewById(R.id.work_udplannum);
        schedstart = (TextView) findViewById(R.id.work_schedstart);
        schedfinish = (TextView) findViewById(R.id.work_schedfinish);
        actstart = (TextView) findViewById(R.id.work_actstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        isstoped = (CheckBox) findViewById(R.id.work_isstoped);
        pmchgevalstart = (TextView) findViewById(R.id.work_pmchgevalstart);
        pmchgevalend = (TextView) findViewById(R.id.work_pmchgevalend);
        udjgresult = (TextView) findViewById(R.id.work_udjgresult);
        udprobdesc = (EditText) findViewById(R.id.work_udprobdesc);
        timelayout = (LinearLayout) findViewById(R.id.work_timelayout);
        udjpnumlayout = (LinearLayout) findViewById(R.id.work_udjpnum_layout);
        udjpnumtext = (TextView) findViewById(R.id.work_udjpnum_text);
        udjpnum = (TextView) findViewById(R.id.work_udjpnum);
        udplstartdatelayout = (LinearLayout) findViewById(R.id.work_udplstartdate_layout);
        udplstartdate = (TextView) findViewById(R.id.work_udplstartdate);
        udplstopdatelayout = (LinearLayout) findViewById(R.id.work_udplstopdate_layout);
        udplstopdate = (TextView) findViewById(R.id.work_udplstopdate);
        udrlstartdate = (TextView) findViewById(R.id.work_udrlstartdate);
        udrlstopdate = (TextView) findViewById(R.id.work_udrlstopdate);
        inspolayout = (LinearLayout) findViewById(R.id.work_inspolayout);
        udinspoby = (TextView) findViewById(R.id.work_udinspoby);
        udinspoby2 = (TextView) findViewById(R.id.work_udinspoby2);
        udinspoby3 = (TextView) findViewById(R.id.work_udinspoby3);
        djplannum = (TextView) findViewById(R.id.work_djplannum);
        djtype = (TextView) findViewById(R.id.work_djtype);
        pccompnum1 = (TextView) findViewById(R.id.work_pccompnum1);
        lastlayout = (LinearLayout) findViewById(R.id.work_lastlayout);
        wtcode = (TextView) findViewById(R.id.work_wtcode);
        assettypelayout = (LinearLayout) findViewById(R.id.work_assettype_layout);
        assettype = (TextView) findViewById(R.id.work_assettype);
        perinsprlayout = (LinearLayout) findViewById(R.id.work_perinspr_layout);
        perinsprtext = (TextView) findViewById(R.id.work_perinspr_text);
        perinspr = (CheckBox) findViewById(R.id.work_perinspr);
        udremarklayout = (LinearLayout) findViewById(R.id.work_udremark_layout);
        udremark = (EditText) findViewById(R.id.work_udremark);
        isbigparlayout = (LinearLayout) findViewById(R.id.work_isbigpar_layout);
        isbigpar = (CheckBox) findViewById(R.id.work_isbigpar);
        udzgmeasurelayout = (LinearLayout) findViewById(R.id.work_udzgmeasure_layout);
        udzgmeasure = (EditText) findViewById(R.id.work_udzgmeasure);
        plannumlayout = (LinearLayout) findViewById(R.id.work_plannum_layout);
        plannum = (TextView) findViewById(R.id.work_plannum);
        pccompnumlayout = (LinearLayout) findViewById(R.id.work_pccompnum_layout);
        pccompnumtext = (TextView) findViewById(R.id.work_pccompnum_text);
        pccompnum = (EditText) findViewById(R.id.work_pccompnum);
        pctypelayout = (LinearLayout) findViewById(R.id.work_pctype_layout);
        pctype = (TextView) findViewById(R.id.work_pctype);
        udfjfollayout = (LinearLayout) findViewById(R.id.work_udfjfol_layout);
        udfjfol = (EditText) findViewById(R.id.work_udfjfol);
        pcresonlayout = (LinearLayout) findViewById(R.id.work_pcreson_layout);
        pcresontext = (TextView) findViewById(R.id.work_pcreson_text);
        pcreson = (EditText) findViewById(R.id.work_pcreson);
        udjgresult1layout = (LinearLayout) findViewById(R.id.work_udjgresult1e_layout);
        udjgresult1 = (EditText) findViewById(R.id.work_udjgresult1);
        udjgwolayout = (LinearLayout) findViewById(R.id.work_udjgwo_layout);
        jgplannum = (TextView) findViewById(R.id.work_jgplannum);
        udjgtype = (TextView) findViewById(R.id.work_udjgtype);
        udfjappnum = (EditText) findViewById(R.id.work_udfjappnum);
        udrprrsb1 = (TextView) findViewById(R.id.work_udrprrsb1);
        inspo2layout = (LinearLayout) findViewById(R.id.work_inspo2layout);
        lead2 = (TextView) findViewById(R.id.work_lead2);
        udinspoby_2 = (TextView) findViewById(R.id.work_udinspoby_2);
        udinspoby2_2 = (TextView) findViewById(R.id.work_udinspoby2_2);
        udinspoby3_2 = (TextView) findViewById(R.id.work_udinspoby3_2);

        cancel = (Button) findViewById(R.id.work_cancel);
        save = (Button) findViewById(R.id.work_save);
    }

    @Override
    protected void initView() {
        titlename.setText(WorkTypeUtils.getTitle(workOrder.WORKTYPE) + "详情");
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        if (!workOrder.isnew) {
            workOrder.isnew = false;
        }

        wonum.setText(workOrder.WONUM);
        description.setText(workOrder.DESCRIPTION);
        branch.setText(workOrder.BRANCH);
        udprojectnum.setText(workOrder.UDPROJECTNUM);
        udlocnum.setText(workOrder.UDLOCNUM);
        udlocation.setText(workOrder.UDLOCATION);
        udstatus.setText(workOrder.UDSTATUS);
        createby.setText(workOrder.CREATEBY);
        createdate.setText(workOrder.CREATEDATE);
        failurecode.setText(workOrder.FAILURECODE);
        problemcode.setText(workOrder.PROBLEMCODE);
        culevel.setText(workOrder.CULEVEL);

        udzglimit.setText(workOrder.UDZGLIMIT);
        udplannum.setText(workOrder.UDPLANNUM);
        schedstart.setText(workOrder.SCHEDSTART);
        schedfinish.setText(workOrder.SCHEDFINISH);
        actstart.setText(workOrder.ACTSTART);
        actfinish.setText(workOrder.ACTFINISH);
        isstoped.setChecked(workOrder.ISSTOPED != 0);
        pmchgevalstart.setText(workOrder.PMCHGEVALSTART);
        pmchgevalend.setText(workOrder.PMCHGEVALEND);
        if (workOrder.WORKTYPE.equals(Constants.FR)) {
            lead2.setText(workOrder.LEAD);
            udinspoby_2.setText(workOrder.UDINSPOBY);
            udinspoby2_2.setText(workOrder.UDINSPOBY2);
            udinspoby3_2.setText(workOrder.UDINSPOBY3);
            udrprrsb.setText(workOrder.UDRPRRSB);
            udjgresult.setText(workOrder.UDJGRESULT);
        } else {
            lead.setText(workOrder.LEAD);
            udinspoby.setText(workOrder.UDINSPOBY);
            udinspoby2.setText(workOrder.UDINSPOBY2);
            udinspoby3.setText(workOrder.UDINSPOBY3);
            udrprrsb1.setText(workOrder.UDRPRRSB);
            udjgresult1.setText(workOrder.UDJGRESULT);
        }
        udprobdesc.setText(workOrder.UDPROBDESC);
        udjpnum.setText(workOrder.UDJPNUM);
        udplstartdate.setText(workOrder.UDPLSTARTDATE);
        udplstopdate.setText(workOrder.UDPLSTOPDATE);
        udrlstartdate.setText(workOrder.UDRLSTARTDATE);
        udrlstopdate.setText(workOrder.UDRLSTOPDATE);
        djplannum.setText(workOrder.DJPLANNUM);
        djtype.setText(workOrder.DJTYPE);
        if (workOrder.WORKTYPE.equals(Constants.WS)) {
            pccompnum1.setText(workOrder.PCCOMPNUM);
        } else {
            pccompnum.setText(workOrder.PCCOMPNUM);
        }
        wtcode.setText(workOrder.WTCODE);
        assettype.setText(workOrder.ASSETTYPE);
        perinspr.setChecked(workOrder.PERINSPR != 0);
        udremark.setText(workOrder.UDREMARK);
        isbigpar.setChecked(workOrder.ISBIGPAR != 0);
        udzgmeasure.setText(workOrder.UDZGMEASURE);
        plannum.setText(workOrder.PLANNUM);
        pctype.setText(workOrder.PCTYPE);
        udfjfol.setText(workOrder.UDFJFOL);
        pcreson.setText(workOrder.PCRESON);

        jgplannum.setText(workOrder.JGPLANNUM);
        udjgtype.setText(workOrder.UDJGTYPE);
        udfjappnum.setText(workOrder.UDFJAPPNUM);


        if (workOrder.UDSTATUS.equals(Constants.STATUS1)) {
            pctype.setOnClickListener(new NormalListDialogOnClickListener(pctype));
        }
        djtype.setOnClickListener(new NormalListDialogOnClickListener(djtype));
        udjgtype.setOnClickListener(new NormalListDialogOnClickListener(udjgtype));
        culevel.setOnClickListener(new NormalListDialogOnClickListener(culevel));
        lead.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
        udinspoby.setOnClickListener(new LayoutOnClickListener(2, Constants.PERSONCODE));
        udinspoby2.setOnClickListener(new LayoutOnClickListener(3, Constants.PERSONCODE));
        udinspoby3.setOnClickListener(new LayoutOnClickListener(4, Constants.PERSONCODE));
        udrprrsb.setOnClickListener(new LayoutOnClickListener(11, Constants.PERSONCODE));
        udprojectnum.setOnClickListener(new LayoutOnClickListener(6, Constants.UDPROCODE));
        udlocnum.setOnClickListener(new LayoutOnClickListener(7, Constants.UDLOCNUMCODE));
//        if (workOrder.WORKTYPE.equals(Constants.WS)) {
//            udjpnum.setOnClickListener(new LayoutOnClickListener(5, Constants.WS_JOBPLANCODE));
//        } else if (workOrder.WORKTYPE.equals(Constants.SP)) {
//            udjpnum.setOnClickListener(new LayoutOnClickListener(5, Constants.SP_JOBPLANCODE));
//        } else if (workOrder.WORKTYPE.equals(Constants.TP)) {
//            udjpnum.setOnClickListener(new LayoutOnClickListener(5, Constants.TP_JOBPLANCODE));
//        }//只有在新建工单的时候可选
        wtcode.setOnClickListener(new LayoutOnClickListener(8, Constants.WTCODE));
        udlocation.setOnClickListener(new LayoutOnClickListener(9, Constants.LOCATIONCODE));
        udplannum.setOnClickListener(new LayoutOnClickListener(10, Constants.ZYS_UDPLANNUMCODE));
        failurecode.setOnClickListener(new LayoutOnClickListener(12, Constants.FAILURECODE));
        problemcode.setOnClickListener(new LayoutOnClickListener(13, Constants.PROBLEMCODE));
        lead2.setOnClickListener(new LayoutOnClickListener(14,Constants.PERSONCODE));
        udinspoby_2.setOnClickListener(new LayoutOnClickListener(15,Constants.PERSONCODE));
        udinspoby2_2.setOnClickListener(new LayoutOnClickListener(16,Constants.PERSONCODE));
        udinspoby3_2.setOnClickListener(new LayoutOnClickListener(17,Constants.PERSONCODE));
        udplstartdate.setOnClickListener(new DateChecked(udplstartdate));
        udplstopdate.setOnClickListener(new DateChecked(udplstopdate));
        udrlstartdate.setOnClickListener(new DateChecked(udrlstartdate));
        udrlstopdate.setOnClickListener(new DateChecked(udrlstopdate));
        udzglimit.setOnClickListener(new DateTimeOnClickListener(udzglimit));
        schedstart.setOnClickListener(new DateTimeOnClickListener(schedstart));
        schedfinish.setOnClickListener(new DateTimeOnClickListener(schedfinish));
        actstart.setOnClickListener(new DateTimeOnClickListener(actstart));
        actfinish.setOnClickListener(new DateTimeOnClickListener(actfinish));
        pmchgevalstart.setOnClickListener(new DateTimeOnClickListener(pmchgevalstart));
        pmchgevalend.setOnClickListener(new DateTimeOnClickListener(pmchgevalend));

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
        setLayout();
    }

//    private View.OnClickListener djtypeOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            NormalListDialog();
//        }
//    };
//
//    private View.OnClickListener pctypeOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            NormalListDialog2();
//        }
//    };

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
        if (textView == djtype) {
            types = getResources().getStringArray(R.array.djtype_array);
        } else if (textView == pctype) {
            types = getResources().getStringArray(R.array.pctype_array);
        } else if (textView == udjgtype) {
            types = getResources().getStringArray(R.array.udjgtype_array);
        } else if (textView == culevel) {
            types = getResources().getStringArray(R.array.culevel_array);
        }
        for (int i = 0; i < types.length; i++) {
            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }
        final NormalListDialog dialog = new NormalListDialog(Work_DetailsActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                textView.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }
//
//    private void NormalListDialog2() {
//        pctypeData();
//        final NormalListDialog dialog = new NormalListDialog(Work_DetailsActivity.this, mMenuItems2);
//        dialog.title("请选择")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)//
//                .show();
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                pctype.setText(mMenuItems2.get(position).mOperName);
//
//                dialog.dismiss();
//            }
//        });
//    }
//
//    /**
//     * 添加定检类型数据*
//     */
//    private void djtypeData() {
//        String[] lctypes = getResources().getStringArray(R.array.djtype_array);
//
//        for (int i = 0; i < lctypes.length; i++)
//            mMenuItems.add(new DialogMenuItem(lctypes[i], 0));
//    }
//
//    /**
//     * 添加排查类型数据*
//     */
//    private void pctypeData() {
//        String[] pctypes = getResources().getStringArray(R.array.pctype_array);
//
//        for (int i = 0; i < pctypes.length; i++)
//            mMenuItems2.add(new DialogMenuItem(pctypes[i], 0));
//    }

    //时间选择监听
    private class DateTimeOnClickListener implements View.OnClickListener {
        TextView textView;

        private DateTimeOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View view) {
            new DateTimeSelect(Work_DetailsActivity.this, textView).showDialog();
        }
    }

    //按照工单类型修改布局
    private void setLayout() {
        switch (workOrder.WORKTYPE) {
            case "FR"://故障工单
                leadlayout.setVisibility(View.GONE);
                inspo2layout.setVisibility(View.VISIBLE);
                timelayout.setVisibility(View.GONE);
                inspolayout.setVisibility(View.GONE);
                lastlayout.setVisibility(View.GONE);
                break;
            case "AA"://终验收工单
                defultlayout.setVisibility(View.GONE);
                inspolayout.setVisibility(View.GONE);
                assettypelayout.setVisibility(View.GONE);
                perinsprlayout.setVisibility(View.GONE);
                udremarklayout.setVisibility(View.GONE);
                isbigparlayout.setVisibility(View.GONE);
                udzgmeasurelayout.setVisibility(View.GONE);
                plannumlayout.setVisibility(View.GONE);
                pccompnumlayout.setVisibility(View.GONE);
                pctypelayout.setVisibility(View.GONE);
                udfjfollayout.setVisibility(View.GONE);
                pcresonlayout.setVisibility(View.GONE);
                udjgresult1layout.setVisibility(View.GONE);
                udjgwolayout.setVisibility(View.GONE);
                udlocationlayout.setVisibility(View.GONE);
                leadText.setText(R.string.work_lead2);
                udplstartdatelayout.setVisibility(View.GONE);
                udplstopdatelayout.setVisibility(View.GONE);
                udjpnumlayout.setVisibility(View.GONE);
                break;
            case "SP"://排查工单
                udplannumlayout.setVisibility(View.GONE);
                defultlayout.setVisibility(View.GONE);
                inspolayout.setVisibility(View.GONE);
                assettypelayout.setVisibility(View.GONE);
                perinsprtext.setText(R.string.work_perinspr2);
                isbigparlayout.setVisibility(View.GONE);
                udjgwolayout.setVisibility(View.GONE);
                leadText.setText(R.string.work_lead2);
                udlocnumlayout.setVisibility(View.GONE);
                udjpnumtext.setText(R.string.work_udjpnum2);
                udlocationlayout.setVisibility(View.GONE);
                break;
            case "TP"://技改工单
                udplannumlayout.setVisibility(View.GONE);
                defultlayout.setVisibility(View.GONE);
                inspolayout.setVisibility(View.GONE);
                perinsprtext.setText(R.string.work_perinspr3);
                udremarklayout.setVisibility(View.GONE);
                isbigparlayout.setVisibility(View.GONE);
                udzgmeasurelayout.setVisibility(View.GONE);
                plannumlayout.setVisibility(View.GONE);
                pccompnumtext.setText(R.string.work_pccompnum2);
                pctypelayout.setVisibility(View.GONE);
                pcresontext.setText(R.string.work_pcreson2);
                udjgresult1layout.setVisibility(View.GONE);
                leadText.setText(R.string.work_lead4);
                udlocationlayout.setVisibility(View.GONE);
                udlocnumlayout.setVisibility(View.GONE);
                udjpnumtext.setText(R.string.work_udjpnum3);
                break;
            case "WS"://定检工单
                udplannumlayout.setVisibility(View.GONE);
                defultlayout.setVisibility(View.GONE);
                assettypelayout.setVisibility(View.GONE);
                udzgmeasurelayout.setVisibility(View.GONE);
                plannumlayout.setVisibility(View.GONE);
                pccompnumlayout.setVisibility(View.GONE);
                pctypelayout.setVisibility(View.GONE);
                udfjfollayout.setVisibility(View.GONE);
                pcresonlayout.setVisibility(View.GONE);
                udjgresult1layout.setVisibility(View.GONE);
                udjgwolayout.setVisibility(View.GONE);
                leadText.setText(R.string.work_lead1);
                udlocationlayout.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    //生成菜单
    private void decisionLayout() {
        switch (workOrder.WORKTYPE) {
            case "FR"://故障工单
//                planLinearlayout.setVisibility(View.GONE);
                break;
            case "AA"://终验收工单
                wpmaterialLinearLayout.setVisibility(View.GONE);
                break;
//            case "DC"://调试工单
//
//                break;
            case "SP"://排查工单
                wpmaterialLinearLayout.setVisibility(View.GONE);
                break;
            case "TP"://技改工单
                wpmaterialLinearLayout.setVisibility(View.GONE);
                break;
            case "WS"://定检工单
                wpmaterialLinearLayout.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }


    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Work_DetailsActivity.this).inflate(
                R.layout.work_popup_window, null);


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

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_plan_id);
        wpmaterialLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_wpmaterial_id);
//        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);
        flowerLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_flower_id);
        commitLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_commit_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        flowerLinearLayout.setOnClickListener(flowerOnClickListener);
        wpmaterialLinearLayout.setOnClickListener(wpmaterialOnClickListener);
        commitLinearLayout.setOnClickListener(commitOnClickListener);
        decisionLayout();

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            if (workOrder.WORKTYPE.equals(Constants.SP)||workOrder.WORKTYPE.equals(Constants.TP)
//                    ||workOrder.WORKTYPE.equals(Constants.WS)) {
            if (workOrder.WORKTYPE.equals(Constants.FR) || (!workOrder.WORKTYPE.equals(Constants.FR) && !udjpnum.getText().toString().equals(""))) {
                Intent intent = new Intent(Work_DetailsActivity.this, Work_WoactivityActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workOrder", workOrder);
                bundle.putSerializable("woactivityList", woactivityList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1000);
                popupWindow.dismiss();
            } else {
                Toast.makeText(Work_DetailsActivity.this, "请选择计划标准", Toast.LENGTH_SHORT).show();
            }
//            }
        }
    };

    private View.OnClickListener flowerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (workOrder.UDSTATUS.equals("新建")){
                MaterialDialogOneBtn();
            }else {
                MaterialDialogOneBtn1();
            }
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener wpmaterialOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, Work_WpmaterialActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("wpmaterialList", wpmaterialLit);
            intent.putExtras(bundle);
            startActivityForResult(intent, 2000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener commitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(Work_DetailsActivity.this, ImageUploadActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("workOrder", workOrder);
//            intent.putExtras(bundle);
//            startActivity(intent);
        }
    };

    class DateChecked implements View.OnClickListener {
        TextView textView;

        public DateChecked(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            new DateSelect(Work_DetailsActivity.this, textView).showDialog();
        }
    }

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Work_DetailsActivity.this);
        dialog.content("确定修改工单吗?")//
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
                        InspectData();
                        dialog.dismiss();
                    }
                });
    }

    //检查字段
    private void InspectData() {
        switch (workOrder.WORKTYPE) {
            case "FR"://故障工单
//                if (udprojectnum.getText().toString().equals("")) {
//                    udprojectnum.setError("项目编号不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (udlocnum.getText().toString().equals("")) {
//                    udlocnum.setError("机位号不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入机位号", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (failurecode.getText().toString().equals("")) {
//                    failurecode.setError("故障类不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入故障类", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (problemcode.getText().toString().equals("")) {
//                    problemcode.setError("故障问题不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入故障问题", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (culevel.getText().toString().equals("")) {
//                    culevel.setError("故障等级不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请选择故障等级", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (udrprrsb.getText().toString().equals("")) {
//                    udrprrsb.setError("提报人不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入提报人", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (udzglimit.getText().toString().equals("")) {
//                    udzglimit.setError("提报时间不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入提报时间", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (schedstart.getText().toString().equals("")) {
//                    schedstart.setError("计划开始时间不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入计划开始时间", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (schedfinish.getText().toString().equals("")) {
//                    schedfinish.setError("计划完成时间不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入计划完成时间", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (lead.getText().toString().equals("")) {
//                    lead.setError("维护/运行组长不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入维护/运行组长", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (udinspoby.getText().toString().equals("")) {
//                    udinspoby.setError("维护/运行人员不为空");
//                    Toast.makeText(Work_DetailsActivity.this, "请输入维护/运行人员", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                break;
            case "AA"://终验收工单

                break;
            case "SP"://排查工单

                break;
            case "TP"://技改工单

                break;
            case "WS"://定检工单

                break;
            default:
                break;
        }
        showProgressDialog("数据提交中...");
        startAsyncTask();
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
        updataInfo = JsonUtils.WorkToJson(getWorkOrder(), getWoactivityList(),getWpmaterialList());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult reviseresult = AndroidClientService.UpdateWO(finalUpdataInfo,
                        "WORKORDER", "WONUM", workOrder.WONUM, Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WebResult workResult) {
                super.onPostExecute(workResult);
                if (workResult.errorMsg == null) {
                    Toast.makeText(Work_DetailsActivity.this, "修改工单失败", Toast.LENGTH_SHORT).show();
                } else if (workResult.errorMsg.equals("成功")) {
                    Toast.makeText(Work_DetailsActivity.this, "修改工单成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Work_DetailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }
        }.execute();
////        }

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
            Intent intent = new Intent(Work_DetailsActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            if (requestCode == 7 || requestCode == 8) {
                if (udprojectnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    intent.putExtra("udprojectnum", udprojectnum.getText().toString());
                }
            }
            if (requestCode == 9) {
                if (udprojectnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请先选择项目编号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (udlocnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请先选择机位号", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("udprojectnum", udprojectnum.getText().toString());
                intent.putExtra("udlocnum", udlocnum.getText().toString());
            }
            if (requestCode == 13) {
                if (failurecode.getText().toString().equals("") && failurelist.equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请先选择故障类", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!failurecode.getText().toString().equals("") && failurelist.equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请重新选择故障类", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("failurelist", failurelist);
            }
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 提交数据*
     */
    private void deleteDataInfo() {
        final NormalDialog dialog = new NormalDialog(Work_DetailsActivity.this);
        dialog.content("确定删除工单吗?")//
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
                        deleteAsyncTask();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 提交数据*
     */
    private void deleteAsyncTask() {
//        new AsyncTask<String, String, WebResult>() {
//            @Override
//            protected WebResult doInBackground(String... strings) {
//                WebResult reviseresult = AndroidClientService.DeleteWO(wonum.getText().toString(), AccountUtils.getpersonId(Work_detailsActivity.this), Constants.WORK_URL);
//                return reviseresult;
//            }
//
//            @Override
//            protected void onPostExecute(WebResult workResult) {
//                super.onPostExecute(workResult);
//                if (workResult==null) {
//                    Toast.makeText(Work_detailsActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
//                }else if (workResult.errorMsg.equals("操作成功！")&&workResult.errorNo.equals("0")) {
//                    Toast.makeText(Work_detailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
//                    Work_detailsActivity.this.finish();
//                } else {
//                    Toast.makeText(Work_detailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
//                }
//                closeProgressDialog();
//            }
//        }.execute();
////        }

    }

    private void MaterialDialogOneBtn() {//开始工作流
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否启动工作流")//
                .btnText("是", "否")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        startWF();
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
        );
    }

    private void MaterialDialogOneBtn1() {//审批工作流
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否填写输入意见")//
                .btnText("是", "否，直接提交")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        EditDialog(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        wfgoon("1", "");
                        dialog.dismiss();
                    }
                }
        );
    }


    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content(isok ? "通过" : "不通过")//
                .btnText("提交", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon("1", text);
                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {

                        dialog.dismiss();
                    }
                }
        );
    }

    /**
     * 开始工作流
     */
    private void startWF() {
        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
                getString(R.string.start), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult result = AndroidClientService.startwf(Work_DetailsActivity.this, WorkTypeUtils.getProcessname(workOrder.WORKTYPE), "WORKORDER", workOrder.WONUM, "WONUM");

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(WebResult s) {
                super.onPostExecute(s);
                if (s != null && s.errorMsg != null && s.errorMsg.equals("工作流启动成功")) {
                    Toast.makeText(Work_DetailsActivity.this, s.errorMsg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Work_DetailsActivity.this, "工作流启动失败", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    /**
     * 审批工作流
     * @param zx
     */
    private void wfgoon(final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
                getString(R.string.approve), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, WebResult>() {
            @Override
            protected WebResult doInBackground(String... strings) {
                WebResult result = AndroidClientService.approve(Work_DetailsActivity.this,
                        WorkTypeUtils.getProcessname(workOrder.WORKTYPE), "WORKORDER", workOrder.WONUM, "WONUM", zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(WebResult s) {
                super.onPostExecute(s);
                if (s==null||s.wonum==null||s.errorMsg==null) {
                    Toast.makeText(Work_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else if (s.wonum.equals(workOrder.WONUM)&&s.errorMsg!=null){
                    udstatus.setText(s.errorMsg);
                    workOrder.UDSTATUS = s.errorMsg;
                    Toast.makeText(Work_DetailsActivity.this, "审批成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Work_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    private WorkOrder getWorkOrder() {
        WorkOrder workOrder = this.workOrder;
        workOrder.WONUM = wonum.getText().toString();
        workOrder.DESCRIPTION = description.getText().toString();
        workOrder.BRANCH = branch.getText().toString();
        workOrder.UDPROJECTNUM = udprojectnum.getText().toString();
        workOrder.UDLOCNUM = udlocnum.getText().toString();
        workOrder.UDLOCATION = udlocation.getText().toString();
        workOrder.UDSTATUS = udstatus.getText().toString();
        workOrder.CREATEBY = createby.getText().toString();
        workOrder.CREATEDATE = createdate.getText().toString();
        workOrder.FAILURECODE = failurecode.getText().toString();
        workOrder.PROBLEMCODE = problemcode.getText().toString();
        workOrder.CULEVEL = culevel.getText().toString();
        workOrder.UDZGLIMIT = udzglimit.getText().toString();
        workOrder.UDPLANNUM = udplannum.getText().toString();
        workOrder.SCHEDSTART = schedstart.getText().toString();
        workOrder.SCHEDFINISH = schedfinish.getText().toString();
        workOrder.ACTSTART = actstart.getText().toString();
        workOrder.ACTFINISH = actfinish.getText().toString();
        workOrder.ISSTOPED = isstoped.isChecked() ? 1 : 0;
        workOrder.PMCHGEVALSTART = pmchgevalstart.getText().toString();
        workOrder.PMCHGEVALEND = pmchgevalend.getText().toString();
        if (workOrder.WORKTYPE.equals(Constants.FR)) {
            workOrder.UDRPRRSB = udrprrsb.getText().toString();
            workOrder.UDJGRESULT = udjgresult.getText().toString();
            workOrder.LEAD = lead2.getText().toString();
            workOrder.UDINSPOBY = udinspoby_2.getText().toString();
            workOrder.UDINSPOBY2 = udinspoby2_2.getText().toString();
            workOrder.UDINSPOBY3 = udinspoby3_2.getText().toString();
        } else {
            workOrder.LEAD = lead.getText().toString();
            workOrder.UDINSPOBY = udinspoby.getText().toString();
            workOrder.UDINSPOBY2 = udinspoby2.getText().toString();
            workOrder.UDINSPOBY3 = udinspoby3.getText().toString();
            workOrder.UDRPRRSB = udrprrsb1.getText().toString();
            workOrder.UDJGRESULT = udjgresult1.getText().toString();
        }
        workOrder.UDPROBDESC = udprobdesc.getText().toString();
        workOrder.UDJPNUM = udjpnum.getText().toString();
        workOrder.UDPLSTARTDATE = udplstartdate.getText().toString();
        workOrder.UDPLSTOPDATE = udplstopdate.getText().toString();
        workOrder.UDRLSTARTDATE = udrlstartdate.getText().toString();
        workOrder.UDRLSTOPDATE = udrlstopdate.getText().toString();
        workOrder.DJPLANNUM = djplannum.getText().toString();
        workOrder.DJTYPE = djtype.getText().toString();
        if (workOrder.WORKTYPE.equals(Constants.WS)) {
            workOrder.PCCOMPNUM = pccompnum1.getText().toString();
        } else {
            workOrder.PCCOMPNUM = pccompnum.getText().toString();
        }
        workOrder.WTCODE = wtcode.getText().toString();
        workOrder.ASSETTYPE = assettype.getText().toString();
        workOrder.PERINSPR = perinspr.isChecked() ? 1 : 0;
        workOrder.UDREMARK = udremark.getText().toString();
        workOrder.ISBIGPAR = isbigpar.isChecked() ? 1 : 0;
        workOrder.UDZGMEASURE = udzgmeasure.getText().toString();
        workOrder.PLANNUM = plannum.getText().toString();
        workOrder.PCTYPE = pctype.getText().toString();
        workOrder.UDFJFOL = udfjfol.getText().toString();
        workOrder.PCRESON = pcreson.getText().toString();
        workOrder.JGPLANNUM = jgplannum.getText().toString();
        workOrder.UDJGTYPE = udjgtype.getText().toString();
        workOrder.UDFJAPPNUM = udfjappnum.getText().toString();
        return workOrder;
    }

    private ArrayList<Woactivity> getWoactivityList() {
        ArrayList<Woactivity> woactivities = new ArrayList<>();
        for (int i = 0; i < woactivityList.size(); i++) {
            if (woactivityList.get(i).TYPE != null) {
                woactivities.add(woactivityList.get(i));
            }
        }
        return woactivities;
    }

    private ArrayList<Wpmaterial> getWpmaterialList() {
        ArrayList<Wpmaterial> wpmaterials = new ArrayList<>();
        for (int i = 0; i < wpmaterialLit.size(); i++) {
            if (wpmaterialLit.get(i).TYPE != null) {
                wpmaterials.add(wpmaterialLit.get(i));
            }
        }
        return wpmaterials;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    lead.setText(option.getName());
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby.setText(option.getName());
                    break;
                case 3:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby2.setText(option.getName());
                    break;
                case 4:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby3.setText(option.getName());
                    break;
                case 5:
                    option = (Option) data.getSerializableExtra("option");
                    udjpnum.setText(option.getName());
                    break;
                case 6:
                    option = (Option) data.getSerializableExtra("option");
                    udprojectnum.setText(option.getName());
                    branch.setText(option.getValue1());
                    udlocnum.setText("");
                    break;
                case 7:
                    option = (Option) data.getSerializableExtra("option");
                    udlocnum.setText(option.getName());
                    break;
                case 8:
                    option = (Option) data.getSerializableExtra("option");
                    wtcode.setText(option.getName());
                    break;
                case 9:
                    option = (Option) data.getSerializableExtra("option");
                    udlocation.setText(option.getName());
                    break;
                case 10:
                    option = (Option) data.getSerializableExtra("option");
                    udplannum.setText(option.getName());
                    break;
                case 11:
                    option = (Option) data.getSerializableExtra("option");
                    udrprrsb.setText(option.getName());
                    break;
                case 12:
                    option = (Option) data.getSerializableExtra("option");
                    failurecode.setText(option.getName());
                    failurelist = option.getValue1();
                    problemcode.setText("");
                    break;
                case 13:
                    option = (Option) data.getSerializableExtra("option");
                    problemcode.setText(option.getName());
                    break;
                case 14:
                    option = (Option) data.getSerializableExtra("option");
                    lead2.setText(option.getName());
                    break;
                case 15:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby_2.setText(option.getName());
                    break;
                case 16:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby2_2.setText(option.getName());
                    break;
                case 17:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby3_2.setText(option.getName());
                    break;
                case 1000:
                    if (data.hasExtra("woactivityList") && data.getSerializableExtra("woactivityList") != null) {
                        woactivityList = (ArrayList<Woactivity>) data.getSerializableExtra("woactivityList");
                    }
                    break;
                case 2000:
                    wpmaterialLit = (ArrayList<Wpmaterial>) data.getSerializableExtra("wpmaterialList");
                    break;
//            case 3000:
//                failurereportList = (ArrayList<Failurereport>) data.getSerializableExtra("failurereportList");
//                break;
//            default:
//                break;
            }
        }
    }
}
