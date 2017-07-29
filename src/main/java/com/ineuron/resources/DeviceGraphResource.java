package com.ineuron.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/graph")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class DeviceGraphResource {


	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceGraphResource.class);

	public DeviceGraphResource() {
		super();
	}

	

	@Path("/save")
	@POST
	@Timed
	public Response createDevice(final String data, @Context HttpHeaders httpHeader) {
		System.out.println("saving...........................................................");
		System.out.println(data);
	
		return Response.ok("data from backend : " + data).build();
		
	}


	@Path("/read")
	@GET
	@Timed
	public Response deviceList(@Context HttpHeaders httpHeader) {
		return Response.ok("({states:{rect7:{type:'task',text:{text:'任务22'}, attr:{ x:192, y:317, width:100, height:50}, props:{text:{value:'任务22'},assignee:{value:''},form:{value:''},desc:{value:''}}}},props:{props:{name:{value:'新建流程111222'},key:{value:''},desc:{value:''}}}})").build();
		
	}

	
}
