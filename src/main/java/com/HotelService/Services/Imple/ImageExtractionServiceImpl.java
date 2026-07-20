package com.HotelService.Services.Imple;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.HotelService.External.ImageExtractionService;

@Service
public class ImageExtractionServiceImpl implements ImageExtractionService {

    @Override
    public List<String> extractImages(String websiteUrl) {

        Set<String> imageUrls = new LinkedHashSet<>();

        try {

            Document document = Jsoup.connect(websiteUrl)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            Elements images = document.select("img");

            for (Element image : images) {

                String imageUrl = image.absUrl("src");

                if (imageUrl == null || imageUrl.isBlank()) {
                    continue;
                }

                imageUrl = imageUrl.toLowerCase();

                if (isValidHotelImage(imageUrl)) {
                    imageUrls.add(imageUrl);
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return new ArrayList<>(imageUrls);

    }

    /**
     * Remove logos, icons, social images etc.
     */
    private boolean isValidHotelImage(String url) {

        // Ignore logos/icons
        if (url.contains("logo")
                || url.contains("icon")
                || url.contains("favicon")
                || url.contains("facebook")
                || url.contains("instagram")
                || url.contains("twitter")
                || url.contains("linkedin")
                || url.contains("youtube")
                || url.contains("loader")
                || url.contains("avatar")
                || url.contains("profile")
                || url.contains("banner-small")
                || url.contains("ads")
                || url.contains("advertisement")) {

            return false;

        }

        // Accept only image extensions
        if (!(url.endsWith(".jpg")
                || url.endsWith(".jpeg")
                || url.endsWith(".png")
                || url.endsWith(".webp"))) {

            return false;

        }

        return true;

    }

}