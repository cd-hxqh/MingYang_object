package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 维修记录
 */
public class Udcarmainlog implements Serializable {


    private String UDCARMAINLOGID; //主键ID
    private String BRANCHDESC; //所属中心
    private String COMISORNO; //是否提交
    private String CREATEBY; //录入人名称
    private String CREATEDATE;//录入日期
    private String DESCRIPTION; //描述
    private String DRIVERID; //录入人编号
    private String DRIVERID1; //司机编号
    private String DRIVERNAME; //司机名称
    private String INVOICENUM; //发票号
    private String LICENSENUM; //车牌号
    private String MAINCONTENT; //维修、保养、更换项目
    private String MAINDATE; //维护日期
    private String MAINLOGNUM; //维修记录编号
    private String MAINNUMBER; //维护数量
    private String MAINPLACE; //维修地点
    private String NUMBER1; //本次维修里程表读数
    private String NUMBER2; //上次维修里程表读数
    private String PRICE; //单价
    private String PRODESC; //所属项目
    private String REMARK; //备注
    private String RESPNAME; //责任人名称
    private String RESPONSID; //责任人ID
    private String TOTALPRICE; //总额
    private String VEHICLENAME; //车牌号中文
    private String SERVICETYPE; //维修类型
    private String STARTDATE; //开始维修时间
    private String ENDDATE; //结束维修时间

    public String getUDCARMAINLOGID() {
        return UDCARMAINLOGID;
    }

    public void setUDCARMAINLOGID(String UDCARMAINLOGID) {
        this.UDCARMAINLOGID = UDCARMAINLOGID;
    }

    public String getSTARTDATE() {
        return STARTDATE;
    }

    public void setSTARTDATE(String STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    public String getENDDATE() {
        return ENDDATE;
    }

    public void setENDDATE(String ENDDATE) {
        this.ENDDATE = ENDDATE;
    }

    public String getSERVICETYPE() {
        return SERVICETYPE;
    }

    public void setSERVICETYPE(String SERVICETYPE) {
        this.SERVICETYPE = SERVICETYPE;
    }

    public String getBRANCHDESC() {
        return BRANCHDESC;
    }

    public void setBRANCHDESC(String BRANCHDESC) {
        this.BRANCHDESC = BRANCHDESC;
    }

    public String getCOMISORNO() {
        return COMISORNO;
    }

    public void setCOMISORNO(String COMISORNO) {
        this.COMISORNO = COMISORNO;
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

    public String getINVOICENUM() {
        return INVOICENUM;
    }

    public void setINVOICENUM(String INVOICENUM) {
        this.INVOICENUM = INVOICENUM;
    }

    public String getLICENSENUM() {
        return LICENSENUM;
    }

    public void setLICENSENUM(String LICENSENUM) {
        this.LICENSENUM = LICENSENUM;
    }

    public String getMAINCONTENT() {
        return MAINCONTENT;
    }

    public void setMAINCONTENT(String MAINCONTENT) {
        this.MAINCONTENT = MAINCONTENT;
    }

    public String getMAINDATE() {
        return MAINDATE;
    }

    public void setMAINDATE(String MAINDATE) {
        this.MAINDATE = MAINDATE;
    }

    public String getMAINLOGNUM() {
        return MAINLOGNUM;
    }

    public void setMAINLOGNUM(String MAINLOGNUM) {
        this.MAINLOGNUM = MAINLOGNUM;
    }

    public String getMAINNUMBER() {
        return MAINNUMBER;
    }

    public void setMAINNUMBER(String MAINNUMBER) {
        this.MAINNUMBER = MAINNUMBER;
    }

    public String getMAINPLACE() {
        return MAINPLACE;
    }

    public void setMAINPLACE(String MAINPLACE) {
        this.MAINPLACE = MAINPLACE;
    }

    public String getNUMBER1() {
        return NUMBER1;
    }

    public void setNUMBER1(String NUMBER1) {
        this.NUMBER1 = NUMBER1;
    }

    public String getNUMBER2() {
        return NUMBER2;
    }

    public void setNUMBER2(String NUMBER2) {
        this.NUMBER2 = NUMBER2;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getRESPNAME() {
        return RESPNAME;
    }

    public void setRESPNAME(String RESPNAME) {
        this.RESPNAME = RESPNAME;
    }

    public String getRESPONSID() {
        return RESPONSID;
    }

    public void setRESPONSID(String RESPONSID) {
        this.RESPONSID = RESPONSID;
    }

    public String getTOTALPRICE() {
        return TOTALPRICE;
    }

    public void setTOTALPRICE(String TOTALPRICE) {
        this.TOTALPRICE = TOTALPRICE;
    }

    public String getVEHICLENAME() {
        return VEHICLENAME;
    }

    public void setVEHICLENAME(String VEHICLENAME) {
        this.VEHICLENAME = VEHICLENAME;
    }
}
