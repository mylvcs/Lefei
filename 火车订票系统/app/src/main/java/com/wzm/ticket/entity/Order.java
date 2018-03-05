package com.wzm.ticket.entity;

import java.io.Serializable;

public class Order implements Serializable{
	private Integer id;//�������
	private String trainCode;// ����
	private String startStation;// ����վ
	private String startTime;// ����ʱ��
	private String arriveStation;// �ִ�վ
	private String arriveTime;// �ִ�ʱ��
	private String username;//�˿�����
	private long userIdNum;//�˿����֤����
	private double ticketPrice;// Ʊ��
	private String date;// ����
	private String status;// ��Ʊ״̬
	public Order(String trainCode, String startStation, String startTime,
			String arriveStation, String arriveTime, String username,
			long userIdNum, double ticketPrice, String date, String status) {
		super();
		this.trainCode = trainCode;
		this.startStation = startStation;
		this.startTime = startTime;
		this.arriveStation = arriveStation;
		this.arriveTime = arriveTime;
		this.username = username;
		this.userIdNum = userIdNum;
		this.ticketPrice = ticketPrice;
		this.date = date;
		this.status = status;
	}
	public Order() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrainCode() {
		return trainCode;
	}
	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getArriveStation() {
		return arriveStation;
	}
	public void setArriveStation(String arriveStation) {
		this.arriveStation = arriveStation;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getUserIdNum() {
		return userIdNum;
	}
	public void setUserIdNum(long userIdNum) {
		this.userIdNum = userIdNum;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
