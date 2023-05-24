import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if Add Place API is working as expected
		
			//Given - all input details
			//when -  Submit the API, resources, http methods
			// Then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\n"
				+ "    \"location\" : {\n"
				+ "        \"lat\": -38.383494,\n"
				+ "        \"lng\": 33.427362\n"
				+ "    },\n"
				+ "    \"accuracy\": 50,\n"
				+ "    \"name\" : \"Frontline house\",\n"
				+ "    \"phone_number\" : \"(+91)9838933937\",\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\n"
				+ "    \"types\": [\n"
				+ "        \"shoe park\",\n"
				+ "        \"shop\"\n"
				+ "    ],\n"
				+ "    \"website\":\"http://google.com\",\n"
				+ "    \"language\": \"French_IN\"\n"
				+ "}")
		.when().post("maps/api/place/add/json")
		.then().log().all()
			.assertThat().statusCode(200);
		
	}

}
