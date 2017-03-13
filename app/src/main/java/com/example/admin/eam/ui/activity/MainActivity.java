package com.example.admin.eam.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.eam.R;
import com.example.admin.eam.manager.AppManager;
import com.example.admin.eam.ui.fragment.FuntionFragment;
import com.example.admin.eam.ui.fragment.MyselfFragment;
import com.example.admin.eam.ui.fragment.TabbarFragment;
import com.example.admin.eam.ui.fragment.WfmentFragment;
import com.example.admin.eam.utils.AccountUtils;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;


public class MainActivity extends BaseActivity {

    /**
     * 功能中心\流程审批\个人设置*
     */
    FuntionFragment funtionFragment;
    WfmentFragment wfmentFragment;
    MyselfFragment myselfFragment;
    TabbarFragment tabbarFragment;
    private boolean hasPermissionWeNeed = false;
    private static final int REQUEST_CODE = 0; // 请求码

    static final String[] PERMISSIONS = new String[]{

            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            for (String permissions:PERMISSIONS) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 如果没有授予该权限，就去提示用户请求
                    showDialogTipUserRequestPermission();
                }
            }
        }

        findViewById();
    }
          // 提示用户该请求权限的弹出框
    private void showDialogTipUserRequestPermission() {
        new AlertDialog.Builder(this)
                         .setTitle("权限不可用")
                         .setMessage("由于EAM需要获取某些权限；\n请允许，否则，您将无法正常使用EAM")
                         .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                             @Override
                      public void onClick(DialogInterface dialog, int which) {
                                         startRequestPermission();
                                     }
                  }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                                         finish();
                                     }
                  }).setCancelable(false).show();
             }
              // 开始提交请求权限
              private void startRequestPermission() {
                 ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE);
             }

              // 用户权限 申请 的回调方法
              @Override
      public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {

                 super.onRequestPermissionsResult(requestCode,permissions,grantResults);

                 if (requestCode == REQUEST_CODE) {

                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                 if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                                         // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                                         boolean b = shouldShowRequestPermissionRationale(permissions[0]);

                                         if (!b)
                                         {
                                             showDialogTipUserGoToAppSettting();
                                         }
                                         else
                                         {
                                             finish();
                                         }

                                 } else {

                                         Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();

                                 }
                             }
                     }
             }
         @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

             super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == REQUEST_CODE) {

                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        for (String permissions:PERMISSIONS) {
                            // 检查该权限是否已经获取
                            int i = ContextCompat.checkSelfPermission(this, permissions);
                            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                            if (i != PackageManager.PERMISSION_GRANTED) {
                                // 提示用户应该去应用设置界面手动开启权限
                                showDialogTipUserGoToAppSettting();
                            } else {

                                if (dialog != null && dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                                Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            }

    private void goToAppSetting() {

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CODE);

    }
          // 提示用户去应用设置界面手动开启权限

    private void showDialogTipUserGoToAppSettting() {

        dialog = new AlertDialog.Builder(this)
                .setTitle("权限不可用")
                .setMessage("请在-应用设置-权限-中，允许EAM使用相应的权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }
    @Override
    protected void findViewById() {

        tabbarFragment = (TabbarFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_content);
        funtionFragment = (FuntionFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_frist);
        wfmentFragment = (WfmentFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_second);
        myselfFragment = (MyselfFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_third);

        showFragment(1);
    }
    @Override
    protected void initView() {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hasPermissionWeNeed=true;
        for (String permissions:PERMISSIONS) {

            int i = ContextCompat.checkSelfPermission(this, permissions);

            if (i != PackageManager.PERMISSION_GRANTED) {
                hasPermissionWeNeed=false;
            }
        }
        if (hasPermissionWeNeed) {
            //判断权限是否足够
            Log.e("登陆", "用户名" + AccountUtils.getuserName(MainActivity.this));
            Log.e("登陆", "密码" + AccountUtils.getUserPassword(MainActivity.this));
            Log.e("登陆", "服务器" + AccountUtils.getIpAddress(MainActivity.this));
            String password = AccountUtils.getUserPassword(MainActivity.this);

            if (null == password || password.length() == 0) {
                AppManager.killAllActivity();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            else
            {

            }
        }
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getResources().getString(R.string.exit_text), Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(MainActivity.this);
        }
    }
    public void showFragment(int index)
    {
        if (funtionFragment==null||wfmentFragment==null||myselfFragment==null)
        {
            return;
        }

        Log.e("Fragment",""+index);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.hide(funtionFragment);
        transaction.hide(wfmentFragment);
        transaction.hide(myselfFragment);

        switch (index)
        {
            case 1:{
                transaction.show(funtionFragment);
            }break;

            case 2:{
                transaction.show(wfmentFragment);
            }break;

            case 3:{
                transaction.show(myselfFragment);
            }break;

        }
        transaction.commit();
    }

}
