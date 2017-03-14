package com.example.admin.eam.application;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
//import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.eam.manager.AppManager;
import com.example.admin.eam.ui.activity.LoginActivity;
import com.example.admin.eam.ui.activity.MainActivity;
import com.example.admin.eam.utils.AccountUtils;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;

/**
 * Created by think on 2015/8/11.
 */

public class BaseApplication extends Application implements LocationListener{

    private String username;

    private static BaseApplication mContext;

    private static Context context;

    private String OrderResult;

    public LocationManager locationManager;

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

    public static BaseApplication getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        context = this.getApplicationContext();
        cn.jpush.android.api.JPushInterface.init(context);
        cn.jpush.android.api.JPushInterface.setDebugMode(true);
        cn.jiguang.analytics.android.api.JAnalyticsInterface.init(this);

        boolean hasPermissionWeNeed=true;
        for (String permissions:new String[]{

                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }) {

            int i = ContextCompat.checkSelfPermission(this, permissions);

            if (i != PackageManager.PERMISSION_GRANTED) {
                hasPermissionWeNeed=false;
            }
        }
        if (hasPermissionWeNeed) {

            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) locationManager
                    .requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                            this);
            else if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) locationManager
                    .requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                            this);
            else{ Toast.makeText(this, "无法定位", Toast.LENGTH_SHORT).show();}
        }
    }


    public static Context getContext() {
        return context;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    @Override
    public void onLocationChanged(Location location)
    {
        LoginEvent lEvent = new LoginEvent();
        lEvent.addKeyValue("经度",""+location.getLongitude());
        lEvent.addKeyValue("纬度",""+location.getLatitude());
        Log.e("定位","经度"+location.getLongitude());
        Log.e("定位","纬度"+location.getLatitude());
        JAnalyticsInterface.onEvent(context, lEvent);
        locationManager.removeUpdates(this);
        locationManager=null;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
