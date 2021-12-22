package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.AcademicDiscipline;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.AcademicDisciplineService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/academicDiscipline")
public class AcademicDisciplineController {

    private AcademicDisciplineService service;

    public AcademicDisciplineController(AcademicDisciplineService service) {
        this.service = service;
    }

    @GetMapping("/")
    Iterable<AcademicDiscipline> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Optional<AcademicDiscipline> getOne(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/")
    StringResponse addOne(@RequestBody AcademicDiscipline i) {

        if (
                i.disciplineCode == null ||
                i.disciplineName == null ||

                i.disciplineName.isEmpty() ||
                i.disciplineCode.isEmpty()
        )
            return new StringResponse("Заполнены не все поля");

        Optional<AcademicDiscipline> o_idCheck = service.findById(i.disciplineCode);

        if (o_idCheck.isEmpty()) {
            service.save(i);
            return new StringResponse("Успешно создана запись дисциплины с кодом = " + i.disciplineCode);
        }
        else {
            service.save(i);
            return new StringResponse("Успешно обновлена запись дисциплины с кодом = " + i.disciplineCode);
        }
    }

    @DeleteMapping("/{id}")
    StringResponse deleteOne(@PathVariable String id) {
        if (service.findById(id).isPresent())
            service.deleteById(id);
        else
            return new StringResponse("Не найдена запись дисциплины с кодом = " + id);

        return new StringResponse("Удалена запись дисциплины с кодом = " + id);
    }
}
