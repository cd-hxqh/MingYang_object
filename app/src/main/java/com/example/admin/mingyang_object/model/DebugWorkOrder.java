package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/23.
 */
public class DebugWorkOrder implements Serializable {
    public String DEBUGWORKORDERNUM;//工单编号
    public String DESCRIPTION;//描述
    public String PRONUM;//项目编号
    public String STATUS;//状态
    public String CREATEBY;//创建人
    public String PLANSTART;//计划开始时间
    public String PLANEND;//计划结束时间


    public boolean isnew;

    public String getDEBUGWORKORDERNUM() {
        return DEBUGWORKORDERNUM;
    }

    public void setDEBUGWORKORDERNUM(String DEBUGWORKORDERNUM) {this.DEBUGWORKORDERNUM = DEBUGWORKORDERNUM;}

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getPLANSTART() {return PLANSTART;}

    public void setPLANSTART(String PLANSTART) {this.PLANSTART = PLANSTART;}

    public String getPLANEND() {return PLANEND;}

    public void setPLANEND(String PLANEND) {this.PLANEND = PLANEND;}
}
