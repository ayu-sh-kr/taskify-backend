package com.resotechs.entities;

import com.resotechs.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.Pending;

    @ManyToOne
    private User user;
}
