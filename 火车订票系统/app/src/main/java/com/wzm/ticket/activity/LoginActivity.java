package com.wzm.ticket.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wzm.ticket.database.UserDao;
import com.wzm.ticket.util.ExitApplication;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginActivity extends Activity{
	private EditText etName;
	private EditText etPass;
	private Button logins;
	private Button register;
	private Button moreInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ExitApplication.getInstance().addActivity(this);
		setViews();
		setListener();
	}
	
	private void setViews() {
		etName = (EditText)findViewById(R.id.etUsername);
		etPass = (EditText)findViewById(R.id.etPwd);
		logins = (Button)findViewById(R.id.btLogin);
		register = (Button)findViewById(R.id.btRegist);
		moreInfo = (Button)findViewById(R.id.textView_moreFun);
	}
	
	private void setListener() {
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
		
		moreInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,MoreActivity.class);
				startActivity(intent);
			}
		});
		
		logins.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String username = etName.getText().toString();
				String password = etPass.getText().toString();
				//获取用户输入的内容
				//判断非空
				//调用userdao的登录验证方法，如果为真则存在用户，否则该用户不存在，提示注册
				if(!username.isEmpty()&&!password.isEmpty()){
					UserDao uDao = new UserDao(LoginActivity.this);
					boolean flag = uDao.checkLogin(username, password);
					if(flag){
						Toast.makeText(LoginActivity.this, "登录成功", 0).show();
						
						SharedPreferences.Editor editor = getSharedPreferences("checkLogin",
								MODE_PRIVATE).edit();
						editor.putString("username", username);
						editor.putString("password", password);
						editor.putBoolean("login", true);
						editor.commit();
						Intent intent = getIntent();
						String tabId = intent.getStringExtra("tabId");
						if(tabId.equals("tab1")){
							intent.setClass(LoginActivity.this, ConfirmOrdersActivity.class);
							intent.putExtra("username", username);
							startActivity(intent);
						
						}else if(tabId.equals("tab2")){
							intent.setClass(LoginActivity.this, OrderManageActivity.class);
							intent.putExtra("username", username);
							startActivity(intent);
						}else if(tabId.equals("tab3")){
							intent.setClass(LoginActivity.this, My12306Activity.class);
							intent.putExtra("username", username);
							startActivity(intent);
						}else if(tabId.equals("tab4")){
							intent.setClass(LoginActivity.this, More2Activity.class);
							intent.putExtra("username", username);
							startActivity(intent);
						}else{
							intent.setClass(LoginActivity.this, More2Activity.class);
							intent.putExtra("username", username);
							startActivity(intent);
						}
					}else{
						
						Toast.makeText(LoginActivity.this, "登录失败", 0).show();
					}
				}else{
					Toast.makeText(LoginActivity.this, "用户名和密码不能为空", 0).show();
				}
			}
		});
		
	}

}
