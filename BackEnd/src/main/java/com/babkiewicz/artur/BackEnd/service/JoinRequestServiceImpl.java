package com.babkiewicz.artur.BackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babkiewicz.artur.BackEnd.model.JoinRequest;
import com.babkiewicz.artur.BackEnd.repository.JoinRequestRepository;

@Service
@Transactional
public class JoinRequestServiceImpl implements JoinRequestService {
	@Autowired
	JoinRequestRepository requestRepository;
	@Override
	public JoinRequest save(JoinRequest joinrequest) {
		return requestRepository.save(joinrequest);
	}
	@Override
	public JoinRequest findById(long id) {
		return requestRepository.findById(id).get();
	}
	@Override
	public List<JoinRequest> findAll() {
		return (List<JoinRequest>) requestRepository.findAll();
	}
}
