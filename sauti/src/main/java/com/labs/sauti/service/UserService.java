package com.labs.sauti.service;

import com.labs.sauti.model.user.User;

public interface UserService {

    User save(User user);
    User getAuthenticatedUser();
    void delete();

}
