package com.resotechs;

import com.resotechs.entities.User;
import com.resotechs.enums.RoleType;
import com.resotechs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class Taskify implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Taskify.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Ayush Jaiswal");
        user.setEmail("ayush@gmail.com");
        user.setPhone("8931007054");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoleType(RoleType.ADMIN);
        user.setActive(true);
        userRepository.save(user);
    }
}
