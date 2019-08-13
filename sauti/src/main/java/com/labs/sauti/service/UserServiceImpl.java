package com.labs.sauti.service;

import com.labs.sauti.model.market_price.FavoriteMarketPriceSearch;
import com.labs.sauti.model.user.Role;
import com.labs.sauti.model.user.User;
import com.labs.sauti.model.user.UserRole;
import com.labs.sauti.repository.RoleRepository;
import com.labs.sauti.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User save(User user) {
        // TODO throw username taken

        User newUser = new User(user.getUsername(), user.getPassword());
        Role roleUser = roleRepository.findByName("user");
        newUser.getUserRoles().add(new UserRole(newUser, roleUser));

        return userRepository.save(newUser);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = userRepository.findUserByUsername(authentication.getName());
        // if (authenticatedUser == null) TODO

        return authenticatedUser;
    }

    @Override
    public void delete() {
        userRepository.delete(getAuthenticatedUser());
    }
}
