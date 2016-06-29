package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/29.
 */
public class Location implements Serializable {
    public String LOCATION;
    public String DESCRIPTION;
    public String UDPRONUM;
    public String UDLOCNUM;

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getUDPRONUM() {
        return UDPRONUM;
    }

    public void setUDPRONUM(String UDPRONUM) {
        this.UDPRONUM = UDPRONUM;
    }

    public String getUDLOCNUM() {
        return UDLOCNUM;
    }

    public void setUDLOCNUM(String UDLOCNUM) {
        this.UDLOCNUM = UDLOCNUM;
    }
}
