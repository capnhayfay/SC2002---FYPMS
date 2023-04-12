package FYPMS.student;

/**
 * Represents a student in the Final Year Projects module
 */
public class Student {

    private final String name;
    private final String email;
    private int projectsRequested;
    private int assignedProject;
    private StudentStatus studentStatus;

    public Student(String name, String email, int projectsRequested, int assignedProject, StudentStatus studentStatus) {
        this.name = name;
        this.email = email;
        this.projectsRequested = projectsRequested;
        this.assignedProject = assignedProject;
        this.studentStatus = studentStatus;
    }

    /**
     * Creates a Student object for Final Year Projects
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
        System.out.println("Student name: " + name);
        System.out.println("Email: " + email);
        if (studentStatus.equals(StudentStatus.ASSIGNED_PROJECT)) {
            System.out.println("Assigned Project: " + assignedProject);
        } else if (studentStatus.equals(StudentStatus.REQUESTED_PROJECT)) {
            System.out.println("Assigned Project: " + projectsRequested);
        }
        System.out.println("Status:" + studentStatus);
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getProjectRequested() {
        return projectsRequested;
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

    public void ChangeStudentStatus(StudentStatus newStatus) {
        studentStatus = newStatus;
    }
}
