package account.supervisor;

import java.util.ArrayList;

import account.UserType;

/**
 * Account for Company Admin
 */
public class FYPCoordinatorAccount extends SupervisorAccount {
    /**
     * Creates FYPCoordinatorAccount with given parameters
     * 
     * @param loginId      which is the login ID of FYPCoordinatorAccount
     * @param password     which is the password of FYPCoordinatorAccount
     * @param userType     which is the Privilege of FYPCoordinatorAccount
     * @param emailAddress which is the email address of FYPCoordinatorAccount
     * @param name         which is the name of FYPCoordinatorAccount
     */
    public FYPCoordinatorAccount(String loginId, String password, UserType userType, String emailAddress, String name,
            ArrayList<String> proj) {
        super(loginId, password, userType, emailAddress, name, proj);

    }

    /**
     * Authenticates login of Coordinator. Extra layer of security (2FA) is
     * included.
     * 
     * @param loginId  which is entered by user
     * @param password which is entered by user
     * @return Account if login successful, null if login failed
     */
    public UserType login(String loginId, String password) {
        if (this.getLoginId().equals(loginId) && this.getPassword().equals(password)) {
            return this.getUserType();
        }
        return null;
    }

    /**
     * Prints the details of this instance of the Coordinator
     */
    public void printDetails() {
        System.out.println("Coordinator name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println();
    }
}