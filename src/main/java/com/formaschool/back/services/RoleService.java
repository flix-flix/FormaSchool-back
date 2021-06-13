package com.formaschool.back.services;

import java.util.List;

import com.formaschool.back.dto.roles.RoleWithDescription;
import com.formaschool.back.dto.roles.RoleWithoutRights;
import com.formaschool.back.models.Role;

public interface RoleService extends CRUDService<Role>{

	public List<RoleWithoutRights> findAllWithoutRights();
	
	public RoleWithDescription findRoleWithDescriptionById(String id);
	
	public Role updateFromRoleWithDesc(RoleWithDescription role);
}
