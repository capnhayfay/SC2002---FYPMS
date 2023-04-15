package src.account;

import src.account.student.StudentAccount;
import src.account.student.StudentStatus;
import src.account.supervisor.FYPCoordinatorAccount;
import src.account.supervisor.SupervisorAccount;

import java.util.ArrayList;

/**
 * The AccountManager class is responsible for managing the various types of
 * accounts in the system.
 * It provides methods for logging in and retrieving src.account information.
 */
public class AccountManager {
    // ArrayLists to hold different types of accounts
    private static final ArrayList<StudentAccount> StudentAccount = new ArrayList<StudentAccount>();
    private static final ArrayList<FYPCoordinatorAccount> FYPCoordinatorAccounts = new ArrayList<FYPCoordinatorAccount>();
    private static final ArrayList<SupervisorAccount> SupervisorAccounts = new ArrayList<SupervisorAccount>();
    /**
     * Creates an instance of the src.account manager to manages the runtime memory versions of all accounts
     */
    public AccountManager() {

    }

    /**
     * Retrieves the SupervisorAccount object associated with the given name.
     *
     * @param SuperName the name of the supervisor src.account to retrieve
     * @return the SupervisorAccount object associated with the given name, or null
     * if not found
     */
    public static SupervisorAccount getSupervisorAccount(String SuperName) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getName().equals(SuperName)) {
                return account;
            }
        }
        return null; // src.account not found
    }

    /**
     * Retrieves a list of all SupervisorAccount objects in the system.
     *
     * @return an ArrayList of SupervisorAccount objects
     */
    public static ArrayList<SupervisorAccount> getSupervisorList() {
        return SupervisorAccounts;
    }

    /**
     * Retrieves a list of all FYPCoordinatorAccount objects in the system.
     *
     * @return an ArrayList of FYPCoordinatorAccount objects
     */
    public static ArrayList<FYPCoordinatorAccount> getCoordinatorList() {
        return FYPCoordinatorAccounts;
    }

    /**
     * Retrieves a list of all StudentAccount objects in the system.
     *
     * @return an ArrayList of StudentAccount objects
     */
    public static ArrayList<StudentAccount> getStudentList() {
        return StudentAccount;
    }

    /**
     * Attempts to log in a student src.account with the given login ID and password.
     *
     * @param loginId  the login ID of the student src.account to log in
     * @param password the password of the student src.account to log in
     * @return the StudentAccount object associated with the given login ID and
     * password, or null if not found
     */
    public static StudentAccount loginStudent(String loginId, String password) {
        for (StudentAccount account : StudentAccount) {
            if (account.getLoginId().equals(loginId) && account.login(loginId, password) != null) {
                return account;
            }
        }
        return null; // src.account not found
    }

    /**
     * Attempts to log in a supervisor src.account with the given login ID and password.
     *
     * @param loginId  the login ID of the supervisor src.account to log in
     * @param password the password of the supervisor src.account to log in
     * @return the SupervisorAccount object associated with the given login ID and
     * password, or null if not found
     */
    public static SupervisorAccount loginSupervisorAccount(String loginId, String password) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getLoginId().equals(loginId) && account.login(loginId, password) != null) {
                return account;
            }
        }
        return null; // src.account not found
    }

    /**
     * Attempts to log in a FYP Coordinator src.account with the given login ID and
     * password.
     *
     * @param loginId  the login ID of the src.account
     * @param password the password of the src.account
     * @return the logged in FYP Coordinator src.account, or null if the src.account does
     * not exist or the password is incorrect
     */
    public static FYPCoordinatorAccount loginFypCoordinatorAccount(String loginId, String password) {
        for (FYPCoordinatorAccount account : FYPCoordinatorAccounts) {
            if (account.getLoginId().equals(loginId) && account.login(loginId, password) != null) {
                return account;
            }
        }
        return null; // src.account not found
    }

    /**
     * Returns the email of the student src.account with the given student ID.
     *
     * @param studentID the student ID of the src.account
     * @return the email of the student src.account, or null if the src.account does not
     * exist
     */
    public static String getStudentEmail(String studentID) {
        for (StudentAccount account : StudentAccount) {
            if (account.getLoginId().equals(studentID)) {
                return account.getEmail();
            }
        }
        return null;
    }

    /**
     * Returns the name of the student src.account with the given student ID.
     *
     * @param studentID the student ID of the src.account
     * @return the name of the student src.account, or null if the src.account does not
     * exist
     */
    public static String getStudentName(String studentID) {
        for (StudentAccount account : StudentAccount) {
            if (account.getLoginId().equals(studentID)) {
                return account.getName();
            }
        }
        return null;
    }

    /**
     * Returns the name of the supervisor src.account with the given supervisor ID.
     *
     * @param supervisorID the supervisor ID of the src.account
     * @return the name of the supervisor src.account, or null if the src.account does not
     * exist
     */
    public static String getSupervisorName(String supervisorID) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getLoginId().equals(supervisorID)) {
                return account.getName();
            }
        }
        return null;
    }

    /**
     * Returns the email of the supervisor src.account with the given supervisor ID.
     *
     * @param supervisorID the supervisor ID of the src.account
     * @return the email of the supervisor src.account, or null if the src.account does not
     * exist
     */
    public static String getSupervisorEmail(String supervisorID) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getLoginId().equals(supervisorID)) {
                return account.getEmail();
            }
        }
        return null;
    }

    /**
     * Sets the status and assigned FYP project ID of the student src.account with the
     * given student ID.
     *
     * @param studentID     the student ID of the src.account
     * @param studentStatus the new status of the student src.account
     * @param fypID         the ID of the FYP project assigned to the student
     *                      src.account
     */
    public static void setStudentStatus(String studentID, StudentStatus studentStatus, int fypID) {
        for (StudentAccount account : StudentAccount) {
            if (account.getLoginId().equals(studentID)) {
                account.setStatus(studentStatus);
                account.setAssignedProject(fypID);
            }
        }
    }
}
