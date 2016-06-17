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

    public String getPROBLEMCODE() {
        return PROBLEMCODE;
    }

    public void setPROBLEMCODE(String PROBLEMCODE) {
        this.PROBLEMCODE = PROBLEMCODE;
    }

    public String getWONUM() {
        return WONUM;
    }

    public void setWONUM(String WONUM) {
        this.WONUM = WONUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getUDPROJECTNUM() {
        return UDPROJECTNUM;
    }

    public void setUDPROJECTNUM(String UDPROJECTNUM) {
        this.UDPROJECTNUM = UDPROJECTNUM;
    }

    public String getUDLOCNUM() {
        return UDLOCNUM;
    }

    public void setUDLOCNUM(String UDLOCNUM) {
        this.UDLOCNUM = UDLOCNUM;
    }

    public String getUDLOCATION() {
        return UDLOCATION;
    }

    public void setUDLOCATION(String UDLOCATION) {
        this.UDLOCATION = UDLOCATION;
    }

    public String getLEAD() {
        return LEAD;
    }

    public void setLEAD(String LEAD) {
        this.LEAD = LEAD;
    }

    public String getFAILURECODE() {
        return FAILURECODE;
    }

    public void setFAILURECODE(String FAILURECODE) {
        this.FAILURECODE = FAILURECODE;
    }

    public String getCULEVEL() {
        return CULEVEL;
    }

    public void setCULEVEL(String CULEVEL) {
        this.CULEVEL = CULEVEL;
    }

    public String getUDZGLIMIT() {
        return UDZGLIMIT;
    }

    public void setUDZGLIMIT(String UDZGLIMIT) {
        this.UDZGLIMIT = UDZGLIMIT;
    }

    public String getUDPLANNUM() {
        return UDPLANNUM;
    }

    public void setUDPLANNUM(String UDPLANNUM) {
        this.UDPLANNUM = UDPLANNUM;
    }

    public String getSCHEDSTART() {
        return SCHEDSTART;
    }

    public void setSCHEDSTART(String SCHEDSTART) {
        this.SCHEDSTART = SCHEDSTART;
    }

    public String getSCHEDFINISH() {
        return SCHEDFINISH;
    }

    public void setSCHEDFINISH(String SCHEDFINISH) {
        this.SCHEDFINISH = SCHEDFINISH;
    }

    public String getACTSTART() {
        return ACTSTART;
    }

    public void setACTSTART(String ACTSTART) {
        this.ACTSTART = ACTSTART;
    }

    public String getACTFINISH() {
        return ACTFINISH;
    }

    public void setACTFINISH(String ACTFINISH) {
        this.ACTFINISH = ACTFINISH;
    }

    public String getISSTOPED() {
        return ISSTOPED;
    }

    public void setISSTOPED(String ISSTOPED) {
        this.ISSTOPED = ISSTOPED;
    }

    public String getPMCHGEVALSTART() {
        return PMCHGEVALSTART;
    }

    public void setPMCHGEVALSTART(String PMCHGEVALSTART) {
        this.PMCHGEVALSTART = PMCHGEVALSTART;
    }

    public String getPMCHGEVALEND() {
        return PMCHGEVALEND;
    }

    public void setPMCHGEVALEND(String PMCHGEVALEND) {
        this.PMCHGEVALEND = PMCHGEVALEND;
    }

    public String getUDPROBDESC() {
        return UDPROBDESC;
    }

    public void setUDPROBDESC(String UDPROBDESC) {
        this.UDPROBDESC = UDPROBDESC;
    }

    public String getWORKTYPE() {
        return WORKTYPE;
    }

    public void setWORKTYPE(String WORKTYPE) {
        this.WORKTYPE = WORKTYPE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getUDJPNUM() {
        return UDJPNUM;
    }

    public void setUDJPNUM(String UDJPNUM) {
        this.UDJPNUM = UDJPNUM;
    }

    public String getUDPLSTARTDATE() {
        return UDPLSTARTDATE;
    }

    public void setUDPLSTARTDATE(String UDPLSTARTDATE) {
        this.UDPLSTARTDATE = UDPLSTARTDATE;
    }

    public String getUDPLSTOPDATE() {
        return UDPLSTOPDATE;
    }

    public void setUDPLSTOPDATE(String UDPLSTOPDATE) {
        this.UDPLSTOPDATE = UDPLSTOPDATE;
    }

    public String getUDRLSTARTDATE() {
        return UDRLSTARTDATE;
    }

    public void setUDRLSTARTDATE(String UDRLSTARTDATE) {
        this.UDRLSTARTDATE = UDRLSTARTDATE;
    }

    public String getUDRLSTOPDATE() {
        return UDRLSTOPDATE;
    }

    public void setUDRLSTOPDATE(String UDRLSTOPDATE) {
        this.UDRLSTOPDATE = UDRLSTOPDATE;
    }

    public String getUDINSPOBY() {
        return UDINSPOBY;
    }

    public void setUDINSPOBY(String UDINSPOBY) {
        this.UDINSPOBY = UDINSPOBY;
    }

    public String getUDINSPOBY2() {
        return UDINSPOBY2;
    }

    public void setUDINSPOBY2(String UDINSPOBY2) {
        this.UDINSPOBY2 = UDINSPOBY2;
    }

    public String getUDINSPOBY3() {
        return UDINSPOBY3;
    }

    public void setUDINSPOBY3(String UDINSPOBY3) {
        this.UDINSPOBY3 = UDINSPOBY3;
    }

    public String getDJPLANNUM() {
        return DJPLANNUM;
    }

    public void setDJPLANNUM(String DJPLANNUM) {
        this.DJPLANNUM = DJPLANNUM;
    }

    public String getDJTYPE() {
        return DJTYPE;
    }

    public void setDJTYPE(String DJTYPE) {
        this.DJTYPE = DJTYPE;
    }

    public String getWTCODE() {
        return WTCODE;
    }

    public void setWTCODE(String WTCODE) {
        this.WTCODE = WTCODE;
    }

    public String getASSETTYPE() {
        return ASSETTYPE;
    }

    public void setASSETTYPE(String ASSETTYPE) {
        this.ASSETTYPE = ASSETTYPE;
    }

    public String getPERINSPR() {
        return PERINSPR;
    }

    public void setPERINSPR(String PERINSPR) {
        this.PERINSPR = PERINSPR;
    }

    public String getUDREMARK() {
        return UDREMARK;
    }

    public void setUDREMARK(String UDREMARK) {
        this.UDREMARK = UDREMARK;
    }

    public String getISBIGPAR() {
        return ISBIGPAR;
    }

    public void setISBIGPAR(String ISBIGPAR) {
        this.ISBIGPAR = ISBIGPAR;
    }

    public String getUDZGMEASURE() {
        return UDZGMEASURE;
    }

    public void setUDZGMEASURE(String UDZGMEASURE) {
        this.UDZGMEASURE = UDZGMEASURE;
    }

    public String getPLANNUM() {
        return PLANNUM;
    }

    public void setPLANNUM(String PLANNUM) {
        this.PLANNUM = PLANNUM;
    }

    public String getPCCOMPNUM() {
        return PCCOMPNUM;
    }

    public void setPCCOMPNUM(String PCCOMPNUM) {
        this.PCCOMPNUM = PCCOMPNUM;
    }

    public String getPCTYPE() {
        return PCTYPE;
    }

    public void setPCTYPE(String PCTYPE) {
        this.PCTYPE = PCTYPE;
    }

    public String getUDFJFOL() {
        return UDFJFOL;
    }

    public void setUDFJFOL(String UDFJFOL) {
        this.UDFJFOL = UDFJFOL;
    }

    public String getPCRESON() {
        return PCRESON;
    }

    public void setPCRESON(String PCRESON) {
        this.PCRESON = PCRESON;
    }

    public String getUDJGRESULT() {
        return UDJGRESULT;
    }

    public void setUDJGRESULT(String UDJGRESULT) {
        this.UDJGRESULT = UDJGRESULT;
    }

    public String getJGPLANNUM() {
        return JGPLANNUM;
    }

    public void setJGPLANNUM(String JGPLANNUM) {
        this.JGPLANNUM = JGPLANNUM;
    }

    public String getUDJGTYPE() {
        return UDJGTYPE;
    }

    public void setUDJGTYPE(String UDJGTYPE) {
        this.UDJGTYPE = UDJGTYPE;
    }

    public String getUDFJAPPNUM() {
        return UDFJAPPNUM;
    }

    public void setUDFJAPPNUM(String UDFJAPPNUM) {
        this.UDFJAPPNUM = UDFJAPPNUM;
    }

    public String getUDRPRRSB() {
        return UDRPRRSB;
    }

    public void setUDRPRRSB(String UDRPRRSB) {
        this.UDRPRRSB = UDRPRRSB;
    }

    public boolean isnew() {
        return isnew;
    }

    public void setIsnew(boolean isnew) {
        this.isnew = isnew;
    }

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