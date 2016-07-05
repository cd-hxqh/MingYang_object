package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/7/4.
 * 土建阶段日报
 */
public class UdprorunlogLine1 implements Serializable{
    public String CREATEDATE;//创建日期
    public String PERSONID;//项目负责人
    public String PERSONDESC;//项目负责人描述
    public String FUNNUM;//风机号
    public String PROPHASE;//当前项目阶段
    public String LAND;//征地
    public String INSIDEROAD;//场内道路
    public String OUTSIDEROAD;//场外道路
    public int VILLAGERINVOLVED;//村民阻工
    public String REMARK;//备注
    public String KEYPOINT;//现场重难点描述
    public String BASESTART;//基础开挖
    public String BASEPLACING;//基础浇筑
    public String BASEAOG;//基础环到货
    public String TAMERAOG;//塔筒到货
    public String VEHICLERECORDS;//现场押车记录

    public String TYPE;
    public String PRORUNLOGNUM;
    public boolean isUpload;

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }

    public String getPERSONDESC() {
        return PERSONDESC;
    }

    public void setPERSONDESC(String PERSONDESC) {
        this.PERSONDESC = PERSONDESC;
    }

    public String getFUNNUM() {
        return FUNNUM;
    }

    public void setFUNNUM(String FUNNUM) {
        this.FUNNUM = FUNNUM;
    }

    public String getPROPHASE() {
        return PROPHASE;
    }

    public void setPROPHASE(String PROPHASE) {
        this.PROPHASE = PROPHASE;
    }

    public String getLAND() {
        return LAND;
    }

    public void setLAND(String LAND) {
        this.LAND = LAND;
    }

    public String getINSIDEROAD() {
        return INSIDEROAD;
    }

    public void setINSIDEROAD(String INSIDEROAD) {
        this.INSIDEROAD = INSIDEROAD;
    }

    public String getOUTSIDEROAD() {
        return OUTSIDEROAD;
    }

    public void setOUTSIDEROAD(String OUTSIDEROAD) {
        this.OUTSIDEROAD = OUTSIDEROAD;
    }

    public int getVILLAGERINVOLVED() {
        return VILLAGERINVOLVED;
    }

    public void setVILLAGERINVOLVED(int VILLAGERINVOLVED) {
        this.VILLAGERINVOLVED = VILLAGERINVOLVED;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getKEYPOINT() {
        return KEYPOINT;
    }

    public void setKEYPOINT(String KEYPOINT) {
        this.KEYPOINT = KEYPOINT;
    }

    public String getBASESTART() {
        return BASESTART;
    }

    public void setBASESTART(String BASESTART) {
        this.BASESTART = BASESTART;
    }

    public String getBASEPLACING() {
        return BASEPLACING;
    }

    public void setBASEPLACING(String BASEPLACING) {
        this.BASEPLACING = BASEPLACING;
    }

    public String getBASEAOG() {
        return BASEAOG;
    }

    public void setBASEAOG(String BASEAOG) {
        this.BASEAOG = BASEAOG;
    }

    public String getTAMERAOG() {
        return TAMERAOG;
    }

    public void setTAMERAOG(String TAMERAOG) {
        this.TAMERAOG = TAMERAOG;
    }

    public String getVEHICLERECORDS() {
        return VEHICLERECORDS;
    }

    public void setVEHICLERECORDS(String VEHICLERECORDS) {
        this.VEHICLERECORDS = VEHICLERECORDS;
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
}
