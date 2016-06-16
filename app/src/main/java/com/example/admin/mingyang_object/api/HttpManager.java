package com.example.admin.mingyang_object.api;


import android.content.Context;
import android.util.Log;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.application.BaseApplication;
import com.example.admin.mingyang_object.bean.LoginResults;
import com.example.admin.mingyang_object.bean.Results;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;


/**
 * Created by apple on 15/5/27.
 */
public class HttpManager {

    private static BaseApplication mApp = BaseApplication.getInstance();
    private static AsyncHttpClient sClient = null;
    private static final String TAG = "HttpManager";

    /**
     * 设置待办事项接口*
     */
    public static String getwfassignmentUrl(String persionid, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'ASSIGNCODE':'" + persionid + "','ASSIGNSTATUS':'=活动'}}";
        } else {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'DESCRIPTION':'" + vlaue + "','ASSIGNCODE':'" + persionid + "','ASSIGNSTATUS':'＝活动'}}";
        }
    }


    /**
     * 设置工单接口*
     */
    public static String getworkorderUrl(String type, String search, String siteid, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WORKTYPE':'" + type + "','SITEID':'" + siteid + "'}}";
        } else {
            return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'%" + search + "%','WORKTYPE':'" + type + "','SITEID':'" + siteid + "'}}";
        }
    }

    /**
     * 设置选择工单接口*
     */
    public static String getChooseWorkOrderUrl(String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WORKTYPE':'!=OSPR'}}";
        } else {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'%" + search + "%','WORKTYPE':'!=OSPR'}}";
        }
    }

    /**
     * 设置计划任务接口*
     */
    public static String getwoactivityUrl(String type, String wonum, String sitesite, int curpage, int showcount) {
        return "{'appid':'" + "UDWOALL','objectname':'" + Constants.WOACTIVITY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'condition':{'parent':'" + wonum + "','siteid':'" + sitesite + "'}" +
                "}";
    }

    /**
     * 设置计划员工接口*
     */
    public static String getwplaborUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WPLABOR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置计划物料接口*
     */
    public static String getwpitemUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WPITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置任务分配接口*
     */
    public static String getassignmentUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.ASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置实际员工接口
     */
    public static String getlabtransUrl(String type, String wonum, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.LABTRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'REFWO':'" + wonum + "'}}";
    }

    /**
     * 设置实际物料接口
     */
    public static String getmatusetransUrl(String type, int curpage, int showcount, String wonum) {
        return "{'appid':'" + Constants.UDPRO_APPID + "','objectname':'" + Constants.MATUSETRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置故障汇报接口
     */
    public static String getfailurereportUrl(String type, String wonum) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.FAILUREREPORT_NAME + "','option':'read','condition':{'WONUM':'" + wonum + "'}}";
    }

    /**
     * 设置库存查询的接口
     */
    public static String getInventorurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVENTOR_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.INVENTOR_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + value + "'}}";
    }



    /**
     * 设置工程台账的接口
     */
    public static String getUdprourl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDPRO_APPID + "','objectname':'" + Constants.UDPRO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.UDPRO_APPID + "','objectname':'" + Constants.UDPRO_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PRONUM':'" + value + "'}}";
    }




    /**
     * 使用用户名密码登录
     *
     * @param cxt
     * @param username 用户名
     * @param password 密码
     * @param imei     密码
     * @param handler  返回结果处理
     */
    public static void loginWithUsername(final Context cxt, final String username, final String password, String imei,
                                         final HttpRequestHandler<String> handler) {

        String ip_adress = AccountUtils.getIpAddress(cxt)+Constants.SIGN_IN_URL;
        Log.i(TAG, "ip_adress=" + ip_adress);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.setTimeout(20000);
        client.post(ip_adress, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "SstatusCode=" + statusCode + "responseString=" + responseString);
                if (statusCode == 200) {
                    LoginResults loginResults = JsonUtils.parsingAuthStr(cxt, responseString);
                    if (loginResults != null) {
                        if (loginResults.getErrcode().equals(Constants.LOGINSUCCESS) || loginResults.getErrcode().equals(Constants.CHANGEIMEI)) {
                            SafeHandler.onSuccess(handler, loginResults.getResult());
                        } else if (loginResults.getErrcode().equals(Constants.USERNAMEERROR)) {
                            SafeHandler.onFailure(handler, loginResults.getErrmsg());
                        }
                    }

                }
            }
        });


    }


    /**
     * 不分页获取信息方法*
     */
    public static void getData(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        String url=AccountUtils.getIpAddress(cxt)+Constants.BASE_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(20000);
        client.get(url, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Results result = JsonUtils.parsingResults1(cxt, responseString);

                SafeHandler.onSuccess(handler, result);

            }
        });
    }


    /**
     * 解析返回的结果--分页*
     */
    public static void getDataPagingInfo(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        Log.i(TAG, "data=" + data);
        String url=AccountUtils.getIpAddress(cxt)+Constants.BASE_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(20000);
        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "statusCode" + "responseString=" + responseString);
                Results result = JsonUtils.parsingResults(cxt, responseString);

                SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
            }
        });
    }


}
