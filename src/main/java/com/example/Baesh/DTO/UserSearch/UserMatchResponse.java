package com.example.Baesh.DTO.UserSearch;

import com.example.Baesh.Entity.UserE;
import lombok.Data;

import java.util.List;

@Data
public class UserMatchResponse {
    private Long id;
    private String userId;
    private String name;
    private String title;
    private String location;
    private String imageUrl;
    private List<String> matchedTags;
    private float matchCount;
    private float meTagCount;

    public UserMatchResponse(UserE user,List<String> matchedTags,float meTagCount){
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.title = user.getIntro();
        this.imageUrl = user.getImageUrl();
        this.location = user.getResidence();
        this.matchedTags = matchedTags;
        this.matchCount = matchedTags.size();
        this.meTagCount = meTagCount;
    }
}
