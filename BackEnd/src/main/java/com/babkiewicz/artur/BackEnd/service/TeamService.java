package com.babkiewicz.artur.BackEnd.service;

import java.util.List;
import java.util.Optional;

import com.babkiewicz.artur.BackEnd.model.Team;

public interface TeamService {
	
	Team save(Team team);
	List<Team> findAll();
	Team findById(long id);

}
