package com.wzm.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wzm.ticket.database.TicketDao;
import com.wzm.ticket.util.ExitApplication;

public class FinishedPayActivity extends Activity{
	TicketDao tDao = new TicketDao(this);
	Button toOrderList,returnFirst;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finish_pay);
		ExitApplication.getInstance().addActivity(this);

		toOrderList = (Button)findViewById(R.id.toOrderList);
		toOrderList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(FinishedPayActivity.this, FinishedOrderList.class);
				startActivity(intent);
			}
		});
		
		returnFirst = (Button)findViewById(R.id.return_First);
		returnFirst.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(FinishedPayActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
