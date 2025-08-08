package com.example.Baesh.Controller;

import com.example.Baesh.Service.UserRecordManagement.SummaryService;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SummaryController {

    private final SummaryService summaryService;
    //오류 발생으로 잠시 비활성화
    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }
    /*
    @PostMapping("/summarize")
    public SummaryResponse summarize(@RequestBody PortfolioRequest request) {
        return summaryService.summarizePortfolio(request);
    }
     */
}
