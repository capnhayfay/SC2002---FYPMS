package FYPMS.project;

import FYPMS.FYPMS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents the list of movies the company has purchased and are entitled to show in their theatres
 */
/**
 * Represents the list of Final Year Projects (FYPs) the department has approved and are entitled to supervise
 */
public class FYPList {
    private final ArrayList<FYP> fyps = new ArrayList<>();

    /**
     * Updates FYP status to 'Completed' automatically (upon starting the application) when the FYP has passed its deadline
     */
    public void updateExpiredFYPStatus() {
        for (FYP fyp : fyps) {
            if (fyp.getDeadline().isBefore(LocalDateTime.now())) {
                if (fyp.getStatus() != FYPStatus.COMPLETED) {
                    fyp.setStatus(FYPStatus.COMPLETED);
                }
            }
        }
    }

    /**
     * Adds a FYP to the list of FYPs the department can supervise
     *
     * @param fyp FYP to be added
     */
    public void addFYP(FYP fyp) {
        fyps.add(fyp);
    }

    /**
     * Removes a FYP by setting its status to "Completed"
     *
     * @param fypId Id of FYP to be removed
     */
    public void removeFYP(int fypId) {
        this.updateFYPStatus(fypId, FYPStatus.COMPLETED);
    }

    /**
     * Prints the available FYPs (Ongoing, Pending) for viewing by students
     */
    public void listAvailableFYPsForStudents() {
        int fypCount = 1;
        System.out.println();
        System.out.println("List of Available Final Year Projects");
        System.out.println();
        for (FYP fyp : fyps) {
            if (fyp.getStatus() != FYPStatus.COMPLETED) {
                System.out.println("============= FYP No. " + fypCount++ + " ==============");
                fyp.printFYPDetails();
                System.out.println();
            }
        }
        System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects available! =====");
    }

    /**
     * Prints all FYPs in the system for faculty members
     */
    public void listAllFYPsForFaculty() {
        int fypCount = 1;
        System.out.println();
        System.out.println("List of All Final Year Projects");
        System.out.println();
        for (FYP fyp : fyps) {
            System.out.println("============= FYP No. " + fypCount++ + " ==============");
            fyp.printFYPDetails();
            System.out.println();
        }
        System.out.println("===== There are " + fyps.size() + " Final Year Projects in the system! =====");
        System.out.println();
        System.out.println("-----------------------------------------");
    }

    /**
     * Searches for a FYP using a keyword inputted by users
     *
     * @param keyword Keyword used to search for a FYP title
     */
    public void searchFYPByTitle(String keyword) {
        int numOfResults = 0;

        System.out.println();
        System.out.println("Search Results for Final Year Projects titled \"" + keyword + "\"");
        System.out.println();

        for (FYP fyp : fyps) {
            if (fyp.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("============= FYP No. " + ++numOfResults + " ==============");
                fyp.printFYPDetails();
                System.out.println();
                System.out.println("Search Results for Final Year Projects titled \"" + keyword + "\"");
                System.out.println();
            }
        }
        if (numOfResults == 0) {
            System.out.println("No such Final Year Project found in the system!");
        } else {
            System.out.println("===== " + numOfResults + " Final Year Projects found! =====");
        }
    }

    /**
     * Sorts FYPs by deadline date
     *
     * @param ascending Boolean value indicating if the FYPs should be sorted in ascending or descending order
     */
    public void sortFYPsByDeadline(boolean ascending) {
        if (ascending) {
            Collections.sort(fyps, Comparator.comparing(FYP::getDeadline));
        } else {
            Collections.sort(fyps, Comparator.comparing(FYP::getDeadline).reversed());
        }
    }
}


