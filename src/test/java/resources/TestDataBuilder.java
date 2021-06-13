package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuilder {
	
	public AddPlace AddPlacePayload(String name, String language, String address) {
		
		AddPlace body = new AddPlace();

		body.setAccuracy(30);
		body.setAddress(address);
		body.setLanguage(language);
		body.setName(name);
		body.setPhone_number("(+91) 983 893 3937");
		body.setWebsite("http://google.com");

		ArrayList<String> type = new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		body.setTypes(type);

		Location L = new Location();
		L.setLat(30.378374);
		L.setLng(34.43545);

		body.setLocation(L);
		
		return body;
		
		
	}
	
	public String DeletePlace(String place_id) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}

}
