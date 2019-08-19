package com.labs.sauti.service;

import com.labs.sauti.model.user.Role;
import com.labs.sauti.model.user.User;
import com.labs.sauti.model.user.UserRole;
import com.labs.sauti.repository.RoleRepository;
import com.labs.sauti.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        if (user == null) return null; // TODO throw
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    @Override
    @Transactional
    public Long save(User user) {
        User foundUser = userRepository.findUserByUsername(user.getUsername());
        if (foundUser != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username taken");

        User newUser = new User(
                user.getUsername(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getLocation(),
                user.getGender()
        );
        Role roleUser = roleRepository.findByName("user");
        newUser.getUserRoles().add(new UserRole(newUser, roleUser));

        return userRepository.save(newUser).getUserId();
    }

    @Override
    @Nullable
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserByUsername(authentication.getName());
    }

    @Override
    public void delete() {
        userRepository.delete(getAuthenticatedUser());
    }
}
