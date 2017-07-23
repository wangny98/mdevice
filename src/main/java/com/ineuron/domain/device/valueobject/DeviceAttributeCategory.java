package com.ineuron.domain.device.valueobject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeviceAttributeCategory {

	private Integer id;
	private String name;

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceAttributeCategory.class);

	/*public void addAttributeCategory(ProductRepository productRepository) throws RepositoryException {
		productRepository.addAttributeCategory(this);
		
	}

	public void updateAttributeCategory(ProductRepository productRepository) throws RepositoryException {
		productRepository.updateAttributeCategory(this);
	}

	
	public void deleteAttibuteCategory(ProductRepository productRepository) throws RepositoryException {
		productRepository.deleteAttributeCategory(this);
		
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
