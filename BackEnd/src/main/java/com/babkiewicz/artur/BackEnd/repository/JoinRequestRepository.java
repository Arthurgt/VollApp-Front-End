package com.babkiewicz.artur.BackEnd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.babkiewicz.artur.BackEnd.model.JoinRequest;

@Repository
public interface JoinRequestRepository extends CrudRepository<JoinRequest,Long> {


}
