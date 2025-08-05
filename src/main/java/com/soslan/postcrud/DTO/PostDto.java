package com.soslan.postcrud.DTO;

import com.soslan.postcrud.model.Platform;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private String text;
    private String mediaUrl;
    private Platform platform;
    private LocalDateTime publishAt;
}
