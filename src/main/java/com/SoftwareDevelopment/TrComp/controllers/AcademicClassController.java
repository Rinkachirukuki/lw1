package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.AcademicClass;
import com.SoftwareDevelopment.TrComp.repositories.AcademicClassSqlRepository;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/academicClass")
public class AcademicClassController {

    @Autowired
    private AcademicClassSqlRepository academicClassSqlRepository;

    private AcademicClassService service;
    private AcademicDisciplineService academicDisciplineService;
    private AcademicGroupService academicGroupService;
    private AudienceService audienceService;
    private TeacherService teacherService;

    public AcademicClassController(AcademicClassService service, AcademicDisciplineService academicDisciplineService, AcademicGroupService academicGroupService, AudienceService audienceService, TeacherService teacherService) {
        this.service = service;
        this.academicDisciplineService = academicDisciplineService;
        this.academicGroupService = academicGroupService;
        this.audienceService = audienceService;
        this.teacherService = teacherService;
    }


    @GetMapping("/")
    Iterable<AcademicClass> getAll() {
        return academicClassSqlRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<AcademicClass> getOne(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("/")
    StringResponse addOne(@RequestBody AcademicClass i) {

        if (
                i.classDate == null ||
                i.academicDiscipline == null ||
                i.academicGroup == null ||
                i.audience == null ||
                i.teacher == null ||

                i.academicDiscipline.disciplineCode.isEmpty() ||
                i.academicGroup.groupCode.isEmpty() ||
                i.audience.audienceCode.isEmpty() ||
                i.teacher.teacherCode.isEmpty()
        )
            return new StringResponse("Заполнены не все поля");

        if (!academicDisciplineService.existsById(i.academicDiscipline.disciplineCode))
            return new StringResponse("Неверно указана академическая дисциплина");

        if (!academicGroupService.existsById(i.academicGroup.groupCode))
            return new StringResponse("Неверно указана академическая группа");

        if (!audienceService.existsById(i.audience.audienceCode))
            return new StringResponse("Неверно указана аудитория");

        if (!teacherService.existsById(i.teacher.teacherCode))
            return new StringResponse("Неверно указан преподаватель");

        Optional<AcademicClass> o_idCheck = i.classId == null ? Optional.empty() : service.findById(i.classId);
        Optional<AcademicClass> o_groupTimeCheck  = service.findByAcademicGroupAndClassDate(i.academicGroup, i.classDate);


        if (o_idCheck.isEmpty()) {
            if (o_groupTimeCheck.isPresent())
                return new StringResponse("У группы " + i.academicGroup.groupCode + " уже стоит занятие в это время");

            service.save(i);
            return new StringResponse("Успешно создана запись занятия с кодом = " + i.classId);
        }
        else {
            if (o_groupTimeCheck.isPresent() && !i.classId.equals(o_groupTimeCheck.get().classId))
                return new StringResponse("У группы " + i.academicGroup.groupCode + " уже стоит занятие в это время");

            service.save(i);
            return new StringResponse("Успешно обновлена запись занятия с кодом = " + i.classId);
        }
    }


    @DeleteMapping("/{id}")
    StringResponse deleteOne(@PathVariable Integer id) {
        if (service.findById(id).isPresent())
            service.deleteById(id);
        else
            return new StringResponse("Не найдена запись занятия с кодом = " + id);

        return new StringResponse("Удалена запись занятия с кодом = " + id);
    }
}
