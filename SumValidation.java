package RESTAssured;

import org.testng.annotations.Test;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	@Test
	public void sumOfCources()
	{
		int price,RPAcopies,sumofcources=0,coursesCount,purchaseAmount;
		
		JsonPath js = new JsonPath(payload.coursePrice());
		
//		1. Print No of courses returned by API
		coursesCount = js.getInt("courses.size()");
		System.out.println("Number of Courses are : " + coursesCount);
		
		//		2.Print Purchase Amount
		purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount is : "+ purchaseAmount);
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		
		for (int i=0; i<coursesCount; i++)
		{
			price = js.get("courses["+i+"].price");	
			RPAcopies = js.getInt("courses["+i+"].copies");
			sumofcources = sumofcources + (price*RPAcopies);
		}
		System.out.println("Here is the sum of " + coursesCount + " all Courses for course : "+ sumofcources);
		
		if(purchaseAmount == sumofcources)
		{
			System.out.println("Sum of courses match with the purchase amount");
		}
		else
		{			
			System.out.println("Sum of courses Does not match with the purchase amount");
			
		}
	}

}
