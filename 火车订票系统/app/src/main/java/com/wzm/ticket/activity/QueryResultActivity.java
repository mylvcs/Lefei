package com.wzm.ticket.activity;

import java.util.List;
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

import com.wzm.ticket.database.TicketDao;
import com.wzm.ticket.util.ExitApplication;

public class QueryResultActivity extends Activity{
	ListView lv;
	Button returnBooking;
	TicketDao tDao = new TicketDao(this);
	List<Map<String,Object>> data = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_result);
		lv = (ListView)findViewById(R.id.query_listview);
		ExitApplication.getInstance().addActivity(this);
		returnBooking = (Button)findViewById(R.id.returnBooking);
		
		returnBooking.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(QueryResultActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		Intent intent = getIntent();
		String startStation = intent.getStringExtra("from");
		String arriveStation = intent.getStringExtra("to");
		String date1 = intent.getStringExtra("date1");
		if(startStation!=null&&arriveStation!=null&&date1!=null){
			data = tDao.queryTicket(startStation, arriveStation, date1);
			if(!data.isEmpty()){
			SimpleAdapter adapter = new SimpleAdapter(this, data,
					R.layout.query_items,
					new String[]{"����","����վ","����ʱ��","�ִ�վ","�ִ�ʱ��","��ʱ","��Ʊ��","Ʊ��","����"},
					new int[]{R.id.TrainCode,R.id.FirstStation,R.id.StartTime,
					R.id.LastStation,R.id.ArriveTime,R.id.UseTime,R.id.leftTicket,R.id.tvPrice,R.id.tv_date2});
			lv.setAdapter(adapter);
			}else{
				Toast.makeText(this, "��ѡ�ĳ��β����ڣ���", 0).show();
			}
		}else{
			Toast.makeText(this, "�����ء�Ŀ�ĵء����ھ�Ϊ��ѡ�", 0).show();
		}
		//�����б����¼�
		//�ж��Ƿ��ѵ�¼,����Ѿ���¼�������������棬������ת����ʾ��¼����
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				Intent intent = getIntent();
				SharedPreferences spf = getSharedPreferences("checkLogin", MODE_PRIVATE);
				boolean isLogin = spf.getBoolean("login", false);//Ĭ�ϳ�ʼ��Ϊ δ��¼
				String username = spf.getString("username", "");
				if(isLogin){
					String trainCode = (String) data.get(position).get("����");
					intent.setClass(QueryResultActivity.this,ConfirmOrdersActivity.class);
					intent.putExtra("trainCode",trainCode );
					intent.putExtra("username",username );
					startActivity(intent);
				}else{
					String trainCode = (String) data.get(position).get("����");
					intent.setClass(QueryResultActivity.this,LoginActivity.class);
					intent.putExtra("trainCode",trainCode );
					intent.putExtra("username",username);
					startActivity(intent);
				}
				
			}
		});
	}
}
