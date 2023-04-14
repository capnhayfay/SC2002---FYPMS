package account.supervisor;

import java.util.ArrayList;

import account.Account;
import account.UserType;

/**
 * 
 * The {@code SupervisorAccount} class represents the account of a supervisor in
 * the FYP system.
 * It extends the {@code Account} class and adds functionality specific to
 * supervisors, such as
 * the ability to add and retrieve assigned projects.
 */
public class SupervisorAccount extends Account {
    private ArrayList<String> proj;

    /**
     * Creates a new {@code SupervisorAccount} with the given parameters.
     * 
     * @param userId       the login ID of the supervisor account.
     * @param password     the password of the supervisor account.
     * @param userType     the user type of the supervisor account.
     * @param emailAddress the email address associated with the supervisor account.
     * @param name         the name of the supervisor account.
     * @param proj         the list of projects assigned to the supervisor.
     */
    public SupervisorAccount(String userId, String password, UserType userType, String emailAddress, String name,
            ArrayList<String> proj) {
        super(userId, password, userType, emailAddress, name);
        this.proj = proj;
    }

    /**
     * Adds a new project to the list of assigned projects for this supervisor.
     * 
     * @param newProj the name of the new project to be added.
     */
    public void addProj(String newProj) {
        proj.add(newProj);
    }

    /**
     * Retrieves the list of assigned projects for this supervisor.
     * 
     * @return an ArrayList of Strings containing the names of the assigned
     *         projects.
     */
    public ArrayList<String> getProjList() {
        return this.proj;
    }

    /**
     * Authenticates login of supervisor.
     * 
     * @param loginId  the login ID entered by the user.
     * @param password the password entered by the user.
     * @return UserType the user type of the supervisor account if login is
     *         successful, null otherwise.
     */
    public UserType login(String loginId, String password) {
        if (this.getLoginId().equals(loginId) && this.getPassword().equals(password)) {
            return this.getUserType();
        }
        return null;
    }

    /**
     * Prints the details of this instance of the supervisor account.
     * The method displays the name and email of the supervisor, as well as a list
     * of assigned projects.
     * If the supervisor has no assigned projects, a message indicating so will be
     * displayed.
     */
    public void printDetails() {
        System.out.println("Supervisor name: " + this.getName());
        System.out.println("Email: " + this.getEmail());
        if (proj.size() == 0) {
            System.out.println("Supervisor has no projects!");
        } else {
            int counter = 1;
            for (String project : proj) {
                System.out.println("Project " + counter + ": " + project);
                counter++;
                System.out.println();
            }
        }
        System.out.println();
    }
}