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
import android.widget.Toast;

import com.wzm.ticket.database.OrderDao;
import com.wzm.ticket.database.TicketDao;
import com.wzm.ticket.util.ExitApplication;

public class CancelOrderActivity extends Activity {
	OrderDao oDao = new OrderDao(this);
	TicketDao tDao = new TicketDao(this);
	ArrayList<Map<String, Object>> data = null;
	Button orderBtn,cancelOrder,changeOrder;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cancel_order);
		ExitApplication.getInstance().addActivity(this);
		lv = (ListView) findViewById(R.id.lv_cancelOrder);
		ExitApplication.getInstance().addActivity(this);

		SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
		String username = spf.getString("username", "");
		// ��ʾ������Ϣ
		data = oDao.queryOrders(username);
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.order_items, 
				new String[] { "�������", "����", "����վ","����ʱ��", "����վ", "����ʱ��", "�˿�����", "���֤��", "Ʊ��","����","��Ʊ״̬" },
				new int[] { R.id.orders_id2, R.id.orders_stationCode,
						R.id.orders_start_station, R.id.orders_start_TIME,
						R.id.orders_arrive_station1, R.id.orders_arrive_TIME,
						R.id.orders_username, R.id.orders_idNumber,
						R.id.orders_costPrice,R.id.orders_date1,R.id.status_items});
		lv.setAdapter(adapter);

		orderBtn = (Button) findViewById(R.id.cancel_return_first);
		orderBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(CancelOrderActivity.this, OrderManageActivity.class);
				startActivity(intent);
			}
		});

		//����б��ִ����Ʊ���������ݿ�order�����1��ticket������1��
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				int id1= Integer.parseInt(data.get(position).get("�������").toString());
				oDao.deleteOrder(id1);
				String trainCode = data.get(position).get("����").toString();
				tDao.addByCode(trainCode);
				Toast.makeText(CancelOrderActivity.this, "��Ʊ�ɹ�", 0).show();
				Intent intent = getIntent();
				intent.setClass(CancelOrderActivity.this, CancelOrderActivity.class);
				startActivity(intent);
			}
		});
	}

	
}
