package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 车辆
 */
public class Udvehicle implements Serializable {

    private String DRIVER; //司机
    private String LICENSENUM; //是车牌号
    private String PRONUM; //所属项目
    private String PRODESC;//所属项目描述
    private String BRANCH;//所属中心
    private String BRANCHDESC;//所属中心描述
    private String TOTALMILEAGE;//
    private String ENDNUMBER;//结束里程数/下次起始里程数
    private String NUMBER1;//加油里程数
    private String NUMBER2;//行驶里程数
    private String NUMBER3;//维修里程数


    public String getDRIVER() {
        return DRIVER;
    }

    public void setDRIVER(String DRIVER) {
        this.DRIVER = DRIVER;
    }

    public String getLICENSENUM() {
        return LICENSENUM;
    }

    public void setLICENSENUM(String LICENSENUM) {
        this.LICENSENUM = LICENSENUM;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getTOTALMILEAGE() {
        return TOTALMILEAGE;
    }

    public void setTOTALMILEAGE(String TOTALMILEAGE) {
        this.TOTALMILEAGE = TOTALMILEAGE;
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

    public String getENDNUMBER() {
        return ENDNUMBER;
    }

    public void setENDNUMBER(String ENDNUMBER) {
        this.ENDNUMBER = ENDNUMBER;
    }

    public String getNUMBER1() {
        return NUMBER1;
    }

    public void setNUMBER1(String NUMBER1) {
        this.NUMBER1 = NUMBER1;
    }

    public String getNUMBER2() {
        return NUMBER2;
    }

    public void setNUMBER2(String NUMBER2) {
        this.NUMBER2 = NUMBER2;
    }

    public String getNUMBER3() {
        return NUMBER3;
    }

    public void setNUMBER3(String NUMBER3) {
        this.NUMBER3 = NUMBER3;
    }
}
