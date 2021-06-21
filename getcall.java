package RESTAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getcall {
	
	@Test
	public void test_numberofCircuits()
	{
//		given().
//		when().
//		then().
//		assert()
		
		given().
		when().
		get("http://ergast.com/api/f1/2017/circuits.json").
		then().
		assertThat().
		statusCode(200).and().
		body("MRData.CircuitTable.CircuitId", hasSize(20));
	
	}

}
