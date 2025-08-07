package com.soslan.postcrud.DTO;

import com.soslan.postcrud.model.Platform;
import com.soslan.postcrud.model.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;

    @NotBlank(message = "Text cannot be empty")
    private String text;

    @NotBlank(message = "Media URL cannot be empty")
    private String mediaUrl;

    @NotNull(message = "Platform cannot be null")
    private Platform platform;

    @NotNull(message = "Status cannot be null")
    private PostStatus status;

    private LocalDateTime publishAt;
}
