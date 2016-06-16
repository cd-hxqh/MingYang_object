package com.example.admin.mingyang_object.api;

import android.content.Context;
import android.util.Log;

import com.example.admin.mingyang_object.bean.LoginResults;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Udpro;

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

    /**解析项目台账信息**/

    public static ArrayList<Udpro> parsingUdpro(Context ctx, String data) {
        Log.i(TAG, "udpro data=" + data);
        ArrayList<Udpro> list = null;
        Udpro udpro = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udpro>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udpro = new Udpro();
                jsonObject = jsonArray.getJSONObject(i);
                udpro.pronum = jsonObject.getString("PRONUM");//项目编号
                udpro.description = jsonObject.getString("DESCRIPTION"); //项目描述
                udpro.branch = jsonObject.getString("BRANCH");//所属中心
                udpro.capacity = jsonObject.getString("CAPACITY");//总厂容量（MW）
                udpro.contractstatus = jsonObject.getString("CONTRACTSTATUS");//合同状态
                udpro.owner = jsonObject.getString("OWNER");//业务单位
                udpro.period = jsonObject.getString("PERIOD");//质保期（年）
                udpro.prostage = jsonObject.getString("PROSTAGE");//项目当前阶段
                udpro.respons = jsonObject.getString("RESPONS");//责任人编号
                udpro.signdate = jsonObject.getString("SIGNDATE");//签订时间
                list.add(udpro);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        String result = null;
        Results results = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
                JSONObject rJson = new JSONObject(result);
                String curpage = rJson.getString("curpage");
                String totalresult = rJson.getString("totalresult");
                String resultlist = rJson.getString("resultlist");
                String totalpage = rJson.getString("totalpage");
                String showcount = rJson.getString("showcount");
                results = new Results();
                results.setCurpage(Integer.valueOf(curpage));
                results.setTotalresult(totalresult);
                results.setResultlist(resultlist);
                results.setTotalpage(totalpage);
                results.setShowcount(Integer.valueOf(showcount));
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }

    }

    /**
     * 不分页解析返回的结果*
     */
    public static Results parsingResults1(Context ctx, String data) {

        String result = null;
        Results results = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
                Log.i(TAG, "result=" + result);
                results = new Results();
                results.setResultlist(result);
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }

    }

    /**
     * 解析工单信息
     */
    public static ArrayList<WorkOrder> parsingWorkOrder(Context ctx, String data, String type) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<WorkOrder> list = null;
        WorkOrder workOrder = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<WorkOrder>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workOrder = new WorkOrder();
                jsonObject = jsonArray.getJSONObject(i);
                workOrder.WONUM = jsonObject.getString("WONUM");
                workOrder.DESCRIPTION = jsonObject.getString("DESCRIPTION");
                list.add(workOrder);
            }
            return list;
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}