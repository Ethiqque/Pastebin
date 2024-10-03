package com.pastebin.mapper;

import com.pastebin.model.dto.post.PostCreateDto;
import com.pastebin.model.dto.post.PostDto;
import com.pastebin.model.entity.Post;
import com.pastebin.model.dto.post.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    Post toEntity(PostCreateDto postCreateDto);

    PostDto toDto(Post post);

    @Mapping(target = "content", ignore = true)  // Контент будет устанавливаться отдельно
    PostResponse toPostResponse(Post post);
}
