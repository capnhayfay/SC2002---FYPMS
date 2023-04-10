package gui;

import account.*;
import java.util.Scanner;
import command.admin.*;

/**
 * GUI which is shown to the Cineplex Admin
 */
public class SupervisorAccountGUI implements Menu, Logout, GetCommand {
    private SupervisorAccount supervisor;

    /**
     * Creates a CineplexAdminGui with the given Cineplex Admin Account
     * 
     * @param cineplexAdmin which is the Cineplex Admin account
     */
    public SupervisorAccountGUI(SupervisorAccount supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * Prints list of possible actions that can be performed by Cineplex Admin
     */
    public void display() {
        System.out.println();
        // System.out.println("-----------------------------------------");
        System.out.println("=========================================");
        System.out.println("           Supervisor Menu           ");
        System.out.println("=========================================");
        // System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Logged in as Supervisor: " + supervisor.getLoginId());
        System.out.println();
        System.out.println("1. Create/update/view projects");
        if (true) {
            System.out.println("2. View student pending requests");
        } else {
            System.out.println("2. View student pending requests (NEW)");
        }
        System.out.println("3. Approve request");
        System.out.println("4. Reject Request");
        System.out.println("5. Request to transfer student");
        System.out.println("6. View Request History");
        System.out.println("7. Change Password");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.println("=========================================");
        System.out.println();

    }

    /**
     * Gets input from Cineplex Admin and executes the required instruction
     * 
     * @return 0 to exit the program entirely, 1 to continue program
     */
    public int execute() {
        System.out.print("Please enter the option number: ");
        Scanner scanner = new Scanner(System.in);
        // Error handling for invalid input
        while (true) {

            if (!scanner.hasNextInt()) {

                System.out.println("Invalid input format for option number. Please try again.");
                scanner.nextLine();
                System.out.println();
                System.out.print("Please enter the option number: ");
                continue;
            }
            int userCh = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            System.out.println("=========================================");

            if (userCh == 5) {
                return 0;
            }
            switch (userCh) {
                case 1:
                    new CreateShowCommand(cineplexAdmin.getCineplex()).execute();
                    break;
                case 2:
                    new UpdateShowCommand(cineplexAdmin.getCineplex()).execute();
                    break;
                case 3:
                    new DeleteShowCommand(cineplexAdmin.getCineplex()).execute();
                    break;

                case 4:
                    logout();
                    break;
                default:
                    System.out.println();
                    System.out.println("Option number out of range. Please try again.");
                    break;
            }
            return 1;
        }
    }

    /**
     * Logout from account by setting Cineplex Admin Account to null
     */
    public void logout() {
        cineplexAdmin = null;
    }

    /**
     * Returns Account in CineplexAdminGUI
     * 
     * @return Account
     */
    public Account getAccount() {
        return this.cineplexAdmin;
    }
}
