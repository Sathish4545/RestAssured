package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



/*
 Different ways to create post req body
  1) Post request body by using HashMap
  2) Post request body creation using Org.JSON
  3) Post request body creation using POJO class
  4) Post request using external json file data
 */
public class DiffWaysToCreatePostRequestBody {
	
//	 1) Post request body by using HashMap
	
//	@Test(priority = 1)
	void testPostUsingHashMap() 
	{
		HashMap data = new HashMap();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "1234567");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	
	}
	
//	 2) Post request body by using Org.json Library
	
//	@Test(priority = 1)
	void testPostUsingJsonLibrary() 
	{
		JSONObject data = new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "1234567");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())//org.json-->convert data into string
			//.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	
	}
	
	
//	 3) Post request body by using POJO Class
	
//	@Test(priority = 1)
	void testPostUsingPOJO() 
	{
		//create an object of pojo class
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("1234567");
		String coursesArr[] = {"C", "C++"};
		data.setCourses(coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
		
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	
	}
	
	
	
//	 4) Post request body by using external json file
	
	@Test(priority = 1)
	void testPostUsingExternalJsonFile() throws FileNotFoundException 
	{
		File f = new File(".\\body.json");
		//File f = new File("C:\Users\Sathish\eclipse-workspace\RestAssuredTraining\body.json");
		//To read the data from file
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
//			.body(data)
			
			
		.when()
			.post("http://localhost:3000/students")
		
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", equalTo("application/json"))
			.log().all();
	
	}
	
	
	
	//deleting student record
	
	@Test(priority = 2)
	void testDelete() 
	{
		given()
		
		
		
		.when()
			.delete("http://localhost:3000/students/a58a")
			//ID is automatically generated
			
		
		.then()
			.statusCode(404);
		
	}	
}
