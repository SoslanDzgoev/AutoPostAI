package com.soslan.postcrud.controller;

import com.soslan.postcrud.DTO.PostDto;
import com.soslan.postcrud.mapper.PostMapper;
import com.soslan.postcrud.model.Post;
import com.soslan.postcrud.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<PostDto>> getAll() {
        List<PostDto> body = mapper.toDtoList(service.findAll());
        return ResponseEntity.ok(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto postDto) {
        Post entity = mapper.toEntity(postDto);
        Post saved = service.save(entity);
        PostDto body = mapper.toDto(saved);
        return ResponseEntity
                .created(URI.create("/api/posts/" + saved.getId()))
                .body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@PathVariable Long id, @Valid @RequestBody PostDto postDto) {
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
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
