package com.ineuron.domain.device.valueobject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeviceType {

	private Integer id;
	private String name;

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceType.class);

	/*public void addType(ProductRepository productRepository) throws RepositoryException {
		productRepository.addType(this);
		
	}

	public void updateType(ProductRepository productRepository) throws RepositoryException {
		productRepository.updateType(this);
	}

	
	public void deleteAttibuteCategory(ProductRepository productRepository) throws RepositoryException {
		productRepository.deleteType(this);
		
	}*/
	
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
	

}
