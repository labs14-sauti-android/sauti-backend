package com.labs.sauti.service;

import com.labs.sauti.model.user.User;

public interface UserService {

    Long save(User user);
    User getAuthenticatedUser();
    void delete();

}
