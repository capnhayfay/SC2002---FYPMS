package gui;
import account.Account;

/**
 * Responsible for verification of Account
 */
public interface Login {
    /**
     * Authenticates login of Account
     * @param username which is Username provided by user
     * @param password which is Password provided by user
     * @return Account if login was successful, null if failed
     */
    public Account login(String username, String password);

    /**
     * Returns Account of user
     * @return Account
     */
    public Account getAccount();
}
