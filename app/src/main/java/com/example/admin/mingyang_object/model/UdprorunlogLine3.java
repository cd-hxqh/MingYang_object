package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/7/6.
 * 工作日报
 */
public class UdprorunlogLine3 implements Serializable{
    public int UDPRORUNLOGLINEID;

    public String RUNLOGDATE;//日期
    public String DESCRIPTION;//描述
    public String WEATHER;//天气
    public double TEM;//温度(℃)
    public double WINDSPEED;//平均风速(m/s)

    public String WORKTYPE;//工作性质
    public String WORKPG;//工作班成员
    public String WORKCRON;//工作任务
    public String COMPSTA;//完成情况
    public String SITUATION;//异常情况说明
    public String REMARK;//备注

    public String TYPE;
    public String PRORUNLOGNUM;
    public boolean isUpload;

    public int getUDPRORUNLOGLINEID() {
        return UDPRORUNLOGLINEID;
    }

    public void setUDPRORUNLOGLINEID(int UDPRORUNLOGLINEID) {
        this.UDPRORUNLOGLINEID = UDPRORUNLOGLINEID;
    }

    public String getRUNLOGDATE() {
        return RUNLOGDATE;
    }

    public void setRUNLOGDATE(String RUNLOGDATE) {
        this.RUNLOGDATE = RUNLOGDATE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getWEATHER() {
        return WEATHER;
    }

    public void setWEATHER(String WEATHER) {
        this.WEATHER = WEATHER;
    }

    public double getTEM() {
        return TEM;
    }

    public void setTEM(double TEM) {
        this.TEM = TEM;
    }

    public double getWINDSPEED() {
        return WINDSPEED;
    }

    public void setWINDSPEED(double WINDSPEED) {
        this.WINDSPEED = WINDSPEED;
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

    public String getWORKTYPE() {
        return WORKTYPE;
    }

    public void setWORKTYPE(String WORKTYPE) {
        this.WORKTYPE = WORKTYPE;
    }

    public String getWORKPG() {
        return WORKPG;
    }

    public void setWORKPG(String WORKPG) {
        this.WORKPG = WORKPG;
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

    public String getSITUATION() {
        return SITUATION;
    }

    public void setSITUATION(String SITUATION) {
        this.SITUATION = SITUATION;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setisUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }
}
