package org.ericsunu;


public class Main {
    public static void main(String[] args) {
        PasswordValidator validator = new PasswordValidator();
        String password = "Test@123";  // Change this to test different passwords

        if (validator.isValid(password)) {
            System.out.println("Password is valid.");
        } else {
            System.out.println("Password is invalid.");
        }
    }
}
