package com.example.admin.mingyang_object.config;

/**
 * Created by think on 2015/10/19.
 */
public class Constants {

    /**
     * 基础接口*
     */
    /**
     * 默认*
     */
    public static final String HTTP_API_IP = "http://deveam.mywind.com.cn:7001";
//    public static final String HTTP_API_URL = "http://121.35.242.172:7001/maximo/mobile/";
//
//    public static final String HTTPS_API_URL = "http://121.35.242.172:7001/meaweb/services";


    /**
     * 登陆URL*
     */

    public static final String SIGN_IN_URL = "/maximo/mobile/system/login";


    /**
     * 通用接口查询*
     */
    public static final String BASE_URL = "/maximo/mobile/common/api";

    /**
     * 工单接口*
     */
    public static final String WORK_URL = "/meaweb/services/WORKORDERSERVICE";

    /**
     * 领料单接口*
     */
    public static final String INVUSE_URL = "/meaweb/services/INVUSESERVICE";
    /**
     * 工作流*
     */
    public static final String WORK_FLOW_URL = "/meaweb/services/WFMENAGEMENTSERVIC";

    /**
     * ------------------数据库表名配置－－开始*
     */
    //待办事项的appid
    public static final String WFASSIGNMENT_APPID = "WFDESIGN";

    //待办事项的表名
    public static final String WFASSIGNMENT_NAME = "WFASSIGNMENT";
    /**
     * 工单管理*
     */
    //故障工单查询的appid
    public static final String UDWOCM_APPID = "UDWOCM";
    //故障工单表名
    public static final String WORKORDER_NAME = "WORKORDER";
    //工单计划任务表名
    public static final String WOACTIVITY_NAME = "WOACTIVITY";
    //调试工单子表appid
    public static final String UDDEBUGWORKORDERLINE_APPID = "UDDEBUGWORKORDERLINE";
    //调试工单子表表名
    public static final String UDDEBUGWORKORDERLINE_NAME = "UDDEBUGWORKORDERLINE";
    //工单计划员工表名
    public static final String WPLABOR_NAME = "WPLABOR";
    //工单计划物料表名
    public static final String WPITEM_NAME = "WPITEM";
    //工单任务分配表名
    public static final String ASSIGNMENT_NAME = "ASSIGNMENT";
    //工单实际员工表名
    public static final String LABTRANS_NAME = "LABTRANS";
    //故障汇报表名
    public static final String FAILUREREPORT_NAME = "FAILUREREPORT";


    /**
     * 工程台账*
     */
    //工程台账appid
    public static final String UDPRO_APPID = "UDPROJECT";
    //工程台账表名**/
    public static final String UDPRO_NAME = "UDPRO";
    /**
     * 风机型号appid*
     */
    public static final String UDFANDETAILS_APPID = "UDFANDETAILS";
    /**
     * 风机型号表名*
     */
    public static final String UDFANDETAILS_NAME = "UDFANDETAILS";

    /**
     * 项目人员appid*
     */
    public static final String UDPERSON_APPID = "UDPERSON";
    /**
     * 项目人员表名*
     */
    public static final String UDPERSON_NAME = "PERSON";

    /**
     * 项目车辆appid*
     */
    public static final String UDVEHICLE_APPID = "UDVEHICLE";
    /**
     * 项目车辆表名*
     */
    public static final String UDVEHICLE_NAME = "UDVEHICLE";


    /**
     * 项目日报appid*
     */
    public static final String UDPRORUNLOG_APPID = "UDPRORUN";
    /**
     * 项目日报表名*
     */
    public static final String UDPRORUNLOG_NAME = "UDPRORUNLOG";

    /**
     * 问题联络单appid*
     */
    public static final String UDFEEDBACK_APPID = "UDFEDBKCON";
    /**
     * 问题联络单表名*
     */
    public static final String UDFEEDBACK_NAME = "UDFEEDBACK";


    /**
     * 库存盘点appid*
     */
    public static final String UDSTOCK_APPID = "UDSTOCK";
    /**
     * 问题联络单表名*
     */
    public static final String UDSTOCK_NAME = "UDSTOCK";







    //设备appid
    public static final String ASSET_APPID = "UDASSET";
    //设备表名
    public static final String ASSET_NAME = "ASSET";
    //作业计划appid
    public static final String JOBPLAN_APPID = "UDPLANSTND";
    //作业计划表名
    public static final String JOBPLAN_NAME = "JOBPLAN";
    //人员appid
    public static final String PERSON_APPID = "UDPERSON";
    //人员表名
    public static final String PERSON_NAME = "PERSON";



    /**
     * 位置表*
     */
    public static final String UDSTORELOC_APPID = "UDSTORELOC";
    /**
     * 位置表名*
     */
    public static final String LOCATIONS_NAME = "LOCATIONS";

    /**
     * 选择工单*
     */
    public static final String WOTRACK_APPID = "WOTRACK";
    /**
     * 选择工单表名*
     */
    public static final String WORKORDER_APPID = "WORKORDER";

    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";


    /**
     * 用户登录表识--开始*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //登录成功

    public static final String CHANGEIMEI = "USER-S-104"; //登录成功,检测到用户更换手机登录

    public static final String USERNAMEERROR = "USER-E-100";//用户名密码错误

    public static final String GETDATASUCCESS = "GLOBAL-S-0";//获取数据成功

    /**
     * 工单跳转类型标识
     */
    public static final String FR = "FR";//故障工单
    public static final String AA = "AA";//终验收工单
    public static final String DC = "DC";//调试工单
    public static final String SP = "SP";//排查工单
    public static final String TP = "TP";//技改工单
    public static final String WS = "WS";//定检工单

    //工单状态
    public static final String STATUS1 = "已批准";
    public static final String STATUS2 = "取消";
    public static final String STATUS3 = "关闭";
    public static final String STATUS4 = "完成";
    public static final String STATUS5 = "等待厂家处理";
    public static final String STATUS6 = "已验收";
    public static final String STATUS7 = "工单执行";
    public static final String STATUS8 = "已完成/关闭";
    public static final String STATUS9 = "提交监督审核";
    public static final String STATUS10 = "提交主任审核";
    public static final String STATUS11 = "工作计划";
    public static final String STATUS12 = "未通过实现后复审";
    public static final String STATUS13 = "调度";
    public static final String STATUS14 = "调度";
    public static final String STATUS15 = "复审";
    public static final String STATUS16 = "已拒绝";
    public static final String STATUS17 = "已拒绝";
    public static final String STATUS18 = "提交主任分配";
    public static final String STATUS19 = "工单修正";
    public static final String STATUS20 = "询比价处理";
    public static final String STATUS21 = "审核完成";
    public static final String STATUS22 = "外协执行";
    public static final String STATUS23 = "外协执行完成";
    public static final String STATUS24 = "服务验收";
    public static final String STATUS25 = "工单建立";


    /**设置数据库参数-开始**/
    /**
     * 数据库路径
     */
    public static final String PATH_DB = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/";
    /**
     * 无SD卡的数据库路径
     */
    public static final String NOT_SDCARD_PATH_DB = "/data/data/";
    /**
     * 数据库名称 *
     */
    public static final String TB_NAME = "sqlite-eam.db";

    /**
     * 选项跳转请求值
     */
    public static final int PERSONCODE = 100; //人员

    public static final int REGULARINSCODE = 110;//定检计划编号

    public static final int WS_JOBPLANCODE = 120;//定检作业计划

}
