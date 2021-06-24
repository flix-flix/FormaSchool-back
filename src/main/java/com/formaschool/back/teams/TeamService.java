package com.formaschool.back.teams;

import java.util.List;

import com.formaschool.back._crud.CRUDService;
import com.formaschool.back.roles.Role;
import com.formaschool.back.roles.dto.RoleWithoutRights;
import com.formaschool.back.teams.dto.TeamNameDescFile;
import com.formaschool.back.teams.dto.TeamNameDescPict;
import com.formaschool.back.teams.dto.TeamNameDescPictUpdate;
import com.formaschool.back.teams.dto.TeamNamePict;

public interface TeamService extends CRUDService<Team> {
	public TeamNameDescPict findTeamNameDescPicById(String id);

	public TeamNameDescPict updateTeamNameDescPic(TeamNameDescPictUpdate dto);

	public TeamNamePict findTeamNamePictById(String id);

	/**
	 * Return all role which are inside the team without their rights
	 * 
	 * @param teamId the id of the team
	 * @return a list of RoleWithoutRights
	 */
	public List<RoleWithoutRights> findRoleWithoutRightsByTeamId(String teamId);

	public Team saveWithFile(TeamNameDescFile dto);

	public void addRoleToTeam(String teamId, Role role);

	public void deleteRole(String teamId, String roleId);

	public List<TeamNamePict> findAllTeamNamePict();

	public Team findTeamIdBySalonId(String salonId);
}
