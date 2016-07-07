package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 *项目人员表
 */
public class UdPerson implements Serializable {

    private String DISPLAYNAME; //姓名
    private String PERSONID; //工号
    private String PRIMARYPHONE; //手机
    private String UDJBDESCRIPTION; //职务
    private String UDPRONUM; //项目台账编号
    private String BRANCH;//中心
    private String DEPARTDESC;//部门
    private String EMAIL;//邮箱

    public String getDISPLAYNAME() {
        return DISPLAYNAME;
    }

    public void setDISPLAYNAME(String DISPLAYNAME) {
        this.DISPLAYNAME = DISPLAYNAME;
    }

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }

    public String getPRIMARYPHONE() {
        return PRIMARYPHONE;
    }

    public void setPRIMARYPHONE(String PRIMARYPHONE) {
        this.PRIMARYPHONE = PRIMARYPHONE;
    }

    public String getUDJBDESCRIPTION() {
        return UDJBDESCRIPTION;
    }

    public void setUDJBDESCRIPTION(String UDJBDESCRIPTION) {
        this.UDJBDESCRIPTION = UDJBDESCRIPTION;
    }

    public String getUDPRONUM() {
        return UDPRONUM;
    }

    public void setUDPRONUM(String UDPRONUM) {
        this.UDPRONUM = UDPRONUM;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getDEPARTDESC() {
        return DEPARTDESC;
    }

    public void setDEPARTDESC(String DEPARTDESC) {
        this.DEPARTDESC = DEPARTDESC;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
}
