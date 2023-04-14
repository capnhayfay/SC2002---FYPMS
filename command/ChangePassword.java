package command;

import java.util.Scanner;

import account.Account;

/**
 * 
 * This class represents the ChangePassword command that allows a user to change
 * their password.
 * The user is prompted to enter their current password, new password and
 * confirm new password.
 * The command will validate the input, and if the new password matches the
 * confirmation password,
 * the user's password will be changed. The maximum number of password attempts
 * is set to 5.
 */
public class ChangePassword implements Command {
    private Account user;

    /**
     * Constructs a new ChangePassword command with the given user account.
     *
     * @param user the user account to change the password for
     */
    public ChangePassword(Account user) {
        this.user = user;
    }

    /**
     * Executes the ChangePassword command, prompting the user to enter their
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
            if (curPass.equals(user.getPassword())) {
                System.out.println("Enter new password:");
                String newPass = sc.next();
                System.out.println("Confirm new password:");
                String finalPass = sc.next();
                if (finalPass.equals(newPass)) {
                    user.setPassword(newPass);
                    break;
                } else {
                    System.out.println("Password does not match!");
                    triesLeft--;
                }
            } else {
                System.out.println("Wrong password");
                triesLeft--;
            }
        }
        if (triesLeft == 0) {
            System.out.println("Exceeded number of tries possible");
            System.out.println("Exiting Application ...");
        }

    }
}
