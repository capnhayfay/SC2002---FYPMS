package src.cli;

import src.account.Account;
import src.account.AccountManager;
import src.account.UserType;
import src.account.student.StudentAccount;
import src.account.supervisor.FYPCoordinatorAccount;
import src.account.supervisor.SupervisorAccount;

import java.util.Scanner;

/**
 * A menu that allows users to login and access their respective accounts.
 * Implements the Menu and Login interfaces.
 */
public class LoginUserMenu implements Menu, Login {
    final UserType userType;
    private StudentAccount studentAccount;
    private FYPCoordinatorAccount fypCoordinatorAccount;
    private SupervisorAccount supervisorAccount;

    /**
     * Creates a new instance of LoginUserMenu for a StudentAccount.
     *
     * @param studentAccount the StudentAccount to be logged in
     */
    public LoginUserMenu(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
        this.userType = UserType.Student;
    }

    /**
     * Creates a new instance of LoginUserMenu for a FYPCoordinatorAccount.
     *
     * @param fypCoordinatorAccount the FYPCoordinatorAccount to be logged in
     */
    public LoginUserMenu(FYPCoordinatorAccount fypCoordinatorAccount) {
        this.fypCoordinatorAccount = fypCoordinatorAccount;
        userType = UserType.FYPCoordinator;
    }

    /**
     * Creates a new instance of LoginUserMenu for a SupervisorAccount.
     *
     * @param supervisorAccount the SupervisorAccount to be logged in
     */
    public LoginUserMenu(SupervisorAccount supervisorAccount) {
        this.supervisorAccount = supervisorAccount;
        userType = UserType.Supervisor;
    }

    /**
     * Displays the login menu and allows users to login.
     * Depending on the userType, it will call the respective login method.
     */
    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print("Please enter your Login ID: ");
            String userLogin = scanner.nextLine();
            System.out.print("Please enter your Password: ");
            String password = scanner.nextLine();
            if (userType == UserType.Student) {
                studentAccount = loginStudent(userLogin, password);
                if (studentAccount == null) {
                    System.out.println();
                    System.out.println("Invalid Details. Please try again.");
                    System.out.println();
                    break;
                } else
                    break;
            } else if (userType == UserType.FYPCoordinator) {
                fypCoordinatorAccount = loginFypCoordinatorAccount(userLogin, password);
                if (fypCoordinatorAccount == null) {
                    System.out.println();
                    System.out.println("Invalid Details. Please try again.");
                    System.out.println();
                    break;
                } else
                    break;
            } else if (userType == UserType.Supervisor) {
                supervisorAccount = loginSupervisorAccount(userLogin, password);
                if (supervisorAccount == null) {
                    System.out.println();
                    System.out.println("Invalid Details. Please try again.");
                    System.out.println();
                    break;
                } else
                    break;
            }
            System.out.println();
            System.out.println("Logged in successfully");
            break;
        }
    }

    /**
     * Authenticates the login details of a StudentAccount.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return the StudentAccount if the login is successful, null if not successful
     */
    public StudentAccount loginStudent(String username, String password) {
        return AccountManager.loginStudent(username, password);
    }

    /**
     * Authenticates the login details of a SupervisorAccount.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return the SupervisorAccount if login is successful, null otherwise
     */
    public SupervisorAccount loginSupervisorAccount(String username, String password) {
        return AccountManager.loginSupervisorAccount(username, password);
    }

    /**
     * Authenticates the login details of a FYPCoordinatorAccount.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return the FYPCoordinatorAccount if login is successful, null otherwise
     */
    public FYPCoordinatorAccount loginFypCoordinatorAccount(String username, String password) {
        return AccountManager.loginFypCoordinatorAccount(username, password);
    }

    /**
     * login which returns account/null
     *
     * @param username which is username entered by user
     * @param password which is password entered by user
     * @return null: Account
     */
    public Account login(String username, String password) {
        return null;
    }

    /**
     * get Account
     *
     * @return null
     */
    public Account getAccount() {
        return null;
    }

    /**
     * Gets student account
     *
     * @return StudentAccount in LoginUserMenu
     */
    public StudentAccount getStudentAccount() {
        return studentAccount;
    }

    /**
     * Gets supervisor account
     *
     * @return SupervisorAccount in LoginUserMenu
     */
    public SupervisorAccount getSupervisorAccount() {
        return supervisorAccount;
    }

    /**
     * get FYP coordinator account
     *
     * @return FYPCoordinatorAccount in LoginUserMenu
     */
    public FYPCoordinatorAccount getFYPCoordinatorAccount() {
        return fypCoordinatorAccount;
    }

    /**
     * get user type
     *
     * @return userType: UserType
     */
    public UserType getUserType() {
        return userType;
    }


}
