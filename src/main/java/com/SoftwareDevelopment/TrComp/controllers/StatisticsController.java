package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Audience;
import com.SoftwareDevelopment.TrComp.models.Student;
import com.SoftwareDevelopment.TrComp.models.answers.AcademicGroupStatistics;
import com.SoftwareDevelopment.TrComp.models.answers.AllAcademicGroupStatistics;
import com.SoftwareDevelopment.TrComp.repositories.AcademicClassSqlRepository;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;


@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private int dateConst = 1000;

    @Autowired
    private AcademicClassSqlRepository repository;

    @GetMapping("/AllGroups")
    Iterable<AllAcademicGroupStatistics> getAll() {
        return repository.findAllAcademicGroupStatistics(new Date( Long.MIN_VALUE*dateConst ),new Date(Long.MAX_VALUE/dateConst));
    }
    @GetMapping("/{id}")
    Iterable<AcademicGroupStatistics> getOne(@PathVariable String id) {

        return repository.findAcademicGroupStatistics(id, new Date( Long.MIN_VALUE*dateConst ), new Date( Long.MAX_VALUE/dateConst ));
    }
}
