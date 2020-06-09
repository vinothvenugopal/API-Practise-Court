package rest28032020;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateChangeReqestTestNG {
	@Test(dataProvider = "getFileName")
	public void createChangeRequest(String fileName)
	{
			File jsonFile = new File(fileName);
			RestAssured.baseURI = "https://dev87897.service-now.com/api/now/table/change_request";
			RestAssured.authentication = RestAssured.basic("admin", "pH2uWtOpzHM1");
			Response response = RestAssured.given().contentType(ContentType.JSON).body(jsonFile).post();
			response.prettyPrint();
			
	}

	
	@DataProvider(name = "getFileName", parallel = true, indices = {0})
	public String[][] fileName()
	{
		String[][] fileName = new String[2][1];
		fileName[0][0] = "./data1.json";
		fileName[1][0] = "./data2.json";
		return fileName;
	}

}
