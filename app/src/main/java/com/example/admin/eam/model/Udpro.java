package com.example.admin.eam.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 项目台账
 */
public class Udpro extends Entity implements Serializable {


    private String PRONUM; //项目编号
    private String DESCRIPTION; //项目描述
    private String BRANCH; //所属中心
    private String BRANCHDESC;//中心描述
    private String CAPACITY; //总厂容量（MW）
    private String CONTRACTSTATUS; //合同状态
    private String OWNER; //业务单位
    private String PERIOD; //质保期（年）
    private String PROSTAGE; //项目当前阶段
    private String RESPONS; //责任人编号
    private String RESPONSNAME;//责任人描述
    private String SIGNDATE; //签订时间
    private String SITEID; //站点
    private String TESTPRO; //试点项目
    private String RESPONSPHONE;//电话号码
    private String DEPT;//责任人部门
    private String WINDSPEED3;//额定风速（m/s）
    private String WINDSPEED1;//切入风速（m/s）
    private String WINDSPEED2;// 	切出风速（m/s）
    private String TEMPERATURE1;// 	环境温度区间（℃）
    private String TEMPERATURE2;//运行温度区间（℃）
    private String BOND;//安全保证金
    private String TRANSPORT;//运输方式
    private String TPOP;//付款比例
    private String REQ2;//预验收要求
    private String REQ1;//试运行要求
    private String UTILIZATION;//可利用率要求
    private String SPECIALCON;// 特殊配置
    private String CYCLE;//调试周期
    private String REMARKS;//备注


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

    public String getBRANCHDESC() {
        return BRANCHDESC;
    }

    public void setBRANCHDESC(String BRANCHDESC) {
        this.BRANCHDESC = BRANCHDESC;
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

    public String getRESPONSNAME() {
        return RESPONSNAME;
    }

    public void setRESPONSNAME(String RESPONSNAME) {
        this.RESPONSNAME = RESPONSNAME;
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

    public String getRESPONSPHONE() {
        return RESPONSPHONE;
    }

    public void setRESPONSPHONE(String RESPONSPHONE) {
        this.RESPONSPHONE = RESPONSPHONE;
    }

    public String getDEPT() {
        return DEPT;
    }

    public void setDEPT(String DEPT) {
        this.DEPT = DEPT;
    }

    public String getWINDSPEED3() {
        return WINDSPEED3;
    }

    public void setWINDSPEED3(String WINDSPEED3) {
        this.WINDSPEED3 = WINDSPEED3;
    }

    public String getWINDSPEED1() {
        return WINDSPEED1;
    }

    public void setWINDSPEED1(String WINDSPEED1) {
        this.WINDSPEED1 = WINDSPEED1;
    }

    public String getWINDSPEED2() {
        return WINDSPEED2;
    }

    public void setWINDSPEED2(String WINDSPEED2) {
        this.WINDSPEED2 = WINDSPEED2;
    }

    public String getTEMPERATURE1() {
        return TEMPERATURE1;
    }

    public void setTEMPERATURE1(String TEMPERATURE1) {
        this.TEMPERATURE1 = TEMPERATURE1;
    }

    public String getTEMPERATURE2() {
        return TEMPERATURE2;
    }

    public void setTEMPERATURE2(String TEMPERATURE2) {
        this.TEMPERATURE2 = TEMPERATURE2;
    }

    public String getBOND() {
        return BOND;
    }

    public void setBOND(String BOND) {
        this.BOND = BOND;
    }

    public String getTRANSPORT() {
        return TRANSPORT;
    }

    public void setTRANSPORT(String TRANSPORT) {
        this.TRANSPORT = TRANSPORT;
    }

    public String getTPOP() {
        return TPOP;
    }

    public void setTPOP(String TPOP) {
        this.TPOP = TPOP;
    }

    public String getREQ2() {
        return REQ2;
    }

    public void setREQ2(String REQ2) {
        this.REQ2 = REQ2;
    }

    public String getREQ1() {
        return REQ1;
    }

    public void setREQ1(String REQ1) {
        this.REQ1 = REQ1;
    }

    public String getUTILIZATION() {
        return UTILIZATION;
    }

    public void setUTILIZATION(String UTILIZATION) {
        this.UTILIZATION = UTILIZATION;
    }

    public String getSPECIALCON() {
        return SPECIALCON;
    }

    public void setSPECIALCON(String SPECIALCON) {
        this.SPECIALCON = SPECIALCON;
    }

    public String getCYCLE() {
        return CYCLE;
    }

    public void setCYCLE(String CYCLE) {
        this.CYCLE = CYCLE;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }
}
