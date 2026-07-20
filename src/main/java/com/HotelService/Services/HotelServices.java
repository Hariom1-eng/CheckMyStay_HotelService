package com.HotelService.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HotelService.Entites.Hotel;

public interface HotelServices {

	//create  
	
	Hotel create (Hotel hotel);
	
	//getall
	
	List<Hotel> getAll();
	
	//get single
	
	Hotel get(String id);

//	Extra method
	
	Hotel get(Hotel hotelId);
	
//	Import multiple hotels simultaneously
	
	List<Hotel> importHotels(List<Hotel> hotels);
	
	
//	 Pagination concept 
	
	Page<Hotel> getAllHotels(int page, int size);
	
	// ---> NEW: Update hotel <---
		Hotel update(String hotelId, Hotel hotelDetails);
		
		Hotel addHotelImage(String hotelId, String base64Image);
	
		List<Hotel> updateMultiple(List<Hotel> hotelsToUpdate);
		
		// Delete hotel by ID
		void delete(String hotelId);
}
