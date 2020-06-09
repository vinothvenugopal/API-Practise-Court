package rest28032020;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetChangeRequest {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://dev87897.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "pH2uWtOpzHM1");
		Response response = RestAssured.get();
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		long time = response.getTime();
		System.out.println("Time taken is "+time);
		System.out.println("content type is "+ response.contentType());
		JsonPath jsonPath = response.jsonPath();
		jsonPath.prettyPrint();
		
		
	}

}
