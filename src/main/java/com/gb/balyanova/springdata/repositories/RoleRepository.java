package com.gb.balyanova.springdata.repositories;

import com.gb.balyanova.springdata.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
