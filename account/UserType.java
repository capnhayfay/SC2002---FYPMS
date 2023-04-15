package account;

/**
 * 
 * The {@code UserType} enumeration represents the privilege of an account.
 * The privilege of an account determines the list of actions they can do and
 * the GUI they are shown.
 * Possible values are {@code Supervisor}, {@code FYPCoordinator}, and
 * {@code Student}.
*/
public enum UserType {
    /**
     * Indicates that the account has supervisor privileges.
     */
    Supervisor,
    /**
     * Indicates that the account has FYPCoordinator privileges.
     */
    FYPCoordinator,
    /*
     * Indicates that the account has student privileges.
     */
    Student
}
