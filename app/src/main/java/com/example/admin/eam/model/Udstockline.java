package com.example.admin.eam.model;


import java.io.Serializable;

/**
 * Created by think on 2015/12/10
 * 库存盘点行
 */
public class Udstockline implements Serializable {

    private int UDSTOCKLINEID;
    private String ZPDROW;//行项目
    private String LGORT;//位置
    private String MAKTX;//物料描述
    private String MATNR;//物料编码
    private String MSEHL;//单位
    private String NUMEXIST;//账存数量
    private String STOCKNUM;//盘点编号
    private String ACTUALQTY;//实盘数量
    private int DIFFQTY;//差异数量
    private String DIFFREASON;//差异原因

    private String TYPE;

    public int getUDSTOCKLINEID() {
        return UDSTOCKLINEID;
    }

    public void setUDSTOCKLINEID(int UDSTOCKLINEID) {
        this.UDSTOCKLINEID = UDSTOCKLINEID;
    }

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

    public String getNUMEXIST() {
        return NUMEXIST;
    }

    public void setNUMEXIST(String NUMEXIST) {
        this.NUMEXIST = NUMEXIST;
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

    public void setACTUALQTY(String  ACTUALQTY) {
        this.ACTUALQTY = ACTUALQTY;
    }

    public int getDIFFQTY() {
        return DIFFQTY;
    }

    public void setDIFFQTY(int DIFFQTY) {
        this.DIFFQTY = DIFFQTY;
    }

    public String getDIFFREASON() {
        return DIFFREASON;
    }

    public void setDIFFREASON(String DIFFREASON) {
        this.DIFFREASON = DIFFREASON;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
