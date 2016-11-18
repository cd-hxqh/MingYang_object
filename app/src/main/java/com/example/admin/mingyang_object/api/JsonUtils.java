package com.example.admin.mingyang_object.api;

import android.content.Context;
import android.util.Log;

import com.example.admin.mingyang_object.bean.LoginResults;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.example.admin.mingyang_object.model.Doclinks;
import com.example.admin.mingyang_object.model.Failurelist;
import com.example.admin.mingyang_object.model.GreaseCard;
import com.example.admin.mingyang_object.model.Item;
import com.example.admin.mingyang_object.model.JobPlan;
import com.example.admin.mingyang_object.model.Location;
import com.example.admin.mingyang_object.model.Person;
import com.example.admin.mingyang_object.model.REGULARINSPECTIONPLANLINK;
import com.example.admin.mingyang_object.model.UdPerson;
import com.example.admin.mingyang_object.model.UdTriprePort;
import com.example.admin.mingyang_object.model.Udcardrivelog;
import com.example.admin.mingyang_object.model.Udcarfuelcharge;
import com.example.admin.mingyang_object.model.Udcarmainlog;
import com.example.admin.mingyang_object.model.UddebugWorkOrderLine;
import com.example.admin.mingyang_object.model.Uddept;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Udfeedback;
import com.example.admin.mingyang_object.model.Udinspo;
import com.example.admin.mingyang_object.model.Udinsproject;
import com.example.admin.mingyang_object.model.Udinvestp;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.UdprorunlogLine1;
import com.example.admin.mingyang_object.model.UdprorunlogLine2;
import com.example.admin.mingyang_object.model.UdprorunlogLine3;
import com.example.admin.mingyang_object.model.UdprorunlogLine4;
import com.example.admin.mingyang_object.model.Udqtyform;
import com.example.admin.mingyang_object.model.Udreport;
import com.example.admin.mingyang_object.model.Udrunliner;
import com.example.admin.mingyang_object.model.Udrunlogr;
import com.example.admin.mingyang_object.model.Udstock;
import com.example.admin.mingyang_object.model.Udstockline;
import com.example.admin.mingyang_object.model.Udvehicle;
import com.example.admin.mingyang_object.model.Udwd;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.model.Wfassignment;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
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
     * 解析开始工作流返回信息
     *
     * @param data
     * @return
     */
    public static WebResult parsingStartWF(String data, String num) {
        Log.i(TAG, "data=" + data);
        String woNum = null;
        WebResult webResult = new WebResult();
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("msg") && !object.getString("msg").equals("")) {
                webResult.errorMsg = object.getString("msg");
            }
            if (object.has(num) && !object.getString(num).equals("")) {
                webResult.wonum = object.getString(num);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return webResult;
    }

    /**
     * 解析审批工作流返回数据
     *
     * @param data
     * @return
     */
    public static WebResult parsingGoOn(String data, String num) {
        Log.i(TAG, "data=" + data);
        String woNum = null;
        WebResult webResult = new WebResult();
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("status") && !object.getString("status").equals("")) {
                webResult.errorMsg = object.getString("status");
            } else if (object.has("massage") && !object.getString("massage").equals("")) {
                webResult.errorMsg = object.getString("massage");
            } else if (object.has("errorMsg") && !object.getString("errorMsg").equals("")) {
                webResult.errorMsg = object.getString("errorMsg");
            }
            if (object.has(num) && !object.getString(num).equals("")) {
                webResult.wonum = object.getString(num);
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

    public static ArrayList<Udvehicle> parsingUdvehicle(String data) {
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
     * 解析部门信息*
     */

    public static ArrayList<Uddept> parsingUddept(String data) {
        ArrayList<Uddept> list = null;
        Uddept uddept = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Uddept>();
            for (int i = 0; i < jsonArray.length(); i++) {
                uddept = new Uddept();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = uddept.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = uddept.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(uddept);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = uddept.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(uddept, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(uddept);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析行驶记录业务单号信息*
     */

    public static ArrayList<Udwd> parsingUdwd(String data) {
        ArrayList<Udwd> list = null;
        Udwd udwd = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udwd>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udwd = new Udwd();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udwd.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udwd.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udwd);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udwd.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udwd, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udwd);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析定检计划信息*
     */

    public static ArrayList<REGULARINSPECTIONPLANLINK> parsingRegularinspectionplanlink(String data) {
        ArrayList<REGULARINSPECTIONPLANLINK> list = null;
        REGULARINSPECTIONPLANLINK regularinspectionplanlink = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<REGULARINSPECTIONPLANLINK>();
            for (int i = 0; i < jsonArray.length(); i++) {
                regularinspectionplanlink = new REGULARINSPECTIONPLANLINK();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = regularinspectionplanlink.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = regularinspectionplanlink.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(regularinspectionplanlink);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = regularinspectionplanlink.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(regularinspectionplanlink, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(regularinspectionplanlink);
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
        Log.i("项目日报", "data=" + data);
        ArrayList<Udprorunlog> list = null;
        Udprorunlog udprorunlog = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udprorunlog>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udprorunlog = new Udprorunlog();
                jsonObject = jsonArray.getJSONObject(i);
                Log.e("项目日报", "项目日报列表" + jsonObject);

                Field[] field = udprorunlog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null) {
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
                    if (jsonObject.has(name) && jsonObject.getString(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udfeedback.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udfeedback);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udfeedback.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udfeedback, jsonObject.get(name));
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
                    if (jsonObject.has(name) && jsonObject.get(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udstockline.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udstockline);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udstockline.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udstockline, jsonObject.get(name));
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

                Log.e("调试工单", "数据" + jsonObject);

                Field[] field = workOrder.getClass().getDeclaredFields();//获取实体类的所有属性，返回Field数组

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
     * 解析调试工单信息
     */
    public static ArrayList<UdTriprePort> parsingTripReport(Context ctx, String data) {

        ArrayList<UdTriprePort> list = null;

        UdTriprePort udTriprePort = null;

        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UdTriprePort>();

            for (int i = 0; i < jsonArray.length(); i++) {

                udTriprePort = new UdTriprePort();
                jsonObject = jsonArray.getJSONObject(i);

                Log.e("出差", "JSON数据" + jsonObject);

                Field[] field = udTriprePort.getClass().getDeclaredFields();//获取实体类的所有属性，返回Field数组

                for (int j = 0; j < field.length; j++) {     //遍历所有属性

                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字

                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udTriprePort.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udTriprePort);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udTriprePort.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udTriprePort, jsonObject.getString(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(udTriprePort);
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


        ArrayList<UddebugWorkOrderLine> list = null;
        UddebugWorkOrderLine uddebugWorkOrderLine = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UddebugWorkOrderLine>();
            for (int i = 0; i < jsonArray.length(); i++) {
                uddebugWorkOrderLine = new UddebugWorkOrderLine();
                jsonObject = jsonArray.getJSONObject(i);

                Log.e("调试工单", "子表数据" + jsonObject);

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
                Field[] field = woactivity.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null) {
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
                woactivity.WONUM = wonum == null ? "" : wonum;
                woactivity.TYPE = "";
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
     * 解析土建阶段子表信息
     */
    public static ArrayList<UdprorunlogLine1> parsingUdprorunlogLine1(Context ctx, String data, String prorunlognum) {
        Log.i(TAG, "UdprorunlogLine1 data=" + data);
        ArrayList<UdprorunlogLine1> list = null;
        UdprorunlogLine1 udprorunlogLine1 = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UdprorunlogLine1>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udprorunlogLine1 = new UdprorunlogLine1();
                jsonObject = jsonArray.getJSONObject(i);
                Log.e("项目日报", "土建阶段子表" + jsonObject);
                Field[] field = udprorunlogLine1.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.get(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udprorunlogLine1.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udprorunlogLine1);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udprorunlogLine1.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udprorunlogLine1, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                udprorunlogLine1.PRORUNLOGNUM = prorunlognum;
                udprorunlogLine1.isUpload = true;
                list.add(udprorunlogLine1);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析吊装调试子表信息
     */
    public static ArrayList<UdprorunlogLine2> parsingUdprorunlogLine2(Context ctx, String data, String prorunlognum) {

        ArrayList<UdprorunlogLine2> list = null;
        UdprorunlogLine2 udprorunlogLine2 = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UdprorunlogLine2>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udprorunlogLine2 = new UdprorunlogLine2();
                jsonObject = jsonArray.getJSONObject(i);
                Log.e("项目日报", "吊装调试子表" + jsonObject + "\n");
                Field[] field = udprorunlogLine2.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.get(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udprorunlogLine2.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udprorunlogLine2);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udprorunlogLine2.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udprorunlogLine2, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                udprorunlogLine2.PRORUNLOGNUM = prorunlognum;
                udprorunlogLine2.isUpload = true;
                list.add(udprorunlogLine2);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 工作日报子表信息
     */
    public static ArrayList<UdprorunlogLine3> parsingUdprorunlogLine3(Context ctx, String data, String prorunlognum) {
        Log.i(TAG, "UdprorunlogLine3 data=" + data);
        ArrayList<UdprorunlogLine3> list = null;
        UdprorunlogLine3 udprorunlogLine3 = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UdprorunlogLine3>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udprorunlogLine3 = new UdprorunlogLine3();
                jsonObject = jsonArray.getJSONObject(i);
                Log.e("项目日报", "工作日报子表" + jsonObject);
                Field[] field = udprorunlogLine3.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.get(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udprorunlogLine3.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udprorunlogLine3);
                            if (value == null || name.equals("UDPRORUNLOGCID") || name.equals("TEM") || name.equals("WINDSPEED")) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udprorunlogLine3.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udprorunlogLine3, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                udprorunlogLine3.PRORUNLOGNUM = prorunlognum;
                udprorunlogLine3.isUpload = true;
                list.add(udprorunlogLine3);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析工装管理子表信息
     */
    public static ArrayList<UdprorunlogLine4> parsingUdprorunlogLine4(Context ctx, String data, String prorunlognum) {
        Log.i(TAG, "UdprorunlogLine1 data=" + data);
        ArrayList<UdprorunlogLine4> list = null;
        UdprorunlogLine4 udprorunlogLine4 = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<UdprorunlogLine4>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udprorunlogLine4 = new UdprorunlogLine4();
                jsonObject = jsonArray.getJSONObject(i);
                Log.e("项目日报", "工装管理子表" + jsonObject);
                Field[] field = udprorunlogLine4.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.get(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udprorunlogLine4.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udprorunlogLine4);
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udprorunlogLine4.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udprorunlogLine4, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                udprorunlogLine4.PRORUNLOGNUM = prorunlognum;
                udprorunlogLine4.isUpload = true;
                list.add(udprorunlogLine4);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 工作日报子表信息
     */
    public static ArrayList<Udrunliner> parsingUdrunliner(Context ctx, String data, String prorunlognum) {
        Log.i(TAG, "Udrunliner data=" + data);
        ArrayList<Udrunliner> list = null;
        Udrunliner udrunliner = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udrunliner>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udrunliner = new Udrunliner();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udrunliner.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.get(name) != null) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udrunliner.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udrunliner);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udrunliner.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udrunliner, jsonObject.get(name));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
//                udprorunlogLine3.PRORUNLOGNUM = prorunlognum;
                udrunliner.isUpload = true;
                list.add(udrunliner);
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
                    if (!name.equals("isnew") && !name.equals("WORKORDERID") && !name.equals("id") && !name.equals("isUpdate")) {
                        getOrSet = workOrder.getClass().getMethod("get" + name);
                        Object value = null;
                        value = getOrSet.invoke(workOrder);
                        if (value != null) {
                            jsonObject.put(name, value + "");
                        }
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
                            if (!name.equals("belongid") && !name.equals("id") && !name.equals("isUpload")) {
                                getOrSet = woactivities.get(i).getClass().getMethod("get" + name);
                                Object value = null;
                                value = getOrSet.invoke(woactivities.get(i));
                                if (value != null) {
                                    woactivityObj.put(name, value + "");
                                }
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    if (woactivityObj.get("TYPE").equals("add") && woactivityObj.has("WORKORDERID") && woactivityObj.get("WORKORDERID").equals("0")) {
                        woactivityObj.remove("WORKORDERID");
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
                            if (!name.equals("isUpload") && name.equals("WPITEMID") || name.equals("ITEMNUM") || name.equals("ITEMQTY") || name.equals("LOCATION") || name.equals("TYPE")) {
                                getOrSet = wpmaterials.get(i).getClass().getMethod("get" + name);
                                Object value = null;
                                value = getOrSet.invoke(wpmaterials.get(i));
                                if (value != null) {
                                    wpmaterialsObj.put(name, value + "");
                                }
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    if (wpmaterialsObj.get("TYPE").equals("add") && wpmaterialsObj.has("WPITEMID") && wpmaterialsObj.get("WPITEMID").equals("0")) {
                        wpmaterialsObj.remove("WPITEMID");
                    }
                    wpmaterialsArray.put(wpmaterialsObj);
                }
                jsonObject.put("WPMATERIAL", wpmaterialsArray);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("封装工单", jsonObject.toString());
        return jsonObject.toString();
    }

    /**
     * 封装工单数据
     *
     * @param workOrder
     * @return
     */
    public static String WorkToJson(WorkOrder workOrder) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = workOrder.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    if (!name.equals("isnew") && !name.equals("WORKORDERID")) {
                        getOrSet = workOrder.getClass().getMethod("get" + name);
                        Object value = null;
                        value = getOrSet.invoke(workOrder);
                        if (value != null) {
                            jsonObject.put(name, value + "");
                        }
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
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("封装工单", jsonObject.toString());
        return jsonObject.toString();
    }

    /**
     * 封装调试工单数据
     *
     * @param workOrder
     * @param woactivities
     * @return
     */
    public static String DebugWorkToJson(DebugWorkOrder workOrder, ArrayList<UddebugWorkOrderLine> woactivities) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = workOrder.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    if (!name.equals("isnew")) {
                        getOrSet = workOrder.getClass().getMethod("get" + name);
                        Object value = null;
                        value = getOrSet.invoke(workOrder);
                        if (value != null) {
                            jsonObject.put(name, value + "");
                        }
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
                object.put("UDDEBUGWORKORDERLINE", "");
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
                jsonObject.put("UDDEBUGWORKORDERLINE", woactivityArray);
            }
//            if (wpmaterials != null && wpmaterials.size() != 0) {
//                object.put("WPMATERIAL", "");
//                JSONArray wpmaterialsArray = new JSONArray();
//                JSONObject wpmaterialsObj;
//                for (int i = 0; i < wpmaterials.size(); i++) {
//                    wpmaterialsObj = new JSONObject();
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
//                                wpmaterialsObj.put(name, value + "");
//                            }
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    wpmaterialsArray.put(wpmaterialsObj);
//                }
//                jsonObject.put("WPMATERIAL", wpmaterialsArray);
//            }

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 封装项目日报数据
     *
     * @return
     */
    public static String UdprorunlogToJson(Udprorunlog udprorunlog, ArrayList<UdprorunlogLine1> udprorunlogLine1s
            , ArrayList<UdprorunlogLine2> udprorunlogLine2s, ArrayList<UdprorunlogLine3> udprorunlogLine3s,
                                           ArrayList<UdprorunlogLine4> udprorunlogLine4s) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udprorunlog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    if (!name.equals("isnew")) {
                        getOrSet = udprorunlog.getClass().getMethod("get" + name);
                        Object value = null;
                        value = getOrSet.invoke(udprorunlog);
                        if (value != null && !name.equals("BRANCH")) {
                            jsonObject.put(name, value + "");
                        }
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
            if (udprorunlogLine1s != null && udprorunlogLine1s.size() != 0) {
                object.put("UDPRORUNLOGLINE1", "");
                JSONArray udprorunlogline1Array = new JSONArray();
                JSONObject udprorunlogline1Obj;
                for (int i = 0; i < udprorunlogLine1s.size(); i++) {
                    udprorunlogline1Obj = new JSONObject();
                    Field[] field1 = udprorunlogLine1s.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = udprorunlogLine1s.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(udprorunlogLine1s.get(i));
                            if (value != null) {
                                udprorunlogline1Obj.put(name, value + "");
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    if (udprorunlogline1Obj.get("TYPE").equals("add") && udprorunlogline1Obj.has("UDPRORUNLOGLINE1ID") && udprorunlogline1Obj.get("UDPRORUNLOGLINE1ID").equals("0")) {
                        udprorunlogline1Obj.remove("UDPRORUNLOGLINE1ID");
                    }
                    udprorunlogline1Array.put(udprorunlogline1Obj);
                }
                jsonObject.put("UDPRORUNLOGLINE1", udprorunlogline1Array);
            }
            if (udprorunlogLine2s != null && udprorunlogLine2s.size() != 0) {
                object.put("UDPRORUNLOGLINE2", "");
                JSONArray udprorunlogline2Array = new JSONArray();
                JSONObject udprorunlogline2Obj;
                for (int i = 0; i < udprorunlogLine2s.size(); i++) {
                    udprorunlogline2Obj = new JSONObject();
                    Field[] field1 = udprorunlogLine2s.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            if (!name.equals("isUpload")) {
                                getOrSet = udprorunlogLine2s.get(i).getClass().getMethod("get" + name);
                                Object value = null;
                                value = getOrSet.invoke(udprorunlogLine2s.get(i));
                                if (value != null) {
                                    udprorunlogline2Obj.put(name, value + "");
                                }
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    if (udprorunlogline2Obj.get("TYPE").equals("add") && udprorunlogline2Obj.has("UDPRORUNLOGLINE2DZID") && udprorunlogline2Obj.get("UDPRORUNLOGLINE2DZID").equals("0")) {
                        udprorunlogline2Obj.remove("UDPRORUNLOGLINE2DZID");
                    }
                    udprorunlogline2Array.put(udprorunlogline2Obj);
                }
                jsonObject.put("UDPRORUNLOGLINE2", udprorunlogline2Array);
            }
            if (udprorunlogLine3s != null && udprorunlogLine3s.size() != 0) {
                object.put("UDPRORUNLOGC", "");
                JSONArray udprorunlogline3Array = new JSONArray();
                JSONObject udprorunlogline3Obj;
                for (int i = 0; i < udprorunlogLine3s.size(); i++) {
                    udprorunlogline3Obj = new JSONObject();
                    Field[] field1 = udprorunlogLine3s.get(i).getClass().getDeclaredFields();//获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = udprorunlogLine3s.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(udprorunlogLine3s.get(i));
                            if (value != null) {
                                udprorunlogline3Obj.put(name, value + "");
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    if (udprorunlogline3Obj.get("TYPE").equals("add") && udprorunlogline3Obj.has("UDPRORUNLOGCID") && udprorunlogline3Obj.get("UDPRORUNLOGCID").equals("0")) {
                        udprorunlogline3Obj.remove("UDPRORUNLOGCID");
                    }
                    udprorunlogline3Array.put(udprorunlogline3Obj);
                }
                jsonObject.put("UDPRORUNLOGC", udprorunlogline3Array);
            }
            if (udprorunlogLine4s != null && udprorunlogLine4s.size() != 0) {
                object.put("UDPRORUNLOGLINE4", "");
                JSONArray udprorunlogLine4Array = new JSONArray();
                JSONObject udprorunlogLine4Obj;
                for (int i = 0; i < udprorunlogLine4s.size(); i++) {
                    udprorunlogLine4Obj = new JSONObject();
                    Field[] field1 = udprorunlogLine4s.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = udprorunlogLine4s.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(udprorunlogLine4s.get(i));
                            if (value != null) {
                                udprorunlogLine4Obj.put(name, value + "");
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    if (udprorunlogLine4Obj.get("TYPE").equals("add") && udprorunlogLine4Obj.has("UDPRORUNLOGLINE4ID") && udprorunlogLine4Obj.get("UDPRORUNLOGLINE4ID").equals("0")) {
                        udprorunlogLine4Obj.remove("UDPRORUNLOGLINE4ID");
                    }
                    udprorunlogLine4Array.put(udprorunlogLine4Obj);
                }
                jsonObject.put("UDPRORUNLOGLINE4", udprorunlogLine4Array);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("项目日报",jsonObject.toString());
        return jsonObject.toString();
    }

    /**
     * 封装问题联络单数据
     *
     * @return
     */
    public static String UdfeedbackToJson(Udfeedback udfeedback) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udfeedback.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = udfeedback.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(udfeedback);
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
            object.put("", "");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 封装出差报告数据
     *
     * @return
     */
    public static String tripPortToJson(UdTriprePort udTriprePort) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udTriprePort.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = udTriprePort.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(udTriprePort);
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
            object.put("", "");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 封装故障提报单数据
     *
     * @return
     */
    public static String UdreportToJson(Udreport udreport) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udreport.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = udreport.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(udreport);
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
            object.put("", "");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 封装质量问题反馈单数据
     *
     * @return
     */
    public static String UdqtyformToJson(Udqtyform udqtyform) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udqtyform.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    if (!name.equals("isnew")) {
                        getOrSet = udqtyform.getClass().getMethod("get" + name);
                        Object value = null;
                        value = getOrSet.invoke(udqtyform);
                        if (value != null) {
                            jsonObject.put(name, value + "");
                        }
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
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("封装质量问题反馈单", jsonObject.toString());
        return jsonObject.toString();
    }

    /**
     * 封装巡检单数据
     *
     * @return
     */
    public static String UdinspoToJson(Udinspo udinspo, ArrayList<Udinsproject> udinsprojects) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udinspo.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    if (!name.equals("id") && !name.equals("isUpdate") && !name.equals("belong")) {
                        getOrSet = udinspo.getClass().getMethod("get" + name);
                        Object value = null;
                        value = getOrSet.invoke(udinspo);
                        if (value != null) {
                            jsonObject.put(name, value + "");
                        }
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
            if (udinsprojects != null && udinsprojects.size() != 0) {
                object.put("UDSTOCKLINE", "");
                JSONArray udinsprojectArray = new JSONArray();
                JSONObject udinsprojectObj;
                for (int i = 0; i < udinsprojects.size(); i++) {
                    udinsprojectObj = new JSONObject();
                    Field[] field1 = udinsprojects.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = udinsprojects.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(udinsprojects.get(i));
                            if (value != null) {
                                udinsprojectObj.put(name, value + "");
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    if (udinsprojectObj.get("TYPE").equals("add") && udinsprojectObj.has("UDPRORUNLOGLINE4ID") && udinsprojectObj.get("UDPRORUNLOGLINE4ID").equals("0")) {
                        udinsprojectObj.remove("UDPRORUNLOGLINE4ID");
                    }
                    udinsprojectArray.put(udinsprojectObj);
                }
                jsonObject.put("UDINSPROJECT", udinsprojectArray);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 封装运行记录数据
     *
     * @return
     */
    public static String UdrunlogrToJson(Udrunlogr udprorunlog, ArrayList<Udrunliner> udprorunlogLine1s) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udprorunlog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    if (!name.equals("isnew")) {
                        getOrSet = udprorunlog.getClass().getMethod("get" + name);
                        Object value = null;
                        value = getOrSet.invoke(udprorunlog);
                        if (value != null && !name.equals("BRANCH")) {
                            jsonObject.put(name, value + "");
                        }
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
            if (udprorunlogLine1s != null && udprorunlogLine1s.size() != 0) {
                object.put("UDRUNLINER", "");
                JSONArray udprorunlogline1Array = new JSONArray();
                JSONObject udprorunlogline1Obj;
                for (int i = 0; i < udprorunlogLine1s.size(); i++) {
                    udprorunlogline1Obj = new JSONObject();
                    Field[] field1 = udprorunlogLine1s.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            if (!name.equals("isUpload")) {
                                getOrSet = udprorunlogLine1s.get(i).getClass().getMethod("get" + name);
                                Object value = null;
                                value = getOrSet.invoke(udprorunlogLine1s.get(i));
                                if (value != null) {
                                    udprorunlogline1Obj.put(name, value + "");
                                }
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    udprorunlogline1Obj.put("FORAPP", "1");
                    udprorunlogline1Obj.put("DESCRIPTION", udprorunlog.LOGNUM);
                    if (udprorunlogline1Obj.get("TYPE").equals("add") && udprorunlogline1Obj.has("UDRUNLINERID") && udprorunlogline1Obj.get("UDRUNLINERID").equals("0")) {
                        udprorunlogline1Obj.remove("UDRUNLINERID");
                    }
                    udprorunlogline1Array.put(udprorunlogline1Obj);
                }
                jsonObject.put("UDRUNLINER", udprorunlogline1Array);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * 封装库存盘点数据
     *
     * @return
     */
    public static String UdstockToJson(Udstock udstock, ArrayList<Udstockline> udstocklines) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udstock.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = udstock.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(udstock);
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
            if (udstocklines != null && udstocklines.size() != 0) {
                object.put("UDSTOCKLINE", "");
                JSONArray wpmaterialsArray = new JSONArray();
                JSONObject wpmaterialsObj;
                for (int i = 0; i < udstocklines.size(); i++) {
                    wpmaterialsObj = new JSONObject();
                    Field[] field1 = udstocklines.get(i).getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                    for (int j = 0; j < field1.length; j++) {
                        field1[j].setAccessible(true);
                        String name = field1[j].getName();//获取属性的名字
                        Method getOrSet = null;
                        try {
                            getOrSet = udstocklines.get(i).getClass().getMethod("get" + name);
                            Object value = null;
                            value = getOrSet.invoke(udstocklines.get(i));
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
                    if (wpmaterialsObj.get("TYPE").equals("add") && wpmaterialsObj.has("UDSTOCKLINEID") && wpmaterialsObj.get("UDSTOCKLINEID").equals("0")) {
                        wpmaterialsObj.remove("UDSTOCKLINEID");
                    }
                    wpmaterialsArray.put(wpmaterialsObj);
                }
                jsonObject.put("UDSTOCKLINE", wpmaterialsArray);
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 封装车辆维修数据
     *
     * @return
     */
    public static String udcarmainlogToJson(Udcarmainlog udcarmainlog) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udcarmainlog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = udcarmainlog.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(udcarmainlog);
                    if (value != null && !name.equals("BRANCH")) {
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
            object.put("", "");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
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
                            if (value == null || Integer.parseInt(String.valueOf(value)) == 0) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udreport.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udreport, jsonObject.get(name));
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
                    if (jsonObject.has(name) && jsonObject.getString(name) != null) {
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
     * 巡检单*
     */
    public static ArrayList<Udrunlogr> parsingUdrunlogr(Context ctx, String data) {
        ArrayList<Udrunlogr> list = null;
        Udrunlogr udrunlogr = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Udrunlogr>();
            for (int i = 0; i < jsonArray.length(); i++) {
                udrunlogr = new Udrunlogr();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = udrunlogr.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = udrunlogr.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(udrunlogr);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = udrunlogr.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(udrunlogr, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(udrunlogr);
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
                Log.e("加油卡台账", "data=" + jsonObject);
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
     * 解析加油卡台账
     */
    public static ArrayList<GreaseCard> parsingGreaseCard(Context ctx, String data) {
        ArrayList<GreaseCard> list = null;
        GreaseCard greaseCard = null;
        try {
            JSONObject jsonObjectData = new JSONObject(data);

            JSONArray jsonArray = new JSONArray(jsonObjectData.getString("resultlist"));
            JSONObject jsonObject;
            list = new ArrayList<GreaseCard>();
            for (int i = 0; i < jsonArray.length(); i++) {
                greaseCard = new GreaseCard();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = greaseCard.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = greaseCard.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(greaseCard);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = greaseCard.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(greaseCard, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(greaseCard);
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


    /**
     * 封装车辆加油数据
     *
     * @return
     */
    public static String udcarfuelchargeToJson(Udcarfuelcharge udcarfuelcharge) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udcarfuelcharge.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = udcarfuelcharge.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(udcarfuelcharge);
                    if (value != null && !name.equals("BRANCH")) {
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
            object.put("", "");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 封装车辆行驶数据
     *
     * @return
     */
    public static String udcardrivelogToJson(Udcardrivelog udcardrivelog) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            Field[] field = udcardrivelog.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                String name = field[j].getName();//获取属性的名字
                Method getOrSet = null;
                try {
                    getOrSet = udcardrivelog.getClass().getMethod("get" + name);
                    Object value = null;
                    value = getOrSet.invoke(udcardrivelog);
                    if (value != null && !name.equals("BRANCH")) {
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
            object.put("", "");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object);
            jsonObject.put("relationShip", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * 附件类*
     */
    public static ArrayList<Doclinks> parsingDoclinks(Context ctx, String data) {
        Log.i(TAG, "ddddata=" + data);
        ArrayList<Doclinks> list = null;
        Doclinks doclinks = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Doclinks>();
            for (int i = 0; i < jsonArray.length(); i++) {
                doclinks = new Doclinks();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = doclinks.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    Log.i(TAG, "name=" + name);
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = doclinks.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(doclinks);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = doclinks.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(doclinks, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(doclinks);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


}