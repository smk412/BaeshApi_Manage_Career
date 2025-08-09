package com.example.Baesh.Controller;

import com.example.Baesh.DTO.ExperienceCreateRequestDTO;
import com.example.Baesh.DTO.ExperienceResponseDTO;
import com.example.Baesh.DTO.ExperienceUpdateRequestDTO;
import com.example.Baesh.Service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 이 클래스는 REST API를 처리하는 컨트롤러임을 명시
@RequestMapping("/api/experiences") // 이 컨트롤러의 모든 메서드는 /api/experiences 경로를 기본으로 가짐
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어 의존성 주입(DI)
public class ExperienceController {

    private final ExperienceService experienceService; // Service 계층의 의존성을 주입받음

    /**
     * 새 이력 생성 API
     * POST /api/experiences
     */
    @PostMapping
    public ResponseEntity<ExperienceResponseDTO> createExperience(
            @RequestBody ExperienceCreateRequestDTO requestDto) {

        // TODO: Spring Security 적용 후, 실제 인증된 사용자의 ID를 가져와야 함
        String tempUserId = "demo-user";

        ExperienceResponseDTO responseDto = experienceService.createExperience(tempUserId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * 특정 사용자의 모든 이력 조회 API
     * GET /api/experiences
     */
    @GetMapping
    public ResponseEntity<List<ExperienceResponseDTO>> getExperiences() {

        // TODO: Spring Security 적용 후, 실제 인증된 사용자의 ID를 가져와야 함
        String tempUserId = "demo-user";

        List<ExperienceResponseDTO> responseDtos = experienceService.getExperiencesByUserId(tempUserId);
        return ResponseEntity.ok(responseDtos);
    }

    /**
     * 이력 수정 API
     * PUT /api/experiences/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExperienceResponseDTO> updateExperience(
            @PathVariable Long id,
            @RequestBody ExperienceUpdateRequestDTO requestDto) {

        ExperienceResponseDTO responseDto = experienceService.updateExperience(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 이력 삭제 API
     * DELETE /api/experiences/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build(); // 내용 없이 204 No Content 응답
    }
}