package com.wzm.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wzm.ticket.util.ExitApplication;

public class My12306Activity extends Activity{
	private Button contacts;
	private Button myInfo;
	private Button updatePass;
	private Button returnMain2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my12306);
		ExitApplication.getInstance().addActivity(this);
		
		contacts = (Button)findViewById(R.id.contacts);
		myInfo = (Button)findViewById(R.id.personal_inf);
		updatePass = (Button)findViewById(R.id.update_pwd);
		
		updatePass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(My12306Activity.this,updatePassActivity.class);
				startActivity(intent);
			}
		});
		myInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(My12306Activity.this,PersonalInfoActivity.class);
				startActivity(intent);
			}
		});
		
		returnMain2 = (Button)findViewById(R.id.returnMain2);
		returnMain2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(My12306Activity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
