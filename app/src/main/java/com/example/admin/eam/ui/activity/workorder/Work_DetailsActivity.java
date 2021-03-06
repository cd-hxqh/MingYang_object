package com.example.admin.eam.ui.activity.workorder;

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

import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.api.JsonUtils;
import com.example.admin.eam.bean.Results;
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.dao.WoactivityDao;
import com.example.admin.eam.dao.WorkOrderDao;
import com.example.admin.eam.dao.WpmaterialDao;
import com.example.admin.eam.model.Failurelist;
import com.example.admin.eam.model.Option;
import com.example.admin.eam.model.WebResult;
import com.example.admin.eam.model.Woactivity;
import com.example.admin.eam.model.WorkOrder;
import com.example.admin.eam.model.Wpmaterial;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.activity.Failurelist1Activity;
import com.example.admin.eam.ui.activity.OptionActivity;
import com.example.admin.eam.ui.activity.PhotoActivity;
import com.example.admin.eam.utils.AccountUtils;
import com.example.admin.eam.utils.ChildClickableLinearLayout;
import com.example.admin.eam.utils.DateSelect;
import com.example.admin.eam.utils.DateTimeSelect;
import com.example.admin.eam.utils.MessageUtils;
import com.example.admin.eam.utils.NetWorkHelper;
import com.example.admin.eam.utils.WorkTypeUtils;
import com.example.admin.eam.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.MaterialDialog2;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


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
    private TextView workplanText;
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
    /**
     * 查看故障原因措施*
     */
    private LinearLayout failureLinearLayout;

    private List<View> views;

    private WorkOrder workOrder;
    private ChildClickableLinearLayout childClickableLinearLayout;
    private LinearLayout work_numlayout;
    private TextView wonum;//工单号
    private TextView description;//工单描述
    private LinearLayout djplannumlayout;
    private TextView djplannum;//定检计划编号
    private LinearLayout description_layout;
    private TextView branch;//中心
    private TextView udprojectnum;//项目
    private TextView proname;//项目名称
    private LinearLayout udlocnumlayout;
    private TextView udlocnum;//机位号
    private LinearLayout udlocationlayout;
    private TextView udlocation;//位置
    private TextView locdesc;//位置描述
    private LinearLayout leadlayout;
    private TextView leadText;
    private TextView lead;//运行组/维护组工程师
    private LinearLayout udproreslayout;
    private TextView udprores;//终验收工单 项目负责人
    private TextView udstatus;//状态
    private TextView createby;//创建人
    private TextView createdate;//创建时间
    private LinearLayout defultlayout;
    private TextView failurecode;//故障类
    private TextView problemcode;//问题原因
    private TextView gzwtdesc;//问题原因描述
    private TextView udgzdj;//故障等级
    private TextView udgztype;//故障类型
    private TextView udrprrsb;//提报人
    private TextView udzglimit;//提报时间
    private LinearLayout udplannumlayout;
    private TextView udplannum;//终验收计划号
    private LinearLayout udreportnumlayout;
    private TextView udreportnum;//故障提报单号
    private TextView schedstart;//计划开始时间
    private TextView schedfinish;//计划结束时间
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际结束时间
    private CheckBox isstoped;//是否停机
    private TextView udstoptime;//故障开始时间
    private TextView udrestarttime;//故障恢复时间
    private TextView udjgresult;//累计时间
    private EditText udremark2;//没有编码的物料
    private EditText udprobdesc;//故障隐患描述
    private TextView udcond2;//需要其他单据
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
    private TextView udinspoby4;//定检人员4
    private TextView udinspoby5;//定检人员5
    private TextView udinspoby6;//定检人员6
    private TextView djtype;//定检类型
    private TextView pccompnum1;//计划定检风机台数
    private LinearLayout lastlayout;
    private TextView wtcode;//风机型号
    private LinearLayout udcond1layout;
    private TextView udcond1;//需要其他单据
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
    private EditText realcomp;//实际完成台数
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
    private LinearLayout udrprrsb1layout;
    private TextView udrprrsb1;//负责人
    private LinearLayout inspo2layout;//故障工单人员信息
    private LinearLayout udwplayout;//
    private TextView udwptype;//人员类型
    private EditText udwp;//承包人员
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
        checkWFPRequired(Work_DetailsActivity.this,WorkTypeUtils.getAppId(workOrder.WORKTYPE),"WORKORDER",workOrder.UDSTATUS);
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

        childClickableLinearLayout = (ChildClickableLinearLayout) findViewById(R.id.custom_layout);
        work_numlayout = (LinearLayout) findViewById(R.id.work_wonum_layout);
        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (TextView) findViewById(R.id.work_describe);
        description_layout = (LinearLayout) findViewById(R.id.work_describe_layout);
        branch = (TextView) findViewById(R.id.work_branch);
        udprojectnum = (TextView) findViewById(R.id.work_udprojectnum);
        proname = (TextView) findViewById(R.id.work_udprojectdesc);
        udlocnumlayout = (LinearLayout) findViewById(R.id.work_udlocnum_layout);
        udlocnum = (TextView) findViewById(R.id.work_udlocnum);
        udlocationlayout = (LinearLayout) findViewById(R.id.work_udlocation_layout);
        udlocation = (TextView) findViewById(R.id.work_udlocation);
        locdesc = (TextView) findViewById(R.id.work_locdesc);
        leadlayout = (LinearLayout) findViewById(R.id.work_lead_layout);
        leadText = (TextView) findViewById(R.id.work_lead_text);
        lead = (TextView) findViewById(R.id.work_lead);
        udproreslayout = (LinearLayout) findViewById(R.id.work_udprores_layout);
        udprores = (TextView) findViewById(R.id.work_udprores);
        udstatus = (TextView) findViewById(R.id.work_status);
        createby = (TextView) findViewById(R.id.work_createby);
        createdate = (TextView) findViewById(R.id.work_createdate);
        defultlayout = (LinearLayout) findViewById(R.id.work_defultlayout);
        failurecode = (TextView) findViewById(R.id.work_failurecode);
        problemcode = (TextView) findViewById(R.id.work_problemcode);
        gzwtdesc = (TextView) findViewById(R.id.work_gzwtdesc);
        udgzdj = (TextView) findViewById(R.id.work_culevel);
        udgztype = (TextView) findViewById(R.id.work_udgztype);
        udrprrsb = (TextView) findViewById(R.id.work_udrprrsb);
        udzglimit = (TextView) findViewById(R.id.work_udzglimit);
        udplannumlayout = (LinearLayout) findViewById(R.id.work_udplannum_layout);
        udplannum = (TextView) findViewById(R.id.work_udplannum);
        udreportnumlayout = (LinearLayout) findViewById(R.id.work_udreportnum_layout);
        udreportnum = (TextView) findViewById(R.id.work_udreportnum);
        schedstart = (TextView) findViewById(R.id.work_schedstart);
        schedfinish = (TextView) findViewById(R.id.work_schedfinish);
        actstart = (TextView) findViewById(R.id.work_actstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        isstoped = (CheckBox) findViewById(R.id.work_isstoped);
        udstoptime = (TextView) findViewById(R.id.work_pmchgevalstart);
        udrestarttime = (TextView) findViewById(R.id.work_pmchgevalend);
        udjgresult = (TextView) findViewById(R.id.work_udjgresult);
        udremark2 = (EditText) findViewById(R.id.work_udremark2);
        udprobdesc = (EditText) findViewById(R.id.work_udprobdesc);
        udcond2 = (TextView) findViewById(R.id.work_udcond2);
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
        udinspoby4 = (TextView) findViewById(R.id.work_udinspoby4);
        udinspoby5 = (TextView) findViewById(R.id.work_udinspoby5);
        udinspoby6 = (TextView) findViewById(R.id.work_udinspoby6);
        djplannumlayout = (LinearLayout) findViewById(R.id.work_djplannum_layout);
        djplannum = (TextView) findViewById(R.id.work_djplannum);
        djtype = (TextView) findViewById(R.id.work_djtype);
        pccompnum1 = (TextView) findViewById(R.id.work_pccompnum1);
        lastlayout = (LinearLayout) findViewById(R.id.work_lastlayout);
        wtcode = (TextView) findViewById(R.id.work_wtcode);
        udcond1layout = (LinearLayout) findViewById(R.id.work_udcond1_layout);
        udcond1 = (TextView) findViewById(R.id.work_udcond1);
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
        realcomp = (EditText) findViewById(R.id.work_realcomp);
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
        udrprrsb1layout = (LinearLayout) findViewById(R.id.work_udrprrsb1_layout);
        udrprrsb1 = (TextView) findViewById(R.id.work_udrprrsb1);
        inspo2layout = (LinearLayout) findViewById(R.id.work_inspo2layout);
        udwplayout = (LinearLayout) findViewById(R.id.work_udwplayout);
        udwptype = (TextView) findViewById(R.id.work_udwptype);
        udwp = (EditText) findViewById(R.id.work_udwp);
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
        proname.setText(workOrder.PRONAME);
        udlocnum.setText(workOrder.UDLOCNUM);
        udlocation.setText(workOrder.UDLOCATION);
        locdesc.setText(workOrder.LOCDESC);
        udstatus.setText(workOrder.UDSTATUS);
        createby.setText(workOrder.CREATENAME);
        createdate.setText(workOrder.CREATEDATE);
        failurecode.setText(workOrder.GZLDESC);
        problemcode.setText(workOrder.UDFAILURECODE);
        gzwtdesc.setText(workOrder.GZWTDESC);
        udgzdj.setText(workOrder.UDGZDJ);
        udgztype.setText(workOrder.UDGZTYPE);

        udzglimit.setText(workOrder.UDZGLIMIT);
        udplannum.setText(workOrder.UDPLANNUM);
        udreportnum.setText(workOrder.UDREPORTNUM);
        schedstart.setText(workOrder.SCHEDSTART);
        schedfinish.setText(workOrder.SCHEDFINISH);
        actstart.setText(workOrder.ACTSTART);
        actfinish.setText(workOrder.ACTFINISH);
        isstoped.setChecked(workOrder.ISSTOPED != 0);
        udstoptime.setText(workOrder.UDSTOPTIME);
        udrestarttime.setText(workOrder.UDRESTARTTIME);
        if (workOrder.WORKTYPE.equals(Constants.FR)) {
            lead2.setText(workOrder.LEADNAME);
            udinspoby_2.setText(workOrder.NAME1);
            udinspoby2_2.setText(workOrder.NAME2);
            udinspoby3_2.setText(workOrder.NAME3);
            udrprrsb.setText(workOrder.UDRPRRSBNAME);
            udjgresult.setText(workOrder.UDJGRESULT);
            udremark2.setText(workOrder.UDREMARK);
        } else {
            lead.setText(workOrder.LEADNAME);
            udprores.setText(workOrder.UDPRORESNAME);
            udinspoby.setText(workOrder.NAME1);
            udinspoby2.setText(workOrder.NAME2);
            udinspoby3.setText(workOrder.NAME3);
            udinspoby4.setText(workOrder.NAME4);
            udinspoby5.setText(workOrder.NAME5);
            udinspoby6.setText(workOrder.NAME6);
            udrprrsb1.setText(workOrder.UDRPRRSBNAME);
            udjgresult1.setText(workOrder.UDJGRESULT);
            udremark.setText(workOrder.UDREMARK);
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
        realcomp.setText(workOrder.REALCOMP);
        wtcode.setText(workOrder.WTCODE);
        udcond1.setText(workOrder.UDCOND1);
        assettype.setText(workOrder.ASSETTYPE);
        perinspr.setChecked(workOrder.PERINSPR != 0);
        isbigpar.setChecked(workOrder.ISBIGPAR != 0);
        udzgmeasure.setText(workOrder.UDZGMEASURE);
        plannum.setText(workOrder.PLANNUM);
        pctype.setText(workOrder.PCTYPE);
        udfjfol.setText(workOrder.UDFJFOL);
        pcreson.setText(workOrder.PCRESON);

        jgplannum.setText(workOrder.JGPLANNUM);
        udjgtype.setText(workOrder.UDJGTYPE);
        udfjappnum.setText(workOrder.UDFJAPPNUM);

        udwptype.setText(workOrder.UDWPTYPE);
        udwp.setText(workOrder.UDWP);
        udcond2.setText(workOrder.UDCOND2);

        if (workOrder.UDSTATUS.equals(Constants.STATUS1)) {
            pctype.setOnClickListener(new NormalListDialogOnClickListener(pctype));
        }
        djtype.setOnClickListener(new NormalListDialogOnClickListener(djtype));
        udjgtype.setOnClickListener(new NormalListDialogOnClickListener(udjgtype));
        udwptype.setOnClickListener(new NormalListDialogOnClickListener(udwptype));
        udcond2.setOnClickListener(new NormalListDialogOnClickListener(udcond2));
        udcond1.setOnClickListener(new NormalListDialogOnClickListener(udcond1));
//        culevel.setOnClickListener(new NormalListDialogOnClickListener(culevel));
        lead.setOnClickListener(new LayoutOnClickListener(1, Constants.PERSONCODE));
        udinspoby.setOnClickListener(new LayoutOnClickListener(2, Constants.PERSONCODE));
        udinspoby2.setOnClickListener(new LayoutOnClickListener(3, Constants.PERSONCODE));
        udinspoby3.setOnClickListener(new LayoutOnClickListener(4, Constants.PERSONCODE));
        udinspoby4.setOnClickListener(new LayoutOnClickListener(20, Constants.PERSONCODE));
        udinspoby5.setOnClickListener(new LayoutOnClickListener(21, Constants.PERSONCODE));
        udinspoby6.setOnClickListener(new LayoutOnClickListener(22, Constants.PERSONCODE));
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
        lead2.setOnClickListener(new LayoutOnClickListener(14, Constants.PERSONCODE));
        udinspoby_2.setOnClickListener(new LayoutOnClickListener(15, Constants.PERSONCODE));
        udinspoby2_2.setOnClickListener(new LayoutOnClickListener(16, Constants.PERSONCODE));
        udinspoby3_2.setOnClickListener(new LayoutOnClickListener(17, Constants.PERSONCODE));
        udprores.setOnClickListener(new LayoutOnClickListener(18, Constants.PERSONCODE));
        djplannum.setOnClickListener(new LayoutOnClickListener(19,Constants.REGULARINSPECTIONPLANLINKCODE));
        udplstartdate.setOnClickListener(new DateChecked(udplstartdate));
        udplstopdate.setOnClickListener(new DateChecked(udplstopdate));
        udrlstartdate.setOnClickListener(new DateChecked(udrlstartdate));
        udrlstopdate.setOnClickListener(new DateChecked(udrlstopdate));
        udzglimit.setOnClickListener(new DateTimeOnClickListener(udzglimit));
        schedstart.setOnClickListener(new DateTimeOnClickListener(schedstart));
        schedfinish.setOnClickListener(new DateTimeOnClickListener(schedfinish));
        actstart.setOnClickListener(new DateTimeOnClickListener(actstart));
        actfinish.setOnClickListener(new DateTimeOnClickListener(actfinish));
        udstoptime.setOnClickListener(new DateTimeOnClickListener(udstoptime));
        udrestarttime.setOnClickListener(new DateTimeOnClickListener(udrestarttime));

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

        if (workOrder.id != 0) {
            getLocationData(workOrder.id);
        }
        getFailureList();
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
        }else if (textView == udwptype) {
            types = getResources().getStringArray(R.array.udwptype_array);
        } else if (textView == udcond2) {
            types = getResources().getStringArray(R.array.udcond2_array);
        } else if (textView == udcond1) {
            types = getResources().getStringArray(R.array.udcond1_array);
        }
//        else if (textView == culevel) {
//            types = getResources().getStringArray(R.array.culevel_array);
//        }
        for (String type : types) {
            mMenuItems.add(new DialogMenuItem(type, 0));
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !udstoptime.getText().toString().equals("") && !udrestarttime.getText().toString().equals("")
                && udjgresult.getText().toString().equals("")) {
            udjgresult.setText(getTime(udstoptime.getText().toString(), udrestarttime.getText().toString()));
        }
    }

    //计算时间差
    private String getTime(String time1, String time2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = null;
        java.util.Date date = null;
        try {
            now = df.parse(time1);
            date = df.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l = date.getTime() - now.getTime();
        long day = l / (24 * 60 * 60 * 1000);//天
        long hour = (l / (60 * 60 * 1000) - day * 24);//小时
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);//分
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);//秒
        return "累积停机" + day + "天" + hour + "小时" + min + "分" + s + "秒";
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
                udproreslayout.setVisibility(View.GONE);
                djplannumlayout.setVisibility(View.GONE);

                SetClick_FR();
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
                lastlayout.setVisibility(View.GONE);
                udreportnumlayout.setVisibility(View.GONE);
                djplannumlayout.setVisibility(View.GONE);

                SetClick_AA();
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
                udproreslayout.setVisibility(View.GONE);
                udreportnumlayout.setVisibility(View.GONE);
                djplannumlayout.setVisibility(View.GONE);

                SetClick_SP();
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
                udrprrsb1layout.setVisibility(View.GONE);
                udproreslayout.setVisibility(View.GONE);
                udreportnumlayout.setVisibility(View.GONE);
                djplannumlayout.setVisibility(View.GONE);

                SetClick_TP();
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
                udproreslayout.setVisibility(View.GONE);
                udreportnumlayout.setVisibility(View.GONE);

                SetClick_WS();
                break;
            default:
                break;
        }
    }

    private void SetClick_FR() {//故障工单
        switch (workOrder.UDSTATUS) {
            case "新建":
//                udprobdesc.setBackgroundColor(getResources().getColor(R.color.light_gray2));
                udrestarttime.setEnabled(false);
//                udrestarttime.setBackgroundColor(getResources().getColor(R.color.light_gray2));
                actstart.setEnabled(false);
//                actstart.setBackgroundColor(getResources().getColor(R.color.light_gray2));
                actfinish.setEnabled(false);
//                actfinish.setBackgroundColor(getResources().getColor(R.color.light_gray2));
                break;
            case "进行中":
            case "已反馈":
            case "物料已申请":
            case "驳回":
                udstoptime.setEnabled(false);
                udprojectnum.setEnabled(false);
                udlocnum.setEnabled(false);
                udlocation.setEnabled(false);
                udplannum.setEnabled(false);
                failurecode.setEnabled(false);
                problemcode.setEnabled(false);
//            culevel.setClickable(false);
                udrprrsb.setEnabled(false);
                udzglimit.setEnabled(false);
                lead2.setEnabled(false);
                udinspoby_2.setEnabled(false);
                udinspoby2_2.setEnabled(false);
                udinspoby3_2.setEnabled(false);
                schedstart.setEnabled(false);
                schedfinish.setEnabled(false);
                udprobdesc.setEnabled(false);
                udremark2.setEnabled(false);
                break;
            default:
                childClickableLinearLayout.setChildClickable(false);
                break;
        }
    }

    private void SetClick_AA() {//终验收工单
        switch (workOrder.UDSTATUS) {
            case "新建":
                udrlstartdate.setEnabled(false);
                udrlstopdate.setEnabled(false);
                break;
            case "已完成":
                udprojectnum.setEnabled(false);
                lead.setEnabled(false);
                udlocnum.setEnabled(false);
                udplannum.setEnabled(false);
                udprores.setEnabled(false);
                break;
            default:
                childClickableLinearLayout.setChildClickable(false);
                break;
        }
    }

    private void SetClick_SP() {//排查
        switch (workOrder.UDSTATUS) {
            case "新建":
                udjgresult1.setEnabled(false);
                perinspr.setClickable(false);
                realcomp.setEnabled(false);
                udzgmeasure.setEnabled(false);
                udremark.setEnabled(false);
                udrlstartdate.setEnabled(false);
                udrlstopdate.setEnabled(false);
                plannum.setEnabled(false);
                break;
            case "待汇报":
            case "驳回":
                udprojectnum.setEnabled(false);
                udjpnum.setEnabled(false);
                udplstartdate.setEnabled(false);
                udplstopdate.setEnabled(false);
                lead.setEnabled(false);
                pccompnum.setEnabled(false);
                wtcode.setEnabled(false);
                pctype.setEnabled(false);
                pcreson.setEnabled(false);
                udzgmeasure.setEnabled(false);
                udremark.setEnabled(false);
                udrlstartdate.setEnabled(false);
                udrlstopdate.setEnabled(false);
                break;
            case "已汇报":
                udprojectnum.setEnabled(false);
                udjpnum.setEnabled(false);
                udplstartdate.setEnabled(false);
                udplstopdate.setEnabled(false);
                lead.setEnabled(false);
                pccompnum.setEnabled(false);
                wtcode.setEnabled(false);
                pctype.setEnabled(false);
                pcreson.setEnabled(false);
                udjgresult1.setEnabled(false);
                perinspr.setClickable(false);
                realcomp.setEnabled(false);
                udzgmeasure.setEnabled(false);
                udremark.setEnabled(false);
                udrlstartdate.setEnabled(false);
                udrlstopdate.setEnabled(false);
                udrlstartdate.setEnabled(false);
                udrlstopdate.setEnabled(false);
                break;
            default:
                childClickableLinearLayout.setChildClickable(false);
                break;
        }
    }

    private void SetClick_TP() {//技改工单
        switch (workOrder.UDSTATUS) {
            case "新建":
                udrlstartdate.setEnabled(false);
                udrlstopdate.setEnabled(false);
                perinspr.setClickable(false);
                break;
            case "驳回":
            case "进行中":
                udprojectnum.setEnabled(false);
                udjpnum.setEnabled(false);
                pcreson.setEnabled(false);
                lead.setEnabled(false);
                udplstartdate.setEnabled(false);
                udplstopdate.setEnabled(false);
                jgplannum.setEnabled(false);
                perinspr.setClickable(false);
                udjgtype.setEnabled(false);
                wtcode.setEnabled(false);
                pccompnum.setEnabled(false);
                break;
            default:
                childClickableLinearLayout.setChildClickable(false);
                break;
        }
    }

    private void SetClick_WS() {//定检工单
        switch (workOrder.UDSTATUS) {
            case "新建":
                udrlstartdate.setEnabled(false);
                udrlstopdate.setEnabled(false);
                perinspr.setClickable(false);
                break;
            case "进行中":
            case "已反馈":
            case "物料已申请":
            case "驳回":
                udprojectnum.setEnabled(false);
                udjpnum.setEnabled(false);
                djplannum.setEnabled(false);
                udinspoby.setEnabled(false);
                udplstartdate.setEnabled(false);
                udplstopdate.setEnabled(false);
                djtype.setEnabled(false);
                lead.setEnabled(false);
                pccompnum1.setEnabled(false);
                perinspr.setClickable(false);
                wtcode.setEnabled(false);
                udremark.setEnabled(false);
                break;
            default:
                childClickableLinearLayout.setChildClickable(false);
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
                failureLinearLayout.setVisibility(View.GONE);
                break;
//            case "DC"://调试工单
//
//                break;
            case "SP"://排查工单
                wpmaterialLinearLayout.setVisibility(View.GONE);
                failureLinearLayout.setVisibility(View.GONE);
                break;
            case "TP"://技改工单
                wpmaterialLinearLayout.setVisibility(View.GONE);
                failureLinearLayout.setVisibility(View.GONE);
                break;
            case "WS"://定检工单
                wpmaterialLinearLayout.setVisibility(View.GONE);
                failureLinearLayout.setVisibility(View.GONE);
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
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_plan_id);
        workplanText = (TextView) contentView.findViewById(R.id.workplan_text);
        wpmaterialLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_wpmaterial_id);
//        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);
        flowerLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_flower_id);
        commitLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_commit_id);
        failureLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_failure_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        flowerLinearLayout.setOnClickListener(flowerOnClickListener);
        wpmaterialLinearLayout.setOnClickListener(wpmaterialOnClickListener);
        commitLinearLayout.setOnClickListener(commitOnClickListener);
        failureLinearLayout.setOnClickListener(failureOnClickListener);

        if (!workOrder.WORKTYPE.equals(Constants.FR)) {
            workplanText.setText("工作详情");
        }
        decisionLayout();

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            if (workOrder.WORKTYPE.equals(Constants.SP)||workOrder.WORKTYPE.equals(Constants.TP)
//                    ||workOrder.WORKTYPE.equals(Constants.WS)) {
//            if (workOrder.WORKTYPE.equals(Constants.FR) || workOrder.WORKTYPE.equals(Constants.AA)
//                    || (!workOrder.WORKTYPE.equals(Constants.FR) && !workOrder.WORKTYPE.equals(Constants.AA) && !udjpnum.getText().toString().equals(""))) {
                Intent intent = new Intent(Work_DetailsActivity.this, Work_WoactivityActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workOrder", workOrder);
                bundle.putSerializable("woactivityList", woactivityList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1000);
                popupWindow.dismiss();
//            } else {
//                Toast.makeText(Work_DetailsActivity.this, "请选择计划标准", Toast.LENGTH_SHORT).show();
//            }
//            }
        }
    };

    private View.OnClickListener flowerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (workOrder.UDSTATUS.equals("新建")) {

                if (checkRequiredFieldcompeletion()) {

                    MaterialDialogOneBtn();
                }

            } else {

                if (checkRequiredFieldcompeletion()) {

                    EditDialog();
                }
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
            popupWindow.dismiss();
            Intent intent = new Intent(Work_DetailsActivity.this, PhotoActivity.class);
            intent.putExtra("ownertable", "WORKORDER");
            intent.putExtra("ownerid", workOrder.getWORKORDERID() + "");
            startActivityForResult(intent, 0);
        }
    };

    private View.OnClickListener failureOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (failurecode.getText().equals("")) {
                Toast.makeText(Work_DetailsActivity.this, "请选择故障类", Toast.LENGTH_SHORT).show();
            } else if (problemcode.getText().equals("")) {
                Toast.makeText(Work_DetailsActivity.this, "请选择问题原因", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Work_DetailsActivity.this, Failurelist1Activity.class);
                intent.putExtra("failurecode", workOrder.UDFAILURECODE);
                startActivityForResult(intent, 0);
            }
            popupWindow.dismiss();
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
                        showProgressDialog("数据提交中...");
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });
    }

    //检查字段
    private boolean isOK() {
        switch (workOrder.WORKTYPE) {
            case "FR"://故障工单
                if (udprojectnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udlocnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入机位号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (failurecode.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入故障类", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (problemcode.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入故障问题", Toast.LENGTH_SHORT).show();
                    return false;
                }
//                if (culevel.getText().toString().equals("")) {
//                    Toast.makeText(Work_DetailsActivity.this, "请选择故障等级", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
                if (udrprrsb.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入提报人", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udzglimit.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入提报时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (schedstart.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划开始时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (schedfinish.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划完成时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (lead2.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入维护/运行组长", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udinspoby_2.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入维护/运行人员", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            case "AA"://终验收工单
                if (udprojectnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (lead.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入负责人", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udlocnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入机位号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            case "SP"://排查工单
                if (udprojectnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
                    return false;
                }
//                if (udjpnum.getText().toString().equals("")) {
//                    Toast.makeText(Work_DetailsActivity.this, "请输入排查标准", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
                if (udplstartdate.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划开始时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udplstopdate.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划完成时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (lead.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入排查负责人", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (wtcode.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入风机型号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (pccompnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入排查完成台数", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (pctype.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入排查类型", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (pcreson.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入排查原因", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            case "TP"://技改工单
                if (udprojectnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
                    return false;
                }
//                if (udjpnum.getText().toString().equals("")) {
//                    Toast.makeText(Work_DetailsActivity.this, "请输入技改标准", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
                if (pcreson.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入技改原因", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (lead.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入技改负责人", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udplstartdate.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划开始时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udplstopdate.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划完成时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udjgtype.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入技改类型", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (wtcode.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入风机型号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (pccompnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入技改风机台数", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            case "WS"://定检工单
                if (udprojectnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入项目编号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udlocnum.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入机位号", Toast.LENGTH_SHORT).show();
                    return false;
                }
//                if (udjpnum.getText().toString().equals("")) {
//                    Toast.makeText(Work_DetailsActivity.this, "请输入定检标准编号", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
                if (udplstartdate.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划开始时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (udplstopdate.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入计划完成时间", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (lead.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入定检组长", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (djtype.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入定检类型", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (wtcode.getText().toString().equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "请输入风机型号", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
        }
        return true;
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        if (AccountUtils.getIsOffLine(Work_DetailsActivity.this) || NetWorkHelper.isNetwork(Work_DetailsActivity.this)) {
            MessageUtils.showMiddleToast(Work_DetailsActivity.this, "暂无网络,现离线保存数据!");
            saveWorkOrder();
            closeProgressDialog();
        } else {
            if (isOK()) {
                String updataInfo = null;
//            if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), getWoactivityList(), getWpmaterialList());
//            } else if (workOrder.status.equals(Constants.APPROVALED)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
//            }
                final String finalUpdataInfo = updataInfo;
                new AsyncTask<String, String, WebResult>() {
                    @Override
                    protected WebResult doInBackground(String... strings) {
                        WebResult reviseresult = AndroidClientService.UpdateWO(Work_DetailsActivity.this, finalUpdataInfo,
                                "WORKORDER", "WONUM", workOrder.WONUM, Constants.WORK_URL);
                        return reviseresult;
                    }

                    @Override
                    protected void onPostExecute(WebResult workResult) {
                        super.onPostExecute(workResult);
                        if (workResult == null || workResult.errorMsg == null) {
                            Toast.makeText(Work_DetailsActivity.this, "修改工单失败", Toast.LENGTH_SHORT).show();
                        } else if (workResult.errorMsg.equals("成功")) {
                            Toast.makeText(Work_DetailsActivity.this, "修改工单成功", Toast.LENGTH_SHORT).show();
                            setResult(100);
                            finish();
                        } else {
                            Toast.makeText(Work_DetailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                        }
                        closeProgressDialog();
                    }
                }.execute();
                //}else {
                closeProgressDialog();
            }else {
                closeProgressDialog();
            }
        }
    }

    private void saveWorkOrder() {
        WorkOrder workOrder = getWorkOrder();
        workOrder.belong = AccountUtils.getpersonId(Work_DetailsActivity.this);
//        workOrder.ishistory = true;
        new WorkOrderDao(Work_DetailsActivity.this).update(workOrder);
        int id = workOrder.id;
        if (id != 0) {
            if (woactivityList.size() != 0) {
                for (Woactivity woactivity : woactivityList) {
                    woactivity.belongid = id;
                }
                if (new WoactivityDao(Work_DetailsActivity.this).queryByWonum(workOrder.WONUM).size() > 0) {//删除默认保存的记录，防止重复
                    new WoactivityDao(Work_DetailsActivity.this).deleteList(new WoactivityDao(Work_DetailsActivity.this).queryByWonum(workOrder.WONUM));
                }
                new WoactivityDao(Work_DetailsActivity.this).create(woactivityList);
            }
            if (wpmaterialLit.size() != 0) {
                for (Wpmaterial wplabor : wpmaterialLit) {
                    wplabor.belongid = id;
                }
                if (new WpmaterialDao(Work_DetailsActivity.this).queryByWonum(workOrder.WONUM).size() > 0) {//删除默认保存的记录，防止重复
                    new WpmaterialDao(Work_DetailsActivity.this).deleteList(new WpmaterialDao(Work_DetailsActivity.this).queryByWonum(workOrder.WONUM));
                }
                new WpmaterialDao(Work_DetailsActivity.this).create(wpmaterialLit);
            }
        }
    }

    //如果为历史数据，则获取本地子表信息
    private void getLocationData(int id) {
        woactivityList = (ArrayList<Woactivity>) new WoactivityDao(Work_DetailsActivity.this).queryById(id);
        if (woactivityList == null || woactivityList.size() == 0) {//如果没有修过记录，则查找默认保存记录
            woactivityList = (ArrayList<Woactivity>) new WoactivityDao(Work_DetailsActivity.this).queryByWonum(workOrder.WONUM);
        }
        wpmaterialLit = (ArrayList<Wpmaterial>) new WpmaterialDao(Work_DetailsActivity.this).queryById(id);
        if (wpmaterialLit == null || wpmaterialLit.size() == 0) {
            wpmaterialLit = (ArrayList<Wpmaterial>) new WpmaterialDao(Work_DetailsActivity.this).queryByWonum(workOrder.WONUM);
        }
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
        final MaterialDialog2 dialog = new MaterialDialog2(Work_DetailsActivity.this);
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

//    private void MaterialDialogOneBtn1() {//审批工作流
//        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.isTitleShow(false)//
//                .btnNum(2)
//                .content("是否填写输入意见")//
//                .btnText("是", "否，直接提交")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)
//                .show();
//
//        dialog.setOnBtnClickL(
//                new OnBtnClickL() {//是
//                    @Override
//                    public void onBtnClick() {
//                        EditDialog(true);
//                        dialog.dismiss();
//                    }
//                },
//                new OnBtnClickL() {//否
//                    @Override
//                    public void onBtnClick() {
//                        wfgoon("1", "");
//                        dialog.dismiss();
//                    }
//                }
//        );
//    }


    private void EditDialog() {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(true)//
                .title("审批工作流")
                .btnNum(3)
                .content("通过")//
                .btnText("取消", "通过", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        dialog.dismiss();
                    }
                },
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
                        wfgoon("0", text.equals("通过") ? "不通过" : text);
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
                WebResult result = AndroidClientService.startwf(Work_DetailsActivity.this, WorkTypeUtils.getProcessname(workOrder.WORKTYPE), "WORKORDER", workOrder.WONUM, "WONUM",AccountUtils.getpersonId(Work_DetailsActivity.this));

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
     *
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
                        WorkTypeUtils.getProcessname(workOrder.WORKTYPE), "WORKORDER", workOrder.WORKORDERID+"", "WORKORDERID", zx, desc,
                        AccountUtils.getpersonId(Work_DetailsActivity.this));

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(WebResult s) {
                super.onPostExecute(s);
                if (s == null || s.wonum == null || s.errorMsg == null) {
                    Toast.makeText(Work_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else if (s.wonum.equals(workOrder.WORKORDERID+"") && s.errorMsg != null) {
                    udstatus.setText(s.errorMsg);
                    workOrder.UDSTATUS = s.errorMsg;
                    Toast.makeText(Work_DetailsActivity.this, "审批成功", Toast.LENGTH_SHORT).show();
                } else {
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
        workOrder.PRONAME = proname.getText().toString();
        workOrder.UDLOCNUM = udlocnum.getText().toString();
        workOrder.UDLOCATION = udlocation.getText().toString();
        workOrder.LOCDESC = locdesc.getText().toString();
        workOrder.UDSTATUS = udstatus.getText().toString();
//        workOrder.CREATEBY = createby.getText().toString();
        workOrder.CREATEDATE = createdate.getText().toString();
//        workOrder.FAILURECODE = failurecode.getText().toString();
//        workOrder.PROBLEMCODE = problemcode.getText().toString();
//        workOrder.CULEVEL = culevel.getText().toString();
        workOrder.UDZGLIMIT = udzglimit.getText().toString();
        workOrder.UDPLANNUM = udplannum.getText().toString();
        workOrder.UDREPORTNUM = udreportnum.getText().toString();
        workOrder.SCHEDSTART = schedstart.getText().toString();
        workOrder.SCHEDFINISH = schedfinish.getText().toString();
        workOrder.ACTSTART = actstart.getText().toString();
        workOrder.ACTFINISH = actfinish.getText().toString();
        workOrder.ISSTOPED = isstoped.isChecked() ? 1 : 0;
        workOrder.UDSTOPTIME = udstoptime.getText().toString();
        workOrder.UDRESTARTTIME = udrestarttime.getText().toString();
        if (workOrder.WORKTYPE.equals(Constants.FR)) {
//            workOrder.UDRPRRSB = udrprrsb.getText().toString();
            workOrder.UDJGRESULT = udjgresult.getText().toString();
//            workOrder.LEAD = lead2.getText().toString();
//            workOrder.UDINSPOBY = udinspoby_2.getText().toString();
//            workOrder.UDINSPOBY2 = udinspoby2_2.getText().toString();
//            workOrder.UDINSPOBY3 = udinspoby3_2.getText().toString();
            workOrder.UDREMARK = udremark2.getText().toString();
        } else {
//            workOrder.LEAD = lead.getText().toString();
//            workOrder.UDINSPOBY = udinspoby.getText().toString();
//            workOrder.UDINSPOBY2 = udinspoby2.getText().toString();
//            workOrder.UDINSPOBY3 = udinspoby3.getText().toString();
//            workOrder.UDRPRRSB = udrprrsb1.getText().toString();
            workOrder.UDJGRESULT = udjgresult1.getText().toString();
            workOrder.UDREMARK = udremark.getText().toString();
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

        workOrder.ISBIGPAR = isbigpar.isChecked() ? 1 : 0;
        workOrder.UDZGMEASURE = udzgmeasure.getText().toString();
        workOrder.PLANNUM = plannum.getText().toString();
        workOrder.PCTYPE = pctype.getText().toString();
        workOrder.UDFJFOL = udfjfol.getText().toString();
        workOrder.PCRESON = pcreson.getText().toString();
        workOrder.JGPLANNUM = jgplannum.getText().toString();
        workOrder.UDJGTYPE = udjgtype.getText().toString();
        workOrder.UDFJAPPNUM = udfjappnum.getText().toString();
        workOrder.UDWP = udwp.getText().toString();
        workOrder.UDWPTYPE = udwptype.getText().toString();
        workOrder.UDCOND1 = udcond1.getText().toString();
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

    private void getFailureList() {//得到故障问题failurelist
        HttpManager.getDataPagingInfo(this, HttpManager.getFailurelist3Url(1, 20, workOrder.FAILURECODE), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                if (results.getResultlist() != null) {
                    ArrayList<Failurelist> items = JsonUtils.parsingFailurelist(results.getResultlist());
                    if (items != null && items.size() == 1) {//问题原因
                        failurelist = items.get(0).FAILURELIST + "";
                    }
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    lead.setText(option.getDesc());
                    workOrder.LEADNAME = option.getDesc();
                    workOrder.LEAD = option.getName();
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby.setText(option.getDesc());
                    workOrder.NAME1 = option.getDesc();
                    workOrder.UDINSPOBY = option.getName();
                    break;
                case 3:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby2.setText(option.getDesc());
                    workOrder.NAME2 = option.getDesc();
                    workOrder.UDINSPOBY2 = option.getName();
                    break;
                case 4:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby3.setText(option.getDesc());
                    workOrder.NAME3 = option.getDesc();
                    workOrder.UDINSPOBY3 = option.getName();
                    break;
                case 5:
                    option = (Option) data.getSerializableExtra("option");
                    udjpnum.setText(option.getName());
                    break;
                case 6:
                    option = (Option) data.getSerializableExtra("option");
                    udprojectnum.setText(option.getName());
                    proname.setText(option.getDesc());
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
                    locdesc.setText(option.getDesc());
                    break;
                case 10:
                    option = (Option) data.getSerializableExtra("option");
                    udplannum.setText(option.getName());
                    break;
                case 11:
                    option = (Option) data.getSerializableExtra("option");
                    udrprrsb.setText(option.getDesc());
                    workOrder.UDRPRRSBNAME = option.getDesc();
                    workOrder.UDRPRRSB = option.getName();
                    break;
                case 12:
                    option = (Option) data.getSerializableExtra("option");
                    failurecode.setText(option.getDesc());
                    workOrder.GZLDESC = option.getDesc();
                    workOrder.FAILURECODE = option.getName();
                    failurelist = option.getValue1();
                    problemcode.setText("");
                    gzwtdesc.setText("");
                    break;
                case 13:
                    option = (Option) data.getSerializableExtra("option");
                    problemcode.setText(option.getName());
                    gzwtdesc.setText(option.getDesc());
                    workOrder.GZWTDESC = option.getDesc();
                    workOrder.UDFAILURECODE = option.getName();
                    break;
                case 14:
                    option = (Option) data.getSerializableExtra("option");
                    lead2.setText(option.getDesc());
                    workOrder.LEADNAME = option.getDesc();
                    workOrder.LEAD = option.getName();
                    break;
                case 15:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby_2.setText(option.getDesc());
                    workOrder.NAME1 = option.getDesc();
                    workOrder.UDINSPOBY = option.getName();
                    break;
                case 16:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby2_2.setText(option.getDesc());
                    workOrder.NAME2 = option.getDesc();
                    workOrder.UDINSPOBY2 = option.getName();
                    break;
                case 17:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby3_2.setText(option.getDesc());
                    workOrder.NAME3 = option.getDesc();
                    workOrder.UDINSPOBY3 = option.getName();
                    break;
                case 18:
                    option = (Option) data.getSerializableExtra("option");
                    udprores.setText(option.getDesc());
                    workOrder.UDPRORES = option.getName();
                    workOrder.UDPRORESNAME = option.getDesc();
                    break;
                case 19:
                    option = (Option) data.getSerializableExtra("option");
                    djplannum.setText(option.getName());
                    proname.setText(option.getDesc());
                    udprojectnum.setText(option.getValue1());
                    branch.setText(option.getValue2());
                    lead.setText(option.getValue5());
                    workOrder.LEAD = option.getValue4();
                    workOrder.LEADNAME = option.getValue5();
                    wtcode.setText(option.getValue6());
                    udjpnum.setText(option.getValue7());
                    udplstartdate.setText(option.getValue9());
                    udplstopdate.setText(option.getValue10());
                    djtype.setText(option.getValue11());
                case 20:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby4.setText(option.getDesc());
                    workOrder.NAME4 = option.getDesc();
                    workOrder.UDINSPOBY4 = option.getName();
                    break;
                case 21:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby5.setText(option.getDesc());
                    workOrder.NAME5 = option.getDesc();
                    workOrder.UDINSPOBY5 = option.getName();
                    break;
                case 22:
                    option = (Option) data.getSerializableExtra("option");
                    udinspoby6.setText(option.getDesc());
                    workOrder.NAME6 = option.getDesc();
                    workOrder.UDINSPOBY6 = option.getName();
                    break;
                case 1000:
                    if (data.hasExtra("woactivityList") && data.getSerializableExtra("woactivityList") != null) {
                        woactivityList = (ArrayList<Woactivity>) data.getSerializableExtra("woactivityList");
                    }
                    break;
                case 2000:
                    wpmaterialLit = (ArrayList<Wpmaterial>) data.getSerializableExtra("wpmaterialList");
                    break;
            }
        }
    }
    private boolean checkRequiredFieldcompeletion()
    {
        StringBuilder nullFields= new StringBuilder("");

        if (RequiredFields==null||RequiredFields.size()==0)
        {
            return true;
        }
        else
        {
            for(String str:RequiredFields)
            {
                try {

                    Method getter = null;
                    getter = workOrder.getClass().getMethod("get"+str);
                    String value=(String)getter.invoke(workOrder);

                    if (value==null||value.length()<=0)
                    {
                        nullFields.append(str+",");

                        Log.e("必填字段","字段名："+str+"字段值为空");
                    }
                    else
                    {
                        Log.e("必填字段","字段名："+str+"字段值："+value);
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }

            }
            if (nullFields.length()==0)
            {
                return true;
            }
            else
            {
                Toast.makeText(this,"以下内容未填写<"+nullFields.toString()+">,请填写并保存后进行其它操作",Toast.LENGTH_LONG);
            }

        }
        return true;
    }
}
