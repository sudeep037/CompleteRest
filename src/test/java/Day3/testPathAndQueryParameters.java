package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class testPathAndQueryParameters {

	// Defining the query and path parameter inside given()
	
	// https://reqres.in/api/users?page=2&id=5
		
		@Test
		void test()
		{
		    given()
		     .pathParam("myPath", "users") //path param
		     .queryParam("page", 2)   //  query param
		     .queryParam("id", 5)  // query param
		     
		    .when()
		     .get("https://reqres.in/api/{myPath}")
		    .then()
		     .statusCode(200)
		     .log().all();
		}
}
