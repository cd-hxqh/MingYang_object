package com.example.admin.eam.ui.fragment;

import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.manager.AppManager;
import com.example.admin.eam.ui.activity.LoginActivity;
import com.example.admin.eam.utils.AccountUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by chris on 2017/3/2.
 */

public class MyselfFragment extends android.support.v4.app.Fragment {

    TextView dispalyname,username,environment,address,version;
    RelativeLayout logout;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_myself,container,false);

        dispalyname = (TextView)v.findViewById(R.id.myself_displayname);
        username = (TextView)v.findViewById(R.id.myself_username);
        environment = (TextView)v.findViewById(R.id.myself_environment);
        address = (TextView)v.findViewById(R.id.myself_ipaddress);
        version = (TextView)v.findViewById(R.id.myself_version);
        logout = (RelativeLayout)v.findViewById(R.id.myself_logout);
        initView();
        return v;
    }
    private void initView()
    {
        dispalyname.setText(AccountUtils.getdisplayName(getActivity().getApplicationContext()));
        username.setText(AccountUtils.getuserName(getActivity().getApplicationContext()));

        String ip = AccountUtils.getIpAddress(getActivity());
        if (ip.startsWith("http://eamapp"))
        {
            environment.setText("正式环境");
        }
        else if (ip.startsWith("http://deveam"))
        {
            environment.setText("开发环境");
        }
        else
        {
            environment.setText("测试环境");
        }
        address.setText(ip);
        version.setText(getVersion()+"(点击检查新版本)");
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNewVersion();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("注销");
                dialog.setMessage("注销当前用户？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AccountUtils.ClearPassWord(getActivity());
                        AppManager.killAllActivity();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });

    }
    public String getVersion() {

        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
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
                    showTipsaDialog();
                }

            }
        };
        at.execute();
    }
    protected void showUpdataDialog() {

        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity()) ;
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

    protected void showTipsaDialog() {

        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity()) ;
        builer.setTitle("明阳风电EAM");
        builer.setMessage("当前已是最新版本");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

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
        pd = new  ProgressDialog(getActivity().getApplicationContext());
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
