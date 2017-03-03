package com.example.admin.eam.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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


import com.example.admin.eam.R;
import com.example.admin.eam.api.HttpManager;
import com.example.admin.eam.api.HttpRequestHandler;
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.manager.AppManager;
import com.example.admin.eam.utils.AccountUtils;
import com.example.admin.eam.utils.MessageUtils;
import com.example.admin.eam.utils.NetWorkHelper;
import com.example.admin.eam.utils.PermissionsChecker;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


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

        if (AccountUtils.getIpAddress(LoginActivity.this).equals("")) {

            AccountUtils.setIpAddress(LoginActivity.this, Constants.HTTP_API_IP);
        }

        startPermissionsActivity();//
        findViewById();
        checkNewVersion();
        ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE},
                    123);
        initView();
    }
    private void startPermissionsActivity() {
        Log.e("权限检查","向用户请求权限");
        PermissionsActivity.startActivityForResult(LoginActivity.this, REQUEST_CODE, PERMISSIONS);
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
        checkBox.setVisibility(View.INVISIBLE);
        mLogin = (Button) findViewById(R.id.user_login);
        ipText = (TextView) findViewById(R.id.ip_address_id);
        versionName = (TextView) findViewById(R.id.versionName);

    }

    @Override
    protected void initView() {

        boolean isChecked = AccountUtils.getIsChecked(LoginActivity.this);
        mUsername.setText(AccountUtils.getuserName(LoginActivity.this));
        if (isChecked) {
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



            Log.e("登陆","自动登陆 账号"+mUsername.getText().toString());
            Log.e("登陆","自动登陆 密码"+mPassword.getText().toString());


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

                   // LoginOffline();

                } else {
                    JPushInterface.setAlias(LoginActivity.this,mUsername.getText().toString(), new TagAliasCallback(){
                        @Override
                        public void gotResult(int i, String s, Set<String> set) {
                            Log.e("极光推送",(i==0)?"设置标签成功":"设置标签失败");
                            Log.e("极光推送","："+s+"："+set);
                        }
                    });
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

        imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        

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

                        }
                        AccountUtils.setUserNameAndPassWord(LoginActivity.this, mUsername.getText().toString(), mPassword.getText().toString());
                        try {//保存登录返回信息
                            JSONObject object = new JSONObject(data);
                            JSONObject LoginDetails = object.getJSONObject("userLoginDetails");

                            AccountUtils.setLoginDetails(LoginActivity.this, LoginDetails.getString("insertOrg"), LoginDetails.getString("insertSite"),
                                    LoginDetails.getString("personId"), mUsername.getText().toString(), LoginDetails.getString("displayName"));

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

    private void checkNewVersion()
    {
        Log.e("库存查询","检查更新");

        AsyncTask<String, Integer, String> at= new AsyncTask<String, Integer, String>(){

            @Override
            protected String doInBackground(String... params) {

                String s = "";

                try {

                    TrustManager[] tm = { new X509TrustManager()
                    {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }};
                    SSLContext sslContext = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");

                    sslContext.init(null, tm, new java.security.SecureRandom());

                    SSLSocketFactory ssf = sslContext.getSocketFactory();

                    String path = "https://mykk.mywind.com.cn:8443/group1/M00/00/10/androidQe9mAGkAEAAACrY0gDzk311.txt";

                    URL url = new URL(path);

                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                    connection.setSSLSocketFactory(ssf);

                    InputStream inputStream  = connection.getInputStream();

                    byte[] data = new byte[3];

                    inputStream.read(data);

                    s= new  String(data);
                }
                catch (Exception e)
                {
                    Log.e("库存查询","读取远程版本错误"+e);
                }
                return s;
            }

            @Override
            protected void onPostExecute(String s) {

                Log.e("库存查询","服务器上的版本"+s);

                Double lastVersion = Double.parseDouble(s);

                Double currentVersion  = Double.parseDouble(getVersion());

                if (lastVersion>currentVersion)
                {
                    Log.e("库存查询","需要更新"+currentVersion+"--->>"+lastVersion);
                    showUpdataDialog();
                }
                else
                {
                    Log.e("库存查询","不需要更新"+currentVersion+"--->>"+lastVersion);

                }

            }
        };
        at.execute();
    }
    protected void showUpdataDialog() {

        AlertDialog.Builder builer = new AlertDialog.Builder(this) ;
        builer.setTitle("明阳风电EAM");
        builer.setMessage("发现新版本，建议马上更新");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("马上更新", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Log.e("自动更新","下载apk,更新");
                downLoadApk();

            }
        });
        builer.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        AlertDialog dialog = builer.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public File getFileFromServer(String path, ProgressDialog pd) throws Exception{


        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){


            URL url = new URL(path);

            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);

            //获取到文件的大小
            pd.setMax(conn.getContentLength());

            InputStream is = conn.getInputStream();

            File file = new File(Environment.getExternalStorageDirectory(), "androidQe9mAGkAEAAACrY0gDzk311.apk");

            FileOutputStream fos = new FileOutputStream(file);

            BufferedInputStream bis = new BufferedInputStream(is);

            byte[] buffer = new byte[1024];

            int len ;

            int total=0;

            while((len =bis.read(buffer))!=-1){

                fos.write(buffer, 0, len);

                total+= len;

                //获取当前下载量
                pd.setProgress(total);

            }

            fos.close();

            bis.close();

            is.close();

            return file;
        }
        else{
            return null;
        }
    }
    protected void downLoadApk() {

        final ProgressDialog pd;    //进度条对话框
        pd = new  ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer("http://mykk.mywind.com.cn:8000/group1/M00/00/07/androidQe9mAGkAEAAACrY0gDzk311.apk", pd);
                    sleep(1000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }}.start();
    }
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
