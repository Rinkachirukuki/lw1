package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Achievement;
import com.SoftwareDevelopment.TrComp.models.Customer;
import com.SoftwareDevelopment.TrComp.services.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    public CustomerController( PrivilegeService privilegeService, CustomerService customerService, GenderService genderService,AchievementService achievementService) {
        this.privilegeService = privilegeService;
        this.customerService = customerService;
        this.genderService = genderService;
        this.achievementService = achievementService;

    }

    private PrivilegeService privilegeService;
    private CustomerService customerService;
    private GenderService genderService;
    private AchievementService achievementService;

    @GetMapping("/list")
    public String showAllVehicles(Model model){

        model.addAttribute("customers", customerService.findAll());

        return "customer/show-all-customers.html";
    }

    @GetMapping("/delete")
    public String customerDelete(@RequestParam("id") int id) {
        customerService.deleteById(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/deleteAchievement")
    public String customerDeleteAchievement(@RequestParam int customerId, @RequestParam int achievementId, Model model) {

        Customer customer = customerService.findById(customerId);

        customer.removeAchievementById(achievementId);

        customerService.save(customer);

        return "redirect:/customer/update?id="+customerId;
    }

    @GetMapping("/addAchievement")
    public String customerAddAchievement(@RequestParam int customerId, @RequestParam int achievementId, Model model) {

        Customer customer = customerService.findById(customerId);

        customer.getAchievements().add(achievementService.findById(achievementId));

        customerService.save(customer);

        return "redirect:/achievement/list?customerId="+customerId;
    }



    @GetMapping("/customersByAchievement")
    public String customersByAchievement(@RequestParam int achievementId, Model model) {

        model.addAttribute("customer", achievementService.findById(achievementId));

        model.addAttribute("achievementList", achievementService.findAll());

        return "customer/show-all-achievements";
    }

    @GetMapping("/update")
    public String customerUpdate(@RequestParam("id") Integer id, Model model) {

        model.addAttribute("customer", customerService.findById(id));

        model.addAttribute("privilegeList", privilegeService.findAll());

        model.addAttribute("genderList", genderService.findAll());

        return "customer/update-form";
    }

    @GetMapping("/add")
    public String customerAdd(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        model.addAttribute("privilegeList", privilegeService.findAll());

        model.addAttribute("genderList",genderService.findAll());

        return "customer/update-form";
    }

    @PostMapping("/save")
    public String customerSave(@ModelAttribute("customer") Customer customer) {

        customerService.save(customer);
        return "redirect:/customer/list";
    }

}
