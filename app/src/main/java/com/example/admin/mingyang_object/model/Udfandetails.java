package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 风机型号表
 */
public class Udfandetails implements Serializable {

    private String BWDATE; //并网运行日期
    private String DJDATE1; //首检完成日期
    private String DJDATE2; //半年检完成日期
    private String DZDATE; //吊装完成日期
    private String EMPST; //机台号
    private String JDDATE3; //全年检完成日期
    private String LOCNUM; //机位号
    private String MODELTYPE; //型号
    private String PRONUM; //項目台账编号
    private String SAPNUM; //成品物料代码（SAP）
    private String SITEID; //站点
    private String TSDATE; //调试完成日期
    private String XJDATE; //上次巡检日期
    private String YYSDATE; //预验收完成日期
    private String ZYYDATE; //终验收完成日期


    public String getBWDATE() {
        return BWDATE;
    }

    public void setBWDATE(String BWDATE) {
        this.BWDATE = BWDATE;
    }

    public String getDJDATE1() {
        return DJDATE1;
    }

    public void setDJDATE1(String DJDATE1) {
        this.DJDATE1 = DJDATE1;
    }

    public String getDJDATE2() {
        return DJDATE2;
    }

    public void setDJDATE2(String DJDATE2) {
        this.DJDATE2 = DJDATE2;
    }

    public String getDZDATE() {
        return DZDATE;
    }

    public void setDZDATE(String DZDATE) {
        this.DZDATE = DZDATE;
    }

    public String getEMPST() {
        return EMPST;
    }

    public void setEMPST(String EMPST) {
        this.EMPST = EMPST;
    }

    public String getJDDATE3() {
        return JDDATE3;
    }

    public void setJDDATE3(String JDDATE3) {
        this.JDDATE3 = JDDATE3;
    }

    public String getLOCNUM() {
        return LOCNUM;
    }

    public void setLOCNUM(String LOCNUM) {
        this.LOCNUM = LOCNUM;
    }

    public String getMODELTYPE() {
        return MODELTYPE;
    }

    public void setMODELTYPE(String MODELTYPE) {
        this.MODELTYPE = MODELTYPE;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getSAPNUM() {
        return SAPNUM;
    }

    public void setSAPNUM(String SAPNUM) {
        this.SAPNUM = SAPNUM;
    }

    public String getSITEID() {
        return SITEID;
    }

    public void setSITEID(String SITEID) {
        this.SITEID = SITEID;
    }

    public String getTSDATE() {
        return TSDATE;
    }

    public void setTSDATE(String TSDATE) {
        this.TSDATE = TSDATE;
    }

    public String getXJDATE() {
        return XJDATE;
    }

    public void setXJDATE(String XJDATE) {
        this.XJDATE = XJDATE;
    }

    public String getYYSDATE() {
        return YYSDATE;
    }

    public void setYYSDATE(String YYSDATE) {
        this.YYSDATE = YYSDATE;
    }

    public String getZYYDATE() {
        return ZYYDATE;
    }

    public void setZYYDATE(String ZYYDATE) {
        this.ZYYDATE = ZYYDATE;
    }
}
