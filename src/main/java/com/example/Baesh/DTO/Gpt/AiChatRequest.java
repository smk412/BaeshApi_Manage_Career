package com.example.Baesh.DTO.Gpt;

import lombok.Data;

@Data
public class AiChatRequest {
    private String message;
    private Long userId;
}
