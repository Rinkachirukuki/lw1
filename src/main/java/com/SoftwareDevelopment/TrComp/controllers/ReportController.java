package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Report;
import com.SoftwareDevelopment.TrComp.services.PriorityService;
import com.SoftwareDevelopment.TrComp.services.ReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/report")
public class ReportController {

    public ReportController(ReportService reportService, PriorityService priorityService) {
        this.reportService = reportService;
        this.priorityService = priorityService;
    }

    private ReportService reportService;
    private PriorityService priorityService;

    @GetMapping("/list")
    public String showAllReports(Pageable page, Model model){

        model.addAttribute("reports", reportService.findAll(page));

        return "report/show-all.html";
    }

    @GetMapping("/delete")
    public String reportDelete(@RequestParam("id") int id) {
        reportService.deleteById(id);

        return "redirect:/report/list";
    }

    @GetMapping("/update")
    public String reportUpdate(@RequestParam("id") Integer id, Model model) {

        Report report = reportService.findById(id);

        model.addAttribute("report", report);

        model.addAttribute("priorities", priorityService.findAll());

        return "report/update-form";
    }

    @GetMapping("/add")
    public String reportAdd(Model model) {

        Report report = new Report();

        model.addAttribute("report", report);

        model.addAttribute("priorities", priorityService.findAll());

        return "report/update-form";
    }

    @PostMapping("/save")
    public String reportSave(@ModelAttribute("report") Report report) {
        reportService.save(report);
        return "redirect:/report/list";
    }




}
