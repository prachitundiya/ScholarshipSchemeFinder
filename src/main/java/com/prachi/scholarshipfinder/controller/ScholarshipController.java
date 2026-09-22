package com.prachi.scholarshipfinder.controller;

import com.prachi.scholarshipfinder.entity.Scholarship;
import com.prachi.scholarshipfinder.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
@CrossOrigin
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    @Autowired
    public ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }


    // ==============================
    // GET ALL SCHOLARSHIPS
    // ==============================

    @GetMapping
    public List<Scholarship> getAllScholarships() {

        return scholarshipService.getAllScholarships();
    }


    // ==============================
    // SEARCH BY SCHOLARSHIP NAME
    // ==============================

    @GetMapping("/name/{name}")
    public List<Scholarship> searchByName(
            @PathVariable("name") String name) {

        return scholarshipService.findByScholarshipName(name);
    }


    // ==============================
    // SEARCH BY STATE
    // ==============================

    @GetMapping("/state/{state}")
    public List<Scholarship> searchByState(
            @PathVariable("state") String state) {

        return scholarshipService.findByState(state);
    }


    // ==============================
    // SEARCH BY CATEGORY
    // ==============================

    @GetMapping("/category/{category}")
    public List<Scholarship> searchByCategory(
            @PathVariable("category") String category) {

        return scholarshipService.findByCategory(category);
    }


    // ==============================
    // SEARCH BY INCOME
    // ==============================

    @GetMapping("/income/{income}")
    public List<Scholarship> searchByIncome(
            @PathVariable("income") double income) {

        return scholarshipService.findByIncomeLimit(income);
    }
}