package com.labs.sauti;

import com.labs.sauti.model.user.Role;
import com.labs.sauti.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//@Component
public class SeedData implements CommandLineRunner {

    private RoleRepository roleRepository;

    public SeedData(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role userRole = new Role("user");

        roleRepository.save(userRole);
    }
}
