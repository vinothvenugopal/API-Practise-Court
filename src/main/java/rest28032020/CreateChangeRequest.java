package rest28032020;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateChangeRequest {

	public static void main(String[] args) {

		File jsonFile = new File("./data1.json");
		RestAssured.baseURI = "https://dev87897.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "pH2uWtOpzHM1");
		Response response = RestAssured.given().contentType(ContentType.JSON).body(jsonFile).post();
		response.prettyPrint();
		
	}

}
