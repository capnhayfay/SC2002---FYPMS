package gui;

import account.*;
import command.ChangePassword;
import command.Student.*;
// import system.SystemSettings;

import java.util.Scanner;

/**
 * GUI which is shown to the Student
 */
public class StudentGUI implements Menu, Logout, GetCommand {
    private Account curAcc;

    /**
     * Creates a StudentGui with the given Student Account
     * 
     * @param curAcc which is the Account of the customer
     */

    public StudentGUI(Account curAcc) {
        this.curAcc = curAcc;
    }

    /**
     * Prints list of possible actions that can be performed by Student
     */
    public void display() {
        System.out.println();
        // System.out.println("-----------------------------------------");
        System.out.println("=========================================");
        System.out.println("               Student Menu             ");
        System.out.println("=========================================");
        // System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Logged in as User: " + curAcc.getLoginId());
        System.out.println();
        System.out.println("1. View all available FYP");
        System.out.println("2. Select FYP");
        System.out.println("3. View your FYP");
        System.out.println("4. Request to Change FYP title");
        System.out.println("5. Request to deregister FYP ");
        System.out.println("6. View Request Status and History");
        System.out.println("7. Change Password");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.println("=========================================");
        System.out.println();
    }

    /**
     * Gets input from Customer and executes the required instruction
     * 
     * @return 0 to exit the program entirely, 1 to continue program
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
                    new ViewAllAvailableFYPCommand().execute();
                    break;
                case 2:
                    new RequestCoordFYPCommand().execute();
                    break;
                case 3:
                    new RequestSuperTitleChangeCommand().execute();
                    break;
                case 4:
                    new RequestCoordDeregisterCommand(curAcc.getName()).execute();
                    break;
                case 5:
                    new ViewRegisteredFYPCommand().execute();
                    break;
                case 6:
                    new ViewSelfRequestRecordsCommand(curAcc.getName());
                    break;

                case 7:
                    new ChangePassword(curAcc).execute();
                    break;

                case 8:
                    logout();
                    System.out.println();
                    System.out.println("Logged out successfully.");
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
     * Logout from account by setting Customer Account to null
     */
    public void logout() {
        this.curAcc = null;
    }

    /**
     * Returns Account in CustomerGUI
     * 
     * @return Account
     */
    public Account getAccount() {
        return this.curAcc;
    }

}
