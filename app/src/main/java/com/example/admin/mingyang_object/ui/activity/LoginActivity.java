package com.example.admin.mingyang_object.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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


import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.HttpManager;
import com.example.admin.mingyang_object.api.HttpRequestHandler;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.manager.AppManager;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.utils.NetWorkHelper;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        if (AccountUtils.getIpAddress(LoginActivity.this).equals("")) {
            AccountUtils.setIpAddress(LoginActivity.this, Constants.HTTP_API_IP);
        }
        imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        mUsername = (EditText) findViewById(R.id.user_login_id);
        mPassword = (EditText) findViewById(R.id.user_login_password);
        checkBox = (CheckBox) findViewById(R.id.isremenber_password);
        mLogin = (Button) findViewById(R.id.user_login);
        ipText = (TextView) findViewById(R.id.ip_address_id);
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
        addIpData();
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
                } else if (NetWorkHelper.isNetwork(LoginActivity.this)){
                    LoginOffline();
                }else {
                    login();
                }
                break;

            case R.id.ip_address_id:
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
                        if (isRemember) {
                            AccountUtils.setChecked(LoginActivity.this, isRemember);
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
                        AccountUtils.setIsOffLine(LoginActivity.this,false);
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
                        AccountUtils.setIsOffLine(LoginActivity.this,true);
                        startIntent();
                        Toast.makeText(LoginActivity.this,"离线登录成功",Toast.LENGTH_SHORT).show();
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
                AccountUtils.setIpAddress(LoginActivity.this, idadresss[position]);

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

        for (int i = 0; i < inspotypes.length; i++)
            mMenuItems.add(new DialogMenuItem(inspotypes[i], 0));


    }
}
