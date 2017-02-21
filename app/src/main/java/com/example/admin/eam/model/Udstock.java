package com.example.admin.eam.model;


import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by think on 2015/12/10
 * 库存盘点
 */
public class Udstock implements Serializable {


    private String UDSTOCKID;//主键ID
    private String STOCKNUM;//盘点单号
    private String DESCRIPTION;//描述
    private String LOCATION;//仓库编号
    private String LOCDESC;//仓库名称
    private String ISOPEN;//明盘
    private String ISCLOSE;//暗盘
    private String STATUS;//状态
    private String CREATEDBY;//创建人
    private String INVOWNER;//能够操作的仓库管理员
    private String CREATENAME;//创建人名称
    private String CREATEDATE;//创建时间
    private String ZPDNUM;//盘点凭证号

    private HashMap<String,String> map = new HashMap<String,String>();
    public HashMap<String,String> getMap()
    {

        map.put("STOCKNUM","盘点单号");
        map.put("DESCRIPTION","描述");
        map.put("LOCATION","仓库编号");
        map.put("LOCDESC","仓库名称");
        map.put("ISOPEN","明盘");
        map.put("ISCLOSE","暗盘");
        map.put("STATUS","状态");
        map.put("CREATEDBY","创建人");
        map.put("INVOWNER","能够操作的仓库管理员");
        map.put("CREATENAME","创建人名称");
        map.put("CREATEDATE","创建时间");
        map.put("ZPDNUM","盘点凭证号");
        return map;
    }

    public String getUDSTOCKID() {
        return UDSTOCKID;
    }

    public void setUDSTOCKID(String UDSTOCKID) {
        this.UDSTOCKID = UDSTOCKID;
    }

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

    public String getISOPEN() {
        return ISOPEN;
    }

    public void setISOPEN(String ISOPEN) {
        this.ISOPEN = ISOPEN;
    }

    public String getISCLOSE() {
        return ISCLOSE;
    }

    public void setISCLOSE(String ISCLOSE) {
        this.ISCLOSE = ISCLOSE;
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

    public String getCREATENAME() {
        return CREATENAME;
    }

    public void setCREATENAME(String CREATENAME) {
        this.CREATENAME = CREATENAME;
    }

    public String getINVOWNER() {
        return INVOWNER;
    }

    public void setINVOWNER(String INVOWNER) {
        this.INVOWNER = INVOWNER;
    }
}
