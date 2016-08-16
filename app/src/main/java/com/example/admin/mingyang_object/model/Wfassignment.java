package com.example.admin.mingyang_object.model;


import java.io.Serializable;

/**
 * Created by think on 2015/12/10
 * 流程审批
 */
public class Wfassignment implements Serializable {


    private String APP;//应用程序的名称
    private String UDASSIGN01;//应用程序中文名称
    private String ASSIGNCODE;//任务分配人
    private String UDASSIGN02;//流程发起人
    private String DESCRIPTION;//描述
    private String DUEDATE;//到期日期
    private String ORIGPERSON;//进行委派之前的任务分配的原始人员
    private String OWNERID;//受控制记录的唯一标识  //keyValue
    private String OWNERTABLE;//表名
    private String PROCESSNAME;//过程名称  不显示
    private String ROLEID;//任务角色  不显示
    private String STARTDATE;//流程发起日期
    private String WFASSIGNMENTID;//编号


    public String getAPP() {
        return APP;
    }

    public void setAPP(String APP) {
        this.APP = APP;
    }

    public String getASSIGNCODE() {
        return ASSIGNCODE;
    }

    public void setASSIGNCODE(String ASSIGNCODE) {
        this.ASSIGNCODE = ASSIGNCODE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDUEDATE() {
        return DUEDATE;
    }

    public void setDUEDATE(String DUEDATE) {
        this.DUEDATE = DUEDATE;
    }

    public String getORIGPERSON() {
        return ORIGPERSON;
    }

    public void setORIGPERSON(String ORIGPERSON) {
        this.ORIGPERSON = ORIGPERSON;
    }

    public String getOWNERID() {
        return OWNERID;
    }

    public void setOWNERID(String OWNERID) {
        this.OWNERID = OWNERID;
    }

    public String getOWNERTABLE() {
        return OWNERTABLE;
    }

    public void setOWNERTABLE(String OWNERTABLE) {
        this.OWNERTABLE = OWNERTABLE;
    }

    public String getPROCESSNAME() {
        return PROCESSNAME;
    }

    public void setPROCESSNAME(String PROCESSNAME) {
        this.PROCESSNAME = PROCESSNAME;
    }

    public String getROLEID() {
        return ROLEID;
    }

    public void setROLEID(String ROLEID) {
        this.ROLEID = ROLEID;
    }

    public String getSTARTDATE() {
        return STARTDATE;
    }

    public void setSTARTDATE(String STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    public String getWFASSIGNMENTID() {
        return WFASSIGNMENTID;
    }

    public void setWFASSIGNMENTID(String WFASSIGNMENTID) {
        this.WFASSIGNMENTID = WFASSIGNMENTID;
    }

    public String getUDASSIGN01() {
        return UDASSIGN01;
    }

    public void setUDASSIGN01(String UDASSIGN01) {
        this.UDASSIGN01 = UDASSIGN01;
    }

    public String getUDASSIGN02() {
        return UDASSIGN02;
    }

    public void setUDASSIGN02(String UDASSIGN02) {
        this.UDASSIGN02 = UDASSIGN02;
    }
}
