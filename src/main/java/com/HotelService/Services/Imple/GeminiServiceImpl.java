package com.HotelService.Services.Imple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.HotelService.Config.GeminiConfig;
import com.HotelService.External.GeminiService;

@Service
public class GeminiServiceImpl implements GeminiService {

    @Autowired
    private GeminiConfig geminiConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String extractHotelInformation(String prompt) {

        try {

            String url = geminiConfig.getApiUrl();
//                    + "?key="
//                    + geminiConfig.getApiKey();

            Map<String, Object> part = new HashMap<>();
            part.put("text", prompt);

            Map<String, Object> content = new HashMap<>();
            content.put("parts", List.of(part));

            Map<String, Object> body = new HashMap<>();
            body.put("contents", List.of(content));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-goog-api-key", geminiConfig.getApiKey());

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(body, headers);
           
            ResponseEntity<String> response =
                    restTemplate.postForEntity(
                            url,
                            entity,
                            String.class);

            JsonNode root =
                    mapper.readTree(response.getBody());

            String geminiResponse = root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            geminiResponse = geminiResponse
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();
            
            System.out.println("API URL = " + geminiConfig.getApiUrl());
            System.out.println("API KEY = " + geminiConfig.getApiKey());

            return geminiResponse;

        } catch (Exception e) {

        	 System.out.println("========== GEMINI ERROR ==========");
        	    e.printStackTrace();

        	    if (e instanceof org.springframework.web.client.HttpStatusCodeException ex) {
        	        System.out.println(ex.getResponseBodyAsString());
        	    }


            return "";

        }

    }

}