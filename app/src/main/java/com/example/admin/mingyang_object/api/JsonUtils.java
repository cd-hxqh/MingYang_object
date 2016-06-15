package com.example.admin.mingyang_object.api;

import android.content.Context;
import android.util.Log;

import com.example.admin.mingyang_object.bean.LoginResults;
import com.example.admin.mingyang_object.config.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Json数据解析类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static LoginResults parsingAuthStr(final Context cxt, String data) {
        Log.i(TAG, "data=" + data);
        LoginResults loginResults = new LoginResults();
        try {
            JSONObject json = new JSONObject(data);
            String errcode = json.getString("errcode");
            String errmsg = json.getString("errmsg");
            loginResults.setErrcode(errcode);
            loginResults.setErrmsg(errmsg);
            if (errcode.equals(Constants.LOGINSUCCESS) || errcode.equals(Constants.CHANGEIMEI)) {
                loginResults.setResult(json.getString("result"));
            }


            return loginResults;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


}