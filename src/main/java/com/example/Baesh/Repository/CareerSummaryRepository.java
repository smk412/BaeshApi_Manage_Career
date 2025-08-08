package com.example.Baesh.Repository;

import com.example.Baesh.Entity.CareerSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerSummaryRepository extends JpaRepository<CareerSummary, Long> {
}