package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

public class stepDefinitons extends Utils {

	RequestSpecification request_spec; // Declare request variable in the class so it can be access over all the methods							
	ResponseSpecification response_spec;
	Response response;
	TestDataBuilder pay_load = new TestDataBuilder();
	static String place_id;
	

	@Given("App API Payload with {string} , {string} and {string}")
	public void app_api_payload_with_and(String name, String language, String address) throws IOException {

		System.out.println("Add Request is building");
		
		request_spec = given()
				.spec(RequestSpecifications())
				.body(pay_load.AddPlacePayload(name, language, address));
		
		System.out.println("This is a useless Print statement");
		

	}

	@When("User calls {string} with {string} HTTP request")
	public void user_calls_add_place_api_with_post_http_request(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);		// get value of returns the resource object AddPlaceAPI from the APIResources class
			System.out.println(resourceAPI.getResource());

		response_spec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectHeader("server", "Apache/2.4.18 (Ubuntu)")
				.expectContentType(ContentType.JSON)
				.build();

		if(method.equalsIgnoreCase("POST"))
		response = request_spec
				.when()
				.post(resourceAPI.getResource())
				.then()
				.spec(response_spec)
				.extract().response();
		
		else if(method.equalsIgnoreCase("GET"))
			response = request_spec
			.when()
			.get(resourceAPI.getResource())
			.then()
			.spec(response_spec)
			.extract().response();
		
		JsonPath js = new JsonPath(response.asString());
		System.out.println(js.get().toString());
		
		
	}

	@Then("The API call is success with code {int}")
	public void the_api_call_is_success_with_code(Integer status_code) {
		
		System.out.println(response.asString());
		assertEquals(response.getStatusCode(), 200);
		
	}

	@And("the {string} reponse is {string}")
	public void the_reponse_is(String Key, String expected_value) {
		
		
		getKeyValue(response, Key).equals(expected_value);
		assertEquals(getKeyValue(response, Key)
				, expected_value);
		
	}
	
	@And("verify place_Id created maps to the {string} using {string}")
	public void verify_place_id_created_maps_to_the_using(String expected_name, String resource) throws IOException {
	    
		place_id = getKeyValue(response, "place_id").toString();
		
		request_spec = given()
				.spec(RequestSpecifications())
				.queryParam("place_id", place_id);
		user_calls_add_place_api_with_post_http_request(resource, "GET");		// You can call other step definition present within the file 
		
		getKeyValue(response, "name").toString().equalsIgnoreCase(expected_name);
		
		assertEquals(getKeyValue(response, "name")
				, expected_name);
		
		System.out.println("In the Get Method" + place_id);
		System.out.println(getKeyValue(response, "name").toString());
	}
	
	@Given("App API Delete Place with place_id")
	public void app_api_delete_place() throws IOException {
	    
		request_spec = given()
				.spec(RequestSpecifications()).body(pay_load.DeletePlace(place_id));
		
		System.out.println(place_id);
		System.out.println(request_spec.toString());
		System.out.println(pay_load.DeletePlace(place_id));
	}


}
