package com.formaschool.back.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.dto.salon.SalonName;
import com.formaschool.back.dto.salon.SalonNameDesc;
import com.formaschool.back.dto.salon.SalonNameDescUpdate;
import com.formaschool.back.models.Salon;
import com.formaschool.back.repositories.SalonRepository;
import com.formaschool.back.services.SalonService;

public class SalonServiceImpl extends CRUDServiceImpl<Salon> implements SalonService {

	private SalonRepository repo;

	public SalonServiceImpl(SalonRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	@Override
	public SalonNameDesc findById(String id) {
		Salon salon = this.repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return this.mapper.convertValue(salon, SalonNameDesc.class);
	}

	@Override
	public SalonNameDesc updateSalonNameDesc(SalonNameDescUpdate dto) {
		Salon salon = this.repo.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (dto.getName() != null)
			salon.setName(dto.getName());
		if (dto.getDesc() != null)
			salon.setDesc(dto.getDesc());
		Salon result = this.repo.save(salon);
		return this.mapper.convertValue(result, SalonNameDesc.class);
	}

	@Override
	public List<SalonName> findAllSalonNameOfTeam(String teamId) {
		return repo.findByTeamId(teamId).stream().map(salon -> dto(salon, SalonName.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Salon> findAllSalonOfTeam(String teamId) {
		return repo.findByTeamId(teamId);
	}
}
