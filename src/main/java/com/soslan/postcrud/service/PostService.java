package com.soslan.postcrud.service;

import com.soslan.postcrud.model.Post;
import com.soslan.postcrud.model.PostStatus;
import com.soslan.postcrud.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    public Post save(Post post) {
        post.setStatus(determineStatus(post));
        return repository.save(post);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private PostStatus determineStatus(Post post) {
        LocalDateTime publishAt = post.getPublishAt();
        if (publishAt == null) return PostStatus.DRAFT;
        if (publishAt.isAfter(LocalDateTime.now())) return PostStatus.SCHEDULED;
        return PostStatus.PUBLISHED;
    }
}
