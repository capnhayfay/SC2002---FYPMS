/**
Represents a command to view all Final Year Projects (FYPs) for a faculty member.
*/
package command.FYPCoord;

import java.util.ArrayList;

import FYPMS.project.*;
import command.Command;

public class ViewAllFYPCommand implements Command {
    /**
     * Constructs a ViewAllFYPCommand object.
     */
    public ViewAllFYPCommand() {
    }

    /**
     * Executes the command to view all Final Year Projects (FYPs) for a faculty
     * member.
     * Calls the FYPList object to list all the FYPs for a faculty member.
     */
    public void execute() {
        int fypCount = 0;
        ArrayList<FYP> fyps = FYPList.getFypList();
        System.out.println();
        System.out.println("List of All Final Year Projects");
        System.out.println();
        for (FYP fyp : fyps) {
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
