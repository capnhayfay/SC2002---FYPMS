/**
 * Represents a src.command to view all Final Year Projects (FYPs) for a faculty member.
 */
package src.command.FYPCoord;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.command.Command;

import java.util.ArrayList;

/**
 * Command for a coordinator to view ALL FYPs
 */
public class ViewAllFYPCommand implements Command {
    /**
     * Constructs a ViewAllFYPCommand object.
     */
    public ViewAllFYPCommand() {
    }

    /**
     * Executes the src.command to view all Final Year Projects (FYPs) for a faculty
     * member.
     * Calls the FYPList object to list all the FYPs for a faculty member.
     */
    public void execute() {
        int fypCount = 0;
        ArrayList<FYP> fypList = FYPList.getFypList();
        System.out.println();
        System.out.println("List of All Final Year Projects");
        System.out.println();
        for (FYP fyp : fypList) {
            fypCount++;
            System.out.println("============= FYP ID " + fyp.getProjectId() + " ==============");
            fyp.printFYPDetails();
            System.out.println();
        }
        System.out.println("===== There are " + fypCount + " Final Year Projects in the system! =====");
        System.out.println();
        System.out.println("-----------------------------------------");
    }
}
