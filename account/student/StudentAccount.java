package account.student;

import account.Account;
import account.UserType;

public class StudentAccount extends Account {

    public StudentAccount(String userId, String password, UserType userType, String emailAddress, String name,
            int assignedProject, StudentStatus studentStatus) {
        super(userId, password, userType, emailAddress, name);
        this.assignedProject = assignedProject;
        this.studentStatus = studentStatus;
    }

    /**
     * Authenticates login of CustomerAccount
     * 
     * @param userId   which is entered by user
     * @param password which is entered by user
     * @return Account if login successful, null if login failed
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
     * Creates a StudentAccount object for Final Year Projects
     *
     * @param name              Name of the student
     * @param email             Email of the student
     * @param projectsRequested Array of integers representing the projectIDs
     *                          requested
     */

    /**
     * Prints the details of this instance of the student
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

    public int getAssignedProject() {
        return assignedProject;
    }

    public boolean hasProject() {
        return studentStatus == StudentStatus.ASSIGNED_PROJECT;
    }

    public StudentStatus getStatus() {
        return studentStatus;
    }

    public void setStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public void setAssignedProject(int assignedProject) {
        this.assignedProject = assignedProject;
    }

}