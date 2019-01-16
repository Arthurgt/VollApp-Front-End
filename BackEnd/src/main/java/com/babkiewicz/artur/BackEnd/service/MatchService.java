package com.babkiewicz.artur.BackEnd.service;

import java.util.List;

import com.babkiewicz.artur.BackEnd.model.Match;

public interface MatchService {
	Match save(Match match);
	List<Match> findAll();
	Match findById(long id);
}
