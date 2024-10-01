package com.pastebin.mapper;

import com.pastebin.model.dto.comment.CommentDto;
import com.pastebin.model.dto.comment.CommentToCreateDto;
import com.pastebin.model.dto.comment.CommentToUpdateDto;
import com.pastebin.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentToCreateDto commentDto);

    void update(CommentToUpdateDto dto, @MappingTarget Comment comment);
}