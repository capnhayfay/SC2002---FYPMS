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
     * Gets the src.account instance
     *
     * @return src.account instance: Account
     */
    Account getAccount();

    /**
     * Gets the user type of the Account
     *
     * @return src.account user type: UserType
     */
    UserType getUserType();

}
