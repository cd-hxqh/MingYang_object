package com.example.admin.mingyang_object.model;

/**
 * Created by chris on 16/8/22.
 */
public class UdTriprePort {

    private String SERIALNUMBER;//编号
    private String DESCRIPTION;//描述
    private String ACOUNT;//出差人编号
    private String DISPLAYNAME;//出差人姓名
    private String DEPARTMENT;//部门编号
    private String DEPARTMENT_DESCRIPTION;//部门名称
    private String CREATEBY;//录入人;
    private String CREATER_DISPLAYNAME;// 录入人姓名
    private String CREATEDATE;//录入日期
    private String TRIPCODE;//出差申请编号
    private String TRIPDATE;//出差日期
    private String PROJECT;//出差项目
    private String PROJECT_DESCRIPTION;//项目名称
    private String TOPLACE;//出差地点
    private String TRIPCONTENT;//出差事由
    private String WORKCONTENT;//工作内容

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSERIALNUMBER() {
        return SERIALNUMBER;
    }

    public void setSERIALNUMBER(String SERIALNUMBER) {
        this.SERIALNUMBER = SERIALNUMBER;
    }

    public String getACOUNT() {
        return ACOUNT;
    }

    public void setACOUNT(String ACOUNT) {
        this.ACOUNT = ACOUNT;
    }

    public String getDISPLAYNAME() {
        return DISPLAYNAME;
    }

    public void setDISPLAYNAME(String DISPLAYNAME) {
        this.DISPLAYNAME = DISPLAYNAME;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public String getDEPARTMENT_DESCRIPTION() {
        return DEPARTMENT_DESCRIPTION;
    }

    public void setDEPARTMENT_DESCRIPTION(String DEPARTMENT_DESCRIPTION) {
        this.DEPARTMENT_DESCRIPTION = DEPARTMENT_DESCRIPTION;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getCREATER_DISPLAYNAME() {
        return CREATER_DISPLAYNAME;
    }

    public void setCREATER_DISPLAYNAME(String CREATER_DISPLAYNAME) {
        this.CREATER_DISPLAYNAME = CREATER_DISPLAYNAME;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getTRIPCODE() {
        return TRIPCODE;
    }

    public void setTRIPCODE(String TRIPCODE) {
        this.TRIPCODE = TRIPCODE;
    }

    public String getTRIPDATE() {
        return TRIPDATE;
    }

    public void setTRIPDATE(String TRIPDATE) {
        this.TRIPDATE = TRIPDATE;
    }

    public String getPROJECT() {
        return PROJECT;
    }

    public void setPROJECT(String PROJECT) {
        this.PROJECT = PROJECT;
    }

    public String getPROJECT_DESCRIPTION() {
        return PROJECT_DESCRIPTION;
    }

    public void setPROJECT_DESCRIPTION(String PROJECT_DESCRIPTION) {
        this.PROJECT_DESCRIPTION = PROJECT_DESCRIPTION;
    }

    public String getTOPLACE() {
        return TOPLACE;
    }

    public void setTOPLACE(String TOPLACE) {
        this.TOPLACE = TOPLACE;
    }

    public String getTRIPCONTENT() {
        return TRIPCONTENT;
    }

    public void setTRIPCONTENT(String TRIPCONTENT) {
        this.TRIPCONTENT = TRIPCONTENT;
    }

    public String getWORKCONTENT() {
        return WORKCONTENT;
    }

    public void setWORKCONTENT(String WORKCONTENT) {
        this.WORKCONTENT = WORKCONTENT;
    }
}
