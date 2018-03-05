package com.wzm.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wzm.ticket.util.ExitApplication;

public class More2Activity extends Activity{
	TextView tvName;
	Button logout,returnFirst;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_info2);
		tvName = (TextView)findViewById(R.id.more_username);
		logout = (Button)findViewById(R.id.more_logout);
		returnFirst = (Button)findViewById(R.id.returnMain3);
		ExitApplication.getInstance().addActivity(this);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra("username");
		tvName.setText("欢迎您："+name);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(More2Activity.this, MainActivity.class));
				//获得SharedPreferences对象
				SharedPreferences share = getSharedPreferences("checkLogin",  
			               MODE_PRIVATE);
				//获得editor 写入对象
				SharedPreferences.Editor  editor = share.edit();  
				editor.clear();//将SharedPreferences内的内容清空
				editor.commit(); //提交事物
				ExitApplication.getInstance().exit();
			}
		});
		
		returnFirst.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				String name = intent.getStringExtra("username");
				SharedPreferences.Editor editor = getSharedPreferences("checkLogin",
						MODE_PRIVATE).edit();
				editor.putString("username", name);
				editor.putBoolean("login", true);
				editor.commit();
				intent.setClass(More2Activity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
