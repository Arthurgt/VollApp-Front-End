package com.babkiewicz.artur.BackEnd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.babkiewicz.artur.BackEnd.model.PlayRequest;

@Repository
public interface PlayRequestRepository extends CrudRepository<PlayRequest,Long> {

}
