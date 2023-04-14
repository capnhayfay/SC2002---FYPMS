/**
 * This command allows the supervisor to view the status of all final year projects that they are supervising.
 * It prints out a list of all FYPs with their details if the FYP's supervisor name matches the supervisor executing the command.
*/

package command.Supervisor;

import java.util.ArrayList;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import command.Command;

public class ViewSupervisorProjectStatusCommand implements Command {
    private final String Supervisor;
    private final FYPList projects;

    /**
     * Constructs a new ViewSupervisorProjectStatusCommand object with the given
     * supervisor name and FYPList.
     * 
     * @param Supervisor The supervisor's name.
     * @param projects   The FYPList to be used.
     */
    public ViewSupervisorProjectStatusCommand(String Supervisor, FYPList projects) {
        this.Supervisor = Supervisor;
        this.projects = projects;
    }

    /**
     * Executes the command to print out a list of all FYPs with their details if
     * the FYP's supervisor name matches
     * the supervisor executing the command.
     */
    public void execute() {
        int fypCount = 1;
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
