package src.account.student;

import src.account.Account;
import src.account.UserType;

/**
 * Class Type for Student Account
 */
public class StudentAccount extends Account {

    private int assignedProject;
    private StudentStatus studentStatus;

    /**
     * Creates a new instance of StudentAccount
     *
     * @param loginId         The login ID of the student
     * @param password        The password of the student
     * @param userType        The userType of the student
     * @param emailAddress    The email address of the student
     * @param name            The name of the student
     * @param assignedProject The ID of the project assigned to the student
     * @param studentStatus   The status of the student (e.g. assigned to a project, not assigned to a project)
     */
    public StudentAccount(String loginId, String password, UserType userType, String emailAddress, String name,
                          int assignedProject, StudentStatus studentStatus) {
        super(loginId, password, userType, emailAddress, name);
        this.assignedProject = assignedProject;
        this.studentStatus = studentStatus;
    }

    /**
     * Authenticates login of StudentAccount
     *
     * @param loginId  The login ID entered by the user
     * @param password The password entered by the user
     * @return The userType if login successful, null if login failed
     */
    public UserType login(String loginId, String password) {
        if (this.getLoginId().equals(loginId) && this.getPassword().equals(password)) {
            return this.getUserType();
        }
        return null;
    }

    /**
     * Prints the details of this instance of the student account
     */
    public void printDetails() {
        System.out.println("StudentAccount name: " + getName());
        System.out.println("Email: " + getEmail());
        if (studentStatus.equals(StudentStatus.ASSIGNED_PROJECT)) {
            System.out.println("Assigned Project: " + assignedProject);
        } else {
            System.out.println("You have no assigned project.");
        }
        System.out.println("Status:" + studentStatus);
        System.out.println();
    }

    /**
     * Gets the ID of the project assigned to the student
     *
     * @return The ID of the project assigned to the student
     */
    public int getAssignedProject() {
        return assignedProject;
    }

    /**
     * Checks if the student has been assigned to a project
     *
     * @return true if the student has been assigned to a project, false otherwise
     */

    /**
     * Sets the ID of the project assigned to the student
     *
     * @param assignedProject The new ID of the project assigned to the student
     */
    public void setAssignedProject(int assignedProject) {
        this.assignedProject = assignedProject;
    }

    /**
     * Gets the status of the student
     *
     * @return The status of the student (e.g. assigned to a project, not assigned
     * to a project)
     */
    public StudentStatus getStatus() {
        return studentStatus;
    }

    /**
     * Sets the status of the student
     *
     * @param studentStatus The new status of the student
     */
    public void setStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

}