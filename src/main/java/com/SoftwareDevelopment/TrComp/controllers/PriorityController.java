package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Priority;
import com.SoftwareDevelopment.TrComp.services.PriorityService;
import com.SoftwareDevelopment.TrComp.services.ReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/priority")
public class PriorityController {

    public PriorityController(ReportService reportService, PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    private PriorityService priorityService;

    @GetMapping("/list")
    public String showAllPriorities(Pageable page, Model model){

        model.addAttribute("priorities", priorityService.findAll(page));

        return "priority/show-all.html";
    }

    @GetMapping("/delete")
    public String priorityDelete(@RequestParam("id") int id) {
        priorityService.deleteById(id);

        return "redirect:/priority/list";
    }

    @GetMapping("/update")
    public String priorityUpdate(@RequestParam("id") Integer id, Model model) {

        Priority priority = priorityService.findById(id);

        model.addAttribute("priority", priority);

        model.addAttribute("priorities", priorityService.findAll());

        return "priority/update-form";
    }

    @GetMapping("/add")
    public String priorityAdd(Model model) {

        Priority priority = new Priority();

        model.addAttribute("priority", priority);

        return "priority/update-form";
    }

    @PostMapping("/save")
    public String prioritySave(@ModelAttribute("report") Priority i) {
        priorityService.save(i);
        return "redirect:/priority/list";
    }




}
