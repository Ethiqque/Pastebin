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
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hash; // Уникальный хэш, используемый для ссылки на контент.

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @OneToOne
    @JoinColumn(name = "text_block_id", nullable = false)
    private TextBlock textBlock;
}
