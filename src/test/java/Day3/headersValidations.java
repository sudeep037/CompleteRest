package Day3;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class headersValidations {

	@Test
	void testHeaders()
	{
		Response res = given()
		
		.when()
		 .get("https://www.google.com/");
		 
	 /*	.then()
		 .header("Content-Type", "text/html; charset=ISO-8859-1")
		 .header("Content-Encoding", "gzip")
		 .header("Server", "gws")
		 .log().all(); */
		 
		 // Get Single header Information
		
		 String header_value = res.getHeader("Content-Type");
		 System.out.println(header_value);
		 
		 // Get all Header Info 
		 
		 Headers headers_values = res.getHeaders();
		 for(Header H:headers_values)
		 {
			 System.out.println(H.getName()+"   "+H.getValue());
		 }
		
	}
}
