package com.futtaim.rest.endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.futtaim.rest.payload.AvailableHotelRequest;
import com.futtaim.rest.payload.BestHotelRequest;
import com.futtaim.rest.payload.ListAvailableHotelFinalResponse;
import com.futtaim.rest.services.AvailableHotelService;
import com.futtaim.rest.thirdparty.providers.WSCrazyRequest;


@Path("/hotel")
public class BestHotelController {
	

 	
	@POST
	@Path("/available")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAvailableHotel(AvailableHotelRequest request) {
	AvailableHotelService service= new AvailableHotelService();
		ListAvailableHotelFinalResponse response= new ListAvailableHotelFinalResponse();
		
	response = service.getAvailableHotel(request);
		
        return Response.ok().entity(response).build();

	}
	
	
	@POST
	@Path("/best")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBestHotel(BestHotelRequest request) {

		String best= "[\r\n" + 
						 "	{\r\n" + 
						 "	\"hotel\":\"karam\",\r\n" + 
						 "    \"hotelRate\":\"5\",\r\n" + 
						 "	\"hotelFare\":\"50\",\r\n" + 
						 "	\"discount\":\"10\",\r\n" + 
						 "	\"roomAmenities\":\"wifi, good price, hi rate\"\r\n" + 
						 "	},\r\n" + 
						 "	{\r\n" + 
						 "	\"hotel\":\"cairo\",\r\n" + 
						 "    \"hotelRate\":\"2\",\r\n" + 
						 "	\"hotelFare\":\"30\",\r\n" + 
						 "	\"discount\":\"10\",\r\n" + 
						 "	\"roomAmenities\":\"wifi, good price, hi rate\"\r\n" + 
						 "	},\r\n" + 
						 "	{\r\n" + 
						 "	\"hotel\":\"fawl\",\r\n" + 
						 "    \"hotelRate\":\"5\",\r\n" + 
						 "	\"hotelFare\":\"200\",\r\n" + 
						 "	\"discount\":\"\",\r\n" + 
						 "	\"roomAmenities\":\"wifi, good price, hi rate, available\"\r\n" + 
						 "	},\r\n" + 
						 "	{\r\n" + 
						 "	\"hotel\":\"hero\",\r\n" + 
						 "    \"hotelRate\":\"3\",\r\n" + 
						 "	\"hotelFare\":\"100\",\r\n" + 
						 "	\"discount\":\"10\",\r\n" + 
						 "	\"roomAmenities\":\"wifi, good price, hi rate\"\r\n" + 
						 "	}\r\n" + 
						 "	]";
	
        return Response.ok().entity(best).build();

	}
	
	@POST
	@Path("/crazy")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCrazyHotel(WSCrazyRequest request) {

		String best= "\r\n" + 
				 "	[\r\n" + 
				 "	{\r\n" + 
				 "	\"hotelName\":\"quds\",\r\n" + 
				 "    \"rate\":\"*****\",\r\n" + 
				 "	\"price\":\"100\",\r\n" + 
				 "	\"discount\":\"10\",\r\n" + 
				 "	\"amenities\":[\r\n" + 
				 "                \"wifi\",\r\n" + 
				 "                \"good price\",\r\n" + 
				 "                \"hi rate\"\r\n" + 
				 "        ]\r\n" + 
				 "\r\n" + 
				 "\r\n" + 
				 "	},\r\n" + 
				 "	{\r\n" + 
				 "	\"hotelName\":\"makah\",\r\n" + 
				 "    \"rate\":\"****\",\r\n" + 
				 "	\"price\":\"100\",\r\n" + 
				 "	\"discount\":\"10\",\r\n" + 
				 "	\"amenities\":[\r\n" + 
				 "                \"wifi\",\r\n" + 
				 "                \"good price\",\r\n" + 
				 "                \"hi rate\"\r\n" + 
				 "        ]\r\n" + 
				 "\r\n" + 
				 "\r\n" + 
				 "	},\r\n" + 
				 "	{\r\n" + 
				 "	\"hotelName\":\"palace\",\r\n" + 
				 "    \"rate\":\"**\",\r\n" + 
				 "	\"price\":\"100\",\r\n" + 
				 "	\"discount\":\"10\",\r\n" + 
				 "	\"amenities\":[\r\n" + 
				 "                \"wifi\",\r\n" + 
				 "                \"good price\",\r\n" + 
				 "                \"hi rate\"\r\n" + 
				 "        ]\r\n" + 
				 "\r\n" + 
				 "\r\n" + 
				 "	},\r\n" + 
				 "	{\r\n" + 
				 "	\"hotelName\":\"harir\",\r\n" + 
				 "    \"rate\":\"***\",\r\n" + 
				 "	\"price\":\"100\",\r\n" + 
				 "	\"discount\":\"10\",\r\n" + 
				 "	\"amenities\":[\r\n" + 
				 "                \"wifi\",\r\n" + 
				 "                \"good price\",\r\n" + 
				 "                \"hi rate\"\r\n" + 
				 "        ]\r\n" + 
				 "\r\n" + 
				 "\r\n" + 
				 "	}\r\n" + 
				 "	\r\n" + 
				 "	]\r\n" + 
				 "\r\n" + 
				 "";
	
        return Response.ok().entity(best).build();

	}
	
		
	}
	
	 

