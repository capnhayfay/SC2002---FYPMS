package FYPMS.faculty.supervisor;

/**
 * Represents a Coordinator for Final Year Projects
 */
public class Supervisor {

    private String name;
    private String email;
    private String supervisorId;
    private String proj_1;
    private String proj_2;

    /**
     * Creates a Coordinator object for Final Year Projects with a Maximum of 2
     * Projects
     *
     * @param name  Name of the Coordinator
     * @param email Email of the Coordinator
     * @param proj1 Title of first project
     * @param proj2 Title of second project
     */
    public Supervisor(String name, String email, String proj1, String proj2) {
        this.name = name;
        this.email = email;
        int atIndex = email.indexOf("@");
        this.supervisorId = email.substring(0, atIndex);
        this.proj_1 = proj1;
        this.proj_2 = proj2;
    }

    /**
     * Prints the details of this instance of the Coordinator
     */
    public void printDetails() {
        System.out.println("Coordinator name: " + name);
        System.out.println("Email: " + email);
        if (proj_1.equals("0")) {
            System.out.println("Coordinator has no projects!");
        } else {
            System.out.println("Project 1: " + proj_1);
            if (!proj_2.equals("0")) {
                System.out.println("Project 2: " + proj_2);
            }
            System.out.println();
        }
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setProj_1(String proj1) {
        this.proj_1 = proj1;
    }

    public String getProj_1() {
        return proj_1;
    }

    public void setProj_2(String proj2) {
        this.proj_2 = proj2;
    }

    public String getProj_2() {
        return proj_2;
    }

}