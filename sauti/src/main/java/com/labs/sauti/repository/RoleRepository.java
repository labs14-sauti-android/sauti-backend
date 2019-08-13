package com.labs.sauti.repository;

import com.labs.sauti.model.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

}
