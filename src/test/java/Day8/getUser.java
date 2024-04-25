package Day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class getUser {

	@Test
	void getUser(ITestContext context)
	{
		String bearerToken = "67a1d5b20d2df23ad880307ebdb19ede88a524f8527e5b408a625e4c108c31cc";
		
		//int id=(Integer) context.getAttribute("userID");   this id variable is only accessible at "Test" level
		
		int id = (Integer) context.getSuite().getAttribute("userID"); // this id variable is accessible at suite level as well as test level also
		
		given()
		 .headers("Authorization","Bearer "+bearerToken)
		 .pathParam("id", id)
		
		.when()
		 .get("https://gorest.co.in/public/v2/users/{id}")
		.then()
		 .statusCode(200)
		 .log().all();
	}
}
