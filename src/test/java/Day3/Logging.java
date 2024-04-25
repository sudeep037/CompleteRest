package Day3;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Logging {

	@Test
	void test()
	{
		given()
		
		.when()
		 .get("https://www.google.com/")
		.then()
		// .log().headers();
		// .log().body();
		   .log().cookies();
	}
}
