package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/8/12.
 */
public class Udrunliner implements Serializable {

    public String UDRUNLINERID;//id
    public String UDRUNLOGLINENUM;//
    public String LOGDATE;//日期
    public String NEWDESC;//描述
    public String WEATHER;//天气
    public String TEM;//温度℃
    public String WINDSPEED;//平均风速(m/s)
    public String PERSONATTNUM;//人员考勤编号
    public String WORKNUM;//工作序号
    public String WORKPG;//工作班成员
    public String WORKTYPE;//工作性质
    public String WORKCRON;//工作任务
    public String COMPSTA;//完成情况
    public String REMARK;//备注

    public String getUDRUNLINERID() {
        return UDRUNLINERID;
    }

    public void setUDRUNLINERID(String UDRUNLINERID) {
        this.UDRUNLINERID = UDRUNLINERID;
    }

    public String getUDRUNLOGLINENUM() {
        return UDRUNLOGLINENUM;
    }

    public void setUDRUNLOGLINENUM(String UDRUNLOGLINENUM) {
        this.UDRUNLOGLINENUM = UDRUNLOGLINENUM;
    }

    public String getLOGDATE() {
        return LOGDATE;
    }

    public void setLOGDATE(String LOGDATE) {
        this.LOGDATE = LOGDATE;
    }

    public String getNEWDESC() {
        return NEWDESC;
    }

    public void setNEWDESC(String NEWDESC) {
        this.NEWDESC = NEWDESC;
    }

    public String getWEATHER() {
        return WEATHER;
    }

    public void setWEATHER(String WEATHER) {
        this.WEATHER = WEATHER;
    }

    public String getTEM() {
        return TEM;
    }

    public void setTEM(String TEM) {
        this.TEM = TEM;
    }

    public String getWINDSPEED() {
        return WINDSPEED;
    }

    public void setWINDSPEED(String WINDSPEED) {
        this.WINDSPEED = WINDSPEED;
    }

    public String getPERSONATTNUM() {
        return PERSONATTNUM;
    }

    public void setPERSONATTNUM(String PERSONATTNUM) {
        this.PERSONATTNUM = PERSONATTNUM;
    }

    public String getWORKNUM() {
        return WORKNUM;
    }

    public void setWORKNUM(String WORKNUM) {
        this.WORKNUM = WORKNUM;
    }

    public String getWORKPG() {
        return WORKPG;
    }

    public void setWORKPG(String WORKPG) {
        this.WORKPG = WORKPG;
    }

    public String getWORKTYPE() {
        return WORKTYPE;
    }

    public void setWORKTYPE(String WORKTYPE) {
        this.WORKTYPE = WORKTYPE;
    }

    public String getWORKCRON() {
        return WORKCRON;
    }

    public void setWORKCRON(String WORKCRON) {
        this.WORKCRON = WORKCRON;
    }

    public String getCOMPSTA() {
        return COMPSTA;
    }

    public void setCOMPSTA(String COMPSTA) {
        this.COMPSTA = COMPSTA;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
