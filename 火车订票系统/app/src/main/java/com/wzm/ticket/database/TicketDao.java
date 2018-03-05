package com.wzm.ticket.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.wzm.ticket.entity.Ticket;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class TicketDao {
	MyOpenHelper dbHelper;
	private Context context;//传入context
	public TicketDao(Context context) {
		this.context=context;
		dbHelper = new MyOpenHelper(context);
	}
	
	//车票卖出后  数量-1 
	public void updateByCode(String trainCode){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		 db.execSQL("update Ticket set TicketNum = TicketNum -1 where TrainCode = ?"
				, new String[]{trainCode});
		db.close();//关闭数据库
	}
	
	//退票，车票增加1
	public void addByCode(String trainCode){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		 db.execSQL("update Ticket set TicketNum = TicketNum +1 where TrainCode = ?"
				, new String[]{trainCode});
		db.close();//关闭数据库
	}
	//根据车次查询 
	public Ticket queryByCode(String trainCode){
		Ticket ticket=null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from Ticket where TrainCode= ?", 
				new String[]{trainCode});
		while(cursor.moveToNext()){
			ticket = new Ticket();
			ticket.setTrainCode(cursor.getString(0));
			ticket.setStartStation(cursor.getString(1));
			ticket.setStartTime(cursor.getString(2));
			ticket.setArriveStation(cursor.getString(3));
			ticket.setArriveTime(cursor.getString(4));
			ticket.setUseTime(cursor.getString(5));
			ticket.setTicketPrice(cursor.getInt(7));
			ticket.setDate(cursor.getString(8));
			cursor.close();
			db.close();//关闭数据库
		}
		db.close();
		return ticket;
		
	}
		
	
	
	// 查询余票（根据车站-车站查询）
	public ArrayList<Map<String, Object>> queryTicket(String startStation,String arriveStation,String date) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();// 返回一个数据库对象
		Cursor cursor = db.rawQuery("select * from Ticket where StartStation =? and ArriveStation = ? and Date = ?", 
				new String[]{startStation,arriveStation,date});// 执行查询，返回一个cursor对象
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		while (cursor.moveToNext()) {// 指针移动，为真则表示有值
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("车次", cursor.getString(0));
			map.put("发车站", cursor.getString(1));
			map.put("发车时间", cursor.getString(2));
			map.put("抵达站", cursor.getString(3));
			map.put("抵达时间", cursor.getString(4));
			map.put("历时", cursor.getString(5));
			map.put("余票量", cursor.getInt(6));
			map.put("票价", cursor.getInt(7));
			map.put("日期", cursor.getString(8));
			data.add(map);//将每一行显示数据以 键-值对 形式存储到Map中
			db.close();
		}
		db.close();
		return data;
	}
}
