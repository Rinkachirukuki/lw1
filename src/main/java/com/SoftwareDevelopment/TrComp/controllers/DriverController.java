package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
@Controller
@RequestMapping("/driver")
public class DriverController {

    public DriverController(VehicleService vehicleService, GenderService genderService, DriverService driverService) {
        this.vehicleService = vehicleService;
        this.genderService = genderService;
        this.driverService = driverService;
    }

    private VehicleService vehicleService;
    private GenderService genderService;
    private DriverService driverService;

    @GetMapping("/list")
    public String showAllDrivers(Model model){

        model.addAttribute("drivers", driverService.findAll());

        return "driver/show-all-drivers.html";
    }

    @GetMapping("/delete")
    public String driverDelete(@RequestParam("id") int id) {
        driverService.deleteById(id);

        return "redirect:/driver/list";
    }

    @GetMapping("/update")
    public String driverUpdate(@RequestParam("id") Integer id, Model model) {

        model.addAttribute("driver", driverService.findById(id));

        model.addAttribute("genderList", genderService.findAll());

        return "driver/update-form";
    }

    @GetMapping("/add")
    public String driverAdd(Model model) {

        model.addAttribute("driver", new Teacher());

        model.addAttribute("genderList", genderService.findAll());

        return "driver/update-form";
    }

    @PostMapping("/save")
    public String driverSave(@ModelAttribute("vehicle") Teacher teacher) {
        driverService.save(teacher);
        return "redirect:/driver/list";
    }




}
*/