package com.example.admin.eam.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by think on 2015/10/28.
 */
@DatabaseTable(tableName = "WorkOrder")

public class WorkOrder implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "WORKORDERID")
    public int WORKORDERID;//工单服务器id
    @DatabaseField(columnName = "WONUM")
    public String WONUM;//工单号
    @DatabaseField(columnName = "DESCRIPTION")
    public String DESCRIPTION;//描述
    @DatabaseField(columnName = "BRANCH")
    public String BRANCH;//中心
    @DatabaseField(columnName = "UDPROJECTNUM")
    public String UDPROJECTNUM;//项目
    @DatabaseField(columnName = "PRONAME")
    public String PRONAME;//项目名称
    @DatabaseField(columnName = "UDLOCNUM")
    public String UDLOCNUM;//机位号
    @DatabaseField(columnName = "UDLOCATION")
    public String UDLOCATION;//位置
    @DatabaseField(columnName = "LOCDESC")
    public String LOCDESC;//位置描述
    @DatabaseField(columnName = "LEAD")
    public String LEAD;//运行组/维护组工程师
    @DatabaseField(columnName = "LEADNAME")
    public String LEADNAME;//运行组/维护组工程师名称
    @DatabaseField(columnName = "UDPRORES")
    public String UDPRORES;//项目负责人
    @DatabaseField(columnName = "UDPRORESNAME")
    public String UDPRORESNAME;//项目负责人名称
    @DatabaseField(columnName = "FAILURECODE")
    public String FAILURECODE;//故障类
    @DatabaseField(columnName = "GZLDESC")
    public String GZLDESC;//故障类描述
    @DatabaseField(columnName = "UDFAILURECODE")
    public String UDFAILURECODE;//问题原因
    @DatabaseField(columnName = "GZWTDESC")
    public String GZWTDESC;//问题原因描述
    @DatabaseField(columnName = "CULEVEL")
    public String CULEVEL;//故障等级
    @DatabaseField(columnName = "UDGZDJ")
    public String UDGZDJ;//故障等级
    @DatabaseField(columnName = "UDGZTYPE")
    public String UDGZTYPE;//故障类型
    @DatabaseField(columnName = "UDZGLIMIT")
    public String UDZGLIMIT;//提报时间
    @DatabaseField(columnName = "UDPLANNUM")
    public String UDPLANNUM;//终验收计划号
    @DatabaseField(columnName = "SCHEDSTART")
    public String SCHEDSTART;//故障开始时间
    @DatabaseField(columnName = "SCHEDFINISH")
    public String SCHEDFINISH;//故障结束时间
    @DatabaseField(columnName = "ACTSTART")
    public String ACTSTART;//实际开始时间
    @DatabaseField(columnName = "ACTFINISH")
    public String ACTFINISH;//实际结束时间
    @DatabaseField(columnName = "ISSTOPED")
    public int ISSTOPED;//是否停机
    @DatabaseField(columnName = "UDSTOPTIME")
    public String UDSTOPTIME;//故障开始时间
    @DatabaseField(columnName = "UDRESTARTTIME")
    public String UDRESTARTTIME;//故障恢复时间
//    public String UDJGRESULT;//累计时间
    @DatabaseField(columnName = "UDPROBDESC")
    public String UDPROBDESC;//故障隐患描述
    @DatabaseField(columnName = "WORKTYPE")
    public String WORKTYPE;//工单类型
    @DatabaseField(columnName = "STATUS")
    public String STATUS;//状态
    @DatabaseField(columnName = "UDSTATUS")
    public String UDSTATUS;//状态
    @DatabaseField(columnName = "CREATEDATE")
    public String CREATEDATE;//创建时间
    @DatabaseField(columnName = "CREATEBY")
    public String CREATEBY;//创建人
    @DatabaseField(columnName = "CREATENAME")
    public String CREATENAME;//创建人名称
    @DatabaseField(columnName = "UDJPNUM")
    public String UDJPNUM;//定检标准编号/排查标准/技改标准
    @DatabaseField(columnName = "UDPLSTARTDATE")
    public String UDPLSTARTDATE;//计划开始时间
    @DatabaseField(columnName = "UDPLSTOPDATE")
    public String UDPLSTOPDATE;//计划完成时间
    @DatabaseField(columnName = "UDRLSTARTDATE")
    public String UDRLSTARTDATE;//实际开始时间
    @DatabaseField(columnName = "UDRLSTOPDATE")
    public String UDRLSTOPDATE;//实际完成时间
    @DatabaseField(columnName = "UDINSPOBY")
    public String UDINSPOBY;//定检人员1
    @DatabaseField(columnName = "NAME1")
    public String NAME1;//定检人员1名称
    @DatabaseField(columnName = "UDINSPOBY2")
    public String UDINSPOBY2;//定检人员2
    @DatabaseField(columnName = "NAME2")
    public String NAME2;//定检人员2名称
    @DatabaseField(columnName = "UDINSPOBY3")
    public String UDINSPOBY3;//定检人员3
    @DatabaseField(columnName = "NAME3")
    public String NAME3;//定检人员3名称
    @DatabaseField(columnName = "UDINSPOBY3")
    public String UDINSPOBY4;//定检人员4
    @DatabaseField(columnName = "NAME4")
    public String NAME4;//定检人员4名称
    @DatabaseField(columnName = "UDINSPOBY5")
    public String UDINSPOBY5;//定检人员5
    @DatabaseField(columnName = "NAME5")
    public String NAME5;//定检人员5名称
    @DatabaseField(columnName = "UDINSPOBY6")
    public String UDINSPOBY6;//定检人员6
    @DatabaseField(columnName = "NAME6")
    public String NAME6;//定检人员6名称
    @DatabaseField(columnName = "DJPLANNUM")
    public String DJPLANNUM;//定检计划编号
    @DatabaseField(columnName = "DJTYPE")
    public String DJTYPE;//定检类型
    @DatabaseField(columnName = "WTCODE")
    public String WTCODE;//风机型号
    @DatabaseField(columnName = "ASSETTYPE")
    public String ASSETTYPE;//设备类别
    @DatabaseField(columnName = "PERINSPR")
    public int PERINSPR;//定检结果（Y\N）
    @DatabaseField(columnName = "UDREMARK")
    public String UDREMARK;//备注
    @DatabaseField(columnName = "ISBIGPAR")
    public int ISBIGPAR;//大部件发放（Y/N）
    @DatabaseField(columnName = "UDZGMEASURE")
    public String UDZGMEASURE;//故障处理方案
    @DatabaseField(columnName = "PLANNUM")
    public String PLANNUM;//排查计划编号
    @DatabaseField(columnName = "PCCOMPNUM")
    public String PCCOMPNUM;//排查完成台数/计划定检风机台数
    @DatabaseField(columnName = "REALCOMP")
    public String REALCOMP;//排查完成台数/计划定检风机台数
    @DatabaseField(columnName = "PCTYPE")
    public String PCTYPE;//排查类型
    @DatabaseField(columnName = "UDFJFOL")
    public String UDFJFOL;//风机跟踪
    @DatabaseField(columnName = "PCRESON")
    public String PCRESON;//排查原因/技改原因
    @DatabaseField(columnName = "UDJGRESULT")
    public String UDJGRESULT;//排查结果/累计时间
    @DatabaseField(columnName = "JGPLANNUM")
    public String JGPLANNUM;//技改计划编号
    @DatabaseField(columnName = "UDJGTYPE")
    public String UDJGTYPE;//技改类型
    @DatabaseField(columnName = "UDFJAPPNUM")
    public String UDFJAPPNUM;//主控程序版本号
    @DatabaseField(columnName = "UDRPRRSB")
    public String UDRPRRSB;//负责人/提报人
    @DatabaseField(columnName = "UDRPRRSBNAME")
    public String UDRPRRSBNAME;//负责人/提报人
    @DatabaseField(columnName = "UDREPORTNUM")
    public String UDREPORTNUM;//故障提报单号
    @DatabaseField(columnName = "UDDESCRIP")
    public String UDDESCRIP;//处理过程
    @DatabaseField(columnName = "UDWPTYPE")
    public String UDWPTYPE;//人员类型
    @DatabaseField(columnName = "UDWP")
    public String UDWP;//承包人员
    @DatabaseField(columnName = "UDCOND2")
    public String UDCOND2;//需要其他单据
    @DatabaseField(columnName = "UDCOND1")
    public String UDCOND1;//需要其他单据

    @DatabaseField(columnName = "isnew")
    public boolean isnew;//是否是新增工单
    @DatabaseField(columnName = "isUpdate")
    public boolean isUpdate;//是否是本地已修改工单
    @DatabaseField(columnName = "belong")
    public String  belong;//工单所属用户


    private HashMap<String,String> map = new HashMap<String,String>();
    public HashMap<String,String> getMap()
    {
        map.put("WONUM","工单号");
        map.put("DESCRIPTION","描述");
        map.put("BRANCH","中心");
        map.put("UDPROJECTNUM","项目");
        map.put("PRONAME","项目名称");
        map.put("UDLOCNUM","机位号");
        map.put("UDLOCATION","位置");
        map.put("LOCDESC","位置描述");
        map.put("LEAD","运行组/维护组工程师");
        map.put("LEADNAME","运行组/维护组工程师名称");
        map.put("UDPRORES","项目负责人");
        map.put("UDPRORESNAME","项目负责人名称");
        map.put("FAILURECODE","故障类");
        map.put("GZLDESC","故障类描述");
        map.put("UDFAILURECODE","问题原因");
        map.put("GZWTDESC","问题原因描述");
        map.put("CULEVEL","故障等级");
        map.put("UDGZDJ","故障等级");
        map.put("UDGZTYPE","故障类型");
        map.put("UDZGLIMIT","提报时间");
        map.put("UDPLANNUM","终验收计划号");
        map.put("SCHEDSTART","故障开始时间");
        map.put("SCHEDFINISH","故障结束时间");
        map.put("ACTSTART","实际开始时间");
        map.put("ACTFINISH","实际结束时间");
        map.put("ISSTOPED","是否停机");
        map.put("UDSTOPTIME","故障开始时间");
        map.put("UDRESTARTTIME","故障恢复时间");
        map.put("UDPROBDESC","故障隐患描述");
        map.put("WORKTYPE","工单类型");
        map.put("STATUS","状态");
        map.put("UDSTATUS","状态");
        map.put("CREATEDATE","创建时间");
        map.put("CREATEBY","创建人");
        map.put("CREATENAME","创建人名称");
        map.put("UDJPNUM","定检标准编号/排查标准/技改标准");
        map.put("UDPLSTARTDATE","计划开始时间");
        map.put("UDPLSTOPDATE","计划完成时间");
        map.put("UDRLSTARTDATE","实际开始时间");
        map.put("UDRLSTOPDATE","实际完成时间");
        map.put("UDINSPOBY","定检人员1");
        map.put("NAME1","定检人员1名称");
        map.put("UDINSPOBY2","定检人员2");
        map.put("NAME2","定检人员2名称");
        map.put("UDINSPOBY3","定检人员3");
        map.put("NAME3","定检人员3名称");
        map.put("UDINSPOBY4","定检人员4");
        map.put("NAME4","定检人员4名称");
        map.put("UDINSPOBY5","定检人员5");
        map.put("NAME5","定检人员5名称");
        map.put("UDINSPOBY6","定检人员6");
        map.put("NAME6","定检人员6名称");
        map.put("DJPLANNUM","定检计划编号");
        map.put("DJTYPE","定检类型");
        map.put("WTCODE","风机型号");
        map.put("ASSETTYPE","设备类别");
        map.put("PERINSPR","定检结果");
        map.put("UDREMARK","备注");
        map.put("ISBIGPAR","大部件发放（Y/N）");
        map.put("UDZGMEASURE","故障处理方案");
        map.put("PLANNUM","排查计划编号");
        map.put("PCCOMPNUM","排查完成台数/计划定检风机台数");
        map.put("REALCOMP","排查完成台数/计划定检风机台数");
        map.put("PCTYPE","排查类型");
        map.put("UDFJFOL","风机跟踪");
        map.put("PCRESON","排查原因/技改原因");
        map.put("UDJGRESULT","排查结果/累计时间");
        map.put("JGPLANNUM","技改计划编号");
        map.put("UDJGTYPE","技改类型");
        map.put("UDFJAPPNUM","主控程序版本号");
        map.put("UDRPRRSB","负责人/提报人");
        map.put("UDRPRRSBNAME","负责人/提报人");
        map.put("UDREPORTNUM","故障提报单号");
        map.put("UDDESCRIP","处理过程");
        map.put("UDWPTYPE","人员类型");
        map.put("UDWP","承包人员");
        map.put("UDCOND2","需要其他单据");
        map.put("UDCOND1","需要其他单据");
        return map;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWORKORDERID() {
        return WORKORDERID;
    }

    public void setWORKORDERID(int WORKORDERID) {
        this.WORKORDERID = WORKORDERID;
    }

    public String getUDFAILURECODE() {
        return UDFAILURECODE;
    }

    public void setUDFAILURECODE(String UDFAILURECODE) {
        this.UDFAILURECODE = UDFAILURECODE;
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

    public String getGZLDESC() {
        return GZLDESC;
    }

    public void setGZLDESC(String GZLDESC) {
        this.GZLDESC = GZLDESC;
    }

    public String getGZWTDESC() {
        return GZWTDESC;
    }

    public void setGZWTDESC(String GZWTDESC) {
        this.GZWTDESC = GZWTDESC;
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

    public int getISSTOPED() {
        return ISSTOPED;
    }

    public void setISSTOPED(int ISSTOPED) {
        this.ISSTOPED = ISSTOPED;
    }

    public String getUDSTOPTIME() {
        return UDSTOPTIME;
    }

    public void setUDSTOPTIME(String UDSTOPTIME) {
        this.UDSTOPTIME = UDSTOPTIME;
    }

    public String getUDRESTARTTIME() {
        return UDRESTARTTIME;
    }

    public void setUDRESTARTTIME(String UDRESTARTTIME) {
        this.UDRESTARTTIME = UDRESTARTTIME;
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

    public String getUDSTATUS() {
        return UDSTATUS;
    }

    public void setUDSTATUS(String UDSTATUS) {
        this.UDSTATUS = UDSTATUS;
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

    public String getCREATENAME() {
        return CREATENAME;
    }

    public void setCREATENAME(String CREATENAME) {
        this.CREATENAME = CREATENAME;
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

    public int getPERINSPR() {
        return PERINSPR;
    }

    public void setPERINSPR(int PERINSPR) {
        this.PERINSPR = PERINSPR;
    }

    public String getUDREMARK() {
        return UDREMARK;
    }

    public void setUDREMARK(String UDREMARK) {
        this.UDREMARK = UDREMARK;
    }

    public int getISBIGPAR() {
        return ISBIGPAR;
    }

    public void setISBIGPAR(int ISBIGPAR) {
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

    public String getREALCOMP() {
        return REALCOMP;
    }

    public void setREALCOMP(String REALCOMP) {
        this.REALCOMP = REALCOMP;
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

    public String getUDRPRRSBNAME() {
        return UDRPRRSBNAME;
    }

    public void setUDRPRRSBNAME(String UDRPRRSBNAME) {
        this.UDRPRRSBNAME = UDRPRRSBNAME;
    }

    public String getLEADNAME() {
        return LEADNAME;
    }

    public void setLEADNAME(String LEADNAME) {
        this.LEADNAME = LEADNAME;
    }

    public String getUDPRORES() {
        return UDPRORES;
    }

    public void setUDPRORES(String UDPRORES) {
        this.UDPRORES = UDPRORES;
    }

    public String getUDPRORESNAME() {
        return UDPRORESNAME;
    }

    public void setUDPRORESNAME(String UDPRORESNAME) {
        this.UDPRORESNAME = UDPRORESNAME;
    }

    public String getUDREPORTNUM() {
        return UDREPORTNUM;
    }

    public void setUDREPORTNUM(String UDREPORTNUM) {
        this.UDREPORTNUM = UDREPORTNUM;
    }

    public boolean getisnew() {
        return isnew;
    }

    public void setisnew(boolean isnew) {
        this.isnew = isnew;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setisUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getbelong() {
        return belong;
    }

    public void setbelong(String belong) {
        this.belong = belong;
    }

    public String getNAME1() {
        return NAME1;
    }

    public void setNAME1(String NAME1) {
        this.NAME1 = NAME1;
    }

    public String getNAME2() {
        return NAME2;
    }

    public void setNAME2(String NAME2) {
        this.NAME2 = NAME2;
    }

    public String getNAME3() {
        return NAME3;
    }

    public void setNAME3(String NAME3) {
        this.NAME3 = NAME3;
    }

    public String getUDGZDJ() {
        return UDGZDJ;
    }

    public void setUDGZDJ(String UDGZDJ) {
        this.UDGZDJ = UDGZDJ;
    }

    public String getUDGZTYPE() {
        return UDGZTYPE;
    }

    public void setUDGZTYPE(String UDGZTYPE) {
        this.UDGZTYPE = UDGZTYPE;
    }

    public String getUDDESCRIP() {
        return UDDESCRIP;
    }

    public void setUDDESCRIP(String UDDESCRIP) {
        this.UDDESCRIP = UDDESCRIP;
    }

    public String getPRONAME() {
        return PRONAME;
    }

    public void setPRONAME(String PRONAME) {
        this.PRONAME = PRONAME;
    }

    public String getLOCDESC() {
        return LOCDESC;
    }

    public void setLOCDESC(String LOCDESC) {
        this.LOCDESC = LOCDESC;
    }

    public String getUDWPTYPE() {
        return UDWPTYPE;
    }

    public void setUDWPTYPE(String UDWPTYPE) {
        this.UDWPTYPE = UDWPTYPE;
    }

    public String getUDWP() {
        return UDWP;
    }

    public void setUDWP(String UDWP) {
        this.UDWP = UDWP;
    }

    public String getUDCOND2() {
        return UDCOND2;
    }

    public void setUDCOND2(String UDCOND2) {
        this.UDCOND2 = UDCOND2;
    }

    public String getUDCOND1() {
        return UDCOND1;
    }

    public void setUDCOND1(String UDCOND1) {
        this.UDCOND1 = UDCOND1;
    }

    public String getUDINSPOBY4() {
        return UDINSPOBY4;
    }

    public void setUDINSPOBY4(String UDINSPOBY4) {
        this.UDINSPOBY4 = UDINSPOBY4;
    }

    public String getNAME4() {
        return NAME4;
    }

    public void setNAME4(String NAME4) {
        this.NAME4 = NAME4;
    }

    public String getUDINSPOBY5() {
        return UDINSPOBY5;
    }

    public void setUDINSPOBY5(String UDINSPOBY5) {
        this.UDINSPOBY5 = UDINSPOBY5;
    }

    public String getNAME5() {
        return NAME5;
    }

    public void setNAME5(String NAME5) {
        this.NAME5 = NAME5;
    }

    public String getUDINSPOBY6() {
        return UDINSPOBY6;
    }

    public void setUDINSPOBY6(String UDINSPOBY6) {
        this.UDINSPOBY6 = UDINSPOBY6;
    }

    public String getNAME6() {
        return NAME6;
    }

    public void setNAME6(String NAME6) {
        this.NAME6 = NAME6;
    }
}
