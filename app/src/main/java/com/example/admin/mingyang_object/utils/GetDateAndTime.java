package com.example.admin.mingyang_object.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by think on 2016/5/30.
 */
public class GetDateAndTime {
    static SimpleDateFormat dateformat;
    public static String GetDateTime(){
        dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateformat.format(new Date());
    }

    public static String GetDate(){
        dateformat = new SimpleDateFormat("yyyy-MM-dd");
        return dateformat.format(new Date());
    }

    public static String GetTime(){
        dateformat = new SimpleDateFormat("HH:mm:ss");
        return dateformat.format(new Date());
    }
}
