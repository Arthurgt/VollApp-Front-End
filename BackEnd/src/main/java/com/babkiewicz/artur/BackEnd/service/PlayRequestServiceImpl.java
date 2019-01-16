package com.babkiewicz.artur.BackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.babkiewicz.artur.BackEnd.model.PlayRequest;
import com.babkiewicz.artur.BackEnd.repository.PlayRequestRepository;

@Service
@Transactional
public class PlayRequestServiceImpl implements PlayRequestService {
	@Autowired
	PlayRequestRepository requestRepository;

	@Override
	public PlayRequest save(PlayRequest playRequest) {
		return requestRepository.save(playRequest);
	}

	@Override
	public PlayRequest findById(long id) {
		return requestRepository.findById(id).get();
	}

	@Override
	public List<PlayRequest> findAll() {
		return (List<PlayRequest>) requestRepository.findAll();
	}

}
