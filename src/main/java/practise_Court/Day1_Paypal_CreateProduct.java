package practise_Court;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Day1_Paypal_CreateProduct  {
	
	List<String> product_ID = new ArrayList<String>();

	@DataProvider (name = "getFileName")
	public String[] getFileName()
	{
		String[] fileName = new String[2];
		fileName[0] = "./Practise1.json";
		fileName[1] = "./Practise2.json";
		return fileName;
	}
	
	@Test(dataProvider = "getFileName")
	public void postRequest(String fileName)
	{
		File file = new File(fileName);
		RestAssured.baseURI = "https://api.sandbox.paypal.com";
		Response response = RestAssured.given()
				.log().all()
				.auth()
				.basic("AWJsEsVAxvJ9xHam6DqU6KP2ZjdM6a8vcgClz-E2x-ouv18Ozr3pjocuNe1s690zoME49SJ1iBkAP_lk",
						"EBn_IkvASlrbwfiXiRYuhM9FiGxQU8zKlDshIZ4q3qQ9fjvch2NpgNKoXvaF1bcNz8l2Fj0D399fX30t")
				.headers("Authorization",
						"Bearer A21AAFv_aJeqhl7GIaRhQg3JPoGa5oHq-1XqB_7489D86JIq8KsawwvhbuQxdUJyJMu-Zhkj42Rp-jIUcgXNNEUO_oKVNMYMQ")
				.contentType(ContentType.JSON).body(file)
				.when()
				.post("v1/catalogs/products")
				.then()
				.assertThat().statusCode(201).extract().response();
		JsonPath jsonPath = response.jsonPath();
		jsonPath.prettyPrint();
		product_ID.add(jsonPath.getString("id"));
		System.out.println("Product ID: "+jsonPath.getString("id")+" added successfully");
	}
	
	@Test(dependsOnMethods = "postRequest")
	public void verifyProductCreation()
	{
		RestAssured.baseURI = "https://api.sandbox.paypal.com";
		Response response = RestAssured.given()
				.log().all()
				.auth()
				.basic("AWJsEsVAxvJ9xHam6DqU6KP2ZjdM6a8vcgClz-E2x-ouv18Ozr3pjocuNe1s690zoME49SJ1iBkAP_lk",
						"EBn_IkvASlrbwfiXiRYuhM9FiGxQU8zKlDshIZ4q3qQ9fjvch2NpgNKoXvaF1bcNz8l2Fj0D399fX30t")
				.headers("Authorization",
						"Basic A21AAFv_aJeqhl7GIaRhQg3JPoGa5oHq-1XqB_7489D86JIq8KsawwvhbuQxdUJyJMu-Zhkj42Rp-jIUcgXNNEUO_oKVNMYMQ")
				.queryParam("page_size", 100,"page",1,"total_required",true)
				.when()
				.get()
				.then()
				.assertThat().statusCode(200).extract().response();
		JsonPath jsonPath = response.jsonPath();
		String product_ID = jsonPath.getString("products.id");
		if(product_ID.contains(product_ID))
		{
			System.out.println("Product ID "+product_ID+" creation verified");
		}
		else
		{
			System.out.println("Creation of Product ID "+product_ID+" could not be verified");
		}

	}
}
