package day3;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class CookiesDemo {
	
//	@Test(priority = 1)
	void testCookies()
	{
		given()
		
		
		
		.when()
			.get("https://www.google.com/")
		
		.then()
		//cookie values are keep changing so it gives error but our expected result also the condition becomes error, if it was failed means out test is passed
			.cookie("AEC","AQTF6HyE7-JS01SIdtS-uuiRtgSmH1GAce2zwDyvFpd-OmCmoMAvAUh9kQ")
			.log().all();

	}
	
	@Test(priority = 2)
	void getCookiesInfo()
	{
		Response res = given()
		
		.when()
			.get("https://www.google.com/"); //here ; means end the stmt
			
		
		//get single cookie
//		String cookie_value = res.getCookie("AEC");
//		System.out.println("Value of cookie is======>"+cookie_value);
		
		
		//get all cookies info
		Map<String,String> cookies_values = res.getCookies();
		
//		System.out.println(cookies_values.keySet());//only keys info
		
		for(String k:cookies_values.keySet()) {
			String cookie_value=res.getCookie(k);
			System.out.println(k+"          "+cookie_value);
					
		}
	}
}
