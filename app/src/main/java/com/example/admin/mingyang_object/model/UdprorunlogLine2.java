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

    public String FUNNUM;//机位号
//    public String REMARK1;//备注
    public String STRING1;//吊装开始
    public String STRING2;//吊装完成
    public String STRING3;//安装验收
    public String STRING4;//并网调试
    public String STRING5;//动态调试
    public String STRING6;//调试验收
    public String STRING7;//试运行开始
    public String STRING8;//试运行完成
    public String STRING9;//预验收完成

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

    public String getFUNNUM() {
        return FUNNUM;
    }

    public void setFUNNUM(String FUNNUM) {
        this.FUNNUM = FUNNUM;
    }

    public String getSTRING1() {
        return STRING1;
    }

    public void setSTRING1(String STRING1) {
        this.STRING1 = STRING1;
    }

    public String getSTRING2() {
        return STRING2;
    }

    public void setSTRING2(String STRING2) {
        this.STRING2 = STRING2;
    }

    public String getSTRING3() {
        return STRING3;
    }

    public void setSTRING3(String STRING3) {
        this.STRING3 = STRING3;
    }

    public String getSTRING4() {
        return STRING4;
    }

    public void setSTRING4(String STRING4) {
        this.STRING4 = STRING4;
    }

    public String getSTRING5() {
        return STRING5;
    }

    public void setSTRING5(String STRING5) {
        this.STRING5 = STRING5;
    }

    public String getSTRING6() {
        return STRING6;
    }

    public void setSTRING6(String STRING6) {
        this.STRING6 = STRING6;
    }

    public String getSTRING7() {
        return STRING7;
    }

    public void setSTRING7(String STRING7) {
        this.STRING7 = STRING7;
    }

    public String getSTRING8() {
        return STRING8;
    }

    public void setSTRING8(String STRING8) {
        this.STRING8 = STRING8;
    }

    public String getSTRING9() {
        return STRING9;
    }

    public void setSTRING9(String STRING9) {
        this.STRING9 = STRING9;
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
