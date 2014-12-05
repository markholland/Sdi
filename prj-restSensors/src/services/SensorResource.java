package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import datamodel.DaoSensors;
import datamodel.Sensor;

public class SensorResource {

	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  String id;
	  public SensorResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = id;
	  }
	  
	  //Application integration     
	  @GET
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Sensor getSensor() {
	    Sensor sens = DaoSensors.instance.getModel().get(id);
	    if(sens==null)
	      throw new RuntimeException("Get: Sensor " + id +  " not found");
	    return sens;
	  }
	  
	  @GET
	  @Path("wait")
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Sensor getSensorWaited(
			  @DefaultValue("0.0") @QueryParam("value") double oldValue,
			  @DefaultValue("0.0") @QueryParam("error") double error,
			  @DefaultValue("500") @QueryParam("delay") int minDelay
			  ) {	
	    try {
			Thread.sleep(minDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    Sensor sens = DaoSensors.instance.getModel().get(id);
	    if(sens==null)
	      throw new RuntimeException("Get: Sensor " + id +  " not found");
	    sens.waitForValue(oldValue,error);
	    return sens;
	  }
	    
	  @PUT
	  @Consumes(MediaType.APPLICATION_XML)
	  public Response putSensor(JAXBElement<Sensor> sensor) {
	    Sensor sens = sensor.getValue();
	    return putAndGetResponse(sens);
	  }
	  
	  @DELETE
	  public void deleteSensor() {
	    Sensor sens = DaoSensors.instance.getModel().remove(id);
	    if(sens == null)
	      throw new RuntimeException("Delete: Sensor " + id +  " not found");
	  }
	   
	  
	  private Response putAndGetResponse(Sensor sens) {
	    Response res;
	    if(DaoSensors.instance.getModel().containsKey(sens.getId())) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    DaoSensors.instance.getModel().put(sens.getId(), sens);
	    return res;
	  }
	
}
