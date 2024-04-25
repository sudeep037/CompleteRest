package Day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

// search for json----> jsonSchema Converter
public class JSONSchema_Validation {

	@Test
	void testJSONSChema()
	{
		given()
		 
		.when()
		 .get("https://petstore.swagger.io/v2/user/sd")
		.then()
		 .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("petstore.json"));
	}
}
