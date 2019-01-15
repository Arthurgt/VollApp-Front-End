package com.babkiewicz.artur.BackEnd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babkiewicz.artur.BackEnd.model.Team;
import com.babkiewicz.artur.BackEnd.model.User;
import com.babkiewicz.artur.BackEnd.repository.TeamRepository;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
	@Autowired
	TeamRepository teamRepository;
	
	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public List<Team> findAll() {
		return (List<Team>) teamRepository.findAll();
	}

	@Override
	public Team findById(long id) {
		return teamRepository.findById(id).get();
	}
}
