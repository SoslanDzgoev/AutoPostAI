package com.soslan.postcrud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime publishAt;
}
