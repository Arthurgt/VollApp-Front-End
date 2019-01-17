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
import com.babkiewicz.artur.BackEnd.model.User;
import com.babkiewicz.artur.BackEnd.service.MatchService;
import com.babkiewicz.artur.BackEnd.service.TeamService;
import com.babkiewicz.artur.BackEnd.service.UserService;

@RestController
public class MatchController {
	@Autowired
	private MatchService matchService;
	@Autowired
	private TeamService teamService;
	@Autowired 
	private UserService userService;

	@PostMapping(value="/saveMatch/{id}/{sender_id}")
	public ResponseEntity<Response> registration(@PathVariable("id") long id,@PathVariable("sender_id") long sender_id, @RequestBody Match match){
		Team team = teamService.findById(id);
		User user = userService.getUser(sender_id);
		
		if(user.getTeam().getId() == team.getId() && user.getCaptain().equals("1")) {
		match.setTeam1(team);
		matchService.save(match);
	    return new ResponseEntity<Response>(new Response("Match is saved successfully"), HttpStatus.OK);}
		else {
		return new ResponseEntity<Response>(new Response("Acces denied"), HttpStatus.OK);	
		}	
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
	@GetMapping(value="/getmatches/{id}/{sender_id}")
	public ResponseEntity<List<Match>> getMatches(@PathVariable("id") long id,@PathVariable("sender_id") long sender_id){
		Team team = teamService.findById(id);
        User user = userService.getUser(sender_id);	
		if(user.getTeam().getId() == team.getId()) {
		Date date = new Date();
		List<Match> matchesA = team.getMatchesAway();
		List<Match> matchesH = team.getMatchesHome();
		List<Match> result = new ArrayList<Match>();
		for(Match match : matchesA) {
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
		for(Match match : matchesH) {
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
		return new ResponseEntity<List<Match>>(result,HttpStatus.OK);}
		else {
			List<Match> empty = new ArrayList<Match>();
			return new ResponseEntity<List<Match>>(empty,HttpStatus.OK);
			}	
	}
}
