package com.wzm.ticket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper{
	/*------构造函数的参数只需要传一个context--------*/
	public MyOpenHelper(Context context) {
		super( context, "ticket.db", null, 1);
	}

	// 初次使用软件时创建数据库表
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql_user = "create table user(id Integer primary key autoincrement," +
				"username text,password text,realname text,telNumber Integer,email text,idCard Integer)";
		db.execSQL(sql_user);//创建数据库表格
		
		//创建 车票的数据表，然后初始化一些数据进去（比如建表的时候就直接插入100张车票的信息）
		String sql_ticket  = "create table Ticket(TrainCode text primary key ," +
				"StartStation text,StartTime text,ArriveStation text," +
				"ArriveTime text,UseTime text,TicketNum Integer, TicketPrice real,Date text)";
		
		db.execSQL(sql_ticket);//创建数据库表格
		db.execSQL("insert into Ticket values(?,?,?,?,?,?,?,?,?)", new Object[]{"K81",
				"武昌","01:07","广州","13:42","10小时35分","100","150","2014-9-29"});
		
		db.execSQL("insert into Ticket values(?,?,?,?,?,?,?,?,?)", new Object[]{"T10",
				"武昌","08:07","广州","16:29","8小时22分","100","170","2014-10-10"});
		
		String sql_order = "create table tbl_order(id Integer primary key autoincrement," +
				"trainCode text ,startStation text,startTime text,arriveStation text," +
				"arriveTime text,username text,userIdNum Integer, ticketPrice real,date text,status text)";
		db.execSQL(sql_order);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
