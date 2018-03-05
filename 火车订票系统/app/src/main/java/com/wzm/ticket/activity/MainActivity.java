package com.wzm.ticket.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.wzm.ticket.database.MyOpenHelper;
import com.wzm.ticket.util.ExitApplication;

public class MainActivity extends TabActivity{
	private MyOpenHelper dbHelper;
	TabHost tabhost;
	Resources rs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ�����⣬������setcontentviewǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.main);  
		ExitApplication.getInstance().addActivity(this);
		tabhost= getTabHost(); 
		rs = getResources();
		
		//�õ�SharedPreferences����ȡ��¼��Ϣ
		SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
		boolean isLogin = spf.getBoolean("login", false);//Ĭ�ϳ�ʼ��Ϊ δ��¼
		
		//��ҳtab1����ƱԤ����
		Intent intent1 = new Intent(this,BookingActivity.class);
		TabSpec tabSpec1 = tabhost.newTabSpec("tab1").setContent(intent1)
				.setIndicator("��ƱԤ��", rs.getDrawable(R.drawable.green_btn_out));
		intent1.putExtra("tabId", "tab1");
		tabhost.addTab(tabSpec1);
		
		//tab2:���isLoginΪtrue����ʾ�ѵ�¼
		if(isLogin){
			Intent intent = getIntent();
			intent.setClass(this,OrderManageActivity.class);
			TabSpec tabSpec2 = tabhost.newTabSpec("tab2").setContent(intent)
					.setIndicator("��������", rs.getDrawable(R.drawable.green_btn_out));
			tabhost.addTab(tabSpec2);
			
		}else{
			Intent intent = new Intent(this,LoginActivity.class);
			TabSpec tabSpec2 = tabhost.newTabSpec("tab2").setContent(intent)
					.setIndicator("��������", rs.getDrawable(R.drawable.green_btn_out));
			intent.putExtra("tabId", "tab2");
			tabhost.addTab(tabSpec2);
		}
		if(isLogin){
			Intent intent = new Intent(this,My12306Activity.class);
			String username = spf.getString("username", "");
			TabSpec tabSpec3 = tabhost.newTabSpec("tab3").setContent(intent)
					.setIndicator("�ҵ�����", rs.getDrawable(R.drawable.green_btn_out));
			intent.putExtra("username", username);
			tabhost.addTab(tabSpec3);
			
		}else{
			Intent intent = new Intent(this,LoginActivity.class);
			TabSpec tabSpec3 = tabhost.newTabSpec("tab3").setContent(intent)
					.setIndicator("�ҵ�����", rs.getDrawable(R.drawable.green_btn_out));
			intent.putExtra("tabId", "tab3");
			tabhost.addTab(tabSpec3);
		}
		
		if(isLogin){
			Intent intent = new Intent(this,More2Activity.class);
			String username = spf.getString("username", "");
			TabSpec tabSpec4 = tabhost.newTabSpec("tab4").setContent(intent)
					.setIndicator("���๦��", rs.getDrawable(R.drawable.green_btn_out));
			intent.putExtra("username", username);
			tabhost.addTab(tabSpec4);
			
		}else{
			Intent intent = new Intent(this,MoreActivity.class);
			TabSpec tabSpec4 = tabhost.newTabSpec("tab4").setContent(intent)
					.setIndicator("���๦��", rs.getDrawable(R.drawable.green_btn_out));
			intent.putExtra("tabId", "tab4");
			tabhost.addTab(tabSpec4);
		}
		
		tabhost.setCurrentTab(0);
	}
}
