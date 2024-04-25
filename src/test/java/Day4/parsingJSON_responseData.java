package Day4;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class parsingJSON_responseData {

	@Test(priority=1)
	void testJsonResponse()
	{
		// Approach 1 -> [we can directly validate response in then()]
		
		/*given()
		 .contentType("ContentType.JSON") 
		
		.when()
		 .get("http://localhost:3000/students")
		 
		.then()
		 .statusCode(200)
		 .header("Content-Type","application/json; charset=utf-8")
		 .body("x[2].Courses[1]",equalTo("Python"))
		 .log().body(); */
		
		// Approach 2 -> [we can get the response in variable and then we can done validations using Assertions]
		
		Response res = given()
		 .contentType(ContentType.JSON)
		 
		
		.when()
		 .get(" http://localhost:3000/STUDENTS");
		
		/*Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
		
	String courseName = res.jsonPath().get("x[2].Courses[1]").toString();
	Assert.assertEquals(courseName, "Python");*/
		
		
		// JSONObject Class (why we go for this approach -> Suppose in real time data might get dynamically displaying and also position might not be same, so at that time if we try to validate field 
		// we might get an error or we might end up with fail test case)
		
		JSONObject jo = new JSONObject(res.toString()); // converting response to JSONObject Type
		boolean status = false;
		for(int i = 0; i<jo.getJSONArray("STUDENTS").length(); i++)
		{
			String locationValue = jo.getJSONArray("STUDENTS").getJSONObject(i).get("Location").toString();
			
			if(locationValue.equals("India"))
			{
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
}