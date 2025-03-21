package org.ericsunu;


import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PasswordValidator validator = new PasswordValidator();

        Scanner userInput = new Scanner(System.in);

        char userSelection;
        do {
            System.out.print("Enter Your Password: ");
            String password = userInput.nextLine();

                if (validator.isValid(password).getKey()) {
                    System.out.println("Password is valid. And your password is: " + validator.checkPasswordStrength(password));
                } else {
                    System.out.println("Password is invalid. Reason(s): " + validator.isValid(password).getValue());
                }

            System.out.print("Do you want to run the app again? [Y/N]: ");
            userSelection = userInput.nextLine().toUpperCase(Locale.getDefault()).charAt(0);

            System.out.println("\n\n\n\n\n");

            // Clear the console screen
            final String osName = System.getProperty("os.name");
            if (osName.contains("Windows")) {
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (IOException | InterruptedException e) {
                    System.out.println("Could not execute command. Error: " + e.getMessage());
                }
            } else {
                try {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                } catch (IOException | InterruptedException e) {
                    System.out.println("Could not execute command. Error: " + e.getMessage());
                }
            }

        } while (userSelection == 'Y');
        System.out.print("\nGoodbye...");

        userInput.close();
    }
}
