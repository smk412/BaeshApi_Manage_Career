package com.example.Baesh.DTO.Gpt;

import lombok.Data;

@Data
public class ChatHistoryDTO {
    private Long id;
    private Long userId;
    private String chatData;
}
