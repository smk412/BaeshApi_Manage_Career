package com.example.Baesh.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceSummaryRequest {
    private String title;
    private String role;
    private String achievement;
    private List<String> tags;
}