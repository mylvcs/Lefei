package com.wzm.ticket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper{
	/*------���캯���Ĳ���ֻ��Ҫ��һ��context--------*/
	public MyOpenHelper(Context context) {
		super( context, "ticket.db", null, 1);
	}

	// ����ʹ�����ʱ�������ݿ��
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql_user = "create table user(id Integer primary key autoincrement," +
				"username text,password text,realname text,telNumber Integer,email text,idCard Integer)";
		db.execSQL(sql_user);//�������ݿ���
		
		//���� ��Ʊ�����ݱ�Ȼ���ʼ��һЩ���ݽ�ȥ�����罨���ʱ���ֱ�Ӳ���100�ų�Ʊ����Ϣ��
		String sql_ticket  = "create table Ticket(TrainCode text primary key ," +
				"StartStation text,StartTime text,ArriveStation text," +
				"ArriveTime text,UseTime text,TicketNum Integer, TicketPrice real,Date text)";
		
		db.execSQL(sql_ticket);//�������ݿ���
		db.execSQL("insert into Ticket values(?,?,?,?,?,?,?,?,?)", new Object[]{"K81",
				"���","01:07","����","13:42","10Сʱ35��","100","150","2014-9-29"});
		
		db.execSQL("insert into Ticket values(?,?,?,?,?,?,?,?,?)", new Object[]{"T10",
				"���","08:07","����","16:29","8Сʱ22��","100","170","2014-10-10"});
		
		String sql_order = "create table tbl_order(id Integer primary key autoincrement," +
				"trainCode text ,startStation text,startTime text,arriveStation text," +
				"arriveTime text,username text,userIdNum Integer, ticketPrice real,date text,status text)";
		db.execSQL(sql_order);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
