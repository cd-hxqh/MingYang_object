package com.example.admin.mingyang_object.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
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
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                titleView.setText(title);//a textview
            }
        });

        String postDate = "username=" + AccountUtils.getUserName(Wfm_webview_Activity.this) + "&password="
                + AccountUtils.getUserPassword(Wfm_webview_Activity.this);
//        webView.loadUrl(AccountUtils.getIpAddress(Wfm_webview_Activity.this)
//                + "/maximo/ui/?event=loadapp&value=" + wfm.getAPP() + "&uniqueid=" + wfm.getOWNERID() + "");
        //webview.reload();// reload page

        webView.postUrl(AccountUtils.getIpAddress(Wfm_webview_Activity.this)
                + "/maximo/ui/?event=loadapp&value=" + wfm.getAPP() + "&uniqueid=" + wfm.getOWNERID(), EncodingUtils.getBytes(postDate, "BASE64"));


    }


}
