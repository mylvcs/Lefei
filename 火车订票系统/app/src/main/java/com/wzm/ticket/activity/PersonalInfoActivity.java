package com.wzm.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wzm.ticket.database.UserDao;
import com.wzm.ticket.entity.User;
import com.wzm.ticket.util.ExitApplication;

public class PersonalInfoActivity extends Activity{
	TextView tv_name,tv_pass,tv_realname,tv_tel,tv_email,tv_idNum;
	Button info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.peronal_info);
		ExitApplication.getInstance().addActivity(this);
		
		findView();
		setText();
		setListener();
	}
	private void setListener() {
		info.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = getIntent();
				intent.setClass(PersonalInfoActivity.this, More2Activity.class);
				startActivity(intent);
			}
		});
	}
	private void findView() {
		tv_name = (TextView)findViewById(R.id.person_username);
		tv_pass = (TextView)findViewById(R.id.person_pass);
		tv_realname = (TextView)findViewById(R.id.person_realname);
		tv_tel = (TextView)findViewById(R.id.person_tel);
		tv_email = (TextView)findViewById(R.id.person_email);
		tv_idNum = (TextView)findViewById(R.id.person_idNum);
		info = (Button)findViewById(R.id.return12306);
	}
	
	private void setText() {
		Intent intent = getIntent();
		String name = intent.getStringExtra("username");
		//根据name到数据库查找该user
		UserDao uDao = new UserDao(this);
		User user = uDao.queryByName1(name);
		tv_name.setText(user.getUsername());
		tv_pass.setText(user.getPassword());
		tv_realname.setText(user.getRealname());
		tv_tel.setText(user.getTelNumber()+"");
		tv_email.setText(user.getEmail());
		tv_idNum.setText(user.getIdCard()+"");
	}
}
