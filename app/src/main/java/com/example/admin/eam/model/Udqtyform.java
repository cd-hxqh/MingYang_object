package com.example.admin.eam.model;

import java.io.Serializable;

/**
 * Created by think on 2016/7/9.
 * 质量问题反馈单
 */
public class Udqtyform implements Serializable{

    public String PRONUM;//项目编号
    public String FAULTDATE;//故障发生日期
    public String PERSONID;//提出人

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getFAULTDATE() {
        return FAULTDATE;
    }

    public void setFAULTDATE(String FAULTDATE) {
        this.FAULTDATE = FAULTDATE;
    }

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }
}
