package com.soslan.postcrud.mapper;

import com.soslan.postcrud.DTO.PostDto;
import com.soslan.postcrud.model.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toDto(Post post);

    Post toEntity(PostDto dto);

    List<PostDto> toDtoList(List<Post> posts);
}
