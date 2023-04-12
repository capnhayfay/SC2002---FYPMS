package gui;

import account.*;
import command.Supervisor.ModifySubmittedFYPTitleCommand;
import command.admin.*;
import moblima.SilverVillage;
import system.SystemSettings;

import java.util.Scanner;

/**
 * GUI which is shown to the Company Admin
 */
public class FYPCoordinatorGUI implements Menu, Logout, GetCommand {
    private FYPCoordinatorAccount FYPCoordinator;

    /**
     * Creates a CompanyAdminGui with the given Company Admin Account
     * 
     * @param curAcc which is the Company Admin Account
     */
    public FYPCoordinatorGUI(FYPCoordinatorAccount FYPCoordinator) {
        this.FYPCoordinator = FYPCoordinator;
    }

    /**
     * Prints list of possible actions that can be performed by Company Admin
     */
    public void display() {
        System.out.println();
        // System.out.println("-----------------------------------------");
        System.out.println("=========================================");
        System.out.println("           FYP Coordinator Menu            ");
        System.out.println("=========================================");
        // System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Logged in as FYP Coordinator: " + FYPCoordinator.getLoginId());
        System.out.println();
        System.out.println("1. Create, update or view projects");
        if (true) {
            System.out.println("2. View pending requests");
        } else {
            System.out.println("2. View student pending requests (NEW)");
        }
        System.out.println("3. Request to transfer student");
        System.out.println("4. View Request History");
        System.out.println("5. View Project by Filters");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        System.out.println("=========================================");
        System.out.println();
    }

    /**
     * Gets input from Company Admin and executes the required instruction
     * 
     * @return 0 to exit the program entirely, 1 to continue program
     */
    public int execute() {
        Scanner scanner = new Scanner(System.in);
        // Error handling for invalid input
        while (true) {
            System.out.print("Please enter the option number: ");
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

            if (userCh == 8) {
                return 0;
            }
            switch (userCh) {
                case 1:
                    System.out.println("Please input a choice from Create, Update, View");
                    System.out.println("1. Create Project");
                    System.out.println("2. Update Project Title");
                    System.out.println("3. View Projects");
                    if (!scanner.hasNextInt()) {

                        System.out.println("Invalid input format for option number. Please try again.");
                        scanner.nextLine();
                        System.out.println();
                        System.out.print("Please enter the option number: ");
                        continue;
                    }
                    int selectedChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (selectedChoice) {
                        case 1:
                            new CreateProjectCommand(FYPCoordinator.getName()).execute();
                            break;
                        case 2:
                            new ModifySubmittedFYPTitleCommand().execute();
                            break;
                        case 3:
                            new ViewSupervisorProjectStatusCommand().execute();
                            break;
                    }
                    break;
                case 2:
                    new ViewPendingStudentRequestsCommand().execute();
                    System.out.println("Please select your choice");
                    System.out.println("1. Approve Request");
                    System.out.println("2. Reject Request");
                    System.out.println("3. Return to Main Page");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input format for option number. Please try again.");
                        scanner.nextLine();
                        System.out.println();
                        System.out.print("Please enter the option number: ");
                        continue;
                    }
                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice2) {
                        case 1:
                            // new approveRequest();
                            break;
                        case 2:
                            // new rejectRequest();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    new RequestTransfertoCoordCommand().execute();
                    break;

                case 4:
                    // new viewRequestHistory()
                    break;

                case 5:
                    System.out.println("Please select your choice");
                    System.out.println("1. Filter by Supervisor");
                    System.out.println("2. Filter by Status");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input format for option number. Please try again.");
                        scanner.nextLine();
                        System.out.println();
                        System.out.print("Please enter the option number: ");
                        continue;
                    }
                    int FilterChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (FilterChoice) {
                        case 1:
                            GenerateFilteredProjectDetailsCommand(1, projects); // filter by status
                            break;
                        case 2:
                            GenerateFilteredProjectDetailsCommand(2, projects); // filter by supervisor
                            break;
                    }
                    break;
                default:
                    break;
            }
            return 1;
        }
    }

    /**
     * Logout from account by setting FYP Coordinator Account to null
     */
    public void logout() {
        FYPCoordinator = null;
    }

    /**
     * Returns Account in FYP Coordinator
     * 
     * @return Account
     */
    public Account getAccount() {
        return FYPCoordinator;
    }
}
