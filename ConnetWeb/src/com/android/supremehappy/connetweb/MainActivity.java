package com.android.supremehappy.connetweb;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	WebView wv;
	final private String DEFAULT_URL="http://192.168.1.149:8081/dokdosarang/intro.html";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		wv = (WebView) findViewById(R.id.webView1);
		
		wv.getSettings().setJavaScriptEnabled(true);// 자바 스크립트 활성화
		wv.getSettings().setUseWideViewPort(true); // 단말기 사이즈에 맞게 수정을 활성화
		wv.setWebViewClient(new WebViewClient());
		
		wv.loadUrl(DEFAULT_URL);
	}
}
