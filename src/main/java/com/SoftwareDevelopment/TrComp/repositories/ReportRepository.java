package com.SoftwareDevelopment.TrComp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SoftwareDevelopment.TrComp.models.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
