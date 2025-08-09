package com.example.Baesh.Service;

import com.example.Baesh.DTO.ExperienceCreateRequestDTO;
import com.example.Baesh.DTO.ExperienceResponseDTO;
import com.example.Baesh.DTO.ExperienceUpdateRequestDTO;
import com.example.Baesh.Entity.ExperienceE;
import com.example.Baesh.Entity.UserE;
import com.example.Baesh.Repository.ExperienceRepository;
import com.example.Baesh.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) // 클래스 전체에 읽기 전용 트랜잭션 적용 (성능 최적화)
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    /**
     * 새 이력 생성
     */
    @Transactional // 쓰기 작업이므로 readOnly = false 트랜잭션 적용
    public ExperienceResponseDTO createExperience(String userId, ExperienceCreateRequestDTO requestDto) {
        // 1. 사용자 조회
        UserE user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));

        // 2. AI 요약 API 호출 (현재는 임시값 사용)
        String summary = getSummaryFromAI(requestDto);

        // 3. DTO를 Entity로 변환
        ExperienceE newExperience = ExperienceE.builder()
                .user(user)
                .title(requestDto.getTitle())
                .role(requestDto.getRole())
                .achievement(requestDto.getAchievement())
                .summary(summary) // AI 요약 결과 저장
                .startDate(LocalDate.parse(requestDto.getStartDate()))
                .endDate(LocalDate.parse(requestDto.getEndDate()))
                .tags(String.join(",", requestDto.getTags())) // List<String> -> "tag1,tag2"
                .build();

        // 4. 데이터베이스에 저장
        ExperienceE savedExperience = experienceRepository.save(newExperience);

        // 5. Entity를 Response DTO로 변환하여 반환
        return new ExperienceResponseDTO(savedExperience);
    }

    /**
     * 특정 사용자의 모든 이력 조회
     */
    public List<ExperienceResponseDTO> getExperiencesByUserId(String userId) {
        // Repository를 사용해 특정 사용자의 모든 이력을 조회
        List<ExperienceE> experiences = experienceRepository.findAllByUser_UserId(userId);

        // 조회된 Entity 리스트를 Response DTO 리스트로 변환
        return experiences.stream()
                .map(ExperienceResponseDTO::new) // = .map(exp -> new ExperienceResponseDto(exp))
                .collect(Collectors.toList());
    }

    /**
     * 이력 수정
     */
    @Transactional
    public ExperienceResponseDTO updateExperience(Long experienceId, ExperienceUpdateRequestDTO requestDto) {
        // 1. 수정할 이력을 DB에서 조회
        ExperienceE experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이력을 찾을 수 없습니다: " + experienceId));

        // TODO: 수정 권한이 있는 사용자인지 확인하는 로직 추가 필요

        // 2. Entity 데이터 수정 (JPA의 '더티 체킹' 기능으로 트랜잭션 종료 시 자동 업데이트)
        experience.update(
                requestDto.getTitle(),
                requestDto.getRole(),
                requestDto.getAchievement(),
                LocalDate.parse(requestDto.getStartDate()),
                LocalDate.parse(requestDto.getEndDate()),
                String.join(",", requestDto.getTags())
        );

        // 3. 수정된 정보를 DTO로 변환하여 반환
        return new ExperienceResponseDTO(experience);
    }

    /**
     * 이력 삭제
     */
    @Transactional
    public void deleteExperience(Long experienceId) {
        // TODO: 삭제 권한이 있는 사용자인지 확인하는 로직 추가 필요
        experienceRepository.deleteById(experienceId);
    }

    /**
     * AI 요약 기능을 수행하는 가상 메서드
     */
    private String getSummaryFromAI(ExperienceCreateRequestDTO requestDto) {
        // 실제로는 여기서 외부 AI 서버 API를 호출하는 로직이 들어갑니다.
        System.out.println("AI 요약 API 호출: " + requestDto.getTitle());
        return "AI가 요약한 내용입니다: '" + requestDto.getTitle() + "' 경험은 사용자의 성장에 큰 도움이 되었습니다.";
    }
}
