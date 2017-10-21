package com.rest.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/auto")
public class AutoResource {

	AutoDAO auto = AutoDAO.getInstance();
	
	@GET
	//@Produces(MediaType.APPLICATION_XML) //specify root element of XML in Automotive bean
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("cars")
	public List<Automotive> getCars() {
		return auto.getCars();
	}
	
	@GET
	@Path("/currentCar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Automotive getCar(@QueryParam("car") String name) {
		System.out.println("In AutoResource: "+ name);
		return auto.getCar(name);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/currentCar/{car}")
	public Automotive getCurrentCar(@PathParam("car") String name) {
		System.out.println("In AutoResource: "+ name);
		return auto.getCar(name);
	}
	
	@POST
	@Path("addCar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String addInventory(Automotive car) {
		
		if(auto.addInventory(car)) {
			return "Operation Succeed";			
		}
		return "Operation Failed";
	}
	
	@PUT
	@Path("changeCar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String updateInventory(Automotive car) {
		if(auto.updateInventory(car)) {
			return "Operation Succeed";
		}
		return "Operation Failed";
	}
	
	@DELETE
	@Path("removeCar")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String deleteInventory(Automotive car) {
		if(auto.deleteInventory(car)) {
			return "Operation Succeed";
		}
		return "Operation Failed";
	}
}
