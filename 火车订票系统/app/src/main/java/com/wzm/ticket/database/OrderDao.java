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

	// 新增订单
	public void insertOrder(Order order) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "insert into tbl_order values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {order.getTrainCode(),order.getStartStation(),
				order.getStartTime(),order.getArriveStation(),order.getArriveTime(),
				order.getUsername(),order.getUserIdNum(),order.getTicketPrice(),
				order.getDate(),order.getStatus()};
		db.execSQL(sql,obj);
		db.close();// 关闭数据库
	}

	// 退票，删除订单
	public void deleteOrder(long id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "delete from tbl_order where id = ?";
		db.execSQL(sql,new Object[]{id});
		db.close();// 关闭数据库
	}
	
	// 修改订单（改签）
	public void updateOrder(Ticket t,String status,int id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "update tbl_order set date = ?,startTime=?,arriveTime=?," +
				"trainCode=?,ticketPrice=?,status=?where id = ?";
		db.execSQL(sql,new Object[]{t.getDate(),t.getStartTime(),
				t.getArriveTime(),t.getTrainCode(),t.getTicketPrice(),
				status,id});
		db.close();// 关闭数据库
	}

	//查询 订单
	public ArrayList<Map<String,Object>> queryOrders(String username){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String sql = "select * from tbl_order where username = ?";
		Cursor c = db.rawQuery(sql, new String[]{username});
		ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		while(c.moveToNext()){
			//一个map就相当于listview中的一行列表项，以键值对存储信息
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("订单编号", c.getInt(0));
			map.put("车次", c.getString(1));
			map.put("发车站", c.getString(2));
			map.put("发车时间", c.getString(3));
			map.put("到达站", c.getString(4));
			map.put("到达时间", c.getString(5));
			map.put("乘客姓名", c.getString(6));
			map.put("身份证号", c.getLong(7));
			map.put("票价", c.getInt(8));
			map.put("日期", c.getString(9));
			map.put("车票状态", c.getString(10));
			data.add(map);
			db.close();
		}
		db.close();
		return data;
	}
}
