package com.HotelService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.HotelService.payload.HotelAutoFillRequest;
import com.HotelService.payload.HotelAutoFillResponse;
import com.HotelService.Services.AiHotelService;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(
	    origins = {
	        "http://localhost:3000",
	        "http://localhost:5173"
	    },
	    allowCredentials = "true"
	)
public class AiHotelController {

    @Autowired
    private AiHotelService aiHotelService;

    @PostMapping("/auto-fill")
    public HotelAutoFillResponse autoFill(
            @RequestBody HotelAutoFillRequest request) {

        return aiHotelService.autoFillHotel(request);

    }

}