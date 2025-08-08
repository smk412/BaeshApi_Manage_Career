package com.example.Baesh.Service;

import com.example.Baesh.DTO.UserSearch.GoogleSearchResponse;
import com.example.Baesh.DTO.UserSearch.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleSearchService {

    private final LocalLMMService localLMMService;

    @Value("${google.api.key}")
    private String apikey;

    @Value("${google.api.cx}")
    private String searchEngineId;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<SearchResult> googleSearch(String query){
        UriComponents uri = UriComponentsBuilder
                .fromUriString("https://www.googleapis.com/customsearch/v1")
                .queryParam("key",apikey)
                .queryParam("cx",searchEngineId)
                .queryParam("q",query)
                .queryParam("excludeTerms", "채용 구인 구직 recruit 잡 site:rocketpunch.com site:jobkorea.co.kr site:saramin.co.kr")
                .build(false);
        String finalUrl = uri.toUriString();

        ResponseEntity<GoogleSearchResponse> response=
                restTemplate.getForEntity(finalUrl, GoogleSearchResponse.class);

        return response.getBody() != null ? response.getBody().getItems() : List.of();
    }
}
