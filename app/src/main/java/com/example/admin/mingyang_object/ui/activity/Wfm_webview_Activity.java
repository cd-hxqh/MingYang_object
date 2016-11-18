package com.example.admin.mingyang_object.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Wfassignment;
import com.example.admin.mingyang_object.utils.AccountUtils;

import org.apache.http.util.EncodingUtils;

/**
 * Created by Administrator on 2016/11/11.
 */
public class Wfm_webview_Activity extends BaseActivity {

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    private WebView webView;

    private String webUrl;

    private Wfassignment wfm; //流程审批

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfm_webview);

        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        wfm = (Wfassignment) getIntent().getSerializableExtra("wfm");
        webUrl = getIntent().getStringExtra("url");
        Log.e("跳转调试","取得的URL： "+webUrl);
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                titleView.setText(title);//a textview
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                String username=AccountUtils.getUserName(Wfm_webview_Activity.this);
                String password=AccountUtils.getUserPassword(Wfm_webview_Activity.this);

                //if (view.getTitle().toString().equals("EAM DEVSYS")||view.getTitle().toString().equals("正式系统"))
                //{
                    Log.e("跳转调试","准备自动登录");

                    Log.e("跳转调试","取得的账号："+username);
                    Log.e("跳转调试","取得的密码："+password);

                    view.loadUrl("javascript:(function(){document.getElementById(\"username\").value=\""+username+"\"})()");
                    view.loadUrl("javascript:(function(){document.getElementById(\"password\").value=\""+password+"\"})()");

                    Log.e("跳转调试","点击登录按钮");
                    view.loadUrl("javascript:(function(){document.getElementById(\"loginbutton\").click()})()");
                //}

            }
        });



//        webView.loadUrl(AccountUtils.getIpAddress(Wfm_webview_Activity.this)
//                + "/maximo/ui/?event=loadapp&value=" + wfm.getAPP() + "&uniqueid=" + wfm.getOWNERID() + "");
        //webview.reload();// reload page

        //webView.postUrl(AccountUtils.getIpAddress(Wfm_webview_Activity.this)
        //        + "/maximo/ui/?event=loadapp&value=" + wfm.getAPP() + "&uniqueid=" + wfm.getOWNERID(), EncodingUtils.getBytes(postDate, "BASE64"));

        webView.loadUrl(webUrl);
    }


}
