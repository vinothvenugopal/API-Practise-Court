package chaining;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteChangeRequestTestNG extends BaseClass {
	@Test(dependsOnMethods = "chaining.CreateChangeReqestTestNG.createChangeRequest")
	public void deleteChangeRequest()
	{
			Response response = RestAssured.given().log().all().delete(sys_id);
			System.out.println("Sys ID "+sys_id+" deleted successfully");
			response.prettyPrint();
	}

}
