package com.labs.sauti.repository;

import com.labs.sauti.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

}
