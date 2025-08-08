package com.example.Baesh.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usercareerhistory")
public class UserCareerHistoryE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String company_history;

    @Column
    private String tech_stack;

    @Column
    private String certificate;

    @Column
    private String award_record;

    @Column
    private int annual_income;

    @Column
    private String academic_ability;

    public UserCareerHistoryE(){}

    public UserCareerHistoryE(String company_history, String tech_stack ,String certificate ,String award_record ,int annual_income, String academic_ability){
        this.company_history = company_history;
        this.tech_stack = tech_stack;
        this.certificate = certificate;
        this.award_record = award_record;
        this.annual_income = annual_income;
        this.academic_ability = academic_ability;
    }

}
