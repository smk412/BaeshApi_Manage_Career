package com.example.Baesh.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerSummaryResponse {
    private String jobSummary;
    private String skillSummary;
    private String interestSummary;
}

