package com.example.admin.mingyang_object.model;

/**
 * Created by chris on 16/8/10.
 */
public class GreaseCard {

    public String CARNUM;//加油卡编号
    public String CARDES;//加油卡描述
    public String PRONUM;//项目编号
    public String LICENSENUM;//车牌号
    public String BALANCE;//余额
    public String CREATEBY;//创建人
    public String DISPLAYNAME;//显示名称
    public String CREATEDATE;//创建日期

    public String getCARNUM() {
        return CARNUM;
    }

    public void setCARNUM(String CARNUM) {
        this.CARNUM = CARNUM;
    }

    public String getCARDES() {
        return CARDES;
    }

    public void setCARDES(String CARDES) {
        this.CARDES = CARDES;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getLICENSENUM() {
        return LICENSENUM;
    }

    public void setLICENSENUM(String LICENSENUM) {
        this.LICENSENUM = LICENSENUM;
    }

    public String getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getDISPLAYNAME() {
        return DISPLAYNAME;
    }

    public void setDISPLAYNAME(String DISPLAYNAME) {
        this.DISPLAYNAME = DISPLAYNAME;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }
}
