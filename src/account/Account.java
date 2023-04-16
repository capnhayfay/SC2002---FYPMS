package src.account;

/**
 * Abstract class for creating an Account object which holds user's login and  account.
 */

public abstract class Account {
    private final String email; // email address of the user
    private final String name; // name of the user
    private final String loginId; // login ID of the user
    private final UserType userType; // type of account: Student, Supervisor and FYPCoordinator
    private String password; // password of the user

    /**
     * Creates an Account Object with given parameters
     *
     * @param loginId      the login ID of the user
     * @param password     the password of the user
     * @param userType     the type of account (Student, Supervisor, FYPCoordinator)
     * @param emailAddress the email address of the user
     * @param name         the name of the user
     */
    public Account(String loginId, String password, UserType userType, String emailAddress, String name) {

        this.loginId = loginId;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.email = emailAddress;
    }

    /**
     * Returns the login ID of the user.
     *
     * @return the login ID of the user
     */
    public final String getLoginId() {
        return loginId;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param newPass the new password to be set
     */
    public final void setPassword(String newPass) {
        this.password = newPass;
    }

    /**
     * Returns the type of account of the user.
     *
     * @return the type of account of the user
     */
    public final UserType getUserType() {
        return userType;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Abstract method that allows for user login.
     *
     * @param userId   the login ID of the user
     * @param password the password of the user
     * @return the UserType of the logged in user
     */
    public abstract UserType login(String userId, String password);

    /**
     * Abstract method for printing details
     */
    public abstract void printDetails();
}