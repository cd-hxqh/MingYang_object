package com.example.admin.mingyang_object.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.UICheckUpdateCallback;
import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.manager.AppManager;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.utils.NetWorkHelper;
import com.example.admin.mingyang_object.utils.PermissionsChecker;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "Activity_Login";
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private ProgressDialog mProgressDialog;
    //    private MemberModel mProfile;
    private CheckBox checkBox; //记住密码
    private TextView versionName;//版本号

    private boolean isRemember; //是否记住密码


    String userName; //用户名

    String userPassWorld; //密码

    String imei; //imei

    /**
     * 服务器Ip 地址*
     */
    private TextView ipText;


    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private String[] idadresss;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private String adress;


    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

//    @Bind(R.id.main_t_toolbar) Toolbar mTToolbar;

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        BDAutoUpdateSDK.uiUpdateAction(this, new MyUICheckUpdateCallback());
        if (AccountUtils.getIpAddress(LoginActivity.this).equals("")) {
            AccountUtils.setIpAddress(LoginActivity.this, Constants.HTTP_API_IP);
        }
        imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        mPermissionsChecker = new PermissionsChecker(this);

        findViewById();
        initView();
    }

    @Override protected void onResume() {
        super.onResume();

        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)&&Build.VERSION.SDK_INT >= 23) {
            startPermissionsActivity();
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    @Override
    protected void findViewById() {
        mUsername = (EditText) findViewById(R.id.user_login_id);
        mPassword = (EditText) findViewById(R.id.user_login_password);
        checkBox = (CheckBox) findViewById(R.id.isremenber_password);
        mLogin = (Button) findViewById(R.id.user_login);
        ipText = (TextView) findViewById(R.id.ip_address_id);
        versionName = (TextView) findViewById(R.id.versionName);
    }

    @Override
    protected void initView() {

        boolean isChecked = AccountUtils.getIsChecked(LoginActivity.this);
        if (isChecked) {
            mUsername.setText(AccountUtils.getUserName(LoginActivity.this));
            mPassword.setText(AccountUtils.getUserPassword(LoginActivity.this));
        }
        checkBox.setOnCheckedChangeListener(cheBoxOnCheckedChangListener);
        mLogin.setOnClickListener(this);
        ipText.setOnClickListener(this);
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        adress = AccountUtils.getIpAddress(LoginActivity.this);
        addIpData();

        versionName.setText(getVersion());

        if (!mUsername.getText().toString().equals("")&&!mPassword.getText().toString().equals("")){
            mLogin.performClick();
        }
    }

    private CompoundButton.OnCheckedChangeListener cheBoxOnCheckedChangListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isRemember = isChecked;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login:
                if (mUsername.getText().length() == 0) {
                    mUsername.setError(getString(R.string.login_error_empty_user));
                    mUsername.requestFocus();
                } else if (mPassword.getText().length() == 0) {
                    mPassword.setError(getString(R.string.login_error_empty_passwd));
                    mPassword.requestFocus();
                } else if (NetWorkHelper.isNetwork(LoginActivity.this)) {
                    LoginOffline();
                } else {
                    login();
                }
                break;

            case R.id.ip_address_id:
                mMenuItems = new ArrayList<>();
                addIpData();
                NormalListDialog();
                break;

        }
    }


    /**
     * 登陆*
     */
    private void login() {
        mProgressDialog = ProgressDialog.show(LoginActivity.this, null,
                getString(R.string.login_loging), true, true);

        HttpManager.loginWithUsername(LoginActivity.this,
                mUsername.getText().toString(),
                mPassword.getText().toString(), imei,
                new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

                        MessageUtils.showMiddleToast(LoginActivity.this, "登录成功");
                        mProgressDialog.dismiss();
                        AccountUtils.setIpAddress(LoginActivity.this,adress);
                        if (isRemember) {
                            AccountUtils.setChecked(LoginActivity.this, true);
                            //记住密码
                            AccountUtils.setUserNameAndPassWord(LoginActivity.this, mUsername.getText().toString(), mPassword.getText().toString());
                        }
                        try {//保存登录返回信息
                            JSONObject object = new JSONObject(data);
                            JSONObject LoginDetails = object.getJSONObject("userLoginDetails");
                            AccountUtils.setLoginDetails(LoginActivity.this, LoginDetails.getString("insertOrg"), LoginDetails.getString("insertSite"),
                                    LoginDetails.getString("personId"), object.getString("userName"), LoginDetails.getString("displayName"));
//                            findByDepartment(LoginDetails.getString("personId"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AccountUtils.setIsOffLine(LoginActivity.this, false);
                        startIntent();

                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {
                        MessageUtils.showMiddleToast(LoginActivity.this, getString(R.string.login_successful_hint));
                        AccountUtils.setIsOffLine(LoginActivity.this, false);
                        startIntent();
                    }

                    @Override
                    public void onFailure(String error) {
                        MessageUtils.showErrorMessage(LoginActivity.this, error);
                        mProgressDialog.dismiss();
                    }
                });
    }


    private void startIntent() {
        Intent inetnt = new Intent();
        inetnt.setClass(this, MainActivity.class);
        startActivity(inetnt);
    }

    /**
     * 离线登录*
     */
    private void LoginOffline() {
        final NormalDialog dialog = new NormalDialog(LoginActivity.this);
        dialog.content("确定离线登录吗?")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        AccountUtils.setIsOffLine(LoginActivity.this, true);
                        startIntent();
                        Toast.makeText(LoginActivity.this, "离线登录成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getString(R.string.exit_text), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(LoginActivity.this);
        }
    }

//    /**
//     * 根据PersionId查询所属部门*
//     */
//    private void findByDepartment(String persionId) {
//        HttpManager.getData(LoginActivity.this, HttpManager.getPersonUrl1(persionId), new HttpRequestHandler<Results>() {
//            @Override
//            public void onSuccess(Results results) {
//
//                ArrayList<Person> item = JsonUtils.parsingPerson(results.getResultlist());
//
//                if (item != null || item.size() != 0) {
//                    AccountUtils.setDepartment(LoginActivity.this, item.get(0).department);
//                }
//            }
//
//            @Override
//            public void onSuccess(Results results, int totalPages, int currentPage) {
//
//
//            }
//
//            @Override
//            public void onFailure(String error) {
//
//            }
//        });
//    }


    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(LoginActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "ip=" + idadresss[position]);
                String ip = idadresss[position];
                if (ip.contains("√")) {
                    ip.replace("√", "");
                }
                AccountUtils.setIpAddress(LoginActivity.this, getResources().getStringArray(R.array.address_text)[position].split(" ")[1].trim());
                adress = getResources().getStringArray(R.array.address_text)[position].split(" ")[1].trim();
                dialog.dismiss();
            }
        });
    }


    /**
     * 设置服务端地址*
     */
    private void addIpData() {
        String[] inspotypes = getResources().getStringArray(R.array.address_text);
        idadresss = getResources().getStringArray(R.array.address_text);

        for (int i = 0; i < inspotypes.length; i++) {
            if (adress != null && adress.equals(inspotypes[i].split(" ")[1])) {
                mMenuItems.add(new DialogMenuItem("√  " + inspotypes[i], 0));
            } else {
                mMenuItems.add(new DialogMenuItem("    " + inspotypes[i], 0));
            }
        }
    }


    private class MyUICheckUpdateCallback implements UICheckUpdateCallback {
        @Override
        public void onCheckComplete() {
            Log.i(TAG, "onCheckComplete");
        }

    }

    public String getVersion() {
             try {
                     PackageManager manager = this.getPackageManager();
                     PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
                     String version = info.versionName;
                     return version;
                 } catch (Exception e) {
                     e.printStackTrace();
                     return this.getString(R.string.can_not_find_version_name);
                 }
         }

}
