package Day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class createUser {

	@Test
	public void testCreateUser(ITestContext context)
	{
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "67a1d5b20d2df23ad880307ebdb19ede88a524f8527e5b408a625e4c108c31cc";
		
		 int id = given()
		 .headers("Authorization","Bearer "+bearerToken)
		 .contentType("application/json")
		 .body(data.toString())
		 
		.when()
		 .post("https://gorest.co.in/public/v2/users")
		 .jsonPath().getInt("id");
		
		System.out.println(id);
		
		//context.setAttribute("userID", id);
		context.getSuite().setAttribute("userID", id);
	}
}
