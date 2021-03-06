package com.ineuron.domain.device.valueobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ineuron.common.exception.INeuronException;
import com.ineuron.common.exception.RepositoryException;
import com.ineuron.dataaccess.db.INeuronRepository;
import com.ineuron.domain.device.valueobject.DeviceAttributeCategory;

public class DeviceAttribute {

	private Integer id;
	private String name;
	private String code;
	private Integer deviceAttributeCategoryId;
	private String description;
	
	private DeviceAttributeCategory deviceAttributeCategory;

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceAttribute.class);

	public void addAttribute(INeuronRepository repository) throws RepositoryException {
		repository.add("addDeviceAttribute", this);
		
	}

	public void updateAttribute(INeuronRepository repository) throws RepositoryException {
		repository.update("updateDeviceAttribute", this);
	}

	
	public void deleteAttibute(INeuronRepository repository) throws RepositoryException {
		repository.delete("deleteDeviceAttribute", this);
		
	}
	
	public void init(INeuronRepository repository) throws RepositoryException{
		
		deviceAttributeCategory=repository.selectOne("getDeviceAttributeCategoryById", deviceAttributeCategoryId);	
	
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDeviceAttributeCategoryId() {
		return deviceAttributeCategoryId;
	}

	public void setDeviceAttributeCategoryId(Integer deviceAttributeCategoryId) {
		this.deviceAttributeCategoryId = deviceAttributeCategoryId;
	}
	
	public DeviceAttributeCategory getDeviceAttributeCategory() {
		return deviceAttributeCategory;
	}

	public void setDeviceAttributeCategory(DeviceAttributeCategory deviceAttributeCategory) {
		this.deviceAttributeCategory = deviceAttributeCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
