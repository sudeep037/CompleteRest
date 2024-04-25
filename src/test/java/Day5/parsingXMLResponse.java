package Day5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
public class parsingXMLResponse {

	// @Test
	void parseXMLResponse()
	{
	  
	//Approach 1 -> without returning the response in variable
		
		/*given()
		 .pathParam("myPath", "Traveler")
		 .queryParam("page", 1)
		
		 .when()
		 .get("http://restapi.adequateshop.com/api/{myPath}")
		.then()
		 .statusCode(200)
		 .header("Content-Type", "application/xml; charset=utf-8")
		 .body("TravelerInformationResponse.page",equalTo("1"))
		 .body("TravelerInformationResponse.travelers.Travelersinformation[0].name",equalTo("Developer")); */
		
	
	// Approach 2 -> By returning the response into variable
		
		Response res = given()
		.pathParam("myPath", "Traveler")
		.queryParam("page", 1)
		
		.when()
		.get("http://restapi.adequateshop.com/api/{myPath}");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		// validating xml body, for that we have to use "xmlPath()" 
		String pageNo = res.xmlPath().get("TravelerInformationResponse.page").toString();   // converting the object into string by using toString()
		Assert.assertEquals(pageNo, 1);
		
		String travelerName = res.xmlPath().get("TravelerInformationResponse.travelers.Travelersinformation[0].name").toString();
		Assert.assertEquals(travelerName, "Developer");
	}
	
	@Test
	void testXMLResponseBody()
	{
	  // Approach -> by using XMLPath Class (As due to dynamicity of data in real time, we might get the node in different position, so it
		                                     // better to use the Approach-> 3)
		
		Response res = given()
		
		.when()
		 .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xml = new XmlPath(res.asString());   // If we want to convert entire response into a String then we should use "asString()"
		
		// Verifying total no of travellers
		List<String> travelers = xml.getList("TravelerInformationResponse.travelers.Travelersinformation");
		Assert.assertEquals(travelers.size(), 10);
		
		// Verifying traveler name present in the Response
		List<String> travelers_name = xml.getList("TravelerInformationResponse.travelers.Travelersinformation.name");
		boolean status = false;
		for(String traveler_name:travelers_name)
		{
			if(traveler_name.equals("Developer"))
			{
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
		// Verifying traveller's id present in response
		
		List<String> travellers_id = xml.getList("TravelerInformationResponse.travelers.Travelersinformation.id");
		boolean status1 = false;
		for(String traveller_id:travellers_id)
		{
			if(traveller_id.equals("3456"))
			{
				status1 = true;
				break;
			}
		}
		Assert.assertEquals(status1, true);
	}
}
