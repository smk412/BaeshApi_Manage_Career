package com.example.Baesh.Service;

import com.example.Baesh.DTO.ExperienceSummaryResponse;
import com.example.Baesh.DTO.OllamaResponse;
import com.example.Baesh.DTO.UserRecordManagement.SelfIntroDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LocalLMMService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String OLLAMA_URL = "http://localhost:11434/api/generate";

    public String buildExperienceSummaryPrompt(String title, String role, String achievement, List<String> tags) {
        return """
                아래 활동 정보를 바탕으로 각 항목을 요약해줘.

                - 활동 제목: %s
                - 담당 역할: %s
                - 주요 성과: %s
                - 관련 태그: %s

                출력 형식(JSON):
                {
                  \"titleSummary\": \"...\",
                  \"roleSummary\": \"...\",
                  \"achievementSummary\": \"...\"
                }
                """.formatted(title, role, achievement != null ? achievement : "없음", String.join(", ", tags));
    }

    public String queryLocalLLM(String prompt) {
        Map<String,Object> request = new HashMap<>();
        request.put("model","gemma3n:e4b");
        request.put("prompt", prompt);
        request.put("stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<OllamaResponse> response = restTemplate.exchange(
                OLLAMA_URL,
                HttpMethod.POST,
                entity,
                OllamaResponse.class
        );

        return Objects.requireNonNull(response.getBody()).getResponse();
    }

    public String cleanLLMJsonResponse(String raw) {
        return raw
                .replaceAll("(?s)```json\\s*", "")
                .replaceAll("(?s)```", "")
                .trim();
    }

    public List<String> AiFeedBack(String subject, String content){
        String prompt = """
                다음 자기소개서를 질문에 맞게 피디백하고 내가 보낸 자기소개서 피드백에 맞게 변경도 해줘.
                만약 자기소개서 질문이 정해진 주제 없음 이라면 그냥 문장 구조하고 문맥 정도만 피드백해줘
                답변 형식은:["피드백 내용","피드백에 맞게 변경된 자기소개서"]
                List<String> 형태로 파싱 할수 있게 꼭 []배열 형식으로 반환해줘 배열 크기는 2야
                참고 같은 사족은 빼
                자기소개 질문:
                """+subject+
                """
                자기소개서:
                """ +content;

        String response = queryLocalLLM(prompt);
        return parseJsonArrayFrom(response);
    }

    public List<String> extractTag(String userIntro){
        String prompt = """
        다음 자기소개에서 직무, 기술, 분야, 경력을 태그로 뽑아줘.
        형식: ["Java", "Spring", "백엔드", "1년차"]
        자기소개:
        """ + userIntro;

        String response = queryLocalLLM(prompt);
        return parseJsonArrayFrom(response);
    }
    public String googleProgrammableSearch(String userIntro){
        String prompt = """
        다음 문장을 웹 검색 키워드로 바꿔줘.
        조건:
        - 구인/채용/구직/리쿠르팅 사이트는 제외하고,
        - 개인 블로그, GitHub, 기술 포트폴리오 중심의 키워드로 구성해줘.
        - 결과는 쌍따옴표 없이 공백 기준 단어 10개 이내의 구글 검색 쿼리로 만들어줘.
        문장:""" + userIntro;

        return queryLocalLLM(prompt);
    }
    private List<String> parseJsonArrayFrom(String text){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(text, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            throw new RuntimeException("LMM 응답 파싱 실패: " + text);
        }
    }
}
