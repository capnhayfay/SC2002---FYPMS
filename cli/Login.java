/**
 * 
 * Represents an interface for logging into an account.
 */
package cli;

import account.Account;
import account.UserType;

public interface Login {
    /**
     * Attempts to log into an account with the given username and password.
     * 
     * @param username the username of the account
     * @param password the password of the account
     * @return the Account object associated with the logged in account, or null if
     *         login was unsuccessful
     */
    Account login(String username, String password);

    /**
     * Gets the Account object associated with the currently logged in account.
     * 
     * @return the Account object associated with the currently logged in account,
     *         or null if no account is currently logged in
     */
    Account getAccount();

    UserType getUserType();
}
