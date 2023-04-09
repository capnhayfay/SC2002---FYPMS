package FYPMS.faculty.coordinator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents the list of coordinators who are responsible for managing Final Year Projects (FYPs)
 */
public class CoordinatorList {
    private final ArrayList<Coordinator> coordinators = new ArrayList<>();

    /**
     * Adds a student to the list of FYP coordinators
     *
     * @param coordinator Coordinator to be added
     */
    public void addCoordinator(Coordinator coordinator) {
        coordinators.add(coordinator);
    }

    /**
     * Removes a student from the list of FYP coordinators
     *
     * @param coordinator Coordinator to be removed
     */
    public void removeCoordinator(Coordinator coordinator) {
        coordinators.remove(coordinator);
    }

    /**
     * Prints the details of all coordinators in the system
     */
    public void listAllCoordinators() {
        int coordinatorCount = 1;
        System.out.println();
        System.out.println("List of All Coordinators");
        System.out.println();
        for (Coordinator coordinator : coordinators) {
            System.out.println("============= Coordinator No. " + coordinatorCount++ + " ==============");
            coordinator.printDetails();
            System.out.println();
        }
        System.out.println("===== There are " + coordinators.size() + " coordinators in the system! =====");
        System.out.println();
        System.out.println("-----------------------------------------");
    }

    /**
     * Searches for a student using an email inputted by users
     *
     * @param email Email used to search for a student
     */
    public void searchCoordinatorByEmail(String email) {
        int numOfResults = 0;

        System.out.println();
        System.out.println("Search Results for Coordinator with Email \"" + email + "\"");
        System.out.println();

        for (Coordinator coordinator : coordinators) {
            if (coordinator.getEmail().equals(email)) {
                System.out.println("============= Coordinator No. " + ++numOfResults + " ==============");
                coordinator.printDetails();
                System.out.println();
            }
        }
        if (numOfResults == 0) {
            System.out.println("No such student found in the system!");
        } else {
            System.out.println("===== " + numOfResults + " student(s) found! =====");
        }
    }

    /**
     * Sorts coordinators by their name
     *
     * @param ascending Boolean value indicating if the coordinators should be sorted in ascending or descending order
     */
    public void sortCoordinatorsByName(boolean ascending) {
        if (ascending) {
            coordinators.sort(Comparator.comparing(Coordinator::getName));
        } else {
            coordinators.sort(Comparator.comparing(Coordinator::getName).reversed());
        }
    }

    public ArrayList<Coordinator> getCoordinatorList() {
        return coordinators;
    }
}
