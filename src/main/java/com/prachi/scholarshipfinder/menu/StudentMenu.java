package com.prachi.scholarshipfinder.menu;

import com.prachi.scholarshipfinder.entity.Scholarship;
import com.prachi.scholarshipfinder.service.ScholarshipService;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    private final ScholarshipService service;

    public StudentMenu(ScholarshipService service) {
        this.service = service;
    }

    public void showMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        STUDENT PORTAL");
            System.out.println("=================================");
            System.out.println("1. View All Scholarships");
            System.out.println("2. Search by State");
            System.out.println("3. Search by Category");
            System.out.println("4. Search by Income Limit");
            System.out.println("5. Search by Scholarship Name");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ==========================================
                // 1. VIEW ALL SCHOLARSHIPS
                // ==========================================

                case 1:

                    List<Scholarship> allScholarships =
                            service.getAllScholarships();

                    if (allScholarships.isEmpty()) {

                        System.out.println("\nNo scholarships available.");

                    } else {

                        System.out.println("\n=================================");
                        System.out.println("       ALL SCHOLARSHIPS");
                        System.out.println("=================================");

                        allScholarships.forEach(System.out::println);
                    }

                    break;


                // ==========================================
                // 2. SEARCH BY STATE
                // ==========================================

                case 2:

                    System.out.print("Enter State: ");
                    String state = sc.nextLine();

                    List<Scholarship> stateList =
                            service.findByState(state);

                    if (stateList.isEmpty()) {

                        System.out.println("\nNo scholarships found.");

                    } else {

                        System.out.println("\n=================================");
                        System.out.println("       SCHOLARSHIPS FOUND");
                        System.out.println("=================================");

                        stateList.forEach(System.out::println);
                    }

                    break;


                // ==========================================
                // 3. SEARCH BY CATEGORY
                // ==========================================

                case 3:

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    List<Scholarship> categoryList =
                            service.findByCategory(category);

                    if (categoryList.isEmpty()) {

                        System.out.println("\nNo scholarships found.");

                    } else {

                        System.out.println("\n=================================");
                        System.out.println("       SCHOLARSHIPS FOUND");
                        System.out.println("=================================");

                        categoryList.forEach(System.out::println);
                    }

                    break;


                // ==========================================
                // 4. SEARCH BY INCOME LIMIT
                // ==========================================

                case 4:

                    System.out.print("Enter Family Income: ");

                    double income = sc.nextDouble();
                    sc.nextLine();

                    List<Scholarship> incomeList =
                            service.findByIncomeLimit(income);

                    if (incomeList.isEmpty()) {

                        System.out.println("\nNo scholarships found.");

                    } else {

                        System.out.println("\n=================================");
                        System.out.println("       SCHOLARSHIPS FOUND");
                        System.out.println("=================================");

                        incomeList.forEach(System.out::println);
                    }

                    break;


                // ==========================================
                // 5. SEARCH BY SCHOLARSHIP NAME
                // ==========================================

                case 5:

                    System.out.print("Enter Scholarship Name: ");

                    String name = sc.nextLine();

                    List<Scholarship> nameList =
                            service.findByScholarshipName(name);

                    if (nameList.isEmpty()) {

                        System.out.println("\nNo scholarships found.");

                    } else {

                        System.out.println("\n=================================");
                        System.out.println("       SCHOLARSHIPS FOUND");
                        System.out.println("=================================");

                        nameList.forEach(System.out::println);
                    }

                    break;


                // ==========================================
                // 6. BACK
                // ==========================================

                case 6:

                    System.out.println("\nReturning to Main Menu...");

                    return;


                // ==========================================
                // INVALID CHOICE
                // ==========================================

                default:

                    System.out.println("\nInvalid Choice.");
            }
        }
    }
}