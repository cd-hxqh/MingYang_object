package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/29.
 * 终验收计划表
 *
 */
public class Udinvestp implements Serializable {
    public String PLANNUM;
    public String APPTYPE;
    public String PROJECTNUM;//项目编号

    public String getPLANNUM() {
        return PLANNUM;
    }

    public void setPLANNUM(String PLANNUM) {
        this.PLANNUM = PLANNUM;
    }

    public String getAPPTYPE() {
        return APPTYPE;
    }

    public void setAPPTYPE(String APPTYPE) {
        this.APPTYPE = APPTYPE;
    }

    public String getPROJECTNUM() {
        return PROJECTNUM;
    }

    public void setPROJECTNUM(String PROJECTNUM) {
        this.PROJECTNUM = PROJECTNUM;
    }
}
