package com.babkiewicz.artur.BackEnd.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babkiewicz.artur.BackEnd.domain.Response;
import com.babkiewicz.artur.BackEnd.model.Match;
import com.babkiewicz.artur.BackEnd.model.Team;
import com.babkiewicz.artur.BackEnd.service.MatchService;
import com.babkiewicz.artur.BackEnd.service.TeamService;

@RestController
public class MatchController {
	@Autowired
	private MatchService matchService;
	@Autowired
	private TeamService teamService;
	
	@PostMapping(value="/saveMatch/{id}")
	public ResponseEntity<Response> registration(@PathVariable("id") long id, @RequestBody Match match){
		Team team = teamService.findById(id);
		match.setTeam1(team);
		matchService.save(match);
	    return new ResponseEntity<Response>(new Response("Match is saved successfully"), HttpStatus.OK);
	}
	@PostMapping(value="/updateMatch")
	public ResponseEntity<Response> registration(@RequestBody Match match){
		matchService.save(match);
	    return new ResponseEntity<Response>(new Response("Match is saved successfully"), HttpStatus.OK);
	}
	@GetMapping(value="/activematches")
	public ResponseEntity<List<Match>> getActiveMatches(){
		List<Match> matches = matchService.findAll();
		List<Match> activeMatches = new ArrayList<Match>();
		Date date = new Date();
		for(Match match : matches) {
			if(match.getStatus().equals("ACTIVE")) {
				if((match.getDate().before(date))) {
					match.setStatus("REJECTED");
					matchService.save(match);
				}
				else {
					activeMatches.add(match);			
				}
			}
		}
		return new ResponseEntity<List<Match>>(activeMatches,HttpStatus.OK);
	}
	@GetMapping(value="/getmatches/{id}")
	public ResponseEntity<List<Match>> getMatches(@PathVariable("id") long id){
		Team team = teamService.findById(id);
		Date date = new Date();
		List<Match> matches1 = team.getMatchesAway();
		List<Match> matches2 = team.getMatchesHome();
		List<Match> matches3 = new ArrayList<Match>();
		List<Match> result = new ArrayList<Match>();
		matches3.addAll(matches1);
		matches3.addAll(matches2);
		for(Match match : matches3) {
			if(match.getStatus().equals("ACTIVE")) {
				if((match.getDate().before(date))) {
					match.setStatus("REJECTED");
					matchService.save(match);
				}		
			}
			else if(match.getStatus().equals("READY")) {
				if((match.getDate().before(date))) {
					match.setStatus("PLAYED");
					matchService.save(match);
				}	
			}
			result.add(match);
		}
		return new ResponseEntity<List<Match>>(result,HttpStatus.OK);
	}
}
