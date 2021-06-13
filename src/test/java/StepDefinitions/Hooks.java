package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceTag")
	public void BeforeScenario() throws IOException {
		
		stepDefinitons m = new stepDefinitons();
		
		if(stepDefinitons.place_id == null)
		{
		m.app_api_payload_with_and("Bobby Johnson", "English", "India");
		m.user_calls_add_place_api_with_post_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_the_using("Bobby Johnson", "GetPlaceAPI");
		}
		
	}

}
