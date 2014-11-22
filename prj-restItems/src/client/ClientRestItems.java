package client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.ClientBuilder;

import datamodel.Item;

public class ClientRestItems {

	private static GenericType<List<Item>> genericType = new GenericType<List<Item>>(){};
	
	private static UriBuilder getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/prj-restItems/rest");
	}
	
	public static void main(String[] args) {  
		
	    Client client = ClientBuilder.newClient();
	    
	    //List all items
		WebTarget target =  client.target(getBaseURI().path("items").build());
		//(GET) XML Request
		String result = target.request("application/xml").get(String.class); 
		System.out.println("Result: "+result);
		
		//EJERCICIO: Listar todos los items en JSON.
		URI resourceUri = getBaseURI().path("items").build();
		target = client.target(resourceUri);
		List<Item> items = target.request("application/json").get(genericType);
		for(Item i : items){
			System.out.println(i.toString());
		}
		String itemId ="REF00001";
		long amount = 2;
		Entity<String> entity = Entity.entity(""+amount,MediaType.TEXT_PLAIN_TYPE);
		
		//EJERCICIO: Decrementar la cantidad del item "itemId" 
		//           en una cantidad "amount" (llamada PUT enviando "entity").
		resourceUri = getBaseURI().path("items").path(itemId).path("dec").build();
		target = client.target(resourceUri);
		target.request("application/json").put(entity);
		
		//EJERCICIO: Obtener y mostrar en pantalla una representaci—n del item "itemId" para 
		//           comprobar que efectivamente se ha decrementado la cantidad. (GET)
		resourceUri = getBaseURI().path("items").path(itemId).build();
		target = client.target(resourceUri);
		Item item = target.request("application/json").get(Item.class);
		System.out.println(item.toString());
	}
}
