package com.example.admin.mingyang_object.utils;

import com.example.admin.mingyang_object.config.Constants;

/**
 * Created by Administrator on 2016/8/25.
 */
public class GetWorkTypeUtil {
    public static String getWorkType(String processname){
        switch (processname){
            case Constants.UDDJWO:
                return Constants.WS;
            case Constants.UDGZWO:
                return Constants.FR;
            case Constants.UDJGWO:
                return Constants.TP;
            case Constants.UDPCWO:
                return Constants.SP;
            case Constants.UDZYSWO:
                return Constants.AA;
        }
        return "";
    }
}
