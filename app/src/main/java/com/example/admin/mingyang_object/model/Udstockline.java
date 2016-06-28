package com.example.admin.mingyang_object.model;


import java.io.Serializable;

/**
 * Created by think on 2015/12/10
 * 库存盘点行
 */
public class Udstockline implements Serializable {


    private String ZPDROW;//行项目
    private String LGORT;//位置
    private String MAKTX;//物料描述
    private String MATNR;//物料编码
    private String MSEHL;//单位
    private String STOCKNUM;//盘点编号
    private String ACTUALQTY;//实盘数量
    private String DIFFQTY;//差异数量
    private String DIFFREASON;//差异原因

    public String getZPDROW() {
        return ZPDROW;
    }

    public void setZPDROW(String ZPDROW) {
        this.ZPDROW = ZPDROW;
    }

    public String getLGORT() {
        return LGORT;
    }

    public void setLGORT(String LGORT) {
        this.LGORT = LGORT;
    }

    public String getMAKTX() {
        return MAKTX;
    }

    public void setMAKTX(String MAKTX) {
        this.MAKTX = MAKTX;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public String getMSEHL() {
        return MSEHL;
    }

    public void setMSEHL(String MSEHL) {
        this.MSEHL = MSEHL;
    }

    public String getSTOCKNUM() {
        return STOCKNUM;
    }

    public void setSTOCKNUM(String STOCKNUM) {
        this.STOCKNUM = STOCKNUM;
    }

    public String getACTUALQTY() {
        return ACTUALQTY;
    }

    public void setACTUALQTY(String ACTUALQTY) {
        this.ACTUALQTY = ACTUALQTY;
    }

    public String getDIFFQTY() {
        return DIFFQTY;
    }

    public void setDIFFQTY(String DIFFQTY) {
        this.DIFFQTY = DIFFQTY;
    }

    public String getDIFFREASON() {
        return DIFFREASON;
    }

    public void setDIFFREASON(String DIFFREASON) {
        this.DIFFREASON = DIFFREASON;
    }
}
