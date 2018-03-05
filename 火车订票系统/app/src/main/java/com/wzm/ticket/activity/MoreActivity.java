package com.wzm.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wzm.ticket.util.ExitApplication;

public class MoreActivity extends Activity{
	Button loginBn;
	Button registerBn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_info1);
		ExitApplication.getInstance().addActivity(this);
		
		loginBn = (Button)findViewById(R.id.login);
		registerBn = (Button)findViewById(R.id.register);
		loginBn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(MoreActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		});
		registerBn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MoreActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
	}
}
