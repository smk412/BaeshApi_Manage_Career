package com.example.Baesh.Repository;

import com.example.Baesh.Entity.ExperienceE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository<ExperienceE, Long>를 상속받는 것만으로 기본적인 CRUD(Create, Read, Update, Delete) 기능이 자동으로 구현

public interface ExperienceRepository extends JpaRepository<ExperienceE, Long> {

    /**
     * 특정 사용자의 모든 이력을 조회하는 메서드
     * Spring Data JPA가 메서드 이름을 분석해서 자동으로 쿼리를 생성합니다.
     * "user 필드의 userId 필드 값을 기준으로 ExperienceE를 모두 찾아줘"
     */
    List<ExperienceE> findAllByUser_UserId(String userId);
}
