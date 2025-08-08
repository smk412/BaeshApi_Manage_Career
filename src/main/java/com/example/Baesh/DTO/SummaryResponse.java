package com.example.Baesh.DTO;

public class SummaryResponse {
    private String jobSummary;
    private String skillSummary;
    private String interestSummary;

    // 생성자 추가
    public SummaryResponse(String jobSummary, String skillSummary, String interestSummary) {
        this.jobSummary = jobSummary;
        this.skillSummary = skillSummary;
        this.interestSummary = interestSummary;
    }

    // Getter/Setter
    public String getJobSummary() { return jobSummary; }
    public void setJobSummary(String jobSummary) { this.jobSummary = jobSummary; }

    public String getSkillSummary() { return skillSummary; }
    public void setSkillSummary(String skillSummary) { this.skillSummary = skillSummary; }

    public String getInterestSummary() { return interestSummary; }
    public void setInterestSummary(String interestSummary) { this.interestSummary = interestSummary; }
}

