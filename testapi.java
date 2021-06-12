package RESTAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import Files.payload;

public class testapi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Validate if Add place API is working
		
		//Given 
		//When - Submit the API Resources, Http method
		//Then 
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response;
		response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		
		System.out.println("Here is the Response \n" + response);
		
		JsonPath js = new JsonPath(response);
		
		String place_id;
		place_id = js.getString("place_id");

		System.out.println("Here is the Place ID \n" + place_id);
		
		
		//Add Place --> Update the Place with New Address --> Get place to validate the New address is present in the response. 
		
		//Updates Place
		String updatedAddress = "New Updated Address";
		
		given().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\n"
				+ "    \"place_id\": \""+place_id+"\" ,\n"
				+ "    \"address\" : \""+ updatedAddress+"\",\n"
				+ "    \"key\":\"qaclick123\"\n"
				+ "}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//get Place
		String getresponse;
		
		getresponse = given().queryParam("key","qaclick123").queryParam("place_id",place_id )
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js2 = new JsonPath(getresponse);
		
		String fetchedaddress = js2.getString("address");
		
		System.out.println(fetchedaddress);
		
		//Junit, .
		//TestNG
		org.testng.Assert.assertEquals(fetchedaddress, updatedAddress);
		
		
	}

}
