package com.example.Baesh.Controller;


import com.example.Baesh.DTO.UserSearch.*;
import com.example.Baesh.Service.GoogleSearchService;
import com.example.Baesh.Service.LocalLMMService;
import com.example.Baesh.Service.UserTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class userSearchController {

    private final LocalLMMService localLMMService;
    private final UserTagService userTagService;
    private final GoogleSearchService googleSearchService;

    @PostMapping("/register_user_tag")
    public void RegisterUserTag(@RequestBody RegisterUserTagRequest request){
        List<String> tags = localLMMService.extractTag(request.getSelfIntroduction());
        userTagService.addTagsToUser(request.getUserId(),tags);
    }

    @PostMapping("/search")
    public List<UserMatchResponse> searchUsers(@RequestBody UserSearchRequest request){
        List<String> tags = localLMMService.extractTag(request.getSelfIntroduction());
        return userTagService.searchArray(tags);
    }

    @PostMapping("/external_search")
    public List<SearchResult> externalSearchUsers(@RequestBody UserSearchRequest request){
        String prompt = localLMMService.googleProgrammableSearch(request.getSelfIntroduction());
        System.out.println(prompt);
        return  googleSearchService.googleSearch(prompt);
    }

}
