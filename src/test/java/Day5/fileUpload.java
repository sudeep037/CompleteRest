package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;


public class fileUpload {

	@Test
	void singleFileUpload()
	{
		File f = new File("D:\\JSON FILES\\booksAPI.json");
		
		 given()
		  .multiPart("file",f)
		  .contentType("multipart/form-data")
		 
		.when()
		 .post("http://localhost:3000/orderDetails")
		
		.then()
		 .statusCode(200)
		 .body("fileName", equalTo("booksAPI.json"))
		 .log().body();
	}
}
