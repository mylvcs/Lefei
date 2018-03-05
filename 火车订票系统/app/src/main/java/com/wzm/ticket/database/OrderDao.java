package com.wzm.ticket.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wzm.ticket.entity.Order;
import com.wzm.ticket.entity.Ticket;

public class OrderDao {
	Context context;
	MyOpenHelper dbHelper;
	
	public OrderDao(Context context) {
		this.context = context;
		dbHelper = new MyOpenHelper((Activity)context);
		
	}

	// ��������
	public void insertOrder(Order order) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "insert into tbl_order values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {order.getTrainCode(),order.getStartStation(),
				order.getStartTime(),order.getArriveStation(),order.getArriveTime(),
				order.getUsername(),order.getUserIdNum(),order.getTicketPrice(),
				order.getDate(),order.getStatus()};
		db.execSQL(sql,obj);
		db.close();// �ر����ݿ�
	}

	// ��Ʊ��ɾ������
	public void deleteOrder(long id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "delete from tbl_order where id = ?";
		db.execSQL(sql,new Object[]{id});
		db.close();// �ر����ݿ�
	}
	
	// �޸Ķ�������ǩ��
	public void updateOrder(Ticket t,String status,int id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "update tbl_order set date = ?,startTime=?,arriveTime=?," +
				"trainCode=?,ticketPrice=?,status=?where id = ?";
		db.execSQL(sql,new Object[]{t.getDate(),t.getStartTime(),
				t.getArriveTime(),t.getTrainCode(),t.getTicketPrice(),
				status,id});
		db.close();// �ر����ݿ�
	}

	//��ѯ ����
	public ArrayList<Map<String,Object>> queryOrders(String username){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String sql = "select * from tbl_order where username = ?";
		Cursor c = db.rawQuery(sql, new String[]{username});
		ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		while(c.moveToNext()){
			//һ��map���൱��listview�е�һ���б���Լ�ֵ�Դ洢��Ϣ
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("�������", c.getInt(0));
			map.put("����", c.getString(1));
			map.put("����վ", c.getString(2));
			map.put("����ʱ��", c.getString(3));
			map.put("����վ", c.getString(4));
			map.put("����ʱ��", c.getString(5));
			map.put("�˿�����", c.getString(6));
			map.put("���֤��", c.getLong(7));
			map.put("Ʊ��", c.getInt(8));
			map.put("����", c.getString(9));
			map.put("��Ʊ״̬", c.getString(10));
			data.add(map);
			db.close();
		}
		db.close();
		return data;
	}
}
