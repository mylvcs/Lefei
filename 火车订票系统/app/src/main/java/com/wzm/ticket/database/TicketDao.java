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
	private Context context;//����context
	public TicketDao(Context context) {
		this.context=context;
		dbHelper = new MyOpenHelper(context);
	}
	
	//��Ʊ������  ����-1 
	public void updateByCode(String trainCode){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		 db.execSQL("update Ticket set TicketNum = TicketNum -1 where TrainCode = ?"
				, new String[]{trainCode});
		db.close();//�ر����ݿ�
	}
	
	//��Ʊ����Ʊ����1
	public void addByCode(String trainCode){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		 db.execSQL("update Ticket set TicketNum = TicketNum +1 where TrainCode = ?"
				, new String[]{trainCode});
		db.close();//�ر����ݿ�
	}
	//���ݳ��β�ѯ 
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
			db.close();//�ر����ݿ�
		}
		db.close();
		return ticket;
		
	}
		
	
	
	// ��ѯ��Ʊ�����ݳ�վ-��վ��ѯ��
	public ArrayList<Map<String, Object>> queryTicket(String startStation,String arriveStation,String date) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();// ����һ�����ݿ����
		Cursor cursor = db.rawQuery("select * from Ticket where StartStation =? and ArriveStation = ? and Date = ?", 
				new String[]{startStation,arriveStation,date});// ִ�в�ѯ������һ��cursor����
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		while (cursor.moveToNext()) {// ָ���ƶ���Ϊ�����ʾ��ֵ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("����", cursor.getString(0));
			map.put("����վ", cursor.getString(1));
			map.put("����ʱ��", cursor.getString(2));
			map.put("�ִ�վ", cursor.getString(3));
			map.put("�ִ�ʱ��", cursor.getString(4));
			map.put("��ʱ", cursor.getString(5));
			map.put("��Ʊ��", cursor.getInt(6));
			map.put("Ʊ��", cursor.getInt(7));
			map.put("����", cursor.getString(8));
			data.add(map);//��ÿһ����ʾ������ ��-ֵ�� ��ʽ�洢��Map��
			db.close();
		}
		db.close();
		return data;
	}
}
