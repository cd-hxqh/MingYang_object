package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/23.
 * 调试工单子表
 */
public class UddebugWorkOrderLine implements Serializable{
    public String WINDDRIVENGENERATORNUM;//风机编码
    public String FJLOCATION;//机台号
    public String DYNAMICDEBUGDATE;//调试日期
    public String SYNCHRONIZATIONDEBUGDATE;//并网运行日期
    public String TIME1;//静态调试日期
    public String TIME2;//动态调试日期
    public String VESION;//程序版本号
    public String RESPONSIBLEPERSON;//调试责任人
    public String DEBUGLEADER;//调试组长
    public String CREW;//调试工程师1
    public String CREW2;//调试工程师2
    public String CREW3;//调试工程师3
    public String QUESTION;//问题记录
    public String DISPOSE;//处理过程
    public String REMARK;//备注

    public String DEBUGWORKORDERNUM;//所属工单号

    public String getWINDDRIVENGENERATORNUM() {
        return WINDDRIVENGENERATORNUM;
    }

    public void setWINDDRIVENGENERATORNUM(String WINDDRIVENGENERATORNUM) {
        this.WINDDRIVENGENERATORNUM = WINDDRIVENGENERATORNUM;
    }

    public String getFJLOCATION() {
        return FJLOCATION;
    }

    public void setFJLOCATION(String FJLOCATION) {
        this.FJLOCATION = FJLOCATION;
    }

    public String getDYNAMICDEBUGDATE() {
        return DYNAMICDEBUGDATE;
    }

    public void setDYNAMICDEBUGDATE(String DYNAMICDEBUGDATE) {
        this.DYNAMICDEBUGDATE = DYNAMICDEBUGDATE;
    }

    public String getSYNCHRONIZATIONDEBUGDATE() {
        return SYNCHRONIZATIONDEBUGDATE;
    }

    public void setSYNCHRONIZATIONDEBUGDATE(String SYNCHRONIZATIONDEBUGDATE) {
        this.SYNCHRONIZATIONDEBUGDATE = SYNCHRONIZATIONDEBUGDATE;
    }

    public String getTIME1() {
        return TIME1;
    }

    public void setTIME1(String TIME1) {
        this.TIME1 = TIME1;
    }

    public String getTIME2() {
        return TIME2;
    }

    public void setTIME2(String TIME2) {
        this.TIME2 = TIME2;
    }

    public String getVESION() {
        return VESION;
    }

    public void setVESION(String VESION) {
        this.VESION = VESION;
    }

    public String getRESPONSIBLEPERSON() {
        return RESPONSIBLEPERSON;
    }

    public void setRESPONSIBLEPERSON(String RESPONSIBLEPERSON) {
        this.RESPONSIBLEPERSON = RESPONSIBLEPERSON;
    }

    public String getDEBUGLEADER() {
        return DEBUGLEADER;
    }

    public void setDEBUGLEADER(String DEBUGLEADER) {
        this.DEBUGLEADER = DEBUGLEADER;
    }

    public String getCREW() {
        return CREW;
    }

    public void setCREW(String CREW) {
        this.CREW = CREW;
    }

    public String getCREW2() {
        return CREW2;
    }

    public void setCREW2(String CREW2) {
        this.CREW2 = CREW2;
    }

    public String getCREW3() {
        return CREW3;
    }

    public void setCREW3(String CREW3) {
        this.CREW3 = CREW3;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getDISPOSE() {
        return DISPOSE;
    }

    public void setDISPOSE(String DISPOSE) {
        this.DISPOSE = DISPOSE;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getDEBUGWORKORDERNUM() {
        return DEBUGWORKORDERNUM;
    }

    public void setDEBUGWORKORDERNUM(String DEBUGWORKORDERNUM) {
        this.DEBUGWORKORDERNUM = DEBUGWORKORDERNUM;
    }
}
