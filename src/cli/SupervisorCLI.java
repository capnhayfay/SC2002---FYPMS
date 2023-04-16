package src.cli;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.request.RequestChangeTitle;
import src.FYPMS.request.RequestHistory;
import src.account.Account;
import src.account.UserType;
import src.account.supervisor.SupervisorAccount;
import src.command.ChangePassword;
import src.command.Supervisor.*;
import src.command.ViewAllRequestRecordsCommand;
import src.command.ViewIncomingRequestRecordsCommand;
import src.command.ViewOutcomingRequestRecordsCommand;
import src.exceptions.fypmsExceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

import static src.cli.InputValidation.scannerValidation;

/**
 * GUI which is shown to the Supervisor Account
 */
public class SupervisorCLI implements Menu, Logout, GetCommand {
    private SupervisorAccount supervisorAccount;
    private UserType userType;

    /**
     * Creates a SupervisorGui with the given Supervisor Account
     *
     * @param supervisorAccount which is the Supervisor account
     * @param userType          which is the account user type
     */
    public SupervisorCLI(SupervisorAccount supervisorAccount, UserType userType) {
        this.supervisorAccount = supervisorAccount;
        this.userType = userType;
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
        System.out.println("Logged in as Supervisor: " + supervisorAccount.getName());
        System.out.println();
        System.out.println("1. Create, update or view projects");
        if (RequestHistory.CheckPendingRequests(supervisorAccount.getLoginId()) == 1) {
            System.out.println("2. View pending requests (NEW)");
        } else {
            System.out.println("2. View pending requests");
        }
        System.out.println("3. Request to transfer student");
        System.out.println("4. View Request History");
        System.out.println("5. Change Password");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.println("=========================================");
        System.out.println();

    }

    /**
     * Gets input from Supervisor and executes the required instruction
     *
     * @return 0 to exit the program entirely, 1 to continue program
     */
    public int execute() {
        System.out.print("Please enter the option number: ");
        Scanner scanner = new Scanner(System.in);
        // Error handling for invalid input
        while (true) {
            int userCh = scannerValidation(scanner);
            scanner.nextLine();
            System.out.println();
            System.out.println("=========================================");

            if (userCh == 7) {
                return 0;
            }
            switch (userCh) {
                case 1 -> {
                    System.out.println("Please input a choice from Create, Update, and View Projects");
                    System.out.println("1. Create Project");
                    System.out.println("2. Update Project Title");
                    System.out.println("3. View Projects");
                    int selectedChoice = scannerValidation(scanner);

                    scanner.nextLine();
                    switch (selectedChoice) {
                        case 1 -> new CreateProjectCommand(supervisorAccount).execute();
                        case 2 -> new ModifyFYPTitle(supervisorAccount).execute();
                        case 3 -> new ViewSubmittedFYPCommand(supervisorAccount.getName()).execute();
                    }
                }
                case 2 -> {
                    ViewPendingStudentRequestsCommand ViewRequests = new ViewPendingStudentRequestsCommand(
                            supervisorAccount.getLoginId());
                    ViewRequests.execute();
                    if (ViewRequests.getRequestNumber() != 0) {
                        System.out.println("Please select your choice");
                        System.out.println("1. Approve or Reject Request");
                        System.out.println("2. Return to Main Page");
                        int choice2 = scannerValidation(scanner);
                        int reqID;
                        int projID;
                        FYP fyp;
                        switch (choice2) {
                            case 1 -> {
                                System.out.println("Input request ID: ");
                                reqID = scannerValidation(scanner);
                                if (reqID / 1000 == 0) {
                                    // Request Title Change
                                    RequestChangeTitle titleRequest = RequestHistory.getRequestChangeTitle(reqID);
                                    assert titleRequest != null;
                                    if (!titleRequest.getRequesteeID().equals(supervisorAccount.getLoginId())) {
                                        System.out.println("Invalid Input. Returning to Main Page...");
                                        break;
                                    }
                                    projID = titleRequest.getFypID();
                                    fyp = FYPList.getFYPById(projID);
                                    new ModifySubmittedFYPTitleCommand(fyp, titleRequest).execute();
                                } else if (reqID / 1000 != 0) {
                                    System.out.println("Invalid Input. Returning to Main Page...");
                                }
                            }
                            case 2 -> System.out.println("Returning to Main Page...");
                            default -> System.out.println("Invalid Input. Returning to Main Page...");
                        }
                    }
                }
                case 3 -> new RequestTransfertoCoordCommand(supervisorAccount).execute();
                case 4 -> {
                    System.out.println("Please select your choice");
                    System.out.println("1. View all incoming requests");
                    System.out.println("2. View all outgoing requests");
                    System.out.println("3. View all requests");
                    System.out.println("4. Return to main page");
                    int requestChoice = scannerValidation(scanner);
                    switch (requestChoice) {
                        case 1 -> new ViewIncomingRequestRecordsCommand(supervisorAccount).execute();
                        case 2 -> new ViewOutcomingRequestRecordsCommand(supervisorAccount).execute();
                        case 3 -> new ViewAllRequestRecordsCommand(supervisorAccount).execute();
                        case 4 -> System.out.println("Returning to Main Page...");
                        default -> System.out.println("Invalid Input. Returning to Main Page...");
                    }
                }
                case 5 -> {
                    new ChangePassword(supervisorAccount).execute();
                    logout();
                }
                case 6 -> {
                    logout();
                    System.out.println();
                    System.out.println("Logged out successfully.");
                    System.out.println();
                    return 1;
                }
                default -> {
                    System.out.println();
                    System.out.println("Option number out of range. Please try again.");
                }
            }
            return 1;
        }
    }

    /**
     * Logout from account by setting Supervisor Account to null
     */
    public void logout() {
        this.supervisorAccount = null;
        this.userType = null;
    }

    /**
     * Returns Account in SupervisorAccountGUI
     *
     * @return Account
     */
    public Account getAccount() {
        return this.supervisorAccount;
    }

    /**
     * Returns String in SupervisorAccountGUI
     *
     * @return String
     */
    public UserType getUserType() {
        return userType;
    }
}
