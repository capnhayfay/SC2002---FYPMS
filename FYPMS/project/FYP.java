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

    /**
     * FYP represents a Final Year Project with its respective attributes.
     * @param projectId the ID of the project.
     * @param supervisorName the name of the project supervisor.
     * @param supervisorEmail the email of the project supervisor.
     * @param studentID the ID of the student who is registered in this project.
     * @param studentName the name of the student who is registered in this project.
     * @param studentEmail the email of the student who is registered in this project.
     * @param projectTitle the title of the project.
     * @param status the status of the project.
    */
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
        return this.projectId;
    }

    public String getTitle() {
        return this.projectTitle;
    }

    public void setTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setStatus(FYPStatus status) {
        this.status = status;
    }

    public FYPStatus getStatus() {
        return this.status;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public String getSupervisorEmail() {
        return this.supervisorEmail;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentEmail() {
        return this.studentEmail;
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
