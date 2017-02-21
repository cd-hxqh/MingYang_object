package com.example.admin.eam.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/27.
 */
public class JobPlan implements Serializable {
    String JPNUM;
    String DESCRIPTION;

    public String getJPNUM() {
        return JPNUM;
    }

    public void setJPNUM(String JPNUM) {
        this.JPNUM = JPNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
}
