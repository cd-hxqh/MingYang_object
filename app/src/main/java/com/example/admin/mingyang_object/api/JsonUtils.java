package com.example.admin.mingyang_object.api;

import android.content.Context;
import android.util.Log;

import com.example.admin.mingyang_object.bean.LoginResults;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Entity;
import com.example.admin.mingyang_object.model.UdPerson;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Udvehicle;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Udpro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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


    public static ArrayList<Udpro> parsingUdpro(Context ctx, String data) {
        Log.i(TAG, "udpro data=" + data);
        ArrayList<Udpro> list = null;
        Udpro udpro = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udpro>();
            Log.i(TAG, "jsonArray length=" + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                udpro = new Udpro();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udpro.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udpro.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udpro);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udpro.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udpro, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udpro);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析风机型号信息*
     */

    public static ArrayList<Udfandetails> parsingUdfandetails(Context ctx, String data) {
        ArrayList<Udfandetails> list = null;
        Udfandetails udfandetails = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udfandetails>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udfandetails = new Udfandetails();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udfandetails.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udfandetails.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udfandetails);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udfandetails.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udfandetails, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udfandetails);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析风机型号信息*
     */

    public static ArrayList<UdPerson> parsingUdPerson(Context ctx, String data) {
        ArrayList<UdPerson> list = null;
        UdPerson udPerson = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UdPerson>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udPerson = new UdPerson();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udPerson.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udPerson.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udPerson);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udPerson.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udPerson, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udPerson);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析项目车辆信息*
     */

    public static ArrayList<Udvehicle> parsingUdvehicle(Context ctx, String data) {
        ArrayList<Udvehicle> list = null;
        Udvehicle udvehicle = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udvehicle>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udvehicle = new Udvehicle();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udvehicle.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udvehicle.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udvehicle);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udvehicle.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udvehicle, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udvehicle);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


//    /**
//     * 解析Json信息*
//     */
//
//    public static ArrayList<Entity> parsing(Class<Udpro> tclass, Context ctx, String data) {
//        ArrayList<Entity> list = null;
//        Entity entity = null;
//        try {
//            JSONArray jsonArray = new JSONArray(data);
//            JSONObject jsonObject;
//            list = new ArrayList<Entity>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                entity = new Entity(tclass);
//                jsonObject = jsonArray.getJSONObject(i);
//                Field[] field = entity.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
//                for (int j = 0; j < field.length; j++) {     //遍历所有属性
//                    field[j].setAccessible(true);
//                    String name = field[j].getName();    //获取属性的名字
//                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
//                        try {
//                            // 调用getter方法获取属性值
//                            Method getOrSet = entity.getClass().getMethod("get" + name);
//                            Object value = getOrSet.invoke(entity);
//                            if (value == null) {
//                                //调用setter方法设属性值
//                                Class[] parameterTypes = new Class[1];
//                                parameterTypes[0] = field[j].getType();
//                                getOrSet = entity.getClass().getDeclaredMethod("set" + name, parameterTypes);
//                                getOrSet.invoke(entity, jsonObject.getString(name));
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//                list.add(entity);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

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
                Field[] field = workOrder.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for(int j=0 ; j<field.length ; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name)&&jsonObject.getString(name)!=null&&!jsonObject.getString(name).equals("")){
                        try{
                            // 调用getter方法获取属性值
                            Method getOrSet = workOrder.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(workOrder);
                            if(value == null){
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = workOrder.getClass().getDeclaredMethod("set" + name,parameterTypes);
                                getOrSet.invoke(workOrder,jsonObject.getString(name));
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                list.add(workOrder);
            }
            return list;
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}