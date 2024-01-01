package com.resotechs;

import com.resotechs.entities.Task;
import com.resotechs.entities.User;
import com.resotechs.enums.RoleType;
import com.resotechs.enums.TaskStatus;
import com.resotechs.repositories.TaskRepository;
import com.resotechs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Taskify implements CommandLineRunner {


    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Taskify.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPhone("9999999999");
        user.setPassword(passwordEncoder.encode("test"));
        user.setRoleType(RoleType.ADMIN);
        user.setActive(true);
        userRepository.save(user);

        taskRepository.saveAll(List.of(
                Task.builder()
                        .title("Implement a Login Page")
                        .description("Create a login page using HTML, CSS, and JavaScript. " +
                                "The page should include fields for username and password, and a ‘Submit’ button. " +
                                "Implement basic form validation to check if the fields are not empty")
                        .user(user)
                        .status(TaskStatus.Pending)
                        .build(),
                Task.builder()
                        .title("Write a Blog Post")
                        .description("Write a blog post about the latest trends in artificial intelligence. " +
                                "The post should be at least 500 words long and include at least three key points.")
                        .user(user)
                        .status(TaskStatus.Pending)
                        .build(),
                Task.builder()
                        .title("Design a Logo")
                        .description("Design a logo for a fictional company called “TechBoost”. " +
                                "The logo should be simple, modern, and reflect the tech industry.")
                        .user(user)
                        .status(TaskStatus.Pending)
                        .build(),
                Task.builder()
                        .title("Organize a Team Meeting")
                        .description("Schedule a team meeting for next Monday to discuss the progress of our current project. " +
                                "Ensure all team members are available and prepare an agenda for the meeting.")
                        .user(user)
                        .status(TaskStatus.Pending)
                        .build()
        ));

        userRepository.saveAll(
                List.of(
                        User.builder()
                                .name("user1")
                                .email("user1@gmail.com")
                                .active(false)
                                .password("test")
                                .roleType(RoleType.USER)
                                .phone("0000000000")
                                .build(),
                        User.builder()
                                .name("user1")
                                .email("user2@gmail.com")
                                .active(false)
                                .roleType(RoleType.USER)
                                .password("test")
                                .phone("0000000002")
                                .build(),
                        User.builder()
                                .name("admin")
                                .email("admin@gmail.com")
                                .active(true)
                                .roleType(RoleType.ADMIN)
                                .password("admin")
                                .phone("0000000003")
                                .build()

                )
        );
    }
}
