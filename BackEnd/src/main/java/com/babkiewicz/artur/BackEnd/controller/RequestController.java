package com.babkiewicz.artur.BackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.babkiewicz.artur.BackEnd.domain.Response;
import com.babkiewicz.artur.BackEnd.model.JoinRequest;
import com.babkiewicz.artur.BackEnd.model.Team;
import com.babkiewicz.artur.BackEnd.model.User;
import com.babkiewicz.artur.BackEnd.service.RequestService;
import com.babkiewicz.artur.BackEnd.service.TeamService;
import com.babkiewicz.artur.BackEnd.service.UserService;

@RestController
public class RequestController {
	@Autowired
	private RequestService requestService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeamService teamService;
	
	@PostMapping(value="/saveJoinRequest/{id}")
	public ResponseEntity<Response> registration(@PathVariable("id") long team_id,@RequestBody Long user_id){
		User user = userService.getUser(user_id);
		Team team = teamService.findById(team_id);
		JoinRequest joinRequest = new JoinRequest(team,user,1);
		requestService.save(joinRequest);
	    return new ResponseEntity<Response>(new Response("joinRequest is saved successfully"), HttpStatus.OK);
	}
	@GetMapping(value="/teamrequests/{id}")
	public ResponseEntity<List<JoinRequest>> getTeamRequests(@PathVariable("id") long team_id){
		Team team = teamService.findById(team_id);
		List<JoinRequest> joinRequests = team.getRequests();
		return new ResponseEntity<List<JoinRequest>>(joinRequests,HttpStatus.OK);
	}
	@DeleteMapping(value="/updateTeamRequest/{id}")
	public ResponseEntity<Response> updateTeamRequests(@PathVariable("id") long request_id){
		JoinRequest joinRequest =requestService.findById(request_id);
		joinRequest.setEnabled(0);
		requestService.save(joinRequest);
		return new ResponseEntity<Response>(new Response("joinRequest has been updated successfully"), HttpStatus.OK);
	}
}
