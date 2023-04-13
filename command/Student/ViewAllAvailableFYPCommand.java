package command.Student;

import FYPMS.FYPMS1;
import FYPMS.project.FYPList;
import command.Command;

public class ViewAllAvailableFYPCommand implements Command {
    public ViewAllAvailableFYPCommand() {

    }

    public void execute() { // View available FYP from csv
        FYPList fypList = FYPMS1.getFypList();
        fypList.listAvailableFYPsForStudents();
    }
}
