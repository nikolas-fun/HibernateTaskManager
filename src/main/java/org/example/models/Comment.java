package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String text;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;

}
