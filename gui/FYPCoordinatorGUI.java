package gui;

import account.*;
import command.FYPCoord.GenerateFilteredProjectDetailsCommand;
import command.FYPCoord.ViewAllRequestHistoryCommand;
import command.Supervisor.CreateProjectCommand;
import command.Supervisor.ModifySubmittedFYPTitleCommand;
import command.Supervisor.RequestTransfertoCoordCommand;
import command.Supervisor.ViewPendingStudentRequestsCommand;
import command.Supervisor.ViewSubmittedFYPCommand;
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
        if (ViewPendingStudentRequestsCommand.checkUpdates() == 1) {
            System.out.println("2. View student pending requests (NEW)");
        } else {
            System.out.println("2. View student pending requests");
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
                    System.out.println("Please input a choice from Create, Update, and View Projects");
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
                            new ViewSubmittedFYPCommand(FYPCoordinator.getName()).execute();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Please select your choice");
                    System.out.println("1. View all pending requests");
                    System.out.println("2. View own pending requests");
                    System.out.println("3. Return to Main Page");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input format for option number. Please try again.");
                        scanner.nextLine();
                        System.out.println();
                        System.out.print("Please enter the option number: ");
                        continue;
                    }
                    int RequestChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (RequestChoice) {
                        case 1:
                            new ViewAllRequestHistoryCommand().execute();
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
                        case 2:
                            new ViewPendingStudentRequestsCommand(FYPCoordinator.getName()).execute();
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
                            int choice3 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice3) {
                                case 1:
                                    // new approveRequest();
                                    break;
                                case 2:
                                    // new rejectRequest();
                                    break;
                                case 3:
                                    break;
                            }
                    }
                    break;
                case 3:
                    new RequestTransfertoCoordCommand(FYPCoordinator.getName()).execute();
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
                            new GenerateFilteredProjectDetailsCommand(1).execute(); // filter by status
                            break;
                        case 2:
                            new GenerateFilteredProjectDetailsCommand(2).execute(); // filter by supervisor
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
