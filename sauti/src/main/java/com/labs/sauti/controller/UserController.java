package com.labs.sauti.controller;

import com.labs.sauti.model.user.User;
import com.labs.sauti.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    private UserService userService;
    private TokenStore tokenStore;

    public UserController(
            UserService userService,
            TokenStore tokenStore
    ) {
        this.userService = userService;
        this.tokenStore = tokenStore;
    }

    @PostMapping("/users/user/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);

            OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
            if (refreshToken != null) {
                tokenStore.removeRefreshToken(refreshToken);
            }
        }
    }

    @PostMapping("users")
    public ResponseEntity<Long> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("users/user")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getAuthenticatedUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("users")
    public ResponseEntity deleteUser() {
        userService.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

}
