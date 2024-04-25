package Day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Map;
public class headersAndCookies
{

	@Test
	void testCookies()
	{
		Response res = given()
		
		.when()
		 .get("https://www.google.com/");
		
		// get single cookie info
		String CV = res.getCookie("AEC");
		System.out.println(CV);
		
	  	Map<String, String> cookieValue = res.getCookies();
	  	System.out.println(cookieValue.keySet());
	  	
	  	// get all cookie info
	  	
	  	for(String a:cookieValue.keySet())
	  	{
	  		String cookie_Value=res.getCookie(a);
	  		System.out.println(a + " " + cookie_Value);
	  	}
	  
	}
}
