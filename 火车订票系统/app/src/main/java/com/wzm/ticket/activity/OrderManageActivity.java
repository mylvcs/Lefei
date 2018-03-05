package com.wzm.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wzm.ticket.util.ExitApplication;

public class OrderManageActivity extends Activity{
	protected static final String TAG = null;
	Button finishOrder,cancelOrder,changeOrder,returnMain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_manage);
		ExitApplication.getInstance().addActivity(this);
		
		finishOrder = (Button)findViewById(R.id.button_finish);
		//����ɶ���
		finishOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
				String name=spf.getString("username", "");//��ȡ��¼���û���
				intent.setClass(OrderManageActivity.this, FinishedOrderList.class);
				startActivity(intent);
			}
		});
		
		cancelOrder = (Button)findViewById(R.id.button_cancelTicket);
		//��Ʊ
		cancelOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
				String name=spf.getString("username", "");//��ȡ��¼���û���
				intent.setClass(OrderManageActivity.this, CancelOrderActivity.class);
				startActivity(intent);
			}
		});
		
		changeOrder = (Button)findViewById(R.id.button_changeTicket);
		//��ǩ
		changeOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
				String name=spf.getString("username", "");//��ȡ��¼���û���
				intent.setClass(OrderManageActivity.this, ChangeOrderActivity.class);
				startActivity(intent);
			}
		});
		//������ҳ
		returnMain = (Button)findViewById(R.id.returnMain);
		returnMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(OrderManageActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
