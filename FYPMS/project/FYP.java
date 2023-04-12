package FYPMS.project;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a single project
 */
public class FYP {

    private int projectId;
    private String supervisorName;
    private String supervisorEmail;
    private String projecTitle;
    private String studentName;
    private String studentEmail;
    private FYPStatus status;

    /**
     * Creates an instance of the FYP class.
     *
     * @param supervisorName   The name of the project supervisor.
     * @param title            The title of the project.
     * @param status           The status of the project: AVAILABLE, RESERVED, or
     *                         ASSIGNED.
     * @param studentName      The name of the student assigned to the project (if
     *                         status is ASSIGNED). Defaults to null.
     * @param requestorList    The name of students who have requested the project.
     * @param statusChangeDate The date of the last change of the project status.
     *                         The first date is constant.
     */
    public FYP(int projectId, String supervisorName, String supervisorEmail, String projecTitle, FYPStatus status) {
        this.projectId = projectId;
        this.supervisorName = supervisorName;
        this.supervisorEmail = supervisorEmail;
        this.projecTitle = projecTitle;
        this.status = status;
    }

    public FYP(int projectId, String supervisorName, String supervisorEmail, String studentName, String studentEmail,
            String projecTitle, FYPStatus status) {
        this.projectId = projectId;
        this.supervisorName = supervisorName;
        this.supervisorEmail = supervisorEmail;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.projecTitle = projecTitle;
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return projecTitle;
    }

    public void setTitle(String title) {
        this.projecTitle = projecTitle;
    }

    public void setStatus(FYPStatus status) {
        this.status = status;
    }

    public FYPStatus getStatus() {
        return status;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    // public String getRequesterList() {
    // return requester;
    // }

    // public LocalDateTime getStatusChangeDate() {
    // return statusChangeDate;
    // }

    /**
     * Prints the details of the instance of FYP.
     */
    public void printFYPDetails() {
        System.out.println();
        System.out.println("Project ID: " + projectId);
        System.out.println("Supervisor Name: " + supervisorName);
        System.out.println("Supervisor Email: " + supervisorEmail);
        System.out.println("Project Title: " + projecTitle);
        System.out.println("Status: " + status);
        System.out.println("-----------------------------------------");
    }

    /**
     * Helper function to convert FYP status in string form to FYPStatus enum, for
     * CSV storage
     * 
     * @param fypStatus FYP status in string form
     * @return FYPStatus enum
     */
    public static FYPStatus convertToFYPStatus(String fypStatus) {
        return switch (fypStatus.toLowerCase()) {
            case "reserved" -> FYPStatus.RESERVED;
            case "unavailable" -> FYPStatus.UNAVAILABLE;
            case "allocated" -> FYPStatus.ALLOCATED;
            default -> FYPStatus.AVAILABLE;
        };
    }
}
