package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification request; 
	
	public RequestSpecification RequestSpecifications() throws IOException{
		
		
		
		RestAssured.baseURI = getGlobalValues("baseURL");
		
		
		if(request == null) 
		{
			
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));	
				request = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURL"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))			// To log the request and response to external file logging.txt
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
				return request;
		}
		
		return request;
	}

	public static String getGlobalValues(String key) throws IOException

	{
		
		FileInputStream fs = new FileInputStream(
				"C:\\Users\\SitharaK\\eclipse-workspace\\ApiFramework\\src\\test\\java\\resources\\global.properties");
		Properties prop = new Properties();
		prop.load(fs);
		return prop.getProperty(key);
	}
	
	public String getKeyValue(Response response, String key) {
		
		String response_as_string = response.asString();
		JsonPath js = new JsonPath(response_as_string);
		return js.get(key).toString();
		
		
	}

}
