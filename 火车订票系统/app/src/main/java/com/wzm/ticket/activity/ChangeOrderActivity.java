package com.wzm.ticket.activity;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wzm.ticket.database.OrderDao;
import com.wzm.ticket.util.ExitApplication;

/**
 * 改签：出发地和目的地都不可以更改，只能修改日期时间或车次。
 * @author Administrator
 *
 */
public class ChangeOrderActivity extends Activity {
	OrderDao oDao = new OrderDao(this);
	ArrayList<Map<String, Object>> data = null;
	Button orderBtn,cancelOrder,changeOrder;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_order);
		ExitApplication.getInstance().addActivity(this);
		lv = (ListView) findViewById(R.id.lv_changeOrder);

		SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
		String username = spf.getString("username", "");
		// 查询订单信息
		data = oDao.queryOrders(username);
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.order_items, 
				new String[] { "订单编号", "车次", "发车站","发车时间", "到达站", "到达时间", "乘客姓名", "身份证号", "票价","日期","车票状态" },
				new int[] { R.id.orders_id2, R.id.orders_stationCode,
						R.id.orders_start_station, R.id.orders_start_TIME,
						R.id.orders_arrive_station1, R.id.orders_arrive_TIME,
						R.id.orders_username, R.id.orders_idNumber,
						R.id.orders_costPrice,R.id.orders_date1,R.id.status_items });
		for(int i=0;i<data.size();i++){
			String status = (String) data.get(i).get("车票状态");
			if(status.equals("已支付"))
				lv.setAdapter(adapter);
		}

		//返回主页
		orderBtn = (Button) findViewById(R.id.change_return_first);
		orderBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(ChangeOrderActivity.this, OrderManageActivity.class);
				startActivity(intent);
			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = getIntent();
				intent.setClass(ChangeOrderActivity.this, Change_order_query_Activity.class);
				String fromStation =data.get(position).get("发车站").toString();
				String toStation =data.get(position).get("到达站").toString();
				String username =data.get(position).get("乘客姓名").toString();
				int ids =(Integer) data.get(position).get("订单编号");
				intent.putExtra("发车站", fromStation);
				intent.putExtra("到达站", toStation);
				intent.putExtra("订单编号", ids);
				intent.putExtra("乘客姓名", username);
				startActivity(intent);
			}
		});
	}

	
}
