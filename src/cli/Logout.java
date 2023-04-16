package src.cli;

import src.account.Account;
import src.account.UserType;

/**
 * Interface responsible for logging out of Account
 */
public interface Logout {
    /**
     * Allows user to logout of their Account
     */
    void logout();

    /**
     * Gets the account instance
     *
     * @return account instance: Account
     */
    Account getAccount();

    /**
     * Gets the user type of the Account
     *
     * @return account user type: UserType
     */
    UserType getUserType();

}
