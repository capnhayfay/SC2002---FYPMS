package command.Supervisor;

import java.util.ArrayList;

import FYPMS.FYPMS;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import command.Command;

public class ViewSubmittedFYPCommand implements Command {
    private String Supervisor;

    public ViewSubmittedFYPCommand(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    public void execute() {
        int fypCount = 1;
        FYPList projects = FYPMS.getFypList();
        ArrayList<FYP> fyps = projects.getFYPs();
        System.out.println();
        System.out.println("List of all Final Year Projects");
        System.out.println();
        for (FYP filteredFyp : fyps) {
            if (filteredFyp.getSupervisorName().equals(Supervisor)) {
                System.out.println("============= FYP No. " + fypCount++ + " ==============");
                filteredFyp.printFYPDetails();
                System.out.println();
            }
        }
        System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects! =====");
    }
}
