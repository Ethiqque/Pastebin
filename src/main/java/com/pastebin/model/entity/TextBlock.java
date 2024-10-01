package com.pastebin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
        import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentUrl; // Ссылка на хранилище, например Amazon S3.

    @Column(length = 2048)
    private String contentHash; // Хэш контента, используемый для генерации уникальной ссылки.

    private Long sizeInBytes; // Размер блока текста в байтах.

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
