package datamodel;

import java.util.HashMap;
import java.util.Map;

public enum DaoItems {
	instance;
	private Map<String, Item> contentProvider = new HashMap<String, Item>();

	private DaoItems() {

		//EJERCICIO: Crear algunos items por defecto para las pruebas.
		Item item = new Item("REF00001", "Cement", 4l);
		contentProvider.put(item.getId(), item);
		item = new Item("REF00002", "Flour", 0l);
		contentProvider.put(item.getId(), item);
		item = new Item("REF00003", "Wine", 0l);
		contentProvider.put(item.getId(), item);
		item = new Item("REF00004", "Screws", 0l);
		contentProvider.put(item.getId(), item);
	}
	public Map<String, Item> getModel(){
		return contentProvider;
	}
}
