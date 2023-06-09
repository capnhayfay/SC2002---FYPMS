package src.FYPMS.project;

import src.exceptions.fypmsExceptions;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents the list of Final Year Projects (FYPs) the department has approved
 * and are entitled to supervise
 */
public class FYPList {
    private static final ArrayList<FYP> fypList = new ArrayList<>();

    /**
     * Default constructor for FYPList
     */
    public FYPList() {
    }

    /**
     * Adds a FYP to the list of FYPs the department can supervise
     *
     * @return list of all FYPS: ArrayList of FYPs
     */
    public static ArrayList<FYP> getFypList() {
        return fypList;
    }

    /**
     * Gets the list of FYPs for a supervisor
     *
     * @param supervisorName the name of the supervisor
     * @return list of FYPs assigned to the supervisor: ArrayList of FYPs
     */
    public static ArrayList<FYP> getSuperFypList(String supervisorName) {
        ArrayList<FYP> superfypList = new ArrayList<FYP>();
        for (FYP fyp : fypList) {
            if (fyp.getSupervisorName().equals(supervisorName)) {
                superfypList.add(fyp);
            }
        }
        return superfypList;
    }

    /**
     * Prints the available FYPs for viewing by students
     */
    public static void listAvailableFYPsForStudents() {
        int fypCount = 1;
        System.out.println();
        System.out.println("List of Available Final Year Projects");
        System.out.println();
        for (FYP fyp : fypList) {
            if (fyp.getStatus() == FYPStatus.AVAILABLE) {
                System.out.println("============= FYP ID " + fyp.getProjectId() + " ==============");
                fyp.printFYPDetails();
                System.out.println();
                fypCount++;
            }
        }
        System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects available! =====");
    }

    /**
     * Returns a FYP using the projectId inputted
     *
     * @param FYPId ID of the FYP to search by
     * @return FYP with ID matching the search term: FYP
     */
    public static FYP getFYPById(int FYPId) {
        for (FYP fyp : fypList) {
            if (fyp.getProjectId() == FYPId) {
                return fyp;
            }
        }
        return null;
    }

    /**
     * Adds a FYP to the list of FYPs the department can supervise
     *
     * @param fyp FYP to be added
     */
    public void addFYP(FYP fyp) {
        fypList.add(fyp);
    }

    public static Optional<FYP> fypIdExists(int fypId) {
        for (FYP fyp : fypList) {
            if (fyp.getProjectId() == fypId) {
                return Optional.of(fyp);
            }
        }
        return Optional.empty();
    }



    // /**
    //  * Searches for a FYP using a keyword inputted by users
    //  *
    //  * @param keyword Keyword used to search for a FYP title
    // //  */
    // public void searchFYPSByKeyword(String keyword) {
    //     int numOfResults = 0;

    //     System.out.println();
    //     System.out.println("Search Results for Final Year Projects titled \"" + keyword + "\"");
    //     System.out.println();

    //     for (FYP fyp : fyps) {
    //         if (fyp.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
    //             System.out.println("============= FYP ID " + fyp.getProjectId() + " ==============");
    //             fyp.printFYPDetails();
    //             System.out.println();
    //             System.out.println("Search Results for Final Year Projects titled \"" + keyword + "\"");
    //             System.out.println();
    //             numOfResults++;
    //         }
    //     }
    //     if (numOfResults == 0) {
    //         System.out.println("No such Final Year Project found in the system!");
    //     } else {
    //         System.out.println("===== " + numOfResults + " Final Year Projects found! =====");
    //     }
    // }

    // public ArrayList<FYP> searchFYPSByKeywordWithReturn(String keyword) {
    //     ArrayList<FYP> fypSearchResults = new ArrayList<>();

    //     for (FYP fyp : fyps) {
    //         if (fyp.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
    //             fypSearchResults.add(fyp);
    //         }
    //     }
    //     return fypSearchResults;
    // }
}
