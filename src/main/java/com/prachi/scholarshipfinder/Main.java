package com.prachi.scholarshipfinder;

import com.prachi.scholarshipfinder.admin.AdminLogin;
import com.prachi.scholarshipfinder.config.AppConfig;
import com.prachi.scholarshipfinder.menu.AdminMenu;
import com.prachi.scholarshipfinder.menu.StudentMenu;
import com.prachi.scholarshipfinder.service.ScholarshipService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ScholarshipService scholarshipService =
                context.getBean(ScholarshipService.class);

        // Get beans from Spring
        AdminLogin adminLogin = context.getBean(AdminLogin.class);

        StudentMenu studentMenu = new StudentMenu(scholarshipService);
        AdminMenu adminMenu = context.getBean(AdminMenu.class);

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n======================================");
            System.out.println("     SCHOLARSHIP FINDER SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Student Portal");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    studentMenu.showMenu();
                    break;

                case 2:
                    if (adminLogin.login()) {
                        adminMenu.showMenu();
                    }
                    break;

                case 3:
                    System.out.println("\nThank you for using Scholarship Finder.");

                    sc.close();
                    context.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}