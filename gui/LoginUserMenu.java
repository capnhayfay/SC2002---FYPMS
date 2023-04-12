package gui;

import java.util.Scanner;

import account.Account;
import account.StudentAccount;
import account.FYPCoordinatorAccount;
import account.SupervisorAccount;
import account.UserType;

import FYPMS.FYPMS;

/**
 * Displayed at start of program
 */
public class LoginUserMenu implements Menu, Login {
    private StudentAccount studentAccount;
    private FYPCoordinatorAccount fypCoordinatorAccount;
    private SupervisorAccount supervisorAccount;
    UserType userType;

    public LoginUserMenu(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
        userType = UserType.Student;
    }

    public LoginUserMenu(FYPCoordinatorAccount fypCoordinatorAccount) {
        this.fypCoordinatorAccount = fypCoordinatorAccount;
        userType = UserType.FYPCoordinator;
    }

    public LoginUserMenu(SupervisorAccount supervisorAccount) {
        this.supervisorAccount = supervisorAccount;
        userType = UserType.Supervisor;
    }

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
                System.out.println("student account is " + studentAccount);
                if (studentAccount == null) {
                    System.out.println("Invalid Details. Please try again.");
                    continue;
                } else
                    break;
            } else if (userType == UserType.FYPCoordinator) {
                fypCoordinatorAccount = loginFypCoordinatorAccount(userLogin, password);
                if (fypCoordinatorAccount == null) {
                    System.out.println("Invalid Details. Please try again.");
                    continue;
                } else
                    break;
            } else if (userType == UserType.Supervisor) {
                supervisorAccount = loginSupervisorAccount(userLogin, password);
                if (supervisorAccount == null) {
                    System.out.println("Invalid Details. Please try again.");
                    continue;
                } else
                    break;
            }
            System.out.println();
            System.out.println("Logged in successfully");
            break;
        }
    }

    /**
     * 
     * Authenticates username and password
     * 
     * @param username which is username entered by Guest
     * @param password which is password entered by Guest
     * @return Account if login successful, null if not successful
     */
    public StudentAccount loginStudent(String username, String password) {
        return FYPMS.loginStudent(username, password);
    }

    /**
     * 
     * Authenticates username and password
     * 
     * @param username which is username entered by Guest
     * @param password which is password entered by Guest
     * @return Account if login successful, null if not successful
     */
    public SupervisorAccount loginSupervisorAccount(String username, String password) {
        return FYPMS.loginSupervisorAccount(username, password);
    }

    /**
     * 
     * Authenticates username and password
     * 
     * @param username which is username entered by Guest
     * @param password which is password entered by Guest
     * @return Account if login successful, null if not successful
     */
    public FYPCoordinatorAccount loginFypCoordinatorAccount(String username, String password) {
        return FYPMS.loginFypCoordinatorAccount(username, password);
    }

    /**
     * Authenticates username and password
     * 
     * @param username which is username entered by Guest
     * @param password which is password entered by Guest
     * @return Account if login successful, null if not successful
     */
    public Account login(String username, String password) {
        return null;
    }

    /**
     * Returns Account in GuestGUI
     * 
     * @return Account
     */
    public Account getAccount() {
        return null;
    }

    public StudentAccount getStudentAccount() {
        return studentAccount;
    }

    public SupervisorAccount getSupervisorAccount() {
        return supervisorAccount;
    }

    public FYPCoordinatorAccount getFYPCoordinatorAccount() {
        return fypCoordinatorAccount;
    }

}
