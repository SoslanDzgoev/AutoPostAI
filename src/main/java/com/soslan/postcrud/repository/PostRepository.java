package com.soslan.postcrud.repository;

import com.soslan.postcrud.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
