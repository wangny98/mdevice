package com.ineuron.domain.product.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ineuron.common.exception.RepositoryException;
import com.ineuron.dataaccess.db.INeuronRepository;
import com.ineuron.domain.product.repository.ProductRepository;
import com.ineuron.domain.product.valueobject.FormulaMaterial;
import com.ineuron.domain.product.valueobject.Material;

public class Formula {
	
	private String id;
	private String name;
	private String description;
	private List<FormulaMaterial> materialSettings;
	private List<Material> materials;
	private List<Material> allMaterials;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Formula.class);
	
	public void addFormula(ProductRepository repository) throws RepositoryException{
		if(id == null){
			id = UUID.randomUUID().toString();
		}
		
		repository.addFormula(this);
	}
	
	public void updateFormula(ProductRepository repository) throws RepositoryException{
		repository.updateFormula(this);
	}
	
	public void deleteFormula(ProductRepository repository) throws RepositoryException {
		repository.deleteFormula(this);
		
	}
	
	public void init(INeuronRepository repository) throws RepositoryException {
		if(materialSettings == null){
			materialSettings = repository.select("getFormulaMaterials", this);
		}
		if(materialSettings != null && materials == null){
			List<Integer> materialIds = new ArrayList<Integer>();
			for(FormulaMaterial material : materialSettings){
				materialIds.add(material.getMaterialId());
			}
			
			LOGGER.info("materialIds size in materialSettings = " + materialIds.size());
			if(materialIds.size() > 0){
				materials = repository.select("getMaterialByIds", materialIds);
				LOGGER.info("material number is " + materials.size() + " in formula " + name);
			}
			
		}
		allMaterials = repository.select("getMaterials", null);
	}
	
	
	@Override
	public String toString(){
		StringBuilder formulaStr = new StringBuilder();
		formulaStr.append("id=").append(id).append(";")
		.append("name=").append(name).append(";")
		.append("description=").append(description).append("||");
		if(materialSettings != null){
			for(FormulaMaterial material : materialSettings){
				formulaStr.append("materialId=").append(material.getMaterialId()).append(";")
				.append("materialQuantity=").append(material.getMaterialQuantity()).append("||");
			}
		}
		
		return formulaStr.toString();
		
	}
	
	public String getId() {
		if(id == null){
			id = UUID.randomUUID().toString();
		}
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<FormulaMaterial> getMaterialSettings() {
		return materialSettings;
	}

	public void setMaterialSettings(List<FormulaMaterial> materialSettings) {
		this.materialSettings = materialSettings;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Material> getAllMaterials() {
		return allMaterials;
	}

	public void setAllMaterials(List<Material> allMaterials) {
		this.allMaterials = allMaterials;
	}
	
	
}
