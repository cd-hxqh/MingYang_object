package com.example.admin.eam.model;

import java.io.Serializable;

/**
 * Created by think on 2016/8/10.
 */
public class Udrunlogr implements Serializable {

    public String UDRUNLOGRID;//运行记录id
    public String LOGNUM;//运行日志编号
    public String DESCRIPTION;//描述
    public String BRANCH;//中心编号
    public String BRANCHDESC;//中心描述
    public String PRONUM;//项目编号
    public String PRODESC;//项目描述
    public String YEAR;//年
    public String MONTH;//月
    public String PROHEAD;//负责人
    public String NAME1;//负责人描述
    public String CREATER;//录入人编号
    public String CREATENAME;//录入人描述
    public String CREATETIME;//录入时间

    public boolean isnew;//是否是新增运行记录


    public String getUDRUNLOGRID() {
        return UDRUNLOGRID;
    }

    public void setUDRUNLOGRID(String UDRUNLOGRID) {
        this.UDRUNLOGRID = UDRUNLOGRID;
    }

    public String getLOGNUM() {
        return LOGNUM;
    }

    public void setLOGNUM(String LOGNUM) {
        this.LOGNUM = LOGNUM;
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

    public String getBRANCHDESC() {
        return BRANCHDESC;
    }

    public void setBRANCHDESC(String BRANCHDESC) {
        this.BRANCHDESC = BRANCHDESC;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
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

    public String getPROHEAD() {
        return PROHEAD;
    }

    public void setPROHEAD(String PROHEAD) {
        this.PROHEAD = PROHEAD;
    }

    public String getNAME1() {
        return NAME1;
    }

    public void setNAME1(String NAME1) {
        this.NAME1 = NAME1;
    }

    public String getCREATER() {
        return CREATER;
    }

    public void setCREATER(String CREATER) {
        this.CREATER = CREATER;
    }

    public String getCREATENAME() {
        return CREATENAME;
    }

    public void setCREATENAME(String CREATENAME) {
        this.CREATENAME = CREATENAME;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }
}
