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
	private final String STATUS = "改签票";
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
	//确认改签
	private void setListener() {
		confirm_order.setOnClickListener(new OnClickListener() {//确认订单
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(ConfirmChangeActivity.this, FinishedOrderList.class);
				startActivity(intent);
			}
		});
		
		//返回到查询结果页面
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
		//得到订单编号
		Bundle bundle = intent.getExtras();
		int ids = (Integer) bundle.getSerializable("订单编号");
		
		String trainCode = intent.getStringExtra("车次");
		//得到7个值
		Ticket t = tDao.queryByCode(trainCode);
		
		//得到姓名
		String username = intent.getStringExtra("乘客姓名");
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
		
		oDao.updateOrder(t,STATUS,ids);//执行改签操作
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
