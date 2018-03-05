package com.wzm.ticket.activity;

import java.util.Calendar;
import java.util.Locale;

import com.wzm.ticket.util.ExitApplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BookingActivity extends Activity{
	private Button btSearch;
	private TextView tvD,tvM,tvY;
	private Spinner spFrom,spTo;
	private LinearLayout ll;
	private Calendar mycalendar;
	private String StartStation;
	private String ArriveStation;
	private String date,date1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.booking);
		ExitApplication.getInstance().addActivity(this);
		findView();
		setListeners();
	}
	
	private void findView() {
		tvY = (TextView) findViewById(R.id.tvYear);
		tvM = (TextView) findViewById(R.id.tvMouth);
		tvD = (TextView) findViewById(R.id.tvDay);
		btSearch = (Button) findViewById(R.id.button1);
		//lv = (ListView) findViewById(R.id.queryResult);//显示车票查询结果的listview？
		ll = (LinearLayout) findViewById(R.id.linearLayout_startDate);//日期选择
		spFrom = (Spinner)findViewById(R.id.spinner1);
		spTo = (Spinner)findViewById(R.id.spinner2);
	}

	/*-----------------设置 监听器------------------*/
	private void setListeners() {
		
		//日期布局管理器 绑定单击事件
		ll.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				selectDate();
			}
		});
		
		//单击  查询按钮 触发事件 
		btSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
					Intent intent = getIntent();
					intent.setClass(BookingActivity.this,QueryResultActivity.class);
					intent.putExtra("from", StartStation);
					intent.putExtra("to", ArriveStation);
					intent.putExtra("date1", date1);
					startActivity(intent);
			}
		});
		
		//起始站
		spFrom.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				String[] station = getResources().getStringArray(R.array.station_choice);
				int index = (int)id;
				StartStation = station[index];
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		//抵达站
		spTo.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				String[] station = getResources().getStringArray(R.array.station_choice);
				int index = (int)id;
				ArriveStation = station[index];
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	/**
	 * 1， 为日期对话框设置 监听事件：OnDateSetListener()，当日期选择的话，会触发这个事件
	 * 2，复写 onDateSet（）方法
	 */
	protected void selectDate() {
		// 设置当前日期
		mycalendar = Calendar.getInstance(Locale.CHINA);
		DatePickerDialog dialog = new DatePickerDialog(this,
				new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// 每次保存设置的日期  
						mycalendar.set(Calendar.YEAR, year);  
						mycalendar.set(Calendar.MONTH, monthOfYear);  
						mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
						tvY.setText(year+ "");
						tvM.setText((monthOfYear+1) + "");
						tvD.setText(dayOfMonth + "");
						/*date1 = new String(new StringBuilder().append(year)
								.append("-").append(monthOfYear + 1)// 得到的月份+1，因为从0开始
								.append("-").append(dayOfMonth));*/
						date1 = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
					}
				},
				mycalendar.get(Calendar.YEAR),//默认当前日期 
				mycalendar.get(Calendar.MONTH),
				mycalendar.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}
}
