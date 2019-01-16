package com.babkiewicz.artur.BackEnd.service;

import java.util.List;

import com.babkiewicz.artur.BackEnd.model.JoinRequest;
import com.babkiewicz.artur.BackEnd.model.Team;


public interface JoinRequestService {
	JoinRequest save(JoinRequest joinrequest);
	JoinRequest findById(long id);
	List<JoinRequest> findAll();
}
