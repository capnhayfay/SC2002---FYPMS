package account.student;

import account.Account;
import account.UserType;

public class StudentAccount extends Account {

    /**
     * Creates a new instance of StudentAccount
     *
     * @param userId          The login ID of the student
     * @param password        The password of the student
     * @param userType        The userType of the student
     * @param emailAddress    The email address of the student
     * @param name            The name of the student
     * @param assignedProject The ID of the project assigned to the student
     * @param studentStatus   The status of the student (e.g. assigned to a project,
     *                        not assigned to a project)
     */
    public StudentAccount(String userId, String password, UserType userType, String emailAddress, String name,
            int assignedProject, StudentStatus studentStatus) {
        super(userId, password, userType, emailAddress, name);
        this.assignedProject = assignedProject;
        this.studentStatus = studentStatus;
    }

    /**
     * Authenticates login of StudentAccount
     * 
     * @param userId   The login ID entered by the user
     * @param password The password entered by the user
     * @return The userType if login successful, null if login failed
     */
    public UserType login(String userId, String password) {
        if (this.getLoginId().equals(userId) && this.getPassword().equals(password)) {
            return this.getUserType();
        }
        return null;
    }

    private int assignedProject;
    private StudentStatus studentStatus;

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
     * Gets the status of the student
     * 
     * @return The status of the student (e.g. assigned to a project, not assigned
     *         to a project)
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

    /**
     * Sets the ID of the project assigned to the student
     * 
     * @param assignedProject The new ID of the project assigned to the student
     */
    public void setAssignedProject(int assignedProject) {
        this.assignedProject = assignedProject;
    }

}