import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if Add Place API is working as expected
		
			//Given - all input details
			//when -  Submit the API, resources, http methods
			// Then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all()
			.assertThat().statusCode(200)
			.body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.41 (Ubuntu)")
			.extract().response().asString();
	
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
		String address1 = "70 Summmer wolk, USA";
		String address = "I chnaged to something else";
		
		//Update Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.UpdatePlace(placeId, address))
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//Get Place
		String addressResponse = 
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).body("address", equalTo(address))
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(addressResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, address);
		//Cucumber Junit, TestNG
		
	}

}
