package com.wzm.ticket.activity;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wzm.ticket.database.OrderDao;
import com.wzm.ticket.util.ExitApplication;

public class FinishedOrderList extends Activity {
	OrderDao oDao = new OrderDao(this);
	ArrayList<Map<String, Object>> data = null;
	Button orderBtn;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list);
		ExitApplication.getInstance().addActivity(this);
		lv = (ListView) findViewById(R.id.lv_orderlist);

		SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
		String username = spf.getString("username", "");
		// 查询订单信息
		data = oDao.queryOrders(username);
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.order_items, 
				new String[] { "订单编号", "车次", "发车站","发车时间", "到达站", "到达时间", "乘客姓名", "身份证号", "票价" ,"日期","车票状态"},
				new int[] { R.id.orders_id2, R.id.orders_stationCode,
						R.id.orders_start_station, R.id.orders_start_TIME,
						R.id.orders_arrive_station1, R.id.orders_arrive_TIME,
						R.id.orders_username, R.id.orders_idNumber,
						R.id.orders_costPrice,R.id.orders_date1,R.id.status_items });
		lv.setAdapter(adapter);

		orderBtn = (Button) findViewById(R.id.return_first);
		orderBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(FinishedOrderList.this, OrderManageActivity.class);
				startActivity(intent);
			}
		});

	}

	
}
