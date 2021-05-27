package com.SoftwareDevelopment.TrComp.controllers;

import com.SoftwareDevelopment.TrComp.exporters.VehicleExcelExporter;
import com.SoftwareDevelopment.TrComp.exporters.VehiclePdfExporter;
import com.SoftwareDevelopment.TrComp.models.Vehicle;
import com.SoftwareDevelopment.TrComp.services.DriverService;
import com.SoftwareDevelopment.TrComp.services.MarkService;
import com.SoftwareDevelopment.TrComp.services.VehicleService;
import com.lowagie.text.DocumentException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.PageFormat;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    public VehicleController(VehicleService vehicleService, MarkService markService,DriverService driverService) {
        this.vehicleService = vehicleService;
        this.markService = markService;
        this.driverService = driverService;
    }

    private VehicleService vehicleService;
    private MarkService markService;
    private DriverService driverService;

    @GetMapping("/list")
    public String showAllVehicles(Model model){

        model.addAttribute("vehicles",vehicleService.findAll());

        return "vehicle/show-all-vehicles.html";
    }

    @GetMapping("/delete")
    public String vehicleDelete(@RequestParam("id") int id) {
        vehicleService.deleteById(id);

        return "redirect:/vehicle/list";
    }

    @GetMapping("/update")
    public String vehicleUpdate(@RequestParam("id") Integer id, Model model) {

        Vehicle vehicle = vehicleService.findById(id);

        model.addAttribute("vehicle", vehicle);

        model.addAttribute("markList", markService.findAll());

        model.addAttribute("driverList",driverService.findAll());

        return "vehicle/update-form";
    }

    @GetMapping("/add")
    public String vehicleAdd(Model model) {

        Vehicle vehicle = new Vehicle();

        model.addAttribute("vehicle", vehicle);

        model.addAttribute("markList", markService.findAll());

        model.addAttribute("driverList",driverService.findAll());

        return "vehicle/update-form";
    }

    @PostMapping("/save")
    public String vehicleSave(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.save(vehicle);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/exportToXLSX")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=vehicles.xlsx";

        response.setHeader(headerKey, headerValue);

        List<Vehicle> vehicleList = (List<Vehicle>)vehicleService.findAll();

        VehicleExcelExporter exporter = new VehicleExcelExporter(vehicleList);

        exporter.export(response);
    }

    @GetMapping("/exportToPDF")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=vehicles.pdf";

        response.setHeader(headerKey, headerValue);

        List<Vehicle> vehicleList = (List<Vehicle>)vehicleService.findAll();

        VehiclePdfExporter exporter = new VehiclePdfExporter(vehicleList);

        exporter.export(response);

    }

}
