package com.resotechs.entities;

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

    @ManyToOne
    private User user;
}
