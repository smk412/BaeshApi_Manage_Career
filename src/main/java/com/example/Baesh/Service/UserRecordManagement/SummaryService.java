package com.example.Baesh.Service.UserRecordManagement;

import com.example.Baesh.Repository.CareerSummaryRepository;
import com.example.Baesh.Service.LocalLMMService;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final LocalLMMService localLMMService;
    private final CareerSummaryRepository repository;

    public SummaryService(LocalLMMService localLMMService, CareerSummaryRepository repository) {
        this.localLMMService = localLMMService;
        this.repository = repository;
    }
    //오류 발생으로 잠시 비활성화
    /*
    public SummaryResponse summarizePortfolio(PortfolioRequest request) {
        // LLM 요약 수행
        CareerSummaryResponse llmResult = localLMMService.summarizeCareer(
                request.getCareer(),
                request.getSkills(),
                request.getInterests()
        );

        // DB 저장
        CareerSummary entity = new CareerSummary(
                request.getCareer(),
                request.getSkills(),
                request.getInterests(),
                llmResult.getJobSummary(),
                llmResult.getSkillSummary(),
                llmResult.getInterestSummary()
        );
        repository.save(entity);

        // 응답 반환
        return new SummaryResponse(
                llmResult.getJobSummary(),
                llmResult.getSkillSummary(),
                llmResult.getInterestSummary()
        );
    }
    */
}
