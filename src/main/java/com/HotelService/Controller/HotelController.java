package com.HotelService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestBody 

import com.HotelService.Entites.Hotel;
import com.HotelService.Services.HotelServices;



import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.HotelService.External.CloudinaryService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private  HotelServices hotelService;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
//	create 
	@PostMapping(
	        value = "/create",
	        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<Hotel> createHotel(

	        @RequestParam String name,
	        @RequestParam(required = false) String brand,
	        @RequestParam(required = false) String category,
	        @RequestParam Integer starRating,
	        @RequestParam(required = false) String description,

	        @RequestParam String address,
	        @RequestParam(required = false) String area,
	        @RequestParam String city,
	        @RequestParam(required = false) String district,
	        @RequestParam String state,
	        @RequestParam String country,
	        @RequestParam(required = false) String pincode,

	        @RequestParam(required = false) String latitude,
	        @RequestParam(required = false) String longitude,

	        @RequestParam String phone,
	        @RequestParam(required = false) String alternatePhone,
	        @RequestParam String email,
	        @RequestParam(required = false) String website,

	        @RequestParam String checkIn,
	        @RequestParam String checkOut,

	        @RequestParam Double pricePerNight,
	        @RequestParam String currency,

	        @RequestParam(required = false) String googleMapLink,
	        @RequestParam(required = false) String mapImage,
	        @RequestParam(required = false) String popularFor,
	        @RequestParam(required = false) String status,

	        @RequestParam(required = false) String amenities,

	        @RequestParam(required = false) String thumbnail,

	        @RequestParam(required = false) MultipartFile thumbnailFile,

	        @RequestParam(required = false) MultipartFile[] galleryImages

	) {

	    Hotel hotel = new Hotel();

	    hotel.setName(name);
	    hotel.setBrand(brand);
	    hotel.setCategory(category);
	    hotel.setStarRating(starRating);
	    hotel.setDescription(description);

	    hotel.setAddress(address);
	    hotel.setArea(area);
	    hotel.setCity(city);
	    hotel.setDistrict(district);
	    hotel.setState(state);
	    hotel.setCountry(country);
	    hotel.setPincode(pincode);

	    hotel.setLatitude(latitude);
	    hotel.setLongitude(longitude);

	    hotel.setPhone(phone);
	    hotel.setAlternatePhone(alternatePhone);
	    hotel.setEmail(email);
	    hotel.setWebsite(website);

	    hotel.setCheckIn(checkIn);
	    hotel.setCheckOut(checkOut);

	    hotel.setPricePerNight(pricePerNight);
	    hotel.setCurrency(currency);

	    hotel.setGoogleMapLink(googleMapLink);
	    hotel.setMapImage(mapImage);
	    hotel.setPopularFor(popularFor);
	    hotel.setStatus(status);

	    // Amenities
	    if (amenities != null && !amenities.isBlank()) {

	        hotel.setAmenities(
	                Arrays.stream(amenities.split(","))
	                        .map(String::trim)
	                        .toList()
	        );

	    }

	    // Thumbnail

	    if (thumbnailFile != null && !thumbnailFile.isEmpty()) {

	        String thumbnailUrl =
	                cloudinaryService.uploadFile(thumbnailFile);

	        hotel.setThumbnail(thumbnailUrl);

	    } else {

	        hotel.setThumbnail(thumbnail);

	    }

	    // Gallery Images

	    if (galleryImages != null && galleryImages.length > 0) {

	        List<String> urls = new ArrayList<>();

	        for (MultipartFile file : galleryImages) {

	            urls.add(
	                    cloudinaryService.uploadFile(file)
	            );

	        }

	        hotel.setImages(urls);

	    }

	    Hotel savedHotel = hotelService.create(hotel);

	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(savedHotel);

	}
//	getSingle
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){

	  

		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
		}
	
	
	
	
//	get  all
	@GetMapping("all")
	public ResponseEntity<List<Hotel>>getAll(){
		return ResponseEntity.ok(hotelService.getAll());
	}
	
	
//	Import multiple hotels data into the  Backend JSON 
	
	@PostMapping("/import")
	public ResponseEntity<List<Hotel>> importHotels(@RequestBody List<Hotel> hotels) {

	    List<Hotel> savedHotels = hotelService.importHotels(hotels);

	    return ResponseEntity.ok(savedHotels);
	}
	
//	Pagination concept 
	
	@GetMapping
	public ResponseEntity<Page<Hotel>> getHotels(

	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "9") int size
	) {

	    return ResponseEntity.ok(
	            hotelService.getAllHotels(page, size)
	    );
	}
	
	
	// ---> NEW: Update Hotel Endpoint <---
		@PutMapping("/{hotelId}")
		public ResponseEntity<Hotel> updateHotel(
				@PathVariable String hotelId, 
				@RequestBody Hotel hotelDetails
		) {
			Hotel updatedHotel = hotelService.update(hotelId, hotelDetails);
			return ResponseEntity.ok(updatedHotel);
		}
		
		// ---> NEW: Batch Update Endpoint <---
		@PutMapping("/batch-update")
		public ResponseEntity<List<Hotel>>updateMultiple(@RequestBody List<Hotel> hotelsToUpdate) {
		    List<Hotel> updatedHotels = hotelService.updateMultiple(hotelsToUpdate);
		    return ResponseEntity.ok(updatedHotels);
		}
		
		
		
		// Delete Hotel
		@DeleteMapping("/delete/{hotelId}")
		public ResponseEntity<String> deleteHotel(
		        @PathVariable String hotelId
		) {

		    hotelService.delete(hotelId);

		    return ResponseEntity.ok("Hotel deleted successfully");
		}
}
