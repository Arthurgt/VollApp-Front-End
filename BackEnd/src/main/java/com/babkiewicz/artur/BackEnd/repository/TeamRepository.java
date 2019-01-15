package com.babkiewicz.artur.BackEnd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.babkiewicz.artur.BackEnd.model.Team;
import com.babkiewicz.artur.BackEnd.model.User;

@Repository
public interface TeamRepository extends CrudRepository<Team,Long> {

}
