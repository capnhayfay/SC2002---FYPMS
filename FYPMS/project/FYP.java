package FYPMS.project;

/**
 * Represents a single project
 */
public class FYP {
    private final int projectId;
    private String supervisorName;
    private String supervisorEmail;
    private String studentID;
    private String studentName;
    private String studentEmail;
    private String projectTitle;
    private FYPStatus status;

    public FYP(int projectId, String supervisorName, String supervisorEmail, String studentID, String studentName, String studentEmail, String projectTitle, FYPStatus status) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.supervisorName = supervisorName;
        this.supervisorEmail = supervisorEmail;
        this.status = status;
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return projectTitle;
    }

    public void setTitle(String projectTitle) {
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

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }


    /**
     * Prints the details of the instance of FYP.
     */
    public void printFYPDetails() {
        System.out.println();
        System.out.println("Project ID: " + this.projectId);
        System.out.println("Supervisor Name: " + this.supervisorName);
        System.out.println("Supervisor Email: " + this.supervisorEmail);
        System.out.println("Project Title: " + this.projectTitle);
        System.out.println("Status: " + this.status);
        System.out.println("Student Name: " + this.studentName);
        System.out.println("Student Email: " + this.studentEmail);
        System.out.println("-----------------------------------------");
    }

}
