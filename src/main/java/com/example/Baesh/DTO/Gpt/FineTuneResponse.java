package com.example.Baesh.DTO.Gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FineTuneResponse {
    private String id;
    private String status;
    private String fine_tuned_model;
}
