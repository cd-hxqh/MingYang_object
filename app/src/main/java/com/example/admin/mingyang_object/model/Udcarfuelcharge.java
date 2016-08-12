package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 加油记录
 */
public class Udcarfuelcharge extends Entity implements Serializable {

    private String UDCARFUELCHARGEID; //主键ID
    private String CARFUELCHARGENUM; //编号
    private String DESCRIPTION; //描述
    private String LICENSENUM; //车牌号
    private String CREATEBY;//录入人名称
    private String CREATEDATE; //录入日期
    private String CHARGEDATE; //加油日期
    private String NUMBER2; //上次加油里程表读数
    private String NUMBER1; //本次加油里程表读数
    private String NUMBER3; //里程差
    private String CARNUM;  //加油卡编号
    private String NUMBER4; //油品号
    private String NUMBER5; //本次加油量（升）
    private String PRICE; //单价
    private String FUELCOST; //加油费
    private String LASTFUELCONSUMPTION; //油耗
    private String INVOICENUM; //发票号
    private String PLACE; //加油地点
    private String REMARK; //备注
    private String BRACHDESC; //所属中心
    private String DRIVERID; //录入人编号
    private String DRIVERID1; //司机编号
    private String DRIVERNAME; //司机名称
    private String PRODESC; //所属项目
    private String VEHICLENAME; //车辆名称
    private String RESPONSID; //责任人ID
    private String RESPNAME; //责任人名称
    private String COMISORNO; //是否提交
    private String PRONUM;//项目编号
    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }




    public String getUDCARFUELCHARGEID() {
        return UDCARFUELCHARGEID;
    }

    public void setUDCARFUELCHARGEID(String UDCARFUELCHARGEID) {
        this.UDCARFUELCHARGEID = UDCARFUELCHARGEID;
    }

    public String getCOMISORNO() {
        return COMISORNO;
    }

    public void setCOMISORNO(String COMISORNO) {
        this.COMISORNO = COMISORNO;
    }

    public String getRESPONSID() {
        return RESPONSID;
    }

    public void setRESPONSID(String RESPONSID) {
        this.RESPONSID = RESPONSID;
    }

    public String getRESPNAME() {
        return RESPNAME;
    }

    public void setRESPNAME(String RESPNAME) {
        this.RESPNAME = RESPNAME;
    }

    public String getBRACHDESC() {
        return BRACHDESC;
    }

    public void setBRACHDESC(String BRACHDESC) {
        this.BRACHDESC = BRACHDESC;
    }

    public String getDRIVERID() {
        return DRIVERID;
    }

    public void setDRIVERID(String DRIVERID) {
        this.DRIVERID = DRIVERID;
    }

    public String getDRIVERID1() {
        return DRIVERID1;
    }

    public void setDRIVERID1(String DRIVERID1) {
        this.DRIVERID1 = DRIVERID1;
    }

    public String getDRIVERNAME() {
        return DRIVERNAME;
    }

    public void setDRIVERNAME(String DRIVERNAME) {
        this.DRIVERNAME = DRIVERNAME;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getVEHICLENAME() {
        return VEHICLENAME;
    }

    public void setVEHICLENAME(String VEHICLENAME) {
        this.VEHICLENAME = VEHICLENAME;
    }

    public String getCARFUELCHARGENUM() {
        return CARFUELCHARGENUM;
    }

    public void setCARFUELCHARGENUM(String CARFUELCHARGENUM) {
        this.CARFUELCHARGENUM = CARFUELCHARGENUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLICENSENUM() {
        return LICENSENUM;
    }

    public void setLICENSENUM(String LICENSENUM) {
        this.LICENSENUM = LICENSENUM;
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

    public String getCHARGEDATE() {
        return CHARGEDATE;
    }

    public void setCHARGEDATE(String CHARGEDATE) {
        this.CHARGEDATE = CHARGEDATE;
    }

    public String getNUMBER2() {
        return NUMBER2;
    }

    public void setNUMBER2(String NUMBER2) {
        this.NUMBER2 = NUMBER2;
    }

    public String getNUMBER1() {
        return NUMBER1;
    }

    public void setNUMBER1(String NUMBER1) {
        this.NUMBER1 = NUMBER1;
    }

    public String getNUMBER3() {
        return NUMBER3;
    }

    public void setNUMBER3(String NUMBER3) {
        this.NUMBER3 = NUMBER3;
    }

    public String getNUMBER4() {
        return NUMBER4;
    }

    public String getCARNUM() {return CARNUM;}

    public void setCARNUM(String CARNUM) {this.CARNUM = CARNUM;}

    public void setNUMBER4(String NUMBER4) {
        this.NUMBER4 = NUMBER4;
    }

    public String getNUMBER5() {
        return NUMBER5;
    }

    public void setNUMBER5(String NUMBER5) {
        this.NUMBER5 = NUMBER5;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getFUELCOST() {
        return FUELCOST;
    }

    public void setFUELCOST(String FUELCOST) {
        this.FUELCOST = FUELCOST;
    }

    public String getLASTFUELCONSUMPTION() {
        return LASTFUELCONSUMPTION;
    }

    public void setLASTFUELCONSUMPTION(String LASTFUELCONSUMPTION) {
        this.LASTFUELCONSUMPTION = LASTFUELCONSUMPTION;
    }

    public String getINVOICENUM() {
        return INVOICENUM;
    }

    public void setINVOICENUM(String INVOICENUM) {
        this.INVOICENUM = INVOICENUM;
    }

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
