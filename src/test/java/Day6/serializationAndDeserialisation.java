package Day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Day2.POJO_postRequest;

public class serializationAndDeserialisation {

	// We are using Jackson Pakage to covert java object to json and vice-versa
	
	@Test
	void convertPOJOtoJSON() throws JsonProcessingException
	{
		// Creating java object using POJO class
		
		POJO_postRequest p = new POJO_postRequest();
		
		p.setId(0);
		p.setUsername("Sunny23");
		p.setFirstName("Sunny");
		p.setLastName("Leone");
		p.setEmail("leone12@gmail.com");
		p.setPassword("34256@fg");
		p.setPhone("8976543123");
		
		// converting java object ------> json object (serialisation)
		
		ObjectMapper om = new ObjectMapper();
		String jsonData = om.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		System.out.println(jsonData);
	}
	
	@Test
	void convertJSONtoPOJO() throws JsonProcessingException
	{
		
		String JSONdata = "{\r\n"
				+ "  \"username\" : \"Sunny23\",\r\n"
				+ "  \"firstName\" : \"Sunny\",\r\n"
				+ "  \"lastName\" : \"Leone\",\r\n"
				+ "  \"email\" : \"leone12@gmail.com\",\r\n"
				+ "  \"password\" : \"34256@fg\",\r\n"
				+ "  \"phone\" : \"8976543123\",\r\n"
				+ "  \"id\" : 0\r\n"
				+ "}";
		
		// Converting JSON data to Java Object (de-serialization)
		
		ObjectMapper om = new ObjectMapper();
		POJO_postRequest sp =om.readValue(JSONdata,POJO_postRequest.class);
		System.out.println(sp.getEmail());
		System.out.println(sp.getFirstName());
	}
}
