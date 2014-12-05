package client;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.ClientBuilder;

public class ClientRestSensors {

	private static UriBuilder getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/prj-restSensors");
	}
	
	public static void main(String[] args) {  
		
	    Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(getBaseURI().path("rest").path("sensors").build());
		String result = target.request("application/xml").get(String.class); 
		System.out.println("All sensors:\n"+result);
		
		String sensorId = "T00001";
		URI resourceUri = getBaseURI().path("rest").path("sensors").path(sensorId).path("wait").build();
	    target = client.target(resourceUri).queryParam("delay", "500");
		while (true) {
			String result1 = target.request("application/json").get(String.class); 
			System.out.println("Sensor: "+result1);	
		}
	}	
}
