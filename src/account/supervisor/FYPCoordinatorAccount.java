package src.account.supervisor;

import src.account.UserType;

import java.util.ArrayList;

/**
 * The {@code FYPCoordinatorAccount} class represents the src.account of a
 * Final Year Project (FYP) Coordinator.
 * <p>
 * This src.account inherits from the {@link SupervisorAccount} class and adds
 * additional functionality specific to FYP Coordinators.
 */
public class FYPCoordinatorAccount extends SupervisorAccount {

    /**
     * Creates a new {@code FYPCoordinatorAccount} instance with the given
     * parameters.
     *
     * @param loginId      the login ID of the coordinator src.account
     * @param password     the password of the coordinator src.account
     * @param userType     the user type of the coordinator src.account
     * @param emailAddress the email address associated with the coordinator src.account
     * @param name         the name of the coordinator src.account
     * @param proj         a list of project IDs associated with the coordinator
     *                     src.account
     */
    public FYPCoordinatorAccount(String loginId, String password, UserType userType, String emailAddress, String name,
                                 ArrayList<String> proj) {
        super(loginId, password, userType, emailAddress, name, proj);
    }

    /**
     * Authenticates the login of a coordinator src.account. This method provides an
     * extra layer of security by implementing a two-factor authentication (2FA)
     * process.
     *
     * @param loginId  the login ID entered by the user
     * @param password the password entered by the user
     * @return the user type of the coordinator src.account if the login is successful,
     * or {@code null} if the login fails
     */
    public UserType login(String loginId, String password) {
        if (this.getLoginId().equals(loginId) && this.getPassword().equals(password)) {
            return this.getUserType();
        }
        return null;
    }

    /**
     * Prints the details of this instance of the coordinator src.account.
     * Specifically, this method prints the name and email address associated with
     * the src.account.
     */
    public void printDetails() {
        System.out.println("Coordinator name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println();
    }
}
