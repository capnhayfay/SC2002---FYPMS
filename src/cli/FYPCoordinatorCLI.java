package src.cli;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.request.*;
import src.account.Account;
import src.account.UserType;
import src.account.supervisor.FYPCoordinatorAccount;
import src.command.ChangePassword;
import src.command.FYPCoord.*;
import src.command.Supervisor.*;
import src.command.ViewAllRequestRecordsCommand;
import src.command.ViewIncomingRequestRecordsCommand;
import src.command.ViewOutcomingRequestRecordsCommand;

import java.util.Scanner;

/**
 * GUI which is shown to FYP Coordinator
 */
public class FYPCoordinatorCLI implements Menu, Logout, GetCommand {
    /**
     * The FYPCoordinator src.account of the user
     */
    private FYPCoordinatorAccount fypCoordinatorAccount;
    /**
     * The user type of the user
     */
    private UserType userType;

    /**
     * Constructs a FYPCoordinatorCLI with the given FYPCoordinator src.account and user
     * type
     *
     * @param fypCoordinatorAccount the FYPCoordinator src.account of the user
     * @param userType              the user type of the user
     */
    public FYPCoordinatorCLI(FYPCoordinatorAccount fypCoordinatorAccount, UserType userType) {
        this.fypCoordinatorAccount = fypCoordinatorAccount;
        this.userType = userType;
    }

    /**
     * Displays the options available for FYPCoordinators
     */
    @Override
    public void display() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("           FYP Coordinator Menu          ");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("Logged in as FYP Coordinator: " + fypCoordinatorAccount.getName());
        System.out.println();
        System.out.println("1. Create, update or view projects");
        if (RequestHistory.CheckPendingRequests(fypCoordinatorAccount.getLoginId()) == 1) {
            System.out.println("2. View pending requests (NEW)");
        } else {
            System.out.println("2. View pending requests");
        }
        System.out.println("3. Request to transfer student");
        System.out.println("4. View Own Request History");
        System.out.println("5. View all Requests History");
        System.out.println("6. View FYP Projects");
        System.out.println("7. Change Password");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.println("=========================================");
        System.out.println();
    }

    /**
     * Gets the user input and executes the required src.command
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

            if (userCh == 9) {
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
                            if (fypCoordinatorAccount.getProjList().size() == 2) {
                                System.out.println();
                                System.out.println("You have reach the maximum allocated project capacity.");
                                break;
                            } else {
                                new CreateProjectCommand(fypCoordinatorAccount).execute();
                            }
                            break;
                        case 2:
                            new ModifyFYPTitle(fypCoordinatorAccount).execute();
                            break;
                        case 3:
                            new ViewSubmittedFYPCommand(fypCoordinatorAccount.getName()).execute();
                            break;
                    }
                    break;
                case 2:
                    ViewPendingStudentRequestsCommand ViewRequests = new ViewPendingStudentRequestsCommand(
                            fypCoordinatorAccount.getLoginId());
                    ViewRequests.execute();
                    if (ViewRequests.getRequestNumber() != 0) {
                        System.out.println("=========================================");
                        System.out.println();
                        System.out.println("Please select your choice");
                        System.out.println("1. Approve or Reject Request");
                        System.out.println("2. Return to Main Page");
                        System.out.println();
                        if (!scanner.hasNextInt()) {
                            System.out.println("Invalid input format for option number. Please try again.");
                            scanner.nextLine();
                            System.out.println();
                            System.out.print("Please enter the option number: ");
                            continue;
                        }
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();
                        int reqID;
                        int projID;
                        FYP selectedProj;
                        switch (choice2) {
                            case 1:
                                System.out.println("Input request ID: ");
                                reqID = scanner.nextInt();
                                System.out.println("=========================================");
                                if (reqID / 1000 == 0) {
                                    // requestitlechange
                                    RequestChangeTitle titleRequest = RequestHistory.getRequestChangeTitle(reqID);
                                    if (!titleRequest.getRequesteeID().equals(fypCoordinatorAccount.getLoginId())) {
                                        System.out.println("Invalid Input. Returning to Main Page...");
                                        break;
                                    }
                                    projID = titleRequest.getFypID();
                                    selectedProj = FYPList.getFYPById(projID);
                                    new ModifySubmittedFYPTitleCommand(selectedProj, titleRequest).execute();
                                } else if (reqID / 1000 == 1) {
                                    RequestDeregister deregistrationRequest = RequestHistory.getRequestDeregister(reqID);
                                    projID = deregistrationRequest.getFypID();
                                    selectedProj = FYPList.getFYPById(projID);
                                    new DeregisterStudentCommand(selectedProj, deregistrationRequest).execute();
                                } else if (reqID / 1000 == 2) {
                                    RequestRegister registrationRequest = RequestHistory.getRequestRegister(reqID);
                                    projID = registrationRequest.getFypID();
                                    selectedProj = FYPList.getFYPById(projID);
                                    new AllocateProjectCommand(selectedProj, registrationRequest).execute();
                                } else if (reqID / 1000 == 3) {
                                    RequestTransferSupervisor transferSupervisorRequest = RequestHistory
                                            .getRequestTransferSupervisor(reqID);
                                    new ChangeSupervisorCommand(transferSupervisorRequest)
                                            .execute();
                                } else {
                                    System.out.println("Invalid Input. Returning to Main Page...");
                                }
                                break;

                            case 2:
                                System.out.println("Returning to Main Page...");
                                break;
                            default:
                                System.out.println("Invalid Input. Returning to Main Page...");
                                break;
                        }
                    }
                    break;
                case 3:
                    new RequestTransfertoCoordCommand(fypCoordinatorAccount).execute();
                    break;

                case 4:
                    System.out.println("Please select your choice");
                    System.out.println("1. View all incoming requests");
                    System.out.println("2. View all outgoing requests");
                    System.out.println("3. View all incoming and outgoing requests");
                    System.out.println("4. Return to main page");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input format for option number. Please try again.");
                        scanner.nextLine();
                        System.out.println();
                        System.out.print("Please enter the option number: ");
                        continue;
                    }
                    int requestChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (requestChoice) {
                        case 1:
                            new ViewIncomingRequestRecordsCommand(fypCoordinatorAccount).execute();
                            break;
                        case 2:
                            new ViewOutcomingRequestRecordsCommand(fypCoordinatorAccount).execute();
                            break;
                        case 3:
                            new ViewAllRequestRecordsCommand(fypCoordinatorAccount).execute();
                            break;
                        case 4:
                            System.out.println("Returning to Main Page...");
                            break;
                        default:
                            System.out.println("Invalid Input. Returning to Main Page...");
                            break;
                    }
                    break;
                case 5:
                    new ViewAllRequestHistoryCommand().execute();
                    break;
                case 6:
                    System.out.println("Please select your choice");
                    System.out.println("1. View all FYPs");
                    System.out.println("2. Filter by Supervisor");
                    System.out.println("3. Filter by Status");
                    System.out.println("4. Return back to Main Page");
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
                            new ViewAllFYPCommand().execute();
                            break;
                        case 2:
                            new GenerateFilteredProjectDetailsCommand(2).execute(); // filter by status
                            break;
                        case 3:
                            new GenerateFilteredProjectDetailsCommand(1).execute(); // filter by supervisor
                            break;
                        case 4:
                            System.out.println("Returning to Main Page...");
                            break;
                        default:
                            System.out.println("Invalid Input. Returning to Main Page...");
                            break;
                    }
                    break;
                case 7:
                    new ChangePassword(fypCoordinatorAccount).execute();
                    logout();
                    break;
                case 8:
                    logout();
                    return 1;
                default:
                    break;
            }
            return 1;
        }

    }

    /**
     * Logout from src.account by setting FYP Coordinator Account to null
     */
    public void logout() {
        this.fypCoordinatorAccount = null;
        this.userType = null;
    }

    /**
     * Returns Account in FYP Coordinator
     *
     * @return Account
     */
    public Account getAccount() {
        return fypCoordinatorAccount;
    }

    /**
     * Returns UserType in FYP Coordinato
     *
     * @return UserType
     */
    public UserType getUserType() {
        return userType;
    }
}
