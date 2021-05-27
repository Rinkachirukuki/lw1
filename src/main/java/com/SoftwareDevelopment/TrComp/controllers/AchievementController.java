package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.models.Achievement;
import com.SoftwareDevelopment.TrComp.models.Customer;
import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.services.AchievementService;
import com.SoftwareDevelopment.TrComp.services.CustomerService;
import com.SoftwareDevelopment.TrComp.services.GenderService;
import com.SoftwareDevelopment.TrComp.services.PrivilegeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/achievement")
public class AchievementController {

    public AchievementController(PrivilegeService privilegeService, CustomerService customerService, GenderService genderService, AchievementService achievementService) {
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
    public String customerAchievementList(Pageable page, @RequestParam(value= "customerId",defaultValue = "0", required=false) int customerId, Model model) {

        model.addAttribute("achievementList", achievementService.findAll());

        if (customerId != 0) {
            model.addAttribute("customer", customerService.findById(customerId));
            return "achievement/show-all-achievements-by-customer";
        }
        else{
            return "achievement/show-all-achievements";
        }
    }

    @GetMapping("/customerList")
    public String customersByAchievement(@RequestParam int achievementId, Model model) {

        model.addAttribute("achievement", achievementService.findById(achievementId));

        return "achievement/show-all-customers-with-achievement";
    }

    @GetMapping("/add")
    public String addAchievement(Model model) {

        model.addAttribute("achievement", new Achievement());

        return "achievement/update-form";
    }

    @PostMapping("/save")
    public String achievementSave(@ModelAttribute("achievement") Achievement achievement) {
        achievementService.save(achievement);
        return "redirect:/achievement/list";
    }

    @GetMapping("/delete")
    public String achievementDelete(@RequestParam("id") int id) {

        achievementService.deleteById(id);

        return "redirect:/achievement/list";
    }

}
