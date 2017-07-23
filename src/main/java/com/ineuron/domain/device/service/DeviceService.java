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
import com.ineuron.domain.device.entity.Device;
import com.ineuron.domain.device.repository.DeviceRepository;
import com.ineuron.domain.device.valueobject.DeviceAttribute;
import com.ineuron.domain.device.valueobject.DeviceAttributeCategory;


public class DeviceService {

	@Inject
	INeuronRepository repository;

	@Inject
	DeviceRepository deviceRepository;


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

	public void createAttribute(DeviceAttribute deviceAttribute) throws RepositoryException {
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
				"getAttributesByCategoryId", attributeCategoryId);
		return attributeList;
	}

	public DeviceAttribute getAttributeByName(String name) throws RepositoryException {
		DeviceAttribute deviceAttribute = repository.selectOne("getAttributeByName", name);
		return deviceAttribute;
	}

	

}
