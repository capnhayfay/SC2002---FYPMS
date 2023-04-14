package gui;

import account.*;
import account.supervisor.FYPCoordinatorAccount;
import command.FYPCoord.AllocateProjectCommand;
import command.FYPCoord.ChangeSupervisorCommand;
import command.FYPCoord.DeregisterStudentCommand;
import command.FYPCoord.GenerateFilteredProjectDetailsCommand;
import command.FYPCoord.ViewAllFYPCommand;
import command.FYPCoord.ViewAllRequestHistoryCommand;
import command.Supervisor.CreateProjectCommand;
import command.Supervisor.ModifyFYPTitle;
import command.Supervisor.ModifySubmittedFYPTitleCommand;
import command.Supervisor.RequestTransfertoCoordCommand;
import command.Supervisor.ViewPendingStudentRequestsCommand;
import command.Supervisor.ViewSubmittedFYPCommand;
import command.ChangePassword;
import command.ViewOwnRequestRecordsCommand;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.RequestChangeTitle;
import FYPMS.request.RequestDeregister;
import FYPMS.request.RequestRegister;
import FYPMS.request.RequestTransferSupervisor;

/**
 * GUI which is shown to the Company Admin
 */
public class FYPCoordinatorGUI implements Menu, Logout, GetCommand {
    private FYPCoordinatorAccount FYPCoordinator;
    private String UserType;

    /**
     * Creates a CompanyAdminGui with the given Company Admin Account
     * 
     * @param curAcc which is the Company Admin Account
     */
    public FYPCoordinatorGUI(FYPCoordinatorAccount FYPCoordinator, String UserType) {
        this.FYPCoordinator = FYPCoordinator;
        this.UserType = UserType;
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
        if (new ViewPendingStudentRequestsCommand(FYPCoordinator.getName()).checkUpdates() == 1) {
            System.out.println("2. View student pending requests (NEW)");
        } else {
            System.out.println("2. View student pending requests");
        }
        System.out.println("3. Request to transfer student");
        System.out.println("4. View Own Request History");
        System.out.println("5. View all Requests");
        System.out.println("6. View Project by Filters");
        System.out.println("7. Change Password");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
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
                            if(!FYPCoordinator.getProj_2().equals("0")){
                                System.out.println();
                                System.out.println("You have reach the maximum allocated project capacity.");
                                break;
                            }
                            else{
                                new CreateProjectCommand(FYPCoordinator).execute();    
                            }
                            break;
                        case 2:
                            new ModifyFYPTitle().execute();
                            break;
                        case 3:
                            new ViewSubmittedFYPCommand(FYPCoordinator.getName()).execute();
                            break;
                    }
                    break;
                case 2:
                    new ViewPendingStudentRequestsCommand(FYPCoordinator.getLoginId()).execute();
                    System.out.println("=========================================");
                    System.out.println("Please select your choice");
                    System.out.println("1. Approve or Reject Request");
                    System.out.println("2. Return to Main Page");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input format for option number. Please try again.");
                        scanner.nextLine();
                        System.out.println();
                        System.out.print("Please enter the option number: ");
                        continue;
                    }
                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                    int reqID;
                    int projID;
                    FYP selectedProj;
                    switch (choice2) {
                        case 1:
                            System.out.println("Input project ID: ");
                            projID = scanner.nextInt();
                            selectedProj = FYPList.getFYPById(projID);
                            System.out.println("Input request ID: ");
                            reqID = scanner.nextInt();
                            if (reqID/1000 == 0){
                                // requestitlechange
                                RequestChangeTitle titleRequest = FYPMS1.getRequestChangeTitle(reqID);
                                new ModifySubmittedFYPTitleCommand(selectedProj, titleRequest).execute();
                            }
                            else if (reqID/1000 == 1){
                                RequestDeregister deregistrationRequest = FYPMS1.getRequestDeregister(reqID);
                                new DeregisterStudentCommand(selectedProj,deregistrationRequest).execute();
                            }
                            else if (reqID/1000 == 2){
                                RequestRegister registrationRequest = FYPMS1.getRequestRegister(reqID);
                                new AllocateProjectCommand(selectedProj, registrationRequest).execute();
                            }
                            else if (reqID/1000 == 3){
                                RequestTransferSupervisor transferSupervisorRequest = FYPMS1.getRequestTransferSupervisor(reqID);
                                new ChangeSupervisorCommand(projID, transferSupervisorRequest.getNewSupervisorID()).execute();
                            }
                            else {
                                System.out.println("Wrong input");
                            }
                            break;
                        case 2:
                            break;
                    }
                    break;
                case 3:
                    new RequestTransfertoCoordCommand(FYPCoordinator).execute();
                    break;

                case 4:
                    new ViewOwnRequestRecordsCommand(FYPCoordinator).execute();
                    break;
                case 5:
                    new ViewAllRequestHistoryCommand().execute();
                    break;
                case 6:
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
                case 7:
                    new ChangePassword(FYPCoordinator).execute();
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
     * Logout from account by setting FYP Coordinator Account to null
     */
    public void logout() {
        this.FYPCoordinator = null;
        this.UserType = "";
    }

    /**
     * Returns Account in FYP Coordinator
     * 
     * @return Account
     */
    public Account getAccount() {
        return FYPCoordinator;
    }

    public String getUserType() {
        return UserType;
    }
}
