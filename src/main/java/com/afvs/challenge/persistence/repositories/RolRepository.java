package com.afvs.challenge.persistence.repositories;

import com.afvs.challenge.persistence.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<RoleEntity, Long> {
}
