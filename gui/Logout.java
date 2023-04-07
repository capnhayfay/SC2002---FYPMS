package gui;

import account.Account;

/**
 * Responsible for logging out of Account
 */
public interface Logout {
    /**
     * Allows user to logout of their Account
     */
    public void logout();
    /**
     * Returns Account of user
     * @return Account
     */
    public Account getAccount();
}
