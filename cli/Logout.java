package cli;

import account.Account;
import account.UserType;

/**
 * Interface responsible for logging out of Account
 */
public interface Logout {
    /**
     * Allows user to logout of their Account
     */
    void logout();

    Account getAccount();

    UserType getUserType();

}
