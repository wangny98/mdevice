package com.ineuron.domain.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.ineuron.common.exception.INeuronException;
import com.ineuron.common.exception.RepositoryException;
import com.ineuron.dataaccess.db.INeuronRepository;
import com.ineuron.domain.user.entity.User;
import com.ineuron.domain.user.valueobject.Role;
import com.ineuron.domain.user.valueobject.RolesCache;

public class UserService {

	
	@Inject
	INeuronRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	public void doRegister(User user) throws RepositoryException {
		user.addUser(repository);
	}

	public void updateUser(User user) throws RepositoryException {
		user.updateUser(repository);
	}
	
	public void deleteUser(User user) throws RepositoryException {
		user.deleteUser(repository);
	}
	
	public User doAuthenticate(User user) throws RepositoryException, INeuronException {
		User founduser = user.doAuthenticate(repository);
		if(founduser != null){
			founduser.getAllPermissions();
		}
		
		LOGGER.info("founduser.getAllPermissions().size() : " + founduser.getAllPermissions().size());
		return founduser;
	}

	public List<User> getUserList() throws RepositoryException, INeuronException {
		List<User> users = repository.select("getUsers", null);
		if(users != null && !users.isEmpty()){
			for(User user : users){
				user.getAllPermissions();
			}
		}
		return users;
	}
	
	public User getUserByUsername(String username) throws RepositoryException, INeuronException {
		User user = repository.selectOne("getUserByUsername", username);
		if(user != null){
			user.getAllPermissions();
		}
		return user;
	}
	
	public void createRole(Role role) throws RepositoryException, INeuronException {
		role.addRole(repository);
		RolesCache rolesCache = RolesCache.getRolesCache();
		rolesCache.addRole(role);
	}
	
	public void updateRole(Role role) throws RepositoryException, INeuronException {
		role.updateRole(repository);
		RolesCache rolesCache = RolesCache.getRolesCache();
		rolesCache.updateRole(role);
	}
	
	public List<Role> getRoleList() throws RepositoryException, INeuronException {
		RolesCache rolesCache = RolesCache.getRolesCache();
		return rolesCache.getRoles();
	}

	public void deleteRole(Role role) throws RepositoryException, INeuronException {
		role.deleteRole(repository);
		RolesCache rolesCache = RolesCache.getRolesCache();
		rolesCache.deleteRole(role);
	}


}
