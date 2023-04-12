package command.FYPCoord;

import FYPMS.FYPMS;
import FYPMS.project.*;
import command.Command;

public class ViewAllFYPCommand implements Command {

    public ViewAllFYPCommand() {
    }

    public void execute() {
        // Available Projects

        //
        // int fypCount = 1;
        FYPList projects = FYPMS.getFypList();
        projects.listAllFYPsForFaculty();
        // ArrayList<FYP> fyps = projects.getFYPs();
        // System.out.println();
        // System.out.println("List of " + filter.toString().toLowerCase() + " Final
        // Year Projects");
        // System.out.println();
        // for (FYP fyp : fyps) {
        // if (fyp.getStatus() == filter) {
        // System.out.println("============= FYP No. " + fypCount++ + "
        // ==============");
        // fyp.printFYPDetails();
        // System.out.println();
        // }
        // }
        // System.out.println("===== There are " + (fypCount - 1) + " Final Year
        // Projects "
        // + filter.toString().toLowerCase() + "! =====");
    }
}
