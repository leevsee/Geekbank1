package com.leeves.h.geekbank1;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Function：
 * Created by h on 2016/8/11.
 *
 * @author
 */
//AppCompatActivity 和activity区别：app有默认的样式
public class WebViewButtonActivity extends AppCompatActivity {

    private WebView mWebView;

    public class TestJSEvent{
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(WebViewButtonActivity.this,toast,Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.web_view);

        //必须4.4以上，要翻墙，这行代码是调试模式，在chrome输入chrome://inspect/#devices，点击inspect
        WebView.setWebContentsDebuggingEnabled(true);

        mWebView.loadUrl("https://m.baidu.com");

//        HashMap<String,String> map = new HashMap<>();
//        map.put("token","XXX");//Cookie
//        map.put("my_haeder","header");//传给web的值
//        mWebView.loadUrl("https://m.baidu.com",map);

//        mWebView.loadUrl("file:///android_asset/test.html");

        //允许使用JS
        mWebView.getSettings().setJavaScriptEnabled(true);

        //cookie管理
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.setAcceptCookie(true);
//        cookieManager.setCookie("domain","cookie");//第一个值叫域，后面是值
//        cookieManager.removeAllCookies();

        //JS调用原生APP
        mWebView.addJavascriptInterface(new TestJSEvent(),"TestApp");

        //原生App调用JS
        mWebView.loadUrl("javascript:javaCallJS()");

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //拦截页面
                // 404页面

                if(url.contains("404")){
                    //如果出现404，跳到某个activity等等
                }
//                view.loadUrl("https://www.zhihu.com");
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //页面开始：显示loading动画
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //页面开始：隐藏loading动画
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //url 替换
                if(url.contains("logo.png")){

                }
                super.onLoadResource(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                //request  hybrid开发 有一部分是web，有一部分是手机，离线网页
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
                super.onTooManyRedirects(view, cancelMsg, continueMsg);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

        });

        mWebView.setWebChromeClient(new TestWebChromeClient());

    }

    public class TestWebChromeClient extends WebChromeClient{
        public TestWebChromeClient(){
            super();
        }
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //获取网页加载进度条，比如微信头上的进度条0-100 mProgressBar.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            //获取网页标题
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }
    }

    //activity里面有个按返回键的方法
    @Override
    public void onBackPressed() {

        if (mWebView != null && mWebView.canGoBack()){
//            WebBackForwardList webBackForwardList = mWebView.copyBackForwardList();
//
//            WebHistoryItem historyItem = webBackForwardList.getItemAtIndex(0);
//
//            String historyUrl = historyItem.getUrl();

            mWebView.goBack();
            mWebView.goForward();
            //正1向前，-1返回
//            mWebView.goBackOrForward(+1);
//            mWebView.reload();
        }else {
            super.onBackPressed();
        }
    }
}
