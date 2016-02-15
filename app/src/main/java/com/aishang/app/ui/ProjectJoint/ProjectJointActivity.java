package com.aishang.app.ui.ProjectJoint;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.webkit.WebSettings;
import android.webkit.WebView;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectJointActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_joint);
        ButterKnife.bind(this);
        initToolbar();
        initWebView();

    }

    private void initToolbar() {
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ProjectJointActivity.this.onBackPressed();
            }
        });
    }

    private void initWebView() {

        webView.setVisibility(WebView.VISIBLE);
        // wv.getSettings().setSupportZoom(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // wv.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("file:///android_asset/consociation.html");

    }
}
