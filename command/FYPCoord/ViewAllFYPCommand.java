/**
Represents a command to view all Final Year Projects (FYPs) for a faculty member.
*/
package command.FYPCoord;

import FYPMS.FYPMS1;
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
        FYPList projects = FYPMS1.getFypList();
        projects.listAllFYPsForFaculty();
    }
}
