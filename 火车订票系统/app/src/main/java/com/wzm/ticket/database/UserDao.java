package com.wzm.ticket.database;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wzm.ticket.entity.User;

public class UserDao {
	Context context;
	private MyOpenHelper help;
	
	public UserDao(Context context) {
		this.context = context;
		help = new MyOpenHelper((Activity)context);
	}

	//注册用
	public boolean register(User user){
		SQLiteDatabase db = help.getReadableDatabase();
		String sql = "insert into user values(null,?,?,?,?,?,?)";
		Object[] obj = {user.getUsername(),user.getPassword(),user.getRealname(),
				user.getTelNumber(),user.getEmail(),user.getIdCard()};
		db.execSQL(sql, obj);
		db.close();
		return true;
	}
	
	//判断用户名是否已经被注册
	public boolean queryByName(String username){
		//读写方式打开数据库（磁盘不足时会以只读模式打开）
		SQLiteDatabase db = help.getReadableDatabase();
		String sql = "select * from user where username = ?";
		Cursor cursor = db.rawQuery(sql, new String[]{username});
		//判断：验证数据库中是否存在此用户
		if(cursor.moveToFirst()){
			cursor.close();
			db.close();//关闭数据库
			return true;
		}
		db.close();
		return false;
	}
	
	//登录
	public boolean checkLogin(String username,String password){
		//以读写方式打开数据库
		//编写sql语句 根据输入的用户名和密码到数据库查询
		//db.rawQuery 命令执行
		//根据游标是否为真判断  用户是否存在
		//关闭数据库
		SQLiteDatabase db = help.getReadableDatabase();
		String sql = "select * from user where username = ? and password = ? ";
		Cursor cursor = db.rawQuery(sql, new String[]{username,password});
		if(cursor.moveToFirst()){
			cursor.close();
			db.close();
			return true;
		}
		db.close();
		return false;
		
	}
	
	//根据name查找user对象
	public User queryByName1(String name){
		User user = null;
		SQLiteDatabase db = help.getReadableDatabase();
		String sql = "select * from user where username = ?";
		Cursor cursor = db.rawQuery(sql, new String[]{name});
		while(cursor.moveToNext()){
			user = new User();
			user.setUsername(cursor.getString(1));
			user.setPassword(cursor.getString(2));
			user.setRealname(cursor.getString(3));
			user.setTelNumber(cursor.getLong(4));
			user.setEmail(cursor.getString(5));
			user.setIdCard((cursor.getLong(6)));
		}
		return user;
		
	}
}