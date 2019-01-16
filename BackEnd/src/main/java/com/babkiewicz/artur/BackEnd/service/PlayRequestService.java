package com.babkiewicz.artur.BackEnd.service;

import java.util.List;
import com.babkiewicz.artur.BackEnd.model.PlayRequest;

public interface PlayRequestService {
	PlayRequest save(PlayRequest playRequest);
	PlayRequest findById(long id);
	List<PlayRequest> findAll();
}
