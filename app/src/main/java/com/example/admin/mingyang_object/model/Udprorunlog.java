package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 项目日报
 */
public class Udprorunlog extends Entity implements Serializable {

    private String PRORUNLOGNUM; //日报编号
    private String DESCRIPTION; //描述
    private String BRANCH; //所属中心
    private String PRONUM; //项目编号
    private String CONTRACTS; //现场联系人编号
    private String CONTDESC; //现场联系人名称
    private String PROSTAGE; //项目阶段
    private String STATUS; //状态
    private String UDPRORESC; //现场负责人
    private String YEAR; //年
    private String MONTH; //月

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
}
