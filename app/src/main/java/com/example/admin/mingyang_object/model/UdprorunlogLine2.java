package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/7/6.
 * 吊装调试日报
 */
public class UdprorunlogLine2 implements Serializable{
    public int UDPRORUNLOGLINE2ID;
    public String CREATEDATE;//日期
    public String PERSONID;//项目负责人
    public String CREATEBY;//负责人描述
    public String PROPHASE;//当前项目阶段
    public String WORKJOB;//当日工作内容
    public String REMARK;//备注

    public String TYPE;
    public String PRORUNLOGNUM;
    public boolean isUpload;

    public int getUDPRORUNLOGLINE2ID() {
        return UDPRORUNLOGLINE2ID;
    }

    public void setUDPRORUNLOGLINE2ID(int UDPRORUNLOGLINE2ID) {
        this.UDPRORUNLOGLINE2ID = UDPRORUNLOGLINE2ID;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getPROPHASE() {
        return PROPHASE;
    }

    public void setPROPHASE(String PROPHASE) {
        this.PROPHASE = PROPHASE;
    }

    public String getWORKJOB() {
        return WORKJOB;
    }

    public void setWORKJOB(String WORKJOB) {
        this.WORKJOB = WORKJOB;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getPRORUNLOGNUM() {
        return PRORUNLOGNUM;
    }

    public void setPRORUNLOGNUM(String PRORUNLOGNUM) {
        this.PRORUNLOGNUM = PRORUNLOGNUM;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setisUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }
}
