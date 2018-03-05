package com.wzm.ticket.entity;

import java.io.Serializable;

public class Ticket implements Serializable{
	public String TrainCode;// 车次
	public String StartStation;// 出发站
	public String StartTime;// 发车时间
	public String ArriveStation;// 抵达站
	public String ArriveTime;// 抵达时间
	public String UseTime;// 历时
	public int TicketNum;// 余票数量
	public int TicketPrice;// 票价
	public String Date;// 日期
	
	
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
