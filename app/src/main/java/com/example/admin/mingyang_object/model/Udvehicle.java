package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by apple on 15/10/26.
 *项目车辆
 */
public class Udvehicle implements Serializable {

    private String LICENSENUM; //车牌号
    private String DRIVER; //司机编号
    private String TOTALMILEAGE; //累计行驶里程数
    private String UDPRONUM; //项目台账编号


    public String getLICENSENUM() {
        return LICENSENUM;
    }

    public void setLICENSENUM(String LICENSENUM) {
        this.LICENSENUM = LICENSENUM;
    }

    public String getDRIVER() {
        return DRIVER;
    }

    public void setDRIVER(String DRIVER) {
        this.DRIVER = DRIVER;
    }

    public String getTOTALMILEAGE() {
        return TOTALMILEAGE;
    }

    public void setTOTALMILEAGE(String TOTALMILEAGE) {
        this.TOTALMILEAGE = TOTALMILEAGE;
    }

    public String getUDPRONUM() {
        return UDPRONUM;
    }

    public void setUDPRONUM(String UDPRONUM) {
        this.UDPRONUM = UDPRONUM;
    }
}
