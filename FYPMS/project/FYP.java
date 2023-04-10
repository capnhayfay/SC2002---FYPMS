package FYPMS.project;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a single project
 */
public class FYP {

    private static int currentId = 1;
    private int projectId;
    private String supervisorName;
    private String title;
    private FYPStatus status;
    private String studentName;
    private String requester;
    private LocalDateTime statusChangeDate;

    /**
     * Creates an instance of the FYP class.
     *
     * @param supervisorName   The name of the project supervisor.
     * @param title            The title of the project.
     * @param status           The status of the project: AVAILABLE, RESERVED, or
     *                         ASSIGNED.
     * @param studentName      The name of the student assigned to the project (if
     *                         status is ASSIGNED). Defaults to null.
     * @param requester        The name of students who have requested the project.
     * @param statusChangeDate The date of the last change of the project status.
     *                         The first date is constant.
     */
    public FYP(String supervisorName, String title, FYPStatus status, String studentName, String requester,
            LocalDateTime statusChangeDate) {
        this.projectId = currentId++;
        this.supervisorName = supervisorName;
        this.title = title;
        this.status = status;
        this.studentName = studentName;
        this.requester = requester;
        this.statusChangeDate = statusChangeDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
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

    public String getRequesterList() {
        return requester;
    }

    public LocalDateTime getStatusChangeDate() {
        return statusChangeDate;
    }

    /**
     * Prints the details of the instance of FYP.
     */
    public void printFYPDetails() {
        System.out.println();
        System.out.println("Project ID: " + projectId);
        System.out.println("Title: " + title);
        System.out.println("Status: " + status);
        System.out.println("Project Coordinator: " + supervisorName);
        System.out.println("Assigned Student: " + studentName);
        System.out.println("Last Status Change Date: " + statusChangeDate);
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
            case "available" -> FYPStatus.AVAILABLE;
            case "reserved" -> FYPStatus.RESERVED;
            case "assigned" -> FYPStatus.ASSIGNED;
            default -> null;
        };
    }
}
