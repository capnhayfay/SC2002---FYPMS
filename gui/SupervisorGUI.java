package gui;

import account.*;
import java.util.Scanner;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.RequestChangeTitle;
import account.supervisor.SupervisorAccount;
import command.ChangePassword;
import command.ViewOwnRequestRecordsCommand;
import command.FYPCoord.AllocateProjectCommand;
import command.Supervisor.*;
import FYPMS.FYPMS1;

/**
 * GUI which is shown to the Supervisor Account
 */
public class SupervisorGUI implements Menu, Logout, GetCommand {
    private SupervisorAccount supervisor;
    private String UserType;

    /**
     * Creates a SupervisorGui with the given Supervisor Account
     * 
     * @param supervisor which is the Supervisor account
     * @return 
     */
    public SupervisorGUI(SupervisorAccount supervisor, String UserType) {
        this.supervisor = supervisor;
        this.UserType = UserType;
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
        System.out.println("1. Create, update or view projects");
        if (new ViewPendingStudentRequestsCommand(supervisor.getName()).checkUpdates() == 1) {
            System.out.println("2. View student pending requests (NEW)");
        } else {
            System.out.println("2. View student pending requests");
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

            if (userCh == 7) {
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
                            if(!supervisor.getProj_2().equals("0")){
                                System.out.println();
                                System.out.println("You have reach the maximum allocated project capacity.");
                                break;
                            }
                            else{
                                new CreateProjectCommand(supervisor).execute();    
                            }
                            break;
                        case 2:
                            new ModifyFYPTitle().execute();
                            break;
                        case 3:
                            new ViewSubmittedFYPCommand(supervisor.getName()).execute();
                            break;
                    }
                    break;
                case 2:
                    new ViewPendingStudentRequestsCommand(supervisor.getLoginId()).execute();
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
                            if (reqID / 1000 == 0){
                                // requestitlechange
                                RequestChangeTitle titleRequest = FYPMS1.getRequestChangeTitle(reqID);
                                new ModifySubmittedFYPTitleCommand(selectedProj, titleRequest).execute();
                            }
                            else if (reqID / 1000 != 0){
                                System.out.println("Wrong input");
                            }
                            break;
                        case 2:
                            break;
                    }
                    break;
                case 3:
                    new RequestTransfertoCoordCommand(supervisor).execute();
                    break;
                case 4:
                    new ViewOwnRequestRecordsCommand(supervisor).execute();
                    break;
                case 5:
                    new ChangePassword(supervisor).execute();
                    logout();
                    break;
                case 6:
                    logout();
                    System.out.println();
                    System.out.println("Logged out successfully.");
                    System.out.println();
                    return 1;
                default:
                    System.out.println();
                    System.out.println("Option number out of range. Please try again.");
                    break;
            }
            return 1;
        }
    }

    /**
     * Logout from account by setting Supervisor Account to null
     */
    public void logout() {
        this.supervisor = null;
        this.UserType = "";
    }

    /**
     * Returns Account in SupervisorAccountGUI
     * 
     * @return Account
     */
    public Account getAccount() {
        return this.supervisor;
    }

    public String getUserType() {
        return UserType;
    }
}
