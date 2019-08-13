package com.labs.sauti.controller;

import com.labs.sauti.model.user.User;
import com.labs.sauti.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("user/delete")
    public ResponseEntity deleteUser() {
        userService.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

}
