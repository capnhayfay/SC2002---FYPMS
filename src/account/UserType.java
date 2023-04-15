package src.account;

/**
 * The {@code UserType} enumeration represents the privilege of an src.account.
 * The privilege of an src.account determines the list of actions they can do and
 * the GUI they are shown.
 * Possible values are {@code Supervisor}, {@code FYPCoordinator}, and
 * {@code Student}.
 */
public enum UserType {
    /**
     * Indicates that the src.account has supervisor privileges.
     */
    Supervisor,
    /**
     * Indicates that the src.account has FYPCoordinator privileges.
     */
    FYPCoordinator,
    /**
     * Indicates that the src.account has student privileges.
     */
    Student
}
