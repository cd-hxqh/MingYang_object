package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 巡检单
 */
public class Udinsproject extends Entity implements Serializable {

    private String JPTASK; //任务编号
    private String DESCRIPTION; //描述
    private String JO1; //系统/项目
    private String JO2;//子系统/子项目
    private String JO3; //标准/检修方法
    private String INSPUNIT; //巡检部位
    private String SERIALNUM; //序号
    private String OK; //巡检结果？
    private String INSPCONTENT; //巡检不合格原因
    private String INSPONUM; //巡检单编号

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
}