package com.example.admin.mingyang_object.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by think on 2015/10/28.
 */
public class WorkOrder implements Serializable {
//    private static final String TAG = "WorkOrder";
//    private static final long serialVersionUID = 2015050105L;

    public String WONUM;//工单号
    public String DESCRIPTION;//描述
    public String BRANCH;//中心
    public String UDPROJECTNUM;//项目
    public String UDLOCNUM;//机位号
    public String UDLOCATION;//位置
    public String LEAD;//运行组/维护组工程师
    public String FAILURECODE;//故障类
    public String PROBLEMCODE;//问题原因
    public String CULEVEL;//故障等级
    public String UDZGLIMIT;//提报时间
    public String UDPLANNUM;//终验收计划号
    public String SCHEDSTART;//故障开始时间
    public String SCHEDFINISH;//故障结束时间
    public String ACTSTART;//实际开始时间
    public String ACTFINISH;//实际结束时间
    public String ISSTOPED;//是否停机
    public String PMCHGEVALSTART;//故障开始时间
    public String PMCHGEVALEND;//故障恢复时间
//    public String UDJGRESULT;//累计时间
    public String UDPROBDESC;//故障隐患描述
    public String WORKTYPE;//工单类型
    public String STATUS;//状态
    public String CREATEDATE;//创建时间
    public String CREATEBY;//创建人
    public String UDJPNUM;//定检标准编号/排查标准/技改标准
    public String UDPLSTARTDATE;//计划开始时间
    public String UDPLSTOPDATE;//计划完成时间
    public String UDRLSTARTDATE;//实际开始时间
    public String UDRLSTOPDATE;//实际完成时间
    public String UDINSPOBY;//定检人员1
    public String UDINSPOBY2;//定检人员2
    public String UDINSPOBY3;//定检人员3
    public String DJPLANNUM;//定检计划编号
    public String DJTYPE;//定检类型
    public String WTCODE;//风机型号
    public String ASSETTYPE;//设备类别
    public String PERINSPR;//定检结果（Y\N）
    public String UDREMARK;//备注
    public String ISBIGPAR;//大部件发放（Y/N）
    public String UDZGMEASURE;//故障处理方案
    public String PLANNUM;//排查计划编号
    public String PCCOMPNUM;//排查完成台数/计划定检风机台数
    public String PCTYPE;//排查类型
    public String UDFJFOL;//风机跟踪
    public String PCRESON;//排查原因/技改原因
    public String UDJGRESULT;//排查结果/累计时间
    public String JGPLANNUM;//技改计划编号
    public String UDJGTYPE;//技改类型
    public String UDFJAPPNUM;//主控程序版本号
    public String UDRPRRSB;//负责人/提报人

    public boolean isnew;//是否是新增工单




//    @Override
//    public void parse(JSONObject jsonObject) throws JSONException {
//        wonum = jsonObject.getString("wonum");
//        status = jsonObject.getString("status");
//        statusdate = jsonObject.getString("statusdate");
//        worktype = jsonObject.getString("worktype");
//        description = jsonObject.getString("description");
//        assetnum = jsonObject.getString("assetnum");
////        assetdescription = jsonObject.getString("assetdescription");
//        udisaq = jsonObject.getString("udisaq");
//        udisbx = jsonObject.getString("udisbx");
//        udiscb = jsonObject.getString("udiscb");
//        udisjf = jsonObject.getString("udisjf");
//        udisjj = jsonObject.getString("udisjj");
////        udisplayname = jsonObject.getString("udisplayname");
//        udremark = jsonObject.getString("udremark");
//        udtjsj = jsonObject.getString("udtjsj");
//        actstart = jsonObject.getString("actstart");
//        actfinish = jsonObject.getString("actfinish");
//        woeq3 = jsonObject.getString("woeq3");
//        woeq2 = jsonObject.getString("woeq2");
//        woeq1 = jsonObject.getString("woeq1");
//        jpnum = jsonObject.getString("jpnum");
////        ldispayname = jsonObject.getString("ldispayname");
//        udcreateby = jsonObject.getString("udcreateby");
//        udcreatedate = jsonObject.getString("udcreatedate");
//        reportdate = jsonObject.getString("reportdate");
//        reportedby = jsonObject.getString("reportedby");
//        lead = jsonObject.getString("lead");
//        targstartdate = jsonObject.getString("targstartdate");
//        targcompdate = jsonObject.getString("targcompdate");
//    }
//
//    public WorkOrder() {
//    }
//
//
//    private WorkOrder(Parcel in) {
//        wonum = in.readString();
//        status = in.readString();
//        statusdate = in.readString();
//        worktype = in.readString();
//        description = in.readString();
//        assetnum = in.readString();
////        assetdescription = in.readString();
//        udisaq = in.readString();
//        udisbx = in.readString();
//        udiscb = in.readString();
//        udisjf = in.readString();
//        udisjj = in.readString();
////        udisplayname = in.readString();
//        udremark = in.readString();
//        udtjsj = in.readString();
//        actstart = in.readString();
//        actfinish = in.readString();
//        woeq3 = in.readString();
//        woeq2 = in.readString();
//        woeq1 = in.readString();
//        jpnum = in.readString();
////        ldispayname = in.readString();
//        udcreateby = in.readString();
//        udcreatedate = in.readString();
//        reportdate = in.readString();
//        reportedby = in.readString();
//        lead = in.readString();
//        targstartdate = in.readString();
//        targcompdate = in.readString();
//    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(wonum);
//        dest.writeString(status);
//        dest.writeString(statusdate);
//        dest.writeString(worktype);
//        dest.writeString(description);
//        dest.writeString(assetnum);
////        dest.writeString(assetdescription);
//        dest.writeString(udisaq);
//        dest.writeString(udisbx);
//        dest.writeString(udiscb);
//        dest.writeString(udisjf);
//        dest.writeString(udisjj);
////        dest.writeString(udisplayname);
//        dest.writeString(udremark);
//        dest.writeString(udtjsj);
//        dest.writeString(actstart);
//        dest.writeString(actfinish);
//        dest.writeString(woeq3);
//        dest.writeString(woeq2);
//        dest.writeString(woeq1);
//        dest.writeString(jpnum);
////        dest.writeString(ldispayname);
//        dest.writeString(udcreateby);
//        dest.writeString(udcreatedate);
//        dest.writeString(reportdate);
//        dest.writeString(reportedby);
//        dest.writeString(lead);
//        dest.writeString(targstartdate);
//        dest.writeString(targcompdate);
//    }
//
//    public static final Creator<WorkOrder> CREATOR = new Creator<WorkOrder>() {
//        @Override
//        public WorkOrder createFromParcel(Parcel source) {
//            return new WorkOrder(source);
//        }
//
//        @Override
//        public WorkOrder[] newArray(int size) {
//            return new WorkOrder[size];
//        }
//    };
}
