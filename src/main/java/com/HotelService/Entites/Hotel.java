package com.HotelService.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;


@Entity
@Table(name="Hotel")
public class Hotel {

    @Id
    private String id;
    private String name;
    private String brand;
    private String category;
    private Integer starRating;
    private String description;
    private String address;
    private String area;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pincode;
    private String latitude;
    private String longitude;
    private String phone;
    private String alternatePhone;
    private String email;
    private String website;
    private String checkIn;
    private String checkOut;
    private Double pricePerNight;
    private String currency;
   
//    @Column(name = "image_url", columnDefinition = "TEXT")
//    private String image;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "hotel_amenities",
        joinColumns = @JoinColumn(name = "hotel_id")
    )
    @Column(name = "amenity")
    private List<String> amenities;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "hotel_images",
        joinColumns = @JoinColumn(name = "hotel_id")
    )
    @Column(name = "image_url")
    private List<String> images;
    private String thumbnail;
    private String mapImage;
    private String googleMapLink;
    private String popularFor;
    private String status;

    // 1. No-Args Constructor
    public Hotel() {
        super();
    }

    // 2. All-Args Constructor
    public Hotel(String id, String name,  String brand, String category,
                 Integer starRating, String description, String address, String area, String city,
                 String district, String state, String country, String pincode, String latitude,
                 String longitude, String phone, String alternatePhone, String email, String website,
                 String checkIn, String checkOut, Double pricePerNight, String currency, String thumbnail,
                 String mapImage, String googleMapLink, String popularFor, String status) {
        super();
        this.id = id;
        this.name = name;
        
        this.brand = brand;
        this.category = category;
        this.starRating = starRating;
        this.description = description;
        this.address = address;
        this.area = area;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.alternatePhone = alternatePhone;
        this.email = email;
        this.website = website;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.pricePerNight = pricePerNight;
        this.currency = currency;
        this.thumbnail = thumbnail;
        this.mapImage = mapImage;
        this.googleMapLink = googleMapLink;
        this.popularFor = popularFor;
        this.status = status;
    }

    // 3. Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }



    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Integer getStarRating() { return starRating; }
    public void setStarRating(Integer starRating) { this.starRating = starRating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAlternatePhone() { return alternatePhone; }
    public void setAlternatePhone(String alternatePhone) { this.alternatePhone = alternatePhone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }

    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }

    public Double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(Double pricePerNight) { this.pricePerNight = pricePerNight; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

//    public List<String> getNearbyPlaces() {
//        return nearbyPlaces;
//    }
//
//    public void setNearbyPlaces(List<String> nearbyPlaces) {
//        this.nearbyPlaces = nearbyPlaces;
//    }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public String getMapImage() { return mapImage; }
    public void setMapImage(String mapImage) { this.mapImage = mapImage; }

    public String getGoogleMapLink() { return googleMapLink; }
    public void setGoogleMapLink(String googleMapLink) { this.googleMapLink = googleMapLink; }

    public String getPopularFor() { return popularFor; }
    public void setPopularFor(String popularFor) { this.popularFor = popularFor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // 4. toString Method
    @Override
    public String toString() {
        return "Hotel [id=" + id + ", name=" + name + ", brand=" + brand + ", category=" + category +
        		", starRating=" + starRating + 
               ", description=" + description + ", address=" + address + ", area=" + area + 
               ", city=" + city + ", district=" + district + ", state=" + state + 
               ", country=" + country + ", pincode=" + pincode + ", latitude=" + latitude + 
               ", longitude=" + longitude + ", phone=" + phone + " alternatePhone=" + alternatePhone + 
               ", email=" + email + ", website=" + website + ", checkIn=" + checkIn + 
               ", checkOut=" + checkOut + ", pricePerNight=" + pricePerNight + ", currency=" + currency + 
               ", \", amenities=\" + amenities +\r\n"
               + "\", images=\" + images +\r\n"
               + "\", nearbyPlaces=\" + nearbyPlaces +  thumbnail=" + thumbnail + ", mapImage=" + mapImage + ", googleMapLink=" + googleMapLink + 
               ", popularFor=" + popularFor + ", status=" + status + "]";
    }
}