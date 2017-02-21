package com.example.admin.eam.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by apple on 15/10/26.
 * 故障提报
 */
public class Udreport extends Entity implements Serializable {

    private int UDREPORTID; //主键ID
    private String ASSETLOC; //设备位置描述
    private String BRANCH; //中心编码
    private String BRANCHDESC; //中心编码描述
    private String CREATEBY;//提报人编号
    private String CUDESCRIBE; //故障描述
    private String CUISPLAN; //是否由集控生成？
    private String UDGZDJ; //故障等级
    private String DESCRIPTION; //描述
    private String END_TIME; //结束时间
    private String UDGZTYPE; //故障类型
    private String FAULT_CODE; //故障类
    private String FAULT_CODE1; //故障代码
    private String FAULT_CODE1DESC; //故障代码描述
    private String FAULT_CODEDESC; //故障类描述
    private String HAPPEN_TIME; //故障提报时间
    private String IS_END; //故障是否结束？
    private String LOCATION; //设备位置
    private String LOCATION_CODE; //机位号
    private String PRONUM; //项目编码
    private String PRODESC; //项目描述
    private String REMARK; //备注
    private String REPORTNUM; //编码
    private String REPORTTIME; //提报时间
    private String RESULT; //处理结果
    private String STATUSTYPE; //状态
    private String WONUM;//故障工单号
    private String UDPBFORMNUM;//质量问题反馈单编号

    private HashMap<String,String> map = new HashMap<String,String>();
    public HashMap<String,String> getMap()
    {
        map.put("ASSETLOC","设备位置描述");
        map.put("BRANCH","中心编码");
        map.put("BRANCHDESC","中心编码描述");
        map.put("CREATEBY","提报人编号");
        map.put("CUDESCRIBE","故障描述");
        map.put("CUISPLAN","是否由集控生成");
        map.put("UDGZDJ","故障等级");
        map.put("DESCRIPTION","描述");
        map.put("END_TIME","结束时间");
        map.put("UDGZTYPE","故障类型");
        map.put("FAULT_CODE","故障类");
        map.put("FAULT_CODE1","故障代码");
        map.put("FAULT_CODE1DESC","故障代码描述");
        map.put("FAULT_CODEDESC","故障类描述");
        map.put("HAPPEN_TIME","故障提报时间");
        map.put("IS_END","故障是否结束");
        map.put("LOCATION","设备位置");
        map.put("LOCATION_CODE","机位号");
        map.put("PRONUM","项目编码");
        map.put("PRODESC","项目描述");
        map.put("REMARK","备注");
        map.put("REPORTNUM","编码");
        map.put("REPORTTIME","提报时间");
        map.put("RESULT","处理结果");
        map.put("STATUSTYPE","状态");
        map.put("WONUM","故障工单号");
        map.put("UDPBFORMNUM","质量问题反馈单编号");
        return map;
    }

    public int getUDREPORTID() {
        return UDREPORTID;
    }

    public void setUDREPORTID(int UDREPORTID) {
        this.UDREPORTID = UDREPORTID;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getASSETLOC() {
        return ASSETLOC;
    }

    public void setASSETLOC(String ASSETLOC) {
        this.ASSETLOC = ASSETLOC;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getBRANCHDESC() {
        return BRANCHDESC;
    }

    public void setBRANCHDESC(String BRANCHDESC) {
        this.BRANCHDESC = BRANCHDESC;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getCUDESCRIBE() {
        return CUDESCRIBE;
    }

    public void setCUDESCRIBE(String CUDESCRIBE) {
        this.CUDESCRIBE = CUDESCRIBE;
    }

    public String getCUISPLAN() {
        return CUISPLAN;
    }

    public void setCUISPLAN(String CUISPLAN) {
        this.CUISPLAN = CUISPLAN;
    }

    public String getUDGZDJ() {
        return UDGZDJ;
    }

    public void setUDGZDJ(String UDGZDJ) {
        this.UDGZDJ = UDGZDJ;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getUDGZTYPE() {
        return UDGZTYPE;
    }

    public void setUDGZTYPE(String UDGZTYPE) {
        this.UDGZTYPE = UDGZTYPE;
    }

    public String getFAULT_CODE() {
        return FAULT_CODE;
    }

    public void setFAULT_CODE(String FAULT_CODE) {
        this.FAULT_CODE = FAULT_CODE;
    }

    public String getFAULT_CODE1() {
        return FAULT_CODE1;
    }

    public void setFAULT_CODE1(String FAULT_CODE1) {
        this.FAULT_CODE1 = FAULT_CODE1;
    }

    public String getFAULT_CODE1DESC() {
        return FAULT_CODE1DESC;
    }

    public void setFAULT_CODE1DESC(String FAULT_CODE1DESC) {
        this.FAULT_CODE1DESC = FAULT_CODE1DESC;
    }

    public String getFAULT_CODEDESC() {
        return FAULT_CODEDESC;
    }

    public void setFAULT_CODEDESC(String FAULT_CODEDESC) {
        this.FAULT_CODEDESC = FAULT_CODEDESC;
    }

    public String getHAPPEN_TIME() {
        return HAPPEN_TIME;
    }

    public void setHAPPEN_TIME(String HAPPEN_TIME) {
        this.HAPPEN_TIME = HAPPEN_TIME;
    }

    public String getIS_END() {
        return IS_END;
    }

    public void setIS_END(String IS_END) {
        this.IS_END = IS_END;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getLOCATION_CODE() {
        return LOCATION_CODE;
    }

    public void setLOCATION_CODE(String LOCATION_CODE) {
        this.LOCATION_CODE = LOCATION_CODE;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getREPORTNUM() {
        return REPORTNUM;
    }

    public void setREPORTNUM(String REPORTNUM) {
        this.REPORTNUM = REPORTNUM;
    }

    public String getREPORTTIME() {
        return REPORTTIME;
    }

    public void setREPORTTIME(String REPORTTIME) {
        this.REPORTTIME = REPORTTIME;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getSTATUSTYPE() {
        return STATUSTYPE;
    }

    public void setSTATUSTYPE(String STATUSTYPE) {
        this.STATUSTYPE = STATUSTYPE;
    }

    public String getWONUM() {
        return WONUM;
    }

    public void setWONUM(String WONUM) {
        this.WONUM = WONUM;
    }

    public String getUDPBFORMNUM() {
        return UDPBFORMNUM;
    }

    public void setUDPBFORMNUM(String UDPBFORMNUM) {
        this.UDPBFORMNUM = UDPBFORMNUM;
    }
}
