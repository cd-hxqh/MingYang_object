package com.example.admin.mingyang_object.model;

import java.io.Serializable;


/**
 * Created by think on 2016/7/7.
 * 部门
 */
public class Uddept implements Serializable {
    private String DEPTNUM;//部门编号
    private String DESCRIPTION;//部门描述

    public String getDEPTNUM() {
        return DEPTNUM;
    }

    public void setDEPTNUM(String DEPTNUM) {
        this.DEPTNUM = DEPTNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
}
