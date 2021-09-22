package com.futtaim.rest.payload;

public class BestHotelRequest {

	private String  city;
	private String  fromDate;
	private String  toDate;
	private String  numberOfAdults;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getNumberOfAdults() {
		return numberOfAdults;
	}
	public void setNumberOfAdults(String numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	
	

}
