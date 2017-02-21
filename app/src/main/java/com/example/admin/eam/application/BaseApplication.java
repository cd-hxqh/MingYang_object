package com.example.admin.eam.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by think on 2015/8/11.
 */

public class BaseApplication extends Application {

    private String username;
    private static BaseApplication mContext;

    private static Context context;

    private String OrderResult;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderResult() {
        return OrderResult;
    }

    public void setOrderResult(String orderResult) {
        OrderResult = orderResult;
    }

    public static BaseApplication getInstance(){
        return mContext;
    }

//    public AndroidClientService getWsService() {
//        return new AndroidClientService(Constants.getWsUrl(this));
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        context = this.getApplicationContext();
        cn.jpush.android.api.JPushInterface.init(context);
        cn.jpush.android.api.JPushInterface.setDebugMode(true);
    }


    public static Context getContext() {
        return context;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
