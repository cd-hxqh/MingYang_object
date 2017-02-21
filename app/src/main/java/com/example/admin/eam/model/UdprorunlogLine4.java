package com.example.admin.eam.model;

import java.io.Serializable;

/**
 * Created by think on 2016/7/5.
 * 工装管理
 */
public class UdprorunlogLine4 implements Serializable{
    public int UDPRORUNLOGLINE4ID;
    public String RUNLOGDATE;//日期
    public String TYPE2;//类型
    public int NUMBER1;//已到货数
    public int NUMBER2;//已吊装数
    public int NUMBER3;//已返厂数
    public int NUMBER4;//风场丢失数

    public String TYPE;
    public String PRORUNLOGNUM;
    public boolean isUpload;

    public int getUDPRORUNLOGLINE4ID() {
        return UDPRORUNLOGLINE4ID;
    }

    public void setUDPRORUNLOGLINE4ID(int UDPRORUNLOGLINE4ID) {
        this.UDPRORUNLOGLINE4ID = UDPRORUNLOGLINE4ID;
    }

    public String getRUNLOGDATE() {
        return RUNLOGDATE;
    }

    public void setRUNLOGDATE(String RUNLOGDATE) {
        this.RUNLOGDATE = RUNLOGDATE;
    }

    public String getTYPE2() {
        return TYPE2;
    }

    public void setTYPE2(String TYPE2) {
        this.TYPE2 = TYPE2;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getNUMBER1() {
        return NUMBER1;
    }

    public void setNUMBER1(int NUMBER1) {
        this.NUMBER1 = NUMBER1;
    }

    public int getNUMBER2() {
        return NUMBER2;
    }

    public void setNUMBER2(int NUMBER2) {
        this.NUMBER2 = NUMBER2;
    }

    public int getNUMBER3() {
        return NUMBER3;
    }

    public void setNUMBER3(int NUMBER3) {
        this.NUMBER3 = NUMBER3;
    }

    public int getNUMBER4() {
        return NUMBER4;
    }

    public void setNUMBER4(int NUMBER4) {
        this.NUMBER4 = NUMBER4;
    }

    public String getPRORUNLOGNUM() {
        return PRORUNLOGNUM;
    }

    public void setPRORUNLOGNUM(String PRORUNLOGNUM) {
        this.PRORUNLOGNUM = PRORUNLOGNUM;
    }
}