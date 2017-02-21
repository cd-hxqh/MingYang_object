package com.example.admin.eam.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/8.
 */
public class REGULARINSPECTIONPLANLINK implements Serializable {
    private String PLANNO;//定检计划编号
    private String PRONUM;//项目编号
    private String PRODESC;//项目描述
    private String BRANCH;//中心编号
    private String BRANCHDESC;//中心描述
    private String HEAD;//负责人
    private String HEADNAME;//负责人描述
    private String FJNO;//风机型号
    private String STANDARDNUM;//定检标准编号
    private String JPDESC;//定检标准描述
    private String PLANSTARTTIME;//计划开始时间
    private String PLANENDTIME;//计划完成时间
    private String REGULARINSPECTIONTYPE;//定检类型

    public String getPLANNO() {
        return PLANNO;
    }

    public void setPLANNO(String PLANNO) {
        this.PLANNO = PLANNO;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
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

    public String getHEAD() {
        return HEAD;
    }

    public void setHEAD(String HEAD) {
        this.HEAD = HEAD;
    }

    public String getHEADNAME() {
        return HEADNAME;
    }

    public void setHEADNAME(String HEADNAME) {
        this.HEADNAME = HEADNAME;
    }

    public String getFJNO() {
        return FJNO;
    }

    public void setFJNO(String FJNO) {
        this.FJNO = FJNO;
    }

    public String getSTANDARDNUM() {
        return STANDARDNUM;
    }

    public void setSTANDARDNUM(String STANDARDNUM) {
        this.STANDARDNUM = STANDARDNUM;
    }

    public String getJPDESC() {
        return JPDESC;
    }

    public void setJPDESC(String JPDESC) {
        this.JPDESC = JPDESC;
    }

    public String getPLANSTARTTIME() {
        return PLANSTARTTIME;
    }

    public void setPLANSTARTTIME(String PLANSTARTTIME) {
        this.PLANSTARTTIME = PLANSTARTTIME;
    }

    public String getPLANENDTIME() {
        return PLANENDTIME;
    }

    public void setPLANENDTIME(String PLANENDTIME) {
        this.PLANENDTIME = PLANENDTIME;
    }

    public String getREGULARINSPECTIONTYPE() {
        return REGULARINSPECTIONTYPE;
    }

    public void setREGULARINSPECTIONTYPE(String REGULARINSPECTIONTYPE) {
        this.REGULARINSPECTIONTYPE = REGULARINSPECTIONTYPE;
    }
}
