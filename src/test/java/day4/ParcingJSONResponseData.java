package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;



public class ParcingJSONResponseData {
	
	
private boolean status;



//	@Test(priority=1)
	void testJsonResponse() {
		//Approach1
		//without capturing the response 
		
		
		/*given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store")
		
		
		.then()
		.statusCode(200)
		.header("Content_Type", "application/json")
		.body("book[3].title", equalTo("The lord of the Rings"));*/
		
		
		
		
		//Approach 2----having more adv
		
		Response res = given()
		//Approach 2 have most advantages	
		//we will get responce in the variable and add the validations
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");
		Assert.assertEquals(res.getStatusCode(), 200); //validation 1
		Assert.assertEquals(res.getHeader("Content-Type"),"application/json");
		
		String bookname =res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The lord of the Rings");
		
	}
	
	
	
	@Test(priority = 2)
	void testJsonResponseBody()
	{
		Response res =
		given()
			.contentType(ContentType.JSON)
		
		
		.when()
			.get("http://localhost:3000/store");
			
			//JSONObject Class
			JSONObject jo = new JSONObject(res.asString()); //converting response to json object type
			/*
			 for(int i=0; i<jo.getJSONArray("book").length(); i++) {
				String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
				System.out.println(bookTitle);
			}
			*/
			
			
			//search for title of the book in json	- validation 1
			boolean status=false;
			for(int i=0; i<jo.getJSONArray("book").length(); i++) {
				String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
				if(bookTitle.equals("The lord of the Rings")) {
					status=true;
					break;
				}
				
			}
					
		  Assert.assertEquals(status, true);
		
		  
		  //validate total price of books   - validation 2
		  
		  double totalprice = 0;
		  for(int i=0; i<jo.getJSONArray("book").length(); i++) {
				String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
				totalprice = totalprice+Double.parseDouble(price);
			}
		  System.out.println("total price of books is:" +totalprice);
		  Assert.assertEquals(totalprice, 53.92);
		  
	
	}
	
}
