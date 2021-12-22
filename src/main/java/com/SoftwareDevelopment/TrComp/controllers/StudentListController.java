package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.StudentList;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.StudentListService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/studentList")
public class StudentListController {

    private StudentListService service;

    public StudentListController(StudentListService service) {
        this.service = service;
    }

    @GetMapping("/")
    Iterable<StudentList> getAll() {
        return service.findAll();
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

                i.student == null ||
                i.status == null
        )
            return new StringResponse("Заполнены не все поля или поля заполнены некорректно");

        Optional<StudentList> o_idCheck = i.studentListId != null ? service.findById(i.studentListId) : Optional.empty();

        if (o_idCheck.isEmpty()) {
            service.save(i);
            return new StringResponse("Успешно создана запись с кодом = " + i.studentListId);
        }
        else {
            service.save(i);
            return new StringResponse("Успешно обновлена запись с кодом = " + i.studentListId);
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
