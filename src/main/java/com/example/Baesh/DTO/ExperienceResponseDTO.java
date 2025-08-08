package com.example.Baesh.DTO;

import com.example.Baesh.Entity.ExperienceE;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Getter

// 데이터베이스에서 조회한 ExperienceE 엔티티를 이 DTO로 변환하여 클라이언트에 전달

public class ExperienceResponseDTO {
    private Long id;
    private String title;
    private String role;
    private String achievement;
    private String summary;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> tags;

    // Entity를 DTO로 변환하는 생성자
    public ExperienceResponseDTO(ExperienceE experience) {
        this.id = experience.getId();
        this.title = experience.getTitle();
        this.role = experience.getRole();
        this.achievement = experience.getAchievement();
        this.summary = experience.getSummary();
        this.startDate = experience.getStartDate();
        this.endDate = experience.getEndDate();
        // DB에 "React,Figma" 형태로 저장된 문자열을 다시 리스트로 변환
        if (experience.getTags() != null && !experience.getTags().isEmpty()) {
            this.tags = Arrays.asList(experience.getTags().split("\\s*,\\s*"));
        }
    }
}
