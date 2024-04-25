package Day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTest {

	@Test
	void parseJSONResponse() throws Exception
	{
		Response res = given()
		 .contentType(ContentType.JSON)
		 .pathParam("myPath", "users")
		 .queryParam("page", 1)
		 
		.when()
		 .get("https://reqres.in/api/{myPath}");
		
		// Validating the JSON field by using the JSONObject class
		
		// Validating email 
		
		JSONObject jo = new JSONObject(res.toString());
		boolean status = false;
		
		for(int i = 0; i<jo.getJSONArray("data").length(); i++)
		{
		    String userEmail = jo.getJSONArray("data").getJSONObject(i).get("email").toString();
		    if(userEmail.equals("george.bluth@reqres.in"))
		    {
		    	status = true;
		    	break;
		    }
		}
		Assert.assertEquals(status, true);
	}
}
