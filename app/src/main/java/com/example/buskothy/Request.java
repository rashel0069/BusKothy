package com.example.buskothy;

public class Request {
    //String requestID;
   private String requestBusNumber;
   private String requestDate;
   private String requestTime;
   private  String requestRoute;
   private String requestMobile;
   private String requestPick;
   private int requestSeat;
   private int requestFare;

    public Request(){

    }
    /*String requestID,*/
    public Request(String requestBusNumber, String requestDate, String requestTime, String requestRoute, String requestMobile, String requestPick, int requestSeat, int requestFare) {
        //this.requestID = requestID;
        this.requestBusNumber = requestBusNumber;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.requestRoute = requestRoute;
        this.requestMobile = requestMobile;
        this.requestPick = requestPick;
        this.requestSeat = requestSeat;
        this.requestFare = requestFare;
    }

    public String getRequestBusNumber() {
        return requestBusNumber;
    }

    public void setRequestBusNumber(String requestBusNumber) {
        this.requestBusNumber = requestBusNumber;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestRoute() {
        return requestRoute;
    }

    public void setRequestRoute(String requestRoute) {
        this.requestRoute = requestRoute;
    }

    public String getRequestMobile() {
        return requestMobile;
    }

    public void setRequestMobile(String requestMobile) {
        this.requestMobile = requestMobile;
    }

    public String getRequestPick() {
        return requestPick;
    }

    public void setRequestPick(String requestPick) {
        this.requestPick = requestPick;
    }

    public int getRequestSeat() {
        return requestSeat;
    }

    public void setRequestSeat(int requestSeat) {
        this.requestSeat = requestSeat;
    }

    public int getRequestFare() {
        return requestFare;
    }

    public void setRequestFare(int requestFare) {
        this.requestFare = requestFare;
    }
    /* public String getRequestID() {
        return requestID;
    }

    public String getRequestBusNumber() {
        return requestBusNumber;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getRequestRoute() {
        return requestRoute;
    }

    public String getRequestMobile() {
        return requestMobile;
    }

    public String getRequestPick() {
        return requestPick;
    }

    public String getRequestSeat() {
        return requestSeat;
    }*/
}
