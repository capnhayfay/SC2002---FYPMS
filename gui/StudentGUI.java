package gui;

import account.*;
import account.student.StudentAccount;
import command.ChangePassword;
import command.ViewAllRequestRecordsCommand;
import command.Student.*;
// import system.SystemSettings;

import java.util.Scanner;

/**
 * 
 * The StudentGUI class represents the graphical user interface shown to the
 * student account.
 * It implements the Menu, Logout, and GetCommand interfaces. The class displays
 * a list of actions
 * that can be performed by the student account, and executes the chosen action
 * based on the user's
 * input. The class also allows the student account to logout and get their
 * account details.
 */

public class StudentGUI implements Menu, Logout, GetCommand {
    private StudentAccount curAcc;
    private String UserType;

    /**
     * Constructs a StudentGUI object with the given student account and user type.
     * 
     * @param curAcc   the student account of the user
     * @param UserType the user type of the student account
     */
    public StudentGUI(StudentAccount curAcc, String UserType) {
        this.curAcc = curAcc;
        this.UserType = UserType;
    }

    /**
     * Displays a list of actions that can be performed by the student account,
     * including
     * viewing available FYPs, registering FYPs, viewing registered FYPs, requesting
     * to
     * change FYP title, requesting to deregister FYPs, viewing request status and
     * history,
     * changing password, logging out, and exiting the program.
     */
    public void display() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("         StudentAccount Menu             ");
        System.out.println("=========================================");
        System.out.println("Logged in as User: " + curAcc.getName());
        System.out.println();
        System.out.println("1. View all available FYP");
        System.out.println("2. Register FYP");
        System.out.println("3. View your registered FYP");
        System.out.println("4. Request to change FYP title");
        System.out.println("5. Request to deregister FYP ");
        System.out.println("6. View Request Status and History");
        System.out.println("7. Change Password");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.println("=========================================");
        System.out.println();
    }

    /**
     * Executes the chosen action based on the user's input. Returns 0 to exit the
     * program
     * entirely, 1 to continue the program.
     * 
     * @return 0 to exit the program entirely, 1 to continue the program
     */
    public int execute() {
        Scanner scanner = new Scanner(System.in);
        // System.out.print("Please enter the option number: ");
        // Error handling for invalid input

        System.out.print("Please enter the option number: ");
        while (true) {
            if (!scanner.hasNextInt()) {

                System.out.println("Invalid input format for option number. Please try again.");
                System.out.println();
                System.out.print("Please enter option number again: ");
                scanner.nextLine();
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
                    new ViewAllAvailableFYPCommand(curAcc).execute();
                    break;
                case 2:
                    new RequestCoordFYPCommand(curAcc).execute();
                    break;
                case 3:
                    new ViewRegisteredFYPCommand(curAcc).execute();
                    break;
                case 4:
                    new RequestSuperTitleChangeCommand(curAcc).execute();
                    break;
                case 5:
                    new RequestCoordDeregisterCommand(curAcc).execute();
                    break;
                case 6:
                    new ViewAllRequestRecordsCommand(curAcc).execute();
                    break;

                case 7:
                    new ChangePassword(curAcc).execute();
                    logout();
                    break;

                case 8:
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
     * Logs out from the student account by setting the account and user type to
     * null.
     */
    public void logout() {
        this.curAcc = null;
        this.UserType = "";
    }

    /**
     * Returns the current student account.
     * 
     * @return the current student account
     */
    public Account getAccount() {
        return this.curAcc;
    }

    /**
     * Returns the user type of the student account.
     * 
     * @return the user type of the student account
     */
    public String getUserType() {
        return UserType;
    }

}
