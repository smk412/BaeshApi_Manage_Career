package com.example.Baesh.DTO.Gpt;

import lombok.Data;

@Data
public class AiChatResponse {
    private String genera;

    public AiChatResponse(String genera){
        this.genera = genera;
    }
}
