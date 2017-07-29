package com.ineuron.domain.device.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.ineuron.common.exception.RepositoryException;
import com.ineuron.dataaccess.db.INeuronRepository;
import com.ineuron.dataaccess.db.ReportData;
import com.ineuron.domain.device.entity.Device;
import com.ineuron.domain.device.valueobject.DeviceAttribute;
import com.ineuron.domain.device.valueobject.DeviceAttributeCategory;
import com.ineuron.domain.device.valueobject.DeviceType;
import com.ineuron.domain.device.valueobject.DeviceStatusData;
import com.ineuron.domain.product.entity.Product;


public class DeviceService {

	@Inject
	INeuronRepository repository;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeviceService.class);

	private boolean addAll;

	
	public Device createDevice(Device device) throws RepositoryException {
		device.addDevice(repository);
		return device;
	}

	public Device updateDevice(Device device) throws RepositoryException {
		device.updateDevice(repository);
		return device;
	}

	public void deleteDevice(Device device) throws RepositoryException {
		repository.delete("deleteDevice", device);
	}

	public List<Device> getDeviceList() throws RepositoryException {
		List<Device> deviceList = repository.select("getDevices", null);
		for (int i = 0; i < deviceList.size(); i++) {
			deviceList.get(i).init(repository);
		}
		return deviceList;
	}

	

	public Device getDeviceById(Integer deviceId) throws RepositoryException {
		Device device = repository.selectOne("getDeviceById", deviceId);
		if (device != null) {
			device.init(repository);
		}
		return device;
	}

	public Device getDeviceByName(String name) throws RepositoryException {
		Device device = repository.selectOne("getDeviceByName", name);
		//System.out.println("get device by name in service: success");
		if(device!=null) device.init(repository);
		return device;
	}
	
	public Device getDeviceByCode(String code) throws RepositoryException {
		Device device = repository.selectOne("getDeviceByCode", code);
		//System.out.println("get device by name in service: success");
		if(device!=null) device.init(repository);
		return device;
	}
	
	public List<Device> getDevicesByAttributeCode(String attributeCode) throws RepositoryException {
		List<Device> devices = repository.select("getDevicesByAttribute", attributeCode);
		//System.out.println("get device by name in service: success");
		for (int i = 0; i < devices.size(); i++) {
			devices.get(i).init(repository);
		}
		return devices;
	}

	
	//Device Status Data
	
	/*
	 * for Device Status Data Report
	 */
	/*public List<ReportData> getDeviceStatusDataByAttributeAndDeviceType(String typeId, String attributeCode)
			throws RepositoryException {
		List<DeviceStatusData> orderReport = repository.select(
				"getDeviceStatusDataByAttributeAndDeviceType", typeId, attributeCode);


		List<ReportData> reportData = new ArrayList<ReportData>();
		boolean added = false;

		for (int i = 0; i < orderReport.size(); i++) {
			List<Object> valueData = new ArrayList<Object>();
			ReportData oneReportData = new ReportData();
			added = false;
			valueData.add(orderReport.get(i).getMonth());
			valueData.add(orderReport.get(i).getAmount());

			for (int j = 0; j < reportData.size(); j++) {
				// already has the product in the report, just add the value
				if (reportData.get(j).getKey() == orderReport.get(i)
						.getProductName()) {
					reportData.get(j).getValues().add(valueData);
					added = true;
					break;
				}
			}
			// new product, so add both product name and value in the report
			// data
			if (!(added)) {
				oneReportData.setKey(orderReport.get(i).getProductName());
				oneReportData.getValues().add(valueData);
				reportData.add(oneReportData);
			}

		}
		
		 * System.out.println("final report data size: "+reportData.size()); for
		 * (int k = 0; k < reportData.size(); k++){
		 * System.out.println("products in final report data: "
		 * +reportData.get(k).getKey()); }
		 
		return reportData;
	}*/
	
	//Device Type
	
	public List<DeviceType> getDeviceTypeList() throws RepositoryException {
		List<DeviceType> deviceTypeList = repository.select("getDeviceTypes", null);
		
		return deviceTypeList;
	}
	
	
	//Device Attribute
	
	public void createAttribute(DeviceAttribute deviceAttribute) throws RepositoryException {
		//System.out.println("device service: in device attribute create: "+deviceAttribute.getName());
		deviceAttribute.addAttribute(repository);
	}

	public void updateAttribute(DeviceAttribute deviceAttribute) throws RepositoryException {
		deviceAttribute.updateAttribute(repository);
	}

	public void deleteAttribute(DeviceAttribute deviceAttribute) throws RepositoryException {
		deviceAttribute.deleteAttibute(repository);
	}

	public List<DeviceAttribute> getAttributeList() throws RepositoryException {
		List<DeviceAttribute> attributeList = repository
				.select("getDeviceAttributes", null);
		for (int i = 0; i < attributeList.size(); i++) {
			attributeList.get(i).init(repository);
		}
		return attributeList;
	}

	public List<DeviceAttribute> getAttributesByCategoryId(Integer attributeCategoryId)
			throws RepositoryException {
		List<DeviceAttribute> attributeList = repository.select(
				"getDeviceAttributesByCategoryId", attributeCategoryId);
		return attributeList;
	}

	public DeviceAttribute getAttributeByName(String name) throws RepositoryException {
		DeviceAttribute deviceAttribute = repository.selectOne("getDeviceAttributeByName", name);
		return deviceAttribute;
	}

	public DeviceAttribute getAttributeByCode(String code) throws RepositoryException {
		DeviceAttribute deviceAttribute = repository.selectOne("getDeviceAttributeByCode", code);
		return deviceAttribute;
	}
	
	//DeviceAttributeCategory
	
	public List<DeviceAttributeCategory> getAttributeCategoryList() throws RepositoryException {
		List<DeviceAttributeCategory> attributeCategoryList = repository
				.select("getDeviceAttributeCategories", null);
		
		return attributeCategoryList;
	}
	

}
