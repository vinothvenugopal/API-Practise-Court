package chaining;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateChangeReqestTestNG extends BaseClass {
	@Test(dataProvider = "getFileName")
	public void createChangeRequest(String fileName)
	{
			File jsonFile = new File(fileName);
		
			Response response = RestAssured.given().contentType(ContentType.JSON).body(jsonFile).post();
			JsonPath jsonresponse = response.jsonPath();
			sys_id = jsonresponse.get("result.sys_id");
			System.out.println(sys_id);
			response.prettyPrint();
	}

	
	@DataProvider(name = "getFileName", indices = {0})
	public String[][] fileName()
	{
		String[][] fileName = new String[2][1];
		fileName[0][0] = "./data1.json";
		fileName[1][0] = "./data2.json";
		return fileName;
	}

}
