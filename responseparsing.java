package RESTAssured;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class responseparsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	
		1. Print No of courses returned by API
		2.Print Purchase Amount
		3. Print Title of the first course
		4. Print All course titles and their respective Prices
		5. Print no of copies sold by RPA Course
		6. Verify if Sum of all Course prices matches with Purchase Amount
		*/

		
		JsonPath js = new JsonPath(payload.coursePrice());
		
		int coursesCount,purchaseAmount,RPAcopies,sumofcources=0,price;
		String firstCourseTitle,title;
		
		//		1. Print No of courses returned by API
		coursesCount = js.getInt("courses.size()");
		System.out.println("Number of Courses are : " + coursesCount);
		
		//		2.Print Purchase Amount
		purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount is : "+ purchaseAmount);
		
		
		//3. Print Title of the first course
		firstCourseTitle = js.getString("courses[0].title");
		
		System.out.println("firstCourseTitle is : " + firstCourseTitle);
		
		
//		4. Print All course titles and their respective Prices

		for (int i=0; i<coursesCount; i++)
		{
			title = js.get("courses["+i+"].title");
			
			System.out.println("Here is the tile for course " + i + ": "+ title);
		}
		
		//5. Print no of copies sold by RPA Course
	
		//RPACoursePrice = js.getInt("courses[2].copies");
		//System.out.println("No of Copies sold for RPA " + RPACoursePrice);

		for (int i=0; i<coursesCount; i++)
		{
			title = js.get("courses["+i+"].title");
			System.out.println(title);
			if(title.toString().equalsIgnoreCase("RPA"))
			{
				RPAcopies = js.getInt("courses["+i+"].copies");
				System.out.println("No of Copies sold for RPA " + RPAcopies);
				break;
			}
		}
		
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
