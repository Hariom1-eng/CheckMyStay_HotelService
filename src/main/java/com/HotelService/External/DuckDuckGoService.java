package com.HotelService.External;

import java.util.List;

import com.HotelService.payload.DuckDuckGoResult;

public interface DuckDuckGoService {

    List<DuckDuckGoResult> searchHotel(
            String hotelName,
            String city);

}