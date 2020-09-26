package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class Employee {

	//@Test
	public void test_getAllEmployee() {
		given()

				.when().get("http://dummy.restapiexample.com/api/v1/employees").then().statusCode(200);
	}
	//@Test(priority=2)
	public void test_addNewEmployee()
	{
		HashMap data= new HashMap();
		data.put("name","Super Woman");
		data.put("salary","5100");
		data.put("age","33");
		
		Response res=
		given()
			.contentType("application/json")
			.body(data)
			.when()
			.post("http://dummy.restapiexample.com/api/v1/create")
			.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("success"), true);
	}
	//@Test
	public void get_EmployeeStatus()
	{
		given()
		.when()
		.get("http://dummy.restapiexample.com/api/v1/employee/6")
		.then()
		.statusCode(200)
		.log().body()
		.body("data.id", equalTo(6))
		.body("data.employee_name",equalTo("Brielle Williamson"));
	}
	
	@Test
	public void update_Employee()
	{

		HashMap data= new HashMap();
		data.put("name","morpheus");
		data.put("job","zion resident");
		
		
		given().contentType("application/json")
		.body(data)
		.when()
		.put("https://reqres.in/api/users/2")
		.then().statusCode(200)
		.log().body()
		.body("name",equalTo("morpheus"))
		.body("job",equalTo("zion resident"));
	}
	
}
