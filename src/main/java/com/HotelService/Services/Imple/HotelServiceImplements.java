package com.HotelService.Services.Imple;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HotelService.Entites.Hotel;
import com.HotelService.Exception.ResourceNotFoundException;
import com.HotelService.HoelRepositories.HotelRepository;
import com.HotelService.Services.HotelServices;

@Service
public class HotelServiceImplements  implements HotelServices{
  
	@Autowired
	private  HotelRepository hotelRepository;
	
	
	@Override
	public Hotel create(Hotel hotel) {
//		this UUID  is used to generates the random  hotel Id
	   String hotelID=	UUID.randomUUID().toString();
	   hotel.setId(hotelID);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		// TODO Auto-generated method stub
		return  hotelRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Hotel with given Id is not  found"));
	}

	@Override
	public Hotel get(Hotel hotelId) {
		
		return null;
	}

	@Override
	public List<Hotel> importHotels(List<Hotel> hotels) {

	    hotels.forEach(hotel -> {
	        if (hotel.getId() == null || hotel.getId().isEmpty()) {
	            hotel.setId(UUID.randomUUID().toString());
	        }
	    });

	    return hotelRepository.saveAll(hotels);
	}
	
	
	@Override
	public Page<Hotel> getAllHotels(int page, int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    return hotelRepository.findAll(pageable);
	
}
	
	// ---> NEW: Update implementation <---
		@Override
		public Hotel update(String hotelId, Hotel hotelDetails) {
			// 1. Find the existing hotel or throw an error
			Hotel existingHotel = hotelRepository.findById(hotelId)
					.orElseThrow(() -> new ResourceNotFoundException("Hotel with given Id is not found"));

			// 2. Update the fields
			// Update these setters based on your actual Hotel entity attributes
			existingHotel.setName(hotelDetails.getName());
			existingHotel.setDescription(hotelDetails.getDescription());
			existingHotel.setCategory(hotelDetails.getCategory());
			existingHotel.setStarRating(hotelDetails.getStarRating());
			existingHotel.setImages(hotelDetails.getImages()); 

			// 3. Save and return the updated hotel
			return hotelRepository.save(existingHotel);
		}
		
		@Override
		public Hotel addHotelImage(String hotelId, String base64Image) {
			Hotel hotel = hotelRepository.findById(hotelId)
					.orElseThrow(() -> new ResourceNotFoundException("Hotel with given Id is not found"));
			
			if (base64Image != null && !base64Image.isEmpty()) {
				hotel.getImages().add(base64Image);
				return hotelRepository.save(hotel);
			}
			
			return hotel;
		}
		
		@Override
		public List<Hotel> updateMultiple(List<Hotel> hotelsToUpdate) {
			List<Hotel> hotelsToSave = new ArrayList<>();

			for (Hotel updateData : hotelsToUpdate) {
				// Find the existing hotel using the ID provided in the JSON payload
				Hotel existingHotel = hotelRepository.findById(updateData.getId())
						.orElseThrow(() -> new ResourceNotFoundException("Hotel with ID " + updateData.getId() + " not found"));

				// Map the fields you want to update (feel free to add more like price, address, etc.)
				existingHotel.setName(updateData.getName());
				existingHotel.setDescription(updateData.getDescription());
				existingHotel.setCategory(updateData.getCategory());
				existingHotel.setStarRating(updateData.getStarRating());
				existingHotel.setImages(updateData.getImages()); 

				// Add to our list to save later
				hotelsToSave.add(existingHotel);
			}

			// Save everything at once for better database performance
			return hotelRepository.saveAll(hotelsToSave);
		}
		
		
		@Override
		public void delete(String hotelId) {

		    Hotel hotel = hotelRepository.findById(hotelId)
		            .orElseThrow(() ->
		                    new ResourceNotFoundException(
		                            "Hotel with ID " + hotelId + " not found"
		                    ));

		    hotelRepository.delete(hotel);
		}
}
