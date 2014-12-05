package services;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import datamodel.DaoSensors;
import datamodel.Sensor;

@Path("/sensors")
public class SensorsResource {

	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;

	  @GET
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public List<Sensor> getAllSensors() {
	    List<Sensor> sensors = new ArrayList<Sensor>();
	    sensors.addAll(DaoSensors.instance.getModel().values());
	    return sensors; 
	  }

	  // get the total number of records
	  @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getCount() {
	    int count = DaoSensors.instance.getModel().size();
	    return String.valueOf(count);
	  }
	  
	  @POST
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public void newSensor(@FormParam("id") String id,
	      @FormParam("description") String description,
	      @FormParam("value") String value,
	      @Context HttpServletResponse servletResponse) throws IOException {
  
		double doubleValue = 0;
		try {
			doubleValue = Float.parseFloat(value);
		} catch (NumberFormatException e) {
			return;
		}
	    Sensor sens = new Sensor(id,description,doubleValue);
	    DaoSensors.instance.getModel().put(id, sens);

	  }
	  
	  
	  // Tunnel
	  @Path("{sensor}")
	  public SensorResource tunnelToSensor(@PathParam("sensor") String id) {
	    return new SensorResource(uriInfo, request, id);
	  }
	
}
