package com.soslan.postcrud.service;

import com.soslan.postcrud.model.Post;
import com.soslan.postcrud.repository.PostRepository;
import com.soslan.postcrud.service.logic.PostStatusResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository repository;
    private final PostStatusResolver statusResolver;

    public PostService(PostRepository repository, PostStatusResolver postStatusResolver) {
        this.repository = repository;
        this.statusResolver = postStatusResolver;
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Optional<Post> findById(UUID id) {
        return repository.findById(id);
    }

    public Post save(Post post) {
        post.setStatus(statusResolver.determineStatus(post));
        return repository.save(post);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
