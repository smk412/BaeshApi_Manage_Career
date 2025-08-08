package com.example.Baesh.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "experiences")
public class ExperienceE { // BaseTimeEntity 상속 제거

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserE user;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String role;

    private String achievement;

    @Column(length = 2000)
    private String summary;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 1000)
    private String tags;

    @Builder
    public ExperienceE(UserE user, String title, String role, String achievement, String summary, LocalDate startDate, LocalDate endDate, String tags) {
        this.user = user;
        this.title = title;
        this.role = role;
        this.achievement = achievement;
        this.summary = summary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags = tags;
    }

    public void update(String title, String role, String achievement, LocalDate startDate, LocalDate endDate, String tags) {
        this.title = title;
        this.role = role;
        this.achievement = achievement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags = tags;
    }

    public void updateSummary(String summary) {
        this.summary = summary;
    }
}
