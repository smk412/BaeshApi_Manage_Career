package com.example.Baesh.Controller;

import com.example.Baesh.DTO.Gpt.AiChatRequest;
import com.example.Baesh.DTO.Gpt.AiChatResponse;
import com.example.Baesh.DTO.Gpt.AiUpdate;
import com.example.Baesh.DTO.Gpt.FineTuneResponse;
import com.example.Baesh.Entity.UserE;
import com.example.Baesh.Repository.ChatHistoryRepository;
import com.example.Baesh.Repository.UserRepository;
import com.example.Baesh.Service.Gpt.OpenAiClientCloneService;
import com.example.Baesh.Service.Gpt.OpenAiClientService;
import lombok.RequiredArgsConstructor;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/gpt")
@RequiredArgsConstructor
public class GptController {
    private final ChatHistoryRepository chatHistoryRepository;
    private final OpenAiClientCloneService openAiClientCloneService;
    private final OpenAiClientService openAiClientService;
    private final UserRepository userRepository;

    @PostMapping("/generate")
    public AiChatResponse generate(@RequestBody AiChatRequest request) throws JSONException {
        String userMessage = request.getMessage();
        UserE userE = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        // JSON 배열로 대화 관리
        JSONArray messages = new JSONArray();

        // 사용자 입력을 AI 모델에 전달하여 봇의 응답 생성
        String botReply = openAiClientCloneService.callModel(userE.getAiModelName(), String.valueOf(messages));

        messages.put(new JSONObject(Map.of("role", "user", "content", botReply)));
        // AI 봇의 응답을 JSON 객체로 추가
        messages.put(new JSONObject(Map.of("role", "assistant", "content", userMessage)));


        openAiClientService.saveChatMessage(messages,userE.getId());
        // AI 응답 반환
        return new AiChatResponse(botReply);
    }

    @PostMapping("/update")
    public FineTuneResponse gptFineTun(@RequestBody AiUpdate aiUpdate) throws IOException {
        String fineTuneId = openAiClientService.gptFineTunUpdate(aiUpdate.getId());
        return openAiClientService.getFineTuneStatus(fineTuneId);
    }

    @GetMapping("/finetune/status/{fineTuneId}")
    public FineTuneResponse getFineTuneStatus(@PathVariable("fineTuneId") String fineTuneId) throws IOException {
        return openAiClientService.getFineTuneStatus(fineTuneId);
    }
}
