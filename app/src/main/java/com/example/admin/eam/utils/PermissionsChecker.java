package com.example.admin.eam.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Administrator on 2016/10/10.
 */
public class PermissionsChecker {
    private final Context mContext;

    public PermissionsChecker(Context context) {

        mContext = context.getApplicationContext();

    }
    // 判断权限集合

    public boolean lacksPermissions(String... permissions) {

        for (String permission : permissions) {

            Log.e("权限检查","将要判断的权限"+permission);

            if (lacksPermission(permission)==false) {

                Log.e("权限检查","没有的权限"+permission);

                return false;
            }
        }
        Log.e("权限检查","全部权限都有");
        return true;
    } // 判断是否缺少权限

    private boolean lacksPermission(String permission) {

        return (ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED);

    }
}
