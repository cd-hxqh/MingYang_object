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
    public String NAME;//负责人描述
    public String PHONE;//电话
    public String PROPHASE;//当前项目阶段
    public String WORKJOB;//当日工作内容
    public String REMARK1;//备注

    public String DZNUM;//机位号

    public String PRORUNLOGNUM;//

    public String CLXPRODUCTION;//主机累计到货数q
    public String COMPCHECKING;//轮毂累计到货数clx
    public String COMPRUNNING;//叶片累计到货数e

    public String BASECOMP;//基础浇筑完成累计数r
    public String BPQPRODUCTION;//吊装完成累计数t
    public String DEBUGGING;//电气安装完成累计数y
    public String DEBUGGING2;//安装验收完成累计数u

    public String DATE1;//试运行开始日期160829
    public String DATE2;//试运行完成日期null
    public String DATE3;//预验收完成日期null

    public String DEBUGGINGCHECK;//试运行台数q
    public String DZCOMP;//试运行完成台数w
    public String DZSTART;//预验收完成台数e

    public String TITLE;
    public String TYPE;
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

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
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

    public String getREMARK1() {
        return REMARK1;
    }

    public void setREMARK1(String REMARK1) {
        this.REMARK1 = REMARK1;
    }

    public String getDZNUM() {
        return DZNUM;
    }

    public void setDZNUM(String DZNUM) {
        this.DZNUM = DZNUM;
    }

    public String getPRORUNLOGNUM() {
        return PRORUNLOGNUM;
    }

    public void setPRORUNLOGNUM(String PRORUNLOGNUM) {
        this.PRORUNLOGNUM = PRORUNLOGNUM;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public String getCLXPRODUCTION() {
        return CLXPRODUCTION;
    }

    public void setCLXPRODUCTION(String CLXPRODUCTION) {
        this.CLXPRODUCTION = CLXPRODUCTION;
    }

    public String getCOMPCHECKING() {
        return COMPCHECKING;
    }

    public void setCOMPCHECKING(String COMPCHECKING) {
        this.COMPCHECKING = COMPCHECKING;
    }

    public String getCOMPRUNNING() {
        return COMPRUNNING;
    }

    public void setCOMPRUNNING(String COMPRUNNING) {
        this.COMPRUNNING = COMPRUNNING;
    }

    public String getBASECOMP() {
        return BASECOMP;
    }

    public void setBASECOMP(String BASECOMP) {
        this.BASECOMP = BASECOMP;
    }

    public String getBPQPRODUCTION() {
        return BPQPRODUCTION;
    }

    public void setBPQPRODUCTION(String BPQPRODUCTION) {
        this.BPQPRODUCTION = BPQPRODUCTION;
    }

    public String getDEBUGGING() {
        return DEBUGGING;
    }

    public void setDEBUGGING(String DEBUGGING) {
        this.DEBUGGING = DEBUGGING;
    }

    public String getDEBUGGING2() {
        return DEBUGGING2;
    }

    public void setDEBUGGING2(String DEBUGGING2) {
        this.DEBUGGING2 = DEBUGGING2;
    }

    public String getDATE1() {
        return DATE1;
    }

    public void setDATE1(String DATE1) {
        this.DATE1 = DATE1;
    }

    public String getDATE2() {
        return DATE2;
    }

    public void setDATE2(String DATE2) {
        this.DATE2 = DATE2;
    }

    public String getDATE3() {
        return DATE3;
    }

    public void setDATE3(String DATE3) {
        this.DATE3 = DATE3;
    }

    public String getDEBUGGINGCHECK() {
        return DEBUGGINGCHECK;
    }

    public void setDEBUGGINGCHECK(String DEBUGGINGCHECK) {
        this.DEBUGGINGCHECK = DEBUGGINGCHECK;
    }

    public String getDZCOMP() {
        return DZCOMP;
    }

    public void setDZCOMP(String DZCOMP) {
        this.DZCOMP = DZCOMP;
    }

    public String getDZSTART() {
        return DZSTART;
    }

    public void setDZSTART(String DZSTART) {
        this.DZSTART = DZSTART;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
}
