package Day7;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {

	// Basic Authentication
	
	@Test(priority=1)
	void testBasicAuthentication()
	{
		given()
		.auth().basic("postman", "password")
		
		.when()
		 .get("https://postman-echo.com/basic-auth")
		 
		.then()
		 .statusCode(200)
		 .body("authenticated", equalTo(true))
		 .log().all();	 
	}
	
	// Digest Authentications
	
	@Test(priority=2)
	void testDigestAuthentication()
	{
		given()
		.auth().digest("postman", "password")
		
		.when()
		 .get("https://postman-echo.com/basic-auth")
		 
		.then()
		 .statusCode(200)
		 .body("authenticated", equalTo(true))
		 .log().all();	 
	}
	
	// Preemptive Authentications
	
	@Test(priority=3)
	void testPreEmptiveAuthentication()
	{
		given()
		.auth().preemptive().basic("postman", "password")
		
		.when()
		 .get("https://postman-echo.com/basic-auth")
		 
		.then()
		 .statusCode(200)
		 .body("authenticated", equalTo(true))
		 .log().all();	 
	}
	
	@Test(priority=4)
	void testBearerTokenAuthentication()
	{
		String bearerToken = "ghp_24pH0Icz1qOtLwj57AuDYatSz2fuYKP";
		
		given()
		 .headers("Authourization","Bearer "+bearerToken)   // We have pass bearer token under the request-header section
		 
		.when()
		 .get("https://api.github.com/user/repos")
		 
		.then()
		 .statusCode(200)
		 .log().all();
	}
	
	@Test
	void testOAuth1Authentication()
	{
		given()
		 .auth().oauth("consumerKey", "consumerSecreat", "accessToken", "tokensecreat")
		 
		.when()
		 .get("url")
		 
		.then()
		 .log().all();
	}
	
	@Test(priority=5)
	void testOAuth2Authentication()
	{
		given()
		 .auth().oauth2("ghp_24pH0Icz1qOtLwj57AuDYatSz2fuYKP")
		 
		.when()
		 .get("https://api.github.com/user/repos")
		.then()
		 .statusCode(200)
		 .log().all();
	}
	
	@Test(priority=6)
	void testAPIKey()
	{
		given()
		 .queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")// appid is API Key
		 .pathParam("myPath", "data/2.5/forecast/daily")
		 .queryParam("q", "Delhi")
		 .queryParam("units", "metrics")
		 .queryParam("cnt", 7)
 		 
		.when()
		 .get("https://api.openweathermap.org/{myPath}")
		
		.then()
		 .statusCode(200)
		 .log().all();
	}
	
}
