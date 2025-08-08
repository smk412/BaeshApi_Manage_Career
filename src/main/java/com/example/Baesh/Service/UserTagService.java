package com.example.Baesh.Service;

import com.example.Baesh.DTO.UserSearch.UserMatchResponse;
import com.example.Baesh.Entity.TagE;
import com.example.Baesh.Entity.UserE;
import com.example.Baesh.Repository.TagRepository;
import com.example.Baesh.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTagService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Transactional
    public void addTagsToUser(String userId, List<String> tagNames){
        //사용자 조회
        UserE userE = userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        //태그 등록 또는 가존 조회
        Set<TagE> tagsToAdd = tagNames.stream()
                .map(this::findOrCreateTag)
                .collect(Collectors.toSet());
        
        //사용자와 태크 연결
        userE.getTags().addAll(tagsToAdd);
    }


    //새로운 태그 등록 함수
    private TagE findOrCreateTag(String name){
        return tagRepository.findByName(name)
                .orElseGet(() -> tagRepository.save(new TagE(name)));
    }


    public List<UserMatchResponse> searchArray(List<String> tags) {
        List<UserE> candidates = userRepository.findUsersByAnyTag(tags);

        return candidates.stream()
                .map((UserE user)->{
                    List<String> matched = user.getTags().stream()
                            .map(TagE::getName)
                            .filter(tags::contains)
                            .toList();
                    return new UserMatchResponse(user,matched,tags.size());
                })
                .sorted(Comparator
                        .comparing(UserMatchResponse::getMatchCount).reversed())
                .collect(Collectors.toList());
    }
}
