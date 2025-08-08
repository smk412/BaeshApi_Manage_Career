package com.example.Baesh.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceSummaryResponse {
    private String title;
    private String summary;
    private List<String> tags;
}
