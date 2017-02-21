package com.example.admin.eam.model;

import java.io.Serializable;


/**
 * Created by think on 2016/7/7.
 * 行驶记录业务单号
 */
public class Udwd implements Serializable {

    private String UDTHREEWDID;//id

    private String NUM;//单号

    private String DESCRIPTION;//描述

    public String getUDTHREEWDID() {
        return UDTHREEWDID;
    }

    public void setUDTHREEWDID(String UDTHREEWDID) {
        this.UDTHREEWDID = UDTHREEWDID;
    }

    public String getNUM() {
        return NUM;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
}
