package com.wzm.ticket.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wzm.ticket.database.OrderDao;
import com.wzm.ticket.database.TicketDao;
import com.wzm.ticket.database.UserDao;
import com.wzm.ticket.entity.Order;
import com.wzm.ticket.entity.Ticket;
import com.wzm.ticket.entity.User;
import com.wzm.ticket.util.ExitApplication;

public class ConfirmPayActivity extends Activity{
	Button returnOrder;
	TicketDao tDao = new TicketDao(this);
	UserDao uDao = new UserDao(this);
	OrderDao oDao = new OrderDao(this);
	List<Map<String,Object>> data = null;
	TextView stationCode,startStaion,startTime,arriveStation,
			 arriveTime,name,costTime,price,date;
	Button payBtn,cancelBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_pay);
		ExitApplication.getInstance().addActivity(this);
		
		findViews();
		setText();
		setListener();
		//��ʾ��Ʊ��Ϣ
		
	}
	private void setListener() {
		//ȡ����ť
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(ConfirmPayActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		//���� 
		returnOrder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		//ȷ��֧��
		payBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				String trainCode = intent.getStringExtra("trainCode");
				tDao.updateByCode(trainCode);//������Ʊ����ÿ֧��һ����������վ����һ�ų�Ʊ
				//�õ��û���Ϣ
				SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
				String username = spf.getString("username", "");
				System.out.println(username);
				User user = uDao.queryByName1(username);
				long userIdNum = user.getIdCard();
				//�õ���Ʊ��Ϣ
				Ticket t = tDao.queryByCode(trainCode);
				//����һ�Ŷ��������µ����ݿ� ������
				String status = "��֧��";
				Order order = new Order(trainCode, t.getStartStation(), t.getStartTime(),
						t.getArriveStation(), t.getArriveTime(), username, userIdNum, 
						t.getTicketPrice(),t.getDate(),status);
				oDao.insertOrder(order);
				
				intent.setClass(ConfirmPayActivity.this, FinishedPayActivity.class);
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
		date.setText(date1);
		
	}
	private void findViews() {
		payBtn = (Button)findViewById(R.id.payBtn);
		cancelBtn = (Button)findViewById(R.id.cancelBtn);
		stationCode = (TextView)findViewById(R.id.pay_stationCode);
		startStaion = (TextView)findViewById(R.id.pay_start_station);
		startTime = (TextView)findViewById(R.id.pay_start_times);
		arriveStation = (TextView)findViewById(R.id.pay_arrive_station1);
		arriveTime = (TextView)findViewById(R.id.pay_arrive_times);
		name = (TextView)findViewById(R.id.pay_name);
		costTime = (TextView)findViewById(R.id.pay_costTime);
		date = (TextView)findViewById(R.id.pay_date);
		price = (TextView)findViewById(R.id.pay_costPrice);
		returnOrder = (Button)findViewById(R.id.returnConfirmOrder);
	}
}
