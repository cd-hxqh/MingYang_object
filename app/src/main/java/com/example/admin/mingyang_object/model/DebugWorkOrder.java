package com.example.admin.mingyang_object.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/6/23.
 */
@DatabaseTable(tableName = "DebugWorkOrder")
public class DebugWorkOrder implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "DEBUGWORKORDERID")
    public String DEBUGWORKORDERID;//工单ID
    @DatabaseField(columnName = "DEBUGWORKORDERNUM")
    public String DEBUGWORKORDERNUM;//工单编号
    @DatabaseField(columnName = "DESCRIPTION")
    public String DESCRIPTION;//描述
    @DatabaseField(columnName = "PRONUM")
    public String PRONUM;//项目编号
    @DatabaseField(columnName = "STATUS")
    public String STATUS;//状态
    @DatabaseField(columnName = "CREATEBY")
    public String CREATEBY;//创建人
    @DatabaseField(columnName = "PLANSTART")
    public String PLANSTART;//计划开始时间
    @DatabaseField(columnName = "PLANEND")
    public String PLANEND;//计划结束时间

    public boolean isnew;

    public String getDEBUGWORKORDERID() {return DEBUGWORKORDERID;}

    public void setDEBUGWORKORDERID(String DEBUGWORKORDERID) {this.DEBUGWORKORDERID = DEBUGWORKORDERID;}

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
