package com.prakashgujarati.khantrajputsamaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;

public class WebActivity extends BaseActivity {

    private Toolbar toolbar;
    private WebView mWebView;
    private String mUrl = "", wUrl = "", wTitle = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initview();

        wTitle = getIntent().getStringExtra("wTitle");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(wTitle);
        }
    }

    public void initview() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mWebView = (WebView) findViewById(R.id.webview_travel_activity);

        wUrl = getIntent().getStringExtra("wUrl");
        renderWebPage(wUrl);
    }

    // Custom method to render a web page
    protected void renderWebPage(String urlToRender) {
        mWebView.setWebViewClient(new WebViewClient() {
            /*
                public void onPageStarted (WebView view, String url, Bitmap favicon)
                    Notify the host application that a page has started loading. This method is
                    called once for each main frame load so a page with iframes or framesets will
                    call onPageStarted one time for the main frame. This also means that
                    onPageStarted will not be called when the contents of an embedded frame changes,
                    i.e. clicking a link whose target is an iframe, it will also not be called for
                    fragment navigations (navigations to #fragment_id).

                Parameters
                    view : The WebView that is initiating the callback.
                    url : The url to be loaded.
                    favicon : The favicon for this page if it already exists in the database.

            */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Do something on page loading started
                Toast.makeText(WebActivity.this, "Page loading started", Toast.LENGTH_SHORT).show();

                /*
                    public String getUrl ()
                        Gets the URL for the current page. This is not always the same as the URL
                        passed to WebViewClient.onPageStarted because although the load for that
                        URL has begun, the current page may not have changed.

                    Returns
                        the URL for the current page
                */
                // Only url is available in this stage
                mUrl = view.getUrl();

                // Update the action bar
                //getSupportActionBar().setSubtitle(mUrl);
            }

            /*
                public void onPageFinished (WebView view, String url)
                    Notify the host application that a page has finished loading. This method is
                    called only for main frame. When onPageFinished() is called, the rendering
                    picture may not be updated yet. To get the notification for the new Picture,
                    use onNewPicture(WebView, Picture).

                Parameters
                    view : The WebView that is initiating the callback.
                    url : The url of the page.
            */
            @Override
            public void onPageFinished(WebView view, String url) {
                // Do something when page loading finished
                Toast.makeText(WebActivity.this, "Page loaded", Toast.LENGTH_SHORT).show();

                // Both url and title is available in this stage
                //mUrl = view.getUrl();

                /*
                    public String getTitle ()
                        Gets the title for the current page. This is the title of the current page
                        until WebViewClient.onReceivedTitle is called.

                    Returns
                        the title for the current page
                */
                // mTitle = view.getTitle();

                // Update the action bar
                // getSupportActionBar().setTitle(mTitle);
                // getSupportActionBar().setSubtitle(mUrl);
            }

        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(urlToRender);
    }
}
