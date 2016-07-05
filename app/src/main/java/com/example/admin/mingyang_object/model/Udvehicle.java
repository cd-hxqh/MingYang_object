package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 * 车辆
 */
public class Udvehicle implements Serializable {

    private String DRIVER; //司机
    private String LICENSENUM; //是车牌号
    private String PRONUM; //
    private String TOTALMILEAGE;//


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
}
