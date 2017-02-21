package com.example.admin.eam.model;

/**
 * Created by think on 2016/6/29.
 * ,故障代码
 */
public class Failurelist {
    public String FAILURECODE;
    public String CODEDESC;
    public int PARENT;
    public String TYPE;
    public int FAILURELIST;
    public String LOCDESC;//位置描述
    public String UDDESCRIPTION;//触发条件
    public String LOCATION;//位置编号

    public String getFAILURECODE() {
        return FAILURECODE;
    }

    public void setFAILURECODE(String FAILURECODE) {
        this.FAILURECODE = FAILURECODE;
    }

    public String getCODEDESC() {
        return CODEDESC;
    }

    public void setCODEDESC(String CODEDESC) {
        this.CODEDESC = CODEDESC;
    }

    public int getPARENT() {
        return PARENT;
    }

    public void setPARENT(int PARENT) {
        this.PARENT = PARENT;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getFAILURELIST() {
        return FAILURELIST;
    }

    public void setFAILURELIST(int FAILURELIST) {
        this.FAILURELIST = FAILURELIST;
    }

    public String getLOCDESC() {
        return LOCDESC;
    }

    public void setLOCDESC(String LOCDESC) {
        this.LOCDESC = LOCDESC;
    }

    public String getUDDESCRIPTION() {
        return UDDESCRIPTION;
    }

    public void setUDDESCRIPTION(String UDDESCRIPTION) {
        this.UDDESCRIPTION = UDDESCRIPTION;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }
}
