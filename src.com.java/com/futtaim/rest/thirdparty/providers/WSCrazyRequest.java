package com.futtaim.rest.thirdparty.providers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WSCrazyRequest{

	private String  city;
	private String  from;
	
	@JsonProperty("To")
	private String  to;
	private String  adultsCount;
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getAdultsCount() {
		return adultsCount;
	}
	public void setAdultsCount(String adultsCount) {
		this.adultsCount = adultsCount;
	}
	
	

}
