package src.cli;

import src.account.Account;
import src.account.UserType;
import src.account.student.StudentAccount;
import src.command.ChangePassword;
import src.command.Student.*;
import src.command.ViewAllRequestRecordsCommand;

import java.util.Scanner;

/**
 * The StudentCLI class represents the graphical user interface shown to the
 * student src.account.
 * It implements the Menu, Logout, and GetCommand interfaces. The class displays
 * a list of actions
 * that can be performed by the student src.account, and executes the chosen action
 * based on the user's
 * input. The class also allows the student src.account to logout and get their
 * src.account details.
 */

public class StudentCLI implements Menu, Logout, GetCommand {
    private StudentAccount studentAccount;
    private UserType userType;

    /**
     * Constructs a StudentCLI object with the given student src.account and user type.
     *
     * @param studentAccount the student src.account of the user
     * @param userType       the user type of the student src.account
     */
    public StudentCLI(StudentAccount studentAccount, UserType userType) {
        this.studentAccount = studentAccount;
        this.userType = userType;
    }

    /**
     * Displays a list of actions that can be performed by the student src.account,
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
        System.out.println("Logged in as User: " + studentAccount.getName());
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
                    new ViewAllAvailableFYPCommand(studentAccount).execute();
                    break;
                case 2:
                    new RequestCoordFYPCommand(studentAccount).execute();
                    break;
                case 3:
                    new ViewRegisteredFYPCommand(studentAccount).execute();
                    break;
                case 4:
                    new RequestSuperTitleChangeCommand(studentAccount).execute();
                    break;
                case 5:
                    new RequestCoordDeregisterCommand(studentAccount).execute();
                    break;
                case 6:
                    new ViewAllRequestRecordsCommand(studentAccount).execute();
                    break;

                case 7:
                    new ChangePassword(studentAccount).execute();
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
     * Logs out from the student src.account by setting the src.account and user type to
     * null.
     */
    public void logout() {
        this.studentAccount = null;
        this.userType = null;
    }

    /**
     * Returns the current student src.account.
     *
     * @return the current student src.account
     */
    public Account getAccount() {
        return this.studentAccount;
    }

    /**
     * Returns the user type of the student src.account.
     *
     * @return the user type of the student src.account
     */
    public UserType getUserType() {
        return userType;
    }

}
