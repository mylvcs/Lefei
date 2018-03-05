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
 * ��ǩ�������غ�Ŀ�ĵض������Ը��ģ�ֻ���޸�����ʱ��򳵴Ρ�
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
		// ��ѯ������Ϣ
		data = oDao.queryOrders(username);
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.order_items, 
				new String[] { "�������", "����", "����վ","����ʱ��", "����վ", "����ʱ��", "�˿�����", "���֤��", "Ʊ��","����","��Ʊ״̬" },
				new int[] { R.id.orders_id2, R.id.orders_stationCode,
						R.id.orders_start_station, R.id.orders_start_TIME,
						R.id.orders_arrive_station1, R.id.orders_arrive_TIME,
						R.id.orders_username, R.id.orders_idNumber,
						R.id.orders_costPrice,R.id.orders_date1,R.id.status_items });
		for(int i=0;i<data.size();i++){
			String status = (String) data.get(i).get("��Ʊ״̬");
			if(status.equals("��֧��"))
				lv.setAdapter(adapter);
		}

		//������ҳ
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
				String fromStation =data.get(position).get("����վ").toString();
				String toStation =data.get(position).get("����վ").toString();
				String username =data.get(position).get("�˿�����").toString();
				int ids =(Integer) data.get(position).get("�������");
				intent.putExtra("����վ", fromStation);
				intent.putExtra("����վ", toStation);
				intent.putExtra("�������", ids);
				intent.putExtra("�˿�����", username);
				startActivity(intent);
			}
		});
	}

	
}
