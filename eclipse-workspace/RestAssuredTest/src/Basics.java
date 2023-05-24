import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if Add Place API is working as expected
		
			//Given - all input details
			//when -  Submit the API, resources, http methods
			// Then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all()
			.assertThat().statusCode(200)
			.body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.41 (Ubuntu)");
		
	}

}
