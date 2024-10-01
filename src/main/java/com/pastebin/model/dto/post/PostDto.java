package com.pastebin.model.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String content;
    private Long authorId;
    private Long projectId;
    private List<Long> likesIds;
    private boolean published;
//    private LocalDateTime publishedAt; //TODO
    private boolean deleted;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    private Long views;

    public void incrementViews() {
        this.views++;
    }
}
