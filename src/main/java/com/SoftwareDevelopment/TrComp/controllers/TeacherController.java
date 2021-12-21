package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Teacher;
import com.SoftwareDevelopment.TrComp.services.TeacherService;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public @ResponseBody Iterable<Teacher> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public @ResponseBody Optional<Teacher> findOne(@RequestParam("code") String code) {
        return service.findById(code);
    }

    @RequestMapping(value="/delete", method=RequestMethod.DELETE)
    public HttpStatus delete(@RequestParam("code") String code) {
        service.deleteById(code);
        return HttpStatus.OK;
    }

    @GetMapping("/update")
    public String driverUpdate(@RequestParam("id") Integer id, Model model) {

        return "driver/update-form";
    }

    @GetMapping("/add")
    public String driverAdd(Model model) {

        model.addAttribute("driver", new Teacher());

        return "driver/update-form";
    }

    @PostMapping("/save")
    public String driverSave(@ModelAttribute("vehicle") Teacher teacher) {
        return "redirect:/driver/list";
    }




}
