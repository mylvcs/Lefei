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

	//ע����
	public boolean register(User user){
		SQLiteDatabase db = help.getReadableDatabase();
		String sql = "insert into user values(null,?,?,?,?,?,?)";
		Object[] obj = {user.getUsername(),user.getPassword(),user.getRealname(),
				user.getTelNumber(),user.getEmail(),user.getIdCard()};
		db.execSQL(sql, obj);
		db.close();
		return true;
	}
	
	//�ж��û����Ƿ��Ѿ���ע��
	public boolean queryByName(String username){
		//��д��ʽ�����ݿ⣨���̲���ʱ����ֻ��ģʽ�򿪣�
		SQLiteDatabase db = help.getReadableDatabase();
		String sql = "select * from user where username = ?";
		Cursor cursor = db.rawQuery(sql, new String[]{username});
		//�жϣ���֤���ݿ����Ƿ���ڴ��û�
		if(cursor.moveToFirst()){
			cursor.close();
			db.close();//�ر����ݿ�
			return true;
		}
		db.close();
		return false;
	}
	
	//��¼
	public boolean checkLogin(String username,String password){
		//�Զ�д��ʽ�����ݿ�
		//��дsql��� ����������û��������뵽���ݿ��ѯ
		//db.rawQuery ����ִ��
		//�����α��Ƿ�Ϊ���ж�  �û��Ƿ����
		//�ر����ݿ�
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
	
	//����name����user����
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