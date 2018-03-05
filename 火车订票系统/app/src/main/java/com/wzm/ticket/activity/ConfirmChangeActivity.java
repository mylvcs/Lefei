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

import com.wzm.ticket.database.OrderDao;
import com.wzm.ticket.database.TicketDao;
import com.wzm.ticket.database.UserDao;
import com.wzm.ticket.entity.Ticket;
import com.wzm.ticket.entity.User;
import com.wzm.ticket.util.ExitApplication;

public class ConfirmChangeActivity extends Activity{
	TicketDao tDao = new TicketDao(this);
	UserDao uDao = new UserDao(this);
	OrderDao oDao = new OrderDao(this);
	List<Map<String,Object>> data = null;
	private final String STATUS = "��ǩƱ";
	TextView orderId,dates,startStaion,startTime,arriveStation,
			 arriveTime,name,stationCode,price,idCard,status;
	Button confirm_order,cancel_order,returnResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_change);
		ExitApplication.getInstance().addActivity(this);
		findViews();
		setText();
		setListener();
	}
	//ȷ�ϸ�ǩ
	private void setListener() {
		confirm_order.setOnClickListener(new OnClickListener() {//ȷ�϶���
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(ConfirmChangeActivity.this, FinishedOrderList.class);
				startActivity(intent);
			}
		});
		
		//���ص���ѯ���ҳ��
/*		returnResult.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(ConfirmChangeActivity.this, QueryResultActivity.class);
				startActivity(intent);
			}
		});*/
	}

	private void setText() {
		Intent intent = getIntent();
		//�õ��������
		Bundle bundle = intent.getExtras();
		int ids = (Integer) bundle.getSerializable("�������");
		
		String trainCode = intent.getStringExtra("����");
		//�õ�7��ֵ
		Ticket t = tDao.queryByCode(trainCode);
		
		//�õ�����
		String username = intent.getStringExtra("�˿�����");
		System.out.println(username);
		User user = uDao.queryByName1(username);
		
		idCard.setText(user.getIdCard()+"");
		orderId.setText(ids+"");
		dates.setText(t.getDate());
		startStaion.setText(t.getStartStation());
		startTime.setText(t.getStartTime());
		arriveStation.setText(t.getArriveStation());
		arriveTime.setText(t.getArriveTime());
		name.setText(username);
		stationCode.setText(t.getTrainCode());
		price.setText(t.getTicketPrice()+"");
		status.setText(STATUS);
		
		oDao.updateOrder(t,STATUS,ids);//ִ�и�ǩ����
		tDao.updateByCode(trainCode);
	}
	private void findViews() {
		orderId = (TextView)findViewById(R.id.orders_id2);
		dates = (TextView)findViewById(R.id.orders_date1);
		startStaion = (TextView)findViewById(R.id.orders_start_station);
		startTime = (TextView)findViewById(R.id.orders_start_TIME);
		arriveStation = (TextView)findViewById(R.id.orders_arrive_station1);
		arriveTime = (TextView)findViewById(R.id.orders_arrive_TIME);
		name = (TextView)findViewById(R.id.orders_username);
		stationCode = (TextView)findViewById(R.id.orders_stationCode);
		price = (TextView)findViewById(R.id.orders_costPrice);
		idCard = (TextView)findViewById(R.id.orders_idNumber);
		status = (TextView)findViewById(R.id.status_change);
		
		confirm_order = (Button)findViewById(R.id.confirm_change_bn);
		cancel_order = (Button)findViewById(R.id.cancel_change_bn);
		
		returnResult = (Button)findViewById(R.id.returnResult);
	}
}
