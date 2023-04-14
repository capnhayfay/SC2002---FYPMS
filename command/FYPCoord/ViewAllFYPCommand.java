package command.FYPCoord;

import FYPMS.FYPMS1;
import FYPMS.project.*;
import command.Command;

public class ViewAllFYPCommand implements Command {

    public ViewAllFYPCommand() {
    }

    public void execute() {
        FYPList projects = FYPMS1.getFypList();
        projects.listAllFYPsForFaculty();
    }
}
