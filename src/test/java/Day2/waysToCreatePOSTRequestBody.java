package Day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class waysToCreatePOSTRequestBody {

	
	// POST request using HashMap
	
	//@Test
	void createPOST1()
	{
		HashMap data = new HashMap();
		data.put("id", 0);
		data.put("username", "Jhonny420");
		data.put("firstName", "Jhonny");
		data.put("lastName", "Deven");
		data.put("email", "Deven420@gmail.com");
		data.put("password", "12345678");
		data.put("phone", "7654238976");
		data.put("userStatus",0);
		
		given()
		 .contentType("application/json")
		 .body(data)
		
		.when()
		 .post("https://petstore.swagger.io/v2/user")
		 
		.then()
		 .statusCode(200);	 
	}
	
	// Create POST using json library
	//@Test(priority=1)
	void createPOST2()
	{
		JSONObject data = new JSONObject();
		data.put("id", 0);
		data.put("username", "Sunny420");
		data.put("firstName", "Sunny");
		data.put("lastName", "Leone");
		data.put("email", "Leone420@gmail.com");
		data.put("password", "78@2345678");
		data.put("phone", "7658938976");
		data.put("userStatus",0);
		
		given()
		 .contentType("application/json")
		 .body(data.toString())
		
		.when()
		 .post("https://petstore.swagger.io/v2/user")
		
		.then()
		 .statusCode(200)
		 .body("username",equalTo("Sunny420"));
	}
	
	//@Test(priority=2)
	void deleteUser()
	{
		given()
		
		.when()
		 .delete("https://petstore.swagger.io/v2/user/Sunny420")
		
		.then()
		 .statusCode(200);
	}
	
	// POST request body using POJO class 
	
	@Test(priority=1)
	void postPOJO()
	{
		POJO_postRequest p = new POJO_postRequest();
		p.setId(0);
		p.setUsername("Sunny23");
		p.setFirstName("Sunny");
		p.setLastName("Leone");
		p.setEmail("leone12@gmail.com");
		p.setPassword("34256@fg");
		p.setPhone("8976543123");
		
		given()
		 .contentType("application/json")
		 .body(p)
		
		.when()
		 .post("https://petstore.swagger.io/v2/user")
		
		.then()
		 .statusCode(200);
		 
	}
}
