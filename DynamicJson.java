package RESTAssured;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test
	public void addBook()
	{
		RestAssured.baseURI = "";
		Response response = given().header("content-type","application/json").body("").when().post("").then().assertThat().extract().response();
		
		JsonPath js = new JsonPath(response.toString());
		String id = js.get("ID");
		System.out.println(id);
	}
	

}
