package com.example.admin.eam.utils;

import com.example.admin.eam.config.Constants;

/**
 * Created by think on 2016/6/8.
 */
public class WorkTypeUtils {
    public static String getTitle(String type){
        String title = "工单";
        switch (type){
            case "FR"://故障工单
                title ="故障工单";
                break;
            case "AA"://终验收工单
                title ="终验收工单";
                break;
            case "DC"://调试工单
                title ="调试工单";
                break;
            case "SP"://排查工单
                title ="排查工单";
                break;
            case "TP"://技改工单
                title ="技改工单";
                break;
            case "WS"://定检工单
                title ="定检工单";
                break;
        }
        return title;
    }

    public static String getProcessname(String type){
        String Processname = "";
        switch (type){
            case "FR"://故障工单
                Processname = Constants.UDGZWO;
                break;
            case "AA"://终验收工单
                Processname = Constants.UDZYSWO;
                break;
            case "SP"://排查工单
                Processname = Constants.UDPCWO;
                break;
            case "TP"://技改工单
                Processname = Constants.UDJGWO;
                break;
            case "WS"://定检工单
                Processname = Constants.UDDJWO;
                break;
            case "DC"://调试工单
                Processname = Constants.UDDEBUGWOR;
                break;
        }
        return Processname;
    }
    public static String getAppId(String type){
        String appid = "";
        switch (type){
            case "FR"://故障工单
                appid = "UDREPORTWO";
                break;
            case "AA"://终验收工单
                appid = "UDZYSWO";
                break;
            case "SP"://排查工单
                appid = "UDPCWO";
                break;
            case "TP"://技改工单
                appid = "UDJGWO";
                break;
            case "WS"://定检工单
                appid ="UDDJWO";
                break;
            case "DC"://调试工单
                appid = "DEBUGORDER";
                break;
        }
        return appid;
    }
}
