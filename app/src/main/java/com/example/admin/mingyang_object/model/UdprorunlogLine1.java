package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/7/4.
 * �����׶��ձ�
 */
public class UdprorunlogLine1 implements Serializable{
    public String CREATEDATE;//��������
    public String PERSONID;//��Ŀ������
    public String PERSONDESC;//��Ŀ����������
    public String FUNNUM;//�����
    public String PROPHASE;//��ǰ��Ŀ�׶�
    public String LAND;//����
    public String INSIDEROAD;//���ڵ�·
    public String OUTSIDEROAD;//�����·
    public int VILLAGERINVOLVED;//�����蹤
    public String REMARK;//��ע
    public String KEYPOINT;//�ֳ����ѵ�����
    public String BASESTART;//��������
    public String BASEPLACING;//��������
    public String BASEAOG;//����������
    public String TAMERAOG;//��Ͳ����
    public String VEHICLERECORDS;//�ֳ�Ѻ����¼

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
