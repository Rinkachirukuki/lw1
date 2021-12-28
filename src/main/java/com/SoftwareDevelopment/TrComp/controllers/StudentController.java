package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Student;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.AcademicGroupService;
import com.SoftwareDevelopment.TrComp.services.StudentService;
import com.SoftwareDevelopment.TrComp.validators.EmailValidator;
import com.SoftwareDevelopment.TrComp.validators.PhoneNumberValidator;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService service;
    private AcademicGroupService academicGroupService;

    public StudentController(StudentService service, AcademicGroupService academicGroupService) {
        this.service = service;
        this.academicGroupService = academicGroupService;
    }

    @GetMapping("/")
    Iterable<Student> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Optional<Student> getOne(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/")
    StringResponse addOne(@RequestBody Student i) {

        if (
                i.studentCode == null ||
                i.firstName == null ||
                i.lastName == null ||
                i.patronymic == null ||
                i.phoneNumber == null ||
                i.birthDate == null ||
                i.email == null ||
                i.academicGroup == null ||

                i.studentCode.isEmpty() ||
                i.firstName.isEmpty() ||
                i.lastName.isEmpty() ||
                i.patronymic.isEmpty() ||
                i.phoneNumber.isEmpty() ||
                i.email.isEmpty() ||
                i.academicGroup.groupCode.isEmpty()
        )
            return new StringResponse("Заполнены не все поля");

        Optional<Student> o_idCheck     = service.findById(i.studentCode);
        Optional<Student> o_emailCheck  = service.findByEmail(i.email);
        Optional<Student> o_phNumCheck  = service.findByPhoneNumber(i.phoneNumber);

        if (!academicGroupService.existsById(i.academicGroup.groupCode))
            return new StringResponse("Неверно указана академическая группа");

        if (o_idCheck.isEmpty()) {
            if(o_emailCheck.isPresent())
                return new StringResponse("Запись студента с таким email уже существует");
            if(o_phNumCheck.isPresent())
                return new StringResponse("Запись студента с таким номером телефона уже существует");
        }
        else {
            Student item = o_idCheck.get();
            if (o_emailCheck.isPresent() && !i.email.equals(item.email))
                return new StringResponse("Запись студента с таким email уже существует");
            if (o_phNumCheck.isPresent() && !i.phoneNumber.equals(item.phoneNumber))
                return new StringResponse("Запись студента с таким номером телефона уже существует");
        }

        if (!EmailValidator.isValid(i.email))
            return new StringResponse("Некорректный email");

        if (!PhoneNumberValidator.isValid(i.phoneNumber))
            return  new StringResponse("Некорректный номер телефона");

        service.save(i);

        if (o_idCheck.isEmpty())
            return new StringResponse("Успешно создана запись студента с кодом = " + i.studentCode);
        else
            return new StringResponse("Успешно изменена запись студента с кодом = " + i.studentCode);
    }


    @DeleteMapping("/{id}")
    StringResponse deleteOne(@PathVariable String id) {
        if (service.findById(id).isPresent())
            service.deleteById(id);
        else
            return new StringResponse("Не найдена запись студента с кодом = " + id);

        return new StringResponse("Удалена запись студента с кодом = " + id);
    }
}
