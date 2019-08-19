package com.labs.sauti.service;

import com.labs.sauti.exception.exception.GenericBadRequestException;
import com.labs.sauti.model.user.Role;
import com.labs.sauti.model.user.User;
import com.labs.sauti.model.user.UserRole;
import com.labs.sauti.repository.RoleRepository;
import com.labs.sauti.repository.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    public static final int ERROR_CODE_INVALID_USERNAME = 1;
    public static final int ERROR_CODE_INVALID_PASSWORD = 2;
    public static final int ERROR_CODE_USERNAME_TAKEN = 3;

    /**
    * Must be 8 to 64 characters long
    * */
    private static final String VALID_USERNAME_REGEX = "^[!-~]{8,64}+$"; // ^[a-zA-Z0-9]{8,64}+$

    /**
     * Must be 8 to 64 characters long
     * */
    private static final String VALID_PASSWORD_REGEX = "^[!-~]{8,64}+$"; // (?=.*[a-z])(?=.*[0-9]).{8,64}

    private static final Pattern VALID_USERNAME_PATTERN = Pattern.compile(VALID_USERNAME_REGEX);
    private static final Pattern VALID_PASSWORD_PATTERN = Pattern.compile(VALID_PASSWORD_REGEX);

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
        if (!isValidUsername(user.getUsername()))
            throw new GenericBadRequestException("Invalid username", ERROR_CODE_INVALID_USERNAME, "");

        if (!isValidPassword(user.getPassword()))
            throw new GenericBadRequestException("Invalid password", ERROR_CODE_INVALID_PASSWORD, "");

        User foundUser = userRepository.findUserByUsername(user.getUsername());
        if (foundUser != null)
            throw new GenericBadRequestException("Username taken", ERROR_CODE_USERNAME_TAKEN, "");

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

    private static boolean isValidUsername(String username) {
        return VALID_USERNAME_PATTERN.matcher(username).matches();
    }

    private static boolean isValidPassword(String password) {
        return VALID_PASSWORD_PATTERN.matcher(password).matches();
    }
}
