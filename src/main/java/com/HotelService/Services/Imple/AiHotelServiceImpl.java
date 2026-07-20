package com.HotelService.Services.Imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.HotelService.External.GeminiService;
import com.HotelService.Util.PromptBuilder;
import com.HotelService.payload.DuckDuckGoResult;
import com.HotelService.payload.HotelAutoFillRequest;
import com.HotelService.payload.HotelAutoFillResponse;
import com.HotelService.External.DuckDuckGoService;
import com.HotelService.Services.AiHotelService;

@Service
public class AiHotelServiceImpl implements AiHotelService {
	
	@Autowired
	private DuckDuckGoService duckDuckGoService;

	@Autowired
	private GeminiService geminiService;
    
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public HotelAutoFillResponse autoFillHotel(
	        HotelAutoFillRequest request) {

	    try {

	        List<DuckDuckGoResult> searchResults =

	                duckDuckGoService.searchHotel(

	                        request.getHotelName(),

	                        request.getCity());

	        StringBuilder rawData =
	                new StringBuilder();

	        for (DuckDuckGoResult result : searchResults) {

	            rawData.append("Title : ")
	                    .append(result.getTitle())
	                    .append("\n");

	            rawData.append("Website : ")
	                    .append(result.getUrl())
	                    .append("\n");

	            rawData.append("Description : ")
	                    .append(result.getDescription())
	                    .append("\n\n");

	        }

	        String prompt =

	                PromptBuilder.buildPrompt(

	                        request.getHotelName(),

	                        request.getCity(),

	                        rawData.toString());

	        String geminiResponse =

	                geminiService.extractHotelInformation(

	                        prompt);

	        System.out.println("Gemini Response:");

	        System.out.println(geminiResponse);

	        geminiResponse = geminiResponse
	                .replace("```json", "")
	                .replace("```", "")
	                .trim();

	        HotelAutoFillResponse response =

	                objectMapper.readValue(

	                        geminiResponse,

	                        HotelAutoFillResponse.class);
	     // Optional fallback values
	        if (response.getName() == null || response.getName().isBlank()) {
	            response.setName(request.getHotelName());
	        }

	        if (response.getCity() == null || response.getCity().isBlank()) {
	            response.setCity(request.getCity());
	        }
	        return response;

	    }

	    catch (Exception e) {

	        e.printStackTrace();

	        return new HotelAutoFillResponse();

	    }

	}

}