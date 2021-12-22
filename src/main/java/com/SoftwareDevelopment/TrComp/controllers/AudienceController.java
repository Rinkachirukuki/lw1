package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Audience;
import com.SoftwareDevelopment.TrComp.response.StringResponse;
import com.SoftwareDevelopment.TrComp.services.AudienceService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/audience")
public class AudienceController {

    private AudienceService service;

    public AudienceController(AudienceService service) {
        this.service = service;
    }

    @GetMapping("/")
    Iterable<Audience> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Optional<Audience> getOne(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/")
    StringResponse addOne(@RequestBody Audience i) {

        if (i.audienceCode == null || i.audienceCode.isEmpty())
            return new StringResponse("Заполнены не все поля");
        else if (i.seatsCount < 0)
            return new StringResponse("Кол-во сидячих мест в аудитории не может быть меньше 0");

        Optional<Audience> o_idCheck = service.findById(i.audienceCode);

        if (o_idCheck.isEmpty()) {
            service.save(i);
            return new StringResponse("Успешно создана запись аудитории с кодом = " + i.audienceCode);
        }
        else {
            service.save(i);
            return new StringResponse("Успешно обновлена запись аудитории с кодом = " + i.audienceCode);
        }
    }

    @DeleteMapping("/{id}")
    StringResponse deleteOne(@PathVariable String id) {
        if (service.findById(id).isPresent())
            service.deleteById(id);
        else
            return new StringResponse("Не найдена запись аудитории с кодом = " + id);

        return new StringResponse("Удалена запись аудитории с кодом = " + id);
    }
}
