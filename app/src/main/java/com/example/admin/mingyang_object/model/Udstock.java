package com.example.admin.mingyang_object.model;


import java.io.Serializable;

/**
 * Created by think on 2015/12/10
 * 库存盘点
 */
public class Udstock implements Serializable {


    private String STOCKNUM;//盘点单号
    private String DESCRIPTION;//描述
    private String LOCATION;//仓库编号
    private String LOCDESC;//仓库名称
    private String STATUS;//状态
    private String CREATEDBY;//创建人
    private String CREATEDATE;//创建时间
    private String ZPDNUM;//过账时间


    public String getSTOCKNUM() {
        return STOCKNUM;
    }

    public void setSTOCKNUM(String STOCKNUM) {
        this.STOCKNUM = STOCKNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getLOCDESC() {
        return LOCDESC;
    }

    public void setLOCDESC(String LOCDESC) {
        this.LOCDESC = LOCDESC;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCREATEDBY() {
        return CREATEDBY;
    }

    public void setCREATEDBY(String CREATEDBY) {
        this.CREATEDBY = CREATEDBY;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getZPDNUM() {
        return ZPDNUM;
    }

    public void setZPDNUM(String ZPDNUM) {
        this.ZPDNUM = ZPDNUM;
    }
}
