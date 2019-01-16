package com.babkiewicz.artur.BackEnd.controller;
import java.util.ArrayList;
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
import com.babkiewicz.artur.BackEnd.model.Match;
import com.babkiewicz.artur.BackEnd.model.PlayRequest;
import com.babkiewicz.artur.BackEnd.model.Team;
import com.babkiewicz.artur.BackEnd.model.User;
import com.babkiewicz.artur.BackEnd.service.JoinRequestService;
import com.babkiewicz.artur.BackEnd.service.MatchService;
import com.babkiewicz.artur.BackEnd.service.TeamService;
import com.babkiewicz.artur.BackEnd.service.UserService;
import com.babkiewicz.artur.BackEnd.service.PlayRequestService;

@RestController
public class RequestController {
	@Autowired
	private JoinRequestService joinRequestService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private MatchService matchService;
	@Autowired
	private PlayRequestService playRequestService;
	
	@PostMapping(value="/saveJoinRequest/{id}")
	public ResponseEntity<Response> saveTeamRequest(@PathVariable("id") long team_id,@RequestBody Long user_id){
		User user = userService.getUser(user_id);
		Team team = teamService.findById(team_id);
		JoinRequest joinRequest = new JoinRequest(team,user,1);
		joinRequestService.save(joinRequest);
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
		JoinRequest joinRequest =joinRequestService.findById(request_id);
		joinRequest.setEnabled(0);
		joinRequestService.save(joinRequest);
		return new ResponseEntity<Response>(new Response("joinRequest has been updated successfully"), HttpStatus.OK);
	}
	@PostMapping(value="/savePlayRequest/{id}")
	public ResponseEntity<Response> savePlayRequest(@PathVariable("id") long team_id,@RequestBody Long match_id){
		Match match = matchService.findById(match_id);
		Team team = teamService.findById(team_id);
		PlayRequest playRequest = new PlayRequest(team,match,1);
		playRequestService.save(playRequest);
	    return new ResponseEntity<Response>(new Response("playRequest is saved successfully"), HttpStatus.OK);
	}
	@GetMapping(value="/playrequests/{id}")
	public ResponseEntity<List<PlayRequest>> getPlayRequests(@PathVariable("id") long team_id){
		List<PlayRequest> playRequests = playRequestService.findAll();
		List<PlayRequest> tempPlayRequests = new ArrayList<PlayRequest>();
		for(PlayRequest playRequest : playRequests) {
			if(playRequest.getMatch().getTeam1().getId()==team_id) {
				tempPlayRequests.add(playRequest);
			}
		}
		return new ResponseEntity<List<PlayRequest>>(tempPlayRequests,HttpStatus.OK);
	}
	@DeleteMapping(value="/deletePlayRequest/{id}")
	public ResponseEntity<Response> deletePlayRequests(@PathVariable("id") long request_id){
		PlayRequest playRequests =playRequestService.findById(request_id);
		playRequests.setEnabled(0);
		playRequestService.save(playRequests);
		return new ResponseEntity<Response>(new Response("playRequests has been updated successfully"), HttpStatus.OK);
	}
	@DeleteMapping(value="/acceptPlayRequest/{id}")
	public ResponseEntity<Response> acceptPlayRequests(@PathVariable("id") long request_id){
		PlayRequest playRequests =playRequestService.findById(request_id);
		Team team = teamService.findById(playRequests.getTeam().getId());
		Match match = matchService.findById(playRequests.getMatch().getId());
		playRequests.setEnabled(0);
		playRequestService.save(playRequests);
		match.setTeam2(team);
		match.setStatus("READY");
		matchService.save(match);
		return new ResponseEntity<Response>(new Response("playRequests has been accepted successfully"), HttpStatus.OK);
	}
}
