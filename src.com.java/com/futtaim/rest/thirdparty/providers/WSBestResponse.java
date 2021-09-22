package com.futtaim.rest.thirdparty.providers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class WSBestResponse {
	

	private String hotel;
	private String hotelRate;
	private String hotelFare;
	private String roomAmenities;
	
	
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public String getHotelRate() {
		return hotelRate;
	}
	public void setHotelRate(String hotelRate) {
		this.hotelRate = hotelRate;
	}
	public String getHotelFare() {
		return hotelFare;
	}
	public void setHotelFare(String hotelFare) {
		this.hotelFare = hotelFare;
	}
	public String getRoomAmenities() {
		return roomAmenities;
	}
	public void setRoomAmenities(String roomAmenities) {
		this.roomAmenities = roomAmenities;
	}
	
	
}


