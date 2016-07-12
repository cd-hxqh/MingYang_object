package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 *行驶记录
 */
public class Udcardrivelog extends Entity implements Serializable {

    private String UDCARDRIVELOGID; //主键
    private String CARDRIVELOGNUM; //记录编号
    private String CREATEBY; //创建人名称
    private String CREATEDATE; //创建日期
    private String DEPARTURE;//出发地
    private String DESTINATION; //目的地
    private String DRIVERID; //创建人编号
    private String ENDNUMBER; //结束里程
    private String FEE; //路桥费
    private String GOREASON; //出车事由
    private String LASTFUELCONSUMPTION; //上次油耗
    private String LICENSENUM; //车牌号
    private String STANDARDFUELCONSUMPTION; //标准油耗
    private String STARTDATE; //出车日期
    private String STARTNUMBER; //起始里程
    private String STARTTIME; //出车时间
    private String WONUM; //业务单号
    private String WORKTYPE; //任务类型
    private String BRANCHDESC; //所属中心
    private String CARNAME; //车牌号中文
    private String DRIVERNAME; //司机中文
    private String DRIVERID1; //司机
    private String PRODESC; //所属项目
    private String NOWPROJECT; //
    private String DESCRIPTION; // 描述
    private String COMISORNO; //是否提交


    public String getUDCARDRIVELOGID() {
        return UDCARDRIVELOGID;
    }

    public void setUDCARDRIVELOGID(String UDCARDRIVELOGID) {
        this.UDCARDRIVELOGID = UDCARDRIVELOGID;
    }

    public String getCOMISORNO() {
        return COMISORNO;
    }

    public void setCOMISORNO(String COMISORNO) {
        this.COMISORNO = COMISORNO;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getWORKTYPE() {
        return WORKTYPE;
    }

    public void setWORKTYPE(String WORKTYPE) {
        this.WORKTYPE = WORKTYPE;
    }

    public String getBRANCHDESC() {
        return BRANCHDESC;
    }

    public void setBRANCHDESC(String BRANCHDESC) {
        this.BRANCHDESC = BRANCHDESC;
    }

    public String getCARNAME() {
        return CARNAME;
    }

    public void setCARNAME(String CARNAME) {
        this.CARNAME = CARNAME;
    }

    public String getDRIVERNAME() {
        return DRIVERNAME;
    }

    public void setDRIVERNAME(String DRIVERNAME) {
        this.DRIVERNAME = DRIVERNAME;
    }

    public String getDRIVERID1() {
        return DRIVERID1;
    }

    public void setDRIVERID1(String DRIVERID1) {
        this.DRIVERID1 = DRIVERID1;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getNOWPROJECT() {
        return NOWPROJECT;
    }

    public void setNOWPROJECT(String NOWPROJECT) {
        this.NOWPROJECT = NOWPROJECT;
    }

    public String getCARDRIVELOGNUM() {
        return CARDRIVELOGNUM;
    }

    public void setCARDRIVELOGNUM(String CARDRIVELOGNUM) {
        this.CARDRIVELOGNUM = CARDRIVELOGNUM;
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

    public String getDEPARTURE() {
        return DEPARTURE;
    }

    public void setDEPARTURE(String DEPARTURE) {
        this.DEPARTURE = DEPARTURE;
    }

    public String getDESTINATION() {
        return DESTINATION;
    }

    public void setDESTINATION(String DESTINATION) {
        this.DESTINATION = DESTINATION;
    }

    public String getDRIVERID() {
        return DRIVERID;
    }

    public void setDRIVERID(String DRIVERID) {
        this.DRIVERID = DRIVERID;
    }

    public String getENDNUMBER() {
        return ENDNUMBER;
    }

    public void setENDNUMBER(String ENDNUMBER) {
        this.ENDNUMBER = ENDNUMBER;
    }

    public String getFEE() {
        return FEE;
    }

    public void setFEE(String FEE) {
        this.FEE = FEE;
    }

    public String getGOREASON() {
        return GOREASON;
    }

    public void setGOREASON(String GOREASON) {
        this.GOREASON = GOREASON;
    }

    public String getLASTFUELCONSUMPTION() {
        return LASTFUELCONSUMPTION;
    }

    public void setLASTFUELCONSUMPTION(String LASTFUELCONSUMPTION) {
        this.LASTFUELCONSUMPTION = LASTFUELCONSUMPTION;
    }

    public String getLICENSENUM() {
        return LICENSENUM;
    }

    public void setLICENSENUM(String LICENSENUM) {
        this.LICENSENUM = LICENSENUM;
    }

    public String getSTANDARDFUELCONSUMPTION() {
        return STANDARDFUELCONSUMPTION;
    }

    public void setSTANDARDFUELCONSUMPTION(String STANDARDFUELCONSUMPTION) {
        this.STANDARDFUELCONSUMPTION = STANDARDFUELCONSUMPTION;
    }

    public String getSTARTDATE() {
        return STARTDATE;
    }

    public void setSTARTDATE(String STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    public String getSTARTNUMBER() {
        return STARTNUMBER;
    }

    public void setSTARTNUMBER(String STARTNUMBER) {
        this.STARTNUMBER = STARTNUMBER;
    }

    public String getSTARTTIME() {
        return STARTTIME;
    }

    public void setSTARTTIME(String STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public String getWONUM() {
        return WONUM;
    }

    public void setWONUM(String WONUM) {
        this.WONUM = WONUM;
    }
}
