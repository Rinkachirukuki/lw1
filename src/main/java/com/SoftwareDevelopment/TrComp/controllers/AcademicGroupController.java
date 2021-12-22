package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.AcademicGroup;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.AcademicGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/academicGroup")
public class AcademicGroupController {

    private AcademicGroupService service;

    public AcademicGroupController(AcademicGroupService service) {
        this.service = service;
    }

    @GetMapping("/")
    Iterable<AcademicGroup> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Optional<AcademicGroup> getOne(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/")
    StringResponse addOne(@RequestBody AcademicGroup i) {

        if (
                i.groupCode == null ||
                i.groupName == null ||

                i.groupName.isEmpty() ||
                i.groupCode.isEmpty()
        )
            return new StringResponse("Заполнены не все поля");

        Optional<AcademicGroup> o_idCheck = service.findById(i.groupCode);

        if (o_idCheck.isEmpty()) {
            service.save(i);
            return new StringResponse("Успешно создана запись группы с кодом = " + i.groupCode);
        }
        else {
            service.save(i);
            return new StringResponse("Успешно обновлена запись группы с кодом = " + i.groupCode);
        }
    }


    @DeleteMapping("/{id}")
    StringResponse deleteOne(@PathVariable String id) {
        if (service.findById(id).isPresent())
            service.deleteById(id);
        else
            return new StringResponse("Не найдена запись группы с кодом = " + id);

        return new StringResponse("Удалена запись группы с кодом = " + id);
    }
}
