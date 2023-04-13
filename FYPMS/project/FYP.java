package FYPMS.project;

/**
 * Represents a single project
 */
public class FYP {

    private final int projectId;
    private String supervisorName;
    private String studentID;
    private String projectTitle;
    private FYPStatus status;


    public FYP(int projectId, String supervisorName, String studentID, String projectTitle, FYPStatus status) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.supervisorName = supervisorName;
        this.status = status;
        this.studentID = studentID;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return projectTitle;
    }

    public void setTitle(String title) {
        this.projectTitle = projectTitle;
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

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
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
        System.out.println("Project Title: " + projectTitle);
        System.out.println("Status: " + status);
        System.out.println("Student ID: " + studentID);
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
