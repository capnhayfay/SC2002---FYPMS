/**
 * This class represents the ChangePassword src.command that allows a user to change
 * their password.
 * The user is prompted to enter their current password, new password and
 * confirm new password.
 * The src.command will validate the input, and if the new password matches the
 * confirmation password,
 * the user's password will be changed. The maximum number of password attempts
 * is set to 5.
 */
package src.command;

import src.account.Account;

import java.util.Scanner;


/**
 * Change password src.command class for users to change their password
 */
public class ChangePassword implements Command {
    private final Account account;

    /**
     * Constructs a new ChangePassword src.command with the given user src.account.
     *
     * @param account the user src.account to change the password for
     */
    public ChangePassword(Account account) {
        this.account = account;
    }

    /**
     * Executes the ChangePassword src.command, prompting the user to enter their
     * current password, new password,
     * and confirmation password. The method will validate the input and change the
     * user's password if the new
     * password matches the confirmation password. The maximum number of password
     * attempts is set to 5.
     */
    public void execute() {
        int triesLeft = 5;
        Scanner sc = new Scanner(System.in);
        while (triesLeft != 0) {
            System.out.println("Enter current password:");
            String curPass = sc.next();
            if (curPass.equals(account.getPassword())) {
                System.out.println();
                System.out.println("Enter new password:");
                String newPass = sc.next();
                System.out.println();
                System.out.println("Confirm new password:");
                String finalPass = sc.next();
                if (finalPass.equals(newPass)) {
                    account.setPassword(newPass);
                    System.out.println();
                    System.out.println("Successfully changed password!");
                    System.out.println();
                    break;
                } else {
                    System.out.println("Password does not match!");
                    triesLeft--;
                }
            } else {
                System.out.println("Wrong password!");
                triesLeft--;
            }
        }
        if (triesLeft == 0) {
            System.out.println("Exceeded number of tries possible");
            System.out.println("Exiting src.runtime.Application ...");
        }

    }
}
