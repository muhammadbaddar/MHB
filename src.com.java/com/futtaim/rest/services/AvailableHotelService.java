package com.futtaim.rest.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futtaim.rest.payload.AvailableHotel;
import com.futtaim.rest.payload.AvailableHotelFinal;
import com.futtaim.rest.payload.AvailableHotelRequest;
import com.futtaim.rest.payload.ListAvailableHotelFinalResponse;
import com.futtaim.rest.thirdparty.providers.WSBestRequest;
import com.futtaim.rest.thirdparty.providers.WSBestResponse;
import com.futtaim.rest.thirdparty.providers.WSCrazyRequest;
import com.futtaim.rest.thirdparty.providers.WSCrazyResponse;

public class AvailableHotelService {

	@SuppressWarnings("finally")
	public ListAvailableHotelFinalResponse getAvailableHotel(AvailableHotelRequest req) {

		List<AvailableHotel>             resList 			     = new ArrayList<AvailableHotel>();
		List<WSBestResponse>             bestList 				 = new ArrayList<WSBestResponse>();
		WSBestRequest                    bestReqWS 				 = new WSBestRequest();
		List<WSCrazyResponse>            crazyList 				 = new ArrayList<WSCrazyResponse>();
		WSCrazyRequest 					 crazyReqWS	 			 = new WSCrazyRequest();
		List<AvailableHotelFinal> 		 resListFinal 	         = new ArrayList<AvailableHotelFinal>();
		AvailableHotel     				 availableHotel 		 = new AvailableHotel();
		AvailableHotelFinal 			 availableHotelFinal 	 = new AvailableHotelFinal();
		ListAvailableHotelFinalResponse  listAvailableFinalHotel = new ListAvailableHotelFinalResponse();


		Client       client = ClientBuilder.newClient();
		ObjectMapper mapper = new ObjectMapper();

		String responseBody = "";


		try {

			LocalDate dateFrom = LocalDate.parse(req.getFromDate());
			LocalDate dateTo   = LocalDate.parse(req.getToDate());

			String dateFromIsoLocal = dateFrom.format(DateTimeFormatter.ISO_LOCAL_DATE);
			String dateToIsoLocal   = dateTo.format(DateTimeFormatter.ISO_LOCAL_DATE);

			Date dateToIsoInst = new Date();
			Date dateFromIsoInst = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


			try {
				dateFromIsoInst = dateFormat.parse(req.getFromDate());
				dateToIsoInst = dateFormat.parse(req.getToDate());

			} catch (ParseException e) {
				e.printStackTrace();
			}

			//SET REQUESTS FOR CRAZY AND BEST

			crazyReqWS.setFrom(dateFromIsoInst.toString());
			crazyReqWS.setTo(dateToIsoInst.toString());
			crazyReqWS.setCity(req.getCity());
			crazyReqWS.setAdultsCount(req.getNumberOfAdults());

			bestReqWS.setFromDate(dateFromIsoLocal);
			bestReqWS.setToDate(dateToIsoLocal);
			bestReqWS.setCity(req.getCity());
			bestReqWS.setNumberOfAdults(req.getNumberOfAdults());

			//CALLING THIRD PARTY TO GET DATA  FROM CRAZY AND BEST
			responseBody = client.target("http://localhost:8080/BestHotelDemo/hotel/crazy")
								 .request(MediaType.APPLICATION_JSON)
								 .post(Entity.entity(crazyReqWS, MediaType.APPLICATION_JSON), String.class);

			crazyList = Arrays.asList(mapper.readValue(responseBody, WSCrazyResponse[].class));
			
			responseBody = client.target("http://localhost:8080/BestHotelDemo/hotel/best")
						 		 .request(MediaType.APPLICATION_JSON)
						 		 .post(Entity.entity(bestReqWS, MediaType.APPLICATION_JSON), String.class);

			bestList = Arrays.asList(mapper.readValue(responseBody, WSBestResponse[].class));
			

			//PROCCESSING DATA
			for (int i = 0; i < crazyList.size(); i++) {

				availableHotel = new AvailableHotel();
				availableHotel.setProvider("Crazy Hotel");
				availableHotel.setHotelName(crazyList.get(i).getHotelName());
				Double netPrice= Double.parseDouble(crazyList.get(i).getPrice()) - Double.parseDouble(crazyList.get(i).getDiscount()) ;
				availableHotel.setFare(netPrice.toString());
				availableHotel.setRate(crazyList.get(i).getRate());
				availableHotel.setAmenities(crazyList.get(i).getAmenities());

				resList.add(availableHotel);
			}

			

			for (int i = 0; i < bestList.size(); i++) {

				availableHotel = new AvailableHotel();
				availableHotel.setProvider("Best Hotel");
				availableHotel.setHotelName(bestList.get(i).getHotel());
				availableHotel.setFare(bestList.get(i).getHotelFare());
				String rateStar = "";
				for (int j = 0; j < Integer.parseInt(bestList.get(i).getHotelRate()); j++)
					rateStar += "*";

				availableHotel.setRate(rateStar);

				String ament = bestList.get(i).getRoomAmenities();
				String[] arrayAment = ament.split(",");

				availableHotel.setAmenities(arrayAment);

				resList.add(availableHotel);
			}

			//SORTING BY RATE
			List<AvailableHotel> sortedAvailableList = resList.stream()
					.sorted(Comparator.comparing(AvailableHotel::getRate)).collect(Collectors.toList());

			for (int i = 0; i < sortedAvailableList.size(); i++) {

				availableHotelFinal = new AvailableHotelFinal();
				availableHotelFinal.setProvider(sortedAvailableList.get(i).getProvider());
				availableHotelFinal.setHotelName(sortedAvailableList.get(i).getHotelName());
				availableHotelFinal.setFare(sortedAvailableList.get(i).getFare());
				availableHotelFinal.setAmenities(sortedAvailableList.get(i).getAmenities());

				resListFinal.add(availableHotelFinal);

			}

			listAvailableFinalHotel.setAvailableHotelFinal(resListFinal);
		

		} catch (Exception e) {
			e.printStackTrace();

		}finally {

		return listAvailableFinalHotel;
		}
	}

}