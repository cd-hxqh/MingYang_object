package com.example.admin.eam.model;

/**
 * Created by chris on 2017/2/24.
 */

public class DocInfo {

    private String DOCINFOID;//
    private String DOCTYPE;//文件类型
    private String URLNAME;//地址

    public String getDOCINFOID() {
        return DOCINFOID;
    }

    public void setDOCINFOID(String DOCINFOID) {
        this.DOCINFOID = DOCINFOID;
    }

    public String getDOCTYPE() {
        return DOCTYPE;
    }

    public void setDOCTYPE(String DOCTYPE) {
        this.DOCTYPE = DOCTYPE;
    }

    public String getURLNAME() {
        return URLNAME;
    }

    public void setURLNAME(String URLNAME) {
        this.URLNAME = URLNAME;
    }
}
