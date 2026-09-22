package com.prachi.scholarshipfinder.menu;

import com.prachi.scholarshipfinder.entity.Scholarship;
import com.prachi.scholarshipfinder.scraper.ScholarshipScraper;
import com.prachi.scholarshipfinder.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AdminMenu {

    private final ScholarshipService service;
    private final ScholarshipScraper scraper;

    @Autowired
    public AdminMenu(ScholarshipService service, ScholarshipScraper scraper) {
        this.service = service;
        this.scraper = scraper;
    }

    public void showMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=================================");
            System.out.println("         ADMIN PANEL");
            System.out.println("=================================");
            System.out.println("1. Add Scholarship");
            System.out.println("2. Update Scholarship");
            System.out.println("3. Delete Scholarship");
            System.out.println("4. View All Scholarships");
            System.out.println("5. Update Scholarships From Website");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    Scholarship scholarship = new Scholarship();

                    System.out.print("Scholarship Name: ");
                    scholarship.setScholarshipName(sc.nextLine());

                    System.out.print("Category: ");
                    scholarship.setCategory(sc.nextLine());

                    System.out.print("State: ");
                    scholarship.setState(sc.nextLine());

                    System.out.print("Income Limit: ");
                    scholarship.setIncomeLimit(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Eligibility: ");
                    scholarship.setEligibility(sc.nextLine());

                    System.out.print("Amount: ");
                    scholarship.setAmount(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Last Date: ");
                    scholarship.setLastDate(sc.nextLine());

                    System.out.print("Website: ");
                    scholarship.setWebsite(sc.nextLine());

                    service.addScholarship(scholarship);
                    break;

                case 2:

                    System.out.print("Enter Scholarship ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Scholarship updateScholarship = service.getScholarshipById(updateId);

                    if (updateScholarship == null) {
                        System.out.println("Scholarship not found.");
                        break;
                    }

                    System.out.print("New Scholarship Name: ");
                    updateScholarship.setScholarshipName(sc.nextLine());

                    System.out.print("New Category: ");
                    updateScholarship.setCategory(sc.nextLine());

                    System.out.print("New State: ");
                    updateScholarship.setState(sc.nextLine());

                    System.out.print("New Income Limit: ");
                    updateScholarship.setIncomeLimit(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("New Eligibility: ");
                    updateScholarship.setEligibility(sc.nextLine());

                    System.out.print("New Amount: ");
                    updateScholarship.setAmount(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("New Last Date: ");
                    updateScholarship.setLastDate(sc.nextLine());

                    System.out.print("New Website: ");
                    updateScholarship.setWebsite(sc.nextLine());

                    service.updateScholarship(updateScholarship);
                    break;

                case 3:

                    System.out.print("Enter Scholarship ID to Delete: ");
                    int deleteId = sc.nextInt();

                    service.deleteScholarship(deleteId);
                    break;

                case 4:

                    service.getAllScholarships().forEach(System.out::println);
                    break;

                case 5:

                    try {
                        scraper.scrapeScholarships();
                    } catch (Exception e) {
                        System.out.println("Error while updating scholarships.");
                        e.printStackTrace();
                    }

                    break;

                case 6:

                    System.out.println("Logging out...");
                    return;

                default:

                    System.out.println("Invalid Choice.");
            }
        }
    }
}