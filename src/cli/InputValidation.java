package src.cli;

import java.util.Scanner;

public class InputValidation {
    public static int scannerValidation(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input format for option number. Please try again.");
            scanner.nextLine();
            System.out.println();
            System.out.print("Please enter the option number: ");
            continue;
        }
        int choice2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice2;
    }
}
