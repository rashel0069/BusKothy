package com.example.buskothy;

public class BusSchedule {
    String todaybusId;
    String todaybusNumber;
    String todaybusDate;
    String todaybusRoute;
    String todaybusTime;

    public BusSchedule(){

    }

    public BusSchedule(String todaybusId, String todaybusNumber, String todaybusDate, String todaybusRoute, String todaybusTime) {
        this.todaybusId = todaybusId;
        this.todaybusNumber = todaybusNumber;
        this.todaybusDate = todaybusDate;
        this.todaybusRoute = todaybusRoute;
        this.todaybusTime = todaybusTime;
    }

    public String getTodaybusId() {
        return todaybusId;
    }

    public String getTodaybusNumber() {
        return todaybusNumber;
    }

    public String getTodaybusDate() {
        return todaybusDate;
    }

    public String getTodaybusRoute() {
        return todaybusRoute;
    }

    public String getTodaybusTime() {
        return todaybusTime;
    }
}

