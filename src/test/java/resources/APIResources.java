package resources;

public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),				// all the methods are listed in enum with desired return attribute
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	APIResources(String resource){											// Have to create a constructer when creating a enum class, String resource is there and matching the return attribute of method list
		
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
