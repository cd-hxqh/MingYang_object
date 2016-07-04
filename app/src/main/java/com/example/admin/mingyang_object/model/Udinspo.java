package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 巡检单
 */
public class Udinspo extends Entity implements Serializable {

    private String ALLTIME; //累计停机时间
    private String BRANCH; //中心编号
    private String CHANGEBY; //更改人编号
    private String CHANGEDATE;//更改时间
    private String COMPTIME; //计划完成时间
    private String CREATEBY; //创建人编号
    private String CREATEDATE; //创建时间
    private String DESCRIPTION; //描述
    private String FJNUM; //设备位置
    private String INSPOBY; //巡检人员
    private String INSPOBY2; //巡检人员编号
    private String INSPOBY3; //巡检人员编号
    private String INSPODATE; //巡检日期
    private String INSPONUM; //巡检单编号
    private String INSPPLANNUM; //巡检计划编号
    private String ISSTOP; //是否停机？
    private String JPNUM; //巡检标准
    private String LASTRUNDATE; //上次巡检时间
    private String MODELTYPE; //风机型号
    private String NEXTRUNDATE; //下次巡检时间
    private String OKTIME; //恢复时间
    private String PRONUM; //项目编号
    private String RESBY; //巡检负责人编号
    private String STARTTIME; //计划开始时间
    private String STATUS; //状态
    private String STOPTIME; //停机时间
    private String UDLOCNUM; //机位号
    private String WEATHER; //天气
    private String PRODESC; //项目名称
    private String JPDESC; //巡检标准名称
    private String FJDESC; //设备位置名称
    private String NAME; //巡检负责人名称
    private String NAME1; //巡检人员名称
    private String NAME2; //巡检人员名称
    private String NAME3; //巡检人员名称


    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME1() {
        return NAME1;
    }

    public void setNAME1(String NAME1) {
        this.NAME1 = NAME1;
    }

    public String getNAME2() {
        return NAME2;
    }

    public void setNAME2(String NAME2) {
        this.NAME2 = NAME2;
    }

    public String getNAME3() {
        return NAME3;
    }

    public void setNAME3(String NAME3) {
        this.NAME3 = NAME3;
    }

    public String getFJDESC() {
        return FJDESC;
    }

    public void setFJDESC(String FJDESC) {
        this.FJDESC = FJDESC;
    }

    public String getJPDESC() {
        return JPDESC;
    }

    public void setJPDESC(String JPDESC) {
        this.JPDESC = JPDESC;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getALLTIME() {
        return ALLTIME;
    }

    public void setALLTIME(String ALLTIME) {
        this.ALLTIME = ALLTIME;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getCHANGEBY() {
        return CHANGEBY;
    }

    public void setCHANGEBY(String CHANGEBY) {
        this.CHANGEBY = CHANGEBY;
    }

    public String getCHANGEDATE() {
        return CHANGEDATE;
    }

    public void setCHANGEDATE(String CHANGEDATE) {
        this.CHANGEDATE = CHANGEDATE;
    }

    public String getCOMPTIME() {
        return COMPTIME;
    }

    public void setCOMPTIME(String COMPTIME) {
        this.COMPTIME = COMPTIME;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getFJNUM() {
        return FJNUM;
    }

    public void setFJNUM(String FJNUM) {
        this.FJNUM = FJNUM;
    }

    public String getINSPOBY() {
        return INSPOBY;
    }

    public void setINSPOBY(String INSPOBY) {
        this.INSPOBY = INSPOBY;
    }

    public String getINSPOBY2() {
        return INSPOBY2;
    }

    public void setINSPOBY2(String INSPOBY2) {
        this.INSPOBY2 = INSPOBY2;
    }

    public String getINSPOBY3() {
        return INSPOBY3;
    }

    public void setINSPOBY3(String INSPOBY3) {
        this.INSPOBY3 = INSPOBY3;
    }

    public String getINSPODATE() {
        return INSPODATE;
    }

    public void setINSPODATE(String INSPODATE) {
        this.INSPODATE = INSPODATE;
    }

    public String getINSPONUM() {
        return INSPONUM;
    }

    public void setINSPONUM(String INSPONUM) {
        this.INSPONUM = INSPONUM;
    }

    public String getINSPPLANNUM() {
        return INSPPLANNUM;
    }

    public void setINSPPLANNUM(String INSPPLANNUM) {
        this.INSPPLANNUM = INSPPLANNUM;
    }

    public String getISSTOP() {
        return ISSTOP;
    }

    public void setISSTOP(String ISSTOP) {
        this.ISSTOP = ISSTOP;
    }

    public String getJPNUM() {
        return JPNUM;
    }

    public void setJPNUM(String JPNUM) {
        this.JPNUM = JPNUM;
    }

    public String getLASTRUNDATE() {
        return LASTRUNDATE;
    }

    public void setLASTRUNDATE(String LASTRUNDATE) {
        this.LASTRUNDATE = LASTRUNDATE;
    }

    public String getMODELTYPE() {
        return MODELTYPE;
    }

    public void setMODELTYPE(String MODELTYPE) {
        this.MODELTYPE = MODELTYPE;
    }

    public String getNEXTRUNDATE() {
        return NEXTRUNDATE;
    }

    public void setNEXTRUNDATE(String NEXTRUNDATE) {
        this.NEXTRUNDATE = NEXTRUNDATE;
    }

    public String getOKTIME() {
        return OKTIME;
    }

    public void setOKTIME(String OKTIME) {
        this.OKTIME = OKTIME;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getRESBY() {
        return RESBY;
    }

    public void setRESBY(String RESBY) {
        this.RESBY = RESBY;
    }

    public String getSTARTTIME() {
        return STARTTIME;
    }

    public void setSTARTTIME(String STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTOPTIME() {
        return STOPTIME;
    }

    public void setSTOPTIME(String STOPTIME) {
        this.STOPTIME = STOPTIME;
    }

    public String getUDLOCNUM() {
        return UDLOCNUM;
    }

    public void setUDLOCNUM(String UDLOCNUM) {
        this.UDLOCNUM = UDLOCNUM;
    }

    public String getWEATHER() {
        return WEATHER;
    }

    public void setWEATHER(String WEATHER) {
        this.WEATHER = WEATHER;
    }
}