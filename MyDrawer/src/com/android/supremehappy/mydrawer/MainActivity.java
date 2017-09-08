package com.android.supremehappy.mydrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private DrawerLayout drawerlayout;
	private View drawerView;
	private WebView mainWebView, subWebView;
	
	private String MAIN_URL="http://www.naver.com";
	private String SUB_URL="http://www.google.com";
	
	private WebViewClient wvc,wvc1; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mainWebView=(WebView) findViewById(R.id.main_webview);
		mainWebView.getSettings().setJavaScriptEnabled(true);
		
		wvc= new WebViewClient();
		mainWebView.setWebViewClient(wvc);
		mainWebView.loadUrl(MAIN_URL);
		
		subWebView=(WebView) findViewById(R.id.sub_webview);
		subWebView.getSettings().setJavaScriptEnabled(true);
		
		wvc1= new WebViewClient();
		subWebView.setWebViewClient(wvc1);
		
		drawerlayout=(DrawerLayout) findViewById(R.id.drawer_layout);
		
		DrawerListener myListener = new DrawerListener(){

			@Override
			public void onDrawerClosed(View arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDrawerOpened(View arg0) {
				
				subWebView.loadUrl(SUB_URL);
				
			}

			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDrawerStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		drawerlayout.setDrawerListener(myListener);

	}
}

