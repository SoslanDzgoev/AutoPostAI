package com.soslan.postcrud.mapper;

import com.soslan.postcrud.dto.PostDto;
import com.soslan.postcrud.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toDto(Post post);

    @Mapping(target = "id", ignore = true)
    Post toEntity(PostDto dto);

    List<PostDto> toDtoList(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(PostDto dto, @MappingTarget Post post);
}
