package com.HotelService.Services.Imple;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.HotelService.payload.DuckDuckGoResult;
import com.HotelService.External.DuckDuckGoService;

@Service
public class DuckDuckGoServiceImple
        implements DuckDuckGoService {

    @Override
    public List<DuckDuckGoResult> searchHotel(
            String hotelName,
            String city) {

        List<DuckDuckGoResult> results =
                new ArrayList<>();

        try {

            String query =
                    URLEncoder.encode(
                    		
                            hotelName
                            + " "
                            + city
                            + " official hotel website address phone email amenities",
                            StandardCharsets.UTF_8);

            String url =
                    "https://html.duckduckgo.com/html/?q="
                            + query;

            Document document =
                    Jsoup.connect(url)
                            .userAgent("Mozilla/5.0")
                            .timeout(10000)
                            .get();

            Elements elements =
                    document.select(".result");

            for (Element element : elements) {

                String title =
                        element.select(".result__title")
                                .text();

                String link =
                        element.select(".result__url")
                                .text();

                String description =
                        element.select(".result__snippet")
                                .text();

                results.add(

                        new DuckDuckGoResult(

                                title,

                                link,

                                description

                        )
                );

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return results;

    }

}