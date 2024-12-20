package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggersDemo {
	
	@Test
	void testLogs() {
		given()
		
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		
		.then()
//		.log().all(); 	//print everything from the response
//		.log().body();		//print only body from the response
//		.log().cookies();	//print only cookies from the response
		.log().headers();	//print only headers from the response
		
		
		
	}
}
