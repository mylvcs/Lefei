package com.example.wangmengyun.Bean;

/**
 * Created by poornima-udacity on 6/26/15.
 */
public class Flight {
    public String departure_City;
    public String arrive_City;
    public String departure_Date;
    public String arrive_Date;
   
    public Flight(String departure_city, String arrive_city, String departure_date, String arrive_date)
    {
        this.departure_City = departure_city;
        this.arrive_City = arrive_city;
        this.departure_Date = departure_date;
        this.arrive_Date = arrive_date;
    }

}