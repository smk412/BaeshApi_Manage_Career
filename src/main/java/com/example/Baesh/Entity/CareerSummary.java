package com.example.Baesh.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CareerSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String role;
    private String achievement;
    private String tags;

    private LocalDateTime createdAt = LocalDateTime.now();
    // ✅ 4개 인자용 생성자 추가
    public CareerSummary(String title, String role, String achievement, String tags) {
        this.title = title;
        this.role = role;
        this.achievement = achievement;
        this.tags = tags;
    }
}
