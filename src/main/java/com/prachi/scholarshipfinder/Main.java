package com.prachi.scholarshipfinder;

import com.prachi.scholarshipfinder.config.AppConfig;
import com.prachi.scholarshipfinder.entity.Scholarship;
import com.prachi.scholarshipfinder.service.ScholarshipService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ScholarshipService service =
                context.getBean(ScholarshipService.class);

        // ADD
        Scholarship scholarship = new Scholarship();
        scholarship.setScholarshipName("Post Matric Scholarship");
        scholarship.setCategory("SC");
        scholarship.setState("Gujarat");
        scholarship.setIncomeLimit(250000);
        scholarship.setEligibility("Minimum 60% Marks");
        scholarship.setAmount(50000);
        scholarship.setLastDate("31-12-2026");
        scholarship.setWebsite("https://scholarships.gov.in");

        service.addScholarship(scholarship);

        // VIEW ALL
        System.out.println("\n===== ALL SCHOLARSHIPS =====");

        service.getAllScholarships().forEach(System.out::println);

        // SEARCH BY ID
        System.out.println("\n===== SEARCH BY ID =====");

        Scholarship s = service.getScholarshipById(1);

        if (s != null)
            System.out.println(s);
        else
            System.out.println("Scholarship Not Found");

        // UPDATE
        System.out.println("\n===== UPDATE =====");

        s.setAmount(75000);

        service.updateScholarship(s);

        System.out.println(service.getScholarshipById(1));

        // DELETE
        // Uncomment this after testing Update
        // service.deleteScholarship(1);

        ((AnnotationConfigApplicationContext) context).close();
    }
}