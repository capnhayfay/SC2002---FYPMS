package FYPMS.faculty.supervisor;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents the list of supervisors who are responsible for managing Final
 * Year Projects (FYPs)
 */
public class SupervisorList {
    private final ArrayList<Supervisor> supervisors = new ArrayList<>();

    /**
     * Adds a supervisor to the list of FYP supervisors
     *
     * @param supervisor Coordinator to be added
     */
    public void addSupervisor(Supervisor supervisor) {
        supervisors.add(supervisor);
    }

    /**
     * Removes a supervisor from the list of FYP supervisors
     *
     * @param supervisor Coordinator to be removed
     */
    public void removeSupervisor(Supervisor supervisor) {
        supervisors.remove(supervisor);
    }

    /**
     * Prints the details of all supervisors in the system
     */
    public void listAllSupervisors() {
        int supervisorCount = 1;
        System.out.println();
        System.out.println("List of All Supervisors");
        System.out.println();
        for (Supervisor supervisor : supervisors) {
            System.out.println("============= Coordinator No. " + supervisorCount++ + " ==============");
            supervisor.printDetails();
            System.out.println();
        }
        System.out.println("===== There are " + supervisors.size() + " supervisors in the system! =====");
        System.out.println();
        System.out.println("-----------------------------------------");
    }

    /**
     * Searches for a supervisor using an email inputted by users
     *
     * @param email Email used to search for a supervisor
     */
    public void searchSupervisorByEmail(String email) {
        int numOfResults = 0;

        System.out.println();
        System.out.println("Search Results for Coordinator with Email \"" + email + "\"");
        System.out.println();

        for (Supervisor supervisor : supervisors) {
            if (supervisor.getEmail().equals(email)) {
                System.out.println("============= Coordinator No. " + ++numOfResults + " ==============");
                supervisor.printDetails();
                System.out.println();
            }
        }
        if (numOfResults == 0) {
            System.out.println("No such supervisor found in the system!");
        } else {
            System.out.println("===== " + numOfResults + " supervisor(s) found! =====");
        }
    }

    /**
     * Returns a supervisor using a Name inputted by users
     *
     * @param name Name used to search for a supervisor
     */
    public Supervisor getSupervisor(String name) {
        for (Supervisor supervisor : supervisors) {
            if (supervisor.getName().equals(name)) {
                return supervisor;
            }
        }
        return null;
    }

    /**
     * Sorts supervisors by their name
     *
     * @param ascending Boolean value indicating if the supervisors should be sorted
     *                  in ascending or descending order
     */
    public void sortSupervisorsByName(boolean ascending) {
        if (ascending) {
            supervisors.sort(Comparator.comparing(Supervisor::getName));
        } else {
            supervisors.sort(Comparator.comparing(Supervisor::getName).reversed());
        }
    }

    public ArrayList<Supervisor> getSupervisors() {
        return supervisors;
    }
}
