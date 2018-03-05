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
		// 设置当前日期 Calendar.getInstance（）方法
		// 创建 日期对话框对象，五个参数
		// dialog.show()

		myCalendar = Calendar.getInstance(Locale.CHINA);

		OnDateSetListener listener = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// 每次保存设置的日期
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
				myCalendar.get(Calendar.YEAR),// 默认当前年份
				myCalendar.get(Calendar.MONTH),
				myCalendar.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}

	private void setListener() {
		// 日期布局管理器这个控件  绑定事件
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
							new String[]{"车次","发车站","发车时间","抵达站","抵达时间","历时","余票量","票价","日期"},
							new int[]{R.id.TrainCode,R.id.FirstStation,R.id.StartTime,
							R.id.LastStation,R.id.ArriveTime,R.id.UseTime,R.id.leftTicket,R.id.tvPrice,R.id.tv_date2});
					lv.setAdapter(adapter);
					}else{
						Toast.makeText(getApplicationContext(), "您选的车次已经没票啦！！", 0).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), "请选择要改签的日期！", 0).show();
				}
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = getIntent();
				String trainCode = data.get(position).get("车次").toString();
				intent.putExtra("车次", trainCode);
				intent.setClass(Change_order_query_Activity.this, ConfirmChangeActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setText() {
		Intent intent = getIntent();
		StartStation = intent.getStringExtra("发车站");
		From.setText(StartStation);
		ArriveStation = intent.getStringExtra("到达站");
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
