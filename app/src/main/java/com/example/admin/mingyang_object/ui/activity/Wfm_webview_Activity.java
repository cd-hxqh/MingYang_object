package com.example.admin.mingyang_object.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
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
        Log.e("跳转调试","启动网页浏览器");
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

        webSettings.setUseWideViewPort(true);//设定支持viewport

        webSettings.setLoadWithOverviewMode(true);

        webSettings.setSupportZoom(true);//

        webSettings.setBuiltInZoomControls(true);

        webSettings.setDisplayZoomControls(false);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        webSettings.setGeolocationEnabled(true);

        webSettings.setDomStorageEnabled(true);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearCache(true);
                webView.clearHistory();
                webView.clearFormData();
                webView.clearSslPreferences();
                webView.clearMatches();
                CookieManager.getInstance().removeAllCookie();
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

                Log.e("跳转调试","打开网页完毕" +url);

                if (url.indexOf("login")>=0)
                {
                    Log.e("跳转调试","当前是登录页面");

                    String username=AccountUtils.getUserName(Wfm_webview_Activity.this);
                    String password=AccountUtils.getUserPassword(Wfm_webview_Activity.this);

                    if((null!=username&&username.length()>0)&&(null!=password&&password.length()>0))
                    {
                        Log.e("跳转调试","取得的账号："+username);
                        Log.e("跳转调试","取得的密码："+password);
                        view.loadUrl("javascript:(function(){document.getElementById(\"username\").value=\""+username+"\"})()");
                        view.loadUrl("javascript:(function(){document.getElementById(\"password\").value=\""+password+"\"})()");
                        Log.e("跳转调试","点击登录按钮");
                        view.loadUrl("javascript:(function(){document.getElementById(\"loginbutton\").click()})()");

                    }
                }
                else
                {
                    Log.e("跳转调试","已经登陆成功");
                }
            }
        });

        webView.loadUrl(webUrl);
    }
}
