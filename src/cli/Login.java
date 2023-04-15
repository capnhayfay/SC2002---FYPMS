/**
 * Represents an interface for logging into an src.account.
 */
package src.cli;

import src.account.Account;
import src.account.UserType;

/**
 * Login interface for scaling
 */
public interface Login {
    /**
     * Attempts to log into an src.account with the given username and password.
     *
     * @param username the username of the src.account
     * @param password the password of the src.account
     * @return the Account object associated with the logged in src.account, or null if
     *         login was unsuccessful
     */
    Account login(String username, String password);

    /**
     * Gets the Account object associated with the currently logged in src.account.
     *
     * @return the Account object associated with the currently logged in src.account,
     *         or null if no src.account is currently logged in
     */
    Account getAccount();

    /**
     * get UserType of the login src.account
     * @return the UserType of the src.account: UserType
     */
    UserType getUserType();
}
