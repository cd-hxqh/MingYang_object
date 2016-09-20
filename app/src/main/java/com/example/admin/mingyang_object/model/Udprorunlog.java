package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 项目日报
 */
public class Udprorunlog extends Entity implements Serializable {

    public String PRORUNLOGNUM; //日报编号
    public String DESCRIPTION; //描述
    public String BRANCH; //所属中心
    public String PRONUM; //项目编号
    public String PRODESC;//项目描述
    public String CONTRACTS; //现场联系人编号
    public String CONTDESC; //现场联系人名称
    public String PROSTAGE; //项目阶段
    public String STATUS; //状态
    public String UDPRORESC; //现场负责人描述
    public String RESPONSID;//现场负责人编号  子表用
    public String PHONENUM;//电话号码
    public String YEAR; //年
    public String MONTH; //月
    public String CHANGEBY;//修改人
    public String CHANGEDATE;//修改时间

    public boolean isnew;//是否是新增项目日报

    public String getCONTDESC() {
        return CONTDESC;
    }

    public void setCONTDESC(String CONTDESC) {
        this.CONTDESC = CONTDESC;
    }

    public String getPRORUNLOGNUM() {
        return PRORUNLOGNUM;
    }

    public void setPRORUNLOGNUM(String PRORUNLOGNUM) {
        this.PRORUNLOGNUM = PRORUNLOGNUM;
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

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getCONTRACTS() {
        return CONTRACTS;
    }

    public void setCONTRACTS(String CONTRACTS) {
        this.CONTRACTS = CONTRACTS;
    }

    public String getPROSTAGE() {
        return PROSTAGE;
    }

    public void setPROSTAGE(String PROSTAGE) {
        this.PROSTAGE = PROSTAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getUDPRORESC() {
        return UDPRORESC;
    }

    public void setUDPRORESC(String UDPRORESC) {
        this.UDPRORESC = UDPRORESC;
    }

    public String getRESPONSID() {
        return RESPONSID;
    }

    public void setRESPONSID(String RESPONSID) {
        this.RESPONSID = RESPONSID;
    }

    public boolean isnew() {
        return isnew;
    }

    public void setisnew(boolean isnew) {
        this.isnew = isnew;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getPHONENUM() {
        return PHONENUM;
    }

    public void setPHONENUM(String PHONENUM) {
        this.PHONENUM = PHONENUM;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getCHANGEBY() {
        return CHANGEBY;
    }

    public void setCHANGEBY(String CHANGEBY) {
        this.CHANGEBY = CHANGEBY;
    }

    public String getCHANGEDATE() {
        return CHANGEDATE;
    }

    public void setCHANGEDATE(String CHANGEDATE) {
        this.CHANGEDATE = CHANGEDATE;
    }
}
