package com.example.admin.mingyang_object.api;

import android.content.Context;
import android.util.Log;

import com.example.admin.mingyang_object.bean.LoginResults;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.example.admin.mingyang_object.model.Failurelist;
import com.example.admin.mingyang_object.model.Item;
import com.example.admin.mingyang_object.model.JobPlan;
import com.example.admin.mingyang_object.model.Location;
import com.example.admin.mingyang_object.model.Person;
import com.example.admin.mingyang_object.model.UdPerson;
import com.example.admin.mingyang_object.model.Udcardrivelog;
import com.example.admin.mingyang_object.model.Udcarfuelcharge;
import com.example.admin.mingyang_object.model.Udcarmainlog;
import com.example.admin.mingyang_object.model.UddebugWorkOrderLine;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Udfeedback;
import com.example.admin.mingyang_object.model.Udinspo;
import com.example.admin.mingyang_object.model.Udinsproject;
import com.example.admin.mingyang_object.model.Udinvestp;
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.Udreport;
import com.example.admin.mingyang_object.model.Udstock;
import com.example.admin.mingyang_object.model.Udstockline;
import com.example.admin.mingyang_object.model.Udvehicle;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.Wfassignment;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Wpmaterial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * Json数据解析类
 */
public class JsonUtils<E> {
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

    /**
     * 流程审批*
     */
    public static ArrayList<Wfassignment> parsingWfassignment(Context ctx, String data) {
        ArrayList<Wfassignment> list = null;
        Wfassignment wfassignment = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Wfassignment>();
            for (int i = 0; i < jsonArray.length(); i++) {
                wfassignment = new Wfassignment();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = wfassignment.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = wfassignment.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(wfassignment);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = wfassignment.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(wfassignment, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(wfassignment);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析新增工单返回信息
     *
     * @param data
     * @return
     */
    public static WebResult parsingInsertWO(String data, String num) {
        Log.i(TAG, "data=" + data);
        String woNum = null;
        WebResult webResult = new WebResult();
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("errorMsg") && !object.getString("errorMsg").equals("")) {
                webResult.errorMsg = object.getString("errorMsg");
            } else if (object.has("success") && !object.getString("success").equals("")) {
                webResult.errorMsg = object.getString("success");
            }
            if (object.has(num) && !object.getString(num).equals("")) {
                webResult.wonum = object.getString(num);
            }
            if (object.has("errorNo")) {
                webResult.errorNo = object.getInt("errorNo");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return webResult;
    }

    /**
     * 项目台账*
     */
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
    public static ArrayList<Location> parsingLocation(String data) {
        ArrayList<Location> list = null;
        Location location = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Location>();
            for (int i = 0; i < jsonArray.length(); i++) {
                location = new Location();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = location.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = location.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(location);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = location.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(location, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(location);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析终验收计划表信息*
     */
    public static ArrayList<Udinvestp> parsingUdinvestp(String data) {
        ArrayList<Udinvestp> list = null;
        Udinvestp location = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udinvestp>();
            for (int i = 0; i < jsonArray.length(); i++) {
                location = new Udinvestp();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = location.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = location.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(location);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = location.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(location, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(location);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析故障代码信息*
     */
    public static ArrayList<Failurelist> parsingFailurelist(String data) {
        ArrayList<Failurelist> list = null;
        Failurelist location = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Failurelist>();
            for (int i = 0; i < jsonArray.length(); i++) {
                location = new Failurelist();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = location.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.get(name) != null && !jsonObject.get(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = location.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(location);
                            if (value == null || Integer.parseInt(value + "") == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = location.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(location, jsonObject.get(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(location);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析终验收计划信息*
     */
    public static ArrayList<Item> parsingItem(String data) {
        ArrayList<Item> list = null;
        Item item = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Item>();
            for (int i = 0; i < jsonArray.length(); i++) {
                item = new Item();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = item.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = item.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(item);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = item.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(item, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(item);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析项目人员信息*
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


    /**
     * 项目日报*
     */
    public static ArrayList<Udprorunlog> parsingUdprorunlog(Context ctx, String data) {
        Log.i(TAG, "Udprorunlog data=" + data);
        ArrayList<Udprorunlog> list = null;
        Udprorunlog udprorunlog = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udprorunlog>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udprorunlog = new Udprorunlog();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udprorunlog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udprorunlog.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udprorunlog);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udprorunlog.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udprorunlog, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udprorunlog);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 问题联络单*
     */
    public static ArrayList<Udfeedback> parsingUdfeedback(Context ctx, String data) {
        ArrayList<Udfeedback> list = null;
        Udfeedback udfeedback = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udfeedback>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udfeedback = new Udfeedback();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udfeedback.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udfeedback.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udfeedback);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udfeedback.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udfeedback, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udfeedback);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 库存盘点*
     */
    public static ArrayList<Udstock> parsingUdstock(Context ctx, String data) {
        ArrayList<Udstock> list = null;
        Udstock udstock = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udstock>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udstock = new Udstock();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udstock.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udstock.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udstock);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udstock.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udstock, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udstock);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 库存盘点行*
     */
    public static ArrayList<Udstockline> parsingUdstockline(Context ctx, String data) {
        ArrayList<Udstockline> list = null;
        Udstockline udstockline = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udstockline>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udstockline = new Udstockline();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udstockline.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udstockline.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udstockline);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udstockline.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udstockline, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udstockline);
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
    public static ArrayList<WorkOrder> parsingWorkOrder(Context ctx, String data) {
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
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = workOrder.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(workOrder);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = workOrder.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(workOrder, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(workOrder);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析调试工单信息
     */
    public static ArrayList<DebugWorkOrder> parsingDebugWorkOrder(Context ctx, String data) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<DebugWorkOrder> list = null;
        DebugWorkOrder workOrder = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<DebugWorkOrder>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workOrder = new DebugWorkOrder();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = workOrder.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = workOrder.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(workOrder);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = workOrder.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(workOrder, jsonObject.getString(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(workOrder);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析调试工单子表信息
     */
    public static ArrayList<UddebugWorkOrderLine> parsingUddebugWorkOrderLine(Context ctx, String data, String wonum) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<UddebugWorkOrderLine> list = null;
        UddebugWorkOrderLine uddebugWorkOrderLine = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UddebugWorkOrderLine>();
            for (int i = 0; i < jsonArray.length(); i++) {
                uddebugWorkOrderLine = new UddebugWorkOrderLine();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = uddebugWorkOrderLine.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = uddebugWorkOrderLine.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(uddebugWorkOrderLine);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = uddebugWorkOrderLine.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(uddebugWorkOrderLine, jsonObject.getString(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                uddebugWorkOrderLine.DEBUGWORKORDERNUM = wonum;
                list.add(uddebugWorkOrderLine);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析任务信息
     */
    public static ArrayList<Woactivity> parsingWoactivity(Context ctx, String data, String wonum) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<Woactivity> list = null;
        Woactivity woactivity = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Woactivity>();
            for (int i = 0; i < jsonArray.length(); i++) {
                woactivity = new Woactivity();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = woactivity.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.get(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = woactivity.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(woactivity);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = woactivity.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(woactivity, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                woactivity.WONUM = wonum;
                woactivity.isUpload = true;
                list.add(woactivity);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析任务信息
     */
    public static ArrayList<Woactivity> parsingJobtask(String data, String wonum) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<Woactivity> list = null;
        Woactivity woactivity = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Woactivity>();
            for (int i = 0; i < jsonArray.length(); i++) {
                woactivity = new Woactivity();
                jsonObject = jsonArray.getJSONObject(i);
                woactivity.TASKID = jsonObject.getString("JPTASK");
                woactivity.DESCRIPTION = jsonObject.getString("DESCRIPTION");
                woactivity.WOJO1 = jsonObject.getString("JO1");
                woactivity.WOJO2 = jsonObject.getString("JO2");
                woactivity.WOJO3 = jsonObject.getString("JO3");
                woactivity.WOJO4 = jsonObject.getString("JO4");
                woactivity.WONUM = wonum;
                woactivity.isUpload = true;
                list.add(woactivity);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析物料信息
     */
    public static ArrayList<Wpmaterial> parsingWpmaterial(Context ctx, String data, String wonum) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<Wpmaterial> list = null;
        Wpmaterial wpmaterial = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Wpmaterial>();
            for (int i = 0; i < jsonArray.length(); i++) {
                wpmaterial = new Wpmaterial();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = wpmaterial.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = wpmaterial.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(wpmaterial);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = wpmaterial.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(wpmaterial, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                wpmaterial.WONUM = wonum;
                wpmaterial.isUpload = true;
                list.add(wpmaterial);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析人员信息
     */
    public static ArrayList<Person> parsingPerson(String data) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<Person> list;
        list = null;
        Person person = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Person>();
            for (int i = 0; i < jsonArray.length(); i++) {
                person = new Person();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = person.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = person.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(person);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = person.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(person, jsonObject.getString(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(person);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析人员信息
     */
    public static ArrayList<JobPlan> parsingJobPlan(String data) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<JobPlan> list;
        list = null;
        JobPlan jobPlan = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<JobPlan>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobPlan = new JobPlan();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = jobPlan.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = jobPlan.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(jobPlan);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = jobPlan.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(jobPlan, jsonObject.getString(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(jobPlan);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 封装工单数据
     *
     * @param workOrder
     * @param woactivities
     * @return
     */
    public static String WorkToJson(WorkOrder workOrder, ArrayList<Woactivity> woactivities, ArrayList<Wpmaterial> wpmaterials) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = workOrder.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = workOrder.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(workOrder);
                    if (value != null) {
                        jsonObject.put(name, value + "");
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            JSONObject object = new JSONObject();
            if (woactivities != null && woactivities.size() != 0) {
                object.put("WOACTIVITY", "");
                JSONArray woactivityArray = new JSONArray();
                JSONObject woactivityObj;
                for (int i = 0; i < woactivities.size(); i++) {
                    woactivityObj = new JSONObject();
                    Field[] field1 = woactivities.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = woactivities.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(woactivities.get(i));
                            if (value != null) {
                                woactivityObj.put(name, value + "");
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    woactivityArray.put(woactivityObj);
                }
                jsonObject.put("WOACTIVITY", woactivityArray);
            }
            if (wpmaterials != null && wpmaterials.size() != 0) {
                object.put("WPMATERIAL", "");
                JSONArray wpmaterialsArray = new JSONArray();
                JSONObject wpmaterialsObj;
                for (int i = 0; i < wpmaterials.size(); i++) {
                    wpmaterialsObj = new JSONObject();
                    Field[] field1 = wpmaterials.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = wpmaterials.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(wpmaterials.get(i));
                            if (value != null) {
                                wpmaterialsObj.put(name, value + "");
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    wpmaterialsArray.put(wpmaterialsObj);
                }
                jsonObject.put("WPMATERIAL", wpmaterialsArray);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
//            json.put("", "");
//            array.put(json);
//            jsonObject.put("relationShip", array);
//            if (woactivities != null && woactivities.size() != 0) {
//                JSONArray woactivityArray = new JSONArray();
//                JSONObject woactivityObj;
//                for (int i = 0; i < woactivities.size(); i++) {
//                    woactivityObj = new JSONObject();
//                    Field[] field1 = woactivities.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
//                    for (int j = 0; j < field1.length; j++) {
//                        field1[j].setAccessible(true);
//                        String name = field1[j].getName();//获取属性的名字
//                        Method getOrSet = null;
//                        try {
//                            getOrSet = woactivities.get(i).getClass().getMethod("get" + name);
//                            Object value = null;
//                            value = getOrSet.invoke(woactivities.get(i));
//                            if (value != null) {
//                                woactivityObj.put(name, value);
//                            }
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    woactivityArray.put(woactivityObj);
//                }
//                jsonObject.put("wotasks", woactivityArray);
//            } else {
//                jsonObject.put("wotasks", new JSONArray());
//            }
//            if (wpmaterials != null && wpmaterials.size() != 0) {
//                JSONArray wpmaterialArray = new JSONArray();
//                JSONObject wpmaterialObj;
//                for (int i = 0; i < wpmaterials.size(); i++) {
//                    wpmaterialObj = new JSONObject();
//                    Field[] field1 = wpmaterials.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
//                    for (int j = 0; j < field1.length; j++) {
//                        field1[j].setAccessible(true);
//                        String name = field1[j].getName();//获取属性的名字
//                        Method getOrSet = null;
//                        try {
//                            getOrSet = wpmaterials.get(i).getClass().getMethod("get" + name);
//                            Object value = null;
//                            value = getOrSet.invoke(wpmaterials.get(i));
//                            if (value != null) {
//                                wpmaterialObj.put(name, value);
//                            }
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    wpmaterialArray.put(wpmaterialObj);
//                }
//                jsonObject.put("wpmaterial", wpmaterialArray);
//            } else {
//                jsonObject.put("wpmaterial", new JSONArray());
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * 故障提报单*
     */
    public static ArrayList<Udreport> parsingUdreport(Context ctx, String data) {
        ArrayList<Udreport> list = null;
        Udreport udreport = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udreport>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udreport = new Udreport();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udreport.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udreport.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udreport);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udreport.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udreport, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udreport);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 巡检单*
     */
    public static ArrayList<Udinspo> parsingUdinspo(Context ctx, String data) {
        ArrayList<Udinspo> list = null;
        Udinspo udinspo = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udinspo>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udinspo = new Udinspo();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udinspo.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udinspo.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udinspo);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udinspo.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udinspo, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udinspo);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 巡检项目*
     */
    public static ArrayList<Udinsproject> parsingUdinsproject(Context ctx, String data) {
        ArrayList<Udinsproject> list = null;
        Udinsproject udinsproject = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udinsproject>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udinsproject = new Udinsproject();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udinsproject.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udinsproject.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udinsproject);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udinsproject.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udinsproject, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udinsproject);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 行驶记录*
     */
    public static ArrayList<Udcardrivelog> parsingUdcardrivelog(Context ctx, String data) {
        ArrayList<Udcardrivelog> list = null;
        Udcardrivelog udcardrivelog = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udcardrivelog>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udcardrivelog = new Udcardrivelog();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udcardrivelog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udcardrivelog.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udcardrivelog);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udcardrivelog.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udcardrivelog, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udcardrivelog);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 加油记录*
     */
    public static ArrayList<Udcarfuelcharge> parsingUdcarfuelcharge(Context ctx, String data) {
        ArrayList<Udcarfuelcharge> list = null;
        Udcarfuelcharge udcarfuelcharge = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udcarfuelcharge>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udcarfuelcharge = new Udcarfuelcharge();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udcarfuelcharge.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udcarfuelcharge.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udcarfuelcharge);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udcarfuelcharge.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udcarfuelcharge, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udcarfuelcharge);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 维修记录*
     */
    public static ArrayList<Udcarmainlog> parsingUdcarmainlog(Context ctx, String data) {
        ArrayList<Udcarmainlog> list = null;
        Udcarmainlog udcarmainlog = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udcarmainlog>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udcarmainlog = new Udcarmainlog();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udcarmainlog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udcarmainlog.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udcarmainlog);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udcarmainlog.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udcarmainlog, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udcarmainlog);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


}