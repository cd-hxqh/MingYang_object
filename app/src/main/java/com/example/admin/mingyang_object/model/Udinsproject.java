package com.example.admin.mingyang_object.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 巡检单
 */
@DatabaseTable(tableName = "Udinsproject")
public class Udinsproject extends Entity implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "UDINSPROJECTID")
    public String UDINSPROJECTID;//
    @DatabaseField(columnName = "JPTASK")
    public String JPTASK; //任务编号
    @DatabaseField(columnName = "DESCRIPTION")
    public String DESCRIPTION; //描述
    @DatabaseField(columnName = "JO1")
    public String JO1; //系统/项目
    @DatabaseField(columnName = "JO2")
    public String JO2;//子系统/子项目
    @DatabaseField(columnName = "JO3")
    public String JO3; //标准/检修方法
    @DatabaseField(columnName = "INSPUNIT")
    public String INSPUNIT; //巡检部位
    @DatabaseField(columnName = "SERIALNUM")
    public String SERIALNUM; //序号
    @DatabaseField(columnName = "OK")
    public String OK; //巡检结果？
    @DatabaseField(columnName = "INSPCONTENT")
    public String INSPCONTENT; //巡检不合格原因
    @DatabaseField(columnName = "INSPONUM")
    public String INSPONUM; //巡检单编号

    @DatabaseField(columnName = "belongid")
    public int belongid;//所属巡检单单本地存储id
    @DatabaseField(columnName = "isUpload")
    public boolean isUpload;//是否是服务器数据
    @DatabaseField(columnName = "TYPE")
    public String TYPE;

    public String getUDINSPROJECTID() {
        return UDINSPROJECTID;
    }

    public void setUDINSPROJECTID(String UDINSPROJECTID) {
        this.UDINSPROJECTID = UDINSPROJECTID;
    }

    public String getJPTASK() {
        return JPTASK;
    }

    public void setJPTASK(String JPTASK) {
        this.JPTASK = JPTASK;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getJO1() {
        return JO1;
    }

    public void setJO1(String JO1) {
        this.JO1 = JO1;
    }

    public String getJO2() {
        return JO2;
    }

    public void setJO2(String JO2) {
        this.JO2 = JO2;
    }

    public String getJO3() {
        return JO3;
    }

    public void setJO3(String JO3) {
        this.JO3 = JO3;
    }

    public String getINSPUNIT() {
        return INSPUNIT;
    }

    public void setINSPUNIT(String INSPUNIT) {
        this.INSPUNIT = INSPUNIT;
    }

    public String getSERIALNUM() {
        return SERIALNUM;
    }

    public void setSERIALNUM(String SERIALNUM) {
        this.SERIALNUM = SERIALNUM;
    }

    public String getOK() {
        return OK;
    }

    public void setOK(String OK) {
        this.OK = OK;
    }

    public String getINSPCONTENT() {
        return INSPCONTENT;
    }

    public void setINSPCONTENT(String INSPCONTENT) {
        this.INSPCONTENT = INSPCONTENT;
    }

    public String getINSPONUM() {
        return INSPONUM;
    }

    public void setINSPONUM(String INSPONUM) {
        this.INSPONUM = INSPONUM;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
