package src.FYPMS.project;

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
     *
     * @param projectId       the ID of the project.
     * @param supervisorName  the name of the project supervisor.
     * @param supervisorEmail the email of the project supervisor.
     * @param studentID       the ID of the student who is registered in this project.
     * @param studentName     the name of the student who is registered in this project.
     * @param studentEmail    the email of the student who is registered in this project.
     * @param projectTitle    the title of the project.
     * @param status          the status of the project.
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

    /**
     * Gets ID of project
     *
     * @return projectID: int
     */
    public int getProjectId() {
        return this.projectId;
    }

    /**
     * Gets title of project
     *
     * @return projectTitle: String
     */
    public String getTitle() {
        return this.projectTitle;
    }

    /**
     * Sets title of project
     *
     * @param projectTitle The title to set for the project
     */
    public void setTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    /**
     * Gets status of project
     *
     * @return status of the project: FYPStatus
     */
    public FYPStatus getStatus() {
        return this.status;
    }

    /**
     * Sets status of project
     *
     * @param status The status to set for the project
     */
    public void setStatus(FYPStatus status) {
        this.status = status;
    }

    /**
     * Returns the name of the supervisor
     *
     * @return supervisorName: String
     */
    public String getSupervisorName() {
        return this.supervisorName;
    }

    /**
     * Sets name of supervisor
     *
     * @param supervisorName name of the project supervisor: String
     */
    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    /**
     * Gets the supervisor email
     *
     * @return supervisorEmail: String
     */
    public String getSupervisorEmail() {
        return this.supervisorEmail;
    }

    /**
     * Sets the supervisor email
     *
     * @param supervisorEmail sets the email of the project supervisor: String
     */
    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    /**
     * Gets the ID of the student assigned to the project
     *
     * @return studentID: String
     */
    public String getStudentID() {
        return this.studentID;
    }

    /**
     * Sets the ID of the student assigned to the project
     *
     * @param studentID the ID of the student assigned to the project
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Gets the Name of the student assigned to the project
     *
     * @return studentName: String
     */
    public String getStudentName() {
        return this.studentName;
    }

    /**
     * Sets the Name of the student assigned to the project
     *
     * @param studentName the Name of the student assigned to the project
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Gets the Email of the student assigned to the project
     *
     * @return studentEmail: String
     */
    public String getStudentEmail() {
        return this.studentEmail;
    }

    /**
     * Sets the Email of the student assigned to the project
     *
     * @param studentEmail the Email of the student assigned to the project
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
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
