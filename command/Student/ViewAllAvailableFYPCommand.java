package command.Student;

import FYPMS.project.FYPList;
import FYPMS.FYPMS;
import command.Command;

public class ViewAllAvailableFYPCommand implements Command {
    public ViewAllAvailableFYPCommand() { // what constructor

    }

    public void execute() { // View available FYP from csv
        FYPList fypList = FYPMS.getFypList();
        fypList.listAvailableFYPsForStudents();
    }
}
