package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.StudentList;
import com.SoftwareDevelopment.TrComp.repositories.StudentListSqlRepository;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.AcademicClassService;
import com.SoftwareDevelopment.TrComp.services.StudentListService;
import com.SoftwareDevelopment.TrComp.services.StudentService;
import com.SoftwareDevelopment.TrComp.validators.StudentStatusValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/studentList")
public class StudentListController {

    @Autowired
    StudentListSqlRepository studentListSqlRepository;

    private StudentListService service;
    private AcademicClassService academicClassService;
    private StudentService studentService;

    public StudentListController(StudentListService service, AcademicClassService academicClassService, StudentService studentService) {
        this.service = service;
        this.academicClassService = academicClassService;
        this.studentService = studentService;
    }


    @GetMapping("/")
    Iterable<StudentList> getAll() {
        return studentListSqlRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<StudentList> getOne(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("/")
    StringResponse addOne(@RequestBody StudentList i) {

        if (
                i.academicClass == null ||
                i.student == null ||
                i.status == null ||

                i.student.studentCode.isEmpty() ||
                i.status.isEmpty()
        )
            return new StringResponse("Заполнены не все поля");

        if (!StudentStatusValidator.isValid(i.status))
            return new StringResponse("Некорректный статус ученика");

        if (!studentService.existsById(i.student.studentCode))
            return new StringResponse("Неверно указан студент");

        if (!academicClassService.existsById(i.academicClass.classId))
            return new StringResponse("Неверно указано учебное занятие");

        Optional<StudentList> o_idCheck = i.studentListId != null ? service.findById(i.studentListId) : Optional.empty();
        Optional<StudentList> o_studentClassCheck = service.findByAcademicClassAndStudent(i.academicClass,i.student);

        if (o_idCheck.isEmpty()) {
            if (o_studentClassCheck.isPresent())
                return new StringResponse("Данный ученик уже отмечен на этой паре. Запись = " + o_studentClassCheck.get().studentListId);

            service.save(i);
            return new StringResponse("Успешно создана запись посещения учеником занятия с кодом = " + i.studentListId);
        }
        else {
            if (o_studentClassCheck.isPresent() && !o_studentClassCheck.get().studentListId.equals(i.studentListId))
                return new StringResponse("Данный ученик уже отмечен на этой паре. Запись = " + o_studentClassCheck.get().studentListId);

            service.save(i);
            return new StringResponse("Успешно обновлена запись посещения учеником занятия с кодом = " + i.studentListId);
        }
    }

    @DeleteMapping("/{id}")
    StringResponse deleteOne(@PathVariable Integer id) {
        if (service.findById(id).isPresent())
            service.deleteById(id);
        else
            return new StringResponse("Не найдена запись с кодом = " + id);

        return new StringResponse("Удалена запись с кодом = " + id);
    }
}
