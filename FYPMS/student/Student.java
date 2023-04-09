package FYPMS.student;

import java.util.Arrays;

/**
 * Represents a student in the Final Year Projects module
 */
public class Student {

    private final String name;
    private final String email;
    private final int[] projectsRequested;
    private StudentStatus studentStatus;

    public Student(String name, String email, int[] projectsRequested, StudentStatus studentStatus) {
        this.name = name;
        this.email = email;
        this.projectsRequested = projectsRequested;
        this.studentStatus = studentStatus;
    }
    /**
     * Creates a Student object for Final Year Projects
     *
     * @param name Name of the student
     * @param email Email of the student
     * @param projectsRequested Array of integers representing the projectIDs requested
     */
    public Student(String name, String email, int[] projectsRequested) {
        this.name = name;
        this.email = email;
        this.projectsRequested = projectsRequested;
    }

    /**
     * Prints the details of this instance of the student
     */
    public void printDetails() {
        System.out.println("Student name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Projects requested:");
        for (int i = 0; i < projectsRequested.length; i++) {
            System.out.println("Project " + (i + 1) + ": " + projectsRequested[i]);
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

    public int[] getProjectsRequested() {
        return projectsRequested;
    }

    public void printStudentDetails() {
        String commaSeparatedRequests = Arrays.toString(projectsRequested);
        System.out.println();
        System.out.println("Student: " + name);
        System.out.println("Email: " + email);
        System.out.println("Requests: " + commaSeparatedRequests);
        System.out.println("STatus: " + studentStatus);
        System.out.println("-----------------------------------------");
    }

    public boolean hasProject() {
        return studentStatus==StudentStatus.ASSIGNED_PROJECT;
    }
}
