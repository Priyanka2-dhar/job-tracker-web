package com.jobtracker;

import java.util.Scanner;

public class CreateUser {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Password (at least 8 characters): ");
        String password = sc.nextLine();

        if (username.isEmpty() || password.length() < 8) {
            System.out.println("Username cannot be empty and password needs 8+ characters.");
            return;
        }
        new UserDAO().create(username, PasswordUtil.hash(password));
        System.out.println("User created.");
    }
}