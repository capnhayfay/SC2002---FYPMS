package src.cli;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
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
import src.cli.InputValidation;

import java.util.Scanner;

import static src.cli.InputValidation.scannerValidation;

/**
 * GUI which is shown to FYP Coordinator
 */
public class FYPCoordinatorCLI implements Menu, Logout, GetCommand {
    /**
     * The FYPCoordinator account of the user
     */
    private FYPCoordinatorAccount fypCoordinatorAccount;
    /**
     * The user type of the user
     */
    private UserType userType;

    /**
     * Constructs a FYPCoordinatorCLI with the given FYPCoordinator account and user
     * type
     *
     * @param fypCoordinatorAccount the FYPCoordinator account of the user
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
            System.out.println("2. View pending requests (NEW PENDING REQUEST)");
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
            int userCh = scannerValidation(scanner);
            System.out.println("=========================================");

            if (userCh == 9) {
                return 0;
            }
            switch (userCh) {
                case 1 -> {
                    System.out.println("Please input a choice from Create, Update, and View Projects");
                    System.out.println("1. Create Project");
                    System.out.println("2. Update Project Title");
                    System.out.println("3. View Projects");
                    int selectedChoice = scannerValidation(scanner);
                    switch (selectedChoice) {
                        case 1 -> {
                            if (fypCoordinatorAccount.getProjList().size() == 2) {
                                System.out.println();
                                System.out.println("You have reach the maximum allocated project capacity.");
                            } else {
                                new CreateProjectCommand(fypCoordinatorAccount).execute();
                            }
                        }
                        case 2 -> new ModifyFYPTitle(fypCoordinatorAccount).execute();
                        case 3 -> new ViewSubmittedFYPCommand(fypCoordinatorAccount.getName()).execute();
                    }
                }
                case 2 -> {
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
                        int choice2 = scannerValidation(scanner);
                        int reqID;
                        int projID;
                        FYP selectedProj = null;
                        switch (choice2) {
                            case 1 -> {
                                System.out.println("Input request ID: ");
                                reqID = scannerValidation(scanner);
                                System.out.println("=========================================");
                                if (reqID / 1000 == 0) {
                                    // requestitlechange
                                    RequestChangeTitle titleRequest = null;
                                    titleRequest = RequestHistory.getRequestChangeTitle(reqID);
                                    do{
                                        try {
                                            titleRequest = RequestHistory.getRequestChangeTitle(reqID);
                                            projID = titleRequest.getFypID();
                                            selectedProj = FYPList.getFYPById(projID);
                                        } catch (Exception e){
                                            System.out.println("Incorrect request ID entered");
                                            System.out.println();
                                            System.out.println("Please Reenter Input");
                                            reqID = scannerValidation(scanner);

                                        }

                                    } while(selectedProj == null);
                                    if (!titleRequest.getRequesteeID().equals(fypCoordinatorAccount.getLoginId())) {
                                        System.out.println("Invalid Input. Returning to Main Page...");
                                        break;
                                    }
                                    if(!titleRequest.getRequestStatus().equals(RequestStatus.PENDING)) {
                                        System.out.println("Error: Request has already been handled.");
                                        break;
                                    }
                                    new ModifySubmittedFYPTitleCommand(selectedProj, titleRequest).execute();
                                } else if (reqID / 1000 == 1) {
                                    RequestDeregister deregistrationRequest = null;
                                    do{
                                        try {
                                            deregistrationRequest = RequestHistory.getRequestDeregister(reqID);
                                            projID = deregistrationRequest.getFypID();
                                            selectedProj = FYPList.getFYPById(projID);
                                        } catch (Exception e){
                                            System.out.println("Incorrect request ID entered");
                                            System.out.println();
                                            System.out.println("Please Reenter Input");
                                            reqID = scannerValidation(scanner);
                                        }

                                    } while(selectedProj == null);
                                    if(!deregistrationRequest.getRequestStatus().equals(RequestStatus.PENDING)) {
                                        System.out.println("Error: Request has already been handled.");
                                        break;
                                    }
                                    new DeregisterStudentCommand(selectedProj, deregistrationRequest).execute();
                                } else if (reqID / 1000 == 2) {
                                    RequestRegister registrationRequest = null;
                                    do{
                                        try {
                                            registrationRequest = RequestHistory.getRequestRegister(reqID);
                                            projID = registrationRequest.getFypID();
                                            selectedProj = FYPList.getFYPById(projID);
                                        } catch (Exception e){
                                            System.out.println("Incorrect request ID entered");
                                            System.out.println();
                                            System.out.println("Please Reenter Input");
                                            reqID = scannerValidation(scanner);

                                        }

                                    } while(selectedProj == null);

                                    if(!registrationRequest.getRequestStatus().equals(RequestStatus.PENDING)) {
                                        System.out.println("Error: Request has already been handled.");
                                        break;
                                    }
                                    new AllocateProjectCommand(selectedProj, registrationRequest).execute();
                                } else if (reqID / 1000 == 3) {
                                    RequestTransferSupervisor transferSupervisorRequest = null;

                                    do{
                                        try {
                                            transferSupervisorRequest = RequestHistory.getRequestTransferSupervisor(reqID);
                                            projID = transferSupervisorRequest.getFypID();
                                            selectedProj = FYPList.getFYPById(projID);
                                        } catch (Exception e){
                                            System.out.println("Incorrect request ID entered");
                                            System.out.println();
                                            System.out.println("Please Reenter Input");
                                            reqID = scannerValidation(scanner);

                                        }

                                    } while(selectedProj == null);

                                    if(!transferSupervisorRequest.getRequestStatus().equals(RequestStatus.PENDING)) {
                                        System.out.println("Error: Request has already been handled.");
                                        break;
                                    }
                                    new ChangeSupervisorCommand(transferSupervisorRequest)
                                            .execute();
                                } else {
                                    System.out.println("Invalid Input. Returning to Main Page...");
                                }
                            }
                            case 2 -> System.out.println("Returning to Main Page...");
                            default -> System.out.println("Invalid Input. Returning to Main Page...");
                        }
                    }
                }
                case 3 -> new RequestTransfertoCoordCommand(fypCoordinatorAccount).execute();
                case 4 -> {
                    System.out.println("Please select your choice");
                    System.out.println("1. View all incoming requests");
                    System.out.println("2. View all outgoing requests");
                    System.out.println("3. View all incoming and outgoing requests");
                    System.out.println("4. Return to main page");
                    int requestChoice = scannerValidation(scanner);
                    switch (requestChoice) {
                        case 1 -> new ViewIncomingRequestRecordsCommand(fypCoordinatorAccount).execute();
                        case 2 -> new ViewOutcomingRequestRecordsCommand(fypCoordinatorAccount).execute();
                        case 3 -> new ViewAllRequestRecordsCommand(fypCoordinatorAccount).execute();
                        case 4 -> System.out.println("Returning to Main Page...");
                        default -> System.out.println("Invalid Input. Returning to Main Page...");
                    }
                }
                case 5 -> new ViewAllRequestHistoryCommand().execute();
                case 6 -> {
                    System.out.println("Please select your choice");
                    System.out.println("1. View all FYPs");
                    System.out.println("2. Filter by Supervisor");
                    System.out.println("3. Filter by Status");
                    System.out.println("4. Return back to Main Page");
                    int FilterChoice = scannerValidation(scanner);
                    System.out.println();
                    switch (FilterChoice) {
                        case 1 -> new ViewAllFYPCommand().execute();
                        case 2 -> new GenerateFilteredProjectDetailsCommand(2).execute(); // filter by status
                        case 3 -> new GenerateFilteredProjectDetailsCommand(1).execute(); // filter by supervisor
                        case 4 -> System.out.println("Returning to Main Page...");
                        default -> System.out.println("Invalid Input. Returning to Main Page...");
                    }
                }
                case 7 -> {
                    new ChangePassword(fypCoordinatorAccount).execute();
                    logout();
                }
                case 8 -> {
                    logout();
                    return 1;
                }
                default -> {
                }
            }
            return 1;
        }

    }



    /**
     * Logout from account by setting FYP Coordinator Account to null
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
