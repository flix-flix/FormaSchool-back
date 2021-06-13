package com.formaschool.back.dto.roles;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleWithDescription {

	private String id;
	private String name;
	private String color;
	private List<DescriptionBoolean> rights;
	private List<DescriptionBoolean> commonRights;
}
