package com.soslan.postcrud.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soslan.postcrud.model.Platform;
import com.soslan.postcrud.model.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class PostDto {
    private Long id;

    @NotBlank(message = "Text cannot be empty")
    private String text;

    @NotBlank(message = "Media URL cannot be empty")
    private String mediaUrl;

    @NotNull(message = "Platform cannot be null")
    private Platform platform;

    @JsonProperty(access = READ_ONLY)
    private PostStatus status;

    private LocalDateTime publishAt;
}
