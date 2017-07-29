package com.ineuron.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.ineuron.api.INeuronResponse;
import com.ineuron.common.exception.InvalidAPITokenException;
import com.ineuron.common.exception.RepositoryException;
import com.ineuron.domain.device.entity.Device;
import com.ineuron.domain.device.service.DeviceService;
import com.ineuron.domain.device.valueobject.DeviceAttributeCategory;
import com.ineuron.domain.device.valueobject.DeviceAttribute;
import com.ineuron.domain.device.valueobject.DeviceType;
import com.ineuron.domain.user.service.SecurityService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/device")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class DeviceResource {

	@Inject
	private DeviceService deviceService;

	@Inject
	private SecurityService securityService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceResource.class);

	public DeviceResource() {
		super();
	}

	

	@Path("/createdevice")
	@POST
	@Timed
	public Response createDevice(final Device device, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			deviceService.createDevice(device);
			response.setValue(device);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
	}

	@Path("/deletedevice")
	@POST
	@Timed
	public Response deleteDevice(final Device device, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			deviceService.deleteDevice(device);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
	}

	@Path("/updatedevice")
	@POST
	@Timed
	public Response updateDevice(final Device device, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			deviceService.updateDevice(device);
			response.setValue(device);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
	}

	@Path("/devicelist")
	@GET
	@Timed
	public Response deviceList(@Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			List<Device> devices = deviceService.getDeviceList();
			response.setValue(devices);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}
	
	
	@Path("/devicetypelist")
	@GET
	@Timed
	public Response deviceTypeList(@Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			List<DeviceType> deviceTypes = deviceService.getDeviceTypeList();
			response.setValue(deviceTypes);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}
	

	/*@Path("/devicelistbycategory")
	@POST
	@Timed
	public Response deviceListByCategory(final Integer deviceCategoryId, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			List<Device> devices = deviceService.getDeviceListByCategory(deviceCategoryId);
			response.setValue(devices);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}*/

	@Path("/getdevicebyname")
	@POST
	@Timed
	public Response deviceByName(final String name, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			Device device = deviceService.getDeviceByName(name);
			response.setValue(device);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}
	
	
	@Path("/getdevicebycode")
	@POST
	@Timed
	public Response deviceByCode(final String code, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			Device device = deviceService.getDeviceByCode(code);
			response.setValue(device);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}
	
	@Path("/getdevicesbyattribute")
	@POST
	@Timed
	public Response devicesByAttribute(final String attributeCode, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			List<Device>  devices = deviceService.getDevicesByAttributeCode(attributeCode);
			response.setValue(devices);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}
	

	@Path("/devicebyid")
	@GET
	@Timed
	public Response deviceById(@QueryParam("id") Integer deviceId, @Context HttpHeaders httpHeader,
			@QueryParam("debug") boolean debug) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, debug);
			Device device = deviceService.getDeviceById(deviceId);
			response.setValue(device);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
	}

	@Path("/deviceattributecategorylist")
	@GET
	@Timed
	public Response deviceAttributeCategoryList(@Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			List<DeviceAttributeCategory> deviceAttributeCategories = deviceService.getAttributeCategoryList();
			response.setValue(deviceAttributeCategories);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}

	@Path("/deviceattributelist")
	@GET
	@Timed
	public Response deviceAttributeList(@Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			List<DeviceAttribute> deviceAttributes = deviceService.getAttributeList();
			response.setValue(deviceAttributes);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
	}

	@Path("/deviceattributesbycategoryid")
	@POST
	@Timed
	public Response deviceAttributesByCategoryId(final Integer deviceAttributeCategoryId, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			List<DeviceAttribute> deviceAttributes = deviceService.getAttributesByCategoryId(deviceAttributeCategoryId);
			response.setValue(deviceAttributes);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
	}

	@Path("/getdeviceattributebyname")
	@POST
	@Timed
	public Response deviceAttributeByName(final String name, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			DeviceAttribute deviceAttribute = deviceService.getAttributeByName(name);
			response.setValue(deviceAttribute);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}
	
	
	@Path("/getdeviceattributebycode")
	@POST
	@Timed
	public Response deviceAttributeByCode(final String code, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			DeviceAttribute deviceAttribute = deviceService.getAttributeByCode(code);
			response.setValue(deviceAttribute);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}
	

	
	@Path("/createdeviceattribute")
	@POST
	@Timed
	public Response createDeviceAttribute(final DeviceAttribute deviceAttribute, @Context HttpHeaders httpHeader) {
		try {
			//System.out.println("in create attribute resource");
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			deviceService.createAttribute(deviceAttribute);
			response.setValue(deviceAttribute);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
	}

	@Path("/updatedeviceattribute")
	@POST
	@Timed
	public Response updateDeviceAttribute(final DeviceAttribute deviceAttribute, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			deviceService.updateAttribute(deviceAttribute);
			response.setValue(deviceAttribute);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}		
	}

	@Path("/deletedeviceattribute")
	@POST
	@Timed
	public Response deleteDeviceAttribute(final DeviceAttribute deviceAttribute, @Context HttpHeaders httpHeader) {
		try {
			INeuronResponse response = new INeuronResponse(securityService, httpHeader, false);
			deviceService.deleteAttribute(deviceAttribute);
			return Response.ok(response).build();
		} catch (RepositoryException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidAPITokenException e) {
			LOGGER.error(e.getMessage(), e.getRootCause());
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

}
