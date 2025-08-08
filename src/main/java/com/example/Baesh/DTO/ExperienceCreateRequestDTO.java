package com.example.Baesh.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor

// 새로운 이력 관리를 생성할 때 필요한 데이터를 정의

public class ExperienceCreateRequestDTO {
    private String title;
    private String role;
    private String startDate;
    private String endDate;
    private String achievement;
    private List<String> tags; // 프론트에서 배열로 보내주므로 List<String>으로 받습니다.
}
