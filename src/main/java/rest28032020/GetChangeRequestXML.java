package rest28032020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetChangeRequestXML {

	public static void main(String[] args) {

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("type", "emergency");
		paramMap.put("sysparm_fields", "sys_id, number");
		
		RestAssured.baseURI = "https://dev87897.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "pH2uWtOpzHM1");
		Response response = RestAssured.given().params(paramMap).accept(ContentType.XML).get();
		
		response.prettyPrint();
		XmlPath responsePath = response.xmlPath();
		List<Object> list = responsePath.getList("response.result.number");
		for (Object responsepathlist : list) {
			System.out.println(responsepathlist);
			
		}
		
	}

}
