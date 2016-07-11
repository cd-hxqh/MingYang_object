package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/27.
 */
public class Person implements Serializable {
    public String PERSONID;//编号
    public String DISPLAYNAME;//名称
    public String BRANCH;//中心
    public String DEPARTMENT;//部门
    public String PRIMARYPHONE;//电话
    public String UDJBDESCRIPTION;//
    public String UDPRONUM;//
    public String DEPARTDESC;//部门

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }

    public String getDISPLAYNAME() {
        return DISPLAYNAME;
    }

    public void setDISPLAYNAME(String DISPLAYNAME) {
        this.DISPLAYNAME = DISPLAYNAME;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
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

    public String getDEPARTDESC() {
        return DEPARTDESC;
    }

    public void setDEPARTDESC(String DEPARTDESC) {
        this.DEPARTDESC = DEPARTDESC;
    }
}
