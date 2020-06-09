package practise_Court;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Day1_Covid19 {

	/*
	 * 1. Find the top 5 Country with Highest New Cases 2. Find the top 5 Country
	 * with lowest New Deaths Cases for the recent days 3. Find the Status of your
	 * Country 4. Verify the response HTTP status code = 200 5. Verify the Response
	 * Time < 600 ms 6. verify the Content Type = json
	 * 
	 * Implement the above Scenario On both PostMan and Rest Assured
	 */

	public static void main(String[] args) {
		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";
		Response response = RestAssured.get();
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		long time = response.getTime();
		System.out.println("Time taken is "+time);
		System.out.println("content type is "+ response.contentType());
		JsonPath jsonPath = response.jsonPath();
		//jsonPath.prettyPrint();

		List<String> countryList = jsonPath.getList("Country_text");
		List<String> newCases = jsonPath.getList("\"New Cases_text\"");
		if(newCases.contains(null))
		{
			newCases.remove(newCases.indexOf(null));
		}
		List<String> newDeath = jsonPath.getList("\"New Deaths_text\"");
		if(newDeath.contains(null)) 
		{
			newDeath.remove(newDeath.indexOf(null));
		}
		List<String> totalDeath = jsonPath.getList("\"Total Deaths_text\"");
		if(totalDeath.contains(null)) 
		{
			totalDeath.remove(totalDeath.indexOf(null));
		}

		//Highest New Cases
		Map<Integer, String> mapNewCases = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < newCases.size(); i++) 
		{
			String strNewCases = newCases.get(i).toString();
			if(!strNewCases.equals(""))
			{
				mapNewCases.put(Integer.parseInt(strNewCases.replaceAll("\\D", "")), countryList.get(i));
			}
		}


		List<Entry<Integer,String>> mapToList = new ArrayList<Map.Entry<Integer,String>>(mapNewCases.entrySet());
		Collections.sort(mapToList, new Comparator<Entry<Integer,String>>() {

			public int compare(Entry<Integer,String> o1, Entry<Integer,String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		System.out.println("Top 5 countries with Highest new cases");
		for (int i = 0; i < 5; i++) {
			System.out.println(mapToList.get(i));

		}


		//lowest death in recent days
		Map<Integer,String> mapNewDeath = new TreeMap<Integer,String>();
		for (int i = 0; i < newCases.size(); i++) 
		{
			String newDeathCount = newDeath.get(i).toString();
			if(!newDeathCount.equals(""))
			{
				newDeathCount = newDeathCount.replaceAll("\\D", "");
				mapNewDeath.put(Integer.parseInt(newDeathCount),countryList.get(i));
			}
		}
		
		List<Entry<Integer,String>> newDeathMapToList = new ArrayList<Map.Entry<Integer,String>>(mapNewDeath.entrySet());
		Collections.sort(newDeathMapToList, new Comparator<Entry<Integer,String>>() {

			public int compare(Entry<Integer,String> o1, Entry<Integer,String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		System.out.println("Top 5 countries with Lowest Death cases");
		for (int i = 0; i < 5; i++) {
			System.out.println(newDeathMapToList.get(i));
		}

		//India
		Response indiaResponse = RestAssured.get("India");
		JsonPath india = indiaResponse.jsonPath();
		india.prettyPrint();
		
		//response time
		if(time<600)
		{
			System.out.println("Response Time is less than 600 ms");
		}
		else
		{
			System.out.println("Response Time is more than 600 ms");
		}
		
	}
}
