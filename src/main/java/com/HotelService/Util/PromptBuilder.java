package com.HotelService.Util;

public class PromptBuilder {

    public static String buildPrompt(
            String hotelName,
            String city,
            String searchResults) {

        return """
You are an AI Hotel Information Extraction Engine.

Your job is to extract VERIFIED hotel information.

Hotel Name:
%s

City:
%s

Below are DuckDuckGo search results and website information.

========================================

%s

========================================

STRICT RULES

1. Return ONLY JSON.

2. Do NOT write explanations.

3. Do NOT write markdown.

4. Do NOT use ```json.

5. Do NOT use ```.

6. Never invent information.

7. If a field is unavailable, return:

"" (empty string)

or

[] (empty array)

8. Use the search results only.

9. Return valid JSON only.

Schema:

{
  "name":"",
  "brand":"",
  "category":"",
  "starRating":0,
  "description":"",
  "address":"",
  "area":"",
  "city":"",
  "district":"",
  "state":"",
  "country":"",
  "pincode":"",
  "latitude":"",
  "longitude":"",
  "phone":"",
  "alternatePhone":"",
  "email":"",
  "website":"",
  "checkIn":"",
  "checkOut":"",
  "pricePerNight":0,
  "currency":"INR",
  "thumbnail":"",
  "mapImage":"",
  "googleMapLink":"",
  "popularFor":"",
  "amenities":[],
  "images":[]
}

Return ONLY JSON.

""".formatted(
                hotelName,
                city,
                searchResults);

    }

}