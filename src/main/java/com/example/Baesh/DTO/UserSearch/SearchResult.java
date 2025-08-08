package com.example.Baesh.DTO.UserSearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    private String title;
    private String link;
    private String snippet;
}
