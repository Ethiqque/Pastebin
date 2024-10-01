package com.pastebin.mapper;

import com.pastebin.model.dto.like.LikeDto;
import com.pastebin.model.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeMapper {

    LikeDto toDto(Like like);

    Like toEntity(LikeDto likeDto);
}