package com.soslan.postcrud.service.logic;

import com.soslan.postcrud.model.Post;
import com.soslan.postcrud.model.PostStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostStatusResolver {

    public PostStatus determineStatus(Post post) {
        LocalDateTime publishAt = post.getPublishAt();
        if (publishAt == null)
            return PostStatus.DRAFT;
        if (publishAt.isAfter(LocalDateTime.now()))
            return PostStatus.SCHEDULED;
        return PostStatus.PUBLISHED;
    }
}
