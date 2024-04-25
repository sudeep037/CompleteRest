package Day6;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {

	// Browse XML to xsd/xml schema
	
	@Test
	void xmlSchemaValidation()
	{
		given()
		
		.when()
		 .get("http://restapi.adequateshop.com/swagger/ui/index#!/Traveler/Traveler_GetTraveler")
		 
		.then()
		 .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
		
	}
}
