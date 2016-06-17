package com.example.admin.mingyang_object.utils;

/**
 * Created by think on 2016/6/8.
 */
public class WorkTitle {
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
}
