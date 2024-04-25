package Day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class deleteUser {

	@Test
	void deleteUser(ITestContext context)
	{
		String bearerToken = "67a1d5b20d2df23ad880307ebdb19ede88a524f8527e5b408a625e4c108c31cc";
		
		// int id = (Integer) context.getAttribute("userID");
		int id = (Integer) context.getSuite().getAttribute("userID"); // suite level id variable
		
		given()
		 .headers("Authorization", "Bearer "+bearerToken)
		 .pathParam("id", id)
		 
		.when()
		 .delete("https://gorest.co.in/public/v2/users/{id}")
		 
		.then()
		 .statusCode(204)
		 .log().all();
	}
}
