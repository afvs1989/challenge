package com.afvs.challenge.persistence.repositories;

import com.afvs.challenge.persistence.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
