package com.example.admin.mingyang_object.webserviceclient;

import android.content.Context;
import android.util.Log;

import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.AccountUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by think on 2015/8/11.
 */
public class AndroidClientService {
    private static final String TAG = "AndroidClientService";
    public static String NAMESPACE = "http://www.ibm.com/maximo";
    public static String url = "";
    public static int timeOut = 60000;

    public AndroidClientService() {
    }

    public AndroidClientService(String url) {
        this.url = url;
    }

    public void setTimeOut(int seconds) {
        this.timeOut = seconds * 1000;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 开始工作流
     * context 上下文
     * processname 过程名
     * keyValue 对应的
     *
     * @return
     */
    public static WebResult startwf(Context context, String processname, String mbo, String keyValue, String key) {
        String url = AccountUtils.getIpAddress(context) + Constants.WORK_FLOW_URL;
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "wfservicestartWF");
        soapReq.addProperty("processname", processname);//流程名称
        soapReq.addProperty("mbo", mbo);//如工单WORKORDER
        soapReq.addProperty("keyValue", keyValue);//对应的表ID的值，如工单需要传送WONUM的值，采购申请prnum的值
        soapReq.addProperty("key", key);//对应的表ID，如工单：wonum，采购申请，prnum
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
        String obj = null;
        WebResult result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = JsonUtils.parsingStartWF(obj, key);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return result;
    }

    /**
     * 审批工作流
     *
     * @return
     */
    public static WebResult approve(Context context, String processname, String mbo, String keyValue, String key, String zx, String desc) {
        String url = AccountUtils.getIpAddress(context) + Constants.WORK_FLOW_URL;
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "wfservicewfGoOn");
        soapReq.addProperty("processname", processname);//流程名称
        soapReq.addProperty("mboName", mbo);//工单WORKORDER
        soapReq.addProperty("keyValue", keyValue);//对应的表ID的值，如工单需要传送wonum的值
        soapReq.addProperty("key", key);//如工单：wonum
        soapReq.addProperty("zx", zx);//审批的结果，1为审批通过，0为审批不通过
        if (!desc.equals("")) {
            soapReq.addProperty("desc", desc);//审批意见
        }
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            return null;
        } catch (XmlPullParserException e) {
            return null;
        }
        String obj = null;
        WebResult result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = JsonUtils.parsingGoOn(obj, key);
        } catch (SoapFault soapFault) {
            Log.i(TAG, "ssssss");
            return null;
        }
        return result;
    }

    /**
     * 工单新增方法
     *
     * @param json
     * @return
     */
    public static WebResult InsertWO(Context context, String json, String mboObjectName, String mboKey, String personId, String url) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceInsertMbo");
        soapReq.addProperty("json", json);
        soapReq.addProperty("flag", 1);
        soapReq.addProperty("mboObjectName", mboObjectName);
        soapReq.addProperty("mboKey", mboKey);
        soapReq.addProperty("personId", personId);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
//            e.printStackTrace();
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();

            Log.i(TAG, "obj=" + obj);
            webResult = JsonUtils.parsingInsertWO(obj, mboKey);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

    /**
     * 通用修改
     */
    public static WebResult UpdateWO(Context context, String json, String mboObjectName, String mboKey, String mboKeyValue, String url) {
//        http://192.168.100.17:7001/meaweb/services/MOBILESERVICE
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceUpdateMbo");
        soapReq.addProperty("json", json);//参数
        soapReq.addProperty("mboObjectName", mboObjectName);//表名
        soapReq.addProperty("mboKey", mboKey);//表主键 如：WONUM
        soapReq.addProperty("mboKeyValue", mboKeyValue);//表主键值
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
        String obj = null;
        WebResult webResult = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            webResult = JsonUtils.parsingInsertWO(obj, mboKey);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }

//    /**
//     * 工单删除
//     */
//    public static WebResult DeleteWO(String wonum, String userid, String url) {
//        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        soapEnvelope.implicitTypes = true;
//        soapEnvelope.dotNet = true;
//        SoapObject soapReq = new SoapObject(NAMESPACE, "workorderserviceWO03ByDelete");
//        soapReq.addProperty("wonum", wonum);
//        soapReq.addProperty("userid",userid);
//        soapEnvelope.setOutputSoapObject(soapReq);
//        HttpTransportSE httpTransport = new HttpTransportSE(url);
//        try {
//            httpTransport.call("urn:action", soapEnvelope);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            return null;
//        }
//        String obj = null;
//        WebResult workResult = null;
//        try {
//            obj = soapEnvelope.getResponse().toString();
//            workResult = JsonUtils.parsinDeleteWo(obj);
//        } catch (SoapFault soapFault) {
//            soapFault.printStackTrace();
//        }
//        return workResult;
//    }


    /**
     * 通过webservice实现图片上传
     *
     * @param imageBuffer
     */
    /**
     * 通用修改
     */
    public static String connectWebService(Context context, String filename, String image, String ownertable, String ownerid, String url) {
        Log.i(TAG, "filename=" + filename + ",ownertable=" + ownertable + ",ownerid=" + ownerid);
        Log.i(TAG, "url=" + url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "mobileserviceuploadImage");
        soapReq.addProperty("filename", filename);//文件名
        soapReq.addProperty("image", image);//图片Json
        soapReq.addProperty("ownertable", ownertable);//表名
        soapReq.addProperty("ownerid", ownerid);//表主键值
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(AccountUtils.getIpAddress(context) + url, timeOut);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
        String obj = null;
        String webResult = null;
        try {
            webResult = soapEnvelope.getResponse().toString();
            Log.i(TAG, "webResult=" + webResult);
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return webResult;
    }


}
