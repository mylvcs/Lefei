package com.wzm.ticket.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wzm.ticket.database.TicketDao;
import com.wzm.ticket.entity.Ticket;
import com.wzm.ticket.util.ExitApplication;

public class ConfirmOrdersActivity extends Activity{
	TicketDao tDao = new TicketDao(this);
	List<Map<String,Object>> data = null;
	TextView stationCode,startStaion,startTime,arriveStation,
			 arriveTime,name,costTime,price,dates;
	Button orderBtn,returnResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_order);
		ExitApplication.getInstance().addActivity(this);
		findViews();
		setText();
		setListener();
	}
	//提交订单的按钮
	private void setListener() {
		orderBtn.setOnClickListener(new OnClickListener() {//确认订单
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(ConfirmOrdersActivity.this, ConfirmPayActivity.class);
				startActivity(intent);
			}
		});
		
		//返回到查询结果页面
		returnResult.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(ConfirmOrdersActivity.this, QueryResultActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setText() {
		Intent intent = getIntent();
		String trainCode = intent.getStringExtra("trainCode");
		String username = intent.getStringExtra("username");
		String date1 = intent.getStringExtra("date1");
		Ticket t = tDao.queryByCode(trainCode);
		stationCode.setText(t.getTrainCode());
		startStaion.setText(t.getStartStation());
		startTime.setText(t.getStartTime());
		arriveStation.setText(t.getArriveStation());
		arriveTime.setText(t.getArriveTime());
		costTime.setText(t.getUseTime());
		price.setText(t.getTicketPrice()+"");
		name.setText(username);
		dates.setText(date1);
	}
	private void findViews() {
		stationCode = (TextView)findViewById(R.id.stationCode);
		startStaion = (TextView)findViewById(R.id.start_station);
		startTime = (TextView)findViewById(R.id.start_times);
		arriveStation = (TextView)findViewById(R.id.arrive_station1);
		arriveTime = (TextView)findViewById(R.id.arrive_times);
		name = (TextView)findViewById(R.id.name);
		costTime = (TextView)findViewById(R.id.costTime);
		price = (TextView)findViewById(R.id.costPrice);
		orderBtn = (Button)findViewById(R.id.submitOrder);
		returnResult = (Button)findViewById(R.id.returnResult);
		dates = (TextView)findViewById(R.id.confirm_date);
	}
}
