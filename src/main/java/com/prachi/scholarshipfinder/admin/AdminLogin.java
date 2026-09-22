package com.prachi.scholarshipfinder.admin;

import com.prachi.scholarshipfinder.entity.Admin;
import com.prachi.scholarshipfinder.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AdminLogin {

    @Autowired
    private AdminService adminService;

    public boolean login() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n========== ADMIN LOGIN ==========");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Admin admin = adminService.login(username, password);

        if (admin != null) {

            System.out.println("\n=================================");
            System.out.println("Login Successful!");
            System.out.println("Welcome " + admin.getUsername());
            System.out.println("Role : " + admin.getRole());
            System.out.println("=================================");

            return true;

        } else {

            System.out.println("\nInvalid Username or Password.");
            return false;
        }
    }
}