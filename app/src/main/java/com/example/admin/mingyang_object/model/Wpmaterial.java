package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/30.
 * 物料
 */
public class Wpmaterial implements Serializable{
    public String ITEMNUM;//物资编码
    public String ITEMDESC;//物资描述
    public String ITEMQTY;//数量
    public String ORDERUNIT;//订购单位
    public String LOCATION;//库房
    public String LOCDESC;//库房描述
//    public String
    public String optiontype;
    public String WONUM;
    public boolean isUpload;

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getITEMQTY() {
        return ITEMQTY;
    }

    public void setITEMQTY(String ITEMQTY) {
        this.ITEMQTY = ITEMQTY;
    }

    public String getORDERUNIT() {
        return ORDERUNIT;
    }

    public void setORDERUNIT(String ORDERUNIT) {
        this.ORDERUNIT = ORDERUNIT;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setITEMDESC(String ITEMDESC) {
        this.ITEMDESC = ITEMDESC;
    }

    public String getLOCDESC() {
        return LOCDESC;
    }

    public void setLOCDESC(String LOCDESC) {
        this.LOCDESC = LOCDESC;
    }
}
