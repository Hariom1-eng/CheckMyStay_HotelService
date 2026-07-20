package com.HotelService.Services;

import com.HotelService.payload.HotelAutoFillRequest;
import com.HotelService.payload.HotelAutoFillResponse;

public interface AiHotelService {

    HotelAutoFillResponse autoFillHotel(HotelAutoFillRequest request);

}