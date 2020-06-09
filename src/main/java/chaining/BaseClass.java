package chaining;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseClass {
	public static String sys_id;
	@BeforeSuite
	public void initURI()
	{
		RestAssured.baseURI = "https://dev87897.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "pH2uWtOpzHM1");
	}

}
