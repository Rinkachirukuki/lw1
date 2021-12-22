package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Teacher;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.TeacherService;
import com.SoftwareDevelopment.TrComp.validators.EmailValidator;
import com.SoftwareDevelopment.TrComp.validators.PhoneNumberValidator;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping("/")
    Iterable<Teacher> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Optional<Teacher> getOne(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/")
    StringResponse addOne(@RequestBody Teacher i) {

        if (
            i.teacherCode == null ||
            i.firstName == null ||
            i.lastName == null ||
            i.patronymic == null ||
            i.phoneNumber == null ||
            i.birthDate == null ||
            i.email == null ||

            i.teacherCode.isEmpty() ||
            i.firstName.isEmpty() ||
            i.lastName.isEmpty() ||
            i.patronymic.isEmpty() ||
            i.phoneNumber.isEmpty() ||
            i.email.isEmpty()
        )
            return new StringResponse("Заполнены не все поля");

        Optional<Teacher> o_idCheck     = service.findById(i.teacherCode);
        Optional<Teacher> o_emailCheck  = service.findByEmail(i.email);
        Optional<Teacher> o_phNumCheck  = service.findByPhoneNumber(i.phoneNumber);

        if (!o_idCheck.isPresent()) {
            if(o_emailCheck.isPresent())
                return new StringResponse("Запись преподавателя с таким email уже существует");
            if(o_phNumCheck.isPresent())
                return new StringResponse("Запись преподавателя с таким номером телефона уже существует");
        }
        else {
            Teacher item = o_idCheck.get();
            if (o_emailCheck.isPresent() && !i.email.equals(item.email))
                return new StringResponse("Запись преподавателя с таким email уже существует");
            if (o_phNumCheck.isPresent() && !i.phoneNumber.equals(item.phoneNumber))
                return new StringResponse("Запись преподавателя с таким номером телефона уже существует");
        }

        if (!EmailValidator.isValid(i.email))
            return new StringResponse("Некорректный email");

        if (!PhoneNumberValidator.isValid(i.phoneNumber))
            return  new StringResponse("Некорректный номер телефона");

        service.save(i);

        if (o_idCheck.isEmpty())
            return new StringResponse("Успешно создана запись преподавателя с кодом = " + i.teacherCode);
        else
            return new StringResponse("Успешно изменена запись преподавателя с кодом = " + i.teacherCode);
    }


    @DeleteMapping("/{id}")
    StringResponse deleteOne(@PathVariable String id) {
        if (service.findById(id).isPresent())
            service.deleteById(id);
        else
            return new StringResponse("Не найдена запись преподавателя с кодом = " + id);

        return new StringResponse("Удалена запись преподавателя с кодом = " + id);
    }
}
