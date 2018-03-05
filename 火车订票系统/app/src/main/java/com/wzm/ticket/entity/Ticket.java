package com.wzm.ticket.entity;

import java.io.Serializable;

public class Ticket implements Serializable{
	public String TrainCode;// ����
	public String StartStation;// ����վ
	public String StartTime;// ����ʱ��
	public String ArriveStation;// �ִ�վ
	public String ArriveTime;// �ִ�ʱ��
	public String UseTime;// ��ʱ
	public int TicketNum;// ��Ʊ����
	public int TicketPrice;// Ʊ��
	public String Date;// ����
	
	
	public Ticket() {
		super();
	}
	public Ticket(String trainCode, String startStation, String startTime,
			String arriveStation, String arriveTime, String useTime,
			int ticketNum, int ticketPrice, String date) {
		super();
		TrainCode = trainCode;
		StartStation = startStation;
		StartTime = startTime;
		ArriveStation = arriveStation;
		ArriveTime = arriveTime;
		UseTime = useTime;
		TicketNum = ticketNum;
		TicketPrice = ticketPrice;
		Date = date;
	}
	public String getTrainCode() {
		return TrainCode;
	}
	public void setTrainCode(String trainCode) {
		TrainCode = trainCode;
	}
	public String getStartStation() {
		return StartStation;
	}
	public void setStartStation(String startStation) {
		StartStation = startStation;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getArriveStation() {
		return ArriveStation;
	}
	public void setArriveStation(String arriveStation) {
		ArriveStation = arriveStation;
	}
	public String getArriveTime() {
		return ArriveTime;
	}
	public void setArriveTime(String arriveTime) {
		ArriveTime = arriveTime;
	}
	public String getUseTime() {
		return UseTime;
	}
	public void setUseTime(String useTime) {
		UseTime = useTime;
	}
	public int getTicketNum() {
		return TicketNum;
	}
	public void setTicketNum(int ticketNum) {
		TicketNum = ticketNum;
	}
	public int getTicketPrice() {
		return TicketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		TicketPrice = ticketPrice;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}

	
}
