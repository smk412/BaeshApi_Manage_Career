package com.example.Baesh.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor

// 기존의 이력 관리를 수정할 때 필요한 데이터를 정의

public class ExperienceUpdateRequestDTO {
    private String title;
    private String role;
    private String startDate;
    private String endDate;
    private String achievement;
    private List<String> tags;
}
