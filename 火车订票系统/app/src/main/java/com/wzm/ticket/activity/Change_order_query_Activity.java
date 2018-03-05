package com.wzm.ticket.activity;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wzm.ticket.database.OrderDao;
import com.wzm.ticket.database.TicketDao;
import com.wzm.ticket.entity.Order;
import com.wzm.ticket.entity.Ticket;
import com.wzm.ticket.util.ExitApplication;

public class Change_order_query_Activity extends Activity {
	private Button btSearch;
	private TextView tvD, tvM, tvY, From, To;
	private LinearLayout ll;
	private ListView lv;
	private String StartStation;
	private String ArriveStation;
	private String date, date1;
	private Calendar myCalendar;
	OrderDao oDao = new OrderDao(this);
	TicketDao tDao = new TicketDao(this);
	List<Map<String,Object>> data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_query);
		ExitApplication.getInstance().addActivity(this);

		findViews();
		setText();
		setListener();
	}

	private void selectDate() {
		// ���õ�ǰ���� Calendar.getInstance��������
		// ���� ���ڶԻ�������������
		// dialog.show()

		myCalendar = Calendar.getInstance(Locale.CHINA);

		OnDateSetListener listener = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// ÿ�α������õ�����
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				tvY.setText(year + "");
				tvM.setText((monthOfYear + 1) + "");
				tvD.setText(dayOfMonth + "");
				date1 = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
			}
		};

		DatePickerDialog dialog = new DatePickerDialog(
				this, 
				listener,
				myCalendar.get(Calendar.YEAR),// Ĭ�ϵ�ǰ���
				myCalendar.get(Calendar.MONTH),
				myCalendar.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}

	private void setListener() {
		// ���ڲ��ֹ���������ؼ�  ���¼�
		ll.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				selectDate();
			}
		});
		
		btSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(date1!=null){
					data = tDao.queryTicket(StartStation, ArriveStation, date1);
					if(!data.isEmpty()){
					SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data,
							R.layout.query_items,
							new String[]{"����","����վ","����ʱ��","�ִ�վ","�ִ�ʱ��","��ʱ","��Ʊ��","Ʊ��","����"},
							new int[]{R.id.TrainCode,R.id.FirstStation,R.id.StartTime,
							R.id.LastStation,R.id.ArriveTime,R.id.UseTime,R.id.leftTicket,R.id.tvPrice,R.id.tv_date2});
					lv.setAdapter(adapter);
					}else{
						Toast.makeText(getApplicationContext(), "��ѡ�ĳ����Ѿ�ûƱ������", 0).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), "��ѡ��Ҫ��ǩ�����ڣ�", 0).show();
				}
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = getIntent();
				String trainCode = data.get(position).get("����").toString();
				intent.putExtra("����", trainCode);
				intent.setClass(Change_order_query_Activity.this, ConfirmChangeActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setText() {
		Intent intent = getIntent();
		StartStation = intent.getStringExtra("����վ");
		From.setText(StartStation);
		ArriveStation = intent.getStringExtra("����վ");
		To.setText(ArriveStation);
	}

	private void findViews() {
		From = (TextView) findViewById(R.id.change_from);
		To = (TextView) findViewById(R.id.change_to);
		ll = (LinearLayout) findViewById(R.id.linearLayout_changeDate);
		tvY = (TextView) findViewById(R.id.change_tvYear);
		tvM = (TextView) findViewById(R.id.change_tvMouth);
		tvD = (TextView) findViewById(R.id.change_tvDay);
		btSearch = (Button) findViewById(R.id.change_query);
		lv = (ListView) findViewById(R.id.change_listview);
	}

}
