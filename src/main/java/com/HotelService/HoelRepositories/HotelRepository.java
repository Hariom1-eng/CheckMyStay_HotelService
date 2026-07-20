package com.HotelService.HoelRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HotelService.Entites.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String>{

	
	
}
