package com.soslan.postcrud.controller;

import com.soslan.postcrud.DTO.PostDto;
import com.soslan.postcrud.mapper.PostMapper;
import com.soslan.postcrud.model.Post;
import com.soslan.postcrud.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")

public class PostController {
    private final PostService service;
    private final PostMapper mapper;

    public PostController(PostService service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<PostDto> getAll() {
        return mapper.toDtoList(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PostDto create(@RequestBody PostDto postDto) {
        Post entity = mapper.toEntity(postDto);
        Post saved = service.save(entity);
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody PostDto postDto) {
        return service.findById(id)
                .map(existing -> {
                    postDto.setId(id);
                    Post updated = service.save(mapper.toEntity(postDto));
                    return ResponseEntity.ok(mapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
