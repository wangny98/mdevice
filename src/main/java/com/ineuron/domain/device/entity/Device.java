package com.ineuron.domain.device.entity;

import java.util.List;

import com.ineuron.common.exception.RepositoryException;
import com.ineuron.dataaccess.db.INeuronRepository;
import com.ineuron.domain.device.valueobject.DeviceType;
//import com.ineuron.domain.device.repository.ProductionRepository;

public class Device {

	private Integer id;
	private String name;
	private String code;
	private Integer typeId;
	private String dataSource; 
	private String plcAddress;
	private String plcDataFrequency;
	private Float minVolume;
	private Float maxVolume;
	private String unit;
	private Integer status;
	private String description;
	
	private DeviceType deviceType;
	//private static final Logger LOGGER = LoggerFactory.getLogger("Device");

	public void addDevice(INeuronRepository repository) throws RepositoryException {
		repository.add("addDevice", this);
	}

	public void updateDevice(INeuronRepository repository) throws RepositoryException {
		repository.update("updateDevice", this);
	}
	
	public void deleteDevice(INeuronRepository repository) throws RepositoryException {
		repository.delete("deleteDevice", this);
	}
	
	public void init(INeuronRepository repository) throws RepositoryException{
		deviceType=repository.selectOne("getDeviceTypeById", typeId);
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

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Float getMinVolume() {
		return minVolume;
	}

	public void setMinVolume(Float minVolume) {
		this.minVolume = minVolume;
	}

	public Float getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(Float maxVolume) {
		this.maxVolume = maxVolume;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getPlcAddress() {
		return plcAddress;
	}

	public void setPlcAddress(String plcAddress) {
		this.plcAddress = plcAddress;
	}

	public String getPlcDataFrequency() {
		return plcDataFrequency;
	}

	public void setPlcDataFrequency(String plcDataFrequency) {
		this.plcDataFrequency = plcDataFrequency;
	}

}
