package com.babkiewicz.artur.BackEnd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import com.babkiewicz.artur.BackEnd.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	
	User findByEmailIgnoreCase(String username);

}
