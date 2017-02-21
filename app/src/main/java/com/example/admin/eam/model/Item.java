package com.example.admin.eam.model;

/**
 * Created by think on 2016/7/1.
 * 物资编码表
 */
public class Item {
    public String ITEMNUM;//物资编码
    public String DESCRIPTION;//描述
    public String ORDERUNIT;//订购单位

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getORDERUNIT() {
        return ORDERUNIT;
    }

    public void setORDERUNIT(String ORDERUNIT) {
        this.ORDERUNIT = ORDERUNIT;
    }
}
