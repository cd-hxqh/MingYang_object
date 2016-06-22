package com.example.admin.mingyang_object.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 项目台账
 */
public class Udpro extends Entity implements Serializable {

    private String PRONUM; //项目编号
    private String DESCRIPTION; //项目描述
    private String BRANCH; //所属中心
    private String CAPACITY; //总厂容量（MW）
    private String CONTRACTSTATUS; //合同状态
    private String OWNER; //业务单位
    private String PERIOD; //质保期（年）
    private String PROSTAGE; //项目当前阶段
    private String RESPONS; //责任人编号
    private String SIGNDATE; //签订时间
    private String SITEID; //站点
    private String TESTPRO; //试点项目

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(String CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public String getCONTRACTSTATUS() {
        return CONTRACTSTATUS;
    }

    public void setCONTRACTSTATUS(String CONTRACTSTATUS) {
        this.CONTRACTSTATUS = CONTRACTSTATUS;
    }

    public String getOWNER() {
        return OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public String getPERIOD() {
        return PERIOD;
    }

    public void setPERIOD(String PERIOD) {
        this.PERIOD = PERIOD;
    }

    public String getPROSTAGE() {
        return PROSTAGE;
    }

    public void setPROSTAGE(String PROSTAGE) {
        this.PROSTAGE = PROSTAGE;
    }

    public String getRESPONS() {
        return RESPONS;
    }

    public void setRESPONS(String RESPONS) {
        this.RESPONS = RESPONS;
    }

    public String getSIGNDATE() {
        return SIGNDATE;
    }

    public void setSIGNDATE(String SIGNDATE) {
        this.SIGNDATE = SIGNDATE;
    }

    public String getSITEID() {
        return SITEID;
    }

    public void setSITEID(String SITEID) {
        this.SITEID = SITEID;
    }

    public String getTESTPRO() {
        return TESTPRO;
    }

    public void setTESTPRO(String TESTPRO) {
        this.TESTPRO = TESTPRO;
    }
}
