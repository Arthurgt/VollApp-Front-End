package com.babkiewicz.artur.BackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babkiewicz.artur.BackEnd.model.Match;
import com.babkiewicz.artur.BackEnd.repository.MatchRepository;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {
	@Autowired
	MatchRepository matchRepository;
	@Override
	public Match save(Match match) {
		return matchRepository.save(match);
	}
	@Override
	public List<Match> findAll() {
		return (List<Match>) matchRepository.findAll();
	}
	@Override
	public Match findById(long id) {
		return matchRepository.findById(id).get();
	}
}
