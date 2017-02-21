package com.example.admin.eam.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by apple on 15/10/26.
 * 巡检单
 */
@DatabaseTable(tableName = "Udinspo")
public class Udinspo extends Entity implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "UDINSPOID")
    private String UDINSPOID; //主键ID
    @DatabaseField(columnName = "ALLTIME")
    private String ALLTIME; //累计停机时间
    @DatabaseField(columnName = "BRANCH")
    private String BRANCH; //中心编号
    @DatabaseField(columnName = "BRANCHDESC")
    private String BRANCHDESC; //编号描述
    @DatabaseField(columnName = "CHANGEBY")
    private String CHANGEBY; //更改人编号
    @DatabaseField(columnName = "CHANGEDATE")
    private String CHANGEDATE;//更改时间
    @DatabaseField(columnName = "COMPTIME")
    private String COMPTIME; //计划完成时间
    @DatabaseField(columnName = "CREATEBY")
    private String CREATEBY; //创建人编号
    @DatabaseField(columnName = "CREATEDATE")
    private String CREATEDATE; //创建时间
    @DatabaseField(columnName = "DESCRIPTION")
    private String DESCRIPTION; //描述
    @DatabaseField(columnName = "FJNUM")
    private String FJNUM; //设备位置
    @DatabaseField(columnName = "INSPOBY")
    private String INSPOBY; //巡检人员
    @DatabaseField(columnName = "INSPOBY2")
    private String INSPOBY2; //巡检人员编号
    @DatabaseField(columnName = "INSPOBY3")
    private String INSPOBY3; //巡检人员编号
    @DatabaseField(columnName = "INSPOBY4")
    private String INSPOBY4; //巡检人员编号
    @DatabaseField(columnName = "INSPOBY5")
    private String INSPOBY5; //巡检人员编号
    @DatabaseField(columnName = "INSPOBY6")
    private String INSPOBY6; //巡检人员编号
    @DatabaseField(columnName = "INSPODATE")
    private String INSPODATE; //巡检日期
    @DatabaseField(columnName = "INSPONUM")
    private String INSPONUM; //巡检单编号
    @DatabaseField(columnName = "INSPPLANNUM")
    private String INSPPLANNUM; //巡检计划编号
    @DatabaseField(columnName = "ISSTOP")
    private String ISSTOP; //是否停机？
    @DatabaseField(columnName = "JPNUM")
    private String JPNUM; //巡检标准
    @DatabaseField(columnName = "LASTRUNDATE")
    private String LASTRUNDATE; //上次巡检时间
    @DatabaseField(columnName = "MODELTYPE")
    private String MODELTYPE; //风机型号
    @DatabaseField(columnName = "NEXTRUNDATE")
    private String NEXTRUNDATE; //下次巡检时间
    @DatabaseField(columnName = "OKTIME")
    private String OKTIME; //恢复时间
    @DatabaseField(columnName = "PRONUM")
    private String PRONUM; //项目编号
    @DatabaseField(columnName = "RESBY")
    private String RESBY; //巡检负责人编号
    @DatabaseField(columnName = "STARTTIME")
    private String STARTTIME; //计划开始时间
    @DatabaseField(columnName = "STATUS")
    private String STATUS; //状态
    @DatabaseField(columnName = "STOPTIME")
    private String STOPTIME; //停机时间
    @DatabaseField(columnName = "UDLOCNUM")
    private String UDLOCNUM; //机位号
    @DatabaseField(columnName = "WEATHER")
    private String WEATHER; //天气
    @DatabaseField(columnName = "PRODESC")
    private String PRODESC; //项目名称
    @DatabaseField(columnName = "JPDESC")
    private String JPDESC; //巡检标准名称
    @DatabaseField(columnName = "FJDESC")
    private String FJDESC; //设备位置名称
    @DatabaseField(columnName = "NAME")
    private String NAME; //巡检负责人名称
    @DatabaseField(columnName = "NAME1")
    private String NAME1; //巡检人员名称
    @DatabaseField(columnName = "NAME2")
    private String NAME2; //巡检人员名称
    @DatabaseField(columnName = "NAME3")
    private String NAME3; //巡检人员名称
    @DatabaseField(columnName = "NAME4")
    private String NAME4; //巡检人员名称
    @DatabaseField(columnName = "NAME5")
    private String NAME5; //巡检人员名称
    @DatabaseField(columnName = "NAME6")
    private String NAME6; //巡检人员名称
    @DatabaseField(columnName = "WONUM")
    private String WONUM; //故障工单号
    @DatabaseField(columnName = "UDWPTYPE")
    private String UDWPTYPE; //处理人员类型
    @DatabaseField(columnName = "UDWP")
    private String UDWP; //处理人员

    @DatabaseField(columnName = "isUpdate")
    public boolean isUpdate;//是否是本地已修改巡检单
    @DatabaseField(columnName = "belong")
    public String  belong;//工单所属用户

    private HashMap<String,String> map = new HashMap<String,String>();
    public HashMap<String,String> getMap()
    {

        map.put("ALLTIME","累计停机时间");
        map.put("BRANCH","中心编号");
        map.put("BRANCHDESC","编号描述");
        map.put("CHANGEBY","更改人编号");
        map.put("CHANGEDATE","更改时间");
        map.put("COMPTIME","计划完成时间");
        map.put("CREATEBY","创建人编号");
        map.put("CREATEDATE","创建时间");
        map.put("DESCRIPTION; //描述","");
        map.put("FJNUM","设备位置");
        map.put("INSPOBY","巡检人员");
        map.put("INSPOBY2","巡检人员编号");
        map.put("INSPOBY3","巡检人员编号");
        map.put("INSPOBY4","巡检人员编号");
        map.put("INSPOBY5","巡检人员编号");
        map.put("INSPOBY6","巡检人员编号");
        map.put("INSPODATE","巡检日期");
        map.put("INSPONUM","巡检单编号");
        map.put("INSPPLANNUM","巡检计划编号");
        map.put("ISSTOP","是否停机？");
        map.put("JPNUM","巡检标准");
        map.put("LASTRUNDATE","上次巡检时间");
        map.put("MODELTYPE","风机型号");
        map.put("NEXTRUNDATE","下次巡检时间");
        map.put("OKTIME","恢复时间");
        map.put("PRONUM","项目编号");
        map.put("RESBY","巡检负责人编号");
        map.put("STARTTIME","计划开始时间");
        map.put("STATUS","状态");
        map.put("STOPTIME","停机时间");
        map.put("UDLOCNUM","机位号");
        map.put("WEATHER","天气");
        map.put("PRODESC","项目名称");
        map.put("JPDESC","巡检标准名称");
        map.put("FJDESC","设备位置名称");
        map.put("NAME","巡检负责人名称");
        map.put("NAME1","巡检人员名称");
        map.put("NAME2","巡检人员名称");
        map.put("NAME3","巡检人员名称");
        map.put("NAME4","巡检人员名称");
        map.put("NAME5","巡检人员名称");
        map.put("NAME6","巡检人员名称");
        map.put("WONUM","故障工单号");
        map.put("UDWPTYPE","处理人员类型");
        map.put("UDWP","处理人员");
        return map;
    }

    public String getUDINSPOID() {
        return UDINSPOID;
    }

    public void setUDINSPOID(String UDINSPOID) {
        this.UDINSPOID = UDINSPOID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME1() {
        return NAME1;
    }

    public void setNAME1(String NAME1) {
        this.NAME1 = NAME1;
    }

    public String getNAME2() {
        return NAME2;
    }

    public void setNAME2(String NAME2) {
        this.NAME2 = NAME2;
    }

    public String getNAME3() {
        return NAME3;
    }

    public void setNAME3(String NAME3) {
        this.NAME3 = NAME3;
    }

    public String getFJDESC() {
        return FJDESC;
    }

    public void setFJDESC(String FJDESC) {
        this.FJDESC = FJDESC;
    }

    public String getJPDESC() {
        return JPDESC;
    }

    public void setJPDESC(String JPDESC) {
        this.JPDESC = JPDESC;
    }

    public String getPRODESC() {
        return PRODESC;
    }

    public void setPRODESC(String PRODESC) {
        this.PRODESC = PRODESC;
    }

    public String getALLTIME() {
        return ALLTIME;
    }

    public void setALLTIME(String ALLTIME) {
        this.ALLTIME = ALLTIME;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getCHANGEBY() {
        return CHANGEBY;
    }

    public void setCHANGEBY(String CHANGEBY) {
        this.CHANGEBY = CHANGEBY;
    }

    public String getCHANGEDATE() {
        return CHANGEDATE;
    }

    public void setCHANGEDATE(String CHANGEDATE) {
        this.CHANGEDATE = CHANGEDATE;
    }

    public String getCOMPTIME() {
        return COMPTIME;
    }

    public void setCOMPTIME(String COMPTIME) {
        this.COMPTIME = COMPTIME;
    }

    public String getCREATEBY() {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY) {
        this.CREATEBY = CREATEBY;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getFJNUM() {
        return FJNUM;
    }

    public void setFJNUM(String FJNUM) {
        this.FJNUM = FJNUM;
    }

    public String getINSPOBY() {
        return INSPOBY;
    }

    public void setINSPOBY(String INSPOBY) {
        this.INSPOBY = INSPOBY;
    }

    public String getINSPOBY2() {
        return INSPOBY2;
    }

    public void setINSPOBY2(String INSPOBY2) {
        this.INSPOBY2 = INSPOBY2;
    }

    public String getINSPOBY3() {
        return INSPOBY3;
    }

    public void setINSPOBY3(String INSPOBY3) {
        this.INSPOBY3 = INSPOBY3;
    }

    public String getINSPODATE() {
        return INSPODATE;
    }

    public void setINSPODATE(String INSPODATE) {
        this.INSPODATE = INSPODATE;
    }

    public String getINSPONUM() {
        return INSPONUM;
    }

    public void setINSPONUM(String INSPONUM) {
        this.INSPONUM = INSPONUM;
    }

    public String getINSPPLANNUM() {
        return INSPPLANNUM;
    }

    public void setINSPPLANNUM(String INSPPLANNUM) {
        this.INSPPLANNUM = INSPPLANNUM;
    }

    public String getISSTOP() {
        return ISSTOP;
    }

    public void setISSTOP(String ISSTOP) {
        this.ISSTOP = ISSTOP;
    }

    public String getJPNUM() {
        return JPNUM;
    }

    public void setJPNUM(String JPNUM) {
        this.JPNUM = JPNUM;
    }

    public String getLASTRUNDATE() {
        return LASTRUNDATE;
    }

    public void setLASTRUNDATE(String LASTRUNDATE) {
        this.LASTRUNDATE = LASTRUNDATE;
    }

    public String getMODELTYPE() {
        return MODELTYPE;
    }

    public void setMODELTYPE(String MODELTYPE) {
        this.MODELTYPE = MODELTYPE;
    }

    public String getNEXTRUNDATE() {
        return NEXTRUNDATE;
    }

    public void setNEXTRUNDATE(String NEXTRUNDATE) {
        this.NEXTRUNDATE = NEXTRUNDATE;
    }

    public String getOKTIME() {
        return OKTIME;
    }

    public void setOKTIME(String OKTIME) {
        this.OKTIME = OKTIME;
    }

    public String getPRONUM() {
        return PRONUM;
    }

    public void setPRONUM(String PRONUM) {
        this.PRONUM = PRONUM;
    }

    public String getRESBY() {
        return RESBY;
    }

    public void setRESBY(String RESBY) {
        this.RESBY = RESBY;
    }

    public String getSTARTTIME() {
        return STARTTIME;
    }

    public void setSTARTTIME(String STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTOPTIME() {
        return STOPTIME;
    }

    public void setSTOPTIME(String STOPTIME) {
        this.STOPTIME = STOPTIME;
    }

    public String getUDLOCNUM() {
        return UDLOCNUM;
    }

    public void setUDLOCNUM(String UDLOCNUM) {
        this.UDLOCNUM = UDLOCNUM;
    }

    public String getWEATHER() {
        return WEATHER;
    }

    public void setWEATHER(String WEATHER) {
        this.WEATHER = WEATHER;
    }

    public String getWONUM() {
        return WONUM;
    }

    public void setWONUM(String WONUM) {
        this.WONUM = WONUM;
    }

    public String getBRANCHDESC() {
        return BRANCHDESC;
    }

    public void setBRANCHDESC(String BRANCHDESC) {
        this.BRANCHDESC = BRANCHDESC;
    }

    public String getUDWPTYPE() {
        return UDWPTYPE;
    }

    public void setUDWPTYPE(String UDWPTYPE) {
        this.UDWPTYPE = UDWPTYPE;
    }

    public String getUDWP() {
        return UDWP;
    }

    public void setUDWP(String UDWP) {
        this.UDWP = UDWP;
    }

    public String getNAME6() {
        return NAME6;
    }

    public void setNAME6(String NAME6) {
        this.NAME6 = NAME6;
    }

    public String getNAME5() {
        return NAME5;
    }

    public void setNAME5(String NAME5) {
        this.NAME5 = NAME5;
    }

    public String getNAME4() {
        return NAME4;
    }

    public void setNAME4(String NAME4) {
        this.NAME4 = NAME4;
    }

    public String getINSPOBY6() {
        return INSPOBY6;
    }

    public void setINSPOBY6(String INSPOBY6) {
        this.INSPOBY6 = INSPOBY6;
    }

    public String getINSPOBY5() {
        return INSPOBY5;
    }

    public void setINSPOBY5(String INSPOBY5) {
        this.INSPOBY5 = INSPOBY5;
    }

    public String getINSPOBY4() {
        return INSPOBY4;
    }

    public void setINSPOBY4(String INSPOBY4) {
        this.INSPOBY4 = INSPOBY4;
    }
}
