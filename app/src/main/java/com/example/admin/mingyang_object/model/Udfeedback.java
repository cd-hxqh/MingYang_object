package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 问题联络单
 */
public class Udfeedback extends Entity implements Serializable {

    private String FEEDBACKNUM; //编号
    private String DESCRIPTION; //描述
    private String PROBLEMTYPE; //问题类型
    private String EMERGENCY; //紧急程度
    private String WORKORDERNUM; //关联故障工单号
    private String PROBLEMSITUATION; //现场问题及进展情况描述
    private String PRONUM; //项目编号
    private String CREATEBY; //需求提出人编号
    private String CREATENAME; //需求提出人名称
    private String CREATEDATE; //提出时间
    private String STATUS; //状态
    private String SUPPORTDEPT; //支持部门编号
    private String LEADER; //支持部门领导编号
    private String RESPONSETIME; //需求完成时间
    private String SOLVEDBY; //问题处理人
    private String FOUNDTIME; //抵达时间
    private String COMPTIME; //完成时间
    private String PROGRESS; //解决问题及反馈
    private String ISSTOP; //是否解决
    private String REMARK; //说明
    private String BRANCH; //所属中心
    private String DEPT1; //提出人部门
    private String DEPT2; //支持部门名称
    private String DEPT3; //解决人所属部门
    private String PRODESC; //项目描述
    private String PHONE1; //负责人电话
    private String PHONE2; //提出人电话
    private String PHONE3; //处理人联系电话
    private String PRORES; //项目负责人
    private String SOLVENAME; //问题处理人
    private String PROSTAGE; //项目阶段



    public String getFEEDBACKNUM() {
        return FEEDBACKNUM;
    }

    public void setFEEDBACKNUM(String FEEDBACKNUM) {
        this.FEEDBACKNUM = FEEDBACKNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPROBLEMTYPE() {
        return PROBLEMTYPE;
    }

    public void setPROBLEMTYPE(String PROBLEMTYPE) {
        this.PROBLEMTYPE = PROBLEMTYPE;
    }

    public String getEMERGENCY() {
        return EMERGENCY;
    }

    public void setEMERGENCY(String EMERGENCY) {
        this.EMERGENCY = EMERGENCY;
    }

    public String getWORKORDERNUM() {
        return WORKORDERNUM;
    }

    public void setWORKORDERNUM(String WORKORDERNUM) {
        this.WORKORDERNUM = WORKORDERNUM;
    }

    public String getPROBLEMSITUATION() {
        return PROBLEMSITUATION;
    }

    public void setPROBLEMSITUATION(String PROBLEMSITUATION) {
        this.PROBLEMSITUATION = PROBLEMSITUATION;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSUPPORTDEPT() {
        return SUPPORTDEPT;
    }

    public void setSUPPORTDEPT(String SUPPORTDEPT) {
        this.SUPPORTDEPT = SUPPORTDEPT;
    }

    public String getLEADER() {
        return LEADER;
    }

    public void setLEADER(String LEADER) {
        this.LEADER = LEADER;
    }

    public String getRESPONSETIME() {
        return RESPONSETIME;
    }

    public void setRESPONSETIME(String RESPONSETIME) {
        this.RESPONSETIME = RESPONSETIME;
    }

    public String getSOLVEDBY() {
        return SOLVEDBY;
    }

    public void setSOLVEDBY(String SOLVEDBY) {
        this.SOLVEDBY = SOLVEDBY;
    }

    public String getFOUNDTIME() {
        return FOUNDTIME;
    }

    public void setFOUNDTIME(String FOUNDTIME) {
        this.FOUNDTIME = FOUNDTIME;
    }

    public String getCOMPTIME() {
        return COMPTIME;
    }

    public void setCOMPTIME(String COMPTIME) {
        this.COMPTIME = COMPTIME;
    }

    public String getPROGRESS() {
        return PROGRESS;
    }

    public void setPROGRESS(String PROGRESS) {
        this.PROGRESS = PROGRESS;
    }

    public String getISSTOP() {
        return ISSTOP;
    }

    public void setISSTOP(String ISSTOP) {
        this.ISSTOP = ISSTOP;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getCREATENAME() {
        return CREATENAME;
    }

    public void setCREATENAME(String CREATENAME) {
        this.CREATENAME = CREATENAME;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getDEPT1() {
        return DEPT1;
    }

    public void setDEPT1(String DEPT1) {
        this.DEPT1 = DEPT1;
    }

    public String getDEPT2() {
        return DEPT2;
    }

    public void setDEPT2(String DEPT2) {
        this.DEPT2 = DEPT2;
    }

    public String getDEPT3() {
        return DEPT3;
    }

    public void setDEPT3(String DEPT3) {
        this.DEPT3 = DEPT3;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getPHONE1() {
        return PHONE1;
    }

    public void setPHONE1(String PHONE1) {
        this.PHONE1 = PHONE1;
    }

    public String getPHONE2() {
        return PHONE2;
    }

    public void setPHONE2(String PHONE2) {
        this.PHONE2 = PHONE2;
    }

    public String getPHONE3() {
        return PHONE3;
    }

    public void setPHONE3(String PHONE3) {
        this.PHONE3 = PHONE3;
    }

    public String getPRORES() {
        return PRORES;
    }

    public void setPRORES(String PRORES) {
        this.PRORES = PRORES;
    }

    public String getSOLVENAME() {
        return SOLVENAME;
    }

    public void setSOLVENAME(String SOLVENAME) {
        this.SOLVENAME = SOLVENAME;
    }

    public String getPROSTAGE() {
        return PROSTAGE;
    }

    public void setPROSTAGE(String PROSTAGE) {
        this.PROSTAGE = PROSTAGE;
    }
}
