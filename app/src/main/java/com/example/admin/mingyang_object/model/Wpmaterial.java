package com.example.admin.mingyang_object.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/6/30.
 * 物料
 */
@DatabaseTable(tableName = "Wpmaterial")
public class Wpmaterial implements Serializable{

    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "WPITEMID")
    public int WPITEMID;//唯一标识
    @DatabaseField(columnName = "ITEMNUM")
    public String ITEMNUM;//物资编码
    @DatabaseField(columnName = "ITEMDESC")
    public String ITEMDESC;//物资描述
    @DatabaseField(columnName = "ITEMQTY")
    public String ITEMQTY;//数量
    @DatabaseField(columnName = "ORDERUNIT")
    public String ORDERUNIT;//订购单位
    @DatabaseField(columnName = "LOCATION")
    public String LOCATION;//库房
    @DatabaseField(columnName = "LOCDESC")
    public String LOCDESC;//库房描述
    @DatabaseField(columnName = "TYPE")
    public String TYPE;
    @DatabaseField(columnName = "WONUM")
    public String WONUM;
    @DatabaseField(columnName = "isUpload")
    public boolean isUpload;
    @DatabaseField(columnName = "belongid")
    public int belongid;//所属工单本地存储id


    public int getWPITEMID() {
        return WPITEMID;
    }

    public void setWPITEMID(int WPITEMID) {
        this.WPITEMID = WPITEMID;
    }

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

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getWONUM() {
        return WONUM;
    }

    public void setWONUM(String WONUM) {
        this.WONUM = WONUM;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }
}
