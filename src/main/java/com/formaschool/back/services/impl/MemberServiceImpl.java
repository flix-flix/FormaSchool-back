package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.member.MemberUserNamePict;
import com.formaschool.back.models.Member;
import com.formaschool.back.models.Salon;
import com.formaschool.back.repositories.MemberRepository;
import com.formaschool.back.services.MemberService;
import com.formaschool.back.services.PermissionService;
import com.formaschool.back.services.SalonService;

public class MemberServiceImpl extends CRUDServiceImpl<Member> implements MemberService {

	private MemberRepository repo;
	private PermissionService permissionService;
	private SalonService salonService;


	public MemberServiceImpl(MemberRepository repo, PermissionService permissionService, SalonService salonService, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
		this.permissionService = permissionService;
		this.salonService = salonService;
	}

	@Override
	public List<Member> findAllByUserId(String userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public List<Member> findMembersByTeamId(String teamId) {
		return this.repo.findMemberByTeamId(teamId);
	}

	@Override
	public List<MemberUserNamePict> findMembersInTeamWithoutPermissionForSalon(String salonId) {
		Salon salon = this.salonService.get(salonId);
		List<Member> members = this.repo.findMemberByTeamId(salon.getTeam().getId());
		return members.stream()
		.filter(member -> this.permissionService.findBySalonIdAndMemberId(salonId, member.getId())==null)
		.map(member -> dto(member, MemberUserNamePict.class))
		.collect(Collectors.toList());
	}
}
