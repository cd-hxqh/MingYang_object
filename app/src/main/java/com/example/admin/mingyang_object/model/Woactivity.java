package com.example.admin.mingyang_object.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/6/21.
 * 工单任务
 */
@DatabaseTable(tableName = "Woactivity")
public class Woactivity extends Entity implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "WORKORDERID")
    public int WORKORDERID;
    @DatabaseField(columnName = "TASKID")
    public String TASKID;//任务
    @DatabaseField(columnName = "DESCRIPTION")
    public String DESCRIPTION;//描述
    @DatabaseField(columnName = "WOJO1")
    public String WOJO1;//系统/项目
    @DatabaseField(columnName = "WOJO2")
    public String WOJO2;//子系统/子项目
    @DatabaseField(columnName = "WOJO3")
    public String WOJO3;//检查/检修方法
    @DatabaseField(columnName = "WOJO4")
    public String WOJO4;//kks编码
//    public String INVCONTENT;//技改完成情况
    @DatabaseField(columnName = "UDSAFTYDESC")
    public String UDSAFTYDESC;//安全缺失(隐患)描述
    @DatabaseField(columnName = "UDZGSTU")
    public String UDZGSTU;//整改情况回复/检修情况
    @DatabaseField(columnName = "UDINSUNIT")
    public String UDINSUNIT;//排查部位
    @DatabaseField(columnName = "UDRPRRSB")
    public String UDRPRRSB;//整改责任人
    @DatabaseField(columnName = "UDRPRRSBNAME")
    public String UDRPRRSBNAME;//整改责任人名称
    @DatabaseField(columnName = "ALLPOWER")
    public String ALLPOWER;//人员数量
    @DatabaseField(columnName = "ALLOPTIME")
    public String ALLOPTIME;//耗时(小时)
    @DatabaseField(columnName = "INVCONTENT")
    public String INVCONTENT;//定检规格/技改完成情况
    @DatabaseField(columnName = "UDRLSTOPDATE")
    public String UDRLSTOPDATE;//完成时间
    @DatabaseField(columnName = "SCHEDSTART")
    public String SCHEDSTART;//计划开始时间
    @DatabaseField(columnName = "SCHEDFINISH")
    public String SCHEDFINISH;//计划完成时间
    @DatabaseField(columnName = "ESTDUR")
    public int ESTDUR;//估计持续时间
    @DatabaseField(columnName = "OWNER")
    public String OWNER;//负责人
    @DatabaseField(columnName = "OWNERNAME")
    public String OWNERNAME;//负责人描述
    @DatabaseField(columnName = "ACTSTART")
    public String ACTSTART;//实际开始时间
    @DatabaseField(columnName = "ACTFINISH")
    public String ACTFINISH;//实际完成时间
    @DatabaseField(columnName = "UDSTARTTIME")
    public String UDSTARTTIME;//开始时间
    @DatabaseField(columnName = "UDENDTIME")
    public String UDENDTIME;//结束时间
    @DatabaseField(columnName = "UDZYSCORN")
    public String UDZYSCORN;//工作任务
    @DatabaseField(columnName = "UDZYSBASIC")
    public String UDZYSBASIC;//执行标准
    @DatabaseField(columnName = "PERINSPR")
    public String  PERINSPR;//验收/排查/定检结果
    @DatabaseField(columnName = "UDPROBDESC")
    public String UDPROBDESC;//问题描述
    @DatabaseField(columnName = "UDREMARK")
    public String UDREMARK;//备注
    @DatabaseField(columnName = "LEAD")
    public String LEAD;//整改责任人
    @DatabaseField(columnName = "UDZGMEASURE")
    public String UDZGMEASURE;//整改方案/整改措施及建议/不合格修正措施负责人
    @DatabaseField(columnName = "UDZGLIMIT")
    public String UDZGLIMIT;//整改期限
    @DatabaseField(columnName = "UDZGRESULT")
    public String UDZGRESULT;//整改完成情况/整改结果验证
    @DatabaseField(columnName = "UDACSTARTTIME")
    public String UDACSTARTTIME;//计划开始时间
    @DatabaseField(columnName = "UDACSTOPTIME")
    public String UDACSTOPTIME;//计划完成时间
    @DatabaseField(columnName = "UDINSPOBY")
    public String UDINSPOBY;//负责人
    @DatabaseField(columnName = "UDINSPOBYNAME")
    public String UDINSPOBYNAME;//负责人

    @DatabaseField(columnName = "belongid")
    public int belongid;//所属工单本地存储id
    @DatabaseField(columnName = "isUpload")
    public boolean isUpload;//是否是服务器数据
    @DatabaseField(columnName = "WONUM")
    public String WONUM;
    @DatabaseField(columnName = "TYPE")
    public String TYPE;

    public int getWORKORDERID() {
        return WORKORDERID;
    }

    public void setWORKORDERID(int WORKORDERID) {
        this.WORKORDERID = WORKORDERID;
    }

    public String getUDINSUNIT() {
        return UDINSUNIT;
    }

    public void setUDINSUNIT(String UDINSUNIT) {
        this.UDINSUNIT = UDINSUNIT;
    }

    public String getTASKID() {
        return TASKID;
    }

    public void setTASKID(String TASKID) {
        this.TASKID = TASKID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getWOJO1() {
        return WOJO1;
    }

    public void setWOJO1(String WOJO1) {
        this.WOJO1 = WOJO1;
    }

    public String getWOJO2() {
        return WOJO2;
    }

    public void setWOJO2(String WOJO2) {
        this.WOJO2 = WOJO2;
    }

    public String getWOJO3() {
        return WOJO3;
    }

    public void setWOJO3(String WOJO3) {
        this.WOJO3 = WOJO3;
    }

    public String getWOJO4() {
        return WOJO4;
    }

    public void setWOJO4(String WOJO4) {
        this.WOJO4 = WOJO4;
    }

    public String getUDSAFTYDESC() {
        return UDSAFTYDESC;
    }

    public void setUDSAFTYDESC(String UDSAFTYDESC) {
        this.UDSAFTYDESC = UDSAFTYDESC;
    }

    public String getUDZGSTU() {
        return UDZGSTU;
    }

    public void setUDZGSTU(String UDZGSTU) {
        this.UDZGSTU = UDZGSTU;
    }

    public String getUDRPRRSB() {
        return UDRPRRSB;
    }

    public void setUDRPRRSB(String UDRPRRSB) {
        this.UDRPRRSB = UDRPRRSB;
    }

    public String getALLPOWER() {
        return ALLPOWER;
    }

    public void setALLPOWER(String ALLPOWER) {
        this.ALLPOWER = ALLPOWER;
    }

    public String getALLOPTIME() {
        return ALLOPTIME;
    }

    public void setALLOPTIME(String ALLOPTIME) {
        this.ALLOPTIME = ALLOPTIME;
    }

    public String getINVCONTENT() {
        return INVCONTENT;
    }

    public void setINVCONTENT(String INVCONTENT) {
        this.INVCONTENT = INVCONTENT;
    }

    public String getUDRLSTOPDATE() {
        return UDRLSTOPDATE;
    }

    public void setUDRLSTOPDATE(String UDRLSTOPDATE) {
        this.UDRLSTOPDATE = UDRLSTOPDATE;
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

    public int getESTDUR() {
        return ESTDUR;
    }

    public void setESTDUR(int ESTDUR) {
        this.ESTDUR = ESTDUR;
    }

    public String getOWNER() {
        return OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
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

    public String getUDSTARTTIME() {
        return UDSTARTTIME;
    }

    public void setUDSTARTTIME(String UDSTARTTIME) {
        this.UDSTARTTIME = UDSTARTTIME;
    }

    public String getUDENDTIME() {
        return UDENDTIME;
    }

    public void setUDENDTIME(String UDENDTIME) {
        this.UDENDTIME = UDENDTIME;
    }

    public String getUDZYSCORN() {
        return UDZYSCORN;
    }

    public void setUDZYSCORN(String UDZYSCORN) {
        this.UDZYSCORN = UDZYSCORN;
    }

    public String getUDZYSBASIC() {
        return UDZYSBASIC;
    }

    public void setUDZYSBASIC(String UDZYSBASIC) {
        this.UDZYSBASIC = UDZYSBASIC;
    }

    public String getPERINSPR() {
        return PERINSPR;
    }

    public void setPERINSPR(String PERINSPR) {
        this.PERINSPR = PERINSPR;
    }

    public String getUDPROBDESC() {
        return UDPROBDESC;
    }

    public void setUDPROBDESC(String UDPROBDESC) {
        this.UDPROBDESC = UDPROBDESC;
    }

    public String getUDREMARK() {
        return UDREMARK;
    }

    public void setUDREMARK(String UDREMARK) {
        this.UDREMARK = UDREMARK;
    }

    public String getLEAD() {
        return LEAD;
    }

    public void setLEAD(String LEAD) {
        this.LEAD = LEAD;
    }

    public String getUDZGMEASURE() {
        return UDZGMEASURE;
    }

    public void setUDZGMEASURE(String UDZGMEASURE) {
        this.UDZGMEASURE = UDZGMEASURE;
    }

    public String getUDZGLIMIT() {
        return UDZGLIMIT;
    }

    public void setUDZGLIMIT(String UDZGLIMIT) {
        this.UDZGLIMIT = UDZGLIMIT;
    }

    public String getUDZGRESULT() {
        return UDZGRESULT;
    }

    public void setUDZGRESULT(String UDZGRESULT) {
        this.UDZGRESULT = UDZGRESULT;
    }

    public String getUDACSTARTTIME() {
        return UDACSTARTTIME;
    }

    public void setUDACSTARTTIME(String UDACSTARTTIME) {
        this.UDACSTARTTIME = UDACSTARTTIME;
    }

    public String getUDACSTOPTIME() {
        return UDACSTOPTIME;
    }

    public void setUDACSTOPTIME(String UDACSTOPTIME) {
        this.UDACSTOPTIME = UDACSTOPTIME;
    }

    public String getWONUM() {
        return WONUM;
    }

    public void setWONUM(String WONUM) {
        this.WONUM = WONUM;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }

    public String getUDRPRRSBNAME() {
        return UDRPRRSBNAME;
    }

    public void setUDRPRRSBNAME(String UDRPRRSBNAME) {
        this.UDRPRRSBNAME = UDRPRRSBNAME;
    }

    public String getOWNERNAME() {
        return OWNERNAME;
    }

    public void setOWNERNAME(String OWNERNAME) {
        this.OWNERNAME = OWNERNAME;
    }

    public String getUDINSPOBY() {
        return UDINSPOBY;
    }

    public void setUDINSPOBY(String UDINSPOBY) {
        this.UDINSPOBY = UDINSPOBY;
    }

    public String getUDINSPOBYNAME() {
        return UDINSPOBYNAME;
    }

    public void setUDINSPOBYNAME(String UDINSPOBYNAME) {
        this.UDINSPOBYNAME = UDINSPOBYNAME;
    }
}
