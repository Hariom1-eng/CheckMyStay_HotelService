package com.HotelService.payload;

public class HotelAutoFillRequest {

    private String hotelName;
    private String city;

    public HotelAutoFillRequest() {
    }

    public HotelAutoFillRequest(String hotelName, String city) {
        this.hotelName = hotelName;
        this.city = city;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}